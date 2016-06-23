package calculator.impl.context.contextbean;

import calculator.impl.tokens.BinaryOperator;
import calculator.impl.tokens.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.jar.Pack200;

/**
 * Contains output context data.
 */
public class MathExpressionBean implements OutputContextBean<Double> {

    private static final Logger log = LoggerFactory.getLogger(MathExpressionBean.class);

    /**
     * Operands stack.
     */
    private Deque<Double> numbersStack = new ArrayDeque<>();

    /**
     * Operators stack.
     */
    private Deque<BinaryOperator> operatorsStack = new ArrayDeque<>();

    /**
     * For brackets - empty function.
     * Considered, that expression always wrapped in additional brackets.
     */
    private Function function;

    /**
     * Link to parent bean. Null if no functions or brackets parsed.
     */
    private MathExpressionBean parent;

    /**
     * Constructor for first bean.
     */
    public MathExpressionBean() {
        if (log.isDebugEnabled()) {
            log.debug("Created bean from empty function.");
        }

        this.function = args -> args[0];
    }

    public MathExpressionBean(Function function, MathExpressionBean parent) {
        if (log.isDebugEnabled()) {
            log.debug("Created bean from \"" + function.getClass().getName() + "\" function.");
        }

        this.parent = parent;
        this.function = function;
    }

    public MathExpressionBean getParent() {
        return parent;
    }

    public double getResultValue() {

        if (log.isDebugEnabled()) {
            if (parent != null) {
                log.debug("Popping function result...");
            } else {
                log.debug("Popping calculation result...");
            }
        }

        while (!operatorsStack.isEmpty()) {
            popTopOperator();
        }

        return function.execute(numbersStack.toArray(new Double[0]));
    }

    /**
     * Add operand to stack.
     * @param operand Operand to be pushed to this bean.
     */
    public void pushOperand(Double operand) {
        numbersStack.push(operand);
    }

    /**
     * Add operator to stack. Calculates operators with higher priority.
     * @param operator Operator to be pushed to this bean.
     */
    public void pushOperator(BinaryOperator operator) {
        while (!operatorsStack.isEmpty() &&
                (operatorsStack.peek().compareTo(operator) > 0)) {
            popTopOperator();
        }
        operatorsStack.push(operator);
    }

    /**
     * Calculates top operator.
     */
    private void popTopOperator() {
        double rightOperand = numbersStack.pop();
        double leftOperand = numbersStack.pop();

        numbersStack.add(operatorsStack.pop().execute(leftOperand, rightOperand));
    }

    @Override
    public boolean isInFunction() {
        return parent != null;
    }
}

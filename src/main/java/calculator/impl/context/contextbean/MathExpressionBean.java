package calculator.impl.context.contextbean;

import calculator.CalculationException;
import calculator.impl.operators.BinaryOperator;
import calculator.impl.operators.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

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
    private Deque<BinaryOperator<Double>> operatorsStack = new ArrayDeque<>();

    /**
     * For brackets - empty function.
     * Considered, that expression always wrapped in additional brackets.
     */
    private Function<Double> function;

    /**
     * Link to parent bean. Null if no functions or brackets parsed.
     */
    private Optional<OutputContextBean> parent = Optional.empty();

    /**
     * Constructor for first bean.
     */
    public MathExpressionBean() {
        if (log.isDebugEnabled()) {
            log.debug("Created bean from empty function.");
        }

        this.function = new Function<Double>() {
            @Override
            public int getMinArgsNumber() {
                return 1;
            }

            @Override
            public Double execute(Double[] args) {
                return args[0];
            }
        };
    }

    public MathExpressionBean(Function function, OutputContextBean<Double> parent) {
        if (log.isDebugEnabled()) {
            log.debug("Created bean from \"" + function.getClass().getName() + "\" function.");
        }

        checkArgument(function != null, "Expected not null function.");
        checkArgument(parent != null, "Expected not null parent.");

        this.parent = Optional.of(parent);
        this.function = function;
    }

    public Optional<OutputContextBean> getParent() {
        return parent;
    }

    @Override
    public Double getResultValue() throws CalculationException {
        if (log.isDebugEnabled()) {
            if (parent.isPresent()) {
                log.debug("Popping calculation result...");
            } else {
                log.debug("Popping function result...");
            }
        }

        while (!operatorsStack.isEmpty()) {
            popTopOperator();
        }

        if (function.getMinArgsNumber() > numbersStack.size()) {
            throw new CalculationException("Function cannot be resolved.", 0);
        }

        return (function.execute(numbersStack.toArray(new Double[0])));
    }

    /**
     * Add operand to stack.
     *
     * @param operand Operand to be pushed to this bean.
     */
    public void pushOperand(Double operand) {
        checkArgument(operand != null, "Expected not null operand.");

        numbersStack.push(operand);
    }

    /**
     * Add operator to stack. Calculates operators with higher priority.
     *
     * @param operator Operator to be pushed to this bean.
     */
    public void pushOperator(BinaryOperator operator) {
        checkArgument(operator != null, "Expected not null operator.");

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
}

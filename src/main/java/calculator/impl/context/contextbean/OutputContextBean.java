package calculator.impl.context.contextbean;

import calculator.impl.tokens.BinaryOperator;

/**
 * Abstract container for output context data.
 * @param <Operand> operand type
 */
public interface OutputContextBean<Operand> {

    /**
     * @return Parent bean
     */
    OutputContextBean getParent();

    /**
     * Add operand.
     * @param operand Operand to be pushed to this bean.
     */
    void pushOperand(Operand operand);

    /**
     * Add operator.
     * @param operator Operator to be pushed to this bean.
     */
    void pushOperator(BinaryOperator operator);

    boolean isInFunction();
}

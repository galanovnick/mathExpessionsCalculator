package calculator.impl.context.contextbean;

import calculator.CalculationException;
import calculator.impl.operators.BinaryOperator;

import java.util.Optional;

/**
 * Abstract container for output context data.
 * @param <Operand> operand type
 */
public interface OutputContextBean<Operand> {

    /**
     * @return Parent bean
     */
    Optional<OutputContextBean> getParent();

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

    Operand getResultValue() throws CalculationException;
}

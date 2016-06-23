package calculator.impl.tokens;

import calculator.impl.tokens.priorityenum.Priority;

/**
 * Abstract binary operator representation.
 */
public interface BinaryOperator extends Comparable<BinaryOperator>{

    /**
     * Returns calculated value.
     * @param leftOperand - expression left operand
     * @param rightOperand - expression right operand
     * @return calculated value
     */
    double execute(double leftOperand, double rightOperand);

    Priority getPriority();
}

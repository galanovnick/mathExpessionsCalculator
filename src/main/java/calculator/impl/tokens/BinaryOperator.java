package calculator.impl.tokens;

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
}

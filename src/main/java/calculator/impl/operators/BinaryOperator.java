package calculator.impl.operators;

/**
 * Abstract binary operator representation.
 */
public interface BinaryOperator<NumberType extends Number> extends Comparable<BinaryOperator>{

    /**
     * Returns calculated value.
     * @param leftOperand - expression left operand
     * @param rightOperand - expression right operand
     * @return calculated value
     */
    NumberType execute(NumberType leftOperand, NumberType rightOperand);

    Priority getPriority();

    @Override
    int compareTo(BinaryOperator o);
}

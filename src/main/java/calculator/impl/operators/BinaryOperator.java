package calculator.impl.operators;

/**
 * Abstract binary operator representation.
 */
public interface BinaryOperator<Operand> extends Comparable<BinaryOperator>{

    /**
     * Returns calculated value.
     * @param leftOperand - expression left operand
     * @param rightOperand - expression right operand
     * @return calculated value
     */
    Operand execute(Operand leftOperand, Operand rightOperand);

    Priority getPriority();

    @Override
    int compareTo(BinaryOperator o);
}

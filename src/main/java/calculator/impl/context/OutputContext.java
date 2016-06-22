package calculator.impl.context;

public interface OutputContext<Operand, BinaryOperator> {

    void pushOperand(Operand operator);

    void pushBinaryOperator(BinaryOperator operand);

    Operand getResult();
}

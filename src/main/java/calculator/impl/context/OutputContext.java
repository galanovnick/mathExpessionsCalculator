package calculator.impl.context;

import calculator.impl.tokens.Function;

public interface OutputContext<Operand, BinaryOperator> {

    void pushOperand(Operand operator);

    void pushBinaryOperator(BinaryOperator operand);

    void pushFunction(Function function);

    Operand getResult();
}

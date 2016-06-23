package calculator.impl.context;

import calculator.exception.CalculationException;
import calculator.impl.tokens.BinaryOperator;
import calculator.impl.tokens.Function;

public interface OutputContext<Operand> {

    void pushOperand(Operand operator);

    void pushBinaryOperator(BinaryOperator operand);

    void pushFunction(Function function);

    void popTopFunction() throws CalculationException;

    Operand getResult();
}

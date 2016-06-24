package calculator.impl;

import calculator.impl.context.contextbean.OutputContextBean;
import calculator.impl.operators.BinaryOperator;
import calculator.impl.operators.Function;

public interface OutputContext<Operand, ResolvingError extends Exception> {

    void pushOperand(Operand operator);

    void pushBinaryOperator(BinaryOperator operand);

    void pushFunction(Function function);

    void popTopFunction() throws ResolvingError;

    OutputContextBean getContextBean();

    Operand getResult();


}

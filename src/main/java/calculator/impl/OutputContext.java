package calculator.impl;

import calculator.CalculationException;
import calculator.impl.context.contextbean.OutputContextBean;
import calculator.impl.operators.BinaryOperator;
import calculator.impl.operators.Function;

public interface OutputContext<Operand> {

    void pushOperand(Operand operator);

    void pushBinaryOperator(BinaryOperator operand);

    void pushFunction(Function function);

    void popTopFunction() throws CalculationException;

    OutputContextBean getContextBean();

    Operand getResult() throws CalculationException;


}

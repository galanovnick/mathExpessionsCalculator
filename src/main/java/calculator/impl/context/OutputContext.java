package calculator.impl.context;

import calculator.impl.context.contextbean.OutputContextBean;
import calculator.impl.tokens.BinaryOperator;
import calculator.impl.tokens.Function;

public interface OutputContext<Operand> {

    void pushOperand(Operand operator);

    void pushBinaryOperator(BinaryOperator operand);

    void pushFunction(Function function);

    void popTopFunction();

    OutputContextBean getContextBean();

    Operand getResult();


}

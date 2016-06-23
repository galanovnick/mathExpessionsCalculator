package calculator.impl.context.contextbean;

import calculator.impl.tokens.BinaryOperator;

public interface OutputContextBean<Operand> {

    OutputContextBean getParent();

    void pushOperand(Operand operand);

    void pushOperator(BinaryOperator operator);

    boolean isInFunction();
}

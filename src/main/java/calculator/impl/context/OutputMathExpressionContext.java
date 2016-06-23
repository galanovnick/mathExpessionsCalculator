package calculator.impl.context;

import calculator.impl.context.contextbean.MathExpressionBean;
import calculator.impl.context.contextbean.OutputContextBean;
import calculator.impl.tokens.BinaryOperator;
import calculator.impl.tokens.Function;

public class OutputMathExpressionContext implements OutputContext<Double>{

    private MathExpressionBean mathExpressionBean
            = new MathExpressionBean();

    @Override
    public void pushOperand(Double operand) {
        mathExpressionBean.pushOperand(operand);
    }

    @Override
    public void pushBinaryOperator(BinaryOperator operator) {
        mathExpressionBean.pushOperator(operator);
    }

    @Override
    public void pushFunction(Function function) {
        mathExpressionBean = new MathExpressionBean(function, mathExpressionBean);
    }

    public Double getResult() {
        while (mathExpressionBean.getParent() != null) {
            popTopFunction();
        }
        return mathExpressionBean.getResultValue();
    }

    @Override
    public void popTopFunction(){
        if (mathExpressionBean.getParent() != null) {
            MathExpressionBean current = mathExpressionBean;
            mathExpressionBean = mathExpressionBean.getParent();
            mathExpressionBean.pushOperand(current.getResultValue());
        }
    }

    @Override
    public OutputContextBean getContextBean() {
        return mathExpressionBean;
    }
}

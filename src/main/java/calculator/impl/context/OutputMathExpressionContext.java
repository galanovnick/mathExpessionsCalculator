package calculator.impl.context;

import calculator.CalculationException;
import calculator.impl.OutputContext;
import calculator.impl.context.contextbean.MathExpressionBean;
import calculator.impl.context.contextbean.OutputContextBean;
import calculator.impl.operators.BinaryOperator;
import calculator.impl.operators.Function;

public class OutputMathExpressionContext implements OutputContext<Double> {

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

    public Double getResult() throws CalculationException{
        return mathExpressionBean.getResultValue();
    }

    @Override
    public void popTopFunction() throws CalculationException {
        if (mathExpressionBean.getParent() != null) {
            MathExpressionBean current = mathExpressionBean;
            mathExpressionBean = mathExpressionBean.getParent();
            mathExpressionBean.pushOperand(current.getResultValue());
        } else {
            throw new CalculationException("\"(\" expected.", 0);
        }
    }

    @Override
    public OutputContextBean getContextBean() {
        return mathExpressionBean;
    }
}

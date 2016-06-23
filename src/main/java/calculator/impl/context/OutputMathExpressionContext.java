package calculator.impl.context;

import calculator.exception.CalculationException;
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
            MathExpressionBean current = mathExpressionBean;
            mathExpressionBean = mathExpressionBean.getParent();
            mathExpressionBean.pushOperand(current.getResultValue());
        }
        return mathExpressionBean.getResultValue();
    }

    @Override
    public void popTopFunction() throws CalculationException {
        if (mathExpressionBean.getParent() != null) {
            MathExpressionBean current = mathExpressionBean;
            mathExpressionBean = mathExpressionBean.getParent();
            mathExpressionBean.pushOperand(current.getResultValue());
        }
        throw new CalculationException("\"(\" expected", 0);
    }
}

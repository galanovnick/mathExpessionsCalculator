package calculator;

import calculator.exception.CalculationException;

public interface MathExpressionsCalculator {

    double evaluate(String mathExpression) throws CalculationException;
}

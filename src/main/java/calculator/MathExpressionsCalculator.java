package calculator;

import calculator.exception.CalculationException;

/**
 * Public API of mathematical expressions calculator.
 */
public interface MathExpressionsCalculator {

    /**
     * Evaluates input math expression.
     * @param mathExpression expression represented by character string
     * @return evaluated floating number
     * @throws CalculationException signals about illegal syntax of input string
     */
    double evaluate(String mathExpression) throws CalculationException;
}

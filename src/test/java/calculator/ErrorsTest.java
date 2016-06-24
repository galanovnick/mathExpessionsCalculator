package calculator;

import calculator.impl.MathExpressionsCalculatorImpl;
import org.junit.Test;

public class ErrorsTest {

    private final MathExpressionsCalculator calculator = new MathExpressionsCalculatorImpl();

    @Test(expected = CalculationException.class)
    public void testInvalidCharacter() throws CalculationException {
        calculator.evaluate("|");
    }

    @Test(expected = CalculationException.class)
    public void testEmptyString() throws CalculationException {
        calculator.evaluate(null);
    }

    @Test(expected = CalculationException.class)
    public void testOpenBracketError() throws CalculationException {
        calculator.evaluate("(1+3");
    }

    @Test(expected = CalculationException.class)
    public void testCloseBracketError() throws CalculationException {
        calculator.evaluate("1+3)");
    }
}

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
        calculator.evaluate("");
        calculator.evaluate(null);
    }
}

package calculator;

import calculator.impl.MathExpressionsCalculatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErrorsTest {

    private final MathExpressionsCalculator calculator = new MathExpressionsCalculatorImpl();

    @Test(expected = CalculationException.class)
    public void testInvalidCharacter() throws CalculationException {
        calculator.evaluate("|");
    }

    @Test
    public void testInvalidCharacterIndex() throws CalculationException {
        try {
            calculator.evaluate("|");
        } catch (CalculationException e) {
            assertEquals("Error position is incorrect.", 1, e.getErrorPosition());
        }
    }

    @Test(expected = CalculationException.class)
    public void testEmptyString() throws CalculationException {
        calculator.evaluate("");
        calculator.evaluate(null);
    }
}

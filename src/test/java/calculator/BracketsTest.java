package calculator;

import calculator.exception.CalculationException;
import calculator.impl.MathExpressionsCalculatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BracketsTest {

    MathExpressionsCalculator calculator = new MathExpressionsCalculatorImpl();

    @Test
    public void testBrackets() throws CalculationException {
        assertEquals("Brackets have been calculated incorrect.",
                8, calculator.evaluate("(2+2)*2"), 0.0001);
    }

    @Test
    public void testBracketsAttachment() throws CalculationException {
        assertEquals("Brackets have been calculated incorrect.",
                4, calculator.evaluate("2*(3+5^(5-4))/4"), 0.0001);
    }
}

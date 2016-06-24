package calculator;

import calculator.impl.MathExpressionsCalculatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionsTest {

    MathExpressionsCalculator calculator = new MathExpressionsCalculatorImpl();

    @Test
    public void testMinimumFunction() throws CalculationException {
        assertEquals("Min function have been calculated incorrect.",
                2, calculator.evaluate("min(2,7,3)"), 0.0001);
    }

    @Test
    public void testMaximumFunction() throws CalculationException {
        assertEquals("Max function have been calculated incorrect.",
                9, calculator.evaluate("max(9,7,8)"), 0.0001);
    }
}

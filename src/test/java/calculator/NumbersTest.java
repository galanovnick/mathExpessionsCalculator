package calculator;

import calculator.impl.MathExpressionsCalculatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumbersTest {

    private final MathExpressionsCalculator calculator
            = new MathExpressionsCalculatorImpl();

    @Test
    public void testPositiveNumber() throws CalculationException {

        assertEquals("Positive number has been calculated incorrect.", 2,
                calculator.evaluate("2"), 0.0001);
    }

    @Test
    public void testNegativeNumber() throws CalculationException {

        assertEquals("Positive number has been calculated incorrect.", -2,
                calculator.evaluate("-2"), 0.0001);
    }

    @Test
    public void testFloatNumber() throws CalculationException {

        assertEquals("Floating number has been calculated incorrect.", 1.234,
                calculator.evaluate("1.234"), 0.0001);
    }
}

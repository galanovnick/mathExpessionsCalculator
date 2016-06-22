package calculator;

import calculator.exception.CalculationException;
import calculator.impl.MathExpressionsCalculatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumbersTest {

    private final MathExpressionsCalculator calculator
            = new MathExpressionsCalculatorImpl();

    @Test
    public void testNumbers() throws CalculationException {

        assertEquals("Number has been calculated incorrect.", 2, calculator.evaluate("2"), 0.0001);
    }
}

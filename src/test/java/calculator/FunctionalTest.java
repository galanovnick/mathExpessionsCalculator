package calculator;

import calculator.impl.MathExpressionsCalculatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionalTest {

    MathExpressionsCalculator calculator = new MathExpressionsCalculatorImpl();

    @Test
    public void testFunctionality() throws CalculationException {
        String mathExpression = "((3 + 2) * (max(min(2^0.5, 2^(5+6), 2^7*8), 6, 3) - 4) + 2) * 4";


        assertEquals("Expression has been calculated incorrect.",
                48, calculator.evaluate(mathExpression), 0.0001);

    }
}

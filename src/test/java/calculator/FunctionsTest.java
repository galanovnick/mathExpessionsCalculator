package calculator;

import calculator.impl.MathExpressionsCalculatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionsTest {

    MathExpressionsCalculator calculator = new MathExpressionsCalculatorImpl();

    @Test
    public void testBracketsFunction() throws CalculationException {
        assertEquals("Brackets function have been calculated incorrect.",
                8, calculator.evaluate("(2+2)*2"), 0.0001);
    }

    @Test
    public void testBracketsNesting() throws CalculationException {
        assertEquals("Brackets nesting have been resolved incorrect.",
                8, calculator.evaluate("((2+(1+(1+0)))*2)+0"), 0.0001);
    }

    @Test(expected = CalculationException.class)
    public void testBracketsArgumentsProblem() throws CalculationException {
        calculator.evaluate("()");
    }

    @Test
    public void testMinimumFunction() throws CalculationException {
        assertEquals("Min function have been calculated incorrect.",
                2, calculator.evaluate("min(2,7,3)"), 0.0001);
    }

    @Test
    public void testMinimumFunctionNesting() throws CalculationException {
        assertEquals("Min function nesting have been resolved incorrect.",
                1, calculator.evaluate("min(min(min(1,2,3),2,3),7,3)"), 0.0001);
    }

    @Test(expected = CalculationException.class)
    public void testMinFunctionArgumentsProblem() throws CalculationException {
        calculator.evaluate("min(1)");
    }

    @Test
    public void testMaximumFunction() throws CalculationException {
        assertEquals("Max function have been calculated incorrect.",
                9, calculator.evaluate("max(9,7,8)"), 0.0001);
    }

    @Test
    public void testMaximumFunctionNesting() throws CalculationException {
        assertEquals("Max function nesting have been resolved incorrect.",
                9, calculator.evaluate("max(max(max(9,8,7),8,7),7,3)"), 0.0001);
    }

    @Test(expected = CalculationException.class)
    public void testMaxFunctionArgumentsProblem() throws CalculationException {
        calculator.evaluate("min(1)");
    }
}

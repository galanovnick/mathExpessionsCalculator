package calculator.impl;

import calculator.MathExpressionsCalculator;
import calculator.exception.CalculationException;
import calculator.impl.abstractstatemachine.AbstractCharacterExpressionResolver;
import calculator.impl.context.InputContext;
import calculator.impl.context.InputMathExpressionContext;
import calculator.impl.context.OutputMathExpressionContext;
import calculator.impl.parser.*;
import calculator.impl.stateenum.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import static calculator.impl.stateenum.State.*;

public class MathExpressionsCalculatorImpl
        extends AbstractCharacterExpressionResolver<CalculationException, State>
        implements MathExpressionsCalculator {

    private final static Logger log = LoggerFactory.getLogger(MathExpressionsCalculator.class);

    public MathExpressionsCalculatorImpl() {
        super(registerTransitions(), registerParsers());
    }

    public double evaluate(String mathExpression) throws CalculationException {
        InputMathExpressionContext inputContext =
                new InputMathExpressionContext(mathExpression);
        OutputMathExpressionContext outputContext =
                new OutputMathExpressionContext();

        run(inputContext, outputContext, START, FINISH);

        return outputContext.getResult();
    }

    @Override
    public void deadlock(InputContext inputContext) throws CalculationException {
        if (log.isWarnEnabled()) {
            log.warn("Input expression is invalid. Symbol at "
                    + (inputContext.getParsingPointer() + 1) + " position unresolved.");
        }


        throw new CalculationException("Cannot resolve symbol at "
                + (inputContext.getParsingPointer() + 1) + " position", inputContext.getParsingPointer());
    }

    private static Map<State, EnumSet<State>> registerTransitions() {
        return new HashMap<State, EnumSet<State>>() {{

            put(START, EnumSet.of(NUMBER, FUNCTION));
            put(NUMBER, EnumSet.of(BINARY_OPERATOR, FINISH));
            put(FUNCTION, EnumSet.of(NUMBER, FUNCTION));
            put(BINARY_OPERATOR, EnumSet.of(NUMBER));
        }};
    }

    private static HashMap<State, ExpressionParser> registerParsers() {
        return new HashMap<State, ExpressionParser>() {{

            put(NUMBER, new NumberParser());
            put(BINARY_OPERATOR, new BinaryOperatorParser());
            put(FUNCTION, new FunctionParser());
            put(FINISH, new FinishParser());
        }};
    }
}

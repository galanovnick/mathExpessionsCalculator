package calculator.impl;

import calculator.MathExpressionsCalculator;
import calculator.exception.CalculationException;
import calculator.impl.abstractstatemachine.AbstractCharacterExpressionResolver;
import calculator.impl.context.InputContext;
import calculator.impl.context.InputMathExpressionContext;
import calculator.impl.context.OutputContext;
import calculator.impl.context.OutputMathExpressionContext;
import calculator.impl.parser.ExpressionParser;
import calculator.impl.parser.ExpressionParsersContainer;
import calculator.impl.parser.FinishParser;
import calculator.impl.parser.NumberParser;
import calculator.impl.stateenum.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
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
                    + inputContext.getPointer() + " position unresolved.");
        }


        throw new CalculationException("Cannot resolve symbol at "
                + inputContext.getPointer() + "position", inputContext.getPointer());
    }

    private static Map<State, EnumSet<State>> registerTransitions() {
        return new HashMap<State, EnumSet<State>>() {{

            put(START, EnumSet.of(NUMBER));
            put(NUMBER, EnumSet.of(FINISH));
        }};
    }

    private static HashMap<State, ExpressionParser> registerParsers() {
        return new HashMap<State, ExpressionParser>() {{

            put(NUMBER, new NumberParser());
            put(FINISH, new FinishParser());
        }};
    }
}

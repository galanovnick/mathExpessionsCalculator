package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.stackcommands.StackCommand;

/**
 * Implements parsing for "finish" state.
 */
public class FinishParser implements ExpressionParser {

    /**
     * Do nothing. Returns empty function.
     * @param inputContext
     * @param outputContext
     * @return empty function
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {
        return () -> {};
    }
}
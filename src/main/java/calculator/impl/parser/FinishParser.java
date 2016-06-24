package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.context.ParsingContent;
import calculator.impl.stackcommands.StackCommand;

/**
 * Implements parsing for "finish" state.
 */
public class FinishParser implements ExpressionParser {

    /**
     * Returns empty function.
     * @param inputContext - input data container
     * @return empty function
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {
        if (outputContext.getContextBean().isInFunction()) {
            return null;
        }

        ParsingContent content = inputContext.getParsingContent();

        if (content.getParsingPointer() < content.getTokens().length) {
            return null;
        }

        return () -> {};
    }
}

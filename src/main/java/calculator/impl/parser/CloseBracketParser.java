package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.context.ParsingContent;
import calculator.impl.stackcommands.StackCommand;

/**
 * Implements parsing for "close_bracket" state.
 */
public class CloseBracketParser implements ExpressionParser {

    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {
        if (!outputContext.getContextBean().isInFunction()) {
            return null;
        }

        ParsingContent content = inputContext.getParsingContent();

        char[] tokens = content.getTokens();
        int pointer = content.getParsingPointer();

        if (pointer >= tokens.length) {
            return null;
        } else if (tokens[pointer] != ')') {
            return null;
        }

        content.moveParsingPointer(1);
        return outputContext::popTopFunction;
    }
}

package calculator.impl.parser;

import calculator.impl.InputContext;
import calculator.impl.OutputContext;
import calculator.impl.ParsingContent;
import calculator.impl.abstractstatemachine.StackCommand;

/**
 * Implements parsing for "close_bracket" state.
 */
public class CloseBracketParser implements ExpressionParser {

    @Override
    public StackCommand parseExpression(InputContext inputContext) {

        ParsingContent content = inputContext.getParsingContent();

        char[] tokens = content.getTokens();
        int pointer = content.getParsingPointer();

        if (pointer >= tokens.length) {
            return null;
        } else if (tokens[pointer] != ')') {
            return null;
        }

        content.moveParsingPointer(1);
        return OutputContext::popTopFunction;
    }
}

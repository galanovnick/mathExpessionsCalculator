package calculator.impl.parser;

import calculator.impl.InputContext;
import calculator.impl.ParsingContent;
import calculator.impl.abstractstatemachine.StackCommand;

/**
 * Implements parsing for "arguments_separator" state.
 */
public class ArgumentsSeparatorParser implements ExpressionParser {

    /**
     * Returns null if no argument separator found.
     * @param inputContext
     * @return empty function or null
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext) {

        ParsingContent content = inputContext.getParsingContent();

        char[] tokens = content.getTokens();
        int pointer = content.getParsingPointer();

        if (pointer >= tokens.length) {
            return null;
        } else if (tokens[pointer] != ',') {
            return null;
        }

        content.moveParsingPointer(1);
        return (outputContext) -> {};
    }
}

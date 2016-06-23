package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.stackcommands.StackCommand;

/**
 * Implements parsing for "arguments_separator" state.
 */
public class ArgumentsSeparatorParser implements ExpressionParser {

    /**
     * Returns null if no argument separator found.
     * @param inputContext
     * @param outputContext
     * @return empty function or null
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {

        char[] tokens = inputContext.getTokens();
        int pointer = inputContext.getParsingPointer();

        if (pointer >= tokens.length) {
            return null;
        } else if (tokens[pointer] != ',') {
            return null;
        }

        inputContext.moveParsingPointer(1);
        return () -> {};
    }
}

package calculator.impl.parser;

import calculator.impl.InputContext;
import calculator.impl.ParsingContent;
import calculator.impl.abstractstatemachine.StackCommand;

/**
 * Implements parsing for "number" state.
 */
public class NumberParser implements ExpressionParser {

    /**
     * Returns function that pushing number into specified stack.
     * If no number parsed, returns null.
     *
     * @param inputContext
     * @return function or null
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext) {

        ParsingContent content = inputContext.getParsingContent();

        char[] tokens = content.getTokens();
        int pointer = content.getParsingPointer();

        char nextChar;
        StringBuilder numberTokens = new StringBuilder();

        while (pointer < tokens.length) {
            nextChar = tokens[pointer++];
            if (numberTokens.length() == 0 && nextChar == '-') {
                numberTokens.append(nextChar);
            } else if (Character.isDigit(nextChar) || nextChar == '.') {
                numberTokens.append(nextChar);
            } else {
                break;
            }
        }
        if (numberTokens.length() == 0 || (numberTokens.indexOf(".") != numberTokens.lastIndexOf("."))) {
            return null;
        }

        content.moveParsingPointer(numberTokens.length());

        return (outputContext) -> outputContext.pushOperand(
                Double.parseDouble(numberTokens.toString()));
    }
}

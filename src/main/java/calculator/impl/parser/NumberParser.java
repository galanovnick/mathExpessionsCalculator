package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.stackcommands.StackCommand;

/**
 * Implements parsing for "number" state.
 */
public class NumberParser implements ExpressionParser {

    /**
     * Returns function that pushing number into specified stack.
     * If no number parsed, returns null.
     *
     * @param inputContext
     * @param outputContext
     * @return function or null
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {

        char[] tokens = inputContext.getTokens();
        int pointer = inputContext.getParsingPointer();

        char nextChar;
        StringBuilder numberTokens = new StringBuilder();

        while (pointer < tokens.length) {
            if (numberTokens.length() == 0 && tokens[pointer] == ',') {
                pointer++;
            } else {
                nextChar = tokens[pointer++];
                if (numberTokens.length() == 0 && nextChar == '-') {
                    numberTokens.append(nextChar);
                } else if (Character.isDigit(nextChar) || nextChar == '.') {
                    numberTokens.append(nextChar);
                } else {
                    break;
                }
            }
        }
        if (numberTokens.length() == 0 || (numberTokens.indexOf(".") != numberTokens.lastIndexOf("."))) {
            return null;
        }

        inputContext.moveParsingPointer(numberTokens.length());

        return () -> outputContext.pushOperand(Double.parseDouble(numberTokens.toString()));
    }
}

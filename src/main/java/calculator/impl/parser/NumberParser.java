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
     * @param inputContext
     * @param outputContext
     * @return function or null
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {

        char[] tokens = inputContext.getTokens();
        int pointer = inputContext.getParsingPointer();

        char nextChar;
        StringBuilder number = new StringBuilder();

        while (pointer < tokens.length) {
            nextChar = tokens[pointer++];
            if (nextChar == '-' && number.length() == 0) {
                number.append(nextChar);
            } else if (Character.isDigit(nextChar) || nextChar == '.') {
                number.append(nextChar);
            } else {
                break;
            }
        }
        if (number.length() == 0 || (number.indexOf(".") != number.lastIndexOf("."))) {
            return null;
        }

        inputContext.moveParsingPointer(number.length());

        return () -> outputContext.pushOperand(Double.parseDouble(number.toString()));
    }
}

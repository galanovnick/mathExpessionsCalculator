package calculator.impl.parser;

import calculator.exception.CalculationException;
import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
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

        char[] tokens = inputContext.getTokens();
        int pointer = inputContext.getParsingPointer();

        if (pointer >= tokens.length) {
            return null;
        } else if (tokens[pointer] != ')') {
            return null;
        }

        inputContext.moveParsingPointer(1);
        return outputContext::popTopFunction;
    }
}

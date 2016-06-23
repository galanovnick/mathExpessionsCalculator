package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.stackcommands.StackCommand;

/**
 * Implements parsing for "close_bracket" state.
 */
public class CloseBracketParser implements ExpressionParser {

    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {

        if (inputContext.getTokens()[inputContext.getParsingPointer()] == ')') {
            inputContext.moveParsingPointer(1);
            return () -> outputContext.popTopFunction();
        }

        return null;
    }
}

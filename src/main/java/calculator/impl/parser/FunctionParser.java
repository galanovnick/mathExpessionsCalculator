package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.stackcommands.StackCommand;
import calculator.impl.tokens.BinaryOperator;
import calculator.impl.tokens.BinaryOperatorsFactory;
import calculator.impl.tokens.Function;
import calculator.impl.tokens.FunctionsFactory;

/**
 * Implements parsing for "function" state.
 */
public class FunctionParser implements ExpressionParser {

    private final FunctionsFactory functionsFactory = new FunctionsFactory();

    /**
     * Returns generated function.
     * If no function parsed, returns null.
     * @param inputContext
     * @param outputContext
     * @return generated function
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {
        char[] tokens = inputContext.getTokens();
        int pointer = inputContext.getParsingPointer();

        StringBuilder functionTokens = new StringBuilder();
        char functionToken;

        while (pointer < tokens.length) {
            functionToken = tokens[pointer++];

            if (!Character.isDigit(functionToken) &&
                    functionToken != '-' &&
                    functionToken != '.' &&
                    functionToken != ')' &&
                    !VALID_OPERATOR_SYMBOLS.contains(functionToken)) {

                functionTokens.append(functionToken + "");

            } else {
                break;
            }
        }

        Function function = functionsFactory.createFunction(functionTokens.toString());

        if (function != null) {
            inputContext.moveParsingPointer(functionTokens.length());
            return () -> outputContext.pushFunction(function);
        }

        return null;
    }
}

package calculator.impl.parser;

import calculator.impl.InputContext;
import calculator.impl.ParsingContent;
import calculator.impl.abstractstatemachine.StackCommand;
import calculator.impl.operators.Function;
import calculator.impl.operators.FunctionsFactory;

/**
 * Implements parsing for "function" state.
 */
public class FunctionParser implements ExpressionParser {

    private final FunctionsFactory functionsFactory = new FunctionsFactory();

    /**
     * Returns generated function.
     * If no function parsed, returns null.
     * @param inputContext Input context
     * @return generated function
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext) {

        ParsingContent content = inputContext.getParsingContent();

        char[] tokens = content.getTokens();
        int pointer = content.getParsingPointer();

        StringBuilder functionTokens = new StringBuilder();
        char functionToken;

        while (pointer < tokens.length) {
            functionToken = tokens[pointer++];
            if (functionToken == '(') {
                functionTokens.append(functionToken);
                break;
            }
            if (!Character.isDigit(functionToken) &&
                    functionToken != '-' &&
                    functionToken != '.' &&
                    functionToken != ')' &&
                    !VALID_OPERATOR_SYMBOLS.contains(functionToken)) {

                functionTokens.append(functionToken);

            } else {
                break;
            }
        }

        Function function = functionsFactory.createFunction(functionTokens.toString());

        if (function != null) {
            content.moveParsingPointer(functionTokens.length());
            return (outputContext) -> outputContext.pushFunction(function);
        }

        return null;
    }
}

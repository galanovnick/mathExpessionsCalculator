package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.context.ParsingContent;
import calculator.impl.stackcommands.StackCommand;
import calculator.impl.tokens.BinaryOperator;
import calculator.impl.tokens.BinaryOperatorsFactory;

/**
 * Implements parsing for "binary_operator" state.
 */
public class BinaryOperatorParser implements ExpressionParser {

    private final BinaryOperatorsFactory binaryOperatorsFactory
            = new BinaryOperatorsFactory();

    /**
     * Returns function that pushing binary operator into specified stack.
     * If no operator parsed, returns null.
     * @param inputContext
     * @return function or null
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {

        ParsingContent content = inputContext.getParsingContent();

        char[] tokens = content.getTokens();
        int pointer = content.getParsingPointer();

        StringBuilder operator = new StringBuilder();
        char operatorToken;

        while (pointer < tokens.length) {

            operatorToken = tokens[pointer++];
            if (VALID_OPERATOR_SYMBOLS.contains(operatorToken)) {
                operator.append(operatorToken);
            } else {
                break;
            }
        }

        BinaryOperator binaryOperator =
                binaryOperatorsFactory.createOperator(operator.toString());

        if (binaryOperator != null) {
            content.moveParsingPointer(1);
            return () -> outputContext.pushBinaryOperator(binaryOperator);
        }

        return null;
    }
}

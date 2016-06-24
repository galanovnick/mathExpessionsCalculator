package calculator.impl.parser;

import calculator.impl.InputContext;
import calculator.impl.ParsingContent;
import calculator.impl.abstractstatemachine.StackCommand;
import calculator.impl.operators.BinaryOperator;
import calculator.impl.operators.BinaryOperatorsFactory;

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
    public StackCommand parseExpression(InputContext inputContext) {

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
            return (outputContext) -> outputContext.pushBinaryOperator(binaryOperator);
        }

        return null;
    }
}

package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
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
     * @param outputContext
     * @return function or null
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext, OutputContext outputContext) {
        char[] tokens = inputContext.getTokens();
        int pointer = inputContext.getParsingPointer();

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
            inputContext.moveParsingPointer(1);
            return () -> outputContext.pushBinaryOperator(binaryOperator);
        }

        return null;
    }
}

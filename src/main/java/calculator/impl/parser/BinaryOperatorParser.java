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

        if (pointer < tokens.length) {

            char operandToken = tokens[pointer];

            BinaryOperator operator = binaryOperatorsFactory.createOperator(operandToken + "");

            if (operator != null) {
                inputContext.moveParsingPointer(1);
                return () -> outputContext.pushBinaryOperator(operator);
            }
        }
        return null;
    }
}

package calculator.impl.parser;

import calculator.CalculationException;
import calculator.impl.InputContext;
import calculator.impl.ParsingContent;
import calculator.impl.abstractstatemachine.StackCommand;
import calculator.impl.context.contextbean.OutputContextBean;

/**
 * Implements parsing for "finish" state.
 */
public class FinishParser implements ExpressionParser {

    /**
     * Returns empty function.
     * @param inputContext - input data container
     * @return empty function
     */
    @Override
    public StackCommand parseExpression(InputContext inputContext) {

        ParsingContent content = inputContext.getParsingContent();

        if (content.getParsingPointer() < content.getTokens().length) {
            return null;
        }

        return (outputContext) -> {
            OutputContextBean outputContextBean
                    = outputContext.getContextBean();
            if (outputContextBean.getParent() != null) {
                throw new CalculationException("\")\" expected.", content.getTokens().length);
            }
        };
    }
}

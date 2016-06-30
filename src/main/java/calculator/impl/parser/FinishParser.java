package calculator.impl.parser;

import calculator.CalculationException;
import calculator.impl.InputContext;
import calculator.impl.ParsingContent;
import calculator.impl.abstractstatemachine.StackCommand;
import calculator.impl.context.contextbean.OutputContextBean;

import java.util.Optional;

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
    public Optional<StackCommand> parseExpression(InputContext inputContext) {

        ParsingContent content = inputContext.getParsingContent();

        if (content.getParsingPointer() < content.getTokens().length) {
            return Optional.empty();
        }

        return Optional.of((outputContext) -> {
            OutputContextBean outputContextBean
                    = outputContext.getContextBean();
            if (outputContextBean.getParent().isPresent()) {
                throw new CalculationException("\")\" expected.", content.getTokens().length);
            }
        });
    }
}

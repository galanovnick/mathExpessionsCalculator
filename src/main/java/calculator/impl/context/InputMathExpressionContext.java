package calculator.impl.context;

import calculator.CalculationException;
import calculator.impl.InputContext;
import calculator.impl.ParsingContent;
import calculator.impl.parser.ExpressionParser;
import calculator.impl.parser.ExpressionParsersContainer;
import calculator.impl.abstractstatemachine.StackCommand;
import calculator.impl.abstractstatemachine.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Contains input data.
 */
public class InputMathExpressionContext implements InputContext<State> {

    private final static Logger log = LoggerFactory.getLogger(InputMathExpressionContext.class);

    /**
     * Contains parsing content
     */
    private final ParsingContent parsingContent;

    /**
     * Contains available parsers.
     */
    private final ExpressionParsersContainer<State> parsersContainer;

    public InputMathExpressionContext(String tokens,
                                      ExpressionParsersContainer parsersContainer)
            throws CalculationException {

        checkNotNull(parsersContainer, "Expected not null parsers container");

        if (tokens == null || tokens.length() == 0) {
            log.error("Input expression is empty or null.");
            throw new CalculationException("Expression cannot be empty.", 0);
        }
        if (log.isDebugEnabled()) {
            log.debug("Created math expression context from \""
                    + tokens.replaceAll("\\s+", "") + "\"");
        }

        parsingContent = new MathExpressionParsingContent(tokens);
        this.parsersContainer = parsersContainer;
    }

    /**
     * Returns stack command if any have been parsed.
     * @param state Current state
     * @return Stack command or null
     */
    @Override
    public Optional<StackCommand> grabActionByState(State state) {
        ExpressionParser parser =
                parsersContainer.getParserByState(state);
        Optional<StackCommand> stackCommand = parser.parseExpression(this);

        return stackCommand;
    }

    @Override
    public ParsingContent getParsingContent() {
        return parsingContent;
    }
}

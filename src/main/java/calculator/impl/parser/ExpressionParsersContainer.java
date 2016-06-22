package calculator.impl.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Contains all available parsers.
 * @param <State>
 */
public class ExpressionParsersContainer<State extends Enum<State>> {

    private final static Logger log = LoggerFactory.getLogger(ExpressionParsersContainer.class);

    private final Map<State, ExpressionParser> parsers;

    public ExpressionParsersContainer(Map<State, ExpressionParser> parsers) {
        this.parsers = parsers;
    }

    /**
     * Returns parser by specified state.
     * @param state
     * @return parser
     */
    public ExpressionParser getParserByState(State state) {

        return parsers.get(state);
    }
}

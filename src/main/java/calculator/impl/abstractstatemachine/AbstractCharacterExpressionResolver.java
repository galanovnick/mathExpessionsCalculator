package calculator.impl.abstractstatemachine;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.parser.ExpressionParser;
import calculator.impl.parser.ExpressionParsersContainer;
import calculator.impl.stackcommands.StackCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Implementation of finite state machine for resolving character expressions.
 *
 * @param <ResolvingError> exception that signal about syntax errors
 * @param <State> fsm state
 */
public abstract class AbstractCharacterExpressionResolver
        <ResolvingError extends Exception, State extends Enum<State>> {

    private final static Logger log = LoggerFactory.getLogger(AbstractCharacterExpressionResolver.class);

    /**
     * Available transitions. Users have to register them using constructor.
     * Key - state.
     * Value - set that contains available transitions from this state.
     */
    private Map<State, EnumSet<State>> transitionMatrix;

    /**
     * Contains all available parsers. Users have to register them using constructor.
     * Key - state.
     * Value - parser for current state
     */
    private final ExpressionParsersContainer<State> parsersContainer;

    protected AbstractCharacterExpressionResolver(Map<State, EnumSet<State>> transitionMatrix,
                                                  Map<State, ExpressionParser> parsersMap) {

        this.transitionMatrix = transitionMatrix;

        this.parsersContainer = new ExpressionParsersContainer<>(parsersMap);
    }

    /**
     * Running by using finite state machine algorithm.
     *
     * @param inputContext Contains input data
     * @param outputContext Contains output data
     * @param startState Start state
     * @param finishState Finish state
     * @throws ResolvingError
     */
    protected final void run(InputContext inputContext, OutputContext outputContext,
                    State startState, State finishState) throws ResolvingError {

        State currentState = startState;
        while (currentState != finishState) {

            Iterator<State> iterator = transitionMatrix.get(currentState).iterator();

            while (iterator.hasNext()) {
                State potentialState = iterator.next();
                if (log.isDebugEnabled()) {
                    log.debug("Potential state: " + potentialState.name());
                }
                if (acceptNextState(potentialState, inputContext, outputContext)) {
                    currentState = potentialState;
                    break;
                } else if (!iterator.hasNext()) {
                    deadlock(inputContext);
                }
            }
        }
    }

    /**
     * Accepts potential state if any stack commands parsed.
     * @param potentialState State to be checked.
     * @param inputContext Input context.
     * @param outputContext Output context.
     * @return true - if state accepted, false - if not.
     */
    private boolean acceptNextState(State potentialState,
                                    InputContext inputContext,
                                    OutputContext outputContext) {

        StackCommand stackCommand = parsersContainer.getParserByState(potentialState)
                .parseExpression(inputContext, outputContext);

        if (stackCommand != null) {
            stackCommand.execute();

            if (log.isDebugEnabled()) {
                log.debug("Accepted \"" + potentialState.name() + "\" state.");
            }
            return true;
        }
        return false;
    }

    /**
     * Method that invoked when machine cannot reach finish state.
     * @param inputContext Input context.
     * @throws ResolvingError
     */
    abstract public void deadlock(InputContext inputContext) throws ResolvingError;

}

package calculator.impl.abstractstatemachine;

import calculator.CalculationException;
import calculator.impl.InputContext;
import calculator.impl.OutputContext;
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
        <State extends Enum<State>> {

    private final static Logger log = LoggerFactory.getLogger(AbstractCharacterExpressionResolver.class);

    /**
     * Available transitions. Users have to register them using constructor.
     * Key - state.
     * Value - set that contains available transitions from this state.
     */
    private Map<State, EnumSet<State>> transitionMatrix;

    protected AbstractCharacterExpressionResolver(Map<State, EnumSet<State>> transitionMatrix) {

        this.transitionMatrix = transitionMatrix;
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
                    State startState, State finishState) throws CalculationException {

        State currentState = startState;
        while (currentState != finishState) {

            currentState = acceptNextState(currentState, inputContext, outputContext);

            if (currentState == null) {
                deadlock(inputContext.getParsingContent().getParsingPointer());
            }
        }
    }

    /**
     * Accepts potential state if any stack commands parsed.
     * @param currentState State to be checked.
     * @param inputContext Input context.
     * @param outputContext Output context.
     * @return true - if state accepted, false - if not.
     */
    private State acceptNextState(State currentState,
                                    InputContext inputContext,
                                    OutputContext outputContext)
            throws CalculationException {

        State nextState = null;

        for (State potentialState : transitionMatrix.get(currentState)) {
            StackCommand stackCommand =
                    inputContext.grabActionByState(potentialState);

            if (stackCommand != null) {
                stackCommand.execute(outputContext);
                nextState = potentialState;

                if (log.isDebugEnabled()) {
                    log.debug("Accepted \"" + potentialState.name() + "\" state.");
                }

                break;
            }

        }

        return nextState;
    }

    /**
     * Method that invoked when machine cannot reach finish state.
     * @throws ResolvingError
     */
    abstract public void deadlock(int deadlockPosition) throws CalculationException;

}

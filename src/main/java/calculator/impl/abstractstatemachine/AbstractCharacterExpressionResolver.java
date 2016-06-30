package calculator.impl.abstractstatemachine;

import calculator.CalculationException;
import calculator.impl.InputContext;
import calculator.impl.OutputContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Implementation of finite state machine for resolving character expressions.
 *
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
        checkNotNull(transitionMatrix, "Expected not null transitions matrix.");
        checkArgument(transitionMatrix.size() != 0, "Expected not empty transitions matrix.");

        this.transitionMatrix = transitionMatrix;
    }

    /**
     * Running by using finite state machine algorithm.
     *
     * @param inputContext Contains input data
     * @param outputContext Contains output data
     * @param startState Start state
     * @param finishState Finish state
     */
    protected final void run(InputContext inputContext, OutputContext outputContext,
                    State startState, State finishState) throws CalculationException {
        checkNotNull(inputContext, "Expected not null input context");
        checkNotNull(outputContext, "Expected not null output context");
        checkNotNull(startState, "Expected not null start state");
        checkNotNull(finishState, "Expected not null finish state");

        State currentState = startState;
        while (currentState != finishState) {

            Optional<State> nextState
                    = acceptNextState(currentState, inputContext, outputContext);

            if (nextState.isPresent()) {
                currentState = nextState.get();
            } else {
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
    private Optional<State> acceptNextState(State currentState,
                                    InputContext inputContext,
                                    OutputContext outputContext)
            throws CalculationException {

        Optional<State> nextState = Optional.empty();

        for (State potentialState : transitionMatrix.get(currentState)) {
            Optional<StackCommand> stackCommand =
                    inputContext.grabActionByState(potentialState);

            if (stackCommand.isPresent()) {
                stackCommand.get().execute(outputContext);
                nextState = Optional.of(potentialState);

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
     */
    abstract public void deadlock(int deadlockPosition) throws CalculationException;

}

package calculator.impl.abstractstatemachine;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;

/**
 * Public API for abstract finite state machine
 * @param <IllegalStateError> exception that signals about illegal state transition
 */
public interface FiniteStateMachine<IllegalStateError extends Exception> {

    /**
     * Run FSM by available states.
     * @param inputContext - input data
     * @param outputContext - output data
     * @throws IllegalStateError
     */
    void run(InputContext inputContext, OutputContext outputContext) throws IllegalStateError;

    /**
     * Throws exception in case of illegal state transition
     * @param inputContext - current input context
     * @throws IllegalStateError
     */
    void deadlock(InputContext inputContext) throws IllegalStateError;
}

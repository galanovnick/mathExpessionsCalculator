package calculator.impl;

import calculator.impl.abstractstatemachine.StackCommand;

/**
 *  Container for input data.
 */
public interface InputContext<State extends Enum<State>> {

    StackCommand grabActionByState(State state);

    ParsingContent getParsingContent();
}

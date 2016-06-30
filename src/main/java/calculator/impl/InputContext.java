package calculator.impl;

import calculator.impl.abstractstatemachine.StackCommand;

import java.util.Optional;

/**
 *  Container for input data.
 */
public interface InputContext<State extends Enum<State>> {

    Optional<StackCommand> grabActionByState(State state);

    ParsingContent getParsingContent();
}

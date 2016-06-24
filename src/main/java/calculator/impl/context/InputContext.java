package calculator.impl.context;

import calculator.impl.stackcommands.StackCommand;

/**
 *  Container for input data.
 */
public interface InputContext<State extends Enum<State>> {

    StackCommand grabActionByState(State state, OutputContext outputContext);

    ParsingContent getParsingContent();
}

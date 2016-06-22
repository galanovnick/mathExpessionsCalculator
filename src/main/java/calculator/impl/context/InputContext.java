package calculator.impl.context;

/**
 * Represents current FSM state. Contains input data.
 * @param <State>
 */
public interface InputContext<State extends Enum<State>> {

    char nextCharacter();
    boolean isParsed();
    void commitParsed();
    void resetParsed();
    int getPointer();

    boolean isInFinishState();
    State getCurrentState();
    void setCurrentState(State state);
}

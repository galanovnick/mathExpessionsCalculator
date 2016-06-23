package calculator.impl.stateenum;

/**
 * All reachable states for finite state machine, used as base for MathExpressionsCalculator.
 */
public enum State {
    START,
    NUMBER,
    BINARY_OPERATOR,
    FUNCTION,
    CLOSE_BRACKET,
    FINISH
}

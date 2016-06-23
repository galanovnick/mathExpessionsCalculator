package calculator.impl.context;

/**
 *  Container for input data.
 */
public interface InputContext {

    char[] getTokens();

    int getParsingPointer();

    void moveParsingPointer(int value);
}

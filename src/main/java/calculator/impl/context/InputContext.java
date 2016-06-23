package calculator.impl.context;

/**
 *
 *
 */
public interface InputContext {

    char[] getTokens();

    int getParsingPointer();

    void moveParsingPointer(int value);
}

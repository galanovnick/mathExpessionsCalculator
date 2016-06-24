package calculator.impl.context;


/**
 * Contains parsing content.
 */
public interface ParsingContent {

    /**
     * Returns input characters.
     * @return characters array
     */
    char[] getTokens();

    /**
     * Returns current parsing pointer position.
     * @return parsing poiner
     */
    int getParsingPointer();

    /**
     * Move current parsing pointer position.
     * @param value
     */
    void moveParsingPointer(int value);

}

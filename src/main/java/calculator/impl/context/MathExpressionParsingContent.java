package calculator.impl.context;


/**
 * Contains math expression parsing content
 */
public class MathExpressionParsingContent implements ParsingContent {

    private final char[] tokens;

    private int parsingPointer = 0;

    public MathExpressionParsingContent(String tokensString) {
        this.tokens = tokensString.replaceAll("\\s+","").toCharArray();
    }


    @Override
    public char[] getTokens() {
        return tokens;
    }

    @Override
    public int getParsingPointer() {
        return parsingPointer;
    }

    @Override
    public void moveParsingPointer(int value) {
        if ((parsingPointer + value) <= tokens.length) {
            parsingPointer += value;
        } else {
            throw new IllegalStateException("Pointer is out of bounds.");
        }
    }
}

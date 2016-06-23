package calculator.impl.context;

import calculator.exception.CalculationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains input data.
 */
public class InputMathExpressionContext implements InputContext {

    private final static Logger log = LoggerFactory.getLogger(InputMathExpressionContext.class);

    private final char[] inputTokens;
    
    private int parsingPointer = 0;

    public InputMathExpressionContext(String tokens) throws CalculationException {

        if (tokens == null || tokens.length() == 0) {
            log.error("Input expression is empty or null.");
            throw new CalculationException("Expression cannot be empty.", 0);
        }
        if (log.isDebugEnabled()) {
            log.debug("Created math expression context from \"" + tokens.replaceAll("\\s+","") + "\"");
        }
        this.inputTokens = tokens.replaceAll("\\s+","").toCharArray();
    }

    @Override
    public char[] getTokens() {
        return inputTokens;
    }

    @Override
    public int getParsingPointer() {
        return parsingPointer;
    }

    @Override
    public void moveParsingPointer(int value) {
        parsingPointer += value;
    }
}

package calculator.impl.context;

import calculator.exception.CalculationException;
import calculator.impl.stateenum.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputMathExpressionContext implements InputContext<State> {

    private final static Logger log = LoggerFactory.getLogger(InputMathExpressionContext.class);

    private final char[] inputTokens;

    private int pointer = 0;
    private int transactionPointer = 0;

    public InputMathExpressionContext(String tokens) throws CalculationException {

        if (tokens == null || tokens.length() == 0) {
            log.error("Input expression is empty or null.");
            throw new CalculationException("Expression cannot be empty.", 0);
        }
        if (log.isDebugEnabled()) {
            log.debug("Created math expression context from \"" + tokens + "\"");
        }
        this.inputTokens = tokens.toCharArray();
    }

    @Override
    public char nextCharacter() {
        if (pointer < inputTokens.length) {
            return inputTokens[pointer++];
        } else {
            throw new IndexOutOfBoundsException("Parsing pointer is out of bound.");
        }
    }

    @Override
    public boolean isParsed() {
        if (log.isDebugEnabled() && !(pointer < inputTokens.length)) {
            log.debug("Parsing done.");
        }
        return pointer >= inputTokens.length;
    }

    @Override
    public void commitParsed() {
        transactionPointer = pointer;
    }

    @Override
    public void resetParsed() {
        pointer = transactionPointer;
    }

    @Override
    public int getPointer() {
        return pointer;
    }
}

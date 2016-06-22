package calculator.exception;

/**
 * Signals that input string contains unresolved symbol.
 */
public class CalculationException extends Exception {
    /**
     * Unresolved symbol position.
     */
    private final int errorPosition;

    public CalculationException(String message, int errorPosition) {
        super(message);
        this.errorPosition = errorPosition;
    }

    public int getErrorPosition() {
        return errorPosition;
    }
}

package calculator.impl.tokens;

/**
 * Abstract function representation.
 */
public interface Function {

    /**
     * Returns calculated value.
     * @param args function arguments
     * @return calculated value
     */
    double execute(Double[] args);
}

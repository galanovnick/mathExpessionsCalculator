package calculator.impl.tokens;

/**
 * Abstract function representation.
 */
public interface Function<Operand> {

    /**
     * Returns calculated value.
     * @param args function arguments
     * @return calculated value
     */
    Operand execute(Operand[] args);
}

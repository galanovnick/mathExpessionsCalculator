package calculator.impl.operators;

/**
 * Abstract function representation.
 */
public interface Function<NumberType> {

    int getMinArgsNumber();

    /**
     * Returns calculated value.
     * @param args function arguments
     * @return calculated value
     */
    NumberType execute(NumberType[] args);
}

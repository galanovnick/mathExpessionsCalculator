package calculator.impl.operators;

/**
 * Implements binary operators comparison.
 */
public abstract class AbstractBinaryOperator<NumberType extends Number>
        implements BinaryOperator<NumberType> {

    @Override
    public int compareTo(BinaryOperator o) {
        if (getPriority().ordinal() == o.getPriority().ordinal()) {
            return 0;
        }
        return (getPriority().ordinal() > o.getPriority().ordinal()) ? 1 : -1;
    }
}

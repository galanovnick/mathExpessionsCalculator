package calculator.impl.tokens;

public abstract class AbstractBinaryOperator implements BinaryOperator {

    @Override
    public int compareTo(BinaryOperator o) {
        if (getPriority().ordinal() == o.getPriority().ordinal()) {
            return 0;
        }
        return (getPriority().ordinal() > o.getPriority().ordinal()) ? 1 : -1;
    }
}

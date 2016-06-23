package calculator.impl.tokens;

import static calculator.impl.tokens.Priority.HIGH;

public class Power extends AbstractBinaryOperator {

    private final Priority priority = HIGH;

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return Math.pow(leftOperand, rightOperand);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}

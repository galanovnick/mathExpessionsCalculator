package calculator.impl.tokens;

import static calculator.impl.tokens.Priority.LOW;

public class Plus extends AbstractBinaryOperator {

    private final Priority priority = LOW;

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand + rightOperand;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}

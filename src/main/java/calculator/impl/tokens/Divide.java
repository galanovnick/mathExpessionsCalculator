package calculator.impl.tokens;

import static calculator.impl.tokens.Priority.MEDIUM;

public class Divide extends AbstractBinaryOperator {

    private final Priority priority = MEDIUM;

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand / rightOperand;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}

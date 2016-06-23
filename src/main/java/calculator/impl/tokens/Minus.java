package calculator.impl.tokens;

import calculator.impl.tokens.priorityenum.Priority;

import static calculator.impl.tokens.priorityenum.Priority.LOW;

public class Minus extends AbstractBinaryOperator {

    private final Priority priority = LOW;

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand - rightOperand;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}

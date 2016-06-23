package calculator.impl.tokens;

import calculator.impl.tokens.priorityenum.Priority;

import static calculator.impl.tokens.priorityenum.Priority.MEDIUM;

public class Multiply extends AbstractBinaryOperator {

    private final Priority priority = MEDIUM;

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand * rightOperand;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}

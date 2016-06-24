package calculator.impl.tokens;

import calculator.impl.tokens.priorityenum.Priority;

import static calculator.impl.tokens.priorityenum.Priority.LOW;

public class Minus extends AbstractBinaryOperator {

    private final Priority priority = LOW;

    @Override
    public Double execute(Double leftOperand, Double rightOperand) {
        return leftOperand - rightOperand;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}

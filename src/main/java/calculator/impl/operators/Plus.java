package calculator.impl.operators;

import static calculator.impl.operators.Priority.LOW;

public class Plus extends AbstractBinaryOperator<Double> {

    private final Priority priority = LOW;

    @Override
    public Double execute(Double leftOperand, Double rightOperand) {
        return leftOperand + rightOperand;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}

package calculator.impl.operators;

import static calculator.impl.operators.Priority.HIGH;

public class Power extends AbstractBinaryOperator<Double> {

    private final Priority priority = HIGH;

    @Override
    public Double execute(Double leftOperand, Double rightOperand) {
        return Math.pow(leftOperand, rightOperand);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}

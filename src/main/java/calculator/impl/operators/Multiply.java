package calculator.impl.operators;

import static calculator.impl.operators.Priority.MEDIUM;

public class Multiply extends AbstractBinaryOperator<Double> {

    private final Priority priority = MEDIUM;

    @Override
    public Double execute(Double leftOperand, Double rightOperand) {
        return leftOperand * rightOperand;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}

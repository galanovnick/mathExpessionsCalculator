package calculator.impl.tokens;

public class BinaryOperatorsFactory {

    public BinaryOperator createOperator(String token) {
        switch (token) {
            case "+": return new Plus();
            default: return null;
        }
    }
}

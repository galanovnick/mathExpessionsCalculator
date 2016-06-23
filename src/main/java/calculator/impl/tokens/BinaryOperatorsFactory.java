package calculator.impl.tokens;

public class BinaryOperatorsFactory {

    public BinaryOperator createOperator(String token) {
        switch (token) {
            case "+": return new Plus();
            case "-": return new Minus();
            case "*": return new Multiply();
            case "/": return new Divide();
            case "^": return new Power();
            default: return null;
        }
    }
}

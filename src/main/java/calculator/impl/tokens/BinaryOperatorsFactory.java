package calculator.impl.tokens;


/**
 * Creates binary operators by string token.
 */
public class BinaryOperatorsFactory {

    /**
     * Returns created binary operator.
     * @param token input string token
     * @return binary operator
     */
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

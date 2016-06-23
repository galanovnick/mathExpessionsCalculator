package calculator.impl.tokens;


/**
 * Creates function by string token.
 */
public class FunctionsFactory {

    /**
     * Returns created function.
     * @param token input string token
     * @return function
     */
    public Function createFunction(String token) {
        switch (token) {
            case "(": return args -> args[0];
            case "min(": return args -> {

                double result = args[0];
                for (double next : args) {
                    result = (next < result) ? next : result;
                }
                return result;
            };

            case "max(": return args -> {

                double result = args[0];
                for (double next : args) {
                    result = (next > result) ? next : result;
                }
                return result;
            };

            default: return null;
        }
    }
}

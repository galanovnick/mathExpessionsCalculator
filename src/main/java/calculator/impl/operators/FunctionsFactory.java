package calculator.impl.operators;


import calculator.CalculationException;

/**
 * Creates function by string token.
 */
public class FunctionsFactory {

    /**
     * Returns created function.
     *
     * @param token input string token
     * @return function
     */
    public Function createFunction(String token) {
        switch (token) {
            case "(":
                return new Function<Double>() {
                    @Override
                    public Double execute(Double[] args) {
                        return args[0];
                    }

                    @Override
                    public int getMinArgsNumber() {
                        return 1;
                    }
                };
            case "min(":
                return new Function<Double>() {
                    @Override
                    public Double execute(Double[] args) {

                        double result = args[0];
                        for (double next : args) {
                            result = (next < result) ? next : result;
                        }
                        return result;
                    }

                    @Override
                    public int getMinArgsNumber() {
                        return 2;
                    }
                };

            case "max(":
                return new Function<Double>() {
                    @Override
                    public Double execute(Double[] args) {

                        double result = args[0];
                        for (double next : args) {
                            result = (next > result) ? next : result;
                        }
                        return result;
                    }

                    @Override
                    public int getMinArgsNumber() {
                        return 2;
                    }
                };

            default:
                return null;
        }
    }
}

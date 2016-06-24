package calculator.impl.abstractstatemachine;

import calculator.impl.OutputContext;

/**
 * Representation of abstract atomic operation, which can be done with stack of operands/operators
 */
public interface StackCommand<ResolvingError extends Exception> {

    /**
     * Execute operation.
     */
    void execute(OutputContext outputContext) throws ResolvingError;
}

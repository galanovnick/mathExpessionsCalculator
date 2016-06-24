package calculator.impl.abstractstatemachine;

import calculator.CalculationException;
import calculator.impl.OutputContext;

/**
 * Representation of abstract atomic operation, which can be done with stack of operands/operators
 */
public interface StackCommand {

    /**
     * Execute operation.
     */
    void execute(OutputContext outputContext) throws CalculationException;
}

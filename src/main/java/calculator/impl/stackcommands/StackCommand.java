package calculator.impl.stackcommands;

/**
 * Representation of abstract atomic operation, which can be done with stack of operands/operators
 */
public interface StackCommand {

    /**
     * Execute operation.
     */
    void execute();
}

package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.stackcommands.StackCommand;

/**
 * Abstract expression parser. Contains parsing method.
 */
public interface ExpressionParser {

    StackCommand parseExpression(InputContext inputContext, OutputContext outputContext);
}

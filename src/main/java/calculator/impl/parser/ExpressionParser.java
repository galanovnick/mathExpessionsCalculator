package calculator.impl.parser;

import calculator.impl.context.InputContext;
import calculator.impl.context.OutputContext;
import calculator.impl.stackcommands.StackCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract expression parser. Contains parsing method.
 */
public interface ExpressionParser {

    StackCommand parseExpression(InputContext inputContext, OutputContext outputContext);

    /**
     * List of valid operators symbols.
     */
    List<Character> VALID_OPERATOR_SYMBOLS = new ArrayList<Character>(){{
        add('+');
        add('-');
        add('*');
        add('/');
        add('^');
    }};
}

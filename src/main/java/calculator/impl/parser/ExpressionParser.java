package calculator.impl.parser;

import calculator.impl.InputContext;
import calculator.impl.abstractstatemachine.StackCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract expression parser. Contains parsing method.
 */
public interface ExpressionParser {

    StackCommand parseExpression(InputContext inputContext);

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

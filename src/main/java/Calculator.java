import java.util.Stack;

/**
 * A class that evaluates the value of an expression.
 */
public class Calculator {
    /**
     * Expression containing numbers, operators, brackets, spaces, other characters.
     */
    private String Expression;

    /**
     * Constructor of the Calculator class
     * @param EnteredExpression Expression
     */
    public Calculator(String EnteredExpression) {
        this.Expression = EnteredExpression;
    }

    /**
     * Method for removing spaces from an expression
     */
    private void DeleteSpace()
    {
        StringBuilder NewExpression = new StringBuilder();
        for (int index = 0; index < Expression.length(); index++)
            if (Expression.charAt(index) != ' ')
                NewExpression.append(Expression.charAt(index));
        Expression = NewExpression.toString();
    }

    /**
     * The method of checking the expression for correctness
     * @return True if the expression is true, otherwise false
     */
    private boolean Correctness()
    {
        if (Expression.isEmpty()) return false;
        else {
            DeleteSpace();
            int bracket = 0;

            for (int index = 0; index < Expression.length(); index++) {

                if (bracket >= 0) {

                    switch (Expression.charAt(index)) {

                        case '+': case '-': case '*': case '/': {
                            if(index == 0 || index == Expression.length() - 1) return false;
                            else
                            if (Expression.charAt(index+1) == '+' ||
                                    Expression.charAt(index+1) == '-' ||
                                    Expression.charAt(index+1) == '*' ||
                                    Expression.charAt(index+1) == '/' ||
                                    Expression.charAt(index+1) == ')')
                                return false;
                            break;
                        }

                        case '(': {
                            bracket++;
                            if (Expression.charAt(index+1) == '+' ||
                                    Expression.charAt(index+1) == '*' ||
                                    Expression.charAt(index+1) == '/' ||
                                    Expression.charAt(index+1) == ')')
                                return false;
                            else if (index == Expression.length() - 1) return false;
                            break;
                        }

                        case ')': {
                            bracket--;
                            if (index == 0) return false;
                            else if (Expression.charAt(index-1) == '+' ||
                                    Expression.charAt(index-1) == '-' ||
                                    Expression.charAt(index-1) == '*' ||
                                    Expression.charAt(index-1) == '/' ||
                                    Expression.charAt(index-1) == '(' )
                                return false;
                            break;
                        }

                        case '.': {
                            if(index == Expression.length()-1) return false;
                            else if (Expression.charAt(index+1) < '0' ||
                                    Expression.charAt(index+1) > '9')
                                return false;
                            break;
                        }

                        default:
                            if (Expression.charAt(index) >= '0' &&
                                    Expression.charAt(index) <= '9') {
                                if (index != 0)
                                    if (Expression.charAt(index-1) == ')' )
                                        return false;
                                if (index != Expression.length()-1)
                                    if (Expression.charAt(index+1) == '(' )
                                        return false;
                            }
                            else return false;
                    }
                }
                else return false;
            }
            return bracket == 0;
        }
    }

    /**
     * A method that determines the operator's priority.
     * @param Operation Symbol (operator, bracket)
     * @return Symbol priority
     */
    private int Priority(char Operation) {
        if (Operation == '*' || Operation == '/')
            return 3;
        else if (Operation == '+' || Operation == '-')
            return 2;
        else if (Operation == '(')
            return 1;
        else if (Operation == ')')
            return -1;
        return 0;
    }

    /**
     * A method that overwrites an expression into a postfix form.
     * @return True if it was possible to write, otherwise false.
     */
    private boolean PostfixForm() {

        if (!Correctness() || Expression.isEmpty())
            return false;

        else {
            Stack<Character> SymbolExpression = new Stack<Character>();
            StringBuilder PostfixExpression = new StringBuilder();

            for (int index = 0; index < Expression.length(); index++) {
                int priority = Priority(Expression.charAt(index));

                if (priority == 0) PostfixExpression.append(Expression.charAt(index));

                else if (priority == 1) SymbolExpression.push(Expression.charAt(index));

                else if (Expression.charAt(index) == '-' && Expression.charAt(index-1) == '(') {

                    PostfixExpression.append(Expression.charAt(index));
                }

                else if (priority > 1) {
                    PostfixExpression.append(' ');

                    while (!SymbolExpression.empty()) {
                        if (Priority(SymbolExpression.peek()) >= priority)
                            PostfixExpression.append(SymbolExpression.pop());
                        else break;
                    }

                    SymbolExpression.push(Expression.charAt(index));
                }

                else if (priority == -1) {
                    PostfixExpression.append(' ');

                    while (Priority(SymbolExpression.peek()) != 1)
                        PostfixExpression.append(SymbolExpression.pop());

                    SymbolExpression.pop();
                }
            }

            while (!SymbolExpression.empty()) {
                PostfixExpression.append(' ');
                PostfixExpression.append(SymbolExpression.pop());
            }
            Expression = PostfixExpression.toString();
            System.out.println(Expression);
            return true;
        }
    }

}

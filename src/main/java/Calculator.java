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


}

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
}

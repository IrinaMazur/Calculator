class CalculatorTest {
        public static void main(String[] args) {
            testCorrectness_ValidExpression();
            testCorrectness_InvalidExpression_UnbalancedBrackets();
            testPostfixForm();
            testCalculate_SimpleAddition();
            testCalculate_ComplexExpression();
            testEquals_SameExpression();
            System.out.println("All tests completed.");
        }

        private static void testCorrectness_ValidExpression() {
            Calculator calculator = new Calculator("3 + 5 * ( 2 - 8 )");
            if (calculator.Calculate()) {
                System.out.println("testCorrectness_ValidExpression: PASSED");
            } else {
                System.out.println("testCorrectness_ValidExpression: FAILED");
            }
        }

        private static void testCorrectness_InvalidExpression_UnbalancedBrackets() {
            Calculator calculator = new Calculator("(3 + 5 * 2 - 8");
            if (!calculator.Calculate()) {
                System.out.println("testCorrectness_InvalidExpression_UnbalancedBrackets: PASSED");
            } else {
                System.out.println("testCorrectness_InvalidExpression_UnbalancedBrackets: FAILED");
            }
        }

        private static void testPostfixForm() {
            Calculator calculator = new Calculator("3 + 5 * ( 2 - 8 )");
            calculator.Calculate();
            String expected = "3 5 2 8 - * +";
            if (calculator.toString().equals(expected)) {
                System.out.println("testPostfixForm: PASSED");
            } else {
                System.out.println("testPostfixForm: FAILED");
            }
        }

        private static void testCalculate_SimpleAddition() {
            Calculator calculator = new Calculator("3 + 5");
            calculator.Calculate();
            String expected = "8.0";
            if (calculator.toString().equals(expected)) {
                System.out.println("testCalculate_SimpleAddition: PASSED");
            } else {
                System.out.println("testCalculate_SimpleAddition: FAILED");
            }
        }

        private static void testCalculate_ComplexExpression() {
            Calculator calculator = new Calculator("( 3 + 5 ) * 2");
            calculator.Calculate();
            String expected = "16.0";
            if (calculator.toString().equals(expected)) {
                System.out.println("testCalculate_ComplexExpression: PASSED");
            } else {
                System.out.println("testCalculate_ComplexExpression: FAILED");
            }
        }

        private static void testEquals_SameExpression() {
            Calculator calculator1 = new Calculator("3 + 5");
            Calculator calculator2 = new Calculator("3 + 5");
            if (calculator1.equals(calculator2)) {
                System.out.println("testEquals_SameExpression: PASSED");
            } else {
                System.out.println("testEquals_SameExpression: FAILED");
            }
        }
    }
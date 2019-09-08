// 424. Evaluate Reverse Polish Notation
// 中文English
// Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
// Valid operators are +, -, *, /. Each operand may be an integer or another expression.
//
// Example
// Example 1:
//
// Input: ["2", "1", "+", "3", "*"]
// Output: 9
// Explanation: ["2", "1", "+", "3", "*"] -> (2 + 1) * 3 -> 9
// Example 2:
//
// Input: ["4", "13", "5", "/", "+"]
// Output: 6
// Explanation: ["4", "13", "5", "/", "+"] -> 4 + 13 / 5 -> 6


public class Solution {
    /*
     * @param tokens: The Reverse Polish Notation
     * @return: the value
     */
    public int evalRPN(String[] tokens) {
        // write your code here
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }

        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (isOperator(token)) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                int result = calculate(num1, num2, token);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
    private boolean isOperator(String token) {
        // 一定要注意 String 的比较要用 equals， 而不用 ==
        // if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
        //     return true;
        // } else {
        //     return false;
        // }

        // 同时这里可以用更简单的做法是
        String operators = "+-*/";
        if (operators.contains(token)) {
            return true;
        } else {
            return false;
        }
    }
    private int calculate(int num1, int num2, String operator) {
        if (operator.equals("+")) {
            return num1 + num2;
        } else if (operator.equals("-")) {
            return num1 - num2;
        } else if (operator.equals("*")) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }
}

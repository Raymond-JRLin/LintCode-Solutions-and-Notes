// 423. Valid Parentheses
// 中文English
// Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
// The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
//
// Example
// Example 1:
//
// Input: "([)]"
// Output: False
// Example 2:
//
// Input: "()[]{}"
// Output: True
// Challenge
// Use O(n) time, n is the number of parentheses.


public class Solution {
    /**
     * @param s A string
     * @return whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        // Write your code here
        if (s == null || s.length() < 2) {
            return false;
        }
        // from example we cannot use 2 pointers, since 它必须成对出现
        // so we can do similar to Expression Expand, or
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                // 前面没有了 或者 前面不是与当前符号成对的
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else {
                // other char
                return false;
            }
        }
        // check whether there are still other char in stack
        return stack.isEmpty();

        // if do just for loop to check continuous 2 positions, it would be wrong, since some cases like: "( [ ] )"
        // for (int i = 0; i < s.length() - 1; i += 2) {
        //     char curr = s.charAt(i);
        //     char next = s.charAt(i + 1);
        //     if (curr == '(') {
        //         if (next != ')') {
        //             return false;
        //         }
        //     } else if (curr == '{') {
        //         if (next != '}') {
        //             return false;
        //         }
        //     } else if (curr == '[') {
        //         if (next != ']') {
        //             return false;
        //         }
        //     } else {
        //         return false;
        //     }
        // }
        // return true;
    }
}

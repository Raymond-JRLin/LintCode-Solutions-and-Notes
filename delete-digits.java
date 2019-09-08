// 182. Delete Digits
// 中文English
// Given string A representing a positive integer which has N digits, remove any k digits of the number, the remaining digits are arranged according to the original order to become a new positive integer.
//
// Find the smallest integer after remove k digits.
//
// N <= 240 and k <= N,
//
// Example
// Example 1:
//
// Input: A = "178542", k = 4
// Output:"12"
// Example 2:
//
// Input: A = "568431", k = 3
// Output:"431"


public class Solution {
    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public String DeleteDigits(String A, int k) {
        // write your code here
        if (k == 0) {
            return A;
        }
        if (A == null || A.length() == 0 || k >= A.length()) {
            return "";
        }
        int n = A.length();

        // method 1: based on stack
        // String result = "";
        // int len = n - k; // 最终结果的长度
        // // stack 里面放最终的结果
        // Stack<Character> stack = new Stack<Character>();
        // for (int i = 0; i < n; i++) {
        //     char c = A.charAt(i);
        //     while (!stack.isEmpty() && c < stack.peek() && n - 1 - i >= len - stack.size()) {
        //         // 如果 stack 里面有元素， 并且还比当前 char 大， 同时剩下的 string 还够放入 stack 中构成结果， 那就要把大的拿出来
        //         stack.pop();
        //     }
        //     if (stack.size() < len) {
        //         // 最终结果就只有 len 长
        //         stack.push(c);
        //     }
        // }
        // while (!stack.isEmpty()) {
        //     // 倒过来组成 string
        //     result = stack.pop() + result;
        // }
        // while (result.length() > 0 && result.charAt(0) == '0') {
        //     // 去掉 leading zeros
        //     result = result.substring(1);
        // }
        // return result;

        // method 2: use StringBuilder
        StringBuilder sb = new StringBuilder(A);
        for (int i = 0; i < k; i++) {
            int j = 0; // 每次都从 0 开始找
            while (j < sb.length() - 1 && sb.charAt(j) <= sb.charAt(j + 1)) {
                j++;
            }
            sb.delete(j, j + 1);
        }
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.delete(0, 1);
        }
        return sb.toString();
    }
}

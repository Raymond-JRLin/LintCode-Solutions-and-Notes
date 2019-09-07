// 427. Generate Parentheses
// 中文English
// Given n, and there are n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
// Example
// Example 1:
//
// Input: 3
// Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
// Example 2:
//
// Input: 2
// Output: ["()()", "(())"]


public class Solution {
    /**
     * @param n n pairs
     * @return All combinations of well-formed parentheses
     */
    public ArrayList<String> generateParenthesis(int n) {
        // Write your code here
        if (n < 0) {
            return null;
        }
        ArrayList<String> result = new ArrayList<String>();
        if (n == 0) {
            return result;
        }
        if (n == 1) {
            result.add("()");
            return result;
        }
        String paren = "";
        dfs(result, paren, n, 0, 0);
        return result;
    }
    private void dfs(ArrayList<String> result, String paren, int n, int left, int right) {
        if (left == n && right == n) {
            result.add(paren);
            return;
        }
        if (left < n) {
            // paren += "(";
            // left++;
            // dfs(result, paren, n, left, right);
            // paren = paren.substring(0, paren.length() - 1);
            // left--;
            dfs(result, paren + "(", n, left + 1, right);
        }
        if (left > right) {
            // paren += ")";
            // right++;
            // dfs(result, paren, n, left, right);
            // paren = paren.substring(0, paren.length() - 1);
            // right--;
            dfs(result, paren + ")", n, left, right + 1);
        }

    }
}

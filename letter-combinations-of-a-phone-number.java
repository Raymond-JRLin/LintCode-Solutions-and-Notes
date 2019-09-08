// 425. Letter Combinations of a Phone Number
// 中文English
// Given a digit string excluded '0' and '1', return all possible letter combinations that the number could represent.
//
// A mapping of digit to letters (just like on the telephone buttons) is given below.
//
// 1	2
// ABC	3
// DEF
// 4
// GHI	5
// JKL	6
// MNO
// 7
// PQRS	8
// TUV	9
// WXYZ
// Example
// Example 1:
//
// Input: "23"
// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
// Explanation:
// '2' could be 'a', 'b' or 'c'
// '3' could be 'd', 'e' or 'f'
// Example 2:
//
// Input: "5"
// Output: ["j", "k", "l"]
// Notice
// Although the answer above is in lexicographical order, your answer could be in any order you want.
//


public class Solution {
    /*
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        // write your code here
        // apparently we should use DFS
        if (digits == null) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        if (digits.length() == 0) {
            return result;
        }
        dfs(digits, 0, result, "");
        return result;
    }
    private void dfs(String digits, int index, List<String> result, String temp) {
        if (digits.length() == index) {
            result.add(temp);
            return;
        }
        String curr = digit2String(digits.charAt(index));
        for (int i = 0; i < curr.length(); i++) {
            temp += String.valueOf(curr.charAt(i));
            dfs(digits, index + 1, result, temp);
            temp = temp.substring(0, temp.length() - 1);
        }
    }
    private String digit2String(char c) {
        if (c == '2') {
            return "abc";
        } else if (c == '3') {
            return "def";
        } else if (c == '4') {
            return "ghi";
        } else if (c == '5') {
            return "jkl";
        } else if (c == '6') {
            return "mno";
        } else if (c == '7') {
            return "pqrs";
        } else if (c == '8') {
            return "tuv";
        } else if (c == '9') {
            return "wxyz";
        } else {
            return "";
        }
    }
}

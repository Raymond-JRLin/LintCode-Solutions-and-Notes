// 136. Palindrome Partitioning
// 中文English
// Given a string s. Partition s such that every substring in the partition is a palindrome.
//
// Return all possible palindrome partitioning of s.
//
// Example
// Example 1:
//
// Input: "a"
// Output: [["a"]]
// Explanation: Only 1 char in the string, only 1 way to split it (itself).
// Example 2:
//
// Input: "aab"
// Output: [["aa", "b"], ["a", "a", "b"]]
// Explanation: There are 2 ways to split "aab".
//     1. Split "aab" into "aa" and "b", both palindrome.
//     2. Split "aab" into "a", "a", and "b", all palindrome.
// Notice
// Different partitionings can be in any order.
// Each substring must be a continuous segment of s.


public class Solution {
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        // write your code here
        List<List<String>> results = new ArrayList<List<String>>();
        if (s == null || s.length() == 0) {
            return results;
        }
        ArrayList<String> list = new ArrayList<>();
        dfs(s, 0, list, results);
        return results;
    }
    private void dfs(String s, int startIndex, ArrayList<String> list, List<List<String>> results) {
        if (startIndex == s.length()) {
            results.add(new ArrayList<String>(list));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String subString = s.substring(startIndex, i + 1);
            if (!isPalindrome(subString)) {
                continue;
            }
            list.add(subString);
            dfs(s, i + 1, list, results);
            list.remove(list.size() - 1);
        }
    }
    private boolean isPalindrome(String s) {
        int l = s.length();
        for (int i = 0, j = l - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}

// 647. Find All Anagrams in a String
// 中文English
// Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
//
// Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 40,000.
//
// The order of output does not matter.
//
// Example
// Example 1:
//
// Input : s =  "cbaebabacd", p = "abc"
// Output : [0, 6]
// Explanation :
// The substring with start index = 0 is "cba", which is an anagram of "abc".
// The substring with start index = 6 is "bac", which is an anagram of "abc".


public class Solution {
    /**
     * @param s a string
     * @param p a non-empty string
     * @return a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        int[] nums = new int[27];
        int n = p.length();
        for (int i = 0; i < n; i++) {
            nums[p.charAt(i) - 'a']++;
        }
        int len = s.length();
        // use 2 pointers to traverse all possible substrings in s and compare them to p
        int start = 0;
        int end = 0;
        int count = 0; // record how many digits in s matched that in p
        while (end < len) {
            // if there is one same char, record count
            if (nums[s.charAt(end) - 'a'] > 0) {
                count++;
            }
            // count equals p.length(), then we found one substring
            if (count == n) {
                result.add(start);
            }
            // if not, move end pointer to next one to keep searching
            // we should minus values in nums first, which means we already visited it
            nums[s.charAt(end) - 'a']--;
            end++;
            // if we passed through p.length, then we should move start pointer to next one
            if (end - start == n) { // end will reach before we check start ~ end
                // if start position matched any char in p, then we should minus count
                if (nums[s.charAt(start) - 'a'] >= 0) {
                    count--;
                }
                // makeup minus we did before, because this position can be 1 since it only appears once in p, but it could be appeared again after start index, e.g. "abab", "ab"
                nums[s.charAt(start) - 'a']++;
                start++;
            }
        }
        return result;
    }
}

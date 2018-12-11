// 386. Longest Substring with At Most K Distinct Characters
// Description
// Given a string s, find the length of the longest substring T that contains at most k distinct characters.
//
// Have you met this question in a real interview?
// Example
// For example, Given s = "eceba", k = 3,
//
// T is "eceb" which its length is 4.
//
// Challenge
// O(n), n is the size of the string s.


public class Solution {
    /**
     * @param s : A string
     * @return : The length of the longest substring
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }

        return method1(s, k);
    }

    private int method1(String s, int k) {
        // 用两根指针，i指向头，j往后扫，扫到k个不同的字符就停止，计算此时的longest length，然后i继续往后，j也从上次停下的位置往后扫，再到k个不同的字符，计算length，然后更新max，以此继续
        // 可以用HashMap记住一个字符出现了几次，因为可能有重复，所以要用Map来记出现的次数，可增可减

        // if (k >= s.length()) {
        //     return s.length();
        // } // 特殊的edge case，因为下面for loop限定了i的范围，如果不写这个case，可以就直接让i < s.length()
        Map<Character, Integer> hash = new HashMap<>(); // <char, frequency>
        int j = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                char c = s.charAt(j);
                if (hash.containsKey(c)) {
                    hash.put(c, hash.get(c) + 1);
                } else { // 新的字符
                    if (hash.size() == k) { // 已经有k个不同的了
                        break;
                    }
                    hash.put(c, 1);
                }
                j++;
            }
            // 每一轮（在i往后移动之前）都要更新max length
            maxLength = Math.max(maxLength, j - i);
            // 在i往后移动之前，要把当前i位置的字符数量减1
            char x = s.charAt(i);
            if (hash.get(x) > 1) {
                hash.put(x, hash.get(x) - 1);
            } else {
                hash.remove(x);
            }
        }
        return maxLength;
    }
}

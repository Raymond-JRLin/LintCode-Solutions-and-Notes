// 958. Palindrome Data Stream
// 中文English
// There is a data stream, one letter at a time, and determine whether the current stream's arrangement can form a palindrome.
//
// Example
// Example 1:
//
// input:s = ['a','a','a','a','a']
// outut:[1,1,1,1,1]
// Explanation:
// “a” can form a palindrome
// “aa” can form a palindrome
// “aaa” can form a palindrome
// “aaaa” can form a palindrome
// “aaaaa” can form a palindrome
// Example 2:
//
// input:s = ['a','b','a']
// outut:[1,0,1]
// Explanation:
// “a” can form a palindrome
// “ab” can't form a palindrome
// “aba” can form a palindrome
// Notice
// 1 <= |s| <= 10^5


public class Solution {
    /**
     * @param s: The data stream
     * @return: Return the judgement stream
     */
    public int[] getStream(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return new int[]{1};
        }

        return method1(s);
    }

    private int[] method1(String s) {
        // 要搞清楚题意： data stream 是可以以随意排列的, 并没有先后顺序。 所以不是第一个 char， 生成新的 string， check 是否是 palindrome； 而是读进来的当前的 string， 能否组成 palindrome。 那么就是每次去查个数为奇数的 char 有几个
        // O(N) time and O(1) space
        int n = s.length();
        int[] result = new int[n];
        int[] count = new int[26];
        int odd = 0;
        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - 'a';
            count[num]++;
            if (count[num] % 2 != 0) {
                odd++;
            } else {
                odd--;
            }

            if (odd > 1) {
                result[i] = 0;
            } else {
                result[i] = 1;
            }
        }

        return result;
    }
}

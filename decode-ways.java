// 512. Decode Ways
// 中文English
// A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given an encoded message containing digits, determine the total number of ways to decode it.
//
// Example
// Example 1:
//
// Input: "12"
// Output: 2
// Explanation: It could be decoded as AB (1 2) or L (12).
// Example 2:
//
// Input: "10"
// Output: 1


public class Solution {
    /**
     * @param s a string,  encoded message
     * @return an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        // Write your code here
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        // 问有多少种方法，可以想到用DP，但在DP过程中有诸多限制条件
        int n = s.length();
        // definition: f[i] = 到当前i有多少种decode方式
        int[] f = new int[n + 1];
        // initialization
        f[0] = 1;
        f[1] = 1;
        // DP
        for (int i = 2; i < n + 1; i++) {
            // 类似于climb stairs, 可以从i - 1过来，也可以从i - 2过来
            // case 1: i - 1 is valid, i.e. 1 ~ 9
            if (s.charAt(i - 1) != '0') {
                f[i] = f[i - 1];
            }
            // case 2: i - 2 and i - 1 is valid together, i.e. 10 ~ 26
            if (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6') { // && has higher priority than ||
                f[i] += f[i - 2];
            }
        }
        // result
        return f[n];
    }
}

// 1572. Asking For The Longest 01 Substring
// Description
// Now that there is a 01 string str, your task is to find the longest 01 consecutive substrings, with 0 and 1 alternating, for example 010, 10101, 01. However, this is a simple problem for the excellent lintcode students. So now, you can do something to make 01 consecutive substrings as long as possible. The operation means that you can select a position, break the string, turn it into two strings, then flip each string and finally stitch them together in the original order. You can do this zero or more times and return the length of the largest 01 consecutive substring you can finally get.
//
// String length does not exceed 100000
// Have you met this question in a real interview?
// Example
// Give str="100010010" and return 5.
//
// You can split 10|0010010 , and after flipping on both sides, it becomes 01|0100100.
// That is, 010100100, select position 1 to position 5, 01010, and the length is 5.
// Give str="1001" and return 2.
//
// No matter how you split the flip, it won't make the answer bigger. So 10 is the largest 01 consecutive substring and returns 2.


public class Solution {
    /**
     * @param str: the string
     * @return: the length of substring
     */
    public int askingForTheLongest01Substring(String str) {
        // Write your code here
        if (str == null || str.length() == 0) {
            return 0;
        }

        // return method1(str);

        // return method2(str);

        return method3(str);
    }

    private int method3(String str) {
        // 2 pointers
        String s = str + str; // concat original string as a circle
        int n = s.length();
        int right = 0;
        int result = 1;
        for (int i = 0; i < n; i++) {
            // i is left pointer
            // 如果和 right 有距离， 那么 right 会在不连续交替的地方停下来， 下面的 while 会 break 直到 i 走到 right 再重新开始一轮
            while (right != n) {
                if (right != i && s.charAt(right) == s.charAt(right - 1)) {
                    // 至少向后走了， 然后不连续交替
                    break;
                } else {
                    right++;
                }
            }
            result = Math.max(result, right - i);
            if (result > n / 2) {
                return n / 2;
            }
        }
        return result;
    }

    // 如果是直接问最大连续01子串，那么直接进行一个动态规划即可。
    // 即if (s[i]!=s[i-1]) dp[i]=dp[i-1]+1 else dp[i]=1
    // 那么，在允许进行操作之后，我们要考虑的是，什么是不变化的。
    // 我们可以这样思考，将字符串的头尾连接起来，形成一个环。每次变化后就会发现，其实是在这个环上选择一个位置剪断成链，然后下一次操作再首尾连接，再挑选一个位置剪断成链，可以发现，在这个无论多少次变化字母的相对的顺序就是不变的，我们只要在这个环中相邻连续不相同的数即可。

    private int method2(String str) {
        // DP
        String s = str + str; // concat original string as a circle
        int n = s.length();
        // definition: f[i] = the longest 10 substring starting with i in s
        int[] f = new int[n];
        // initialization
        Arrays.fill(f, 1); // at least every position has itself to be 10 substring
        // DP
        int result = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                f[i] = f[i - 1] + 1;
            }
            if (f[i] > n / 2) {
                return n / 2;
            }
            if (f[i] > result) {
                result = f[i];
            }
        }
        // result
        return result;
    }

    private int method1(String str) {
        // brute force
        String s = str + str; // concat original string as a circle
        int n = s.length();
        int result = 1;
        for (int i = 1; i < n; i++) {
            // start at every point, to get the longest 10 substring
            int temp = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(j) != s.charAt(j - 1)) {
                    temp++;
                } else {
                    break;
                }
            }
            if (temp > n / 2) {
                return n / 2; // the largest is n / 2
            }
            if (temp > result) {
                result = temp;
            }
        }
        return result;
    }
}


answer:


public class Solution {
    /**
     * @param str: the string
     * @return: the length of substring
     */
    public int askingForTheLongest01Substring(String str) {
        // Write your code here
        String s = str + str;
        int n = s.length();
        int dp[] = new int[200010];
        dp[0] = 1;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            if (dp[i] > n / 2) return n / 2;
            if (dp[i] > ans) ans = dp[i];
        }
        return ans;
    }
}


考点

动态规划

题解

如果是直接问最大连续01子串，那么直接进行一个动态规划即可。
即if (s[i]!=s[i-1]) dp[i]=dp[i-1]+1 else dp[i]=1
那么，在允许进行操作之后，我们要考虑的是，什么是不变化的。
我们可以这样思考，将字符串的头尾连接起来，形成一个环。每次变化后就会发现，其实是在这个环上选择一个位置剪断成链，然后下一次操作再首尾连接，再挑选一个位置剪断成链，可以发现，在这个无论多少次变化字母的相对的顺序就是不变的，我们只要在这个环中相邻连续不相同的数即可。然后就在环上dp一下即可。

// 1560. MinimumString
// Description
// Given a string s of lowercase letters of length n, remove the k characters from it and we will get a new string of length n-k. Please output the new string with the smallest lexicographic order.
//
// The lexicographical order in this problem: Firstly compare the length of two strings, the lexicographical order of the smaller length is smaller. If the length is the same, then comparison is started from the left side of the string to find the first different character, and the corresponding character smaller is the smaller lexicographical order string.
//
// For example: "abbz" and "abza"
// Firstly two strings are the same length, then compare bit by bit from the left:
// The first bit is "a", then continue to compare the next one
// The second bit is "b", then continue to compare the next one
// For third bit, the first string is "b", and the second string is "z". Because "b" < "z", the lexicographic order of the first string is smaller.
//
// 0 \leq k < n \leq 1000000≤k<n≤100000
// Have you met this question in a real interview?
// Example
// Given s="abccc",k=2,return "abc"
//
// Explanation：
// Delete the `c` of the 4th and 5th positions
// Given s="bacdb",k=2,return "acb"
//
// Explanation：
// Delete the `b` of the 1st position and the `d` of the 4th place.
// Given s="cba",k=2,return "a"
//
// Explanation：
// Delete the `c` of the 1st position and the `b` of the 2nd place.


public class Solution {
    /**
     * @param s: the string
     * @param k: the max time to remove characters
     * @return: Please output the new string with the smallest lexicographic order.
     */
    public String MinimumString(char[] s, int k) {
        // Write your code here
        if (s == null || s.length == 0) {
            return null;
        }
        if (k == 0) {
            return String.valueOf(s);
        }

        // return mytry(s, k);

        // return mytry2(s, k);

        return method2(s, k);
    }

    private String method2(char[] arr, int k) {
        // same idea with mytry2 but use StringBuilder
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            while (k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) > c) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(c);
        }
        while (k > 0) {
            sb.deleteCharAt(sb.length() - 1);
            k--;
        }
        return sb.toString();
    }

    private String mytry2(char[] arr, int k) {
        Stack<Character> stack = new Stack<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > arr[i]) {
                // attention we should pop when we have k to delete and current char is smaller
                stack.pop();
                k--;
            }
            stack.push(arr[i]);
        }
        while (k > 0) {
            stack.pop();
            k--; // when we pop(), then k--
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    String result;
    private String mytry(char[] arr, int k) {
        // wrong: stackOverFlow
        result = String.valueOf(arr);
        dfs(arr, arr.length - k, 0, new StringBuilder());
        return result;
    }
    private void dfs(char[] arr, int k, int index, StringBuilder sb) {
        if (k == 0) {
            // System.out.println("get: " + sb.toString());
            if (isSmallLexi(sb.toString(), result)) {
                result = sb.toString();
                // System.out.println("shorter result: " + result);
            }
            return;
        }
        for (int i = index; i < arr.length; i++) {
            sb.append(arr[i]);
            dfs(arr, k - 1, i + 1, sb);
            // System.out.println("now result: " + result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    private boolean isSmallLexi(String curr, String ori) {
        // System.out.println("compare: " + curr + " and " + ori);
        if (curr.length() != ori.length()) {
            return curr.length() < ori.length();
        }
        for (int i = 0; i < curr.length(); i++) {
            if (curr.charAt(i) != ori.charAt(i)) {
                return curr.charAt(i) < ori.charAt(i);
            }
        }
        return true;
    }
}


// answer:

这是一个经典的贪心问题
最优解是每次删除出现的第一个左边>右边相邻位的字符，如此往复k次即可，因为删除之后能尽量让字符串前面的字符减小，这也是全局最优解
注意一种特殊情况，就是如果已经不存在左边>右边相邻位的字符时，如果此时还有剩余的删除次数，就直接选择删后面的字符

public class Solution {
    /**
     * @param s: the string
     * @param k: the max time to remove characters
     * @return: Please output the new string with the smallest lexicographic order.
     */
    public String MinimumString(char[] s, int k) {
        // Write your code here
        int len = s.length, tot = 0;
        char[] Ans = new char[len + 1];
        Ans[++tot] = s[0];
        for (int i = 1; i < len; i++) {
            while (k > 0 && tot > 0 && Ans[tot] > s[i]) {
                k--;
                tot--;
            }
            Ans[++tot] = s[i];
        }
        String res = new String(Ans, 1, tot - k);
        return res;
    }
}

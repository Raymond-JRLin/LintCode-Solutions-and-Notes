// 823. Input Stream
// 中文English
// Give two input stream inputA and inputB, which have Backspace. If the final result of the two input streams are equal, output YES, otherwise output NO.
//
// Example
// Example1
//
// Input:  inputA = "abcde<<" and inputB = "abcd<e<"
// Output: "YES"
// Explanation:
// The final result of inputA and inputB is "abc", so return "YES".
// Example2
//
// Input:  inputA = "a<<bc" and inputB = "abc<"
// Output: "NO"
// Explanation:
// The final result of inputA is "bc", and the final result of inputB is "ab", so return "NO".
// Notice
// Input characters include only lowercase letters and '<'
// The length of input stream does not exceed 10000.


public class Solution {
    /**
     * @param inputA: Input stream A
     * @param inputB: Input stream B
     * @return: The answer
     */
    public String inputStream(String inputA, String inputB) {
        // Write your code here
        if ((inputA == null || inputA.length() == 0) && (inputB == null || inputB.length() == 0)) {
            return "YES";
        }

        // return mytry(inputA, inputB);

        return method2(inputA, inputB);
    }

    private String method2(String s1, String s2) {
        // 我们也可以用两个数组来存， 模拟 stack
        int n = s1.length();
        char[] a = new char[n];
        int len1 = 0;
        for (char c : s1.toCharArray()) {
            if (c == '<') {
                if (len1 > 0) {
                    len1--;
                }
            } else {
                a[len1++] = c;
            }
        }

        int m = s2.length();
        char[] b = new char[m];
        int len2 = 0;
        for (char c : s2.toCharArray()) {
            if (c == '<') {
                if (len2 > 0) {
                    len2--;
                }
            } else {
                b[len2++] = c;
            }
        }

        if (len1 == len2) {
            for (int i = 0; i < len1; i++) {
                if (a[i] != b[i]) {
                    return "NO";
                }
            }
            return "YES";
        } else {
            return "NO";
        }
    }


    private String mytry(String s1, String s2) {
        String result1 = getStr(s1);
        String result2 = getStr(s2);
        return result1.equals(result2) ? "YES" : "NO";
    }
    private String getStr(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '<') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}

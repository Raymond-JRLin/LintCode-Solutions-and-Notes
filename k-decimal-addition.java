// 1398. K Decimal Addition
// 中文English
// Give k, a, b, which means a and b are all k base numbers, and output a + b.
//
// Example
// Example1
//
// Input: k = 3, a = "12", b = "1"
// Output: 20
// Explanation:
// 12 + 1 = 20 in 3 bases.
// Example2
//
// Input: k = 10, a = "12", b = "1"
// Output: 13
// Explanation:
// 12 + 1 = 13 in 10 bases.
// Notice
// 2 <= k <= 10
// a, b are strings, the length does not exceed 1000.
// There may be a leading zero.


public class Solution {
    /**
     * @param k: The k
     * @param a: The A
     * @param b: The B
     * @return: The answer
     */
    public String addition(int k, String a, String b) {
        // Write your code here
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }

        return mytry(k, a, b);
    }

    private String mytry(int k, String a, String b) {
        int n = a.length();
        int m = b.length();
        int indexA = 0;
        while (indexA < n && a.charAt(indexA) == '0') {
            indexA++;
        }

        int indexB = 0;
        while (indexB < m && b.charAt(indexB) == '0') {
            indexB++;
        }

        int i = n - 1;
        int j = m - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= indexA && j >= indexB) {
            int sum = (a.charAt(i) - '0') + (b.charAt(j) - '0') + carry;
            int res = sum % k;
            carry = sum / k;
            sb.insert(0, res);
            i--;
            j--;
        }
        while (i >= indexA) {
            int sum = (a.charAt(i) - '0') + carry;
            int res = sum % k;
            carry = sum / k;
            sb.insert(0, res);
            i--;
        }
        while (j >= indexB) {
            int sum = (b.charAt(j) - '0') + carry;
            int res = sum % k;
            carry = sum / k;
            sb.insert(0, res);
            j--;
        }
        if (carry != 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }
}

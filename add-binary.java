// 408. Add Binary
// 中文English
// Given two binary strings, return their sum (also a binary string).
//
// Example
// Example 1:
//
// Input:
// a = "0", b = "0"
// Output:
// "0"
// Example 2:
//
// Input:
// a = "11", b = "1"
// Output:
// "100"


public class Solution {
    /**
     * @param a a number
     * @param b a number
     * @return the result
     */
    public String addBinary(String a, String b) {
        // Write your code here
        if (a == null || a.length() == 0 || a == "0") {
            return b;
        }
        if (b == null || b.length() == 0 || b == "0") {
            return a;
        }

        // method 1: use binary add based on a-b-problem and class API
        // reference: https://zhengyang2015.gitbooks.io/lintcode/add_binary_408.html
        // int biA = Integer.parseInt(a, 2);
        // int biB = Integer.parseInt(b, 2);
        // while (biB != 0) {
        //     int carry = biA & biB;
        //     biA = biA ^ biB;
        //     biB = carry << 1;
        // }
        // return Integer.toBinaryString(biA);

        // method 2: based on binary definition and 2 pointers, add bit by bit from end of a and b, if it's or over 2, then add 1 more to previous bit
        int lenA = a.length() - 1;
        int lenB = b.length() - 1;
        String result = new String("");
        int carry = 0;
        while (lenA >= 0 || lenB >= 0) {
            // get current digit
            int numA = lenA >= 0 ? a.charAt(lenA) - '0' : 0;
            int numB = lenB >= 0 ? b.charAt(lenB) - '0' : 0;
            // calculate sum
            int sum = numA + numB + carry;
            // sum % 2 is residue which should be in current digit
            result = String.valueOf(sum % 2) + result;
            carry = sum / 2;
            lenA--;
            lenB--;
        }
        if (carry == 1) {
            result = String.valueOf(carry) + result;
        }
        return result;
    }
}

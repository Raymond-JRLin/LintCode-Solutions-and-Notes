// 718. Repeat String
// 中文English
// Write a function, give a string A consisting of N characters and a string B consisting of M characters, returns the number of times A must be stated such that B is a substring of the repeated string. If B can never be a substring of the repeated A, then your function should return -1.
//
// Example
// Example1
//
// Input:  A = "abcd", B = "cdabcdab"
// Output: 3
// Explanation:
// After stating string A three times we obtain the string `abcdabcdabcd`. String B is a substring of this string.
// Example2
//
// Input:  A = "ac", B = "cc"
// Output: -1
// Explanation:
// `B` can never be a substring of the repeated `A`
// Notice
// Assume that 0 <= N <= 1000, 1 <= M <= 1000
//


public class Solution {
    /*
     * @param : string A to be repeated
     * @param : string B
     * @return: the minimum number of times A has to be repeated
     */
    public int repeatedString(String A, String B) {
        // write your code here
        if (A == null || A.length() == 0) {
            return -1;
        }
        if (B == null || B.length() == 0) {
            return 0;
        }

        return mytry(A, B);
    }

    private int mytry(String A, String B) {
        int n = A.length();
        int m = B.length();


        char first = B.charAt(0);
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (A.charAt(i) == first) {
                start = i;
                break;
            }
        }

        int j = 0;
        int count = start == 0 ? 0 : 1;
        while (j < m) {
            if (B.charAt(j) == A.charAt(start % n)) {
                if (start % n == 0) {
                    count++;
                }
                j++;
                start++;

            } else {
                return -1;
            }
        }

        // if (start % n == 0) {
        //     count++;
        // }

        return count;
    }
}

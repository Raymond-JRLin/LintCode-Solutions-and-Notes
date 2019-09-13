// 213. String Compression
// 中文English
// Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3.
//
// If the "compressed" string would not become smaller than the original string, your method should return the original string.
//
// You can assume the string has only upper and lower case letters (a-z).
//
// Example
// Example 1:
//
// Input: str = "aabcccccaaa"
// Output: "a2b1c5a3"
// Example 2:
//
// Input: str = "aabbcc"
// Output: "aabbcc"


public class Solution {
    /*
     * @param str: a string
     * @return: a compressed string
     */
    public String compress(String str) {
        // write your code here
        if (str == null || str.length() == 0) {
            return "";
        }
        int n = str.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int distinct = 0;
        while (i < n) {
            char curr = str.charAt(i);
            distinct++;
            int count = 0;
            while (i < n && str.charAt(i) == curr) {
                count++;
                i++;
            }
            sb.append(curr).append(count);
            count = 0;

            // int j = i;
			// while (j < n && str.charAt(j) == curr) {
			//     j++;
			// }
			// int len = j - i;
			// sb.append(curr).append(len);
			// i = j;
        }
        String result = sb.toString();
        if (result.length() >= n) {
            return str;
        } else {
            return result;
        }
    }
}

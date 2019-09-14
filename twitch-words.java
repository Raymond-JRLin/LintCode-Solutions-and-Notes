// 1401. Twitch Words
// 中文English
// A normal word never contains two or more consecutive letters. We call a substring is a twitch if three or more letters in the word are consecutive. Now given a word and output the start points and the end points of all the twitch from left to right.
//
// Example
// Example1
//
// Input: str = "whaaaaatttsup"
// Output: [[2,6],[7,9]]
// Explanation:
// "aaaa" and "ttt" are twitching letters, and output their starting and ending points.
// Example2
//
// Input: str = "whaaaaatttsup"
// Output: [[2,6],[7,9]]
// Explanation:
// "ooo", "sss" and "ssss" are twitching letters, and output their starting and ending points.
// Notice
// The input string length is n, n <= 100000.


public class Solution {
    /**
     * @param str: the origin string
     * @return: the start and end of every twitch words
     */
    public int[][] twitchWords(String str) {
        // Write your code here
        if (str == null || str.length() < 2) {
            return new int[0][2];
        }

        return mytry(str);
    }

    private int[][] mytry(String s) {
        int n = s.length();
        List<int[]> list = new ArrayList<>();

        int left = 0;
        int right = 0;
        int count = 0;
        while (right < n) {
            // System.out.println("left, right, count is " + left + ", " + right + ", " + count);
            while (right < n && s.charAt(right) == s.charAt(left)) {

                count++;
                right++;
                // System.out.println("duplicate: right, count is " + right + ", " + count);
            }
            if (count > 2) {
                // System.out.println("found one: [" + left + ", " + right + "]");
                list.add(new int[]{left, right - 1});


            }
            count = 0;
            left = right;
        }

        int size = list.size();
        int[][] result = new int[size][2];
        for (int i = 0; i < size; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}

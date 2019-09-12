// 53. Reverse Words in a String
// 中文English
// Given an input string, reverse the string word by word.
//
// Example
// Example 1:
// 	Input:  "the sky is blue"
// 	Output:  "blue is sky the"
//
// 	Explanation:
// 	return a reverse the string word by word.
//
// Example 2:
// 	Input:  "hello world"
// 	Output:  "world hello"
//
// 	Explanation:
// 	return a reverse the string word by word.
//
// Clarification
// What constitutes a word?
// A sequence of non-space characters constitutes a word and some words have punctuation at the end.
// Could the input string contain leading or trailing spaces?
// Yes. However, your reversed string should not contain leading or trailing spaces.
// How about multiple spaces between two words?
// Reduce them to a single space in the reversed string.


public class Solution {
    /**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
        // write your code
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();
        // int n = array.length;
        // if (n == 0) {
        //     return "";
        // }
        // sb.append(array[n - 1]);
        // for (int i = n - 2; i >= 0; i--) {
        //     if (!array[i].equals("")) {
        //         sb.append(" ");
        //         sb.append(array[i]);
        //     }

        // }
        // return sb.substring(0, sb.length());
        for (int i = array.length - 1; i >= 0; --i) {
            if (!array[i].equals("")) {
                sb.append(array[i]).append(" ");
            }
        }

        //remove the last " "
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}

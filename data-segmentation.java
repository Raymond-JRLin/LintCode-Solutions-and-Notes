// 956. Data Segmentation
// 中文English
// Given a string str, we need to extract the symbols and words of the string in order.
//
// Example
// Example 1:
//
// input: str = "(hi (i am)bye)"
// outut:["(","hi","(","i","am",")","bye",")"].
// Explanation:Separate symbols and words.
// Example 2:
//
// input: str =  "#ok    yes"
// outut:["#","ok","yes"]
// Explanation:Separate symbols and words.
// Example 3:
//
// input: str =  "##s"
// outut:["#","#","s"]
// Explanation:Separate symbols and words.
// Notice
// The length of str does not exceed 10000.
// The given str contains only lowercase letters, symbols, and spaces.


public class Solution {
    /**
     * @param str: The input string
     * @return: The answer
     */
    public String[] dataSegmentation(String str) {
        // Write your code here
        if (str == null || str.length() == 0) {
            return new String[0];
        }

        // return mytry(str);

        return method2(str);
    }

    private String[] method2(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder(); // current word
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (sb.length() != 0) {
                    result.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else if (Character.isLetter(c)) {
                sb.append(c);
            } else {
                // symbols
                if (sb.length() != 0) {
                    result.add(sb.toString());
                    sb = new StringBuilder();
                }
                result.add(String.valueOf(c));
            }
        }
        if (sb.length() != 0) {
            result.add(sb.toString());
        }
        return result.toArray(new String[0]);
    }

    private String[] mytry(String s) {
        StringBuilder result = new StringBuilder(); // record final result
        StringBuilder sb = new StringBuilder(); // record words
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            } else {
                // it's not a letter
                if (sb.length() != 0) {
                    // if we catched a words
                    result.append(sb);
                    sb = new StringBuilder();
                    result.append(" "); // append a space
                }
                // if it's not a space, do the same as appending words
                if (c != ' ') {
                    result.append(c);
                    result.append(" ");
                }
            }
        }

        if (sb.length() != 0) {
            result.append(sb);
        }
        // corner case: given stringis "    "
        if (result.length() == 0) {
            return new String[0];
        } else {
            return result.toString().trim().split(" ");
        }
    }
}

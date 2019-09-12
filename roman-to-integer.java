// 419. Roman to Integer
// 中文English
// Given a roman numeral, convert it to an integer.
//
// The answer is guaranteed to be within the range from 1 to 3999.
//
// Example
// Example 1:
//
// Input: "IV"
// Output: 4
// Example 2:
//
// Input: "XCIX"
// Output: 99
// Clarification
// What is Roman Numeral?
//
// https://en.wikipedia.org/wiki/Roman_numerals
// https://zh.wikipedia.org/wiki/罗马数字
// http://baike.baidu.com/view/42061.htm


public class Solution {
    /**
     * @param s Roman representation
     * @return an integer
     */
    public int romanToInt(String s) {
        // Write your code here
        if (s == null) {
            return -1;
        }
        Map<Character, Integer> hash = new HashMap<>();
        hash.put('I', 1);
        hash.put('V', 5);
        hash.put('X', 10);
        hash.put('L', 50);
        hash.put('C', 100);
        hash.put('D', 500);
        hash.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (hash.get(s.charAt(i)) < hash.get(s.charAt(i + 1))) {
                result -= hash.get(s.charAt(i));
            } else {
                result += hash.get(s.charAt(i));
            }
        }
        result += hash.get(s.charAt(s.length() - 1));
        return result;
    }
}

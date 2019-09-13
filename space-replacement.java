// 212. Space Replacement
// 中文English
// Write a method to replace all spaces in a string with %20. The string is given in a characters array, you can assume it has enough space for replacement and you are given the true length of the string.
//
// You code should also return the new length of the string after replacement.
//
// Example
// Example 1:
//
// Input: string[] = "Mr John Smith" and length = 13
// Output: string[] = "Mr%20John%20Smith" and return 17
// Explanation:
// The string after replacement should be "Mr%20John%20Smith", you need to change the string in-place and return the new length 17.
// Example 2:
//
// Input: string[] = "LintCode and Jiuzhang" and length = 21
// Output: string[] = "LintCode%20and%20Jiuzhang" and return 25
// Explanation:
// The string after replacement should be "LintCode%20and%20Jiuzhang", you need to change the string in-place and return the new length 25.
// Challenge
// Do it in-place.
//
// Notice
// If you are using Java or Python，please use characters array instead of string.
//


public class Solution {
    /**
     * @param string: An array of Char
     * @param length: The true length of the string
     * @return: The true length of new string
     */
    public int replaceBlank(char[] string, int length) {
        // Write your code here

        // 感觉就是找 space 的个数， 但是别忘了同时还要更改原 string
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (string[i] == ' ') {
                count++;
            }
        }
        int n = length + count * 2; // new length of string after replacing
        // 原数组提供了足够的位置， 但是要在原数组 in-place 的去改动，e.g.
        // ___________________________________
        // |M|r| |J|o|h|n| |S|m|i|t|h| | | | |
        // -----------------------------------
        // 那就要从后往前遍历， 类似于插入排序
        // 一根指针遍历原有 valid string， 另一根从数组的最末尾开始覆盖
        int j = n - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (string[i] == ' ') {
                string[j--] = '0';
                string[j--] = '2';
                string[j--] = '%';
            } else {
                string[j--] = string[i];
            }
        }
        return n;
    }
}

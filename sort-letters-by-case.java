// 49. Sort Letters by Case
// 中文English
// Given a string which contains only letters. Sort it by lower case first and upper case second.
//
// Example
// Example 1:
// 	Input:  "abAcD"
// 	Output:  "acbAD"
//
// Example 2:
// 	Input: "ABC"
// 	Output:  "ABC"
//
// Challenge
// Do it in one-pass and in-place.
//
// Notice
// It's NOT necessary to keep the original order of lower-case letters and upper case letters.
//


public class Solution {
    /*
     * @param chars: The letter array you should sort by Case
     * @return: nothing
     */
    public void sortLetters(char[] chars) {
        // write your code here
        if (chars == null || chars.length == 0) {
            return;
        }
        int n = chars.length;
        // my try: 2 pointers
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            // 注意1: 这里不能相等， 错误例子：DERLKAJKFKLAJFKAKLFJKLJFa， 因为如果相等的话， 第一次交换过后变成“aERLKAJKFKLAJFKAKLFJKLJFD”， 则 right 指针在 E 的位置上时依然满足条件， 然后指向 a 退出， 交换 a 与 E
            // 如果要写成 <= 那么就在 swap 前加一个 if 判断， 同样是 <=
            // while (left <= right && isLower(chars[left])) {
            //     left++;
            // }
            // while (left <= right && !isLower(chars[right])) {
            //     right--;
            // }
            while (left <= right && Character.isLowerCase(chars[left])) {
                left++;
            }
            while (left <= right && Character.isUpperCase(chars[right])) {
                right--;
            }
            if (left <= right) {
                swap(chars, left, right);
                left++;
                right--;
            }
        }
    }
    private boolean isLower(char c) {
        // 此方法也可以直接用 Character.isLowerCase/isUpperCase 代替
        if (c - 'A' < 26) {
            // 注意2: 大写在先， 小写在后
            return false;
        } else {
            return true;
        }
    }
    private void swap(char[] chars, int left, int right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
    }
}

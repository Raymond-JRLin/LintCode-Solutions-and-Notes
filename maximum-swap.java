// 1095. Maximum Swap
// Description
// Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
//
// The given number is in the range [0, 10^8]
// Have you met this question in a real interview?
// Example
// Example 1:
//
// Input: 2736
// Output: 7236
// Explanation: Swap the number 2 and the number 7.
// Example 2:
//
// Input: 9973
// Output: 9973
// Explanation: No swap.


public class Solution {
    /**
     * @param num: a non-negative intege
     * @return: the maximum valued number
     */
    public int maximumSwap(int num) {
        // Write your code here

        // return mytry(num);

        return method2(num);
    }

    private int method2(int num) {
        // backward traverse: O(N) time and O(1) space
        // We will scan the number from backward direction. In the scan, if the ith digit is the largest by far, store it and its index or if the current digit is smaller than the largest digit recorded by far, this digit and the largest digit are the best suitable for swap.
        // 从后往前找， 遇到一个大的数字， 就记录为当前最大的， 它之前的更小的值都可以是潜在交换的， 而如果遇到更大的数， 那么就直接更新为当前最大的， 因为如果在更前面遇到小的值， 两个大的都可以交换的条件下， 用更大的交换能够得到更大的数
        char[] array = String.valueOf(num).toCharArray();
        int n = array.length;
        int left = -1;
        int right = -1;
        int maxDigit = -1;
        int maxIndex = -1;
        for (int i = n - 1; i >= 0; i--) {
            int curr = array[i] - '0';
            if (curr > maxDigit) {
                maxDigit = curr;
                maxIndex = i;
            } else if (curr < maxDigit) {
                left = i;
                right = maxIndex;
            }
        }
        if (left == -1) {
            // no need to swap
            return num;
        }
        swap(array, left, right);
        return Integer.parseInt(new String(array));
    }

    private int mytry(int num) {
        // brute force: O(N ^ 2) time
        char[] array = String.valueOf(num).toCharArray();
        int n = array.length;
        int result = num;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[i]) {
                    continue;
                }
                swap(array, i, j);
                int after = Integer.parseInt(new String(array));
                if (after > result) {
                    result = after;
                }
                swap(array, i, j);
            }
        }
        return result;
    }
    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

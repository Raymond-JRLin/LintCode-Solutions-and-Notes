// 604. Window Sum
// 中文English
// Given an array of n integers, and a moving window(size k), move the window at each iteration from the start of the array, find the sum of the element inside the window at each moving.
//
// Example
// Example 1
//
// Input：array = [1,2,7,8,5], k = 3
// Output：[10,17,20]
// Explanation：
// 1 + 2 + 7 = 10
// 2 + 7 + 8 = 17
// 7 + 8 + 5 = 20


public class Solution {
    /**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0 || k < 1) {
            return new int[0];
        }


        // return method(nums, k);

        // 2018.02.09
        return mytry(nums, k);
    }

    private int[] method(int[] nums, int k) {
        int end = nums.length - k;
        int[] array = new int[end + 1];
        // for (int i = 0; i <= end; i++) {
        //     int sum = 0;
        //     for (int j = 0; j < k; j++) {
        //         sum = sum + nums[i + j];
        //     }
        //     array[i] = sum;
        // }
        // return array;
        int length = nums.length - k + 1;
        int[] sums = new int[length];
        for (int i = 0; i < k; i++) {
            sums[0] += nums[i];
        }
        for (int i = 1; i < length; i++) {
            sums[i] = sums[i - 1] - nums[i - 1] + nums[i + k - 1];
        }
        return sums;
    }

    private int[] mytry(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index = 0;
        for (int i = 0; i < k; i++) {
            result[index] += nums[i];
        }
        index++;
        int j = 0;
        for (int i = k; i < n; i++) {
            result[index] = result[index - 1] - nums[j++] + nums[i];
            index++;
        }
        return result;
    }
}

// 373. Partition Array by Odd and Even
// 中文English
// Partition an integers array into odd number first and even number second.
//
// Example
// Example 1:
//
// Input: [1,2,3,4]
// Output: [1,3,2,4]
// Example 2:
//
// Input: [1,4,2,3,5,6]
// Output: [1,3,5,4,2,6]
// Challenge
// Do it in-place.
//
// Notice
// The answer is not unique. All you have to do is give a vaild answer.
//


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        // write your code here;
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;

        // method 1: O(n) time and O(n) space
        // int[] result = new int[n];
        // int odd = 0;
        // int even = n - 1;
        // for (int num : nums) {
        //     if (num % 2 == 1) {
        //         result[odd] = num;
        //         odd++;
        //     } else {
        //         result[even] = num;
        //         even--;
        //     }
        // }
        // for (int i = 0; i < n; i++) {
        //     nums[i] = result[i];
        // }

        // method 2: O(n) time and O(1) in-place space: quick sort
        int left = 0;
        int right = n - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}

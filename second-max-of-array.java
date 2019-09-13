// 479. Second Max of Array
// 中文English
// Find the second max number in a given array.
//
// Example
// Example1:
//
// Input: [1,3,2,4]
// Output: 3
// Example2:
//
// Input: [1,1,2,2]
// Output: 2
// Notice
// You can assume the array contains at least two numbers.
// The second max number is the second number in a descending array.
//


public class Solution {
    /**
     * @param nums: An integer array.
     * @return: The second max number in the array.
     */
    public int secondMax(int[] nums) {
        /* your code */
        // sort
        // Arrays.sort(nums);
        // return nums[nums.length - 2];

        // for traverse
        int max = nums[0];
        int second = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                second = max;
                max = nums[i];
            } else {
                second = Math.max(second, nums[i]);
            }
        }
        return second;
    }
}

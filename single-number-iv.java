// 824. Single Number IV
// 中文English
// Give an array, all the numbers appear twice except one number which appears once and all the numbers which appear twice are next to each other. Find the number which appears once.
//
// Example
// Example 1:
//
// Input: [3,3,2,2,4,5,5]
// Output: 4
// Explanation: 4 appears only once.
// Example 2:
//
// Input: [2,1,1,3,3]
// Output: 2
// Explanation: 2 appears only once.
// Notice
// 1 <= nums.length < 10^4
// In order to limit the time complexity of the program, your program will run 10^5 times.


public class Solution {
    /**
     * @param nums: The number array
     * @return: Return the single number
     */
    public int getSingleNumber(int[] nums) {
        // Write your code here

        // return mytry(nums);

        return method2(nums);
    }

    private int method2(int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[mid - 1]) {
                if ((mid - start + 1) % 2 == 1) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] == nums[mid + 1]) {
                if ((mid - start) % 2 == 1) {
                    end = mid - 1;
                } else {
                    start = mid;
                }
            } else {
                return nums[mid];
            }
        }
        if (nums[start] != nums[start - 1] && nums[start] != nums[start + 1]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }

    private int mytry(int[] nums) {
        // O(N) time
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}

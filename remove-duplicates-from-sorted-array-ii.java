// 101. Remove Duplicates from Sorted Array II
// 中文English
// Given a sorted array, remove the duplicates in place such that each element appear at most twice and return the new length.
// If a number appears more than two times, then keep the number appears twice in array after remove.
//
// Example
// Example 1:
// 	Input: []
// 	Output: 0
//
//
// Example 2:
// 	Input:  [1,1,1,2,2,3]
// 	Output: 5
//
// 	Explanation:
// 	the length is 5: [1,1,2,2,3]
//
// Notice
// Need to operate in the original array
//


public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 1;
        int duplicates = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                duplicates++;
                if (duplicates <= 2) {
                    nums[index] = nums[i];
                    index++;
                }

            } else {
                nums[index] = nums[i];
                index++;
                duplicates = 1;
            }
        }
        return index;
    }
}

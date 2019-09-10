// 196. Missing Number
// 中文English
// Given an array contains N numbers of 0 .. N, find which number doesn't exist in the array.
//
// Example
// Example 1:
//
// Input:[0,1,3]
// Output:2
// Example 2:
//
// Input:[1,2,3]
// Output:0
// Challenge
// Do it in-place with O(1) extra memory and O(n) time.
//


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int findMissing(int[] nums) {
        // write your code here

        // method 1: based on sorting
        // Arrays.sort(nums);
        // int n = nums.length;
        // if (nums[0] != 0) {
        //     return 0;
        // }
        // if (nums[n - 1] != n) {
        //     return n;
        // }
        // for (int i = 0; i < n - 1; i++) {
        //     if (nums[i] + 1 != nums[i + 1]) {
        //         return nums[i] + 1;
        //     }
        // }
        // return -1;

        // method 2
        // from challenge, we can have some clues: we can only traverse this array from left to right and swap 2 numbers which are in wrong place
        // so we can traverse and swap element with value of i to position with index of i
        int i = 0;
        int n = nums.length;
        while (i < n) {
            while (nums[i] != i && nums[i] < n) {
                // nums[i] < n 这个条件是为了， 譬如 [0, 1, 3]， 最后一位数字存在， 但是中间 miss 了一位， 而 index 取不到最后一位数字
                // swap until one of them (i, temp) have their same value with index
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
            i++;
        }
        // final check
        for (i = 0; i < n; i++) {
            if (nums[i] != i) {
                // the value in index i is not i, then this i is missing
                return i;
            }
        }
        // check last index
        return n;
    }
}

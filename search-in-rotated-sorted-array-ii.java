// 63. Search in Rotated Sorted Array II
// 中文English
// Follow up for Search in Rotated Sorted Array:
//
// What if duplicates are allowed?
//
// Would this affect the run-time complexity? How and why?
//
// Write a function to determine if a given target is in the array.
//
// Example
// Example 1:
//
// Input:
// []
// 1
// Output:
// false
// Example 2:
//
// Input:
// [3,4,4,5,7,0,1,2]
// 4
// Output:
// true


public class Solution {
    /**
     * param A : an integer ratated sorted array and duplicates are allowed
     * param target :  an integer to be search
     * return : a boolean
     */
    public boolean search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return false;
        }
        int n = A.length;
        int drop = 0;
        while (A[drop] <= A[drop + 1]) {
            drop++;
            if (drop == n - 1) {
                // edge case: the last one is rotated and our target
                return A[drop] == target || bs(A, target, 0, drop);
            }
        }
        return bs(A, target, 0, drop) || bs(A, target, drop + 1, n - 1);
    }
    private boolean bs(int[] nums, int target, int start, int end) {
        if (start == end) {
            return nums[start] == target;
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }
        return false;
    }
}

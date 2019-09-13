// 61. Search for a Range
// 中文English
// Given a sorted array of n integers, find the starting and ending position of a given target value.
//
// If the target is not found in the array, return [-1, -1].
//
// Example
// Example 1:
//
// Input:
// []
// 9
// Output:
// [-1,-1]
//
// Example 2:
//
// Input:
// [5, 7, 7, 8, 8, 10]
// 8
// Output:
// [3, 4]
// Challenge
// O(log n) time.


public class Solution {
    /**
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        // write your code here
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (A == null || A.length == 0) {
            return result;
        }

        int startIndex = -1;
        int endIndex = -1;
        int n = A.length;
        startIndex = startIndex(A, 0, n - 1, target);
        endIndex = endIndex(A, 0, n - 1, target);
        result[0] = startIndex;
        result[1] = endIndex;
        return result;
    }
    private int startIndex(int[] nums, int left, int right, int target) {
        int start = left;
        int end = right;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        } else {
            return -1;
        }
    }
    private int endIndex(int[] nums, int left, int right, int target) {
        int start = left;
        int end = right;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            return end;
        } else if (nums[start] == target) {
            return start;
        } else {
            return -1;
        }
    }
}

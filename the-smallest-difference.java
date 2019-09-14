// 387. The Smallest Difference
// 中文English
// Given two array of integers(the first array is array A, the second array is array B), now we are going to find a element in array A which is A[i], and another element in array B which is B[j], so that the difference between A[i] and B[j] (|A[i] - B[j]|) is as small as possible, return their smallest difference.
//
// Example
// Example 1:
//
// Input: A = [3, 6, 7, 4], B = [2, 8, 9, 3]
// Output: 0
// Explanation: A[0] - B[3] = 0
// Example 2:
//
// Input: A = [1, 2, 3, 4], B = [7, 6, 5]
// Output: 1
// Explanation: B[2] - A[3] = 1
// Challenge
// O(n log n) time
//


public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        // write your code here
        int n = A.length;
        int m = B.length;

        // method 1: brute, passed 85% TLE
        // int diff = Integer.MAX_VALUE;
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         if (Math.abs(A[i] - B[j]) < diff) {
        //             diff = Math.abs(A[i] - B[j]);
        //         }
        //     }
        // }
        // return diff;

        // method 2: use binary search
        // loop shorter array and do binary search on longer array
        // if (n > m) {
        //     Arrays.sort(A);
        //     return helper(B, A);
        // } else {
        //     Arrays.sort(B);
        //     return helper(A, B);
        // }

        // method 3: 2 pointers
        Arrays.sort(A);
        Arrays.sort(B);
        int diff = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            diff = Math.min(diff, Math.abs(A[i] - B[j]));
            if (diff == 0) {
                return 0;
            }
            if (A[i] > B[j]) {
                j++;
            } else {
                i++;
            }
        }
        return diff;
    }
    private int helper(int[] nums1, int[] nums2) {
        // nums1 is smaller array and nums2 is the larger one
        int n = nums1.length;
        int m = nums2.length;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int target = nums1[i];
            result = Math.min(result, bs(nums2, target));
            if (result == 0) {
                return 0;
            }
        }
        return result;
    }
    private int bs(int[] nums, int target) {
        // use Binary Search to find the min diff
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return 0;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return Math.min(Math.abs(nums[start] - target), Math.abs(nums[end] - target));
    }
}

// 547. Intersection of Two Arrays
// 中文English
// Given two arrays, write a function to compute their intersection.
//
// Example
// Example 1:
//
// Input: nums1 = [1, 2, 2, 1], nums2 = [2, 2],
// Output: [2].
// Example 2:
//
// Input: nums1 = [1, 2], nums2 = [2],
// Output: [2].
// Challenge
// Can you implement it in three different algorithms?
//
// Notice
// Each element in the result must be unique.
// The order of the results needs to be ascending


public class Solution {
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // Write your code here
        if (nums1 == null || nums2 == null) {
            return null;
        }
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        // merge sort
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int k = 0;
        int len = nums1.length <= nums2.length ? nums1.length : nums2.length;
        int[] temp = new int[len];
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (k == 0 || temp[k - 1] != nums1[i]) {
                    temp[k++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        // temp may have empty positions
        int[] results = new int[k];
        for (int index = 0; index < k; index++) {
            results[index] = temp[index];
        }
        return results;
    }

}

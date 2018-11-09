// 548. Intersection of Two Arrays II
// Given two arrays, write a function to compute their intersection.
//
// Example
// Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
//
// Challenge
// What if the given array is already sorted? How would you optimize your algorithm?
// What if nums1's size is small compared to num2's size? Which algorithm is better?
// What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
// Notice
// Each element in the result should appear as many times as it shows in both arrays.
// The result can be in any order.


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

        return method1(nums1, nums2);
    }

    private int[] method1(int[] nums1, int[] nums2) {
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
                temp[k++] = nums1[i];
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

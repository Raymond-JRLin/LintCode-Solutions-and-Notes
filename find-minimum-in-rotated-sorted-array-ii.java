// 160. Find Minimum in Rotated Sorted Array II
// 中文English
// Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
// Find the minimum element.
//
// Example
// Example 1:
//
// Input :[2,1]
// Output : 1.
// Example 2:
//
// Input :[4,4,5,6,7,0,1,2]
// Output : 0.
// Notice
// The array may contain duplicates.
//


public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        if (num == null || num.length == 0) {
            return -1;
        }
        int start = 0;
        int end = num.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (num[mid] == num[end]) {
                end = end - 1;
            } else if (num[mid] < num[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return Math.min(num[start], num[end]);
    }
}

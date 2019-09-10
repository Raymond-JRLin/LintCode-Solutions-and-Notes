// 6. Merge Two Sorted Arrays
// 中文English
// Merge two given sorted ascending integer array A and B into a new sorted integer array.
//
// Example
// Example 1:
//
// Input:  A=[1], B=[1]
// Output: [1,1]
// Explanation:  return array merged.
// Example 2:
//
// Input:  A=[1,2,3,4], B=[2,4,5,6]
// Output: [1,2,2,3,4,4,5,6]
// Explanation: return array merged.
// Challenge
// How can you optimize your algorithm if one array is very large and the other is very small?
//


class Solution {
    /**
     * @param A and B: sorted integer array A and B.
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // Write your code here
        int len = A.length + B.length;
        int[] results = new int[len];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < A.length && j < B.length) {
            // if (A[i] < B[j]) {
            //     results[k++] = A[i++];
            // } else {
            //     results[k++] = B[j++];
            // }
            results[k++] = A[i] < B[j] ? A[i++] : B[j++];
        }
        while (i < A.length) {
            results[k++] = A[i++];
        }
        while (j < B.length) {
            results[k++] = B[j++];
        }
        return results;
    }
}

// 64. Merge Sorted Array
// 中文English
// Given two sorted integer arrays A and B, merge B into A as one sorted array.
//
// Example
// Example 1:
//
// Input：[1, 2, 3] 3  [4,5]  2
// Output：[1,2,3,4,5]
// Explanation:
// After merge, A will be filled as [1, 2, 3, 4, 5]
// Example 2:
//
// Input：[1,2,5] 3 [3,4] 2
// Output：[1,2,3,4,5]
// Explanation:
// After merge, A will be filled as [1, 2, 3, 4, 5]
// Notice
// You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
//


class Solution {
    /**
     * @param A: sorted integer array A which has m elements,
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        // to avoid to move the whole array backward everytime, we can compare the bigger one, and make bigger one be the latter position
        // pay attention to: 1- m and n is length not index, 2- one array is shorter so when we are done to put this array, don't forget to deal with another one
        int position = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            // A is the longer array

            // A[position] = A[i] > B[j] ? A[i] : B[j];
            // position--;
            // i--;
            // j--;
            // above is wrong because everytime, we only put one elements, and move pointer of such array but stay another pointer in another array
            // if (A[i] >= B[j]) {
            //     A[position] = A[i];
            //     position--;
            //     i--;
            // } else {
            //     A[position] = B[j];
            //     position--;
            //     j--;
            // }

            A[position--] = A[i] > B[j] ? A[i--] : B[j--];
        }
        while (i >= 0) {
            A[position--] = A[i--];
        }
        // we also need to consider if we reach the head of B - the shorter arrar, because even though it's size is smaller but the elements can be smaller too, then when i reach the head of longer array but j will still stay in the shorter array, thus we need to arrange rest of array B
        while (j >= 0 ) {
            A[position--] = B[j--];
        }
    }
}

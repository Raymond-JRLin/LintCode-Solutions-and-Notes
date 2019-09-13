// 463. Sort Integers
// 中文English
// Given an integer array, sort it in ascending order. Use selection sort, bubble sort, insertion sort or any O(n2) algorithm.
//
// Example
// Example 1:
// 	Input:  [3, 2, 1, 4, 5]
// 	Output: [1, 2, 3, 4, 5]
//
// 	Explanation:
// 	return the sorted array.
//
// Example 2:
// 	Input:  [1, 1, 2, 1, 1]
// 	Output: [1, 1, 1, 1, 2]
//
// 	Explanation:
// 	return the sorted array.
//

public class Solution {
    /**
     * @param A an integer array
     * @return void
     */
    public void sortIntegers(int[] A) {
        // Write your code here
        if (A == null || A.length == 0) {
            return;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                if (A[j] < A[i]) {
                    int temp = A[j];
                    A[j] = A[i];
                    A[i] = temp;
                }
            }
        }
    }
}

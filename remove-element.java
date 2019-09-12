// 172. Remove Element
// 中文English
// Given an array and a value, remove all occurrences of that value in place and return the new length.
//
// The order of elements can be changed, and the elements after the new length don't matter.
//
// Example
// Example 1:
// 	Input: [], value = 0
// 	Output: 0
//
//
// Example 2:
// 	Input:  [0,4,4,0,0,2,4,4], value = 4
// 	Output: 4
//
// 	Explanation:
// 	the array after remove is [0,0,0,2]


public class Solution {
    /**
     *@param A: A list of integers
     *@param elem: An integer
     *@return: The new length after remove
     */
    public int removeElement(int[] A, int elem) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem) {
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }
}

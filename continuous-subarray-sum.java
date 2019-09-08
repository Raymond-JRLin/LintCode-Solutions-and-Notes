// 402. Continuous Subarray Sum
// 中文English
// Given an integer array, find a continuous subarray where the sum of numbers is the biggest. Your code should return the index of the first number and the index of the last number. (If their are duplicate answer, return the minimum one in lexicographical order)
//
// Example
// Example 1:
//
// Input: [-3, 1, 3, -3, 4]
// Output: [1, 4]
// Example 2:
//
// Input: [0, 1, 0, 1]
// Output: [0, 3]
// Explanation: The minimum one in lexicographical order.


public class Solution {
    /**
     * @param A an integer array
     * @return  A list of integers includes the index of the first number and the index of the last number
     */
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        // Write your code here
        if (A == null || A.length == 0) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        // global index
        int start = 0;
        int end = 0;
        // local index
        int left = 0;
        int right = 0;
        // for (int i = 0; i < A.length; i++) {
        //     if (sum < 0) {
        //         sum = A[i];
        //         left = i;
        //         right = i;
        //     } else {
        //         sum += A[i];
        //         right = i;
        //     }
        //     if (max < sum) {
        //         max = sum;
        //         start = left;
        //         end = right;
        //     }
        // }
        // result.add(start);
        // result.add(end);
        // return result;

        int minSum = 0;
        for (int i = 0; i < A.length; i++) {
            if (sum < minSum) {
                minSum = sum;
                left = i;
            }
            sum += A[i];
            if (sum - minSum > max) {
                max = sum - minSum;
                end = i;
                if (left <= end) {
                    start = left;
                }
            }
        }
        result.add(start);
        result.add(end);
        return result;
    }
}

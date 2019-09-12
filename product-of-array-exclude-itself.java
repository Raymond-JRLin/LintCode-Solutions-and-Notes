// 50. Product of Array Exclude Itself
// 中文English
// Given an integers array A.
//
// Define B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1], calculate B WITHOUT divide operation.Out put B
//
// Example
// Example 1
//
// Input: A = [1, 2, 3]
// Output: [6, 3, 2]
// Explanation：B[0] = A[1] * A[2] = 6; B[1] = A[0] * A[2] = 3; B[2] = A[0] * A[1] = 2
// Example 2
//
// Input: A = [2, 4, 6]
// Output: [24, 12, 8]


public class Solution {
    /*
     * @param : Given an integers array A
     * @return: A long long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public List<Long> productExcludeItself(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() == 0) {
            return null;
        }
        int n = nums.size();
        List<Long> result = new ArrayList<Long>();
        if (n == 1) {
            result.add(1L);
            return result;
        }
        // method 1: my solution, O(n^2) time
        // for (int i = 0; i < n; i++) {
        //     long product = 1;
        //     // 向右方向展开
        //     int right = i + 1;
        //     while (right < n) {
        //         product *= nums.get(right);
        //         right++;
        //     }
        //     // 向左方向展开
        //     int left = i - 1;
        //     while (left >= 0) {
        //         product *= nums.get(left);
        //         left--;
        //     }
        //     result.add(product);
        // }
        // return result;

        // method 2: referenced from answer - forward-backward traversal, O(n) time and O(n) space
        // in method 1, we traverse to left and right, so we can do a pre-process to calculate production from left and right, and then use results directly
        // forward
        long[] left = new long[n];
        left[0] = nums.get(0);
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums.get(i);
        }
        // backward
        long[] right = new long[n];
        right[n - 1] = nums.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums.get(i);
        }
        // get result
        result.add(right[1]);
        for (int i = 1; i < n - 1; i++) {
            result.add(left[i - 1] * right[i + 1]);
        }
        result.add(left[n - 2]);
        return result;
    }
};

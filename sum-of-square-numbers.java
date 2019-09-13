// 697. Sum of Square Numbers
// 中文English
// Given a integer c, your task is to decide whether there're two integers a and b such that a^2 + b^2 = c.
//
// Example
// Example 1:
//
// Input : n = 5
// Output : true
// Explanation :
// 1 * 1 + 2 * 2 = 5
// Example 2:
//
// Input : n = -5
// Output : false


public class Solution {
    /*
     * @param : the given number
     * @return: whether whether there're two integers
     */
    public boolean checkSumOfSquareNumbers(int num) {
        // write your code here
        if (num < 0) {
            return false;
        }
        if (num == 0) {
            return true;
        }
        for (int i = 0; i <= Math.sqrt(num); i++) {
            int target = num - i * i;
            if (isInt(target)) {
                return true;
            }
        }
        return false;
    }
    private boolean isInt(int num) {
        double root = Math.sqrt(num);
        if (root - (int) root == 0.0) {
            return true;
        }
        return false;
    }
    // private boolean bs(int target) {
    //     int start = 0;
    //     int end = target;
    //     while (start + 1 < end) {
    //         int mid = start + (end - start) / 2;
    //         int result = mid * mid;
    //         if (result == target) {
    //             return true;
    //         } else if (result < target) {
    //             start = mid;
    //         } else {
    //             end = mid;
    //         }
    //     }
    //     if (start * start == target) {
    //         return true;
    //     }
    //     if (end * end == target) {
    //         return true;
    //     }
    //     return false;

    // }
};

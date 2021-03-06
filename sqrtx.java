// 141. Sqrt(x)
// 中文English
// Implement int sqrt(int x).
//
// Compute and return the square root of x.
//
// Example
// Example 1:
// 	Input:  0
// 	Output: 0
//
//
// Example 2:
// 	Input:  3
// 	Output: 1
//
// 	Explanation:
// 	return the largest integer y that y*y <= x.
//
// Example 3:
// 	Input:  4
// 	Output: 2
//
//
// Challenge
// O(log(x))
//


class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        if (x == 0) {
            return 0;
        }
        long start = 1;
        long end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (start * start <= x) {
            return (int) start;
        }
        if (end * end <= x) {
            return (int) end;
        }
        return -1;
    }
}

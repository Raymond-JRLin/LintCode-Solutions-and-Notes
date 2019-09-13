// 658. Swap Without Extra Variable (Only C++)
// 中文English
// Given two variables, x and y, swap two variables without using a third variable.
//
// Example
// Example 1:
//
// Input :  x = 10, y = 5
// Output : x = 5, y = 10


class Solution {
public:
    /**
     * @param x an integer
     * @param y an integer
     * @return nothing
     */
    void swap(int &x, int &y) {
        // Write your code here
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
    }
};

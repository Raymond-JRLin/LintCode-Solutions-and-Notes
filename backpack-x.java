// 801. Backpack X
// 中文English
// You have a total of n yuan. Merchant has three merchandises and their prices are 150 yuan, 250 yuan and 350 yuan. And the number of these merchandises can be considered as infinite. After the purchase of goods need to be the remaining money to the businessman as a tip, finding the minimum tip for the merchant.
//
// Example
// Example 1:
//
// Input:  n = 900
// Output:  0
// Example 2:
//
// Input:  n = 800
// Output:  0


public class Solution {
    /**
     * @param n: the money you have
     * @return: the minimum money you have to give
     */
    public int backPackX(int n) {
        // write your code here
        if (n < 0) {
            return 0;
        } else if (n <= 150) {
            return 150 - n;
        } else if (n > 150 && n <= 250) {
            return 250 - n;
        } else {
            return n % 50;
        }
    }
}

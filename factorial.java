// 690. Factorial
// 中文English
// Given a number n, return the factorial of the number as a string.
//
// Example
// Example 1
//
// Input: 0
// Output: "1"
// Example 2
//
// Input: 20
// Output: "2432902008176640000"


public class Solution {
    /*
     * @param : an integer
     * @return:  the factorial of n
     */
    public String factorial(int n) {
        // write your code here
        if (n == 0) {
            return "1";
        }
        if (n == 1 || n == 2) {
            return String.valueOf(n);
        }

        return mytry(n);
    }

    private String mytry(int n) {
        int[] nums = new int[10000];
        nums[0] = 1;
        int size = 1;
        for (int i = 2; i <= n; i++) {
            size = multiply(i, nums, size);
        }
        String result = "";
        for (int i = size - 1; i >= 0; i--) {
            result += String.valueOf(nums[i]);
        }
        return result;
    }
    private int multiply(int num, int[] nums, int size) {

        int carry = 0;
        for (int i = 0; i < size; i++) {
            int product = nums[i] * num + carry;
            nums[i] = product % 10;
            carry = product / 10;
        }
        while (carry != 0) {
            nums[size] = carry % 10;
            carry /= 10;
            size++;
        }
        return size;
    }
}

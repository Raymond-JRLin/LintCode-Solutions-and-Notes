// 37. Reverse 3-digit Integer
// Description
// Reverse a 3-digit integer.
//
// You may assume the given number is larger or equal to 100 but smaller than 1000.
//
// Have you met this question in a real interview?
// Example
// Reverse 123 you will get 321.
//
// Reverse 900 you will get 9.


public class Solution {
    /**
     * @param number: A 3-digit number.
     * @return: Reversed number.
     */
    public int reverseInteger(int number) {
        // write your code here

        // return mytry(number);

        return method2(number);
    }

    private int method2(int num) {
        // 直接用 / and % 来处理， 就无需考虑 0 是否要算进去， 因为乘起来的话就还是 0
        int digit1 = num % 10; // 个位数
        int digit2 = (num / 10) % 10; // 十位数
        int digit3 = (num / 100) % 10; // 百位数
        return digit1 * 100 + digit2 * 10 + digit3;
    }

    private int mytry(int num) {
        String s = new StringBuilder(String.valueOf(num)).reverse().toString();
        int i = 0;
        while (s.charAt(i) == '0') {
            i++;
        }
        return Integer.parseInt(s.substring(i, s.length()));
    }
}

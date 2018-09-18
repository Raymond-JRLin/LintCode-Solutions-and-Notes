// 1570. Binary Stream
// Description
// Take each bit from a binary stream (0/1) to form a binary string. The i-th time can take consecutive i-bits from the stream. Each time a bit is taken out, the binary string consisting of the previous bits is shifted to the highest position by one bit, and then the current bit is added. When j bits (j<=i) are taken, if the value of the current binary string is divisible by 3, then output it.
//
// The length of the binary stream is less than or equal to 200000200000
// Have you met this question in a real interview?
// Example
// Give a = "11011"``, return [2,3,5]。
//
// Explanation:
// When taking 2 bits, the binary string is 11, and after converting to decimal, it is 3, which can be divisible by 3.
// When taking 3 bits, the binary string is 110, and after converting to decimal, it is 6, which can be divisible by 3.
// When taking 5 bits, the binary string is 11011, and after converting to decimal, it is 27, which can be divisible by 3.
// Give a ="0000"`` , return [1,2,3,4]。
//
// Explanation:
// No matter how many bits are taken, they are all 0, so all output.


public class Solution {
    /**
     * @param s: the binary stream
     * @return: the outputs
     */
    public int[] getOutput(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return new int[0];
        }

        return mytry(s);
    }

    private int[] mytry(String s) {
        // 想说题意不是很清楚诶 都理解错了 =。=
        // 实际上就是从左到右依次拿 i 个， 转成 10 进制看是否能被 3 整除
        int n = s.length();
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 模拟这个过程， * 2 => 向左移位
            sum = sum * 2 + (s.charAt(i) - '0');
            sum %= 3; // trick: 每次 % 3 就不会让结果变得非常大
            if (sum == 0) {
                list.add(i + 1);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}

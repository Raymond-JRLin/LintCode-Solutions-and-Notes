// 1640. Duplicates Digits
// Description
// Given an integer n, return the number of numbers that have duplicates digits.
//
// n does not exceed 1e181e18
//
// Have you met this question in a real interview?
// Example
// Give n=21, return 1.
//
// Explanation:
// Only 11 has duplicates digits.
// Give n=100, return 10
//
// Explanation:
// 11, 22, 33, 44, 55, 66, 77, 88, 99, 100 have duplicates digits.


public class Solution {
    /**
     * @param n: The integer n.
     * @return: The number of of numbers that have duplicates digits.
     */
    public long countNumbers(long n) {
        // Write your code here.
        if (n >= 0 && n <= 1) {
            return 0L;
        }

        return method1(n);
    }

    private long method1(long n) {
        // similar to LeetCode 357. Count Numbers with Unique Digits
        // 有两个不同： 上面这题是找 unique 的， 而这题找有重复 digit 的； 还有一个很重要是上面这题给的 input 是这个数有几位数， 而这里是范围的上限
        // 所以可以先求这个数关于出 10 的最高幂次以内的 unique， 然后固定高位次， 分别去找每个低位次的 unique。 譬如说， 123， 先求出 100 以内的 unique， 然后查看 '1', 都是要选比它小的数， 那么当前位次可选的只有 1 个数， 即 0， 但是因为是第一高位， 所以要减去 0 这个选项， 那么就没有更多的 unique了； 接着查看 '2'， 可选的有 0 & 1, 1 已经在第一高位了， 所以不能选， 即减去之前出现过的数， 那么我们就可以固定更高位次的数， 此时有 8 个 unique， 即 102-109； 接着查看 '3', 可选的有3个数 0 & 1 & 2， 需要固定前面的高位， 所以 1 2 都不能选， 此时只有 1 个 unique， 即 120
        // 在计算不同位次的 unique 的时候， 也是用相同的计算全排列的方法， 但是有几点注意：
        // 1. 采取固定高位次的方法， 需要减掉之前出现过的所有的数， 使得 availability 变少
        // 2. 得到的 char 就可以作为可选的数的个数， 个数是 base 1， 而数是 base 0 的。 这个可选是指放在当前这个位次的数， 之后的依然按照全排列以 availability 表示
        int curr = 9;
        int available = 9;
        long unique = 10L;
        String s = String.valueOf(n);
        for (int i = 1; i < s.length() - 1 && i <= 10; i++) {
            // 注意这里 length - 1， 因为这里算的是 10 的幂次， 即 0 的个数
            curr = curr * available;
            unique += curr;
            available--;
        }
        // System.out.println("we have " + unique + " unique nums");

        if (s.length() <= 10) {
            // 和计算 unique 一样， 超出 10 ^ 10 不可能有 unique 的了
            Set<Integer> visited = new HashSet<>(); // 记录出现过的数

            for (int i = 0; i < s.length(); i++) {
                // 查看每一位次
                int num = s.charAt(i) - '0'; // 当前位次上的数
                // System.out.println("i: " + i + " and num is " + num);
                int base = num; // 有几个数可选
                // 第一高位要去掉 0
                if (i == 0) {
                    base--;
                }
                // System.out.println("base: " + base);
                // 去掉之前出现过的数
                for (int b: visited) {
                    if (b < num) {
                        base--;
                    }
                }
                // System.out.println("then base: " + base);
                // 如果还有可选的数可以放在当前位次
                if (base > 0) {
                    // 之后的全排列的可以用的数， 只和 i 有关系， 因为是全排列填充： _, _, _ 前两个空杯填了， 那么第三个空可用的只有 10 - 2 = 8 个数了， 记住个数是 base 1， 而 i 是 base 0 的
                    available = 10 - i - 1;
                    // System.out.println("avaiable: " + available);
                    // 做之后更低位次的全排列
                    for (int j = i + 1; j < s.length(); j++) {
                        base *= available;
                        available--;
                    }
                    unique += base;
                    // System.out.println("found unique : " + base);
                }
                // 如果出现了重复出现的数， 那么没必要继续进行了， 因为固定高位之后， 一定就是重复的了
                if (visited.contains(num)) {
                    break;
                }

                visited.add(num);
            }
            // 输入的这个数本身是 unique 的
            if (visited.size() == s.length()) {
                unique++;
            }
        }
        // 返回的是个数， 要加 1
        return n + 1 - unique;
    }
}

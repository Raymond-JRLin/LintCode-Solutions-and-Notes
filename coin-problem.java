// 1546. Coin Problem
// Description
// Ming is a salesman. After the customer bought something in his place and paid Ming a certain amount of money, Xiao Ming needs to return the extra money to the guest. The guest paid Xiao Ming n money, the price of Xiao Ming’s commodity was m money, and the denomination that Xiao Ming can return to the guests can only be a combination of [100, 50, 20, 10, 5, 2, 1]. Now Xiao Ming wants to minimize the sum of the number of banknotes, please return to this minimum.
//
// 1 \leq m \leq n \leq 10000000001≤m≤n≤1000000000
// Have you met this question in a real interview?
// Example
// Give n=100, m=29, return 3.
//
// 100-29=71
// Ming retrieved one 50, one 20 and one 1.
// So the answer is 3.
// Give n=50, m=5, return 3.
//
// 50-5=45
// Ming retrieved two 20 and one 5.
// So the answer is 3.


public class Solution {
    /**
     * @param n: The guest paid
     * @param m: the price
     * @return: the sum of the number of banknotes
     */
    public int coinProblem(int n, int m) {
        // Write your code here
        if (n == m) {
            return 0;
        }

        // return mytry(n, m);

        return method2(n, m);
    }

    private int method2(int n, int m) {
        // just check one by one on greedy
        n -= m;
        int result = 0;
        result += n / 100;
        n %= 100;
        result += n / 50;
        n %= 50;
        result += n / 20;
        n %= 20;
        result += n / 10;
        n %= 10;
        result += n / 5;
        n %= 5;
        result += n / 2;
        n %= 2;
        result += n / 1;
        n %= 1;
        return result;
    }

    private int mytry(int n, int m) {
        int target = n - m;
        final int[] coins = new int[]{100, 50, 20, 10, 5, 2, 1};
        int i = 0;
        int count = 0;
        while (target > 0) {
            while (target > 0 && coins[i] > target) {
                i++;
            }
            target -= coins[i];
            count++;
        }
        return count;
    }
}


// answer:

从最大的钱开始找，能找大面额的尽量找大面额的，否则尝试小面额的。直到1为止。

public class Solution {
    /**
     * @param n: The guest paid
     * @param m: the price
     * @return: the sum of the number of banknotes
     */
    public int coinProblem(int n, int m) {
        // Write your code here
        n -= m;
        int sum = 0;
        sum += n / 100;
        n %= 100;
        sum += n / 50;
        n %= 50;
        sum += n / 20;
        n %= 20;
        sum += n / 10;
        n %= 10;
        sum += n / 5;
        n %= 5;
        sum += n / 2;
        n %= 2;
        sum += n;
        return sum;
    }
}

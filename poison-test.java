// 1568. Poison Test
// Description
// Given the n bottles of water, only one bottle of water is poison, and mouse will die 24 hours later after drinking any dose of poison.
// If you want to figure out that which bottle of water is poison in 24 hours, how many mice are needed to ensure to success of poison test?
// (That means you can only feed each mouse once)
//
// 1<= n <=10000000
// Have you met this question in a real interview?
// Example
// Givenn=3,return 2
//
// Explanation：
// Let No. 1 mouse drink No.1 water,and let No. 2 mouse drink No.2 water
// If the No. 1 mouse died, it indicates that No. 1 water is poison.
// If the No. 2 mouse dies, it indicates that the No. 2 water is a poison.
// If it is not dead, it means that water No. 3 is poison.
// Givenn=6,return 3
//
// Explanation：
// Let No. 1 mice drink No. 5 and No. 6 water,let No. 2 mice  drink No. 3 and No. 4 water, and let No. 3 mice drink No. 2, No. 4 and No. 6 water .
// If the mice 1, 2, and 3 are not dead, the water No. 1 is poisonous;
// If the mice 1, 2 are not dead, 3 die, then the 2nd water is poisonous;
// If the mice 1, 3 are not dead, 2 die, then the 3rd water is poisonous;
// If the mouse 1 does not die, 2, 3 die, then the 4th water is poisonous;
// If the mouse 1 dies, 2, 3 does not die, then the 5th water is poisonous;
// If the mice die 1, 3, 2 are not dead, then the 6th water is poisonous;


public class Solution {
    /**
     * @param n: There are n bottles of water
     * @return: Return the number of mice
     */
    public int getAns(int n) {
        // Write your code here
        if (n <= 1) {
            return 0;
        }

        return method1(n);
    }

    private int method1(int n) {
        // 01 编码： 这么想， 一共 n 个 bottle， 只有一个有毒， 那么就总共是 n 种情况， 要用多少只老鼠可以表示这 n 种情况呢？ 就采取二进制的方式， 通过 0 1 不同来表示， 所以就是 2 ^ m 来表示所有的情况， m 就是老鼠的数量， 注意一只老鼠可以喝多杯水。
        // 还可以这么考虑： 二分法， [0, n / 2] [n / 2 + 1, n] 然后总会有一半有毒， 然后继续二分, 老鼠的数量就是 logn, 和二进制的结果是一样的
        int result = 0;
        int sum = 1;
        while (sum < n) {
            sum *= 2;
            result++;
        }
        return result;
    }

    // ref: 所需要的小白鼠的数量M为最小的满足2^M >= n的M
    // 为什么这样是对的呢？
    // 首先我们将每瓶水按顺序用格雷码进行编号（例如0000,0001,0010,0011,0100....）
    // （你可以把此处的格雷码理解成第i个(2<=i<=n)二进制编号和第i-1个二进制编号仅有一位不同）
    // 编号的第i位代表第i只小白鼠是(1)否(0)喝的东西里是否含有这瓶水的成分（例如01101代表1,3,4号小白鼠喝了这种水），并且算出至少多少位二进制能够将所有的小白鼠进行完全编号（M）
    // 在试验结束后小白鼠的存活状态我们也用一个01串来表示，0表示没死，1表示死了，那么我们编号与这个01串相同的水就是毒药
    // （因为喝了这瓶水的小白鼠都死了）


}


// answer:


public class Solution {
    /**
     * @param n: There are n bottles of water
     * @return: Return the number of mice
     */
    public int getAns(int n) {
        // Write your code here
        int ans=1;
        int sign=0;
        while(ans < n) {
            ans = ans * 2;
            sign++;
        }
        return sign;
    }
}

// 3. Digit Counts
// 中文English
// Count the number of k's between 0 and n. k can be 0 - 9.
//
// Example
// Example 1:
//
// Input:
// k = 1, n = 1
// Output:
// 1
// Explanation:
// In [0, 1], we found that 1 appeared once (1).
// Example 2:
//
// Input:
// k = 1, n = 12
// Output:
// 5
// Explanation:
// In [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], we found that one appeared five times (1, 10, 11, 12)(Note that there are two 1 in 11).


class Solution {
    /*
     * param k : As description.
     * param n : As description.
     * return: An integer denote the count of digit k in 1..n
     */
    public int digitCounts(int k, int n) {
        // write your code here
        // brute force
        int sum = 0;
        // special case
        if (k == 0) {
            return n / 10 + 1;
        }
        // for (int i = 0; i <= n; i++) {
        //     sum += brute(i, k);
        // }

        // according rules
            int factor = 1;
            int low = 0, cur = 0, high = 0;

            while(n/factor != 0){
                low = n - (n/factor) * factor;//低位数字
                cur = (n/factor) % 10;//当前位数字
                high = n / (factor*10);//高位数字

                if(cur < k)
                    sum += high * factor;
                else if(cur == k)
                    sum += high * factor + low + 1;
                else
                    sum += (high + 1) * factor;

                factor *= 10;
            }
        return sum;
    }
    private int brute(int i, int k) {
        int count = 0;
        while (i != 0) {
            // drag last digit (个位)
            if (i % 10 == k) {
                count++;
            }
            // 1 digit shifts from left to right
            i = i / 10;
        }
        return count;
    }
};

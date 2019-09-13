// 518. Super Ugly Number
// 中文English
// Write a program to find the nth super ugly number.
//
// A super ugly number is a positive integer in which all prime factors are within a given set of prime numbers.
//
// For example, given [2, 7, 13, 19], then [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the first 12 super ugly numbers.
//
// Example
// Example 1:
//
// Input: n = 6, [2,7,13,19]
// Output: 13
// Example 2:
//
// Input: n = 11, [2,3,5]
// Output: 15
// Notice
// 11 is a super ugly number for any given primes.
// 0 < k ≤ 100, 0 < n ≤ 10^610
// ​6
// ​​  , 0 < primes[i] < 1000


public class Solution {
    /*
     * @param n: a positive integer
     * @param primes: the given prime list
     * @return: the nth super ugly number
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        // write your code here
        if (n == 1) {
            return 1;
        }
        // my try: similar to Ugly number II, use PQ to do multiplications
        int len = primes.length;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        Long result = 1L;
        pq.offer(result);
        set.add(result);
        for (int i = 0; i < n; i++) {
            result = pq.poll();
            for (int j = 0; j < len; j++) {
                Long next = result * primes[j];
                if (!set.contains(next)) {
                    set.add(next);
                    pq.offer(next);
                }
            }
        }
        return result.intValue();
    }
}

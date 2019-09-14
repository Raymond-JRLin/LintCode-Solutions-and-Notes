// 4. Ugly Number II
// 中文English
// Ugly number is a number that only have prime factors 2, 3 and 5.
//
// Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
//
// Example
// Example 1:
//
// Input: 9
// Output: 10
// Example 2:
//
// Input: 1
// Output: 1
// Challenge
// O(n log n) or O(n) time.
//
// Notice
// Note that 1 is typically treated as an ugly number.


class Solution {
    /**
     * @param n an integer
     * @return the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        // Write your code here
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Long[] prime = new Long[3];
        prime[0] = Long.valueOf(2);
        prime[1] = Long.valueOf(3);
        prime[2] = Long.valueOf(5);
        HashSet<Long> hash = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            pq.offer(prime[i]);
            hash.add(prime[i]);
        }
        Long result = Long.valueOf(1);
        for (int i = 1; i < n; i++) {
            result = pq.poll();
            for (int j = 0; j < 3; j++) {
                Long newUgly = result * prime[j];
                if (!hash.contains(newUgly)) {
                    hash.add(newUgly);
                    pq.offer(newUgly);
                }
            }
        }
        return result.intValue();
    }
};

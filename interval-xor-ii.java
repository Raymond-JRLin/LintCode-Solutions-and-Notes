// 1652. Interval XOR II
// cat-only-icon
// CAT Only
// Description
// Given an integer array A(index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers i and k. For each query, calculate the XOR of the numbers Ai, A(i + 1), ..., A(i+k-1).return the result list.
//
// You can use‘^' to do it.In most languages '^' means XOR.
// n<=1000,number of queries<=1000.
// Ai<1000, k>0,i+k-1<n.
// Have you met this question in a real interview?
// Example
// For array [1,2,3,4] and query[(0,2),(1,3)] return[3,5].


/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param A:
     * @param query:
     * @return: nothing
     */
    public List<Integer> intervalXOR(int[] A, List<Interval> query) {
        //
        if (query == null || query.size() == 0) {
            return Collections.emptyList();
        }

        // return mytry(A, query);

        return mytry2(A, query);
    }

    private List<Integer> mytry2(int[] nums, List<Interval> queries) {
        // similar to prefix sum
        int[] xor = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            xor[i] = xor[i - 1] ^ nums[i - 1];
        }
        for (int x : xor) {
            System.out.print(x + " ");
        }
        List<Integer> result = new ArrayList<>();
        for (Interval query : queries) {
            // 这里要换成 prefix 的 index， 本来两个 [start, end] 要 - 1
            result.add(xor[query.start] ^ xor[query.start + query.end]);
        }
        return result;
    }

    private List<Integer> mytry(int[] nums, List<Interval> queries) {
        // brute force: O(NM) time and O(1) space
        List<Integer> result = new ArrayList<>();
        for (Interval query : queries) {
            int sum = 0;
            for (int i = query.start; i <= query.start + query.end - 1; i++) {
                sum ^= nums[i];
            }
            result.add(sum);
        }
        return result;
    }
}

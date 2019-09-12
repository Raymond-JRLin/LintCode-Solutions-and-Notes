// 198. Permutation Index II
// 中文English
// Given a permutation which may contain repeated numbers, find its index in all the permutations of these numbers, which are ordered in lexicographical order. The index begins at 1.
//
// Example
// Example 1:
//
// Input :[1,4,2,2]
// Output:3
// Example 2:
//
// Input :[1,6,5,3,1]
// Output:24


public class Solution {
    /**
     * @param A an integer array
     * @return a long integer
     */
    public long permutationIndexII(int[] A) {
        // Write your code here
        // Similar to I, but here we may have duplicates, so we can do the same as previouse one plus eliminating duplicates: / duplicate!
        int n = A.length;
        // use HashMap to record duplicates
        Map<Integer, Integer> hash = new HashMap<>();
        long fact = 1;
        long result = 1;
        int duplicates = 1;
        for (int i = n - 1; i >= 0; i--) {
            // record duplicates
            if (!hash.containsKey(A[i])) {
                hash.put(A[i], 1);
            } else {
                hash.put(A[i], hash.get(A[i]) + 1);
                duplicates *= hash.get(A[i]);
            }
            // calculate the number of smaller numbers
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (A[j] < A[i]) {
                    count++;
                }
            }
            // update permutation result
            result += count * fact / duplicates;
            // update fact
            fact *= n - i;
        }
        return result;
    }
}

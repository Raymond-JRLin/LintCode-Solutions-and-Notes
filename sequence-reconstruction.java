// 605. Sequence Reconstruction
// 中文English
// Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
//
// Example
// Example 1:
//
// Input:org = [1,2,3], seqs = [[1,2],[1,3]]
// Output: false
// Explanation:
// [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
// Example 2:
//
// Input: org = [1,2,3], seqs = [[1,2]]
// Output: false
// Explanation:
// The reconstructed sequence can only be [1,2].
// Example 3:
//
// Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
// Output: true
// Explanation:
// The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
// Example 4:
//
// Input:org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
// Output:true


public class Solution {
    /**
     * @param org a permutation of the integers from 1 to n
     * @param seqs a list of sequences
     * @return true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // Write your code here
        // because we don't have structure like Neighbor/node to record the direction from upper level to next, so we need to use a map to record such relation between integers
        Map<Integer, Set<Integer>> map = new HashMap<>();
        // a map for indegree calculation
        Map<Integer, Integer> indegree = new HashMap<>();
        // initialize indegree and map
        for (int num : org) {
            map.put(num, new HashSet<Integer>());
            indegree.put(num, 0);
        }
        // record relation and calculate indegree
        int len = 0; // special case, e.g. [1], [[],[]]
        int n = org.length;
        for (int[] seq : seqs) {
            len += seq.length;
            if (seq.length >= 1 && (seq[0] <= 0 || seq[0] > n)) {
                return false;
                // avoid case as [100000] in seqs but org does not have it
            }
            for (int i = 1; i < seq.length; i++) {
                if (seq[i] <= 0 || seq[i] > n) {
                    return false;
                    // avoid case as [1, 1000000] in seqs but org does not have 1000000
                }
                if (map.get(seq[i - 1]).add(seq[i])) {
                    indegree.put(seq[i], indegree.get(seq[i]) + 1);
                }

            }
        }
        if (len < n) {
            // sequence are not enough to constrct a supersequence as org
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        // put all integers with indegree of 0
        for (int num : indegree.keySet()) {
            if (indegree.get(num) == 0) {
                queue.offer(num);
            }
        }
        // topological sorting
        int count = 0;
        while (queue.size() == 1) {
            // set size == 1 condition because
            int num = queue.poll();
            for (int neighbor : map.get(num)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
            count++;
        }
        return count == n;

    }
}

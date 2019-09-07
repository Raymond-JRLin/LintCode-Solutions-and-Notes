// 152. Combinations
// 中文English
// Given two integers n and k. Return all possible combinations of k numbers out of 1, 2, ... , n.
//
// Example
// Example 1:
//
// Input: n = 4, k = 2
// Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
// Example 2:
//
// Input: n = 4, k = 1
// Output: [[1],[2],[3],[4]]
// Notice
// You can return all combinations in any order, but numbers in a combination should be in ascending order.
//


public class Solution {
    /*
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        // write your code here
        if (n < 0 || k < 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        dfs(result, list, n, k, 1);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> list, int n, int k, int index) {
        if (list.size() == k) {
            result.add(new ArrayList<Integer>(list));
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            dfs(result, list, n, k, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

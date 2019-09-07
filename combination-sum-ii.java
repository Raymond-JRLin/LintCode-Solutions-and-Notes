// 153. Combination Sum II
// 中文English
// Given an array num and a number target. Find all unique combinations in num where the numbers sum to target.
//
// Example
// Example 1:
//
// Input: num = [7,1,2,5,1,6,10], target = 8
// Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
// Example 2:
//
// Input: num = [1,1,1], target = 2
// Output: [[1,1]]
// Explanation: The solution set must not contain duplicate combinations.
// Notice
// Each number in num can only be used once in one combination.
// All numbers (including target) will be positive integers.
// Numbers in a combination a1, a2, … , ak must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak)
// Different combinations can be in any order.
// The solution set must not contain duplicate combinations.


public class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return results;
        }
        Arrays.sort(num);
        ArrayList<Integer> list = new ArrayList<Integer>();
        dfs(num, 0, target, list, results);
        return results;
    }
    private void dfs(int[] num, int index, int residue, ArrayList<Integer> list, List<List<Integer>> results) {
        if (residue == 0) {
            results.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = index; i < num.length; i++) {
            if (residue < 0) {
                break;
            }
            if (i != index && num[i] == num[i - 1]) {
                continue;
            }
            list.add(num[i]);
            dfs(num, i + 1, residue - num[i], list, results);
            list.remove(list.size() - 1);
        }
    }
}

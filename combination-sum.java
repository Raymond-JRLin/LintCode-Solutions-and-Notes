// 135. Combination Sum
// 中文English
// Given a set of candidtate numbers candidates and a target number target. Find all unique combinations in candidates where the numbers sums to target.
//
// The same repeated number may be chosen from candidates unlimited number of times.
//
// Example
// Example 1:
//
// Input: candidates = [2, 3, 6, 7], target = 7
// Output: [[7], [2, 2, 3]]
// Example 2:
//
// Input: candidates = [1], target = 3
// Output: [[1, 1, 1]]
// Notice
// All numbers (including target) will be positive integers.
// Numbers in a combination a1, a2, … , ak must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak)
// Different combinations can be in any order.
// The solution set must not contain duplicate combinations.


public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }
        // if (candidates.length == 0) {
        //     results.add(new ArrayList<Integer>());
        //     return results;
        // }
        int[] nums = removeDuplicates(candidates);
        dfs(nums, 0, target, new ArrayList<Integer>(), results);
        return results;
    }
    private int[] removeDuplicates(int[] cand) {
        Arrays.sort(cand);
        int index = 0;
        for (int i = 0; i < cand.length; i++) {
            if (cand[i] != cand[index]) {
                index++;
                cand[index] = cand[i];
            }
        }
        int[] nums = new int[index + 1];
        for (int i = 0; i < index + 1; i++) {
            nums[i] = cand[i];
        }
        return nums;
    }
    private void dfs(int[] nums, int index, int residue, List<Integer> list,
        List<List<Integer>> results) {
        if (residue == 0) {
            results.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (residue < nums[i]) {
                break;
            }
            list.add(nums[i]);
            // residue = residue - nums[i]; 不行，因为residue值改变了
            dfs(nums, i, residue - nums[i], list, results);
            // start from i, because we can use numbers several times
            list.remove(list.size() - 1);
        }
    }
}

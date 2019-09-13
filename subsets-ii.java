// 18. Subsets II
// 中文English
// Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
//
// Example
// Example 1:
//
// Input: [0]
// Output:
// [
//   [],
//   [0]
// ]
// Example 2:
//
// Input: [1,2,2]
// Output:
// [
//   [2],
//   [1],
//   [1,2,2],
//   [2,2],
//   [1,2],
//   []
// ]
// Challenge
// Can you do it in both recursively and iteratively?
//
// Notice
// Each element in a subset must be in non-descending order.
// The ordering between two subsets is free.
// The solution set must not contain duplicate subsets.


class Solution {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<>();
        dfs(nums, 0, list, results);
        return results;
    }
    private void dfs(int[] nums, int index, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> results) {
        results.add(new ArrayList<Integer>(list));
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            dfs(nums, i + 1, list, results);
            list.remove(list.size() - 1);
        }
    }
}

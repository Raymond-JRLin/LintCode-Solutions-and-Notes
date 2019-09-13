// 17. Subsets
// 中文English
// Given a set of distinct integers, return all possible subsets.
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
// Input: [1,2,3]
// Output:
// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]
// Challenge
// Can you do it in both recursively and iteratively?
//
// Notice
// Elements in a subset must be in non-descending order.
// The solution set must not contain duplicate subsets.


class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
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
        helper(new ArrayList<Integer>(), nums, 0, results);
        return results;
    }
    private void helper(ArrayList<Integer> subset, int[] nums, int start,ArrayList<ArrayList<Integer>> results) {
        results.add(new ArrayList<Integer>(subset));
        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            helper(subset, nums, i + 1, results);
            subset.remove(subset.size() - 1);
        }
    }
}

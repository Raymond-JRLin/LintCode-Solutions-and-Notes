// 15. Permutations
// 中文English
// Given a list of numbers, return all possible permutations.
//
// Example
// Example 1:
//
// Input: [1]
// Output:
// [
//   [1]
// ]
// Example 2:
//
// Input: [1,2,3]
// Output:
// [
//   [1,2,3],
//   [1,3,2],
//   [2,1,3],
//   [2,3,1],
//   [3,1,2],
//   [3,2,1]
// ]
// Challenge
// Do it without recursion.
//
// Notice
// You can assume that there is no duplicate numbers in the list.
//


class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        dfs(nums, new ArrayList<Integer>(), results);
        return results;
    }
    private void dfs(int[] nums, List<Integer> list, List<List<Integer>> results) {
        if (list.size() == nums.length) {
            results.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                // continue;
                list.add(nums[i]);
            dfs(nums, list, results);
            list.remove(list.size() - 1);
            }

        }
    }
}

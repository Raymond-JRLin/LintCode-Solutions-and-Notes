// 16. Permutations II
// 中文English
// Given a list of numbers with duplicate number in it. Find all unique permutations.
//
// Example
// Example 1:
//
// Input: [1,1]
// Output:
// [
//   [1,1]
// ]
// Example 2:
//
// Input: [1,2,2]
// Output:
// [
//   [1,2,2],
//   [2,1,2],
//   [2,2,1]
// ]
// Challenge
// Using recursion to do it is acceptable. If you can do it without recursion, that would be great!
//


class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        int length = nums.length;
        int[] visited = new int[length];
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            visited[i] = 0;
        }
        List<Integer> list = new ArrayList<>();
        dfs(nums, visited, list, results);
        return results;
    }
    private void dfs(int[] nums, int[] visited, List<Integer> list, List<List<Integer>> results) {
        if (list.size() == nums.length) {
            results.add(new ArrayList<Integer>(list));
            // remember transfer the references => deep copy
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            if (i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = 1;
            dfs(nums, visited, list, results);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }
}

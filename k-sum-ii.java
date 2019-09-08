// 90. k Sum II
// 中文English
// Given n unique postive integers, number k (1<=k<=n) and target.
//
// Find all possible k integers where their sum is target.
//
// Example
// Example 1:
//
// Input: [1,2,3,4], k = 2, target = 5
// Output:  [[1,4],[2,3]]
// Example 2:
//
// Input: [1,3,4,6], k = 3, target = 8
// Output:  [[1,3,4]]


public class Solution {
    /*
     * @param A: an integer array
     * @param k: a postive integer <= length(A)
     * @param targer: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (A == null || A.length == 0) {
            return result;
        }
        dfs(result, new ArrayList<Integer>(), A, k, target, 0);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int k, int target, int index) {
        if (list.size() == k) {
            if (sum(list) == target) {
                // DEEP COPY!!!
                result.add(new ArrayList<Integer>(list));
                return;
            }
        }
        if (list.size() > k) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(result, list, nums, k, target, i + 1);
            list.remove(list.size() - 1);
        }
    }
    private int sum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }
}

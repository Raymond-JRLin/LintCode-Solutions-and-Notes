// 124. Longest Consecutive Sequence
// 中文English
// Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
// Example
// Example 1
//
// Input : [100, 4, 200, 1, 3, 2]
// Output : 4
// Explanation : The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length:4
// Clarification
// Your algorithm should run in O(n) complexity.
//


public class Solution {
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] num) {
        // write you code here
        // because it's required to solve in O(n), which means we cannot use sort, so we can use one data structure to complete
        if (num == null || num.length == 0) {
            return 0;
        }
        // based on HashSet
        // // put all nums in a set
        // Set<Integer> set = new HashSet<>();
        // for (int val : num) {
        //     set.add(val);
        // }
        // int result = 0;
        // for (int val : num) {
        //     int length = 1;
        //     // set.remove(val);
        //     if (set.contains(val - 1)) {
        //         int pre = val - 1;
        //         while (set.contains(pre)) {
        //             set.remove(pre);
        //             pre--;
        //             length++;
        //         }
        //     }
        //     if (set.contains(val + 1)) {
        //         int next = val + 1;
        //         while (set.contains(next)) {
        //             set.remove(next);
        //             next++;
        //             length++;
        //         }
        //     }
        //     result = Math.max(result, length);
        // }
        // return result;

        // based on HashMap
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i : num) {
            if (!map.containsKey(i)) {
                int pre = map.containsKey(i - 1) ? map.get(i - 1) : 0;
                int next = map.containsKey(i + 1) ? map.get(i + 1) : 0;
                int sum = pre + next + 1;
                map.put(i, sum);
                result = Math.max(result, sum);
                map.put(i - pre, sum);
                map.put(i + next, sum);
            } else {
                continue;
            }
        }
        return result;
    }
}

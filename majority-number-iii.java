// 48. Majority Number III
// 中文English
// Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.
//
// Find it.
//
// Example
// Example 1:
//
// Input: [3,1,2,3,2,3,3,4,4,4] and k=3,
// Output: 3.
// Example 2:
//
// Input: [1,1,2] and k=3,
// Output: 1.
// Challenge
// O(n) time and O(k) extra space
//
// Notice
// There is only one majority number in the array.
//


public class Solution {
    /*
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The majority number
     */
    public int majorityNumber(List<Integer> nums, int k) {
        // write your code here
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        int n = nums.size();
        // 通过上一题 II， 再看 O(k) space， 那么能够想到要存 top(k - 1) 来进行 kk抵消， 可以使用 map 来存储
        // 注意的是： 当 k - 1 个存储位置用完了后， 如果处理？ 就是把所有的 count 都要 - 1， 然后移除变为 0 的
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // use map to record top(k - 1)
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                // new number, we need to check if map is full
                if (map.size() >= k) {
                    removeCount(map);
                }
                map.put(num, 1);
            }
        }
        // reset all count to 0
        for (int key : map.keySet()) {
            map.put(key, 0);
        }
        // loop given list to record absolute count of keys
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
                if (map.get(num) > n / k) {
                    return num;
                }
            }
        }
        return -1;
    }
    private void removeCount(Map<Integer, Integer> map) {
        Set<Integer> remove = new HashSet<Integer>();
        for (int key : map.keySet()) {
            map.put(key, map.get(key) - 1);
            // we cannot delete right now, because of thread safety
            // if (map.get(key) == 0) {
            //     map.remove(key);
            // }
            if (map.get(key) == 0) {
                remove.add(key);
            }
        }
        for (int key : remove) {
            map.remove(key);
        }
    }
}

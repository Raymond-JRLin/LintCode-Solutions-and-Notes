// 47. Majority Element II
// 中文English
// Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.
//
// Find it.
//
// Example
// Example 1:
//
// Input: [99,2,99,2,99,3,3],
// Output: 99.
// Example 2:
//
// Input: [1, 2, 1, 2, 1, 3, 3],
// Output: 1.
// Challenge
// O(n) time and O(1) extra space.
//
// Notice
// There is only one majority number in the array.


public class Solution {
    /*
     * @param nums: a list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        int n = nums.size();
        // method 1: HashMapm, O(n) time and O(n) space
        // Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // for (int num : nums) {
        //     if (map.containsKey(num)) {
        //         map.put(num, map.get(num) + 1);
        //     } else {
        //         map.put(num, 1);
        //     }
        //     if (map.get(num) > n / 3) {
        //         return num;
        //     }
        // }
        // return -1;
        // method 2: O(n) time and O(1) space
        // 在 I 中， 我们是两两抵消， 现在超过 1/3， 所以 candidate 取 top2 进行三三抵消
        int key1 = -1;
        int key2 = -1;
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            // if (count1 != 0 && num == key1) {
            if (num == key1) {
                count1++;
            // } else if (count2 != 0 && num == key2) {
            } else if (num == key2) {
                count2++;
            } else if (count1 == 0) {
                key1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                key2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        // double check top2 to see which one is majority
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == key1) {
                count1++;
            }
            if (num == key2) {
                count2++;
            }
        }
        if (count1 > count2) {
            return key1;
        } else {
            return key2;
        }
    }
}

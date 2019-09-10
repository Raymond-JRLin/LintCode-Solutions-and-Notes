// 46. Majority Element
// 中文English
// Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.
//
// Example
// Example 1:
//
// Input: [1, 1, 1, 1, 2, 2, 2]
// Output: 1
// Example 2:
//
// Input: [1, 1, 1, 2, 2, 2, 2]
// Output: 2
// Challenge
// O(n) time and O(1) extra space
//
// Notice
// You may assume that the array is non-empty and the majority number always exist in the array.
//


public class Solution {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        int n = nums.size();
        // from notice: You may assume that the array is non-empty and the majority number always exist in the array. so it would be easier

        // method 1: based on HashMap, O(n) time and O(n) space
        // HashMap<Integer, Integer> map = new HashMap<>();
        // for (int num : nums) {
        //     if (map.containsKey(num)) {
        //         map.put(num, map.get(num) + 1);
        //     } else {
        //         map.put(num, 1);
        //     }
        //     // we can do checking here
        //     if (map.get(num) > n / 2) {
        //         return num;
        //     }
        // }
        // // or we can put following checking step in above for loop after put a num into map
        // // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        // //     if (entry.getValue() > n / 2) {
        // //         return entry.getKey();
        // //     }
        // // }
        // return -1;

        // method 2: if we want to do in O(n) time and O(1) space, we should use Mooreâ€™s Voting Algorithm
        // initialize candidate and its position
        // int index = 0; // or we can use index
        int count = 1;
        int candidate = nums.get(0);
        for (int num : nums) {
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                candidate = num;
                count = 1;
            }
        }
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        if (count > n / 2) {
            return candidate;
        } else {
            return -1;
        }
    }
}

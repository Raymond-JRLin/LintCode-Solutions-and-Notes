// 56. Two Sum
// 中文English
// Given an array of integers, find two numbers such that they add up to a specific target number.
//
// The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are zero-based.
//
// Example
// Example1:
// numbers=[2, 7, 11, 15], target=9
// return [0, 1]
// Example2:
// numbers=[15, 2, 7, 11], target=9
// return [1, 2]
// Challenge
// Either of the following solutions are acceptable:
//
// O(n) Space, O(nlogn) Time
// O(n) Space, O(n) Time
// Notice
// You may assume that each input would have exactly one solution
//


public class Solution {
    /*
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        // int[] results = new int[2];
        // if (numbers == null || numbers.length == 0) {
        //     return results;
        // }
        // int left = 0;
        // int right = numbers.length - 1;
        // Arrays.sort(numbers);
        // while(left < right) {
        //     if (numbers[left] + numbers[right] == target) {
        //         results[0] = left + 1;
        //         results[1] = right + 1;
        //         return results;
        //     } else if (numbers[left] + numbers[right] < target) {
        //         left++;
        //     } else {
        //         right--;
        //     }
        // }
        // return results;

        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (hash.get(numbers[i]) != null) {
                int[] results = {hash.get(numbers[i]) + 1, i + 1};
                return results;
            }
            hash.put(target - numbers[i], i);
        }
        int[] results = new int[2];
        return results;
    }
}

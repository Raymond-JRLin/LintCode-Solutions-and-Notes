// 1368. Same Number
// 中文English
// Given an array, If the same number exists in the array, and the minimum distance the same number is less than the given value k, output YES, otherwise output NO.
//
// Example
// Example1
//
// Input:  array = [1,2,3,1,5,9,3] and k = 4
// Output: "YES"
// Explanation:
// The distance of 1 whose indexes are  3 and 0 is 3, which meets the requirement and output YES.
// Example2
//
// Input:  array = [1,2,3,5,7,1,5,1,3] and k = 4
// Output: "YES"
// Explanation:
// The distance of 1 whose indexes are 0 and 5 is 5, which meets the requirement， and output YES.
// Notice
// The length of the given array is n，and n <= 100000.
// The element is x，0 <= x <= 1e9.
// 1 <= k < n。


public class Solution {
    /**
     * @param nums: the arrays
     * @param k: the distance of the same number
     * @return: the ans of this question
     */
    public String sameNumber(int[] nums, int k) {
        // Write your code here

        if (nums == null || nums.length == 0) {
            return "NO";
        }

        return mytry(nums, k);
    }

    private String mytry(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) < k) {
                    return "YES";
                } else {
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return "NO";
    }
}

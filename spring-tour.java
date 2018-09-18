// 1575. Spring Tour
// Description
// There are n group children ready to go on a spring tour. The array a indicates the number of people in each group. There are no more than four people in each group. There are now several cars. Each car can only take up to four people. The same group of children must sit in the same car, and each car need't be full. Ask how many cars you need to meet the travel needs of your children.
//
// 0 \leq n \leq 1e40≤n≤1e4
// 1 \leq a[i] \leq 41≤a[i]≤4
// Have you met this question in a real interview?
// Example
// Given a = [1,2,3,4]，return 3.
//
// At this time, there are 4 people in the fourth group, sitting alone in a car.
// The first group has 1 person, the third group has 3 people, and just 4 people get for one car.
// The second group of 2 people has a single car, so at least 3 cars are needed.
// Given a = [1,2,2,2]，return 2.
//
// Explanation:
// The first group and the second group get one car
// The third group and the fourth group get one car
// Therefore, at least two cars are needed.


public class Solution {
    /**
     * @param a: The array a
     * @return: Return the minimum number of car
     */
    public int getAnswer(int[] a) {
        // Write your code here
        if (a == null || a.length == 0) {
            return 0;
        }

        return mytry(a);
    }

    private int mytry(int[] nums) {
        int n = nums.length;
        int result = 0;
        Arrays.sort(nums);
        int left = 0;
        int right = n - 1;
        int sum = 0; // record last time when sum of left and right is not 4 enough
        while (left <= right) {
            if (nums[left] + nums[right] + sum > 4) {
                // larger than 4, then right should be in one car
                right--;
                result++;
                sum = 0;
            } else if (nums[left] + nums[right] + sum == 4) {
                // move both
                left++;
                right--;
                result++;
                sum = 0;
            } else {
                // move left to get more, record this skipping value
                left++;
                sum += nums[left];
            }
        }
        return result;
    }
}

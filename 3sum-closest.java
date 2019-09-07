// 59. 3Sum Closest
// 中文English
// Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers.
//
// Example
// Example 1:
//
// Input:[2,7,11,15],3
// Output:20
// Explanation:
// 2+7+11=20
// Example 2:
//
// Input:[-1,2,1,-4],1
// Output:2
// Explanation:
// -1+2+1=2
// Challenge
// O(n^2) time, O(1) extra space
//
// Notice
// You may assume that each input would have exactly one solution.
//


public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
        // not only length == 0; if length is shorter than 3 then we cannot find any answer
        if (numbers == null || numbers.length < 3) {
            return -1;
        }
        Arrays.sort(numbers);
        int diff = Integer.MAX_VALUE;
        int sum = 0;
        // i < numbers.length - 2, because we see i as the smallest one, and we need 3 number at least, so skip the last 2 biggest numbers
        for (int i = 0; i < numbers.length - 2; i++) {
            int residue = target - numbers[i];
            // the 2nd biggest number starts from the next position
            int left = i + 1;
            int right = numbers.length - 1;
            while (left < right) {
                if (numbers[left] + numbers[right] < residue) {
                    int newDiff = residue - numbers[left] - numbers[right];
                    diff = Math.min(diff, newDiff);
                    if (diff == newDiff) {
                        sum = numbers[left] + numbers[right] + numbers[i];
                    }
                    left++;
                } else {
                    int newDiff = numbers[left] + numbers[right] - residue;
                    diff = Math.min(diff, newDiff);
                    if (diff == newDiff) {
                        sum = numbers[left] + numbers[right] + numbers[i];
                    }
                    right--;
                }
            }
        }
        return sum;
    }
}

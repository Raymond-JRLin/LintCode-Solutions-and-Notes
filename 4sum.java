// 58. 4Sum
// 中文English
// Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
//
// Find all unique quadruplets in the array which gives the sum of target.
//
// Example
// Example 1:
//
// Input:[2,7,11,15],3
// Output:[]
//
// Example 2:
//
// Input:[1,0,-1,0,-2,2],0
// Output:
// [[-1, 0, 0, 1]
// ,[-2, -1, 1, 2]
// ,[-2, 0, 0, 2]]
// Notice
// Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
// The solution set must not contain duplicate quadruplets.
//


public class Solution {
    /**
     * @param numbers : Give an array numbersbers of n integer
     * @param target : you need to find four elements that's sum of target
     * @return : Find all unique quadruplets in the array which gives the sum of
     *           zero.
     */
    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
        /* your code */
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (numbers == null || numbers.length < 4) {
            return results;
        }
        // we can do 2 for loops as 3 sum, then it would be O(n^3)
        Arrays.sort(numbers);
        // i < numbers.length - 2, because we see i as the smallest one, and we need 3 number at least, so skip the last 2 biggest numbers
        for (int i = 0; i < numbers.length - 3; i++) {
            // skip the duplicates
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < numbers.length - 2; j++) {
                if (j > i + 1 && numbers[j] == numbers[j - 1]) {
                    continue;
                }
                int residue = target - numbers[i] - numbers[j];
                int left = j + 1;
                int right = numbers.length - 1;
                // 2 sum method
                while (left < right) {
                    if (numbers[left] + numbers[right] == residue) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(numbers[i]);
                        list.add(numbers[j]);
                        list.add(numbers[left]);
                        list.add(numbers[right]);
                        results.add(list);
                        left++;
                        right--;
                        // don't forget also skip duplicates to avoid same numbers for 2 sum
                        while (left < right && numbers[left] == numbers[left - 1]) {
                            left++;
                        }
                        while (left < right && numbers[right] == numbers[right + 1]) {
                            right--;
                        }
                    } else if (numbers[left] + numbers[right] < residue) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return results;
    }
}

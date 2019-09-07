// 57. 3Sum
// 中文English
// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//
// Example
// Example 1:
//
// Input:[2,7,11,15]
// Output:[]
// Example 2:
//
// Input:[-1,0,1,2,-1,-4]
// Output:	[[-1, 0, 1],[-1, -1, 2]]
// Notice
// Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
//
// The solution set must not contain duplicate triplets.
//


public class Solution {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        // write your code here
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        // not only length == 0; if length is shorter than 3 then we cannot find any answer
        if (numbers == null || numbers.length < 3) {
            return results;
        }
        Arrays.sort(numbers);
        // i < numbers.length - 2, because we see i as the smallest one, and we need 3 number at least, so skip the last 2 biggest numbers
        for (int i = 0; i < numbers.length - 2; i++) {
            // skip the duplicates
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            int residue = 0 - numbers[i];
            // the 2nd biggest number starts from the next position
            int left = i + 1;
            int right = numbers.length - 1;
            while (left < right) {
                if (numbers[left] + numbers[right] == residue) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(numbers[i]);
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
        return results;
    }
}

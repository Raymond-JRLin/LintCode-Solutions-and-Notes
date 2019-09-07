// 681. First Missing Prime Number
// 中文English
// Given a list of prime numbers and find the smallest prime number that doesn't appear in this list.
//
// Example
// Example1
//
// Input: [3,5,7]
// Output: 2
// Example2
//
// Input: [2,3,5,7,11,13,17,23,29]
// Output: 19


public class Solution {
    /*
     * @param : an array of integer
     * @return: the first missing prime number
     */
    public int firstMissingPrime(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 2;
        }

		if (nums[0] != 2) {
			return 2;
		}
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] - nums[i - 1] > 2) {
				int cur = nums[i - 1] + (nums[i - 1] % 2 == 0 ? 1 : 2);
				while (cur < nums[i]) {
					if (isPrime(cur)) {
						return cur;
					}
					cur += 2;
				}
			}
		}
		int start = nums[nums.length - 1] + 2;
		while (true) {
			if (isPrime(start)) {
				return start;
			} else {
				start += 2;
			}
		}
    }
    private boolean isPrime(int num) {
		if (num != 2 && num % 2 == 0) {
			return false;
		}
		if (num == 2) {
			return true;
		}
		int upper = (int) Math.sqrt(num);
		for (int i = 3; i <= upper; i = i + 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
};

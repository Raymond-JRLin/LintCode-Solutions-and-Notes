// 662. Guess Number Higher or Lower
// 中文English
// We are playing the Guess Game. The game is as follows:
//
// I pick a number from 1 to n. You have to guess which number I picked.
//
// Every time you guess wrong, I will tell you if this number is greater or less than the number you guessed.
//
// You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
// -1 means this number is less than the number you guessed
//
// 1 means this number is greater than the number you guessed
//
// 0 means this number is equal to the number you guessed
//
// Example
// Example 1:
//
// Input : n = 10, I pick 4 (but you don't know)
// Output : 4


/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    /**
     * @param n an integer
     * @return the number you guess
     */
    public int guessNumber(int n) {
        // Write your code here
        if (guess(n) == 0) {
            return n;
        }
        if (guess(1) == 0) {
            return 1;
        }
        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (guess(start) == 0) {
            return start;
        } else if (guess(end) == 0) {
            return end;
        } else {
            return -1;
        }
    }
}

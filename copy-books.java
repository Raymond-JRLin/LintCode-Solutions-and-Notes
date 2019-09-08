// 437. Copy Books
// 中文English
// Given n books and the i-th book has pages[i] pages. There are k persons to copy these books.
//
// These books list in a row and each person can claim a continous range of books. For example, one copier can copy the books from i-th to j-th continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).
//
// They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?
//
// Return the shortest time that the slowest copier spends.
//
// Example
// Example 1:
//
// Input: pages = [3, 2, 4], k = 2
// Output: 5
// Explanation:
//     First person spends 5 minutes to copy book 1 and book 2.
//     Second person spends 4 minutes to copy book 3.
// Example 2:
//
// Input: pages = [3, 2, 4], k = 3
// Output: 4
// Explanation: Each person copies one of the books.
// Challenge
// O(nk) time
//


public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here
        // temporarily copy solution here
        if (pages.length == 0) {
            return 0;
        }

        int total = 0;
        int max = pages[0];
        for (int i = 0; i < pages.length; i++) {
            total += pages[i];
            if (max < pages[i]) {
                max = pages[i];
            }
        }

        int start = max;
        int end = total;

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (countCopiers(pages, mid) > k) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (countCopiers(pages, start) <= k) {
            return start;
        }

        return end;
    }

    private int countCopiers(int[] pages, int limit) {
        if (pages.length == 0) {
            return 0;
        }

        int copiers = 1;
        int sum = pages[0]; // limit is always >= pages[0]
        for (int i = 1; i < pages.length; i++) {
            if (sum + pages[i] > limit) {
                copiers++;
                sum = 0;
            }
            sum += pages[i];
        }

        return copiers;
    }

}

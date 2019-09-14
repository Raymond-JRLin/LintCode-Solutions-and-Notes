// 382. Triangle Count
// 中文English
// Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three edges length is the three numbers that we find?
//
// Example
// Example 1:
//
// Input: [3, 4, 6, 7]
// Output: 3
// Explanation:
// They are (3, 4, 6),
//          (3, 6, 7),
//          (4, 6, 7)
// Example 2:
//
// Input: [4, 4, 4, 4]
// Output: 4
// Explanation:
// Any three numbers can form a triangle.
// So the answer is C(3, 4) = 4


public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int S[]) {
        // write your code here
        if (S == null || S.length < 3) {
            return 0;
        }
        // Triangle Inequality Theorem: the sum of the lengths of any 2 sides of a triangle must exceed the length of the third side.
        Arrays.sort(S); // sort from small to large
        int n = S.length;
        int count = 0;
        // the 1st index
        // for (int i = 0; i < n - 2; i++) {
        //     // 2nd pointer - 2nd side
        //     int second = i + 1;
        //     while (second < n - 1) {
        //         // 3rd pointer searching for the 3rd side
        //         int third = second + 1;
        //         while (third < n) {
        //             if (S[i] + S[second] > S[third]) {
        //                 count++;
        //             }
        //             third++;
        //         }
        //         second++;
        //     }
        // }
        // return count;

        // actually, it's similar to 3 sum with inequality sign
        // so we can fix one, and move another 2 pointer to check
        // for (int i = 0; i < n - 2; i++) {
        //     int second = i + 1;
        //     while (second < n - 1) {
        //         if (S[i] + S[second] > S[n - 1]) {
        //             count += n - 1 - second;
        //         }
        //         second++;
        //     }
        // }
        // return count;
        // above is wrong, because if we fixed i and move second only, we would miss possibilites when "right" (n - 1) pointer move forward, since the result of moving second to end or moving "right"(n - 1) to front are the same, so we should fix one pointer, and do 2 difference scenarios with another 2 pointer like answer does: fix the the third pointer(set as i in for loop), i.e. the max side, and move 1st and 2nd pointer representing 1st and 2nd sides, if left + right > i, check right--; otherwise check left++

        // hence we should do:
        // fix 3rd pointer as i in for loop
        for (int i = 0; i < n; i++) {
            // because i is the 3rd pointer/ max side, so it should be moved to the last element
            int left = 0;
            int right = i - 1;
            while (left < right) {
                // if 2 poiter met, we're done, and they cannot be equal, because in one combination, we can only use once
                if (S[left] + S[right] > S[i]) {
                    count += right - left;
                    right--; // decrease smaller 2 sides
                } else {
                    left++; // increase smaller 2 sides
                }
            }
        }
        return count;
    }
}

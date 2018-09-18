// 1585. Moving Stones
// Description
// There are n stones distributed on the x-axis, and their positions are represented by an array of arr. It is now required to move these stones to 1, 3, 5, 7, 2n-1 or 2, 4, 6, 8, 2n. That is to say, these stones are required to move to odd-numbered bits starting from 1 or consecutive even-numbered bits starting from 2. Returns the minimum number of moves. You can only move 1 stone at a time, only move the stone one unit to the left or one unit to the right. You cannot have two stones at the same time in the same location.
//
// 1 \leq n \leq 100001≤n≤10000
// 1 \leq arr[i] \leq 1000001≤arr[i]≤100000
//
// Have you met this question in a real interview?
// Example
// Give arr=[5,4,1] and return 1.
//
// Explanation：
// You only need to move the stone in position 4 to the left to 3,
// [1,3,5], in line with the requirements.
// Give arr=[1,6,7,8,9] and return 5.
//
// Explanation：
// The optimal mobility scheme is to move 1 to 2, move 6 to 4, move 7 to 6, and move 9 to 10.
// The cost is 1+2+1+1=5.


public class Solution {
    /**
     * @param arr: the positions
     * @return: minimum number of moves
     */
    public int movingStones(int[] arr) {
        // Write your code here
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // return method1(arr);

        return method2(arr);
    }

    private int method2(int[] arr) {
        Arrays.sort(arr);
        int oddCost = 0, evenCost = 0;
        for (int i = 0; i < arr.length; i++) {
            // 和 1 是相同的思路， 不过利用 i 来写对应的奇偶的位置
            oddCost += Math.abs(arr[i] - 2 * i - 1); // -1, -3, -5
            evenCost += Math.abs(arr[i] - 2 * i - 2); // -2, -4, -6
        }

        return Math.min(oddCost, evenCost);
    }

    private int method1(int[] nums) {
        Arrays.sort(nums);
        int oddPos = 1; // starting position of odd sequence
        int evenPos = 2; // starting position of even number
        int oddCost = 0; // cost of assgining as odd sequence
        int evenCost = 0; // cost of even sequence
        for (int num : nums) {
            // 按照奇偶应有的顺序才检查
            oddCost += Math.abs(num - oddPos);
            evenCost += Math.abs(num - evenPos);
            oddPos += 2;
            evenPos += 2;
        }
        return Math.min(oddCost, evenCost);
    }
}

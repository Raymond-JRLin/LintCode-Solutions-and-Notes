// 34. N-Queens II
// 中文English
// Follow up for N-Queens problem.
//
// Now, instead outputting board configurations, return the total number of distinct solutions.
//
// Example
// Example 1:
//
// Input: n=1
// Output: 1
// Explanation:
// 1:
// 1
// Example 2:
//
// Input: n=4
// Output: 2
// Explanation:
// 1:
// 0 0 1 0
// 1 0 0 0
// 0 0 0 1
// 0 1 0 0
// 2:
// 0 1 0 0
// 0 0 0 1
// 1 0 0 0
// 0 0 1 0


public class Solution {
    /*
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int count = 0;
    public int totalNQueens(int n) {
        // write your code here
        if (n < 1) {
            return 0;
        }
        // 其实和 I 是一样的， 只是不把所有结果输出， 而是输出个数， 当然可以做完了所有结果求个 size， 因为 int 不能在传参中被改变， 所以我们可以设置一个全局变量的 counter

        // method 1: use List
        // List<Integer> cols = new ArrayList<>();
        // dfs(cols, n);
        // return count;

        // method 2: use Array
        int[] cols = new int[n]; // n is the total columns
        for (int i = 0; i < n; i++) {
            cols[i] = -1;
        }
        dfsArray(cols, 0);
        return count;
    }

    // method 1
    private void dfs(List<Integer> cols, int n) {
        if (cols.size() == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(cols, i)) {
                cols.add(i);
                dfs(cols, n);
                cols.remove(cols.size() - 1);
            }
        }
    }
    private boolean isValid(List<Integer> cols, int col) {
        int row = cols.size(); // current row
        for (int i = 0; i < cols.size(); i++) {
            if (cols.get(i) == col || Math.abs(i - row) == Math.abs(cols.get(i) - col)) {
                return false;
            }
        }
        return true;
    }

    // method 2
    private void dfsArray(int[] cols, int row) {
        int n = cols.length;
        if (row == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValidArray(cols, row, i)) {
                cols[row] = i;
                dfsArray(cols, row + 1);
                cols[row] = -1;
            }
        }
    }
    private boolean isValidArray(int[] cols, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (col == cols[i] || Math.abs(i - row) == Math.abs(cols[i] - col)) {
                return false;
            }
        }
        return true;
    }
}

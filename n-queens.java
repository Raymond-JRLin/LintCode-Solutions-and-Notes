// 33. N-Queens
// 中文English
// The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other(Any two queens can't be in the same row, column, diagonal line).
//
// Given an integer n, return all distinct solutions to the n-queens puzzle.
//
// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
//
// Example
// Example 1:
//
// Input:1
// Output:
//    [["Q"]]
//
//
// Example 2:
//
// Input:4
// Output:
// [
//   // Solution 1
//   [".Q..",
//    "...Q",
//    "Q...",
//    "..Q."
//   ],
//   // Solution 2
//   ["..Q.",
//    "Q...",
//    "...Q",
//    ".Q.."
//   ]
// ]
//
// Challenge
// Can you do it without recursion?
//


class Solution {
    /**
     * Get all distinct N-Queen solutions
     * @param n: The number of queens
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position
     */
    List<List<String>> solveNQueens(int n) {
        // write your code here
        // queen cannot meet in row, column and diagonals
        // use DFS to put queen each raw
        if (n < 1) {
            return null;
        }
        List<List<String>> result = new ArrayList<>();

        // method 1: recursion of DFS with pruning
        // 每一行都有一个 Q 分不出在哪儿， 所以要用 column 进行标记
        // ref: https://segmentfault.com/a/1190000003762668
        List<Integer> cols = new ArrayList<Integer>();
        dfs(result, cols, n);
        return result;

        // method 1-2: use array instead of List
        // ref: http://www.cnblogs.com/grandyang/p/4377782.html
        // int[] rows = new int[n]; // rows[i] = col, 第 i-th 行放在 col-th 列
        // // initialization
        // for (int i = 0; i < n; i++) {
        //     rows[i] = -1;
        // }
        // dfsArray(result, rows, 0); // start from 0-th row
        // return result;

        // method 2: non-recursion
        // ref: https://www.jiuzhang.com/qa/1572/
        // or another version: http://www.cnblogs.com/TenosDoIt/p/3801621.html
        // int[] rows = new int[n];
        // iteration(result, rows, n);
        // return result;
    }

    // method 1:
    private void dfs(List<List<String>> result, List<Integer> cols, int n) {
        if (cols.size() == n) {
            // draw chess board
            List<String> queens = draw(cols);
            result.add(queens);
            return;
        }
        // dfs
        for (int i = 0; i < n; i++) {
            if (isValid(cols, i)) {
                cols.add(i);
                dfs(result, cols, n);
                cols.remove(cols.size() - 1);
            }
        }
    }
    private boolean isValid(List<Integer> cols, int col) {
        int row = cols.size(); // the row of this col should be in
        for (int i = 0; i < cols.size(); i++) {
            // i is the row index of compared point, its value is column index
            // if (cols.get(i) == col || cols.get(i) - i == col - row || cols.get(i) + i == col + row) {
            if (cols.get(i) == col || Math.abs(row - i) == Math.abs(cols.get(i) - col)) {
                // 不在同一列 || 不在主对角线 || 不在副对角线
                // 注意 main diagonal! 不可以用 abs， 反例： (2, 0), (1, 3), 所以两边保持一样的顺序做减法就可以了, e.g. (col - row) for both or (row - col) for both
                return false;
            }
        }
        return true;
    }
    private List<String> draw(List<Integer> cols) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < cols.size(); i++) {
            // 注意每行写一个 string
            String row = "";
            int col = cols.get(i);
            for (int j = 0; j < col; j++) {
                row += ".";
            }
            row += "Q";
            for (int j = col + 1; j < cols.size(); j++) {
                row += ".";
            }
            result.add(row);
        }
        return result;
    }

    // method 1-2:
    private void dfsArray(List<List<String>> result, int[] rows, int row) {
        int n = rows.length;
        if (row == n) {
            // found a possible solution
            List<String> queens = drawArray(rows);
            result.add(queens);
            return;
        }
        for (int col = 0; col < n; col++) {
            // scan each column for current row, check if it's valid
            if (isValidArray(rows, row, col)) {
                rows[row] = col; // 这一行的 value 为该放置的列数
                dfsArray(result, rows, row + 1);
                // back-tracking: 重新设置为 -1， 表示这一行没定下来放在哪列
                rows[row] = -1;
            }
        }
    }
    private boolean isValidArray(int[] rows, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (col == rows[i] || Math.abs(row - i) == Math.abs(rows[i] - col)) {
                // 之前这列要没放过 || 之前放置的不能是左上角或右上角， 即行数和列数差 1
            // or we can say similar to method 1:
            // if (rows[i] == col || rows[i] - i == col - row || rows[i] + i == col + row) {
            // 不在同一列 || 不在主对角线 || 不在副对角线
                return false;
            }
        }
        return true;
    }
    private List<String> drawArray(int[] rows) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < rows.length; i++) {
            // 注意每行写一个 string
            String row = "";
            int col = rows[i];
            for (int j = 0; j < col; j++) {
                row += ".";
            }
            row += "Q";
            for (int j = col + 1; j < rows.length; j++) {
                row += ".";
            }
            result.add(row);
        }
        return result;
    }

    // method 2:
    private void iteration(List<List<String>> result, int[] rows, int n) {
        // initialize rows
        for (int i = 0; i < n; i++) {
            rows[i] = -1; // -1 means the i-th row is unoccupied
        }
        // iteration: use while loop to simulate recursion
        int index = 0;
        while (index != -1) {
            // exit
            if (index == n) {
                // found all raw to be a possible answer
                List<String> queens = drawIte(rows);
                result.add(queens);
                index--; // go back to upper row to check different solution
            }
            boolean found = false; // flag
            for (int i = rows[index] + 1; i < n; i++) {
                // 其实没有太懂， 没有拿一个栗子走一遍， 感觉 index 表示第几行， 然后从 0 开始， 找到了后把 i 设成这一行的 Q 应在的位置 - 也就是第几列，
                if (isValidIte(rows, index, i)) {
                    rows[index] = i;
                    index++;
                    found = true;
                    break;
                }
            }
            // 如果没有找到， 就说明有冲突， 重新设置为 -1， 然后往上一层重新找
            if (!found) {
                rows[index] = -1;
                index--;
            }
        }
    }
    private List<String> drawIte(int[] row) {
        List<String> queens = new ArrayList<>();
        for (int i = 0; i < row.length; i++) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < row.length; j++) {
            if (j == row[i]) {
                sb.append('Q');
            }
            else {
                sb.append('.');
            }
        }
            queens.add(sb.toString());
        }
        return queens;
    }
    private boolean isValidIte(int[] rows, int row, int col) {
        for (int i = 0; i < row; i++) {
            // 查询和之前的是否有冲突
            if (rows[i] == col || Math.abs(rows[i] - col) == Math.abs(i - row)) {
                //
                return false;
            }
        }
        return true;
    }
};

// 389. Valid Sudoku
// 中文English
// Determine whether a Sudoku is valid.
//
// The Sudoku board could be partially filled, where empty cells are filled with the character ..
//
// Example
// Example 1:
//
// Input:
// ["53..7....","6..195...",".98....6.","8...6...3","4..8.3..1","7...2...6",".6....28.","...419..5","....8..79"]
// Output: true
// Explanation:
// The sudoku is look like this. It's vaild.
// Valid Sudoku
//
// Example 2:
//
// Input:
// ["53..7j...","6..195...",".98....6.","8...6...3","4..8.3..1","7...2...6",".6....28.","...419..5","....8..79"]
// Output: false
// Explanation:
// The sudoku is look like this. It's invaild because there are two '5' in the first row and the sixth line.
// image
//
// Clarification
// What is Sudoku?
//
// http://sudoku.com.au/TheRules.aspx
// https://zh.wikipedia.org/wiki/數獨
// https://en.wikipedia.org/wiki/Sudoku
// http://baike.baidu.com/subview/961/10842669.htm
// Notice
// A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.


public class Solution {
    /*
     * @param board: the board
     * @return: whether the Sudoku is valid
     */
    public boolean isValidSudoku(char[][] board) {
        // write your code here
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            boolean[] valid = new boolean[9];
            for (int j = 0; j < m; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                if (isValid(valid, c) == false) {
                    return false;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            boolean[] valid = new boolean[9];
            for (int j = 0; j < n; j++) {
                char c = board[j][i];
                if (c == '.') {
                    continue;
                }
                if (isValid(valid, c) == false) {
                    return false;
                }
            }
        }
        // don't forget to check small 3 * 3 matrix - then we don't need to check column and row, we just check whether same digit occur more than once
        for (int i = 0; i < n; i += 3) {
            for (int j = 0; j < m; j += 3) {
                boolean[] valid = new boolean[9];
                for (int k = 0; k < 9; k++) {
                    char c = board[i + k / 3][j + k % 3];
                    if (c == '.') {
                        continue;
                    }
                    if (isValid(valid, c) == false) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    private boolean isValid(boolean[] valid, char c) {
        int cell = c - '0';
        if (cell > 9 || cell < 1) {
            return false;
        }
        if (valid[cell - 1] == true) {
            return false;
        }
        valid[cell - 1] = true;
        return true;
    }
};

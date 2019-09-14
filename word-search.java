// 123. Word Search
// 中文English
// Given a 2D board and a word, find if the word exists in the grid.
//
// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
// Example
// Example 1:
//
// Input：["ABCE","SFCS","ADEE"]，"ABCCED"
// Output：true
// Explanation：
// [
//      A B C E
//      S F C S
//      A D E E
// ]
// (0,0)A->(0,1)B->(0,2)C->(1,2)C->(2,2)E->(2,1)D
// Example 2:
//
// Input：["z"]，"z"
// Output：true
// Explanation：
// [ z ]
// (0,0)z


public class Solution {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        // write your code here
        // the 1st method came into my mind is BFS used in matrix
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }
        if (word.length() == 0) {
            return true;
        }
        int n = board.length;
        int m = board[0].length;
        // int[] dx = {1, -1, 0, 0};
        // int[] dy = {0, 0, 1, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
        // BFS would be very wasted and not easy to complete it
        // it should use DFS

    }
    private boolean dfs(char[][] board, String word, int i, int j, int start) {
        if (start == word.length()) {
            return true;
        }
        if (!inBound(board, i, j)) {
            return false;
        }
        if (board[i][j] != word.charAt(start)) {
            return false;
        }
        board[i][j] = '$';
        // boolean up = dfs(board, word, i + 1, j, start + 1);
        // boolean down = dfs(board, word, i - 1, j, start + 1);
        // boolean left = dfs(board, word, i, j - 1, start + 1);
        // boolean right = dfs(board, word, i, j + 1, start + 1);
        boolean result =  dfs(board, word, i + 1, j, start + 1) || dfs(board, word, i - 1, j, start + 1) || dfs(board, word, i, j - 1, start + 1) || dfs(board, word, i, j + 1, start + 1);
        board[i][j] = word.charAt(start);
        return result;
    }
    private boolean inBound(char[][] board, int i, int j) {
        int n = board.length;
        int m = board[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return false;
        } else {
            return true;
        }
    }

    // private boolean bfs(char[][] board, String word, int i, int j) {
    //     Queue<Coordinate> queue = new LinkedList<>();
    //     Coordinate point = new Coordinate(i, j);
    //     queue.offer(point);
    //     int index = 1;
    //     while (!queue.isEmpty()) {
    //         Coordinate coor = queue.poll();
    //         for (int k = 0; k < 4; k++) {
    //             Coordinate nei = new Coordinate(coor.x + dx[k], coor.y + du[k]);
    //             // whether match word
    //             if (boar[nei.x][nei.y] != word.charAt(index)) {
    //                 continue;
    //             }
    //             target = target + board[nei.x][nei.y];
    //         }
    //     }
    // }
    // private inBound(char[][] board) {

    // }
}
// class Coordinate {
//     int x;
//     int y;
//     Coordinate(int i, int j) {
//         x = i;
//         y = j;
//     }
// }

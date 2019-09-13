// 477. Surrounded Regions
// 中文English
// Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
//
// A region is captured by flipping all 'O''s into 'X''s in that surrounded region.
//
// Example
// Example 1:
//
// Input:
//   X X X X
//   X O O X
//   X X O X
//   X O X X
// Output:
//   X X X X
//   X X X X
//   X X X X
//   X O X X
// Example 2:
//
// Input:
//   X X X X
//   X O O X
//   X O O X
//   X O X X
// Output:
//   X X X X
//   X O O X
//   X O O X
//   X O X X


public class Solution {
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    public void surroundedRegions(char[][] board) {
        // write your code here
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int n = board.length;
        int m = board[0].length;
        // 从例子来看， 只要 O 不在边界上， 里面的连成片就都可以变成 X
        // 可以从边界上开始扫， 如果有 O 那就 BFS 把连在一起的全变成另一个字符， 然后最后扫一遍， 仍然是 O 的变成 X， 另一个字符还原成 O

        // 1: scan boarders
        for (int i = 0; i < n; i++) {
            // left border
            if (board[i][0] == 'O') {
                bfs(board, new Coordinator(i, 0));
            }
            // right border
            if (board[i][m - 1] == 'O') {
                bfs(board, new Coordinator(i, m - 1));
            }
        }
        for (int j = 0; j < m; j++) {
            // top border
            if (board[0][j] == 'O') {
                bfs(board, new Coordinator(0, j));
            }
            // bottom border
            if (board[n - 1][j] == 'O') {
                bfs(board, new Coordinator(n - 1, j));
            }
        }
        // 2: set remained 'O' to 'X' and reset $' back to 'O'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '$') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void bfs(char[][] board, Coordinator curr) {
        // change this site to another char
        board[curr.x][curr.y] = '$';
        Queue<Coordinator> queue = new LinkedList<>();
        queue.offer(curr);
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            Coordinator coor = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = coor.x + dx[i];
                int y = coor.y + dy[i];
                Coordinator nei = new Coordinator(x, y);
                if (!isValid(board, x, y)) {
                    continue;
                }
                if (board[x][y] == 'O') {
                    board[x][y] = '$';
                    queue.offer(nei);
                }
            }
        }
    }
    private boolean isValid(char[][] board, int x, int y) {
        int n = board.length;
        int m = board[0].length;
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        } else {
            return false;
        }
    }
}
class Coordinator {
    int x;
    int y;
    public Coordinator(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

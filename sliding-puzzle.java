// 941. Sliding Puzzle
// 中文English
// On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
//
// A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
//
// The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
//
// Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
//
// Example
// Example 1:
//
// Given board = `[[1,2,3],[4,0,5]]`, return `1`.
//
// Explanation:
// Swap the 0 and the 5 in one move.
// Example 2：
//
// Given board = `[[1,2,3],[5,4,0]]`, return `-1`.
//
// Explanation:
// No number of moves will make the board solved.
// Example 3:
//
// Given board = `[[4,1,2],[5,0,3]]`, return `5`.
//
// Explanation:
// 5 is the smallest number of moves that solves the board.
// An example path:
// After move 0: [[4,1,2],[5,0,3]]
// After move 1: [[4,1,2],[0,5,3]]
// After move 2: [[0,1,2],[4,5,3]]
// After move 3: [[1,0,2],[4,5,3]]
// After move 4: [[1,2,0],[4,5,3]]
// After move 5: [[1,2,3],[4,5,0]]
// Example 4:
//
// Given board = `[[3,2,4],[1,5,0]]`, return `14`.
// Notice
// board will be a 2 x 3 array as described above.
// board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].


public class Solution {
    /**
     * @param board: the given board
     * @return:  the least number of moves required so that the state of the board is solved
     */
    public int slidingPuzzle(int[][] board) {
        // write your code here

        // return mytry(board);

        return method1(board);
    }

    private int method1(int[][] matrix) {
        Matrix root = new Matrix(matrix);
        Queue<Matrix> queue = new LinkedList<>();
        queue.offer(root);
        int result = -1;
        Set<Integer> set = new HashSet<>();
        set.add(root.hash());

        int[][] completed = {{1, 2, 3}, {4, 5, 0}};
        Matrix res = new Matrix(completed);
        int target = res.hash();

        while (!queue.isEmpty()) {
            Matrix curr = queue.poll();
            if (curr.hash() == target) {
                result = curr.step;
                break;
            }
            int x = 0;
            int y = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    if (curr.nums[i][j] == 0) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
            Matrix next;
            next = curr.up(x, y);
            if (next != null && !set.contains(next.hash())) {
                queue.offer(next);
                set.add(next.hash());
            }
            next = curr.down(x, y);
            if (next != null && !set.contains(next.hash())) {
                queue.offer(next);
                set.add(next.hash());
            }
            next = curr.left(x, y);
            if (next != null && !set.contains(next.hash())) {
                queue.offer(next);
                set.add(next.hash());
            }
            next = curr.right(x, y);
            if (next != null && !set.contains(next.hash())) {
                queue.offer(next);
                set.add(next.hash());
            }
        }

        return result;
    }
    private class Matrix {
        public int[][] nums = new int[3][3];
        public int step = 0;
        public Matrix(int[][] matrix) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    nums[i][j] = matrix[i][j];
                }
            }
        }

        public int hash() {
            int hash = 1;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    hash = (hash * SEED + nums[i][j]) % MOD;
                }
            }
            return hash;
        }

        // (x, y) 表示当前的位置， 然后把 [x][y] 向上下左右移动
        public Matrix up(int x, int y) {
            if (x == 0) {
                return null;
            }
            Matrix state = new Matrix(this.nums);
            state.step = this.step + 1;
            int temp = state.nums[x][y];
            state.nums[x][y] = state.nums[x - 1][y];
            state.nums[x - 1][y] = temp;
            return state;
        }

        public Matrix down(int x, int y) {
            if (x == 1) {
                return null;
            }
            Matrix state = new Matrix(this.nums);
            state.step = this.step + 1;
            int temp = state.nums[x][y];
            state.nums[x][y] = state.nums[x + 1][y];
            state.nums[x + 1][y] = temp;
            return state;
        }

        public Matrix left(int x, int y) {
            if (y == 0) {
                return null;
            }
            Matrix state = new Matrix(this.nums);
            state.step = this.step + 1;
            int temp = state.nums[x][y];
            state.nums[x][y] = state.nums[x][y - 1];
            state.nums[x][y - 1] = temp;
            return state;
        }

        public Matrix right(int x, int y) {
            if (y == 2) {
                return null;
            }
            Matrix state = new Matrix(this.nums);
            state.step = this.step + 1;
            int temp = state.nums[x][y];
            state.nums[x][y] = state.nums[x][y + 1];
            state.nums[x][y + 1] = temp;
            return state;
        }
    }

    private int mytry(int[][] nums) {
        // MLE
        int[] state = new int[1];
        state[0] = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (nums[i][j] == 0) {
                    // visited[i][j] = true;
                    // set.add(getHash(nums));
                    dfs(nums, i, j, set, state, 0);
                }
            }
        }
        return state[0] == Integer.MAX_VALUE ? -1 : state[0];
    }

    public int[] dx = {0, 0, 1, -1};
    public int[] dy = {1, -1, 0, 0};

    private void dfs(int[][] nums, int i, int j, Set<Integer> set, int[] state, int count) {
        if (checkMatrix(nums, i, j)) {
            state[0] = Math.min(state[0], count);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x < 0 || x >= 2 || y < 0 || y >= 3) {
                continue;
            }

            int hash = getHash(nums);
            if (set.contains(hash)) {
                continue;
            }

            nums[i][j] = nums[x][y];
            nums[x][y] = 0;
            set.add(hash);
            // print(nums);
            dfs(nums, x, y, set, state, count + 1);
            if (state[0] == 1) {
                return;
            }
            nums[x][y] = nums[i][j];
            nums[i][j] = 0;
            set.remove(hash);
        }

    }
    private boolean checkMatrix(int[][] nums, int x, int y) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (!checkPoint(nums, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkPoint(int[][] nums, int i, int j) {
        return (i * 2 + j + 1) == nums[i][j];
    }
    public final int SEED = 33;
    public final int MOD = Integer.MAX_VALUE / SEED;
    private int getHash(int[][] nums) {
        int hash = 1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                hash = (hash * SEED + nums[i][j]) % MOD;
            }
        }
        return hash;
    }
    private void print(int[][] nums) {
        System.out.print("[");
        for (int i = 0; i < 2; i++) {
            System.out.print("[");
            for (int j = 0; j < 3; j++) {
                System.out.print(nums[i][j] + ",");
            }
            System.out.print("]");
        }
        System.out.println("]");
    }

    // private int mytry(int[][] nums) {

    //     int[] state = new int[1];
    //     Set<Integer> set = new HashSet<>();
    //     for (int i = 0; i < 2; i++) {
    //         for (int j = 0; j < 3; j++) {
    //             if (nums[i][j] == 0) {
    //                 // visited[i][j] = true;
    //                 // set.add(getHash(nums));
    //                 bfs(nums, i, j, set, state);
    //                 if (state[0] == 1) {
    //                     return "YES";
    //                 } else {
    //                     return "NO";
    //                 }
    //             }
    //         }
    //     }
    //     return "NO";
    // }
    // private int bfs(int[][] nums, int i, int j) {
    //     int[] dx = {0, 0, 1, -1};
    //     int[] dy = {1, -1, 0, 0};
    //     Queue<Coordinator> queue = new LinkedList<>();
    //     Coordinator root = new Coordinator(i, j);
    //     queue.offer(root);
    //     int count = 0;
    //     while (!queue.isEmpty()) {
    //         Coordinator curr = queue.poll();
    //         count++;
    //         for (int k = 0; k < 4; k++) {
    //             int x = curr.x + dx[k];
    //             int y = curr.y + dy[k];
    //             if (x < 0 || x >= 2 || y < 0 || y >= 3) {
    //                 continue;
    //             }

    //         }
    //     }
    // }
    // private class Coordinator {
    //     public int x;
    //     public int y;
    //     public Coordinator(int x, int y) {
    //         this.x = x;
    //         this.y = y;
    //     }
    // }
}

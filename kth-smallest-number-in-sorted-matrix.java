// 401. Kth Smallest Number in Sorted Matrix
// 中文English
// Find the kth smallest number in a row and column sorted matrix.
//
// Each row and each column of the matrix is incremental.
//
// Example
// Example 1:
//
// Input:
// [
//   [1 ,5 ,7],
//   [3 ,7 ,8],
//   [4 ,8 ,9],
// ]
// k = 4
// Output: 5
// Example 2:
//
// Input:
// [
//   [1, 2],
//   [3, 4]
// ]
// k = 3
// Output: 3
// Challenge
// O*(klogn*) time, n is the maximum of the width and height of the matrix.
//


public class Solution {
    /*
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        if (k == 0) {
            return matrix[0][0];
        }
        int n = matrix.length;
        int m = matrix[0].length;
        // my try: make all numbers as Pair with their col and row index, then use PQ to sort all of them, O(nm) time, O(k) space
        // PriorityQueue<Pair> pq = new PriorityQueue<>(k, new Comparator<Pair>() {
        //     @Override
        //     public int compare(Pair p1, Pair p2) {
        //         return p1.val - p2.val; // min-heap
        //     }
        // });
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         Pair pair = new Pair(matrix[i][j], i, j);
        //         pq.offer(pair);
        //     }
        // }
        // int index = 1;
        // while (index < k) {
        //     pq.poll();
        //     index++;
        // }
        // return pq.poll().val;

        // method 2: use PQ too, but 是像 BSF 那样， 拿出一个， 把它右边和下边的放进去， similar to scan diagonally
        PriorityQueue<Pair> pq = new PriorityQueue<>(k, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.val - p2.val; // min-heap
            }
        });
        // direction: only right and down
        int[] dcol = {0, 1};
        int[] drow = {1, 0};
        boolean[][] visited = new boolean[n][m];
        // create start Pair
        Pair root = new Pair(matrix[0][0], 0, 0);
        pq.offer(root);
        // scan other k - 1 pair
        for (int i = 1; i < k; i++) {
            Pair curr = pq.poll();
            int currCol = curr.col;
            int currRow = curr.row;
            // similar to BFS, scan right and down number of current number, then check whether it's valid in matrix and never been visisted
            for (int j = 0; j < 2; j++) {
                int nextCol = currCol + dcol[j];
                int nextRow = currRow + drow[j];
                if (!isValid(nextCol, nextRow, n, m) || visited[nextRow][nextCol]) {
                    continue;
                }
                Pair next = new Pair(matrix[nextRow][nextCol], nextRow, nextCol);
                visited[nextRow][nextCol] = true;
                pq.offer(next);
            }
        }
        return pq.poll().val;
    }
    private boolean isValid(int col, int row, int n, int m) {
        if (col >= 0 && col < m && row >= 0 && row < n) {
            return true;
        } else {
            return false;
        }
    }
}
class Pair {
    public int val;
    public int row;
    public int col;
    public Pair(int v, int r, int c) {
        val = v;
        row = r;
        col = c;
    }
}

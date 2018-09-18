// 654. Sparse Matrix Multiplication
// Description
// Given two Sparse Matrix A and B, return the result of AB.
//
// You may assume that A's column number is equal to B's row number.
//
// Have you met this question in a real interview?
// Example
// A = [
//   [ 1, 0, 0],
//   [-1, 0, 3]
// ]
//
// B = [
//   [ 7, 0, 0 ],
//   [ 0, 0, 0 ],
//   [ 0, 0, 1 ]
// ]
//
//
//      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
// AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
//                   | 0 0 1 |


public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = A[0].length;
        int k = B[0].length;

        int[][] C = new int[n][k];

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (A[i][j] != 0)
                    for (int l = 0; l < k; ++l)
                        C[i][l] += A[i][j] * B[j][l];
        return C;
    }
}


answer:

public class Solution {
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = A[0].length;
        int k = B[0].length;

        int[][] C = new int[n][k];

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (A[i][j] != 0)
                    for (int l = 0; l < k; ++l)
                        C[i][l] += A[i][j] * B[j][l];
        return C;
    }
}

// version: 高频题班
//常规做法
public class Solution {
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < t; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}



//改进做法
public class Solution {
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < m; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}


//进一步改进
public class Solution {
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        List<List<Integer>> col = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            col.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                if (B[i][j] != 0) {
                    col.get(i).add(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] == 0) {
                    continue;
                }
                for (int p = 0; p < col.get(k).size(); p++) {
                    int j = col.get(k).get(p);
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}

// another version:
// 分别将A,B稀疏矩阵中有效值存储在PointA和PointB中，只需要将两组Point做乘法，然后将结果Point转化成新矩阵即可。
// 时间复杂度O(mn) 空间复杂度取决于稀疏程度。

public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        List<Point> pA = new ArrayList<>();
        List<Point> pB = new ArrayList<>();
        //find non-zero points
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[0].length; j++){
                if(A[i][j] != 0){
                    pA.add(new Point(i, j, A[i][j]));
                }
            }
        }
        for(int i = 0; i < B.length; i++){
            for(int j = 0; j < B[0].length; j++){
                if(B[i][j] != 0){
                    pB.add(new Point(i, j, B[i][j]));
                }
            }
        }

        //A * B
        List<Point> result = new ArrayList<>();
        for(Point pointA: pA){
            for(Point pointB: pB){
                if(pointA.y == pointB.x){
                    result.add(new Point(pointA.x, pointB.y, pointA.value * pointB.value));
                }
            }
        }

        //build matrix
        int[][] C = new int[A.length][B[0].length];
        for(Point pointC: result){
            C[pointC.x][pointC.y] += pointC.value;
        }

        return C;
    }
}

class Point{
    int x;
    int y;
    int value;
    public Point(int x, int y, int v){
        this.x = x;
        this.y = y;
        value = v;
    }
}

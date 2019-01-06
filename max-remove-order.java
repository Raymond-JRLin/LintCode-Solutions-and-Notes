// 1641. Max Remove Order
// Description
// Give a m * n board with a value of 0 or 1. At each step we can remove a point whose value is 1 and there is another 1 in the same row or in the same column. Find a remove order which allows us to remove the most points, return the max number of points we can remove.
//
// n and m do not exceed 5050
//
// Have you met this question in a real interview?
// Example
// Give mp=[[1,0,1],[1,0,0]], return 2.
//
// Explanation:
// In the board
// 1, 0, 1
// 1, 0, 0
// We can firstly remove the point on (0,2), then remove the point on (1,0).
// If we firstly remove the point on (0,0),
// then we can only remove one point.
// Give mp=[[1,0],[1,0]], return 1.
//
// Explanation:
// In the board
// 1, 0
// 1, 0
// We can only remove (0,0) or (1,0)


public class Solution {
    /**
     * @param mp: the board
     * @return: the max number of points we can remove
     */
    public int getAns(int[][] mp) {
        // Write your code here.
        if (mp == null || mp.length == 0 || mp[0].length == 0) {
            return 0;
        }

        return method1(mp);
    }

    private int method1(int[][] matrix) {
        // Union Find
        // 实际上按照题意， 每一行/列的 1 都算在一起， 那就是把它们当作同一个集合， 就可以考虑 Union Find, 如果一个集合里有 n 个点， 那么一共可以 remove 掉 n - 1 个点
        // 这里写 UF 有点儿 tricky， 和通常的不太一样， 差别在于：
        // 1. 2D 转换成 1D， 这个其实简单， 通常的 index 转换就可以了
        // 2. 如何来记录每个集合里有多少个点呢？ 可以另外使用一个数组 count[][]， 记录的是以 [i][j] 为 root 的集合有多少个 1， 当然是 1 的点并入总 root 的位置也会是为 1， 因为 initialize 为 1 了， 这里初始化为 1 也便于计算， 直接将这个位置并入 root， 最后在求和 - 求所有集合的时候， 让 count[i][j] - 1 就为可以 remove 的个数， 即减掉自身， 所以为 1 的地方减 1 就为 0， 既 make sense, 也好计算
        int n = matrix.length;
        int m = matrix[0].length;
        UnionFind uf = new UnionFind(n * m);
        // check rows： 把每一行向左端合并
        // 注意把 2D 坐标转换成 1D 是乘以 column 数
        for (int i = 0; i < n; i++) {
            int index = -1;
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    if (index == -1) {
                        index = i * m + j;
                    } else {
                        // 在 Union Find 中， 合并到第二个参数的位置上， 最终的结果就会是并到最先遇到的的行和列, 即向左上角方向合并
                        uf.connect(i * m + j, index);
                    }
                }
            }
        }
        // for (int num : uf.counts) {
        //     System.out.print(num + " ");
        // }
        // System.out.println();
        // for (int num : uf.nums) {
        //     System.out.print(num + " ");
        // }
        // System.out.println();
        // check columns： 把每一列向上端合并
        for (int j = 0; j < m; j++) {
            int index = -1;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] == 1) {
                    if (index == -1) {
                        index = i * m + j;
                    } else {
                        uf.connect(i * m + j, index);
                    }
                }
            }
        }
        int result = 0;
        for (int num : uf.counts) {
            // System.out.print(num + " ");
            result += (num - 1);
        }
        // System.out.println();
        // for (int num : uf.nums) {
        //     System.out.print(num + " ");
        // }
        return result;

        // e.g.
        // [[1,1,1,1],
        //  [1,1,0,1],
        //  [1,0,1,0]]
        // after checking row:
        // uf.counts: 4 1 1 1 3 1 1 1 2 1 1 1
        // uf.nums:   0 0 0 0 4 4 6 4 8 9 8 11
        // 可以看到第一行都并入 0， 第二行并入 4， 第三行并入 8
        // after checking col:
        // uf.counts: 9 1 1 1 1 1 1 1 1 1 1 1
        // uf.nums:   0 0 0 0 0 0 6 0 0 9 0 11
        // 因为每行都并入第一列， 最后会并入 [0, 0]
    }
    private class UnionFind {
        private int[] nums; // father 数组
        private int[] counts; // 用来记录有几个 1

        public UnionFind(int n) {
            this.nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i;
            }
            this.counts = new int[n];
            Arrays.fill(counts, 1); //初始化为 1
        }

        private void connect(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                nums[rootA] = rootB;
                // 增加下面两条语句来计算总的 1 的个数
                // 都往 rootB 中并
                counts[rootB] += counts[rootA];
                counts[rootA] = 1;
            }
        }

        private int find(int i) {
            while (i != nums[i]) {
                nums[i] = nums[nums[i]]; // path compression
                i = nums[i];
            }
            return i;
        }
    }
}

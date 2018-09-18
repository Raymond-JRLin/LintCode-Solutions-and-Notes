// 1396. Set Union

// There is a list composed by sets. If two sets have the same elements, merge them. In the end, there are several sets left.
//
// The number of sets n <=1000.
// The number of elements for each set m <= 100.
// The element must be a non negative integer and not greater than 100000.

// Example
// Given list = [[1,2,3],[3,9,7],[4,5,10]], return 2.
//
// Explanation:
// There are 2 sets of [1,2,3,9,7] and [4,5,10] left.
// Given list = [[1],[1,2,3],[4],[8,7,4,5]], return 2.
//
// Explanation:
// There are 2 sets of [1,2,3] and [4,5,7,8] left.


public class Solution {
    /**
     * @param sets: Initial set list
     * @return: The final number of sets
     */
    public int setUnion(int[][] sets) {
        // Write your code here
        if (sets == null || sets.length == 0 || sets[0].length == 0) {
            return 0;
        }

        // return method1(sets);

        return method2(sets);
    }

    private int method2(int[][] sets) {
        // 记录具体某个数是在第几行
        int[] index = new int[100001];
        Arrays.fill(index, -1);

        UnionFind uf = new UnionFind(sets.length);

        for (int i = 0; i < sets.length; i++) {
            for (int j = 0; j < sets[i].length; j++) {
                int num = sets[i][j];
                if (index[num] != -1) {
                    uf.connect(i, index[num]);
                } else {
                    index[num] = i;
                }
            }
        }

        return uf.count;
    }
    private class UnionFind {
        private int[] nums;
        private int count;

        public UnionFind(int n) {
            this.nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i;
            }
            this.count = n;
        }

        private void connect(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                nums[rootA] = rootB;
                count--;
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

    private int method1(int[][] sets) {
        // 记录这一行的连接后的 index 是几， 如果不是它原来的， 那么就和其他的 sets[i] 合并了
        int[] uf = new int[1001];
        for (int i = 0; i < uf.length; i++) {
            uf[i] = i;
        }
        // 记录具体某个数是在第几行
        int[] index = new int[100001];
        Arrays.fill(index, -1);

        for (int i = 0; i < sets.length; i++) {
            for (int j = 0; j < sets[i].length; j++) {
                int num = sets[i][j];
                if (index[num] != -1) {
                    // 在前面已经出现过了
                    connect(uf, i, index[num]); // 将当前 uf 记录的行数变为前面出现过的行数
                    // index[num] = find(uf, i); // 更改当前数字对应的行数 （其实没有更改， 因为 connect 只是把 uf 的 i 更改为出现过的行数）
                } else {
                    // 新的数字， 就直接记录为当前的行数
                    // index[num] = find(uf, i);
                    index[num] = i;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < sets.length; i++) {
            // 查找那些依然还在自己本身这一行， 不在的说明已经合并了
            if (uf[i] == i) {
                result++;
            }
        }
        return result;
    }
    private void connect(int[] nums, int a, int b) {
        int rootA = find(nums, a);
        int rootB = find(nums, b);
        if (rootA != rootB) {
            nums[rootA] = rootB;
        }
    }
    private int find(int[] nums, int i) {
        while (i != nums[i]) {
            nums[i] = nums[nums[i]]; // path compression
            i = nums[i];
        }
        return i;
    }
}

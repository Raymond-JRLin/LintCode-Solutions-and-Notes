// 1637. Tree problem
// Description
// Given a tree of n nodes. The ith node's father is fa[i-1] and the value of the ith node is val[i-1]. Especially, 1 represents the root, 2 represents the second node and so on. We gurantee that -1 is the father of root(the first node) which means that fa[0] = -1.
// The average value of a subtree is the result of the sum of all nodes in the subtree divide by the number of nodes in it.
// Find the maximum average value of the subtrees in the given tree, return the number which represents the root of the subtree.
//
// the number of nodes do not exceed 100000
// If there are more than one subtree meeting the requirement, return the minimum number.
//
// Have you met this question in a real interview?
// Example
// Given fa=[-1,1,1,2,2,2,3,3], representing the father node of each point, and val=[100,120,80,40,50,60,50,70] , representing the value of each node, return 1.
//
// Sample diagram：
//                       -1  ------No.1
//                     /     \
//          No.2 ----1        1---------No.3
//                /  |  \     /  \
//               2   2   2    3   3
// No.1 node is (100+120+80+40+50+60+50+70) / 8 = 71.25
// No.2 node are (120 + 40 + 50 + 60) / 4 = 67.5
// No.3 node are (80+50+70) / 3 = 66.6667
// So return 1.


public class Solution {
    /**
     * @param fa: the father
     * @param val: the val
     * @return: the biggest node
     */
    public int treeProblem(int[] fa, int[] val) {
        // Write your code here
        if (fa == null || fa.length == 0) {
            return -1;
        }

        // return mytry(fa, val);

        return method2(fa, val);
    }

    private int method2(int[] fa, int[] val) {
        // 类似于上一种做法， 重要的是找到每个以 node 为 root 的 subtree 的 sum 和 count
        int n = fa.length;
        int[] sums = new int[n];
        int[] counts = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            counts[i]++;
            sums[i] += val[i];
            if (fa[i] != -1) {
                // 注意转成 0-based
                counts[fa[i] - 1] += counts[i];
                sums[fa[i] - 1] += sums[i];
            }
        }
        int result = 0;
        double maxAve = Double.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            double ave = (double) sums[i] / counts[i];
            if (ave > maxAve) {
                maxAve = ave;
                result = i + 1; // 转回 1-based
            }
        }
        return result;
    }

    private int mytry(int[] fa, int[] val) {
        // recursion to do bottom-up
        // construct adjacent list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < fa.length; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < fa.length; i++) {
            // 只做 parent -> children 的方向
            adj.get(fa[i] - 1).add(i); // 0-based
            // adj.get(i).add(fa[i] - 1);
        }
        int[] sums = new int[fa.length]; // 记录 i node 为 root 的 subtree 的 sum
        int[] counts = new int[fa.length]; // 记录 i node 为 root 的 subtree 的个数
        recursion(adj, val, 0, sums, counts);
        return node + 1; // 我自己用的是 0-based， 别忘了 + 1
    }
    private double maxAverage = Double.MIN_VALUE;
    private int node = 0;
    private int recursion(List<List<Integer>> adj, int[] val, int root, int[] sums, int[] counts) {
        if (adj.get(root).size() == 0) {
            // 别忘了 check leaf node 它自身
            if (val[root] > maxAverage) {
                maxAverage = val[root];
                node = root;
            }
            counts[root] = 1;
            return val[root];
        }
        // 计算当前 root 为 root 的 subtree 的情况
        int sum = val[root];
        int count = 0;
        for (int next : adj.get(root)) {
            sum += recursion(adj, val, next, sums, counts);
            // System.out.println("add to " + root + ", by " + val[next]);
            count += counts[next]; // 加 next 为 root 的 subtree 的总个数
        }
        // 别忘了加上 root 自己这个个数
        double ave = (double) sum / ++count;
        // System.out.println("now check " + root + ", sum is " + sum + " , has total " + count + ", ave is " + ave);
        if (ave > maxAverage) {
            maxAverage = ave;
            node = root;
        }
        // update
        sums[root] = sum;
        counts[root] = count;
        return sum;
    }
}

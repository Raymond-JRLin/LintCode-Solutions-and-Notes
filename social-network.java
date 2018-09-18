// 1569. Social Network
// Description
// Everyone has their online friends. Now there are n people and m friend relationships . Ask if any person can directly or indirectly contact all online people. If ok, return "yes", else return "no".
// The friend relationship is represented by a array a and a array b, which means that a[i] and b[i] are a pair of friends.
//
// 1 \leq n \leq 50001≤n≤5000
// 1 \leq m \leq 100001≤m≤10000
// A person may be friends with himself
//
// Have you met this question in a real interview?
// Example
// Given n=4, a=[1,1,1], b=[2,3,4],return yes.
//
// 1 and 2, 3, 4 can be directly contacted
// 2, 3, 4 and 1 can be directly contacted, these 3 people can be indirectly contacted by 1
// Given n=5, a=[1,2,4], b=[2,3,5], return no.
//
// 1,2,3 can be connected to each other
// 4,5 can communicate with each other
// However, the two groups cannot be contacted. For example, 1 cannot contact 4 or 5


public class Solution {
    /**
     * @param n: the person sum
     * @param a: the array a
     * @param b: the array b
     * @return: yes or no
     */
    public String socialNetwork(int n, int[] a, int[] b) {
        // Write your code here
        if (a == null || a.length == 0 || b == null || b.length == 0) {
            return "no";
        }

        // return mytry(n, a, b);

        return method2(n, a, b);
    }

    private String method2(int n, int[] a, int[] b) {
        // union find
        father = new int[n + 1];
        count = n;
        for (int i = 0; i < a.length; i++) {
            connect(a[i], b[i]);
        }
        return count == 1 ? "yes" : "no";
    }
    private int[] father;
    private int count;
    private int find(int i) {
        if (father[i] == 0) {
            return i;
        }
        return father[i] = find(father[i]);
    }
    private void connect(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            father[rootA] = rootB;
            count--;
        }
    }

    private String mytry(int n, int[] a, int[] b) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < a.length; i++) {
            adj.get(a[i]).add(b[i]);
            adj.get(b[i]).add(a[i]);
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a[0]);
        visited.add(a[0]);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (adj.get(curr) != null) {
                for (int next : adj.get(curr)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }

        return visited.size() == n ? "yes" : "no";
    }
}


考点

并查集

题解

对于每对关系建边，做并查集。若两个点拥有共同的祖先，代表这两个点能够相互联系。最后，若所有的人都只有同一个共同的祖先，则代表所有的人能够相互联系。

// answer:

public class Solution {
    /**
     * @param n: the person sum
     * @param a: the array a
     * @param b: the array b
     * @return: yes or no
     */

    private int[] father = null;
    private int count;

    private int find(int x) {
        if (father[x] == 0) {
            return x;
        }
        return father[x] = find(father[x]);
    }

    public void connect(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
            count--;
        }
    }

    public String socialNetwork(int n, int[] a, int[] b) {
        // Write your code here
        father = new int[n + 1];
        int m = a.length;
        count = n;
        for (int i = 1; i <= n; i++) father[i] = 0;
        for (int i = 0; i < m; i++)  connect(a[i], b[i]);
        int fa = find(1);
        if (count == 1) return "yes";
        else            return "no";
    }
}

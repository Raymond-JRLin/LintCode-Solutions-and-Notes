// 1366. Directed Graph Loop
// 中文English
// Please judge whether there is a cycle in the directed graph with n vertices and m edges. The parameter is two int arrays. There is a directed edge from start[i] to end[i].
//
// Example
// Example1
//
// Input: start = [1],end = [2]
// Output: "False"
// Explanation:
// There is only one edge 1->2, and do not form a cycle.
// Example2
//
// Input: start = [1,2,3],end = [2,3,1]
// Output: "True"
// Explanation:
// There is a cycle 1->2->3->1.
// Notice
// 2 <= n <= 10^5
// 1 <= m <= 4*10^5
// 1 <= start[i], end[i] <= n


public class Solution {
    /**
     * @param start: The start points set
     * @param end: The end points set
     * @return: Return if the graph is cyclic
     */
    public boolean isCyclicGraph(int[] start, int[] end) {
        // Write your code here

        // return mytry(start, end); // MLE

        // return mytry2(start, end); // still MLE

        // BFS 应该不行了， 因为每次都要把下一个放进去这样时间空间复杂度都会很高， 而且还不能用 visited 判断
        return method1(start, end);
    }

    public boolean method1(int[] start, int[] end) {
        // 使用拓扑排序， 如果无法完成拓扑排序， 则有向图存在环， 时间复杂度 O(n + m)。
        int n = 100001;
        int[] indegree = new int[n];
        boolean[] has = new boolean[n]; // record what numbers are really exist in given arrays
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // get in-degree and adjacent list
        for (int i = 0; i < start.length; i++) {
            int s = start[i];
            int e = end[i];
            adj.get(s).add(e);
            has[s] = true;
            has[e] = true;
            indegree[e]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (has[i] && indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // no startings
        if (queue.isEmpty()) {
            return true;
        }
        // BFS
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : adj.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // check
        for (int i = 0; i < n; i++) {
            if (has[i] && indegree[i] != 0) {
                // 如果这个点给了， 但是没有被放进 queue 里过， 说明一定有另一个点指向它也同时被指向， 构成环
                return true;
            }
        }

        return false;
    }


    public boolean mytry2(int[] start, int[] end) {
        int n = start.length;
        int len = 2;
        for (int i = 0; i < n; i++) {
            len = Math.max(len, Math.max(start[i], end[i]));
        }
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < len + 1; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; i++) {
            int s = start[i];
            int e = end[i];
            adj.get(s).add(e);
        }
        for (int s : start) {
            if (adj.get(s).size() == 0) {
                continue;
            }
            if (bfs2(adj, s)) {
                return true;
            }
        }
        return false;
    }
    private boolean bfs2(List<List<Integer>> adj, int start) {
        Queue<Integer> queue = new LinkedList<>();
        for (int root : adj.get(start)) {
            if (root == start) {
                return true;
            }
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (adj.get(curr).size() == 0) {
                continue;
            }
            for (int next : adj.get(curr)) {
                if (next == start) {
                    return true;
                }
                queue.offer(next);
            }
        }
        return false;
    }

    private boolean mytry(int[] start, int[] end) {
        int n = start.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int s = start[i];
            int e = end[i];
            List<Integer> list = map.getOrDefault(s, new ArrayList<Integer>());
            list.add(e);
            map.put(s, list);
        }
        for (int s : start) {
            if (!map.containsKey(s)) {
                continue;
            }
            if (bfs(map, s)) {
                return true;
            }
        }
        return false;
    }
    private boolean bfs(Map<Integer, List<Integer>> map, int start) {
        Queue<Integer> queue = new LinkedList<>();
        for (int root : map.get(start)) {
            if (root == start) {
                return true;
            }
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (!map.containsKey(curr)) {
                continue;
            }
            for (int next : map.get(curr)) {
                if (next == start) {
                    return true;
                }
                queue.offer(next);
            }
        }
        return false;
    }
}

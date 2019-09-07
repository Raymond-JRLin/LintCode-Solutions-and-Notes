// 932. Friends Within Three Jumps
// 中文English
// Given two array a and b, a[i] and b[i] are friends. And then given two query arrays c and d, find whether c[i] and d[i] are friends within three jumps.(i.e A and B are friends, B and C are friends, so B is a one-jump friend of A and C is a two-jumps friend of A)
//
// Example
// Example1
//
// Input: a = [1,2], b = [2,3], c = [1], d = [3]
// Output: [1]
// Explanation:
// 1 → 2 → 3 ，3 is a two-jumps friend of 1.
// Example2
//
// Input: a = [1,2,3,4], b = [2,3,4,5], c = [1,1], d = [4,5]
// Output: [1,0]
// Explanation:
// 1 → 2 → 3 → 4 → 5，4 is a three-jumps friend of 1, 5 is a four-jumps friend of 1.
// Notice
// The length of all arrays do not exceed 1000.
// If there is more than one friend relationship chain, calculate the relationship of least jumps.
// c[i] may equal to d[i]


public class Solution {
    /**
     * @param a: The a tuple
     * @param b: The b tuple
     * @param c: the c tuple
     * @param d: the d tuple
     * @return: The answer
     */
    public int[] withinThreeJumps(int[] a, int[] b, int[] c, int[] d) {
        // Write your code here
        if (a == null || a.length == 0 || b == null || b.length ==0) {
            return new int[0];
        }

        // return mytry(a, b, c, d);

        return method2(a, b, c, d);
    }

    private int[] method2(int[] a, int[] b, int[] queries, int[] targets) {
        List<List<Integer>> relations = new ArrayList<>();
        for (int i = 0; i < 1001; i++) {
            List<Integer> list = new ArrayList<>();
            relations.add(list);
        }
        for (int i = 0; i < a.length; i++) {
            relations.get(a[i]).add(b[i]);
            relations.get(b[i]).add(a[i]);
        }

        int n = queries.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = dfs(relations, queries[i], targets[i], 0, 3);
        }

        return result;
    }
    private int dfs(List<List<Integer>> relations, int query, int target, int curr, int layer) {
        if (query == target) {
            return 1;
        }
        if (curr == layer) {
            return 0;
        }
        for (int next : relations.get(query)) {
            if (dfs(relations, next, target, curr + 1, layer) == 1) {
                return 1;
            }
        }
        return 0;
    }

    private int[] mytry(int[] a, int[] b, int[] queries, int[] targets) {
        int n = queries.length;
        int[] result = new int[n];

        // construct friendship relation
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            Set<Integer> setA = map.getOrDefault(a[i], new HashSet<Integer>());
            setA.add(b[i]);
            map.put(a[i], setA);
            Set<Integer> setB = map.getOrDefault(b[i], new HashSet<Integer>());
            setB.add(a[i]);
            map.put(b[i], setB);
        }

        // BFS
        for (int i = 0; i < n; i++) {
            int query = queries[i];
            int target = targets[i];
            if (!map.containsKey(query)) {
                result[i] = 0;
                continue;
            }
            if (map.get(query).contains(target)) {
                result[i] = 1;
                continue;
            }

            result[i] = bfs(query, target, map);
        }
        return result;
    }
    private int bfs(int query, int target, Map<Integer, Set<Integer>> map) {
        int jump = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(query);
        while (!queue.isEmpty()) {
            if (jump >= 3) {
                return 0;
            }

            jump++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (!map.containsKey(curr)) {
                    return 0;
                }
                if (map.get(curr).contains(target)) {

                    return 1;
                }

                for (int next : map.get(curr)) {
                    queue.offer(next);

                }
            }

        }
        return 0;
    }
}

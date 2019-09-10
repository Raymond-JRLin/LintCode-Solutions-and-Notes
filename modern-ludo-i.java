// 1565. Modern Ludo I
// 中文English
// There is a one-dimensional board with a starting point on the far left side of the board and an end point on the far right side of the board. There are several positions on the board that are connected to other positions, ie if A is connected to B, then when chess falls at position A, you can choose whether to move the chess from A to B. And the connection is one way, which means that the chess cannot move from B to A. Now given the length and connections of the board, and you have a six-sided dice(1-6), output the minimum steps to reach the end point.
//
// Example
// Example1
//
// Input: length = 10 and connections = [[2, 10]]
// Output: 1
// Explanation:
// 1->2 (dice)
// 2->10(for free)
// Example2
//
// Input: length = 15 and connections = [[2, 8],[6, 9]]
// Output: 2
// Explanation:
// 1->6 (dice)
// 6->9 (for free)
// 9->15(dice)
// Notice
// the index starts from 1.
// length > 1
// The starting point is not connected to any other location
// connections[i][0] < connections[i][1]


public class Solution {
    /**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
        // Write your code here
        if (length == 1) {
            return 0;
        }
        if (length > 1 && length <= 7) {
            return 1;
        }
        if (connections == null || connections.length == 0) {
            return length / 6 + 1;
        }
        // return mytry(length, connections);

        return method2(length, connections);
    }

    private int method2(int len, int[][] conn) {
        // DP
        Map<Integer, List<Integer>> map = new HashMap<>();
        // build the connections from back to front
        for (int[] con : conn) {
            List<Integer> list = map.getOrDefault(con[0], new ArrayList<>());
            list.add(con[1]);
            map.put(con[0], list);
        }
        // definition: f[i] = the min step to get i
        int[] f = new int[len + 1];
        // initialization
        Arrays.fill(f, len / 6 + 1);
        f[0] = 0;
        for (int i = 1; i <= 7; i++) {
            f[i] = 1;
            connect(map, f, i);
        }
        // DP
        for (int i = 7; i <= len; i++) {
            for (int j = 1; j <= 6; j++) {
                f[i] = Math.min(f[i], f[i - j] + 1);
            }
            connect(map, f, i);
        }
        // result
        return f[len];
    }
    private void connect(Map<Integer, List<Integer>> map, int[] f, int i) {
        if (map.containsKey(i)) {
            for (int next : map.get(i)) {
                f[next] = Math.min(f[next], f[i]);
            }
        }
    }

    private int mytry(int len, int[][] conn) {
        // BFS
        Map<Integer, List<Integer>> map = new HashMap<>();
        // build the connections from back to front
        for (int[] con : conn) {
            List<Integer> list = map.getOrDefault(con[0], new ArrayList<>());
            list.add(con[1]);
            map.put(con[0], list);
        }

        // BFS from back to front
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        // if (!map.containsKey(len)) {
            // for (int i = 1; i < 7; i++) {

                    queue.offer(new int[]{1, 0});
                    visited.add(1);

            // }
        // } else {
            // queue.offer(new int[]{len, map.containsKey(len) ? 0 : 1});
            // visited.add(len);
        // }
        boolean found = false;
        int result = len;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // System.out.println("new round");
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                visited.add(curr[0]);
                // System.out.println("check " + curr[0] + ", dis: " + curr[1]);
                if (map.containsKey(curr[0])) {
                    for (int next : map.get(curr[0])) {
                        if (next == len) {
                            return curr[1];
                        }
                        if (visited.contains(next)) {
                            continue;
                        }
                        queue.offer(new int[]{next, curr[1]});
                        // visited.add(next);
                    }
                } else {
                    for (int j = 1; j < 7; j++) {
                        if (curr[0] + j == len) {
                            // System.out.println("get " + (curr[0] - j) + " with " + (curr[1] + 1));
                            found = true;
                            result = Math.min(result, curr[1] + 1);
                        }
                        if (visited.contains(curr[0] + j)) {
                            continue;
                        }
                        queue.offer(new int[]{curr[0] + j, curr[1] + 1});
                        // visited.add(curr[0] - j);
                    }
                }

            }
            if (found) {
                return result;
            }
        }
        return -1;
    }
}

// 1644. Plane Maximum Rectangle
// Description
// Given a n point on the two-dimensional coordinate system, output the maximum area of the rectangle that consisting of four points. If it cannot form a rectangle, output 0
//
// n <= 1000
// 0 <= x,y <= 1000
// each side of the rectangle is required to be perpendicular to the X or Y axis
// Have you met this question in a real interview?
// Example
// a = [[1,1],[1,2],[2,1],[2,2],[2,3],[3,2],[3,1]], return 2
//
// The four points selected are: [1,1], [1,2], [3,1], [3,2]
// a = [[1,1],[1,2],[2,2],[2,3],[3,3],[3,4],[4,4]], return 0
//
// No four points can form a rectangle


public class Solution {
    /**
     * @param a: the points
     * @return: return the maximum rectangle area
     */
    public int getMaximum(int[][] a) {
        // write your code here
        if (a == null || a.length < 4) {
            return 0;
        }

        return mytry(a);
    }

    private int mytry(int[][] points) {
        int n = points.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            Set<Integer> set = map.getOrDefault(p[0], new HashSet<>());
            set.add(p[1]);
            map.put(p[0], set);
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                if (p1[0] == p2[0] || p1[1] == p2[1]) {
                    continue;
                }
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
                    result = Math.max(result, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                }
            }
        }
        return result == Integer.MIN_VALUE ? 0 : result;
    }
}

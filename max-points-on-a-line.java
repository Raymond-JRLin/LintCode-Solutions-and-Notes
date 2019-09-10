// 186. Max Points on a Line
// 中文English
// Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
//
// Example
// Example 1:
//
// Input:(1,2),(3,6),(0,0),(1,3).
// Output:3
// Example 2:
//
// Input:(1,2),(2,3),(3,2).
// Output:2
// Notice
// point.x and point.y ranges from -1000 to 1000
//


/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    /**
     * @param points an array of point
     * @return an integer
     */
    public int maxPoints(Point[] points) {
        // Write your code here
        if (points == null || points.length == 0) {
            return 0;
        }
        int n = points.length;
        int max = 0;
        // my try: use slope and HashMap
        // 不可以直接求这个点到原点的斜率， 而是要两两对比
        // double infinite = Double.MAX_VALUE; // 为了精度要用 double 型
        // int max = 0;
        // for (int i = 0; i < n; i++) {
        //     // 以某个点， 求两两的斜率
        //     Map<Double, Integer> map = new HashMap<Double, Integer>(); // <slope, count>
        //     // 可能有重复的点
        //     int duplicates = 0;
        //     // 被比较的点
        //     int y0 = points[i].y;
        //     int x0 = points[i].x;
        //     for (int j = i + 1; j < n; j++) {
        //         // loop 之后的每个点求斜率
        //         int y = points[j].y;
        //         int x = points[j].x;
        //         if (x == x0 && y == y0) {
        //             // 注意重复点
        //             duplicates++;
        //         } else if (x == x0) {
        //             // 注意垂直的线， 斜率不存在
        //             if (map.containsKey(infinite)) {
        //                 map.put(infinite, map.get(infinite) + 1);
        //             } else {
        //                 map.put(infinite, 1);
        //             }
        //         } else {
        //             double slope = (double) (y - y0) / (x - x0);
        //             if (map.containsKey(slope)) {
        //                 map.put(slope, map.get(slope) + 1);
        //             } else {
        //                 map.put(slope, 1);
        //             }
        //         }
        //     }
        //     // find out the max, 别忘了 + 1， 是 i 这个比较的点本身
        //     max = Math.max(max, mapMax(map) + duplicates + 1);
        // }
        // return max;

        // 上面也看到了精度的影响很大， 譬如说如果不设置 infinite 为 Double 型， 而是 Integer 型的话， 这个 case: [[0,-12],[5,2],[2,5],[0,-5],[1,5],[2,-2],[5,-4],[3,4],[-2,4],[-1,4],[0,-5],[0,-8],[-2,-1],[0,-11],[0,-9]] 通不过
        // 所以我们为了更加精确无误的计算共线，我们应当避免除法，从而避免无线不循环小数的出现，那么怎么办呢，我们把除数和被除数都保存下来，不做除法，但是我们要让这两数分别除以它们的最大公约数
        // method 2: use HashMap and Greatest Common Divisor
        for (int i = 0; i < n; i++) {
            // 以某个点， 求两两的斜率
            Map<Map<Integer, Integer>, Integer> map = new HashMap<Map<Integer, Integer>, Integer>(); // <化简后的点 Pair, count>, 也可以用 Map<Map<>, Integer> 来存两个点
            // 可能有重复的点
            int duplicates = 0;
            // 被比较的点
            int y0 = points[i].y;
            int x0 = points[i].x;
            int currMax = 0;
            int infinite = 0;
            for (int j = i + 1; j < n; j++) {
                // loop 之后的每个点求斜率
                int y = points[j].y;
                int x = points[j].x;
                if (x == x0 && y == y0) {
                    // 注意重复点
                    duplicates++;
                } else if (x == x0) {
                    // 注意垂直的线， 斜率不存在
                    infinite++;
                    currMax = Math.max(currMax, infinite);
                } else {
                    int dx = x - x0;
                    int dy = y - y0;
                    int gcd = gcd(dx, dy);
                    Map<Integer, Integer> lines = new HashMap<Integer, Integer>();
                    lines.put(dx / gcd, dy / gcd);
                    if (map.containsKey(lines)) {
                        map.put(lines, map.get(lines) + 1);
                    } else {
                        map.put(lines, 1);
                    }
                    currMax = Math.max(currMax, map.get(lines));
                }
            }
            // find out the max, 别忘了 + 1， 是 i 这个比较的点本身
            max = Math.max(max, currMax + duplicates + 1);
        }
        return max;
    }
    private int mapMax(Map<Double, Integer> map) {
        // int result = 0;
        // for (Map.Entry<Double, Integer> entry : map.entrySet()) {
        //     int count = entry.getValue();
        //     if (count > result) {
        //         result = count;
        //     }
        // }
        // return result;

        int max = 0;
        for (double key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
            }
        }
        return max;
    }
    private int gcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }
}
class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

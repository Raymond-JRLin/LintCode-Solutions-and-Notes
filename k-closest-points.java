// 612. K Closest Points
// Description
// Given some points and an origin point in two-dimensional space, find k points which are nearest to the origin.
// Return these points sorted by distance, if they are same in distance, sorted by the x-axis, and if they are same in the x-axis, sorted by y-axis.
//
// Have you met this question in a real interview?
// Example
// Given points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
// return [[1,1],[2,5],[4,4]]


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
     * @param points a list of points
     * @param origin a point
     * @param k an integer
     * @return the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // Write your code here
        if (origin == null || points == null || points.length < k) {
            return new Point[k];
        }

        return method1(points, origin, k);
    }

    private Point[] method1(Point[] points, Point origin, int k) {
        // O(NlogK) time and O(K) space
        final Point globalOrigin = origin;
        Point[] results = new Point[k];
        if (points == null || points.length < k || k == 0) {
            return results;
        }
        PriorityQueue<Point> pq = new PriorityQueue<>(k, (o1, o2) -> {
            int diff = Integer.compare(getDistance(o2, globalOrigin), getDistance(o1, globalOrigin));
            if (diff == 0) {
                diff = Integer.compare(o2.x, o1.x);
            }
            if (diff == 0) {
                diff = Integer.compare(o2.y, o1.y);
            }
            return diff;
        });
        for (int i = 0; i < points.length; i++) {
            pq.offer(points[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            results[i] = pq.poll();
        }
        return results;
    }
    private int getDistance(Point point, Point origin) {
        return (point.x - origin.x) * (point.x - origin.x) + (point.y - origin.y) * (point.y - origin.y);
    }
}

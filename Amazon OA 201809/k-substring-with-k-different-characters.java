// 1639. K-Substring with K different characters
// Description
// Given a string S and an integer K.
// Calculate the number of substrings of length K and containing K different characters
//
// Have you met this question in a real interview?
// Example
// String: "abcabc"
// K: 3
//
// Answer: 3
// substrings:  ["abc", "bca", "cab"]
// String: "abacab"
// K: 3
//
// Answer: 2
// substrings: ["bac", "cab"]

public class Solution {
    /**
     * @param restaurant:
     * @param n:
     * @return: nothing
     */
    public List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
        // Write your code here
        if (n < 1 || restaurant == null || restaurant.size() == 0 || n > restaurant.size()) {
            return Collections.emptyList();
        }

        return mytry(restaurant, n);
    }

    private List<List<Integer>> mytry(List<List<Integer>> restaurant, int n) {
        final int x0 = 0;
        final int y0 = 0;

        PriorityQueue<Point> pq = new PriorityQueue<Point>((o1, o2) -> {
                if (o1.dist == o2.dist) {
                    return Integer.compare(o1.index, o2.index);
                } else {
                    return Integer.compare(o2.dist, o1.dist);
                }
            }
        );
        for (int i = 0; i < restaurant.size(); i++) {
            List<Integer> list = restaurant.get(i);
            if (list == null || list.size() != 2) {
                continue;
            }
            int x = list.get(0);
            int y = list.get(1);
            int d = getDist(x, y, x0, y0);
            Point point = new Point(x, y, d, i);
            pq.offer(point);
            if (pq.size() > n) {
                pq.poll();
            }
        }

        int furthest = pq.peek().dist;
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> curr : restaurant) {
            int d = getDist(curr.get(0), curr.get(1), x0, y0);
            if (d <= furthest) {
                result.add(new ArrayList<>(curr));
            }
            if (result.size() == n) {
                break;
            }
        }
        return result;
    }
    private int getDist(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
    private class Point {
        private int x;
        private int y;
        private int index;
        private int dist;

        public Point(int x, int y, int d, int i) {
            this.x = x;
            this.y = y;
            this.dist = d;
            this.index = i;
        }
    }
}

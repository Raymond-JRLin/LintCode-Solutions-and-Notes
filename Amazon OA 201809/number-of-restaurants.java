// 1562. Number of restaurants
// Description
// Give a List of data representing the coordinates[x,y] of each restaurant and the customer is at the origin[0,0]. Find the n restaurants closest to the customer firstly. Then you need to pick n restaurants which appeare firstly in the List and the distance between the restaurant and the customer can't more than the longest distance in the n closest restaurants. Return their coordinates in the original order.
//
// 1.Coordinates in range [-1000,1000]
// 2.n>0
// 3.No same coordinates
//
// Have you met this question in a real interview?
// Example
// Given : n = 2 , List = [[0,0],[1,1],[2,2]]
// Return : [[0,0],[1,1]]
// Given : n = 3,List = [[0,1],[1,2],[2,1],[1,0]]
// Return :[[0,1],[1,2],[2,1]]


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

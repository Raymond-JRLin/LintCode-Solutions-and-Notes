// 1552. Parking Problem
// Description
// Ming went out to buy a drink at noon. On the way to the trash can, he passed a public parking lot. Because of the programmer's professional habits, he would like to know how much the parking lot is maximizing utilization in the morning.
// After communicating with the uncle of the guard, he obtained a record of the vehicle arrival time and playing time of the parking lot in the morning (data format reference sample input).
// Can you write a function through the data you get to help Ming calculate this parking lot. How many cars are parked at the same time in the morning?
//
// For convenience, simplify the calculation, the entry time and the opening time to record on the whole point, such as 9 o'clock, 10 o'clock.
// If the admission time in the parking record is later than the playing time, the parking record is considered invalid, such as [7,3]
// Assume that if more than one car enters the market at the same time, the exit vehicle is preferred.
// The number of parking records does not exceed 100,000100,000, and the time does not exceed 1e91e9
// Have you met this question in a real interview?
// Example
// Given a = [[8,9],[4,6],[3,7],[6,8]], return 2.
//
// Explanation:
// There are 2 cars in the parking lot at [4, 6] or [6, 7],
// and at most 1 car in the rest of the time.`
// Given a = [[1,2],[2,3],[3,4],[4,5]] , return 1。
//
// Explanation:
// Whenever one car is driven out, another car comes in again, up to one car.


public class Solution {
    /**
     * @param a: the Parking Record
     * @return: The max number of cars
     */
    public int getMax(int[][] a) {
        // Write your code here
        if (a == null || a.length == 0) {
            return 0;
        }

        // return mytry(a);

        return method2(a);
    }

    private int method2(int[][] a) {
        // same as meeting room
        int n = a.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            if (a[i][0] > a[i][1]) {
                // no need to include '=' case since we gonna minus on ends[]
                // also we CANNOT since we use an array then there would be empty position with value of 0, after sorint it would be in the beginning to cause mistakes
                continue;
            }
            starts[i] = a[i][0];
            ends[i] = a[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int result = 0;
        int temp = 0;
        int in = 0;
        int out = 0;
        while (in < n) {
            // System.out.println(i + ": " + starts[i] + ", " + ends[i]);
            if (starts[in] == ends[out]) {
                in++;
                out++;
            } else if (starts[in] < ends[out]) {
                temp++;
                result = Math.max(result, temp);
                in++;
            } else {
                temp--;
                out++;
            }
        }

        // or we can do by index
        // int index = 0;
        // for (int i = 0; i < n; i++) {
        //     if (starts[i] < ends[index]) {
        //         continue;
        //     }
        //     result = Math.max(result, i - index);
        //     index++;
        // }
        return result;
    }

    private int mytry(int[][] a) {
        int n = a.length;
        List<int[]> inters = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i][0] >= a[i][1]) {
                continue;
            }
            inters.add(new int[]{a[i][0], a[i][1]});
        }
        if (inters.size() == 0) {
            return 0;
        }

        Collections.sort(inters, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        // int count = 1;
        // for (int i = 0; i < inters.size() - 1; i++) {
        //     if (inters.get(i)[1] >= inters.get(i + 1)[0]) {
        //         continue;
        //     }
        //     count++;
        // }
        // return count;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] inter : inters) {
            if (!pq.isEmpty() && pq.peek() <= inter[0]) {
                pq.poll();
            }
            // min-heap: so offer every end time, since we sort array as start time, so we can keep tracking if there's sharing room for several intervals
            pq.offer(inter[1]);
        }
        return pq.size();
    }
}

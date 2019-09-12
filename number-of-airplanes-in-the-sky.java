// 391. Number of Airplanes in the Sky
// 中文English
// Given an list interval, which are taking off and landing time of the flight. How many airplanes are there at most at the same time in the sky?
//
// Example
// Example 1:
//
// Input: [(1, 10), (2, 3), (5, 8), (4, 7)]
// Output: 3
// Explanation:
// The first airplane takes off at 1 and lands at 10.
// The second ariplane takes off at 2 and lands at 3.
// The third ariplane takes off at 5 and lands at 8.
// The forth ariplane takes off at 4 and lands at 7.
// During 5 to 6, there are three airplanes in the sky.
// Example 2:
//
// Input: [(1, 2), (2, 3), (3, 4)]
// Output: 1
// Explanation: Landing happen before taking off.
// Notice
// If landing and taking off of different planes happen at the same time, we consider landing should happen at first.
//


/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */


public class Solution {
    /*
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        // write your code here
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }

        // 想了一下其实不是 segment tree 因为并不是有起始点和终点， 然后中间平分

        // method 1: use HashMap
        // ref: https://aaronice.gitbooks.io/lintcode/content/data_structure/number_of_airplanes_in_the_sky.html
        // int max = 0;
        // Map<Integer, Integer> map = new HashMap<>(); // <time, count>
        // for (Interval airplane : airplanes) {
        //     int start = airplane.start;
        //     int end = airplane.end;
        //     for (int i = start; i < end; i++) {
        //         // add each hour time between this interval
        //         if (map.containsKey(i)) {
        //             map.put(i, map.get(i) + 1);
        //         } else {
        //             map.put(i, 1);
        //         }
        //         max = Math.max(max, map.get(i));
        //     }
        // }
        // return max;
        // it's corrent but would TLE, because if the range of interval is so large then for loop would take a long time

        // method 2: sweep line
        List<Plane> list = new ArrayList<>();
        for (Interval airplane : airplanes) {
            list.add(new Plane(airplane.start, 1));
            list.add(new Plane(airplane.end, - 1));
        }
        Collections.sort(list, new Comparator<Plane>() {
            @Override
            public int compare(Plane p1, Plane p2) {
                if (p1.time == p2.time) {
                    // when time is equal, meaning maybe a start time of a plane collides an end time of another plane, then we would compair their flag
                    return p1.flag - p2.flag; // ascending
                } else {
                    return p1.time - p2.time; // ascending
                }
            }
        });
        int max = 0;
        int sum = 0;
        for (Plane plane : list) {
            if (plane.flag == 1) {
                sum++;
            } else {
                sum--;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
class Plane {
    int time;
    int flag;
    public Plane(int time, int flag) {
        this.time = time;
        this.flag = flag;
    }
}

// 821. Time Intersection

// Give two users' ordered online time series, and each section records the user's login time point x and offline time point y. Find out the time periods when both users are online at the same time, and output in ascending order.
//
// We guarantee that the length of online time series meet 1 <= len <= 1e6.
// For a user's online time series, any two of its sections do not intersect.

// Example
// Given seqA = [(1,2),(5,100)], seqB = [(1,6)], return [(1,2),(5,6)].
//
// Explanation:
// In these two time periods (1,2),(5,6), both users are online at the same time.
// Given seqA = [(1,2),(10,15)], seqB = [(3,5),(7,9)], return [].
//
// Explanation:
// There is no time period, both users are online at the same time.


/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param seqA: the list of intervals
     * @param seqB: the list of intervals
     * @return: the time periods
     */
    public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
        // Write your code here
        if (seqA == null || seqA.size() == 0 || seqB == null || seqB.size() == 0) {
            return Collections.emptyList();
        }

        return mytry(seqA, seqB);
    }

    private List<Interval> mytry(List<Interval> A, List<Interval> B) {
        int sizeA = A.size();
        int sizeB = B.size();
        int i = 0;
        int j = 0;
        List<Interval> result = new ArrayList<>();
        while (i < sizeA && j < sizeB) {
            Interval interA = A.get(i);
            Interval interB = B.get(j);
            if (interA.start >= interB.end) {
                j++;
            } else if (interA.end <= interB.start) {
                i++;
            } else {
                Interval inter = new Interval(Math.max(interA.start, interB.start), Math.min(interA.end, interB.end));
                result.add(inter);
                if (interA.end <= interB.end) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        return result;
    }
}

// 833. Process Sequence
// Description
// There is a process sequence that contains the start and end of each process. There is a query sequence asking how many processes are running at a certain point in time. Please return the query result of the query sequence.
//
// 1 <= logs[i].start,logs[i].end,queries[i] <= 2^32 - 1
// 1 <= |logs|,|queries| <= 10^5
// Have you met this question in a real interview?
// Example
// Given logs = [[1, 1234], [2, 1234]], queries = [2], return [2].
//
// Explanation:
// There are 2 processes running at time 2.
// Given logs = [[1, 1234], [2, 1234]], queries = [1, 1235], return [1, 0].
//
// Explanation:
// There is a process running at time 1, and 0 processes running at time 1235.


/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */
public class Solution {
    /**
     * @param logs: Sequence of processes
     * @param queries: Sequence of queries
     * @return: Return the number of processes
     */
    public List<Integer> numberOfProcesses(List<Interval> logs, List<Integer> queries) {
        // Write your code here
        if (queries == null || queries.size() == 0) {
            return new ArrayList<Integer>();
        }

        return mytry(logs, queries);
    }

    private List<Integer> mytry(List<Interval> logs, List<Integer> queries) {
        int n = logs.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = logs.get(i).start;
            ends[i] = logs.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        List<Integer> result = new ArrayList<>();
        for (int q : queries) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (starts[i] > q) {
                    break;
                }
                count++;
            }
            for (int i = 0; i < n; i++) {
                if (ends[i] > q) {
                    break;
                }
                count--;
            }
            result.add(count);
        }
        return result;
    }
}

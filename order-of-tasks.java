// 1465. Order Of Tasks
// Description
// There are n different tasks, the execution time of tasks are t[], and the probability of success are p[]. When a task is completed or all tasks fail, the operation ends. Tasks are performed in a different order, and it is expected that the time to stop the action is generally different. Please answer in what order to perform the task in order to make the end of the expected action the shortest time? If the expected end time of the two task sequences is the same, the lexicographic minimum order of tasks is returned.
//
// 1 \leq n \leq 50, 1 \leq t_i \leq 10, 0 \leq p_i \leq 11≤n≤50,1≤t
// ​i
// ​​ ≤10,0≤p
// ​i
// ​​ ≤1
// nn is a positive integer, t_it
// ​i
// ​​  is a positive integer, p_ip
// ​i
// ​​  is a floating-point number
// Have you met this question in a real interview?
// Example
// Given n=1, t=[1], p=[1.0], return [1].
//
//
// Explanation:
// The shortest expected action end time is 1.0*1+(1.0-1.0)*1=1.0
//
// Given n=2, t=[1,2], p=[0.3, 0.7], return [2,1].
//
//
// Explanation:
// The shortest expected action end time is 0.7*2+(1.0-0.7)*0.3*(2+1)+(1.0-0.7)*(1.0-0.3)*(2+1)=2.3



public class Solution {
    /**
     * @param n: The number of tasks
     * @param t: The time array t
     * @param p: The probability array p
     * @return: Return the order
     */
    public int[] getOrder(int n, int[] t, double[] p) {
        // Write your code here
        if (n == 0) {
            return new int[0];
        }

        return method1(n, t, p);
    }

    private int[] method1(int n, int[] t, double[] p) {
        Task[] tasks = new Task[n];
        for (int i = 0; i < t.length; i++) {
            tasks[i] = new Task(i, t[i], p[i]);
        }

        Arrays.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (Math.abs((o1.prob / o1.time) - (o2.prob/ o2.time)) < 1e-8) {
                    return Integer.compare(o1.id, o2.id);
                }
                return Double.compare((o2.prob/ o2.time), (o1.prob / o1.time));
            }
        });

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = tasks[i].id + 1;
        }
        return result;
    }
    private class Task {
        private int id;
        private int time;
        private double prob;
        public Task(int i, int t, double p) {
            id = i;
            time = t;
            prob = p;
        }
    }
}

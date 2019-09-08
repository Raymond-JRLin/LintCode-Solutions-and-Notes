// 615. Course Schedule
// 中文English
// There are a total of n courses you have to take, labeled from 0 to n - 1.
//
// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
// Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
//
// Example
// Example 1:
//
// Input: n = 2, prerequisites = [[1,0]]
// Output: true
// Example 2:
//
// Input: n = 2, prerequisites = [[1,0],[0,1]]
// Output: false


public class Solution {
    /**
     * @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Write your code here
        // 1: degree
        int[] degree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
        }
        // 2: directions
        List[] direction = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            direction[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            direction[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        // 3: enqueue courses with degree of 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        // 4: BFS
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            int size = direction[course].size();
            for (int i = 0; i < size; i++) {
                int nextCourse = (int) direction[course].get(i);
                degree[nextCourse]--;
                if (degree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        // if (count == numCourses) {
        //     return true;
        // } else {
        //     return false;
        // }
        return count == numCourses;
    }
}

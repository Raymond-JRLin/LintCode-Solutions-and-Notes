// 825. Bus Station
// 中文English
// There are a city's N bus information, route[i] stores the bus stop through which the i-th bus passes, find the minimum number of transfers from station A to station B. If you can't get to station B from station A, return -1.
//
// Example
// Example 1
//
// Input:
// N = 2
// route = [[1, 2, 3, 4], [3, 5, 6, 7]]
// A = 1
// B = 4
// Output: 1
// Explanation:
// We only need to take the bus No.0, you can get to Station 3 from Station 0.
// Example 2
//
// Input:
// N = 2
// route = [[1,2,3,4],[3,5,6,7]]
// A = 1
// B = 7
// Output: 2
// Explanation:
// We need to take bus No.0 from station 0 and then take bus No.1 to station 6 at station 2.
// Notice
// 1 <= N <= 100, 2 <= |route[i]| <= 100, 0 <= route[i][j] <= 2^31 - 1
// A and B two stations must exist and they are different


public class Solution {
    /**
     * @param N: The number of buses
     * @param route: The route of buses
     * @param A: Start bus station
     * @param B: End bus station
     * @return: Return the minimum transfer number
     */
    public int getMinTransferNumber(int N, int[][] route, int A, int B) {
        // Write your code here

        // return method1(N, route, A, B);

        return method2(N, route, A, B);
    }

    private int method2(int buses, int[][] routes, int s, int t) {
        // another simpler BFS version
        // ref: https://leetcode.com/problems/bus-routes/discuss/122712/Simple-Java-Solution-using-BFS
        // 1: create a relation 每一个 station 有哪些 bus 经过
        Map<Integer, List<Integer>> stations = new HashMap<>(); // <station, buses>
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                List<Integer> list = stations.getOrDefault(routes[i][j], new ArrayList<Integer>());
                list.add(i);
                stations.put(routes[i][j], list);
            }
        }

        // 2. BFS by stations
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s); // put stations into queue
        Set<Integer> visitedStation = new HashSet<>();
        Set<Integer> visitedBus = new HashSet<>();
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll(); // station
                visitedStation.add(curr);
                for (int bus : stations.get(curr)) {
                    // which bus will pass this current station
                    if (visitedBus.contains(bus)) {
                        continue;
                    }
                    visitedBus.add(bus);
                    for (int station : routes[bus]) {
                        // check all stations that this bus can pass by
                        if (station == t) {
                            return result;
                        }
                        if (visitedStation.contains(station)) {
                            continue;
                        }
                        queue.offer(station);
                        visitedStation.add(station);
                    }
                }
            }
        }
        return -1;
    }

    private int method1(int buses, int[][] routes, int start, int end) {
        // 找最近的路线， 自然想到用 BFS， 但是没办法沿着 station 走， 因为没办法是的是否需要换乘 bus 并记录 bus 的数量， 因此应该要以 bus 为 node， 沿着 bus 走， check 是否到了 end

        Queue<Integer> queue = new LinkedList<>();

        // 1: create a mapping of real station number and our indexes starting from 0
        // 不重新建立 station 的编号从 0 开始的话， 下面没办法去 for 每一个 station； 建立好之后， 下面用到 station 的时候， 都要用我们的编号
        int count = 0; // count # distinct stations
        Map<Integer, Integer> mapping = new HashMap<>(); // <real station number, our index>
        int[] status = new int[1001]; // record the status of bus, 很巧妙的使用取 | 来标记， 右边第一位是 1 表示起点或者走过了的， 第二位表示终点
        for (int i = 0; i < buses; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!mapping.containsKey(routes[i][j])) {
                    mapping.put(routes[i][j], count++);
                }
                if (routes[i][j] == start) {
                    status[i] |= 1; // 起始 bus
                    queue.offer(i); // 放入的是 bus 而不是 station

                }
                if (routes[i][j] == end) {
                    status[i] |= 2; // 终点 bus
                }
            }
        }

        // 2: create a relation 每一个 station 有哪些 bus 经过
        // Map<Integer, List<Integer>> stations = new HashMap<>(); // <station, buses>
        // for (int i = 0; i < count; i++) {
        //     stations.put(i, new ArrayList<Integer>());
        // }
        // for (int i = 0; i < buses; i++) {
        //     for (int j = 0; j < routes[i].length; j++) {
        //         int index = mapping.get(routes[i][j]);
        //         stations.get(index).add(i);
        //     }
        // }
        // or use List
        List<List<Integer>> stations = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            stations.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < buses; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int index = mapping.get(routes[i][j]);
                stations.get(index).add(i);
            }
        }

        // 3: 记录哪些 bus 可以互相换乘， 即建立图之间的关系
        // Map<Integer, List<Integer>> transfer = new HashMap<>();
        // for (int i = 0; i < buses; i++) {
        //     transfer.put(i, new ArrayList<>());
        // }
        // for (int i = 0; i < count; i++) {
        //     for (int j = 0; j < stations.get(i).size(); j++) {
        //         for (int k = j + 1; k < stations.get(i).size(); k++) {
        //             int a = stations.get(i).get(j);
        //             int b = stations.get(i).get(k);
        //             transfer.get(a).add(b);
        //             transfer.get(b).add(a);
        //         }
        //     }
        // }
        // here cannot use Map otherwise MLT
        List<List<Integer>> transfer = new ArrayList<>();
        for (int i = 0; i < buses; i++) {
            transfer.add(new ArrayList<>());
            for (int j = 0; j < buses; j++) {
                transfer.get(i).add(0);
            }
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < stations.get(i).size(); j++) {
                for (int k = j + 1; k < stations.get(i).size(); k++) {
                    int a = stations.get(i).get(j);
                    int b = stations.get(i).get(k);
                    transfer.get(a).set(b, 1);
                    transfer.get(b).set(a, 1);
                }
            }
        }

        int result = 0;
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if ((status[curr] & 2) != 0) {
                    return result;
                }
                for (int j = 0; j < buses; j++) {
                    if ((status[j] & 1) != 0) {
                        continue;
                    }
                    if (transfer.get(curr).get(j) != 1) {
                        continue;
                    }
                    queue.offer(j);
                    status[j] |= 1; // 要用 | 操作来标记， 因为如果此时的 j bus 是我们的终点 bus， 如果不用 | 而是直接设走过的为 1， 那么就会把终点也改成 1 则走不到了。 所以采用 | 的操作来标记两位的 0 1 状态
                }
            }
        }
        return -1;
    }
}

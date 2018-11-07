// 629. Minimum Spanning Tree
// Description
// Given a list of Connections, which is the Connection class (the city name at both ends of the edge and a cost between them), find some edges, connect all the cities and spend the least amount.
// Return the connects if can connect all the cities, otherwise return empty list.
//
// Return the connections sorted by the cost, or sorted city1 name if their cost is same, or sorted city2 if their city1 name is also same.
//
// Have you met this question in a real interview?
// Example
// Gievn the connections = ["Acity","Bcity",1], ["Acity","Ccity",2], ["Bcity","Ccity",3]
//
// Return ["Acity","Bcity",1], ["Acity","Ccity",2]


/**
 * Definition for a Connection.
 * public class Connection {
 *   public String city1, city2;
 *   public int cost;
 *   public Connection(String city1, String city2, int cost) {
 *       this.city1 = city1;
 *       this.city2 = city2;
 *       this.cost = cost;
 *   }
 * }
 */
public class Solution {
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    public List<Connection> lowestCost(List<Connection> connections) {
        // Write your code here
        if (connections == null || connections.size() == 0) {
            return Collections.emptyList();
        }

        return method1(connections);
    }

    private List<Connection> method1(List<Connection> connections) {
        // Kruskal’s algorithm: a greedy approach as in each iteration it finds an edge which has least weight and add it to the growing spanning tree.
        // O(ElogV) time 
        // 1. sort connections as cost, city1, city2
        Collections.sort(connections, new conComparator());
        // 2. mapping city name to index to use them in the union find array
        int n = 0;
        Map<String, Integer> map = new HashMap<>(); // <city name, index>
        for (Connection con : connections) {
            if (!map.containsKey(con.city1)) {
                map.put(con.city1, n++);
            }
            if (!map.containsKey(con.city2)) {
                map.put(con.city2, n++);
            }
        }
        // 3. union find, connect cities
        UnionFind uf = new UnionFind(n);
        List<Connection> result = new ArrayList<>();
        for (Connection con : connections) {
            int a = map.get(con.city1);
            int b = map.get(con.city2);
            if (uf.connect(a, b)) {
                // if they connect successfully, then they are current connection
                result.add(con);
            }
        }
        // we need only one connected component left
        if (uf.count != 1) {
            return Collections.emptyList();
        }
        return result;
    }
    private class UnionFind {
        private int[] nums;
        private int count;

        public UnionFind(int n) {
            count = n;
            nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i;
            }
        }

        public int find(int i) {
            while (i != nums[i]) {
                nums[i] = nums[nums[i]];
                i = nums[i];
            }
            return nums[i];
        }

        public boolean connect(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                nums[rootA] = rootB;
                count--;
                return true;
            }
            return false;
        }
    }
    private class conComparator implements Comparator<Connection> {
        @Override
        public int compare(Connection o1, Connection o2) {
            if (o1.cost != o2.cost) {
                return Integer.compare(o1.cost, o2.cost);
            } else if (o1.city1.equals(o2.city1)) {
                return o1.city2.compareTo(o2.city2);
            } else {
                return o1.city1.compareTo(o2.city1);
            }
        }
    }
}

// 526. Load Balancer
// 中文English
// Implement a load balancer for web servers. It provide the following functionality:
//
// Add a new server to the cluster => add(server_id).
// Remove a bad server from the cluster => remove(server_id).
// Pick a server in the cluster randomly with equal probability => pick().
// At beginning, the cluster is empty. When pick() is called you need to randomly return a server_id in the cluster.
//
// Example
// Example 1:
//
// Input:
//   add(1)
//   add(2)
//   add(3)
//   pick()
//   pick()
//   pick()
//   pick()
//   remove(1)
//   pick()
//   pick()
//   pick()
// Output:
//   1
//   2
//   1
//   3
//   2
//   3
//   3
// Explanation: The return value of pick() is random, it can be either 2 3 3 1 3 2 2 or other.


public class LoadBalancer {

    private Map<Integer, Integer> dict = null;
    private List<Integer> servers = null;

    public LoadBalancer() {
        // Initialize your data structure here.
        dict = new HashMap<Integer, Integer>();
        servers = new ArrayList<Integer>();
    }

    // @param server_id add a new server to the cluster
    // @return void
    public void add(int server_id) {
        // Write your code here
        int m = servers.size();
        dict.put(server_id, m);
        servers.add(server_id);
    }

    // @param server_id server_id remove a bad server from the cluster
    // @return void
    public void remove(int server_id) {
        // Write your code here
        int index = dict.get(server_id);
        int m = servers.size();
        dict.put(servers.get(m - 1), index);
        servers.set(index, servers.get(m - 1));
        servers.remove(m - 1);
        dict.remove(server_id);
    }

    // @return pick a server in the cluster randomly with equal probability
    public int pick() {
        // Write your code here
        Random r = new Random();
        int m = servers.size();
        int index = Math.abs(r.nextInt()) % m;
        return servers.get(index);
    }
}

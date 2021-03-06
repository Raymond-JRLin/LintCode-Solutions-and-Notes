// 505. Web Logger
// Description
// Implement a web logger, which provide two methods:
//
// hit(timestamp), record a hit at given timestamp.
// get_hit_count_in_last_5_minutes(timestamp), get hit count in last 5 minutes.
// the two methods will be called with non-descending timestamp (in sec).
//


answer:

public class WebLogger {

    private LinkedList<Integer> timestamps;
    public WebLogger() {
        // initialize your data structure here.
        timestamps = new LinkedList<Integer>();
    }

    /**
     * @param timestamp an integer
     * @return void
     */
    public void hit(int timestamp) {
        // Write your code here
        timestamps.add(timestamp);
    }

    /**
     * @param timestamp an integer
     * @return an integer
     */
    public int get_hit_count_in_last_5_minutes(int timestamp) {
        // Write your code here
        while (!timestamps.isEmpty() && timestamps.getFirst()  + 300 <= timestamp)
            timestamps.removeFirst();
        return timestamps.size();
    }
}

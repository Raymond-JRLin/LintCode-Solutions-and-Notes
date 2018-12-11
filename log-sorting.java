// 1380. Log Sorting
// Description
// Give a log, consisting of List< String >, each element representing one line of logs. Each line of log information is separated by a space. The first is the ID of the log, followed by the log content.
// There are two ways to make content:
//
// All consist of letters and spaces.
// All consist of numbers and spaces.
// Now that the logs are sorted, it is required that component 1 be sorted in order of content lexicography and placed at the top, and component 2 should be placed at the bottom and output in the order of input. (Note that the space also belongs to the content, and when the lexicographic order of the composition method 1 is equal, sort according to the dictionary order of log ID., and the guarantee ID is not repeated)
// The total number of logs entered was n, and n < = 10000.
//
// The length of each line is mi, and mi < = 100.
//
// Have you met this question in a real interview?
// Example
// Given
//
// [
//     "zo4 4 7",
//     "a100 Act zoo",
//     "a1 9 2 3 1",
//     "g9 act car"
// ]
// , return
//
// [
//     "a100 Act zoo",
//     "g9 act car",
//     "zo4 4 7",
//     "a1 9 2 3 1"
// ]
// Explanation:
// "Act zoo" < "act car", So the output is as above.
// Given
//
// [
//     "zo4 4 7",
//     "a100 Actzoo",
//     "a100 Act zoo",
//     "Tac Bctzoo",
//     "Tab Bctzoo",
//     "g9 act car"
// ]
// , return
//
// [
//     "a100 Act zoo",
//     "a100 Actzoo",
//     "Tab Bctzoo",
//     "Tac Bctzoo",
//     "g9 act car",
//     "zo4 4 7"
// ]
// Explanation:
// Because "Bctzoo" == "Bctzoo", the comparison "Tab" and "Tac" have "Tab" < Tac ", so" Tab Bctzoo "< Tac Bctzoo".
// Because ' '<'z', so "A100 Act zoo" < A100 Actzoo".


public class Solution {
    /**
     * @param logs: the logs
     * @return: the log after sorting
     */
    public String[] logSort(String[] logs) {
        // Write your code here
        if (logs == null || logs.length == 0) {
            return logs;
        }

        // return mytry(logs);

        return method2(logs);
    }

    private String[] method2(String[] logs) {
        // more neat: just put component 2 (number) to final result directly when we iterate from back to front, and sort component 1 (letters);
        int n = logs.length;
        String[] result = new String[n];
        int count = n - 1;
        List<String> list = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            String curr = logs[i];
            int index = curr.indexOf(" ");
            String body = curr.substring(index + 1);
            if (body.charAt(0) >= '0' && body.charAt(0) <= '9') {
                // number
                result[count--] = curr;
            } else {
                list.add(curr);
            }
        }

        Collections.sort(list, new letterLogComparator());
        count = 0;
        for (String s : list) {
            result[count++] = s;
        }

        return result;
    }
    private class letterLogComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            int index1 = s1.indexOf(" ");
            int index2 = s2.indexOf(" ");
            String head1 = s1.substring(0, index1);
            String head2 = s2.substring(0, index2);
            String body1 = s1.substring(index1 + 1);
            String body2 = s2.substring(index2 + 1);
            if (body1.equals(body2)) {
                return head1.compareTo(head2);
            } else {
                return body1.compareTo(body2);
            }
        }
    }

    private String[] mytry(String[] logs) {
        int n = logs.length;
        PriorityQueue<Log> pq = new PriorityQueue<>(new Comparator<Log>() {
            @Override
            public int compare(Log o1, Log o2) {
                // letters log first, then numbers log
                if (o1.type != o2.type) {
                    return Integer.compare(o1.type, o2.type);
                }
                // same type log
                if (o1.type == 0) {
                    // letters log
                    if (o1.content.equals(o2.content)) {
                        return o1.id.compareTo(o2.id);
                    } else {
                        return o1.content.compareTo(o2.content);
                    }
                } else {
                    // numbers log: as their input order
                    return Integer.compare(o1.order, o2.order);
                }
            }
        });
        for (int j = 0; j < n; j++) {
            String log = logs[j];
            int i = 0;
            while (i < log.length() && log.charAt(i) != ' ') {
                i++;
            }
            String id = log.substring(0, i);
            String content = log.substring(i + 1, log.length());
            int type = Character.isDigit(content.charAt(0)) ? 1 : 0;
            Log curr;
            if (type == 0) {
                curr = new Log(id, content, type);
            } else {
                curr = new Log(id, content, type, j);
            }
            pq.offer(curr);
        }

        String[] result = new String[n];
        int index = 0;
        while (!pq.isEmpty()) {
            Log log = pq.poll();
            result[index++] = log.id + " " + log.content;
        }

        return result;
    }

    private class Log {
        String id;
        String content;
        int type; // 0 is letters, 1 is numbers
        int order; // input order
        // for letters type log
        public Log(String id, String content, int type) {
            this.id = id;
            this.content = content;
            this.type = type;
        }
        // for numbers type log
        public Log(String id, String content, int type, int order) {
            this.id = id;
            this.content = content;
            this.type = type;
            this.order = order;
        }
    }
}

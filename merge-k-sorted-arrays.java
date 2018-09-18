// 486. Merge K Sorted Arrays
// Description
// Given k sorted integer arrays, merge them into one sorted array.
//
// Have you met this question in a real interview?
// Example
// Given 3 sorted arrays:
//
// [
//   [1, 3, 5, 7],
//   [2, 4, 6],
//   [0, 8, 9, 10, 11]
// ]
// return [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11].
//
// Challenge
// Do it in O(N log k).
//
// N is the total number of integers.
// k is the number of arrays.


public class Solution {
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public List<Integer> mergekSortedArrays(int[][] arrays) {
        // Write your code here
        // array is different from LinkedList, so we need to record the position of current value in array including row and column. So we can create a new class to record such info
        if (arrays == null || arrays.length == 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        if (arrays[0].length == 0) {
            return result;
        }
        // similar to Merge K Sorted Lists, we can use a PQ to sort current elements
        int k = arrays.length;
        PriorityQueue<Element> pq = new PriorityQueue<>(k, new Comparator<Element>() {
            public int compare(Element a, Element b) {
                return a.val - b.val; // min-heap
            }
        });
        // put the first numbers in 2D arrays - [i][0] into PQ
        for (int i = 0; i < k; i++) {
            Element num = new Element(i, 0, arrays[i][0]);
            pq.add(num);
        }
        // extract one by one and add next one to PQ
        while (!pq.isEmpty()) {
            Element ele = pq.poll();
            result.add(ele.val);
            // check if this ele is the last number in this 1D array
            // if there is one more number after this ele, add it into PQ
            if (ele.row < k && ele.col + 1 < arrays[ele.row].length) {
                Element nextEle = new Element(ele.row, ele.col + 1, arrays[ele.row][ele.col + 1]);
                pq.add(nextEle);
            }
        }
        return result;
    }
}
class Element {
    int val;
    int col;
    int row;
    Element(int r, int c, int v) {
        row = r;
        col = c;
        val = v;
    }
}

update signature:

public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
        // array is different from LinkedList, so we need to record the position of current value in array including row and column. So we can create a new class to record such info
        if (arrays == null || arrays.length == 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        if (arrays[0].length == 0) {
            return new int[0];
        }
        // similar to Merge K Sorted Lists, we can use a PQ to sort current elements
        int k = arrays.length;
        PriorityQueue<Element> pq = new PriorityQueue<>(k, new Comparator<Element>() {
            public int compare(Element a, Element b) {
                return a.val - b.val; // min-heap
            }
        });
        // put the first numbers in 2D arrays - [i][0] into PQ
        for (int i = 0; i < k; i++) {
            Element num = new Element(i, 0, arrays[i][0]);
            pq.add(num);
        }
        // extract one by one and add next one to PQ
        while (!pq.isEmpty()) {
            Element ele = pq.poll();
            result.add(ele.val);
            // check if this ele is the last number in this 1D array
            // if there is one more number after this ele, add it into PQ
            if (ele.row < k && ele.col + 1 < arrays[ele.row].length) {
                Element nextEle = new Element(ele.row, ele.col + 1, arrays[ele.row][ele.col + 1]);
                pq.add(nextEle);
            }
        }

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }

    private class Element {
        int val;
        int col;
        int row;
        Element(int r, int c, int v) {
            row = r;
            col = c;
            val = v;
        }
    }
}


answer:

class Element {
    public int row, col, val;
    Element(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

public class Solution {
    private Comparator<Element> ElementComparator = new Comparator<Element>() {
        public int compare(Element left, Element right) {
            return left.val - right.val;
        }
    };

    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null) {
            return new int[0];
        }

        int total_size = 0;
        Queue<Element> Q = new PriorityQueue<Element>(
            arrays.length, ElementComparator);

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                Element elem = new Element(i, 0, arrays[i][0]);
                Q.add(elem);
                total_size += arrays[i].length;
            }
        }

        int[] result = new int[total_size];
        int index = 0;
        while (!Q.isEmpty()) {
            Element elem = Q.poll();
            result[index++] = elem.val;
            if (elem.col + 1 < arrays[elem.row].length) {
                elem.col += 1;
                elem.val = arrays[elem.row][elem.col];
                Q.add(elem);
            }
        }

        return result;
    }
}


// another one: don not use our own class but offer 1 value in each row into queue every time
// but I think time and space complexity would be a little larger 

public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
        if (arrays == null || arrays.length == 0) {
            return new int[]{};
        }

        int newArrayCount = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        int currentIndex = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null && arrays[i].length > currentIndex) {
                queue.add(arrays[i][currentIndex]);
                newArrayCount += arrays[i].length;
            }
        }
        currentIndex += 1;
        int[] newArray = new int[newArrayCount];
        int index = 0;
        while (!queue.isEmpty()) {
            int number = queue.poll();
            newArray[index++] = number;
            for (int i = 0; i < arrays.length; i++) {
                if (arrays[i] != null && arrays[i].length > currentIndex) {
                    queue.add(arrays[i][currentIndex]);
                }
            }
            currentIndex += 1;
        }
        return newArray;

    }
}

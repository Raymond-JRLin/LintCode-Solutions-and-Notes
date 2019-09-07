// 601. Flatten 2D Vector
// 中文English
// Implement an iterator to flatten a 2d vector.
//
// Example
// Example 1:
//
// Input:[[1,2],[3],[4,5,6]]
// Output:[1,2,3,4,5,6]
// Example 2:
//
// Input:[[7,9],[5]]
// Output:[7,9,5]


public class Vector2D implements Iterator<Integer> {
    // ArrayList's iterator method
    // List<Iterator<Integer>> list;
    // int curr;

    // 2 iterators method
    Iterator<List<Integer>> it;
    Iterator<Integer> curr;

    // 2 variables method
    // List<List<Integer>> list;
    // int curLine;
    // int curElem;

    public Vector2D(List<List<Integer>> vec2d) {
        // Initialize your data structure here

        // ArrayList's iterator method
        // list = new ArrayList<>();
        // for (List<Integer> vec : vec2d) {
        //     if (vec.size() > 0) {
        //         list.add(vec.iterator());
        //     }
        // }
        // curr = 0;

        // 2 iterators method
        it = vec2d.iterator();
        curr = it.next().iterator();

        // 2 variables method
        // list = vec2d;
        // curLine = 0;
        // curElem = 0;
    }

    @Override
    public Integer next() {
        // Write your code here

        // ArrayList's iterator method
        // int result = list.get(curr).next();
        // if (!list.get(curr).hasNext()) {
        //     curr++;
        // }
        // return result;

        // 2 iterators method
        // hasNext();
        return curr.next();

        // 2 variables method
        // return list.get(curLine).get(curElem++);
    }

    @Override
    public boolean hasNext() {
        // Write your code here

        // ArrayList's iterator method
        // return curr < list.size() && list.get(curr).hasNext();

        // 2 iterators method
        while ((curr == null || !curr.hasNext()) && it.hasNext()) {
            curr = it.next().iterator();
        }
        return curr != null && curr.hasNext();

        // 2 variables method
        // while (curLine < list.size()) {
        //     if (curElem < list.get(curLine).size()) {
        //         // if there are elements in this line
        //         return true;
        //     } else {
        //         // move to next line
        //         curLine++;
        //         curElem = 0;
        //     }
        // }
        // return false;
    }

    @Override
    public void remove() {}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

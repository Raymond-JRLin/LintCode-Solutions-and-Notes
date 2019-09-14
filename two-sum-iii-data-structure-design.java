// 607. Two Sum III - Data structure design
// 中文English
// Design and implement a TwoSum class. It should support the following operations: add and find.
//
// add - Add the number to an internal data structure.
// find - Find if there exists any pair of numbers which sum is equal to the value.
//
// Example
// Example 1:
//
// add(1); add(3); add(5);
// find(4) // return true
// find(7) // return false


public class TwoSum {
    public List<Integer> list;
    public HashMap<Integer, Integer> hash;
    public TwoSum() {
        list = new ArrayList<Integer>();
        hash = new HashMap<Integer, Integer>();
    }

    // Add the number to an internal data structure.
    public void add(int number) {
        // Write your code here
        if (hash.containsKey(number)) {
            hash.put(number, hash.get(number) + 1);
        } else {
            hash.put(number, 1);
            list.add(number);
        }
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        // Write your code here
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            int residue = value - num;
            if (num == residue && hash.get(num) > 1 || num != residue && hash.containsKey(residue)) {
                return true;
            }
        }
        return false;
    }
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);

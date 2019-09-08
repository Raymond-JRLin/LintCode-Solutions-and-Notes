// 657. Insert Delete GetRandom O(1)
// 中文English
// Design a data structure that supports all following operations in average O(1) time.
//
// insert(val): Inserts an item val to the set if not already present.
// remove(val): Removes an item val from the set if present.
// getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
// Example
// // Init an empty set.
// RandomizedSet randomSet = new RandomizedSet();
//
// // Inserts 1 to the set. Returns true as 1 was inserted successfully.
// randomSet.insert(1);
//
// // Returns false as 2 does not exist in the set.
// randomSet.remove(2);
//
// // Inserts 2 to the set, returns true. Set now contains [1,2].
// randomSet.insert(2);
//
// // getRandom should return either 1 or 2 randomly.
// randomSet.getRandom();
//
// // Removes 1 from the set, returns true. Set now contains [2].
// randomSet.remove(1);
//
// // 2 was already in the set, so return false.
// randomSet.insert(2);
//
// // Since 2 is the only number in the set, getRandom always return 2.
// randomSet.getRandom();


public class RandomizedSet {
    // 能想到的是要用一个 set 存下 element， 但是如何拿到一个 random 的 element？ 而且还是要 O(1) 的
    // 然鹅 set 并没有提供根据 index 来查找 value 的
    // 所以应该要想到需要用 array or ArryaList
    // 那这个时候要在 O(1) 完成 insert 和 remove， insert 没问题， 但是 remove(index) is sensitive to the value of index as well as the list length. The "advertised" complexity of O(N) for the average and worst cases. In the best case, the complexity is actually O(1). so for O(1) remove we should choose remove the tail
    // 但是不能保证每次要删除的都在 tail， 这时候还需要一个 map 来生成 index 和 value 之间的 mapping 关系， 那就可以调换要删掉的和 tail 的位置， 然后删掉tail
    // 对于 random element 我们需要 random class

    public ArrayList<Integer> list;
    public HashMap<Integer, Integer> map;
    public Random rand;

    public RandomizedSet() {
        // do initialize if necessary
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>(); // record <value, index>
        rand = new Random();
    }

    // Inserts a value to the set
    // Returns true if the set did not already contain the specified element or false
    public boolean insert(int val) {
        // Write your code here
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, list.size());
            list.add(val);
            return true;
        }
    }

    // Removes a value from the set
    // Return true if the set contained the specified element or false
    public boolean remove(int val) {
        // Write your code here
        if (map.containsKey(val)) {
            int index = map.get(val); // get the index of value to be removed
            map.remove(val); // remove from map
            int tail = list.get(list.size() - 1); // get the last value in list
            list.set(index, tail); // swap tail with value to be removed
            map.put(tail, index); // update map
            list.remove(list.size() - 1); // remove from list
            return true;
        } else {
            return false;
        }
    }

    // Get a random element from the set
    public int getRandom() {
        // Write your code here
        int random = rand.nextInt(list.size());
        return list.get(random);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */

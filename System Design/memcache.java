// 538. Memcache
// Description
// Implement a memcache which support the following features:
//
// get(curtTime, key). Get the key's value, return 2147483647 if key does not exist.
// set(curtTime, key, value, ttl). Set the key-value pair in memcache with a time to live (ttl). The key will be valid from curtTime to curtTime + ttl - 1 and it will be expired after ttl seconds. if ttl is 0, the key lives forever until out of memory.
// delete(curtTime, key). Delete the key.
// incr(curtTime, key, delta). Increase the key's value by delta return the new value. Return 2147483647 if key does not exist.
// decr(curtTime, key, delta). Decrease the key's value by delta return the new value. Return 2147483647 if key does not exist.
// It's guaranteed that the input is given with increasingcurtTime.
//
// Have you met this question in a real interview?
// Clarification
// Actually, a real memcache server will evict keys if memory is not sufficient, and it also supports variety of value types like string and integer. In our case, let's make it simple, we can assume that we have enough memory and all of the values are integers.
//
// Search "LRU" & "LFU" on google to get more information about how memcache evict data.
//
// Try the following problem to learn LRU cache:
// http://www.lintcode.com/problem/lru-cache
//
// Example
// get(1, 0)
// >> 2147483647
// set(2, 1, 1, 2)
// get(3, 1)
// >> 1
// get(4, 1)
// >> 2147483647
// incr(5, 1, 1)
// >> 2147483647
// set(6, 1, 3, 0)
// incr(7, 1, 1)
// >> 4
// decr(8, 1, 1)
// >> 3
// get(9, 1)
// >> 3
// delete(10, 1)
// get(11, 1)
// >> 2147483647
// incr(12, 1, 1)
// >> 2147483647


public class Memcache {

    Map<Integer, Element> map;

    public Memcache() {
        // do intialization if necessary
        map = new HashMap<>();
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: An integer
     */
    public int get(int curtTime, int key) {
        // write your code here
        if (!map.containsKey(key)) {
            return Integer.MAX_VALUE;
        }
        Element ele = map.get(key);
        if (ele.time > curtTime || ele.time == -1) {
            // time is expired time
            return ele.val;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param value: An integer
     * @param ttl: An integer
     * @return: nothing
     */
    public void set(int curtTime, int key, int value, int ttl) {
        // write your code here
        int time;
        if (ttl == 0) {
            time = -1;
        } else {
            time = curtTime + ttl;
        }
        map.put(key, new Element(value, time));
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: nothing
     */
    public void delete(int curtTime, int key) {
        // write your code here
        if (!map.containsKey(key)) {
            return;
        }
        map.remove(key);
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int incr(int curtTime, int key, int delta) {
        // write your code here
        if (get(curtTime, key) == Integer.MAX_VALUE) {
            // if the key does not exist
            return Integer.MAX_VALUE;
        }
        map.get(key).val += delta;
        return map.get(key).val;
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int decr(int curtTime, int key, int delta) {
        // write your code here
        if (get(curtTime, key) == Integer.MAX_VALUE) {
            // if the key does not exist
            return Integer.MAX_VALUE;
        }
        map.get(key).val -= delta;
        return map.get(key).val;
    }

    private class Element {
        public int val;
        public int time;
        public Element(int v, int t) {
            val = v;
            time = t;
        }
    }
}


answer:

class Resource {
    public int value;
    public int expired;
    public Resource(int value, int expired) {
        this.value = value;
        this.expired = expired;
    }
}

public class Memcache {

    Map<Integer, Resource> client = null;

    public Memcache() {
        // Initialize your data structure here.
        client = new HashMap<Integer, Resource>();
    }

    public int get(int curtTime, int key) {
        // Write your code here
        if (!client.containsKey(key))
            return Integer.MAX_VALUE;

        Resource res = client.get(key);
        if (res.expired >= curtTime || res.expired == -1)
            return res.value;
        else
            return Integer.MAX_VALUE;
    }

    public void set(int curtTime, int key, int value, int ttl) {
        // Write your code here
        int expired;
        if (ttl == 0)
            expired = -1;
        else
            expired = curtTime + ttl - 1;
        client.put(key, new Resource(value, expired));
    }

    public void delete(int curtTime, int key) {
        // Write your code here
        if (!client.containsKey(key))
            return;
        client.remove(key);
    }

    public int incr(int curtTime, int key, int delta) {
        // Write your code here
        if (get(curtTime, key) == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        client.get(key).value += delta;
        return client.get(key).value;
    }

    public int decr(int curtTime, int key, int delta) {
        // Write your code here
        if (get(curtTime, key) == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        client.get(key).value -= delta;
        return client.get(key).value;
    }
}

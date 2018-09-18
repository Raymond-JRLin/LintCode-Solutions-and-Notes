// 24. LFU Cache
// Description
// LFU (Least Frequently Used) is a famous cache eviction algorithm.
//
// For a cache with capacity k, if the cache is full and need to evict a key in it, the key with the lease frequently used will be kicked out.
//
// Implement set and get method for LFU cache.


public class LFUCache {
    // LRU cache 和使用的顺序有关， 而 LFU cache 与用的次数有关
    // 但用的次数相同的可能有多个 node
    Map<Integer, Node> nodeMap;
    LinkedHashSet[] freqList;
    int size;
    int minFreq;
    int maxFreq;
    // @param capacity, an integer
    public LFUCache(int capacity) {
        // Write your code here
        nodeMap = new HashMap<Integer, Node>();
        freqList = new LinkedHashSet[capacity * 2];
        size = capacity;
        minFreq = 1;
        maxFreq = size * 2 - 1;
        initFreqList(); // frequency list is global variable so we don't need argument
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // Write your code here
        Node node = nodeMap.get(key);
        if (node == null) {
            // if this node does not exist
            if (nodeMap.size() == size) {
                // if size if full, we should evict first
                eviction(); // we evit one node with min frequency so we don't need argument
            }
            // add this new node
            node = addNode(key, value); // get this node to add its frequency
        } else {
            // if this node existed already
            node.val = value; // change its value
        }
        // add frequency
        addFreq(node);

    }

    public int get(int key) {
        // Write your code here
        Node node = nodeMap.get(key);
        if (node != null) {
            // if it exits
            addFreq(node); // add its frequency
            return node.val;
        } else {
            return -1;
        }
    }
    private void eviction() {
        int count = 0;
        int target = 1;
        while (count < target) {
            LinkedHashSet<Node> minFreqSet = freqList[minFreq];
            Iterator<Node> it = minFreqSet.iterator();
            if (count < target && it.hasNext()) {

                Node node = it.next(); // get node needed to be evicted
                // evict it from node map
                nodeMap.remove(node.key);
                // evict it from frequency list
                minFreqSet.remove(node);
                count++; // succeeded
            }
            if (!it.hasNext()) {
                // if list of this frequency is emtpy, find next min frequency
                findNextMinFreq();
            }
        }



    }
    private Node addNode(int key, int value) {
        Node newNode = new Node(key, value); // create a new node
        nodeMap.put(key, newNode); // put into node map
        LinkedHashSet set = freqList[0]; // get freq == 0 position LHS
        set.add(newNode); // put into frequency set
        minFreq = 1; // reset min frequency
        return newNode; // return this new added node for use of set method
    }
    private void addFreq(Node node) {
        int currFreq = node.freq;
        if (currFreq < maxFreq) {
            int nextFreq = currFreq + 1;
            LinkedHashSet<Node> currSet = freqList[currFreq];
            LinkedHashSet<Node> nextSet = freqList[nextFreq];
            // remove it from current set
            currSet.remove(node);
            // change frequency
            node.freq = nextFreq;
            // add it into next set
            nextSet.add(node);
            // change node map
            nodeMap.put(node.key, node);
            // change min frequency if necessary
            if (minFreq == currFreq && currSet.isEmpty()) {
                // if min frequency is current node's frequency and this node is the last one node with min frequency
                minFreq = nextFreq;
            }
        } else {
            // current frequency == max frequency
            LinkedHashSet<Node> set = freqList[currFreq];
            // remove it first
            set.remove(node);
            // re-insert it to move it rear
            set.add(node);
        }
    }
    private void findNextMinFreq() {
        while (minFreq <= maxFreq && freqList[minFreq].isEmpty()) {
            minFreq++;
        }
        if (minFreq > maxFreq) {
            minFreq = 0;
        }
    }
    private void initFreqList() {
        for (int i = 0; i < size * 2; i++) {
            freqList[i] = new LinkedHashSet<Node>();
        }
    }
}
class Node {
    int key;
    int val;
    int freq;
    Node(int k, int v) {
        key = k;
        val = v;
        freq = 0;
    }
}


answer:


public class LFUCache {

    private final Map<Integer, CacheNode> cache;
    private final LinkedHashSet[] frequencyList;
    private int lowestFrequency;
    private int maxFrequency;
    private final int maxCacheSize;

    // @param capacity, an integer
    public LFUCache(int capacity) {
        // Write your code here
        this.cache = new HashMap<Integer, CacheNode>(capacity);
        this.frequencyList = new LinkedHashSet[capacity * 2];
        this.lowestFrequency = 0;
        this.maxFrequency = capacity * 2 - 1;
        this.maxCacheSize = capacity;
        initFrequencyList();
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // Write your code here
        CacheNode currentNode = cache.get(key);
        if (currentNode == null) {
            if (cache.size() == maxCacheSize) {
                doEviction();
            }
            LinkedHashSet<CacheNode> nodes = frequencyList[0];
            currentNode = new CacheNode(key, value, 0);
            nodes.add(currentNode);
            cache.put(key, currentNode);
            lowestFrequency = 0;
        } else {
            currentNode.v = value;
        }
        addFrequency(currentNode);
    }

    public int get(int key) {
        // Write your code here
        CacheNode currentNode = cache.get(key);
        if (currentNode != null) {
            addFrequency(currentNode);
            return currentNode.v;
        } else {
            return -1;
        }
    }

    public void addFrequency(CacheNode currentNode) {
        int currentFrequency = currentNode.frequency;
        if (currentFrequency < maxFrequency) {
            int nextFrequency = currentFrequency + 1;
            LinkedHashSet<CacheNode> currentNodes = frequencyList[currentFrequency];
            LinkedHashSet<CacheNode> newNodes = frequencyList[nextFrequency];
            moveToNextFrequency(currentNode, nextFrequency, currentNodes, newNodes);
            cache.put(currentNode.k, currentNode);
            if (lowestFrequency == currentFrequency && currentNodes.isEmpty()) {
                lowestFrequency = nextFrequency;
            }
        } else {
            // Hybrid with LRU: put most recently accessed ahead of others:
            LinkedHashSet<CacheNode> nodes = frequencyList[currentFrequency];
            nodes.remove(currentNode);
            nodes.add(currentNode);
        }
    }

    public int remove(int key) {
        CacheNode currentNode = cache.remove(key);
        if (currentNode != null) {
            LinkedHashSet<CacheNode> nodes = frequencyList[currentNode.frequency];
            nodes.remove(currentNode);
            if (lowestFrequency == currentNode.frequency) {
                findNextLowestFrequency();
            }
            return currentNode.v;
        } else {
            return -1;
        }
    }

    public int frequencyOf(int key) {
        CacheNode node = cache.get(key);
        if (node != null) {
            return node.frequency + 1;
        } else {
            return 0;
        }
    }

    public void clear() {
        for (int i = 0; i <= maxFrequency; i++) {
            frequencyList[i].clear();
        }
        cache.clear();
        lowestFrequency = 0;
    }

    public int size() {
        return cache.size();
    }

    public boolean isEmpty() {
        return this.cache.isEmpty();
    }

    public boolean containsKey(int key) {
        return this.cache.containsKey(key);
    }

    private void initFrequencyList() {
        for (int i = 0; i <= maxFrequency; i++) {
            frequencyList[i] = new LinkedHashSet<CacheNode>();
        }
    }

    private void doEviction() {
        int currentlyDeleted = 0;
        double target = 1; // just one
        while (currentlyDeleted < target) {
            LinkedHashSet<CacheNode> nodes = frequencyList[lowestFrequency];
            if (nodes.isEmpty()) {
                break;
            } else {
                Iterator<CacheNode> it = nodes.iterator();
                while (it.hasNext() && currentlyDeleted++ < target) {
                    CacheNode node = it.next();
                    it.remove();
                    cache.remove(node.k);
                }
                if (!it.hasNext()) {
                    findNextLowestFrequency();
                }
            }
        }
    }

    private void moveToNextFrequency(CacheNode currentNode, int nextFrequency,
                                     LinkedHashSet<CacheNode> currentNodes,
                                     LinkedHashSet<CacheNode> newNodes) {
        currentNodes.remove(currentNode);
        newNodes.add(currentNode);
        currentNode.frequency = nextFrequency;
    }

    private void findNextLowestFrequency() {
        while (lowestFrequency <= maxFrequency && frequencyList[lowestFrequency].isEmpty()) {
            lowestFrequency++;
        }
        if (lowestFrequency > maxFrequency) {
            lowestFrequency = 0;
        }
    }

    private class CacheNode {
        public final int k;
        public int v;
        public int frequency;

        public CacheNode(int k, int v, int frequency) {
            this.k = k;
            this.v = v;
            this.frequency = frequency;
        }
    }
}

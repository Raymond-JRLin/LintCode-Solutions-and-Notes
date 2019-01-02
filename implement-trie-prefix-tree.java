// 442. Implement Trie (Prefix Tree)
// Description
// Implement a trie with insert, search, and startsWith methods.
//
// You may assume that all inputs are consist of lowercase letters a-z.
//
// Have you met this question in a real interview?
// Example
// insert("lintcode")
// search("code")
// >>> false
// startsWith("lint")
// >>> true
// startsWith("linterror")
// >>> false
// insert("linterror")
// search("lintcode)
// >>> true
// startsWith("linterror")
// >>> true


/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {
    // Initialize your data structure here.

    // method 1: use array
    // public TrieNode[] children;
    // public boolean isWord;
    // public TrieNode() {
    //     children = new TrieNode[26];
    //     isWord = false;
    // }

    // method 2: HashMap
    public HashMap<Character, TrieNode> map;
    public boolean isWord;
    public TrieNode() {
        map = new HashMap<Character, TrieNode>();
        isWord = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        // method 1: use array
        // TrieNode head = root;
        // for (int i = 0; i < word.length(); i++) {
        //     int pos = word.charAt(i) - 'a';
        //     if (head.children[pos] == null) {
        //         // if current char is null then create a new one
        //         head.children[pos] = new TrieNode();
        //     }
        //     // keep going down with connection
        //     head = head.children[pos];
        // }
        // head.isWord = true;

        // method 2: HashMap
        TrieNode head = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (head.map.containsKey(c)) {
                head = head.map.get(c);
            } else {
                TrieNode node = new TrieNode();
                head.map.put(c, node);
                head = node;
            }
        }
        head.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        // method 1: use array
        // TrieNode head = root;
        // for (int i = 0; i < word.length(); i++) {
        //     int pos = word.charAt(i) - 'a';
        //     if (head.children[pos] == null) {
        //         return false;
        //     } else {
        //         // keep searching down
        //         head = head.children[pos];
        //     }
        // }
        // return head.isWord;

        // method 2: HashMap
        TrieNode head = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (head.map.containsKey(c)) {
                head = head.map.get(c);
            } else {
                return false;
            }
        }
        return head.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        // method 1: use array
        // TrieNode head = root;
        // for (int i = 0; i < prefix.length(); i++) {
        //     int pos = prefix.charAt(i) - 'a';
        //     if (head.children[pos] == null) {
        //         return false;
        //     } else {
        //         head = head.children[pos];
        //     }
        // }
        // // 这里不能判断是否等于null， 因为在 for loop 里其实已经到了 prefix 下一个了， 所以只要 for loop 里没有返回 false， 那就意味着找到了
        // return true;

        // method 2: HashMap
        TrieNode head = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!head.map.containsKey(c)) {
                return false;
            } else {
                head = head.map.get(c);
            }
        }
        return true;
    }
}

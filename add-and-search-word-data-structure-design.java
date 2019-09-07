public class WordDictionary {

    TrieNode root = new TrieNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
        // Write your code here
        TrieNode head = root;

        // method 1: array
        // for (int i = 0; i < word.length(); i++) {
        //     int pos = word.charAt(i) - 'a';
        //     if (head.children[pos] == null) {
        //         head.children[pos] = new TrieNode();
        //     }
        //     head = head.children[pos];
        // }
        // head.isWord = true;

        // method 2: map
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!head.map.containsKey(c)) {
                TrieNode node = new TrieNode();
                head.map.put(c, node);
            }
            head = head.map.get(c);
        }
        head.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        // Write your code here
        // 因为 "." 可以代表任意 char， 所以如果遇到了 "." 就要递归调用去查找所有可能性， 只要有一个结果是 true 就可以返回 true
        TrieNode head = root;

        // method 1: array
        // return searchWordArray(head, word, 0);

        // method 2: map
        return searchWordMap(head, word, 0);
    }
    // method 1: array
    // private boolean searchWordArray(TrieNode head, String word, int index) {
    //     if (index == word.length()) {
    //         // 查找完了 word， 如果不是一个 valid 的 word 就可以直接返回 false
    //         return head.isWord;
    //     }
    //     char c = word.charAt(index);
    //     if (c == '.') {
    //         // for loop 一遍当前 node 的数组
    //         for (TrieNode node : head.children) {
    //             if (node != null) {
    //                 // 当数组里面有 valid 的 TrieNode 的时候， 才可以继续查找
    //                 if (searchWord(node, word, index + 1) == true) {
    //                     return true;
    //                 }
    //             }
    //         }
    //         // 此时 for loop 结束， 如果没找到， 那就没有可能的组合， 可以直接返回 false
    //         return false;
    //     } else if (head.children[c - 'a'] != null) {
    //         // 如果不是 '.'， 有相对应的下一个 node， 继续查找
    //         return searchWord(head.children[c - 'a'], word, index + 1);
    //     } else {
    //         return false;
    //     }
    // }

    // method 2: map
    private boolean searchWordMap(TrieNode head, String word, int index) {
        if (index == word.length()) {
            // 查找完了 word， 如果不是一个 valid 的 word 就可以直接返回 false
            return head.isWord;
        }
        char c = word.charAt(index);
        if (c == '.') {
            // for loop 一遍当前 node 的 map
            for (Map.Entry<Character, TrieNode> entry : head.map.entrySet()) {
                // 当数组里面有 valid 的 TrieNode 的时候， 才可以继续查找
                if (searchWordMap(entry.getValue(), word, index + 1) == true) {
                    return true;
                }
            }
            // 此时 for loop 结束， 如果没找到， 那就没有可能的组合， 可以直接返回 false
            return false;
        } else if (head.map.get(c) != null) {
            // 如果不是 '.'， 有相对应的下一个 node， 继续查找
            return searchWordMap(head.map.get(c), word, index + 1);
        } else {
            return false;
        }
    }
}
// method 1: array
// class TrieNode {
//     public TrieNode[] children;
//     public boolean isWord;
//     public TrieNode() {
//         children = new TrieNode[26];
//         isWord = false;
//     }
// }

// method 2: map
class TrieNode {
    public HashMap<Character, TrieNode> map;
    public boolean isWord;
    public TrieNode() {
        map = new HashMap<Character, TrieNode>();
        isWord = false;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

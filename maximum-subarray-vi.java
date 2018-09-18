// 722. Maximum Subarray VI

// Given an array of integers. find the maximum XOR subarray value in given array.
//
// What's the XOR: https://en.wikipedia.org/wiki/Exclusive_or
//
// Expected time complexity O(n).

// Example
// Given nums = [1, 2, 3, 4], return 7
// The subarray [3, 4] has maximum XOR value
//
// Given nums = [8, 1, 2, 12, 7, 6], return 15
// The subarray [1, 2, 12] has maximum XOR value
//
// Given nums = [4, 6], return 6
// The subarray [6] has maximum XOR value


public class Solution {
    /**
     * @param nums: the array
     * @return: the max xor sum of the subarray in a given array
     */
    public int maxXorSubarray(int[] nums) {
        // write code here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // return mytry(nums);

        return method2(nums);
    }

    private int method2(int[] nums) {
        // Trie: O(N)
        // ref: https://www.geeksforgeeks.org/find-the-maximum-subarray-xor-in-a-given-array/
        // Create a Trie and insert 0 into it
        root = new TrieNode();
        insert(0);

        // Initialize answer and xor of current prefix
        int result = Integer.MIN_VALUE;
        int pre_xor = 0;

        // Traverse all input array element
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // update current prefix xor and insert it into Trie
            pre_xor = pre_xor ^ nums[i];
            insert(pre_xor);

            // Query for current prefix xor in Trie and update result if required
            result = Math.max(result, query(pre_xor));

        }
        return result;
    }
    // Assumed int size
    private final int INT_SIZE = 32;

    // A Trie Node
    private class TrieNode {
        private int value;  // Only used in leaf nodes
        private TrieNode[] arr =  new TrieNode[2];
        public TrieNode() {
            value = 0;
            arr[0] = null;
            arr[1] = null;
        }
    }
    private TrieNode root;

    // Inserts pre_xor to trie with given root
    private void insert(int pre_xor) {
        TrieNode temp = root;

        // Start from the msb, insert all bits of pre_xor into Trie
        for (int i = INT_SIZE - 1; i >= 0; i--) {
            // Find current bit in given prefix
            int val = (pre_xor & (1 << i)) >= 1 ? 1 : 0;

            // Create a new node if needed
            if (temp.arr[val] == null) {
                temp.arr[val] = new TrieNode();
            }
            temp = temp.arr[val];
        }

        // Store value at leaf node
        temp.value = pre_xor;
    }

    // Finds the maximum XOR ending with last number in prefix XOR 'pre_xor' and returns the XOR of this maximum with pre_xor which is maximum XOR ending with last element of pre_xor.
    // 简单来说就是从左向右 for bits， 每次都尽可能地去找和当前 pre_xor 不同的 bit， 那么 XOR 的结果就会是最大的， 也就是非从头到现在的位置的最大值, i.e. [...***///] XOR 掉的是 ...， 得到的最大值是其中某一段 ***， /// 是还没有查的后面部分
    private int query(int pre_xor) {
        TrieNode temp = root;
        for (int i = INT_SIZE - 1; i >= 0; i--) {
            // Find current bit in given prefix
            int val = (pre_xor & (1 << i)) >= 1 ? 1 : 0;

            // Traverse Trie, first look for a prefix that has opposite bit
            if (temp.arr[1 - val] != null) {
                temp = temp.arr[1 - val];
            } else if (temp.arr[val] != null) {
                // If there is no prefix with opposite bit, then look for same bit.
                temp = temp.arr[val];
            }
        }
        return pre_xor ^ (temp.value);
    }

    private int mytry(int[] nums) {
        // brute force: O(N ^ 2) time
        int n = nums.length;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int curr = 0;
            for (int j = i; j < n; j++) {
                curr ^= nums[j];
                // we should put comparison here, since we'd like to compare every possible subarray, like [...****...], not [...***]
                result = Math.max(result, curr);
            }
        }
        return result;
    }
}

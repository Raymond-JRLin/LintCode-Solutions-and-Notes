// 71. Binary Tree Zigzag Level Order Traversal
// 中文English
// Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
//
// Example
// Example 1:
//
// Input:{1,2,3}
// Output:[[1],[3,2]]
// Explanation:
//     1
//    / \
//   2   3
// it will be serialized {1,2,3}
// Example 2:
//
// Input:{3,9,20,#,#,15,7}
// Output:[[3],[20,9],[15,7]]
// Explanation:
//     3
//    / \
//   9  20
//     /  \
//    15   7
// it will be serialized {3,9,20,#,#,15,7}


/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: A list of lists of integer include
     *          the zigzag level order traversal of its nodes' values
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // Queue<TreeNode> queue = new LinkedList<>();
        // queue.offer(root);
        // boolean isLeft = true;
        // while (!queue.isEmpty()) {
        //     ArrayList<Integer> level = new ArrayList<>();
        //     int size = queue.size();
        //     for (int i = 0; i < size; i++) {
        //         TreeNode node = queue.poll();
        //         level.add(node.val);
        //         if (isLeft) {
        //             if (node.right != null) {
        //                 queue.offer(node.right);
        //             }
        //             if (node.left != null) {
        //                 queue.offer(node.left);
        //             }
        //         } else {
        //             if (node.left != null) {
        //                 queue.offer(node.left);
        //             }
        //             if (node.right != null) {
        //                 queue.offer(node.right);
        //             }
        //         }
        //     }
        //     isLeft = !isLeft;
        //     result.add(level);
        // }
        // return result;
        // above is wrong, we cannot use Queue to achieve right answer
        // 因为按照queue来倒的话，会出现当前左右子数的子节点放入时是内部按照顺序，但是同一层级子树之间是相反的：
            //       1
            //      / \
            //     2   3
            //    / \ / \
            //    4 5 6 7
        // 2 3 是先加入3，再加入2的，但是3拿出来的时候会先加入6，再加入7，等到2拿出来的时候会先加入4再加入5，导致[6, 7, 4, 5]
        // 所以应该同一层级，按照取出来的顺序（左到右或者右到左）分别加入他们的子树，再在下一层取得时候整体反过来 -> stack
        // Stack<TreeNode> stack = new Stack<>();
        // stack.push(root);
        // boolean isLeft = true;
        // while (!stack.empty()) {
        //     ArrayList<Integer> level = new ArrayList<>();
        //     int size = stack.size();
        //     for (int i = 0; i < size; i++) {
        //         TreeNode node = stack.pop();
        //         level.add(node.val);
        //         if (isLeft) {
        //             if (node.left != null) {
        //                 stack.push(node.left);
        //             }
        //             if (node.right != null) {
        //                 stack.push(node.right);
        //             }
        //         } else {
        //             if (node.right != null) {
        //                 stack.push(node.right);
        //             }
        //             if (node.left != null) {
        //                 stack.push(node.left);
        //             }
        //         }
        //     }
        //     isLeft = !isLeft;
        //     result.add(level);
        // }
        // return result;
        // above is still wrong, because 拿出3的时候，push 5，然后就会把5pop出来，2 却留在了后面
        // 所以要用两个stack 轮流使用颠倒
        Stack<TreeNode> currStack = new Stack<>();
        Stack<TreeNode> nextStack = new Stack<>();
        Stack<TreeNode> temp;
        currStack.push(root);
        boolean isLeft = true;
        while (!currStack.empty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = currStack.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = currStack.pop();
                level.add(node.val);
                if (isLeft) {
                    if (node.left != null) {
                        nextStack.push(node.left);
                    }
                    if (node.right != null) {
                        nextStack.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        nextStack.push(node.right);
                    }
                    if (node.left != null) {
                        nextStack.push(node.left);
                    }
                }
            }
            isLeft = !isLeft;
            result.add(level);
            // switch stacks
            temp = nextStack;
            nextStack = currStack;
            currStack = temp;
        }
        return result;
    }
}

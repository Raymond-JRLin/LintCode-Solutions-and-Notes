// 474. Lowest Common Ancestor II
// 中文English
// Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
//
// The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
//
// The node has an extra attribute parent which point to the father of itself. The root's parent is null.
//
// Example
// Example 1:
//
// Input：{4,3,7,#,#,5,6},3,5
// Output：4
// Explanation：
//      4
//      / \
//     3   7
//        / \
//       5   6
// LCA(3, 5) = 4
// Example 2:
//
// Input：{4,3,7,#,#,5,6},5,6
// Output：7
// Explanation：
//       4
//      / \
//     3   7
//        / \
//       5   6
// LCA(5, 6) = 7


/**
 * Definition of ParentTreeNode:
 *
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */
public class Solution {
    /**
     * @param root: The root of the tree
     * @param A, B: Two node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                                 ParentTreeNode A,
                                                 ParentTreeNode B) {
        // Write your code here
        if (root == null) {
            return null;
        }
        if (root == A || root == B) {
            return root;
        }
        // ParentTreeNode left = lowestCommonAncestorII(root.left, A, B);
        // ParentTreeNode right = lowestCommonAncestorII(root.right, A, B);
        // if (left != null && right != null) {
        //     return root;
        // }
        // if (left != null) {
        //     return left;
        // }
        // if (right != null) {
        //     return right;
        // }
        // return null;
        ArrayList<ParentTreeNode> pathOfA = findPath(A);
        ArrayList<ParentTreeNode> pathOfB = findPath(B);
        ParentTreeNode result = null;
        // int size = pathOfA.size() < pathOfB.size() ? pathOfA.size() : pathOfB.size();
        // for (int i = size - 1; i > 0; i++) {
        //     if (pathOfA.get(i) != pathOfB.get(i)) {
        //         break;
        //     } else {
        //         result = pathOfA.get(i);
        //     }
        // }
        // above is wrong, we cannot use for loop because we need to loop from last element of both lists
        int i = pathOfA.size() - 1;
        int j = pathOfB.size() - 1;
        while (i >= 0 && j >= 0) {
            if (pathOfA.get(i) != pathOfB.get(j)) {
                break;
            } else {
                result = pathOfA.get(i);
                i--;
                j--;
            }
        }
        return result;
    }
    private ArrayList<ParentTreeNode> findPath(ParentTreeNode node) {
        ArrayList<ParentTreeNode> list = new ArrayList<>();
        while (node != null) {
            list.add(node);
            node = node.parent;
        }
        return list;
    }
}

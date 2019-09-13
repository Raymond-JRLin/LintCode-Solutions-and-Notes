// 7. Serialize and Deserialize Binary Tree
// 中文English
// Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.
//
// Example
// Example 1:
//
// Input：{3,9,20,#,#,15,7}
// Output：{3,9,20,#,#,15,7}
// Explanation：
// Binary tree {3,9,20,#,#,15,7},  denote the following structure:
// 	  3
// 	 / \
// 	9  20
// 	  /  \
// 	 15   7
// it will be serialized {3,9,20,#,#,15,7}
// Example 2:
//
// Input：{1,2,3}
// Output：{1,2,3}
// Explanation：
// Binary tree {1,2,3},  denote the following structure:
//    1
//   / \
//  2   3
// it will be serialized {1,2,3}
// Our data serialization use BFS traversal. This is just for when you got Wrong Answer and want to debug the input.
//
// You can use other method to do serializaiton and deserialization.
//
// Notice
// There is no limit of how you deserialize or serialize a binary tree, LintCode will take your output of serialize as the input of deserialize, it won't check the result of serialize.
//


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
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if (root == null) {
            return "{}";
        }
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (node == null) {
                continue;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(queue.get(0).val);
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i) == null) {
                sb.append(",#");
            } else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data.equals("{}")) {
            return null;
        }
        // 按照 BFS 一层一层处理
        String[] values = data.substring(1, data.length() - 1).split(","); // remove { and } at the head and tail
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        list.add(root);
        int index = 0;
        boolean isLeftChild = true;
        for (int i = 1; i < values.length; i++) {
            if (!values[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(values[i]));
                if (isLeftChild) {
                    list.get(index).left = node;
                } else {
                    list.get(index).right = node;
                }
                list.add(node);
            }
            if (!isLeftChild) {
                index++;
            }
            isLeftChild = !isLeftChild;
        }
        return root;
    }
}

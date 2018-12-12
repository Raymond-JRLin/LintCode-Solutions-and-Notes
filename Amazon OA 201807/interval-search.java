// 1564. Interval Search
// Description
// Given a List of intervals, the length of each interval is 1000, such as [500,1500], [2100,3100].Give a number arbitrarily and determine if the number belongs to any of the intervals.return True or False.
//
// Have you met this question in a real interview?
// Example
// Given:
//
// List = [[100,1100],[1000,2000],[5500,6500]]
// number = 6000
// Return: True


public class Solution {
    /**
     * @param intervalList:
     * @param number:
     * @return: return True or False
     */
    public String isInterval(List<List<Integer>> intervalList, int number) {
        // Write your code here
        if (intervalList == null || intervalList.size() == 0 || intervalList.get(0).size() == 0) {
            return "False";
        }

        // return mytry(intervalList, number);

        // return mytry2(intervalList, number);

        return method3(intervalList, number);
    }

    public String method3(List<List<Integer>> intervalList, int number) {
        // interval BST: O(logn) 实现 search 和 insert
        TreeNode root = new TreeNode(intervalList.get(0).get(0));
        for (int i = 1; i < intervalList.size(); i++) {
            int val = intervalList.get(i).get(0);
            TreeNode newNode = new TreeNode(val);
            addNodeToBST(root, newNode);
        }

        int num = number - 1000;  // we need find a node bigger than 5000 and smaller than 6000
        if (search(root, num)) {
            return "True";
        } else {
            return "False";
        }
    }

    // O(logn)
    private boolean search(TreeNode root, int num){
        if (root == null){
            return false;
        }
        if (root.val >= num && root.val < num + 1000) {
            return true;
        } else if (num < root.val) {
            return search(root.left, num);
        } else {
            return search(root.right, num);
        }
    }

    // O(logn)
    private void addNodeToBST(TreeNode root, TreeNode cur){
        if (cur.val < root.val) {
            if (root.left == null) {
                root.left = cur;
            } else {
                addNodeToBST(root.left, cur);
            }

        } else if (cur.val > root.val) {
            if (root.right == null) {
                root.right = cur;
            } else {
                addNodeToBST(root.right, cur);
            }
        }
    }

    private class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private String mytry2(List<List<Integer>> intervals, int num) {
        // BS: O(NlogN) time since we should sort list
        // sort
        Collections.sort(intervals, (o1, o2) -> {
            return Integer.compare(o1.get(0), o2.get(0));
        });
        int start = bs(intervals, num, 0); // equal or Less
        int end = bs(intervals, num, 1); // equal or larger
        if (start == end && start != -1) {
            return "True";
        } else {
            return "False";
        }
    }
    private int bs(List<List<Integer>> inters, int target, int i) {
        int start = 0;
        int end = inters.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (inters.get(mid).get(i) == target) {
                return mid;
            } else if (inters.get(mid).get(i) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (i == 0) {
            if (inters.get(end).get(i) < target) {
                return end;
            } else if (inters.get(start).get(i) < target) {
                return start;
            } else {
                return -1;
            }
        } else {
            if (inters.get(start).get(i) > target) {
                return start;
            } else if (inters.get(end).get(i) > target) {
                return end;
            } else {
                return -1;
            }
        }
    }

    private String mytry(List<List<Integer>> intervals, int num) {
        // brute force
        for (List<Integer> inter : intervals) {
            if (num >= inter.get(0) && num <= inter.get(1)) {
                return "True";
            }
        }
        return "False";
    }
}

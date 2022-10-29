package medium;

import common.PrintUtil;
import common.TreeNode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/10/28 8:11 PM
 */
public class ValidateBinarySearchTree_98 {

    public long[] recursion(TreeNode root) {
        if (root == null) {
            return null;
        }
        long[] leftResult = recursion(root.left);
        long[] rightResult = recursion(root.right);
        if (leftResult != null && leftResult[0] == 0) {
            return new long[] {0, 0, 0};
        }
        if (rightResult != null && rightResult[0] == 0) {
            return new long[] {0, 0, 0};
        }

        long leftMax = leftResult == null ? Long.MIN_VALUE : leftResult[2];
        long rightMin = rightResult == null ? Long.MAX_VALUE : rightResult[1];
        if (root.val <= leftMax || root.val >= rightMin) {
            return new long[] {0, 0, 0};
        }

        long min = leftResult == null ? root.val : leftResult[1];
        long max = rightResult == null ? root.val : rightResult[2];
        return new long[] {1, min, max};
    }

    public boolean isValidBST(TreeNode root) {
        long[] flags = recursion(root);
        return flags[0] == 1;
    }

    public boolean isValidBSTv2(TreeNode root) {
        Deque<ScanNode> queue = new LinkedList();
        queue.addFirst(new ScanNode(1, root));
        Integer pre = null;
        while (!queue.isEmpty()) {
            ScanNode cur = queue.removeFirst();
            if (cur.op == 2) {
                if (pre != null) {
                    if (pre >= cur.node.val) {
                        return false;
                    }
                }
                pre = cur.node.val;
            } else {
                if (cur.node.right != null) {
                    queue.addFirst(new ScanNode(1, cur.node.right));
                }
                queue.addFirst(new ScanNode(2, cur.node));
                if (cur.node.left != null) {
                    queue.addFirst(new ScanNode(1, cur.node.left));
                }
            }
        }
        return true;
    }

    Boolean result = true;
    TreeNode pre = null;
    private void dfs(TreeNode root) {

        if (root == null ) {
            return;
        }
        dfs(root.left) ;
        if (pre != null) {
            if (pre.val > root.val) {
                result = false;
                return;
            }
        }
        pre = root;
        dfs(root.right);
    }

    // iterative

    public void iterateIn(TreeNode root) {
        Deque<ScanNode> queue = new LinkedList();
        queue.addFirst(new ScanNode(1, root));
        List<Integer> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            ScanNode cur = queue.removeFirst();
            if (cur.op == 2) {
                result.add(cur.node.val);
            } else {
                if (cur.node.right != null) {
                    queue.addFirst(new ScanNode(1, cur.node.right));
                }
                queue.addFirst(new ScanNode(2, cur.node));
                if (cur.node.left != null) {
                    queue.addFirst(new ScanNode(1, cur.node.left));
                }
            }
        }
        PrintUtil.print(result);
    }

    public void iteratePre(TreeNode root) {
        Deque<ScanNode> queue = new LinkedList();
        queue.addFirst(new ScanNode(1, root));
        List<Integer> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            ScanNode cur = queue.removeFirst();
            if (cur.op == 2) {
                result.add(cur.node.val);
            } else {
                if (cur.node.right != null) {
                    queue.addFirst(new ScanNode(1, cur.node.right));
                }
                if (cur.node.left != null) {
                    queue.addFirst(new ScanNode(1, cur.node.left));
                }
                queue.addFirst(new ScanNode(2, cur.node));
            }
        }
        PrintUtil.print(result);
    }

    public void iteratePost(TreeNode root) {
        Deque<ScanNode> queue = new LinkedList();
        queue.addFirst(new ScanNode(1, root));
        List<Integer> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            ScanNode cur = queue.removeFirst();
            if (cur.op == 2) {
                result.add(cur.node.val);
            } else {
                queue.addFirst(new ScanNode(2, cur.node));
                if (cur.node.right != null) {
                    queue.addFirst(new ScanNode(1, cur.node.right));
                }
                if (cur.node.left != null) {
                    queue.addFirst(new ScanNode(1, cur.node.left));
                }
            }
        }
        PrintUtil.print(result);
    }

    static class ScanNode {
        int op;  // 1 SCAN, 2 out
        TreeNode node;
        ScanNode(int op, TreeNode node) {
            this.op = op;
            this.node = node;
        }

    }


    public static void main(String[]args) {
        ValidateBinarySearchTree_98 v = new ValidateBinarySearchTree_98();
        TreeNode root = TreeNode.create();
        v.iteratePre(root);
        v.iterateIn(root);
        v.iteratePost(root);
    }

}

package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.leetcode.entity.TreeNode;

public class AlgorithmController101to120 {
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isSymmetricImpl(root.left, root.right);
  }

  public boolean isSymmetricImpl(TreeNode left, TreeNode right) {
    if (left == null || right == null) {
      return left == right;
    }
    if (left.val != right.val) {
      return false;
    }
    return isSymmetricImpl(left.left, right.right) && isSymmetricImpl(left.right, right.left);
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    levelOrderImpl(root, 0, res);
    return res;
  }

  public void levelOrderImpl(TreeNode node, int level, List<List<Integer>> res) {
    if (node == null) {
      return;
    }
    if (res.size() <= level) {
      List<Integer> temp = new ArrayList<>();
      temp.add(node.val);
      res.add(temp);
    } else {
      res.get(level).add(node.val);
    }
    levelOrderImpl(node.left, level + 1, res);
    levelOrderImpl(node.right, level + 1, res);
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    if (root == null) {
      return res;
    }
    q.offer(root);
    boolean flag = true;
    while (!q.isEmpty()) {
      int len = q.size();
      LinkedList<Integer> temp = new LinkedList<>();
      for (int i = 0; i < len; i++) {
        TreeNode c = q.poll();
        if (flag) {
          temp.add(c.val);
        } else {
          temp.addFirst(c.val);
        }
        if (c.left != null) {
          q.add(c.left);
        }
        if (c.right != null) {
          q.add(c.right);
        }
      }
      res.add(temp);
      flag = !flag;
    }
    return res;
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder == null || inorder == null || preorder.length != inorder.length) {
      return null;
    }
    return buildTree(preorder, inorder, 0, preorder.length - 1, 0);
  }

  public TreeNode buildTree(int[] preorder, int[] inorder, int start, int end, int index) {
    if (start > end) {
      return null;
    }
    TreeNode node = new TreeNode(preorder[index]);
    int inIndex = start;
    while (preorder[index] != inorder[inIndex]) {
      inIndex++;
    }
    node.left = buildTree(preorder, inorder, start, inIndex - 1, index + 1);
    node.right = buildTree(preorder, inorder, inIndex + 1, end, index + inIndex - start + 1);
    return node;
  }

  public TreeNode buildTree106(int[] inorder, int[] postorder) {
    if (inorder == null || postorder == null || inorder.length != postorder.length) {
      return null;
    }
    return buildTree106(inorder, postorder, 0, postorder.length - 1, postorder.length - 1);
  }

  public TreeNode buildTree106(int[] inorder, int[] postorder, int start, int end, int index) {
    if (start > end) {
      return null;
    }
    TreeNode node = new TreeNode(postorder[index]);
    int inIndex = start;
    while (postorder[index] != inorder[inIndex]) {
      inIndex++;
    }
    node.right = buildTree106(inorder, postorder, inIndex + 1, end, index - 1);
    node.left = buildTree106(inorder, postorder, start, inIndex - 1, index - (end - inIndex) - 1);
    return node;
  }

}

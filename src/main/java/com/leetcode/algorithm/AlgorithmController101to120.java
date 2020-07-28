package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

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

}

package com.leetcode.algorithm;

import com.leetcode.entity.TreeNode;

public class AlgorithmController221to241 {
  public int maximalSquare(char[][] matrix) {
    int y = matrix.length;
    int x = matrix[0].length;
    int[][] dp = new int[y][x];
    int res = 0;

    for (int i = 0; i < y; i++) {
      for (int j = 0; j < x; j++) {
        if (matrix[i][j] == '1') {
          if (i == 0 || j == 0) {
            dp[i][j] = 1;
          } else {
            dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
          }
          res = Math.max(dp[i][j], res);
        }

      }
    }
    return res * res;
  }

  public int countNodes;

  public int countNodes(TreeNode root) {
    countNodesHelp(root);
    return countNodes;
  }

  public void countNodesHelp(TreeNode root) {
    if (root == null) {
      return;
    }
    this.countNodes += 1;
    this.countNodes(root.left);
    this.countNodes(root.right);
  }
}

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

  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    if (E >= C || F >= D || A >= G || B >= H) {
      return (C - A) * (D - B) + (G - E) * (H - F);
    }
    int left = Math.max(A, E);
    int right = Math.min(C, G);
    int top = Math.min(D, H);
    int bottom = Math.max(B, F);
    return (C - A) * (D - B) - (right - left) * (top - bottom) + (G - E) * (H - F);
  }

}

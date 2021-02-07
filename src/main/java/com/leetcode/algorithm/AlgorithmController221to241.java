package com.leetcode.algorithm;

import java.util.Stack;

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

  public int calculate(String s) {
    Stack<Integer> sk = new Stack<>();
    int operator = 0;
    int res = 0;
    int sign = 1;
    for (int i = 0; i < s.length(); i++) {
      char ca = s.charAt(i);
      if (Character.isDigit(ca)) {
        operator = operator * 10 + (ca - '0');
      } else if (ca == '+') {
        res += sign * operator;
        sign = 1;
        operator = 0;
      } else if (ca == '-') {
        res += sign * operator;
        sign = -1;
        operator = 0;
      } else if (ca == '(') {
        sk.add(res);
        sk.add(sign);
        res = 0;
        sign = 1;
      } else if (ca == ')') {
        res += sign * operator;
        res *= sk.pop();
        res += sk.pop();
        operator = 0;
      }
    }
    return res + (sign * operator);
  }

}

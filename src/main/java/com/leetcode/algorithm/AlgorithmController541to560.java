package com.leetcode.algorithm;

import com.leetcode.entity.TreeNode;

public class AlgorithmController541to560 {

  public String reverseStr(String s, int k) {
    char[] sc = s.toCharArray();
    for (int i = 0; i < sc.length; i += 2 * k) {
      int end = Math.min(i + k - 1, sc.length - 1);
      reverse(sc, i, end);
    }
    return String.valueOf(sc);
  }

  public char[] reverse(char[] sc, int left, int right) {
    while (left < right) {
      char temp = sc[left];
      sc[left] = sc[right];
      sc[right] = temp;
      left++;
      right--;
    }
    return sc;
  }

  public int[][] updateMatrix(int[][] mat) {
    int rowMax = mat.length, colMax = mat[0].length;
    int[][] dp = new int[rowMax][colMax];
    int flag = (int) Math.pow(10, 7);
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (i == 0 && j == 0) {
          dp[i][j] = mat[i][j] == 0 ? 0 : flag;
          continue;
        }
        if (mat[i][j] == 0) {
          dp[i][j] = 0;
          continue;
        }
        if (i == 0) {
          dp[i][j] = dp[i][j - 1] + 1;
          continue;
        }
        if (j == 0) {
          dp[i][j] = dp[i - 1][j] + 1;
          continue;
        }
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
      }
    }
    for (int i = rowMax - 1; i >= 0; i--) {
      for (int j = colMax - 1; j >= 0; j--) {
        if (i == rowMax - 1 && j == colMax - 1) {
          continue;
        }
        if (mat[i][j] == 0) {
          continue;
        }
        if (i == rowMax - 1) {
          dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
          continue;
        }
        if (j == colMax - 1) {
          dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
          continue;
        }
        dp[i][j] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1]) + 1);
      }
    }
    return dp;
  }

  public int diameterOfBinaryTreeRes = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    return Math.max(diameterOfBinaryTreeHelp(root) - 1, diameterOfBinaryTreeRes);
  }

  public int diameterOfBinaryTreeHelp(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = diameterOfBinaryTreeHelp(root.left);
    int right = diameterOfBinaryTreeHelp(root.right);
    diameterOfBinaryTreeRes = Math.max(diameterOfBinaryTreeRes, left + right);
    return Math.max(left, right) + 1;
  }

  public String findContestMatch(int n) {
    String[] teams = new String[n];
    for (int i = 0; i < n; i++) {
      teams[i] = Integer.toString(i + 1);
    }
    for (; n > 1; n /= 2) {
      for (int i = 0; i < n; i++) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(teams[i]);
        sb.append(',');
        sb.append(teams[n - 1 - i]);
        sb.append(')');
        teams[i] = sb.toString();
      }
    }
    return teams[0];
  }


}

package com.leetcode.algorithm;

import java.util.*;

import com.leetcode.entity.ListNode;

public class AlgorithController61to80 {

  public ListNode rotateRight(ListNode head, int k) {
    int len = 0;
    ListNode currentNode = head;
    while(currentNode != null) {
      len++;
      currentNode = currentNode.next;
    }
    if (len <= 1 || k % len == 0) {
      return head;
    }
    int point = k;
    if (k > len) {
      point = k % len;
    }
    currentNode = head;
    int newLen = 1;
    ListNode result = null;
    while(currentNode != null) {
      if (newLen == len - point) {
        result = currentNode.next;
        currentNode.next = null;
        ListNode tempResult = result;
        while(true) {
          if (tempResult.next == null) {
            tempResult.next = head;
            break;
          } 
          tempResult = tempResult.next;
        }
        break;
      }
      currentNode = currentNode.next;
      newLen++;
    }
    return result;
  }

  public int uniquePaths(int m, int n) {
    int[][] board = new int[m][n];
    return countPath(0, 0, m, n, board);
  }

  public int countPath(int i, int j, int m, int n, int[][] board) {
    if (i >= m || j >= n) {
      return 0;
    }
    if (i == m - 1 && j == n - 1) {
      return 1;
    }
    if (board[i][j] != 0) {
      return board[i][j];
    }
    board[i][j] = countPath(i + 1, j, m, n, board) + countPath(i, j + 1, m, n, board);
    return board[i][j];
  }

  public int uniquePathsWithObstaclesSoSlow(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[][] board = new int[m][n];
    return countPathWithObstacles(0, 0, m, n, board, obstacleGrid);
  }

  public int countPathWithObstacles(int i, int j, int m, int n, int[][] board, int[][] sourceBoard) {
    if (i >= m || j >= n || sourceBoard[i][j] == 1) {
      return 0;
    }
    if (i == m - 1 && j == n - 1) {
      return 1;
    }
    if (board[i][j] != 0) {
      return board[i][j];
    }
    board[i][j] = countPathWithObstacles(i + 1, j, m, n, board, sourceBoard) + countPathWithObstacles(i, j + 1, m, n, board, sourceBoard);
    return board[i][j];
  }

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int rowLen = obstacleGrid[0].length;
    int[] dp = new int[rowLen];
    dp[0] = 1;
    for (int[] row: obstacleGrid) {
      for (int i = 0; i < row.length; i++) {
        if (row[i] == 1) {
          dp[i] = 0;
        } else if (i > 0) {
          dp[i] += dp[i - 1];
        }
      }
    }
    return dp[rowLen - 1];
  }

}

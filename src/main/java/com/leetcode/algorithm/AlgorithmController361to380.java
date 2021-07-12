package com.leetcode.algorithm;

public class AlgorithmController361to380 {

  public int maxKilledEnemies(char[][] grid) {
    int max = 0;
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == '0') {
          max = Math.max(max, maxKilledEnemiesHelp(grid, row, col));
        }
      }
    }
    return max;
  }

  public int maxKilledEnemiesHelp(char[][] grid, int row, int col) {
    int rowMax = grid.length;
    int colMax = grid[0].length;
    int num = 0;
    for (int i = row; i >= 0; i--) {
      char current = grid[i][col];
      if (current == 'W') {
        break;
      }
      if (current == 'E') {
        num++;
      }
    }
    for (int i = row; i < rowMax; i++) {
      char current = grid[i][col];
      if (current == 'W') {
        break;
      }
      if (current == 'E') {
        num++;
      }
    }
    for (int i = col; i >= 0; i--) {
      char current = grid[row][i];
      if (current == 'W') {
        break;
      }
      if (current == 'E') {
        num++;
      }
    }
    for (int i = col; i < colMax; i++) {
      char current = grid[row][i];
      if (current == 'W') {
        break;
      }
      if (current == 'E') {
        num++;
      }
    }
    return num;
  }

}

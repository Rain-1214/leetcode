package com.leetcode.algorithm;

import java.util.Arrays;

public class AlgorithmController461to480 {
  public int hammingDistance(int x, int y) {
    int i = x ^ y, res = 0;
    while (i > 0) {
      res++;
      i &= i - 1;
    }
    return res;
  }

  public int minMoves2(int[] nums) {
    Arrays.sort(nums);
    int res = 0;
    for (int num : nums) {
      res += Math.abs(nums[nums.length / 2] - num);
    }
    return res;
  }

  public int minMoves2II(int[] nums) {
    int mid = minMovesHelp(nums, 0, nums.length - 1, nums.length / 2);
    int res = 0;
    for (int num : nums) {
      res += Math.abs(mid - num);
    }
    return res;
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public int minMovesHelp(int[] nums, int left, int right, int target) {
    if (left == right) {
      return nums[left];
    }
    int temp = nums[right];
    int i = left, index = left;
    while (index < right) {
      if (nums[index] < temp) {
        swap(nums, i, index);
        i++;
      }
      index++;
    }
    swap(nums, i, right);
    if (i == target) {
      return nums[i];
    } else if (i > target) {
      return minMovesHelp(nums, left, i - 1, target);
    } else {
      return minMovesHelp(nums, i + 1, right, target);
    }
  }

  public static final int[][] dirs = new int[][] { new int[] { 0, 1 }, new int[] { 0, -1 }, new int[] { 1, 0 },
      new int[] { -1, 0 } };

  public int islandPerimeter(int[][] grid) {
    int rowMax = grid.length;
    int colMax = grid[0].length;
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (grid[i][j] == 1) {
          return islandPerimeterHelp(grid, i, j, rowMax, colMax);
        }
      }
    }
    return 0;
  }

  public int islandPerimeterHelp(int[][] grid, int i, int j, int rowMax, int colMax) {
    int res = 0;
    grid[i][j] = -1;
    for (int[] dir : dirs) {
      int x = i + dir[0], y = j + dir[1];
      if (x < 0 || x >= rowMax || y < 0 || y >= colMax || grid[x][y] == 0) {
        res++;
      } else if (grid[x][y] == 1) {
        res += islandPerimeterHelp(grid, x, y, rowMax, colMax);
      }
    }
    return res;
  }

  public int islandPerimeterII(int[][] grid) {
    int rowMax = grid.length;
    int colMax = grid[0].length;
    int res = 0;
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (grid[i][j] == 1) {
          res += 4;
          if (i > 0 && grid[i - 1][j] == 1) {
            res -= 2;
          }
          if (j > 0 && grid[i][j - 1] == 1) {
            res -= 2;
          }
        }
      }
    }
    return res;
  }

}

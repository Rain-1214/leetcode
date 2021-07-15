package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

import com.leetcode.entity.NestedInteger;

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

  class HitCounter {

    public Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public HitCounter() {
      this.map = new HashMap<>();
    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
      this.map.put(timestamp, this.map.getOrDefault(timestamp, 0) + 1);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
      int sum = 0;
      for (int i = timestamp; i > timestamp - 300; i--) {
        if (this.map.containsKey(i)) {
          sum += this.map.get(i);
        }
      }
      return sum;
    }
  }

  class HitCounterII {

    public Queue<Integer> q;
    public int count;

    /** Initialize your data structure here. */
    public HitCounterII() {
      this.q = new LinkedList<Integer>();
      this.count = 0;
    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
      this.q.add(timestamp);
      this.count++;
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
      if (q.size() == 0) {
        return 0;
      }
      while (q.peek() < timestamp - 299) {
        q.poll();
        count--;
        if (q.size() == 0) {
          return 0;
        }
      }
      return count;
    }
  }

  class HitCounterIII {

    public ArrayList<Integer> list;

    /** Initialize your data structure here. */
    public HitCounterIII() {
      this.list = new ArrayList<Integer>();
    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
      this.list.add(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
      int sum = 0;
      for (int i = list.size() - 1; i >= 0; i--) {
        if (list.get(i) < timestamp - 300) {
          break;
        }
        sum++;
      }
      return sum;
    }
  }

  public int maxSumSubmatrix(int[][] matrix, int k) {
    int res = Integer.MIN_VALUE;
    int rowMax = matrix.length, colMax = matrix[0].length;
    for (int i = 0; i < rowMax; i++) {
      int[] preSum = new int[colMax];
      for (int j = i; j < rowMax; j++) {
        for (int z = 0; z < colMax; z++) {
          preSum[z] = preSum[z] + matrix[j][z];
        }
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int area = 0;
        for (int n : preSum) {
          area += n;
          Integer temp = set.ceiling(area - k);
          if (temp != null) {
            res = Math.max(res, area - temp);
          }
          set.add(area);
        }
      }
    }
    return res;
  }

}

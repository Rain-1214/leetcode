package com.leetcode.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class AlgorithmController261to280 {
  public boolean validTree(int n, int[][] edges) {
    int[][] graph = new int[n][n];
    for (int[] edge : edges) {
      graph[edge[0]][edge[1]] = 1;
      graph[edge[1]][edge[0]] = 1;
    }
    Queue<Integer> q = new LinkedList<>();
    q.add(0);
    boolean[] visitor = new boolean[n];
    while (!q.isEmpty()) {
      int current = q.poll();
      visitor[current] = true;
      for (int i = 0; i < n; i++) {
        if (graph[current][i] == 1) {
          if (visitor[i]) {
            return false;
          }
          visitor[i] = true;
          graph[current][i] = 0;
          graph[i][current] = 0;
          q.add(i);
        }
      }
    }
    for (boolean v : visitor) {
      if (!v) {
        return false;
      }
    }
    return true;
  }

  public boolean isUgly(int n) {
    if (n == 0) {
      return false;
    }
    while (true) {
      if (n % 2 == 0) {
        n /= 2;
      } else if (n % 3 == 0) {
        n /= 3;
      } else if (n % 5 == 0) {
        n /= 5;
      } else {
        break;
      }
    }
    return n == 1;
  }

}

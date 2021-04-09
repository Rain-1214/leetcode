package com.leetcode.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
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

  class UglyNum {

    public int[] nums = new int[1690];

    public UglyNum() {
      PriorityQueue<Long> pq = new PriorityQueue<Long>();
      HashSet<Long> set = new HashSet<>();
      int[] primes = new int[] { 2, 3, 5 };
      int num = 0;
      pq.add(1L);
      while (num < 1690) {
        long head = pq.poll();
        nums[num] = (int) head;
        for (int n : primes) {
          long temp = head * n;
          if (!set.contains(temp)) {
            set.add(temp);
            pq.add(temp);
          }
        }
        num++;
      }
    }
  }

  class UglyNumII {

    public int[] nums = new int[1690];

    public UglyNumII() {
      int i2 = 0, i3 = 0, i5 = 0;
      nums[0] = 1;
      for (int i = 1; i < nums.length; i++) {
        int n2 = nums[i2] * 2;
        int n3 = nums[i3] * 3;
        int n5 = nums[i5] * 5;
        nums[i] = Math.min(Math.min(n2, n3), n5);
        if (nums[i] == n2) {
          i2++;
        }
        if (nums[i] == n3) {
          i3++;
        }
        if (nums[i] == n5) {
          i5++;
        }
      }
    }
  }

  public UglyNum u = new UglyNum();

  public int nthUglyNumber(int n) {
    return u.nums[n - 1];
  }

}

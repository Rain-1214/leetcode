package com.leetcode.algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

public class AlgorithmController501to520 {

  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    int len = profits.length;
    int curr = 0;
    int[][] arr = new int[len][2];
    for (int i = 0; i < len; i++) {
      arr[i][0] = profits[i];
      arr[i][1] = capital[i];
    }

    Arrays.sort(arr, (a, b) -> a[0] - b[0]);
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

    for (int i = 0; i < k; i++) {
      while (curr < len && arr[curr][1] <= w) {
        pq.add(arr[curr][0]);
        curr++;
      }
      if (pq.isEmpty()) {
        break;
      }
      w += pq.poll();
    }

    return w;
  }
}

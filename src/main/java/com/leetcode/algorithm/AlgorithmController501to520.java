package com.leetcode.algorithm;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

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

  public int[] nextGreaterElements(int[] nums) {
    int[] newArr = new int[nums.length * 2];
    for (int i = 0; i < nums.length; i++) {
      newArr[i] = nums[i];
      newArr[nums.length + i] = nums[i];
    }
    int[] res = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      int j = i + 1;
      boolean find = false;
      while (j < nums.length + i) {
        if (newArr[j] > newArr[i]) {
          find = true;
          res[i] = newArr[j];
          break;
        }
        j++;
      }
      if (!find) {
        res[i] = -1;
      }
    }
    return res;
  }

  public int[] nextGreaterElementsII(int[] nums) {
    int[] res = new int[nums.length];
    Deque<Integer> dq = new LinkedList<>();
    for (int i = 0; i < nums.length - 1; i++) {
      dq.addLast(i);
    }
    for (int i = nums.length - 1; i >= 0; i--) {
      if (!dq.isEmpty() && dq.peekLast() == i) {
        dq.pollLast();
      }
      while (!dq.isEmpty() && nums[dq.peekFirst()] <= nums[i]) {
        dq.removeFirst();
      }
      if (dq.isEmpty()) {
        res[i] = -1;
      } else {
        res[i] = nums[dq.peekFirst()];
      }
      dq.addFirst(i);
    }
    return res;
  }

  public String convertToBase7(int num) {
    if (num == 0) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    boolean isNegative = false;
    if (num < 0) {
      isNegative = true;
      num = -num;
    }
    while (num > 0) {
      sb.append(num % 7);
      num /= 7;
    }
    if (isNegative) {
      sb.append('-');
    }
    return sb.reverse().toString();
  }
}

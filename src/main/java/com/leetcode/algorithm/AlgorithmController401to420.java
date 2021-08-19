package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.leetcode.entity.TreeNode;

public class AlgorithmController401to420 {

  public List<String> readBinaryWatch(int turnedOn) {
    List<String> res = new ArrayList<>();
    int[] current = new int[10];
    int[] weight = new int[10];
    int temp = 1;
    for (int i = 0; i < 10; i++) {
      weight[i] = temp;
      temp *= 2;
      if (i == 3) {
        temp = 1;
      }
    }
    readBinaryWatchHelper(0, turnedOn, current, res, weight);
    return res;
  }

  public void readBinaryWatchHelper(int start, int num, int[] current, List<String> res, int[] weight) {
    if (num == 0) {
      String temp = generateTimeString(current, weight);
      if (temp != null) {
        res.add(temp);
      }
      return;
    }
    for (int i = start; i < current.length; i++) {
      current[i] = 1;
      readBinaryWatchHelper(i + 1, num - 1, current, res, weight);
      current[i] = 0;
    }
  }

  public String generateTimeString(int[] current, int[] weight) {
    StringBuilder sb = new StringBuilder();
    int temp = 0;
    for (int i = 0; i < 4; i++) {
      temp += current[i] * weight[i];
    }
    if (temp >= 12) {
      return null;
    }
    sb.append(temp);
    temp = 0;
    for (int i = 4; i < 10; i++) {
      temp += current[i] * weight[i];
    }
    if (temp >= 60) {
      return null;
    }
    sb.append(':');
    if (temp < 10) {
      sb.append('0');
    }
    sb.append(temp);
    return sb.toString();
  }

  public String removeKdigits(String num, int k) {
    Deque<Integer> dq = new LinkedList<Integer>();
    char[] ca = num.toCharArray();
    int i = 1;
    dq.addLast(ca[0] - '0');
    while (i < ca.length) {
      int current = ca[i] - '0';
      if (!dq.isEmpty() && current < dq.peekLast() && k > 0) {
        while (!dq.isEmpty() && current < dq.peekLast() && k > 0) {
          dq.removeLast();
          k--;
        }
      }
      dq.addLast(current);
      i++;
    }
    while (k > 0) {
      dq.removeLast();
      k--;
    }
    if (dq.size() == 0) {
      return "0";
    }
    while (!dq.isEmpty() && dq.peek() == 0) {
      dq.pop();
    }
    if (dq.size() == 0) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    while (!dq.isEmpty()) {
      sb.append(dq.pop());
    }
    return sb.toString();
  }

  public boolean canCross(int[] stones) {
    boolean[][] cache = new boolean[stones.length][stones.length];
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 2; i < stones.length; i++) {
      map.put(stones[i], i);
    }
    int prevDistance = stones[1] - stones[0];
    if (prevDistance > 1) {
      return false;
    }
    return canCross(stones, 1, prevDistance, map, cache);
  }

  public boolean canCross(int[] stones, int start, int prevDistance, Map<Integer, Integer> map, boolean[][] cache) {
    if (start == stones.length - 1) {
      return true;
    }
    for (int i = -1; i <= 1; i++) {
      int nextDistance = stones[start] + prevDistance + i;
      if (map.containsKey(nextDistance) && map.get(nextDistance) > start) {
        int index = map.get(nextDistance);
        if (cache[start][index]) {
          continue;
        }
        if (canCross(stones, index, stones[index] - stones[start], map, cache)) {
          return true;
        }
        cache[start][index] = true;
      }
    }
    return false;
  }

  public boolean canCrossII(int[] stones) {
    int n = stones.length;
    boolean[][] dp = new boolean[n][n];
    dp[0][0] = true;
    for (int i = 1; i < n; i++) {
      if (stones[i] - stones[i - 1] > i) {
        return false;
      }
    }
    for (int i = 1; i < n; i++) {
      for (int j = i - 1; j >= 0; j--) {
        int k = stones[i] - stones[j];
        if (k > j + 1) {
          break;
        }
        dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
        if (i == n - 1 && dp[i][k]) {
          return true;
        }
      }
    }
    return false;
  }

  public int sumOfLeftLeaves(TreeNode root) {
    return sumOfLeftLeaves(root, false);
  }

  public int sumOfLeftLeaves(TreeNode root, boolean isLeft) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return isLeft ? root.val : 0;
    }
    return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
  }

  public String toHex(int num) {
    if (num == 0) {
      return "0";
    }
    char[] dic = "0123456789abcdef".toCharArray();
    StringBuilder sb = new StringBuilder();
    while (num != 0) {
      int temp = num & 15;
      sb.append(dic[temp]);
      num >>>= 4;
    }
    return sb.reverse().toString();
  }
}

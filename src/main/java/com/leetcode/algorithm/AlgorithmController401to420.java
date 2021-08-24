package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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

  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, (a, b) -> {
      if (a[0] == b[0]) {
        return a[1] - b[1];
      }
      return b[0] - a[0];
    });
    int[][] res = new int[people.length][2];
    List<Integer> index = new ArrayList<>();
    for (int i = 0; i < people.length; i++) {
      index.add(i);
    }
    for (int i = 0; i < people.length; i++) {
      int temp = people[i][1];
      res[index.get(temp)] = people[i];
      index.remove(temp);
    }
    return res;
  }

  public final int[][] dir = new int[][] { new int[] { -1, 0 }, new int[] { 1, 0 }, new int[] { 0, -1 },
      new int[] { 0, 1 } };

  public int trapRainWater(int[][] heightMap) {
    int m = heightMap.length, n = heightMap[0].length, currentHeight = 1, res = 0, maxHeight = Integer.MIN_VALUE;
    int[][] currentGraph = new int[m][n];
    boolean hasNext = true;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        maxHeight = Math.max(maxHeight, heightMap[i][j]);
      }
    }
    while (hasNext) {
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          currentGraph[i][j] = 0;
          if (heightMap[i][j] >= currentHeight) {
            currentGraph[i][j] = 1;
          }
        }
      }
      res += rainWaterArea(currentGraph);
      currentHeight++;
      if (currentHeight > maxHeight) {
        break;
      }
    }
    return res;
  }

  public int rainWaterArea(int[][] graph) {
    int m = graph.length, n = graph[0].length;
    for (int i = 0; i < m; i++) {
      rainWaterMove(graph, i, 0);
      rainWaterMove(graph, i, n - 1);
    }
    for (int i = 0; i < n; i++) {
      rainWaterMove(graph, 0, i);
      rainWaterMove(graph, m - 1, i);
    }
    int res = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (graph[i][j] == 0) {
          res++;
        }
      }
    }
    return res;
  }

  public void rainWaterMove(int[][] graph, int x, int y) {
    int m = graph.length, n = graph[0].length;
    if (x < 0 || y < 0 || x >= m || y >= n) {
      return;
    }
    if (graph[x][y] == 1 || graph[x][y] == 2) {
      return;
    }
    graph[x][y] = 2;
    for (int[] next : dir) {
      int nextX = x + next[0];
      int nextY = y + next[1];
      rainWaterMove(graph, nextX, nextY);
    }
  }

  public int trapRainWaterII(int[][] heightMap) {
    int m = heightMap.length, n = heightMap[0].length, res = 0;
    boolean[][] visitor = new boolean[m][n];
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
          visitor[i][j] = true;
          pq.add(new int[] { i, j, heightMap[i][j] });
        }
      }
    }

    while (!pq.isEmpty()) {
      int[] current = pq.poll();
      for (int[] next : dir) {
        int nextX = current[0] + next[0];
        int nextY = current[1] + next[1];
        if (nextX < 0 || nextY < 0 || nextX >= m || nextY >= n || visitor[nextX][nextY]) {
          continue;
        }
        if (heightMap[nextX][nextY] < current[2]) {
          res += current[2] - heightMap[nextX][nextY];
        }
        pq.add(new int[] { nextX, nextY, Math.max(heightMap[nextX][nextY], current[2]) });
        visitor[nextX][nextY] = true;
      }
    }
    return res;
  }

  public boolean validWordAbbreviation(String word, String abbr) {
    char[] wc = word.toCharArray();
    char[] ac = abbr.toCharArray();
    int index = 0;
    for (int i = 0; i < ac.length; i++) {
      if (index >= wc.length) {
        return false;
      }
      if (Character.isLetter(ac[i])) {
        if (ac[i] != wc[index]) {
          return false;
        }
        index++;
      } else {
        int temp = 0;
        while (i < ac.length && Character.isDigit(ac[i])) {
          if (temp == 0 && ac[i] == '0') {
            return false;
          }
          temp = temp * 10 + (ac[i] - '0');
          i++;
        }
        if (i == ac.length) {
          return index + temp == wc.length;
        } else {
          i--;
          index += temp;
        }
      }
    }
    return index == wc.length;
  }

  public boolean validWordAbbreviationII(String word, String abbr) {
    char[] wc = word.toCharArray();
    char[] ac = abbr.toCharArray();
    int wi = 0, ai = 0;
    while (wi < wc.length && ai < ac.length) {
      if (Character.isLetter(ac[ai])) {
        if (ac[ai] != wc[wi]) {
          return false;
        }
        wi++;
        ai++;

      } else {
        int temp = 0;
        while (ai < ac.length && Character.isDigit(ac[ai])) {
          if (temp == 0 && ac[ai] == '0') {
            return false;
          }
          temp = temp * 10 + (ac[ai] - '0');
          ai++;
        }
        if (ai == ac.length) {
          return wi + temp == wc.length;
        } else {
          wi += temp;
        }
      }
    }
    return wi == wc.length && ai == ac.length;
  }

  public int longestPalindrome(String s) {
    char[] sc = s.toCharArray();
    int res = 0;
    int[] num = new int[128];
    for (char c : sc) {
      num[c]++;
    }
    for (int n : num) {
      res += n % 2;
    }
    return res == 0 ? sc.length : sc.length - res + 1;
  }

}

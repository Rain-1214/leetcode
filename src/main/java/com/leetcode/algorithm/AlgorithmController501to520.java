package com.leetcode.algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import com.leetcode.entity.TreeNode;
import com.leetcode.entity.BSTNodeWithParent.Node;

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

  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    int rowMax = maze.length;
    int colMax = maze[0].length;
    int[][] dp = new int[rowMax][colMax];
    for (int[] row : dp) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }
    dp[start[0]][start[1]] = 0;

    Queue<int[]> q = new LinkedList<>();
    q.add(start);

    while (!q.isEmpty()) {
      int[] current = q.poll();
      int row = current[0];
      int col = current[1];
      for (int i = 0; i < 4; i++) {
        int newRow = row;
        int newCol = col;
        int tempDis = 0;
        switch (i) {
          case 3:
            for (int j = newRow; j >= 0; j--) {
              if (maze[j][newCol] != 1) {
                newRow = j;
              } else {
                break;
              }
            }
            tempDis = Math.abs(newRow - row);
            break;
          case 2:
            for (int j = newCol; j < colMax; j++) {
              if (maze[newRow][j] != 1) {
                newCol = j;
              } else {
                break;
              }
            }
            tempDis = Math.abs(newCol - col);
            break;
          case 1:
            for (int j = newRow; j < rowMax; j++) {
              if (maze[j][newCol] != 1) {
                newRow = j;
              } else {
                break;
              }
            }
            tempDis = Math.abs(newRow - row);
            break;
          case 0:
            for (int j = newCol; j >= 0; j--) {
              if (maze[newRow][j] != 1) {
                newCol = j;
              } else {
                break;
              }
            }
            tempDis = Math.abs(newCol - col);
            break;
        }
        if (dp[newRow][newCol] > tempDis + dp[row][col]) {
          dp[newRow][newCol] = tempDis + dp[row][col];
          q.add(new int[] { newRow, newCol });
        }
      }
    }
    return dp[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dp[destination[0]][destination[1]];
  }

  public int shortestDistanceII(int[][] maze, int[] start, int[] destination) {
    int rowMax = maze.length;
    int colMax = maze[0].length;
    int[][] dp = new int[rowMax][colMax];
    shortestDistance(maze, start[0], start[1], destination, dp);
    return dp[destination[0]][destination[1]] == 0 ? -1 : dp[destination[0]][destination[1]];
  }

  public void shortestDistance(int[][] maze, int row, int col, int[] destination, int[][] dp) {
    int rowMax = maze.length;
    int colMax = maze[0].length;
    int currentStep = dp[row][col];
    for (int i = 0; i < 4; i++) {
      int newRow = row;
      int newCol = col;
      int tempDis = 0;
      switch (i) {
        case 3:
          for (int j = newRow; j >= 0; j--) {
            if (maze[j][newCol] != 1) {
              newRow = j;
            } else {
              break;
            }
          }
          tempDis = Math.abs(newRow - row);
          break;
        case 2:
          for (int j = newCol; j < colMax; j++) {
            if (maze[newRow][j] != 1) {
              newCol = j;
            } else {
              break;
            }
          }
          tempDis = Math.abs(newCol - col);
          break;
        case 1:
          for (int j = newRow; j < rowMax; j++) {
            if (maze[j][newCol] != 1) {
              newRow = j;
            } else {
              break;
            }
          }
          tempDis = Math.abs(newRow - row);
          break;
        case 0:
          for (int j = newCol; j >= 0; j--) {
            if (maze[newRow][j] != 1) {
              newCol = j;
            } else {
              break;
            }
          }
          tempDis = Math.abs(newCol - col);
          break;
      }
      if (newRow == row && newCol == col) {
        continue;
      }
      if (newRow == destination[0] && newCol == destination[1]
          && (dp[newRow][newCol] == 0 || dp[newRow][newCol] > tempDis + currentStep)) {
        dp[newRow][newCol] = tempDis + currentStep;
        continue;
      }
      if (dp[destination[0]][destination[1]] != 0 && dp[destination[0]][destination[1]] < tempDis + currentStep) {
        continue;
      }
      if (dp[newRow][newCol] == 0 || dp[newRow][newCol] > tempDis + currentStep) {
        dp[newRow][newCol] = tempDis + dp[row][col];
        shortestDistance(maze, newRow, newCol, destination, dp);
      }
    }
  }

  public String[] findRelativeRanks(int[] score) {
    int[] copy = Arrays.copyOf(score, score.length);
    Arrays.sort(copy);
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < copy.length; i++) {
      map.put(copy[i], score.length - i);
    }
    String[] res = new String[score.length];
    for (int i = 0; i < score.length; i++) {
      res[i] = map.get(score[i]) == 1 ? "Gold Medal"
          : map.get(score[i]) == 2 ? "Silver Medal"
              : map.get(score[i]) == 3
                  ? "Bronze Medal"
                  : Integer.toString(map.get(score[i]));
    }
    return res;
  }

  public String[] findRelativeRanksII(int[] score) {
    int max = score[0];
    for (int s : score) {
      max = Math.max(max, s);
    }
    int[] bucket = new int[max + 1];
    for (int i = 0; i < score.length; i++) {
      bucket[score[i]] = i + 1;
    }

    int index = 0;
    String[] res = new String[score.length];
    for (int i = bucket.length - 1; i >= 0; i--) {
      if (bucket[i] == 0) {
        continue;
      }
      if (index == 0) {
        res[bucket[i] - 1] = "Gold Medal";
      } else if (index == 1) {
        res[bucket[i] - 1] = "Silver Medal";
      } else if (index == 2) {
        res[bucket[i] - 1] = "Bronze Medal";
      } else {
        res[bucket[i] - 1] = Integer.toString(index + 1);
      }
      index++;
    }
    return res;
  }

  public boolean checkPerfectNumber(int num) {
    if (num == 1) {
      return false;
    }
    int sum = 1;
    for (int i = 2; i * i <= num; i++) {
      if (num % i == 0) {
        sum += i;
        if (i * i != num) {
          sum += num / i;
        }
      }
    }
    return sum == num;
  }

  public int[] findFrequentTreeSum(TreeNode root) {
    if (root == null) {
      return new int[0];
    }
    Map<Integer, Integer> map = new HashMap<>();
    findFrequentTreeSum(root, map);
    int max = 0;
    for (int i : map.values()) {
      max = Math.max(max, i);
    }
    List<Integer> res = new ArrayList<>();
    for (int i : map.keySet()) {
      if (map.get(i) == max) {
        res.add(i);
      }
    }
    int[] result = new int[res.size()];
    for (int i = 0; i < res.size(); i++) {
      result[i] = res.get(i);
    }
    return result;
  }

  public int findFrequentTreeSum(TreeNode root, Map<Integer, Integer> map) {
    if (root == null) {
      return 0;
    }
    int left = findFrequentTreeSum(root.left, map);
    int right = findFrequentTreeSum(root.right, map);
    int sum = left + right + root.val;
    map.put(sum, map.getOrDefault(sum, 0) + 1);
    return sum;
  }

  public int fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    return fib(n - 1) + fib(n - 2);
  }

  public int fibII(int n) {
    int[] temp = new int[100];
    temp[0] = 0;
    temp[1] = 1;
    for (int i = 2; i <= n; i++) {
      temp[i] = temp[i - 1] + temp[i - 2];
    }
    return temp[n];
  }

  public Node inorderSuccessor(Node node) {
    if (node.right == null) {
      if (node.parent == null) {
        return null;
      }
      if (node == node.parent.left) {
        return node.parent;
      }
      Node temp = node.parent;
      while (temp != null && temp.right == node) {
        node = temp;
        temp = temp.parent;
      }
      return temp;
    } else {
      Node temp = node.right;
      while (temp.left != null) {
        temp = temp.left;
      }
      return temp;
    }
  }

  public int findBottomLeftValueDeep = 0;
  public int findBottomLeftValueRes = 0;

  public int findBottomLeftValue(TreeNode root) {
    this.findBottomLeftValueDeep = 0;
    this.findBottomLeftValueRes = root.val;
    findBottomLeftValue(root, 0);
    return this.findBottomLeftValueRes;
  }

  public void findBottomLeftValue(TreeNode root, int deep) {
    if (root == null) {
      return;
    }
    findBottomLeftValue(root.left, deep + 1);
    if (deep > this.findBottomLeftValueDeep) {
      this.findBottomLeftValueDeep = deep;
      this.findBottomLeftValueRes = root.val;
    }
    findBottomLeftValue(root.right, deep + 1);
  }

  public int findRotateSteps(String ring, String key) {
    char[] ringChars = ring.toCharArray();
    char[] keyChars = key.toCharArray();
    int[][] dp = new int[keyChars.length][ringChars.length];
    Map<Character, ArrayList<Integer>> map = new HashMap<>();
    for (int i = 0; i < ringChars.length; i++) {
      if (map.containsKey(ringChars[i])) {
        map.get(ringChars[i]).add(i);
      } else {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(i);
        map.put(ringChars[i], list);
      }
    }
    char first = keyChars[0];
    for (int i : map.get(first)) {
      int temp = Math.abs(i - 0);
      int revers = ringChars.length - temp;
      dp[0][i] = Math.min(temp, revers) + 1;
    }
    for (int i = 1; i < keyChars.length; i++) {
      char cur = keyChars[i];
      ArrayList<Integer> list = map.get(cur);
      int[] prev = dp[i - 1];
      for (int k = 0; k < prev.length; k++) {
        if (prev[k] == 0) {
          continue;
        }
        for (int j : list) {
          int temp = Math.abs(j - k);
          int revers = ringChars.length - temp;
          if (dp[i][j] == 0) {
            dp[i][j] = Math.min(temp, revers) + 1 + prev[k];
          } else {
            dp[i][j] = Math.min(dp[i][j], Math.min(temp, revers) + 1 + prev[k]);
          }
        }
      }
    }
    int res = Integer.MAX_VALUE;
    for (int i : map.get(keyChars[keyChars.length - 1])) {
      res = Math.min(res, dp[keyChars.length - 1][i]);
    }
    return res;
  }

  public List<Integer> largestValues(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      int size = q.size();
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < size; i++) {
        TreeNode cur = q.poll();
        if (cur.left != null) {
          q.add(cur.left);
        }
        if (cur.right != null) {
          q.add(cur.right);
        }
        max = Math.max(max, cur.val);
      }
      res.add(max);
    }
    return res;
  }

  public int longestPalindromeSubseq(String s) {
    char[] sc = s.toCharArray();
    int[][] dp = new int[sc.length][sc.length];
    for (int i = 0; i < sc.length; i++) {
      dp[i][i] = 1;
    }
    for (int i = sc.length - 1; i >= 0; i--) {
      for (int j = i + 1; j < sc.length; j++) {
        if (sc[i] == sc[j]) {
          dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[0][sc.length - 1];
  }

  public int findMinMoves(int[] machines) {
    int tol = 0;
    for (int i : machines) {
      tol += i;
    }
    if (tol % machines.length != 0) {
      return -1;
    }
    int avg = tol / machines.length;
    int sum = 0, res = 0;
    for (int num : machines) {
      num = num - avg;
      sum += num;
      res = Math.max(res, Math.max(Math.abs(sum), num));
    }
    return res;
  }

  public int changeRes = 0;

  public int change(int amount, int[] coins) {
    if (amount == 0) {
      return 0;
    }
    Arrays.sort(coins);
    change(amount, coins, 0);
    return changeRes;
  }

  public void change(int amount, int[] coins, int index) {
    if (amount == 0) {
      changeRes++;
      return;
    }
    for (int i = index; i < coins.length; i++) {
      if (amount - coins[i] >= 0) {
        change(amount - coins[i], coins, i);
      }
    }
  }

  public int changeII(int amount, int[] coins) {
    if (amount == 0) {
      return 1;
    }
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    for (int coin : coins) {
      for (int i = coin; i <= amount; i++) {
        dp[i] += dp[i - coin];
      }
    }
    return dp[amount];
  }

  class Solution519 {

    public int[] list;
    public int m;
    public int n;
    public Random random = new Random();
    public int count = 0;

    public Solution519(int m, int n) {
      int max = m * n;
      this.list = new int[max];
      this.m = m;
      this.n = n;
      for (int i = 0; i < max; i++) {
        list[i] = i;
      }
    }

    public int[] flip() {
      int index = random.nextInt(list.length - count);
      int[] res = new int[2];
      int cur = list[index];
      res[0] = cur / n;
      res[1] = cur % n;
      list[index] = list[list.length - 1 - count];
      list[list.length - 1 - count] = cur;
      count++;
      return res;
    }

    public void reset() {
      this.count = 0;
    }
  }

  class Solution519II {

    public HashMap<Integer, Integer> map;
    public int m;
    public int n;
    public Random random = new Random();
    public int total = 0;

    public Solution519II(int m, int n) {
      this.map = new HashMap<>();
      this.m = m;
      this.n = n;
      this.total = m * n;
    }

    public int[] flip() {
      int x = random.nextInt(total);
      total--;
      int res = map.getOrDefault(x, x);
      map.put(x, map.getOrDefault(total, total));
      return new int[] { res / n, res % n };
    }

    public void reset() {
      map.clear();
      total = m * n;
    }
  }

  public boolean detectCapitalUse(String word) {
    char[] wc = word.toCharArray();
    boolean isFirstUpper = Character.isUpperCase(wc[0]);
    int count = 0;
    for (int i = 1; i < wc.length; i++) {
      if (Character.isUpperCase(wc[i])) {
        count++;
      }
    }
    if (isFirstUpper) {
      return count == 0 || count == wc.length - 1;
    } else {
      return count == 0;
    }
  }

  public int findLUSlength(String a, String b) {
    char[] ac = a.toCharArray();
    char[] bc = b.toCharArray();
    if (ac.length != bc.length) {
      return Math.max(ac.length, bc.length);
    }
    int i = 0;
    while (i < ac.length) {
      if (ac[i] != bc[i]) {
        return Math.max(ac.length, bc.length);
      }
      i++;
    }
    return -1;
  }

}

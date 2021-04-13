package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

  public int minCostII(int[][] costs) {
    int costLen = costs[0].length;
    if (costLen == 1) {
      return costs[0][0];
    }
    int[][] dp = new int[costs.length + 1][costLen];
    for (int i = 0; i < costs.length; i++) {
      int[] tempCost = costs[i];
      int[] tempDp = dp[i];
      int[] currentDp = dp[i + 1];
      for (int j = 0; j < tempCost.length; j++) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < tempDp.length; k++) {
          if (k == j) {
            continue;
          }
          min = Math.min(min, tempDp[k]);
        }
        currentDp[j] = min + tempCost[j];

      }
    }
    int min = dp[dp.length - 1][0];
    for (int i = 1; i < costLen; i++) {
      min = Math.min(min, dp[dp.length - 1][i]);
    }
    return min;
  }

  public boolean canPermutePalindrome(String s) {
    Map<Character, Integer> map = new HashMap<>();
    char[] sa = s.toCharArray();
    if (sa.length == 1) {
      return true;
    }
    for (int i = 0; i < sa.length; i++) {
      int temp = map.getOrDefault(sa[i], 0);
      map.put(sa[i], temp + 1);
    }
    int len = sa.length;
    int oddNum = 0;
    for (int i : map.values()) {
      if (i % 2 != 0) {
        oddNum++;
      }
    }
    return len % 2 == 0 ? oddNum == 0 : oddNum == 1;
  }

  public boolean canPermutePalindromeII(String s) {
    int[] map = new int[26];
    char[] sa = s.toCharArray();
    if (sa.length == 1) {
      return true;
    }
    for (int i = 0; i < sa.length; i++) {
      map[(int) sa[i] - 'a']++;
    }
    int len = s.length();
    int oddNum = 0;
    for (int i : map) {
      if (i % 2 != 0) {
        oddNum++;
      }
    }
    return len % 2 == 0 ? oddNum == 0 : oddNum == 1;
  }

  public List<String> generatePalindromes(String s) {
    int len = s.length();
    List<String> res = new ArrayList<>();
    if (len == 1) {
      res.add(s);
      return res;
    }
    int[] charNum = new int[26];
    for (char c : s.toCharArray()) {
      charNum[(int) c - 'a']++;
    }
    
    
    int oddNum = 0;
    int oddIndex = -1;
    for (int i = 0;i < charNum.length; i++) {
      if (charNum[i] % 2 != 0) {
        oddNum++;
        oddIndex = i;
      }
    }
    if ((len % 2 == 0 && oddNum != 0) || (len % 2 != 0 && oddNum != 1)) {
      return res;
    }
    // StringBuilder sb = new StringBuilder();
    // if (oddNum == 1) {
    //   charNum[oddIndex] -= 1;
    //   sb.append((char) ('a' + oddIndex));
    // }
    // generatePalindromes(charNum, sb, res);
    char[] sb = new char[len];
    if (oddNum == 1) {
      charNum[oddIndex] -= 1;
      sb[len / 2] = (char) ('a' + oddIndex);
    }
    generatePalindromes(charNum, sb, res, 0);
    return res;
  }

  public void generatePalindromes(int[] charNum, StringBuilder sb, List<String> res) {
    int count = 0;
    for (int i = 0; i < charNum.length; i++) {
      if (charNum[i] == 0) {
        count++;
        continue;
      }
      sb.append((char) ('a' + i));
      sb.insert(0, (char) ('a' + i));
      charNum[i] = charNum[i] - 2;
      generatePalindromes(charNum, sb, res);
      charNum[i] = charNum[i] + 2;
      sb.delete(0, 1);
      sb.delete(sb.length() - 1, sb.length());
    }
    if (count == 26) {
      res.add(sb.toString());
    }
  }

  public void generatePalindromes(int[] charNum, char[] sb, List<String> res, int index) {
    int count = 0;
    for (int i = 0; i < charNum.length; i++) {
      if (charNum[i] == 0) {
        count++;
        continue;
      }
      sb[index] = (char) ('a' + i);
      sb[sb.length - 1 - index] = sb[index];
      charNum[i] = charNum[i] - 2;
      generatePalindromes(charNum, sb, res, index + 1);
      charNum[i] = charNum[i] + 2;
    }
    if (count == 26) {
      res.add(new String(sb));
    }
  }

  public int missingNumber(int[] nums) {
    int allSum = 0;
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum+= nums[i];
      allSum += i;
    }
    return allSum + nums.length - sum;
  }
}

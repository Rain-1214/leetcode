package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.leetcode.entity.TreeNode;

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
    for (int i = 0; i < charNum.length; i++) {
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
    // charNum[oddIndex] -= 1;
    // sb.append((char) ('a' + oddIndex));
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
      sum += nums[i];
      allSum += i;
    }
    return allSum + nums.length - sum;
  }

  public String alienOrder(String[] words) {
    if (words.length == 1) {
      return words[0];
    }
    Map<Character, Set<Character>> map = new HashMap<>();
    for (int i = 0; i < words.length - 1; i++) {
      char[] csa = words[i].toCharArray();
      char[] nexts = words[i + 1].toCharArray();
      for (int j = 0; j < Math.max(csa.length, nexts.length); j++) {
        if (j >= csa.length) {
          break;
        }
        if (j >= nexts.length) {
          return "";
        }
        char c = csa[j];
        char nextsc = nexts[j];
        if (c == nextsc) {
          continue;
        }
        Set<Character> temp = map.getOrDefault(c, new HashSet<Character>());
        temp.add(nexts[j]);
        map.put(c, temp);
        break;
      }
    }
    int[] degrees = new int[26];
    Arrays.fill(degrees, -1);
    for (String s : words) {
      for (char sa : s.toCharArray()) {
        degrees[(int) (sa - 'a')] = 0;
      }
    }
    for (Set<Character> cs : map.values()) {
      for (char targetChar : cs) {
        degrees[(int) (targetChar - 'a')] += 1;
      }
    }
    Queue<Character> q = new LinkedList<>();
    int resLen = 0;
    for (int i = 0; i < degrees.length; i++) {
      if (degrees[i] != -1) {
        resLen++;
      }
      if (degrees[i] == 0) {
        q.add((char) (i + 'a'));
        degrees[i] -= 1;
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!q.isEmpty()) {
      Character current = q.poll();
      sb.append(current);
      if (map.containsKey(current)) {
        for (char targetChar : map.get(current)) {
          int index = targetChar - 'a';
          degrees[index] -= 1;
          if (degrees[index] == 0) {
            q.add(targetChar);
            degrees[index] = -1;
          }
        }
      }
    }
    if (sb.length() != resLen) {
      return "";
    }
    return sb.toString();
  }

  public int closestValue(TreeNode root, double target) {
    int res = root.val;
    while (root != null) {
      res = Math.abs(res - target) < Math.abs(root.val - target) ? res : root.val;
      root = target < root.val ? root.left : root.right;
    }
    return res;
  }

  public String hexString(int num) {
    String res = Integer.toHexString(num);
    if (res.length() == 1) {
      return "0" + res;
    }
    return res;
  }

  public String encode(List<String> strs) {
    StringBuilder sb = new StringBuilder();
    for (String s : strs) {
      sb.append(hexString(s.length()));
      sb.append(s);
    }
    return sb.toString();
  }

  public List<String> decode(String s) {
    List<String> res = new ArrayList<>();
    if (s.length() == 0) {
      return res;
    }
    for (int i = 0; i < s.length(); i += 2) {
      int len = Integer.parseInt(s.substring(i, i + 2), 16);
      res.add(s.substring(i + 2, i + 2 + len));
      i += len;
    }
    return res;
  }

  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    PriorityQueue<Double[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1] > 0 ? 1 : -1);
    closestKValues(root, target, pq);
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      res.add(pq.poll()[0].intValue());
    }
    return res;
  }

  public void closestKValues(TreeNode root, double target, PriorityQueue<Double[]> pq) {
    if (root == null) {
      return;
    }
    Double[] temp = new Double[] { (double) root.val, Math.abs(root.val - target) };
    pq.add(temp);
    closestKValues(root.left, target, pq);
    closestKValues(root.right, target, pq);
  }

  public List<Integer> closestKValuesII(TreeNode root, double target, int k) {
    LinkedList<Integer> res = new LinkedList<>();
    if (root == null || k <= 0) {
      return res;
    }
    closestKValuesIIInorder(root, target, k, res);
    return res;
  }

  public void closestKValuesIIInorder(TreeNode root, double target, int k, LinkedList<Integer> q) {
    if (root == null) {
      return;
    }
    closestKValuesIIInorder(root.left, target, k, q);
    if (q.size() == k) {
      if (Math.abs(root.val - target) < Math.abs(q.peek() - target)) {
        q.poll();
      } else {
        return;
      }
    }
    q.add(root.val);
    closestKValuesIIInorder(root.right, target, k, q);
  }

}

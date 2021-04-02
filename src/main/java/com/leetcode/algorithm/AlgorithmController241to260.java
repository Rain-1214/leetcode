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

public class AlgorithmController241to260 {
  public List<Integer> diffWaysToCompute(String input) {
    return diffWaysToCompute(input.toCharArray(), 0, input.length() - 1);
  }

  public List<Integer> diffWaysToCompute(char[] input, int l, int r) {
    List<Integer> res = new ArrayList<>();
    int temp = 0;
    int i = l;
    for (; i <= r; i++) {
      if (Character.isDigit(input[i])) {
        temp = temp * 10 + (input[i] - '0');
      } else {
        break;
      }
    }
    if (i == r + 1) {
      res.add(temp);
      return res;
    }

    for (; i <= r; i++) {
      if (Character.isDigit(input[i])) {
        continue;
      }
      List<Integer> left = diffWaysToCompute(input, l, i - 1);
      List<Integer> right = diffWaysToCompute(input, i + 1, r);
      for (int lVal : left) {
        for (int rVal : right) {
          switch (input[i]) {
          case '+':
            res.add(lVal + rVal);
            break;
          case '-':
            res.add(lVal - rVal);
            break;
          default:
            res.add(lVal * rVal);
          }
        }
      }
    }
    return res;
  }

  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] table = new int[26];
    for (int i = 0; i < s.length(); i++) {
      table[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < s.length(); i++) {
      table[t.charAt(i) - 'a']--;
      if (table[t.charAt(i) - 'a'] < 0) {
        return false;
      }
    }
    return true;
  }

  public boolean isAnagramII(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] table = new int[26];
    for (int c : s.toCharArray()) {
      table[c - 'a']++;
    }
    for (int c : t.toCharArray()) {
      table[c - 'a']--;
      if (table[c - 'a'] < 0) {
        return false;
      }
    }
    return true;
  }

  public int shortestDistance(String[] wordsDict, String word1, String word2) {
    int distance = Integer.MAX_VALUE;
    int i1 = -1;
    int i2 = -1;
    for (int i = 0; i < wordsDict.length; i++) {
      String t = wordsDict[i];
      if (t.equals(word1)) {
        i1 = i;
      } else if (t.equals(word2)) {
        i2 = i;
      }
      if (i1 != -1 && i2 != -1) {
        distance = Math.min(Math.abs(i1 - i2), distance);
      }
    }
    return distance;
  }

  class WordDistance {

    public Map<String, Map<String, Integer>> cache = new HashMap<>();
    public String[] table;

    public WordDistance(String[] wordsDict) {
      this.table = wordsDict;
    }

    public int shortest(String word1, String word2) {
      Map<String, Integer> temp = this.cache.getOrDefault(word1, new HashMap<>());
      if (temp.containsKey(word2)) {
        return temp.get(word2);
      }
      int min = this.table.length;
      for (int i = 0; i < this.table.length; i++) {
        if (this.table[i].equals(word1)) {
          int j = 1;
          while (i - j >= 0 || i + j < this.table.length) {
            if (i - j >= 0 && this.table[i - j].equals(word2)
                || i + j < this.table.length && this.table[i + j].equals(word2)) {
              break;
            }
            j++;
          }
          min = Math.min(min, j);
        }
      }
      temp.put(word2, min);
      this.cache.put(word1, temp);
      Map<String, Integer> temp2 = this.cache.getOrDefault(word2, new HashMap<>());
      temp2.put(word1, min);
      this.cache.put(word2, temp2);
      return min;
    }
  }

  class WordDistanceII {

    public Map<String, ArrayList<Integer>> cache = new HashMap<>();

    public WordDistanceII(String[] wordsDict) {
      for (int i = 0; i < wordsDict.length; i++) {
        this.cache.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
      }
    }

    public int shortest(String word1, String word2) {
      int min = Integer.MAX_VALUE;
      int i1 = 0, i2 = 0;
      ArrayList<Integer> l1 = this.cache.get(word1);
      ArrayList<Integer> l2 = this.cache.get(word2);
      while (i1 < l1.size() && i2 < l2.size()) {
        min = Math.min(min, Math.abs(l1.get(i1) - l2.get(i2)));
        if (l1.get(i1) < l2.get(i2)) {
          i1++;
        } else {
          i2++;
        }
      }
      return min;
    }
  }

  public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
    int min = wordsDict.length;
    for (int i = 0; i < wordsDict.length; i++) {
      if (wordsDict[i].equals(word1)) {
        int j = 1;
        while (i - j >= 0 || i + j < wordsDict.length) {
          if (i - j >= 0 && wordsDict[i - j].equals(word2)
              || i + j < wordsDict.length && wordsDict[i + j].equals(word2)) {
            break;
          }
          j++;
        }
        min = Math.min(min, j);
      }
    }
    return min;
  }

  public boolean isStrobogrammatic(String num) {
    int len = num.length();
    int left = len / 2;
    int right = len / 2;
    if (len % 2 == 0) {
      left = len == 1 ? 0 : right - 1;
    }
    char[] c = num.toCharArray();
    Set<Character> unable = new HashSet<>();
    unable.add('2');
    unable.add('3');
    unable.add('4');
    unable.add('5');
    unable.add('7');
    while (left >= 0 && right < len) {
      if (unable.contains(c[right])) {
        return false;
      }
      if (c[right] == '0' || c[right] == '1' || c[right] == '8') {
        if (c[right] != c[left]) {
          return false;
        }
      }
      if (c[right] == '6' && c[left] != '9' || c[right] == '9' && c[left] != '6') {
        return false;
      }
      left--;
      right++;
    }
    return true;
  }

  public List<String> findStrobogrammatic(int n) {
    return findStrobogrammaticHelper(n, n);
  }

  public List<String> findStrobogrammaticHelper(int n, int m) {
    List<String> res = new ArrayList<>();
    if (m == 0) {
      res.add("");
      return res;
    }
    if (m == 1) {
      res.add("1");
      res.add("8");
      res.add("0");
      return res;
    }
    List<String> temp = findStrobogrammaticHelper(n, m - 2);
    for (String s : temp) {
      if (m != n) {
        res.add("0" + s + "0");
      }
      res.add("1" + s + "1");
      res.add("8" + s + "8");
      res.add("6" + s + "9");
      res.add("9" + s + "6");
    }
    return res;
  }

  public final char[][] strobogrammaticMap = new char[][] { { '0', '0' }, { '1', '1' }, { '6', '9' }, { '8', '8' },
      { '9', '6' } };

  public List<String> findStrobogrammaticII(int n) {
    List<String> res = new ArrayList<>();
    if (n < 1) {
      return res;
    }
    char[] current = new char[n];
    findStrobogrammaticII(0, n - 1, current, res);
    return res;
  }

  public void findStrobogrammaticII(int l, int r, char[] current, List<String> res) {
    if (l > r) {
      if (current.length == 1 || current[0] != '0') {
        res.add(String.valueOf(current));
      }
      return;
    }
    for (char[] chars : this.strobogrammaticMap) {
      if (l == r && chars[0] != chars[1]) {
        continue;
      }
      current[l] = chars[0];
      current[r] = chars[1];
      findStrobogrammaticII(l + 1, r - 1, current, res);
    }
  }

  public int strobogrammaticNum = 0;

  public int strobogrammaticInRange(String low, String high) {
    int res = 0;
    for (int i = low.length(); i <= high.length(); i++) {
      strobogrammaticNum = 0;
      char[] chars = new char[i];
      findStrobogrammaticIII(0, i - 1, chars, low, high);
      res += strobogrammaticNum;
    }
    return res;
  }

  public void findStrobogrammaticIII(int l, int r, char[] current, String low, String high) {
    if (l > r) {
      if (current.length == 1 || current[0] != '0') {
        String temp = String.valueOf(current);
        if (comparatorStringIsBigger(temp, low) && comparatorStringIsBigger(high, temp)) {
          strobogrammaticNum += 1;
        }
      }
      return;
    }
    for (char[] chars : this.strobogrammaticMap) {
      if (l == r && chars[0] != chars[1]) {
        continue;
      }
      current[l] = chars[0];
      current[r] = chars[1];
      findStrobogrammaticIII(l + 1, r - 1, current, low, high);
    }
  }

  public boolean comparatorStringIsBigger(String s1, String s2) {
    if (s1.length() == s2.length()) {
      return s1.compareTo(s2) >= 0;
    }
    return s1.length() > s2.length();
  }

  public List<List<String>> groupStrings(String[] strings) {
    Map<String, ArrayList<String>> table = new HashMap<>();
    for (String s : strings) {
      StringBuilder sb = new StringBuilder();
      if (s.length() == 1) {
        ArrayList<String> temp = table.getOrDefault("-1", new ArrayList<>());
        temp.add(s);
        table.put("-1", temp);
        continue;
      }
      char[] t = s.toCharArray();
      for (int i = 1; i < t.length; i++) {
        int sub = t[i] - t[i - 1];
        if (sub < 0) {
          sub += 26;
        }
        sb.append(sub);
      }
      String key = sb.toString();
      ArrayList<String> temp = table.getOrDefault(key, new ArrayList<>());
      temp.add(s);
      table.put(sb.toString(), temp);
    }
    return new ArrayList<List<String>>(table.values());
  }

  public int countUnivalSubtreesRes = 0;

  public int countUnivalSubtrees(TreeNode root) {
    countUnivalSubtreesHelper(root);
    return countUnivalSubtreesRes;
  }

  public boolean countUnivalSubtreesHelper(TreeNode root) {
    if (root == null) {
      return true;
    }
    boolean left = countUnivalSubtreesHelper(root.left);
    boolean right = countUnivalSubtreesHelper(root.right);
    if (!left || !right) {
      return false;
    }
    if (root.left == null && root.right == null) {
      countUnivalSubtreesRes += 1;
      return true;
    }
    if (root.left == null) {
      if (root.val == root.right.val) {
        countUnivalSubtreesRes += 1;
        return true;
      }
      return false;
    }
    if (root.right == null) {
      if (root.val == root.left.val) {
        countUnivalSubtreesRes += 1;
        return true;
      }
      return false;
    }
    if (root.val == root.left.val && root.val == root.right.val) {
      countUnivalSubtreesRes += 1;
      return true;
    }
    return false;
  }

  class Vector2D {

    Queue<Integer> q = new LinkedList<>();

    public Vector2D(int[][] vec) {
      for (int i = 0; i < vec.length; i++) {
        for (int j = 0; j < vec[i].length; j++) {
          q.add(vec[i][j]);
        }
      }
    }

    public int next() {
      return q.poll();
    }

    public boolean hasNext() {
      return !q.isEmpty();
    }
  }

  class Vector2DII {

    int[][] vec;
    int innerIndex = 0;
    int outIndex = 0;

    public Vector2DII(int[][] vec) {
      this.vec = vec;
    }

    public int next() {
      if (innerIndex >= vec[outIndex].length) {
        innerIndex = 0;
        outIndex++;
        return this.next();
      }
      return vec[outIndex][innerIndex++];
    }

    public boolean hasNext() {
      if (outIndex < vec.length && innerIndex >= vec[outIndex].length) {
        innerIndex = 0;
        outIndex++;
        return hasNext();
      }
      return outIndex < vec.length;
    }
  }

  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, (int[] a, int[] b) -> {
      return a[0] - b[0];
    });
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] < intervals[i - 1][1]) {
        return false;
      }
    }
    return true;
  }

  public int minMeetingRooms(int[][] intervals) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(intervals.length, (a, b) -> a - b);
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    pq.add(intervals[0][1]);
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] >= pq.peek()) {
        pq.poll();
      }
      pq.add(intervals[i][1]);
    }
    return pq.size();
  }

  public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> res = new ArrayList<>();
    if (n == 1) {
      return res;
    }
    getFactors(n, new ArrayList<>(), res, (int) Math.sqrt(n));
    return res;
  }

  public void getFactors(int n, List<Integer> currentList, List<List<Integer>> res, int max) {
    if (n == 1) {
      List<Integer> temp = new ArrayList<>(currentList);
      res.add(temp);
      return;
    }
    int start = currentList.isEmpty() ? 2 : currentList.get(currentList.size() - 1);
    for (int i = start; i <= max; i++) {
      if (n % i == 0) {
        currentList.add(i);
        getFactors(n / i, currentList, res, max);
        currentList.remove(currentList.size() - 1);
      }
    }
  }

  public List<List<Integer>> getFactorsII(int n) {
    return getFactorsII(2, n);
  }

  public List<List<Integer>> getFactorsII(int start, int end) {
    List<List<Integer>> res = new ArrayList<>();
    if (end == 1) {
      return res;
    }
    int max = (int) Math.sqrt(end);
    for (int i = start; i <= max; i++) {
      if (end % i == 0) {
        List<Integer> temp = new ArrayList<>();
        temp.add(i);
        temp.add(end / i);
        res.add(temp);
        List<List<Integer>> next = getFactorsII(i, end / i);
        for (List<Integer> l : next) {
          l.add(i);
          res.add(l);
        }
      }
    }
    return res;

  }

  public boolean verifyPreorder(int[] preorder) {
    if (preorder.length < 3) {
      return true;
    }
    return verifyPreorder(preorder, 0, preorder.length - 1);
  }

  public boolean verifyPreorder(int[] preorder, int start, int end) {
    if (start >= end) {
      return true;
    }
    int middle = -1;
    for (int i = start + 1; i <= end; i++) {
      if (preorder[i] > preorder[start] && middle == -1) {
        middle = i;
        continue;
      }
      if (middle != -1 && preorder[i] < preorder[start]) {
        return false;
      }
    }
    if (middle == -1) {
      return verifyPreorder(preorder, start + 1, end);
    }
    return verifyPreorder(preorder, start + 1, middle - 1) && verifyPreorder(preorder, middle, end);
  }

  public boolean verifyPreorderII(int[] preorder) {
    int[] stack = new int[preorder.length];
    int index = -1;
    int min = Integer.MIN_VALUE;
    for (int num : preorder) {
      if (num < min) {
        return false;
      }
      while (index > -1 && num > stack[index]) {
        min = stack[index--];
      }
      stack[++index] = num;
    }
    return true;
  }

  public int minCost(int[][] costs) {
    int[][] dp = new int[costs.length + 1][3];
    dp[0] = new int[] { 0, 0, 0 };
    for (int i = 0; i < costs.length; i++) {
      int[] temp = new int[3];
      temp[0] = Math.min(dp[i][1], dp[i][2]) + costs[i][0];
      temp[1] = Math.min(dp[i][0], dp[i][2]) + costs[i][1];
      temp[2] = Math.min(dp[i][0], dp[i][1]) + costs[i][2];
      dp[i + 1] = temp;
    }
    return Math.min(Math.min(dp[costs.length][1], dp[costs.length][2]), dp[costs.length][0]);
  }

  public int minCostII(int[][] costs) {
    int dp0 = 0, dp1 = 0, dp2 = 0;
    for (int i = 0; i < costs.length; i++) {
      int temp0 = Math.min(dp1, dp2) + costs[i][0];
      int temp1 = Math.min(dp0, dp2) + costs[i][1];
      int temp2 = Math.min(dp0, dp1) + costs[i][2];
      dp0 = temp0;
      dp1 = temp1;
      dp2 = temp2;
    }
    return Math.min(Math.min(dp0, dp1), dp2);
  }

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> res = new ArrayList<>();
    binaryTreePaths(root, res, "");
    return res;
  }

  public void binaryTreePaths(TreeNode root, List<String> res, String s) {
    if (root == null) {
      return;
    }
    StringBuilder tempSb = new StringBuilder(s);
    tempSb.append(Integer.toString(root.val));
    if (root.left == null && root.right == null) {
      res.add(tempSb.toString());
    } else {
      tempSb.append("->");
      binaryTreePaths(root.left, res, tempSb.toString());
      binaryTreePaths(root.right, res, tempSb.toString());
    }
  }

  public int addDigits(int num) {
    if (num < 10) {
      return num;
    }
    int res = 0;
    while (num > 0) {
      res += num % 10;
      num /= 10;
    }
    return res < 10 ? res : addDigits(res);
  }

}

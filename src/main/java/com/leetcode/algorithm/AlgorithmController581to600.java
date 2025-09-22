package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class AlgorithmController581to600 {

  public int findUnsortedSubarray(int[] nums) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int right = -1;
    int left = -1;
    for (int i = 0; i < nums.length; i++) {
      int left_item = nums[i];
      if (left_item >= max) {
        max = left_item;
      } else {
        right = i;
      }
      int right_item = nums[nums.length - 1 - i];
      if (right_item <= min) {
        min = right_item;
      } else {
        left = nums.length - 1 - i;
      }
    }
    return right == -1 ? 0 : right - left + 1;
  }

  public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < pid.size(); i++) {
      map.put(pid.get(i), new ArrayList<>());
    }
    for (int i = 0; i < ppid.size(); i++) {
      int parentId = ppid.get(i);
      if (parentId == 0) {
        continue;
      }
      map.get(parentId).add(pid.get(i));
    }
    List<Integer> result = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.add(kill);
    while (!queue.isEmpty()) {
      int current = queue.poll();
      result.add(current);
      List<Integer> children = map.get(current);
      queue.addAll(children);
    }
    return result;
  }

  public int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= n; j++) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        char c1 = word1.charAt(i - 1);
        char c2 = word2.charAt(j - 1);
        if (c1 == c2) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
        }
      }
    }
    return dp[m][n];
  }

  public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        char c1 = text1.charAt(i - 1);
        char c2 = text2.charAt(j - 1);
        if (c1 == c2) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[m][n];
  }

  public int[][] outerTrees(int[][] trees) {
    int n = trees.length;
    if (n < 4) {
      return trees;
    }
    int leftMost = 0;
    for (int i = 0; i < n; i++) {
      if (trees[i][0] < trees[leftMost][0] || (trees[i][0] == trees[leftMost][0] && trees[i][1] < trees[leftMost][1])) {
        leftMost = i;
      }
    }
    int p = leftMost;
    List<int[]> res = new ArrayList<>();
    boolean[] used = new boolean[n];
    do {
      int q = (p + 1) % n;
      for (int r = 0; r < n; r++) {
        if (cross(trees[p], trees[r], trees[q]) < 0) {
          q = r;
        }
      }
      for (int i = 0; i < n; i++) {
        if (used[i] || i == p || i == q) {
          continue;
        }
        if (cross(trees[p], trees[i], trees[q]) == 0) {
          res.add(trees[i]);
          used[i] = true;
        }
      }
      if (!used[q]) {
        res.add(trees[q]);
        used[q] = true;
      }
      p = q;
    } while (p != leftMost);
    return res.toArray(new int[res.size()][]);
  }

  public int cross(int[] p1, int[] p2, int[] p3) {
    return (p2[0] - p1[0]) * (p3[1] - p1[1]) - (p2[1] - p1[1]) * (p3[0] - p1[0]);
  }

  public class FileSystem {

    private File root;

    public class File {
      public String name;
      public boolean isDirectory;
      public String content;
      public Map<String, File> children;

      public File(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.children = new HashMap<>();
        this.content = "";
      }
    }

    public FileSystem() {
      this.root = new File("/", true);
    }

    public List<String> ls(String path) {
      File current = this.root;
      String[] paths = path.split("/");
      for (int i = 1; i < paths.length; i++) {
        String name = paths[i];
        current = current.children.get(name);
        if (current == null) {
          return new ArrayList<>();
        }
      }
      if (current.isDirectory) {
        return new ArrayList<>(current.children.keySet()).stream().sorted().collect(Collectors.toList());
      }
      return new ArrayList<>(Arrays.asList(current.name));
    }

    public void mkdir(String path) {
      File current = this.root;
      String[] paths = path.split("/");
      for (int i = 1; i < paths.length; i++) {
        String name = paths[i];
        if (!current.children.containsKey(name)) {
          current.children.put(name, new File(name, true));
        }
        current = current.children.get(name);
      }
    }

    public void addContentToFile(String filePath, String content) {
      File current = this.root;
      String[] paths = filePath.split("/");
      for (int i = 1; i < paths.length; i++) {
        String name = paths[i];
        if (!current.children.containsKey(name)) {
          current.children.put(name, new File(name, false));
        }
        current = current.children.get(name);
      }
      current.content += content;
    }

    public String readContentFromFile(String filePath) {
      File current = this.root;
      String[] paths = filePath.split("/");
      for (int i = 1; i < paths.length; i++) {
        String name = paths[i];
        current = current.children.get(name);
      }
      return current.content;
    }
  }

  class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  };

  public List<Integer> preorder(Node root) {
    Stack<Node> q = new Stack<>();
    if (root == null) {
      return new ArrayList<>();
    }
    q.add(root);
    List<Integer> res = new ArrayList<>();
    while (!q.isEmpty()) {
      Node current = q.pop();
      res.add(current.val);
      for (int i = current.children.size() - 1; i >= 0; i--) {
        q.add(current.children.get(i));
      }
    }
    return res;
  }

  public List<Integer> preorder2(Node root) {
    List<Integer> res = new ArrayList<>();
    preorderHelper(root, res);
    return res;
  }

  public void preorderHelper(Node root, List<Integer> res) {
    if (root == null) {
      return;
    }
    res.add(root.val);
    for (Node node : root.children) {
      preorderHelper(node, res);
    }
  }

  public List<Integer> postorder(Node root) {
    List<Integer> res = new ArrayList<>();
    postorderHelper(root, res);
    return res;
  }

  public void postorderHelper(Node root, List<Integer> res) {
    if (root == null) {
      return;
    }
    for (Node node : root.children) {
      postorderHelper(node, res);
    }
    res.add(root.val);
  }

  public boolean isValid(String code) {
    char[] codeChar = code.toCharArray();
    if (codeChar[0] != '<' || codeChar[codeChar.length - 1] != '>') {
      return false;
    }
    Stack<String> stack = new Stack<>();
    boolean isStart = true;
    boolean hasSuffix = false;
    for (int i = 0; i < codeChar.length; i++) {
      char c = codeChar[i];
      if (c == '<') {
        if (isCdata(codeChar, i)) {
          int nextCdata = findNextCdata(codeChar, i);
          if (nextCdata == -1) {
            return false;
          }
          i = nextCdata;
          if (stack.isEmpty()) {
            return false;
          }
          hasSuffix = false;
          continue;
        }
        int nextCloseFlag = findNextCloseFlag(codeChar, i);
        if (nextCloseFlag == -1) {
          return false;
        }
        int startIndex = i + 1;
        boolean isClose = false;
        if (startIndex < codeChar.length && codeChar[startIndex] == '/') {
          startIndex++;
          isClose = true;
        }
        String tag = code.substring(startIndex, nextCloseFlag);
        if (!isValidTag(tag)) {
          return false;
        }
        i = nextCloseFlag;
        if (isClose) {
          if (stack.isEmpty() || !stack.pop().equals(tag)) {
            return false;
          }
        } else {
          if (isStart) {
            isStart = false;
          } else {
            if (stack.isEmpty()) {
              return false;
            }
          }
          stack.add(tag);
        }
        hasSuffix = false;
      } else {
        hasSuffix = true;
      }
    }
    if (hasSuffix) {
      return false;
    }
    return stack.isEmpty();
  }

  public boolean isValidTag(String tag) {
    if (tag.length() < 1 || tag.length() > 9) {
      return false;
    }
    for (int i = 0; i < tag.length(); i++) {
      if (!Character.isUpperCase(tag.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public boolean isCdata(char[] codeChar, int index) {
    char[] cdata = new char[] { '<', '!', '[', 'C', 'D', 'A', 'T', 'A', '[' };
    for (int i = 0; i < cdata.length; i++) {
      if (codeChar[index + i] != cdata[i]) {
        return false;
      }
    }
    return true;
  }

  public int findNextCdata(char[] codeChar, int index) {
    for (int i = index; i < codeChar.length - 2; i++) {
      if (codeChar[i] == ']' && codeChar[i + 1] == ']' && codeChar[i + 2] == '>') {
        return i + 2;
      }
    }
    return -1;
  }

  public int findNextCloseFlag(char[] codeChar, int index) {
    for (int i = index; i < codeChar.length; i++) {
      if (codeChar[i] == '>') {
        return i;
      }
    }
    return -1;
  }

  public int gcd(int a, int b) {
    if (b == 0) {
      return a; // 余数为0时，a就是最大公约数
    }
    return gcd(b, a % b);
  }

  public String fractionAddition(String expression) {
    char[] expressionChar = expression.toCharArray();
    int[] num = getNumerator(expressionChar, 0);
    int index = num[2];
    while (index < expressionChar.length) {
      int[] num1 = getNumerator(expressionChar, index);
      index = num1[2];
      if (num1[1] == num[1]) {
        num[0] = num1[0] + num[0];
        num[1] = num1[1];
      } else {
        num[0] = num1[0] * num[1] + num[0] * num1[1];
        num[1] = num1[1] * num[1];
      }
    }
    if (num[0] == 0 || num[1] == 0) {
      return "0/1";
    }
    int isNegative = num[0] < 0 ? -1 : 1;
    num[0] = Math.abs(num[0]);
    num[1] = Math.abs(num[1]);
    int gcd = gcd(num[0], num[1]);
    num[0] /= gcd;
    num[1] /= gcd;
    return isNegative * num[0] + "/" + num[1];
  }

  public int[] getNumerator(char[] expressionChar, int index) {
    if (index >= expressionChar.length) {
      return null;
    }
    int[] res = new int[3];
    int startIndex = index;
    boolean isNegative = false;
    int temp = 0;
    while (index < expressionChar.length) {
      if (expressionChar[index] == '-' || expressionChar[index] == '+') {
        if (index == startIndex) {
          isNegative = expressionChar[index] == '-';
          index++;
          continue;
        } else {
          break;
        }
      }
      if (expressionChar[index] == '/') {
        res[0] = temp;
        temp = 0;
        index++;
        continue;
      }
      temp = temp * 10 + (expressionChar[index] - '0');
      index++;
    }
    res[0] = isNegative ? 0 - res[0] : res[0];
    res[1] = temp;
    res[2] = index;
    return res;
  }

  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    int d1 = getDistance(p1, p2);
    int d2 = getDistance(p1, p3);
    int d3 = getDistance(p1, p4);
    int d4 = getDistance(p2, p3);
    int d5 = getDistance(p2, p4);
    int d6 = getDistance(p3, p4);
    Map<Integer, Integer> map = new HashMap<>();
    map.put(d1, map.getOrDefault(d1, 0) + 1);
    map.put(d2, map.getOrDefault(d2, 0) + 1);
    map.put(d3, map.getOrDefault(d3, 0) + 1);
    map.put(d4, map.getOrDefault(d4, 0) + 1);
    map.put(d5, map.getOrDefault(d5, 0) + 1);
    map.put(d6, map.getOrDefault(d6, 0) + 1);
    if (map.size() == 2) {
      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        if (entry.getKey() == 0) {
          return false;
        }
        if (entry.getValue() != 2 && entry.getValue() != 4) {
          return false;
        }
      }
    } else {
      return false;
    }
    return true;
  }

  public int getDistance(int[] p1, int[] p2) {
    return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
  }

  public int len = -1;

  public boolean validSquare2(int[] p1, int[] p2, int[] p3, int[] p4) {
    return validSquareHelp(p1, p2, p3) && validSquareHelp(p1, p2, p4) && validSquareHelp(p1, p3, p4)
        && validSquareHelp(p2, p3, p4);
  }

  public boolean validSquareHelp(int[] p1, int[] p2, int[] p3) {
    int d1 = getDistance(p1, p2);
    int d2 = getDistance(p1, p3);
    int d3 = getDistance(p2, p3);
    boolean isRight = d1 + d2 == d3 || d1 + d3 == d2 || d2 + d3 == d1;

    if (!isRight) {
      return false;
    }

    if (len == -1) {
      len = Math.min(d1, d2);
    }

    if (d1 == 0 || d2 == 0 || d3 == 0 || len != Math.min(d1, d2)) {
      return false;
    }

    return true;
  }

  public int findLHS(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      set.add(n);
    }
    int res = 0;
    for (int n : nums) {
      map.put(n, map.getOrDefault(n, 0) + 1);
      map.put(n - 1, map.getOrDefault(n - 1, 0) + 1);
      if (set.contains(n + 1)) {
        res = Math.max(res, map.get(n));
      }
      if (set.contains(n - 1)) {
        res = Math.max(res, map.get(n - 1));
      }
    }
    return res;
  }

  public int maxCount(int m, int n, int[][] ops) {
    int minM = m;
    int minN = n;
    for (int[] op : ops) {
      minM = Math.min(minM, op[0]);
      minN = Math.min(minN, op[1]);
    }
    return minM * minN;
  }

  public String[] findRestaurant(String[] list1, String[] list2) {
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < list1.length; i++) {
      map.put(list1[i], i);
    }
    int minIndex = Integer.MAX_VALUE;
    List<String> res = new ArrayList<>();
    for (int i = 0; i < list2.length; i++) {
      String s = list2[i];
      if (map.containsKey(s)) {
        int index = map.get(s) + i;
        if (index < minIndex) {
          minIndex = index;
          res.clear();
          res.add(s);
        } else if (index == minIndex) {
          res.add(s);
        }
      }
    }
    return res.toArray(new String[res.size()]);
  }

  public int findIntegers(int n) {
    int[] dp = new int[31];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i < 31; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    int pre = 0, res = 0;
    for (int i = 29; i >= 0; i--) {
      int bid = 1 << i;
      if ((n & bid) != 0) {
        res += dp[i + 1];
        if (pre == 1) {
          break;
        }
        pre = 1;
      } else {
        pre = 0;
      }

      if (i == 0) {
        res++;
      }
    }
    return res;
  }

}

package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

}

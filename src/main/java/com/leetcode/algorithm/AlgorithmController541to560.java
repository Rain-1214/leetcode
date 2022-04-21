package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leetcode.entity.TreeNode;

public class AlgorithmController541to560 {

  public String reverseStr(String s, int k) {
    char[] sc = s.toCharArray();
    for (int i = 0; i < sc.length; i += 2 * k) {
      int end = Math.min(i + k - 1, sc.length - 1);
      reverse(sc, i, end);
    }
    return String.valueOf(sc);
  }

  public char[] reverse(char[] sc, int left, int right) {
    while (left < right) {
      char temp = sc[left];
      sc[left] = sc[right];
      sc[right] = temp;
      left++;
      right--;
    }
    return sc;
  }

  public int[][] updateMatrix(int[][] mat) {
    int rowMax = mat.length, colMax = mat[0].length;
    int[][] dp = new int[rowMax][colMax];
    int flag = (int) Math.pow(10, 7);
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (i == 0 && j == 0) {
          dp[i][j] = mat[i][j] == 0 ? 0 : flag;
          continue;
        }
        if (mat[i][j] == 0) {
          dp[i][j] = 0;
          continue;
        }
        if (i == 0) {
          dp[i][j] = dp[i][j - 1] + 1;
          continue;
        }
        if (j == 0) {
          dp[i][j] = dp[i - 1][j] + 1;
          continue;
        }
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
      }
    }
    for (int i = rowMax - 1; i >= 0; i--) {
      for (int j = colMax - 1; j >= 0; j--) {
        if (i == rowMax - 1 && j == colMax - 1) {
          continue;
        }
        if (mat[i][j] == 0) {
          continue;
        }
        if (i == rowMax - 1) {
          dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
          continue;
        }
        if (j == colMax - 1) {
          dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
          continue;
        }
        dp[i][j] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1]) + 1);
      }
    }
    return dp;
  }

  public int diameterOfBinaryTreeRes = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    return Math.max(diameterOfBinaryTreeHelp(root) - 1, diameterOfBinaryTreeRes);
  }

  public int diameterOfBinaryTreeHelp(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = diameterOfBinaryTreeHelp(root.left);
    int right = diameterOfBinaryTreeHelp(root.right);
    diameterOfBinaryTreeRes = Math.max(diameterOfBinaryTreeRes, left + right);
    return Math.max(left, right) + 1;
  }

  public String findContestMatch(int n) {
    String[] teams = new String[n];
    for (int i = 0; i < n; i++) {
      teams[i] = Integer.toString(i + 1);
    }
    for (; n > 1; n /= 2) {
      for (int i = 0; i < n; i++) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(teams[i]);
        sb.append(',');
        sb.append(teams[n - 1 - i]);
        sb.append(')');
        teams[i] = sb.toString();
      }
    }
    return teams[0];
  }

  public List<Integer> boundaryOfBinaryTreeRes;

  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    boundaryOfBinaryTreeRes = new ArrayList<>();
    boundaryOfBinaryTreeRes.add(root.val);
    boundaryOfBinaryTreeLeft(root.left);
    if (root.left != null || root.right != null) {
      boundaryOfBinaryTreeBottom(root);
    }
    boundaryOfBinaryTreeRight(root.right);
    return boundaryOfBinaryTreeRes;
  }

  public void boundaryOfBinaryTreeLeft(TreeNode root) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      return;
    }
    if (root.left != null) {
      boundaryOfBinaryTreeRes.add(root.val);
      boundaryOfBinaryTreeLeft(root.left);
    } else {
      boundaryOfBinaryTreeRes.add(root.val);
      boundaryOfBinaryTreeLeft(root.right);
    }
  }

  public void boundaryOfBinaryTreeBottom(TreeNode root) {
    if (root == null) {
      return;
    }
    boundaryOfBinaryTreeBottom(root.left);
    if (root.left == null && root.right == null) {
      boundaryOfBinaryTreeRes.add(root.val);
    }
    boundaryOfBinaryTreeBottom(root.right);
  }

  public void boundaryOfBinaryTreeRight(TreeNode root) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      return;
    }
    if (root.right != null) {
      boundaryOfBinaryTreeRight(root.right);
      boundaryOfBinaryTreeRes.add(root.val);
    } else {
      boundaryOfBinaryTreeRight(root.left);
      boundaryOfBinaryTreeRes.add(root.val);
    }
  }

  public Map<String, Integer> removeBoxesMap;

  public String turnIntArrayToString(int[] boxes) {
    StringBuilder sb = new StringBuilder();
    for (int n : boxes) {
      sb.append(n);
      sb.append(',');
    }
    return sb.toString();
  }

  public int removeBoxes(int[] boxes) {
    removeBoxesMap = new HashMap<>();
    return removeBoxesHelp(boxes);
  }

  public int removeBoxesHelp(int[] boxes) {
    if (boxes.length == 0) {
      return 0;
    }
    String key = turnIntArrayToString(boxes);
    if (removeBoxesMap.containsKey(key)) {
      return removeBoxesMap.get(key);
    }
    int max = 0;
    for (int i = 0; i < boxes.length; i++) {
      int index = i;
      while (i < boxes.length && boxes[index] == boxes[i]) {
        i++;
      }
      int count = i - index;
      int temp = count * count;
      int[] newBoxes = new int[boxes.length - count];
      for (int j = 0; j < boxes.length; j++) {
        if (j < index) {
          newBoxes[j] = boxes[j];
        } else if (j >= i) {
          newBoxes[j - count] = boxes[j];
        }
      }
      max = Math.max(max, removeBoxesHelp(newBoxes) + temp);
      i--;
    }
    removeBoxesMap.put(key, max);
    return max;
  }

  public int removeBoxes2(int[] boxes) {
    int len = boxes.length;
    int[][][] dp = new int[len][len][len];
    return removeBoxesHelp2(boxes, dp, 0, len - 1, 0);
  }

  public int removeBoxesHelp2(int[] boxes, int[][][] dp, int l, int r, int k) {
    if (l > r) {
      return 0;
    }
    if (dp[l][r][k] == 0) {
      int realR = r, realK = k;
      while (realR > l && boxes[realR] == boxes[realR - 1]) {
        realR--;
        realK++;
      }
      dp[l][r][k] = removeBoxesHelp2(boxes, dp, l, realR - 1, 0) + (realK + 1) * (realK + 1);
      for (int i = l; i < r; i++) {
        if (boxes[i] == boxes[r]) {
          dp[l][r][k] = Math.max(dp[l][r][k],
              removeBoxesHelp2(boxes, dp, l, i, k + 1) + removeBoxesHelp2(boxes, dp, i + 1, r - 1, 0));
        }
      }
    }

    return dp[l][r][k];
  }

  public int findCircleNum(int[][] isConnected) {
    int len = isConnected.length, res = 0;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (isConnected[i][j] == 1) {
          res++;
          findCircleNumDfs(isConnected, i);
        }
      }
    }
    return res;
  }

  public void findCircleNumDfs(int[][] isConnected, int target) {
    int len = isConnected.length;
    for (int i = 0; i < len; i++) {
      if (isConnected[target][i] == 1) {
        isConnected[target][i] = 0;
        isConnected[i][target] = 0;
        if (target != i) {
          findCircleNumDfs(isConnected, i);
        }
      }
    }
  }

  public int findCircleNum2(int[][] isConnected) {
    UnionFind uf = new UnionFind(isConnected.length);
    int len = isConnected.length;
    for (int i = 0; i < len; i++) {
      for (int j = i + 1; j < len; j++) {
        if (isConnected[i][j] == 1) {
          uf.merge(i, j);
        }
      }
    }
    return uf.findRootNum();
  }

  public class UnionFind {

    public int[] parent;

    public UnionFind(int n) {
      parent = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    public void merge(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX != rootY) {
        parent[rootX] = rootY;
      }
    }

    public int find(int x) {
      if (parent[x] == x) {
        return x;
      }
      int temp = find(parent[x]);
      parent[x] = temp;
      return temp;
    }

    public int findRootNum() {
      int res = 0;
      for (int i = 0; i < parent.length; i++) {
        if (parent[i] == i) {
          res++;
        }
      }
      return res;
    }

  }

  public int findCircleNum3(int[][] isConnected) {
    int len = isConnected.length, res = 0;
    boolean[] visited = new boolean[len];
    for (int i = 0; i < len; i++) {
      if (!visited[i]) {
        res++;
        findCircleNum3dfs(isConnected, i, visited);
      }
    }

    return res;
  }

  public void findCircleNum3dfs(int[][] isConnected, int target, boolean[] visited) {
    int len = isConnected.length;
    if (visited[target]) {
      return;
    }
    visited[target] = true;
    for (int i = 0; i < len; i++) {
      if (isConnected[target][i] == 1) {
        findCircleNum3dfs(isConnected, i, visited);
      }
    }
  }

}

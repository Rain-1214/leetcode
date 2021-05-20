package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgorithmController301to320 {

  public char[] removeInvalidParenthesesSc;
  public int removeInvalidParenthesesLen;
  public Set<String> removeInvalidParenthesesSet;

  public List<String> removeInvalidParentheses(String s) {
    this.removeInvalidParenthesesLen = s.length();
    this.removeInvalidParenthesesSet = new HashSet<>();
    this.removeInvalidParenthesesSc = s.toCharArray();

    int leftRemove = 0;
    int rightRemove = 0;
    for (char c : removeInvalidParenthesesSc) {
      if (c == '(') {
        leftRemove++;
      } else if (c == ')') {
        if (leftRemove == 0) {
          rightRemove++;
        }
        if (leftRemove > 0) {
          leftRemove--;
        }
      }
    }
    removeInvalidParentheses(0, 0, 0, leftRemove, rightRemove, new StringBuilder());
    return new ArrayList<>(this.removeInvalidParenthesesSet);
  }

  public void removeInvalidParentheses(int index, int leftCount, int rightCount, int leftRemove, int rightRemove,
      StringBuilder sb) {
    if (index == this.removeInvalidParenthesesLen) {
      if (leftRemove == 0 && rightRemove == 0) {
        this.removeInvalidParenthesesSet.add(sb.toString());
      }
      return;
    }

    char currentChar = this.removeInvalidParenthesesSc[index];
    if (currentChar == '(' && leftRemove > 0) {
      this.removeInvalidParentheses(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, sb);
    }
    if (currentChar == ')' && rightRemove > 0) {
      this.removeInvalidParentheses(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, sb);
    }

    sb.append(currentChar);

    if (currentChar != '(' && currentChar != ')') {
      this.removeInvalidParentheses(index + 1, leftCount, rightCount, leftRemove, rightRemove, sb);
    } else if (currentChar == '(') {
      this.removeInvalidParentheses(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, sb);
    }
    if (leftCount > rightCount) {
      this.removeInvalidParentheses(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, sb);
    }
    sb.deleteCharAt(sb.length() - 1);
  }

  public int minAreaLeft, maxAreaRight, minAreaTop, maxAreaBottom;

  public int minArea(char[][] image, int x, int y) {
    if (image.length == 0 || image[0].length == 0) {
      return 0;
    }
    minAreaLeft = maxAreaRight = y;
    minAreaTop = maxAreaBottom = x;
    minAreaHelp(image, x, y);

    return (maxAreaRight - minAreaLeft + 1) * (maxAreaBottom - minAreaTop + 1);
  }

  public void minAreaHelp(char[][] image, int x, int y) {
    if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] == '0') {
      return;
    }
    image[x][y] = '0';
    minAreaLeft = Math.min(y, minAreaLeft);
    maxAreaRight = Math.max(y, maxAreaRight);
    minAreaTop = Math.min(x, minAreaTop);
    maxAreaBottom = Math.max(x, maxAreaBottom);
    minAreaHelp(image, x - 1, y);
    minAreaHelp(image, x + 1, y);
    minAreaHelp(image, x, y - 1);
    minAreaHelp(image, x, y + 1);

  }

  class NumArray {

    int[] nums;

    public NumArray(int[] nums) {
      if (nums.length == 0) {
        this.nums = new int[0];
        return;
      }
      this.nums = new int[nums.length];
      this.nums[0] = nums[0];
      for (int i = 1; i < nums.length; i++) {
        this.nums[i] = this.nums[i - 1] + nums[i];
      }
    }

    public int sumRange(int left, int right) {
      if (left == 0) {
        return this.nums[right];
      }
      return this.nums[right] - this.nums[left - 1];
    }
  }

  class NumMatrix {

    int[][] matrix;

    public NumMatrix(int[][] matrix) {
      int rows = matrix.length;
      int cols = matrix[0].length;

      this.matrix = new int[rows][cols];

      for (int x = 0; x < rows; x++) {
        for (int y = 0; y < cols; y++) {
          if (y == 0) {
            this.matrix[x][y] = matrix[x][y];
            continue;
          }
          this.matrix[x][y] = this.matrix[x][y - 1] + matrix[x][y];
        }
      }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      int res = 0;
      for (int i = row1; i <= row2; i++) {
        if (col1 == 0) {
          res += this.matrix[i][col2];
          continue;
        }
        res += this.matrix[i][col2] - this.matrix[i][col1 - 1];
      }
      return res;
    }
  }

  class NumMatrixII {

    int[][] dp;

    public NumMatrixII(int[][] matrix) {
      int rows = matrix.length;
      int cols = matrix[0].length;

      this.dp = new int[rows + 1][cols + 1];

      for (int x = 0; x < rows; x++) {
        for (int y = 0; y < cols; y++) {
          dp[x + 1][y + 1] = dp[x + 1][y] + dp[x][y + 1] + matrix[x][y] - dp[x][y];
        }
      }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      return dp[row2 + 1][col2 + 1] - dp[row2 + 1][col1] - dp[row1][col2 + 1] + dp[row1][col1];
    }
  }

  public static final int[][] dirs = new int[][] { new int[] { 0, -1 }, new int[] { 0, 1 }, new int[] { -1, 0 },
      new int[] { 1, 0 } };

  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    List<Integer> res = new ArrayList<>();
    boolean[] visitor = new boolean[m * n];
    UnionFind unionFind = new UnionFind(m * n);

    for (int[] pos : positions) {
      int x = pos[0];
      int y = pos[1];
      int index = x * n + y;
      if (!visitor[index]) {
        unionFind.addCount();
        visitor[index] = true;
        for (int[] dir : dirs) {
          int nextX = x + dir[0];
          int nextY = y + dir[1];

          int nextIndex = nextX * n + nextY;
          if (inArea(m, n, nextX, nextY) && visitor[nextIndex] && !unionFind.isConnect(index, nextIndex)) {
            unionFind.merge(index, nextIndex);
          }
        }
      }
      res.add(unionFind.getCount());
    }
    return res;
  }

  public boolean inArea(int m, int n, int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }

  class UnionFind {

    private int[] parent;
    private int count;

    public void addCount() {
      this.count++;
    }

    public int getCount() {
      return this.count;
    }

    public UnionFind(int n) {
      this.parent = new int[n];
      for (int i = 0; i < n; i++) {
        this.parent[i] = i;
      }
    }

    public boolean isConnect(int x, int y) {
      return this.find(x) == this.find(y);
    }

    public int find(int x) {
      if (this.parent[x] != x) {
        this.parent[x] = this.find(this.parent[x]);
        return this.parent[x];
      }
      return this.parent[x];
    }

    public void merge(int x, int y) {
      int rootX = this.find(x);
      int rootY = this.find(y);
      if (rootX == rootY) {
        return;
      }
      this.parent[rootX] = rootY;
      this.count--;
    }

  }

  public boolean isAdditiveNumber(String num) {
    int len = num.length();
    if (len < 3) {
      return false;
    }
    for (int i = 1; i < num.length(); i++) {
      String s1 = num.substring(0, i);
      for (int j = i + 1; j < num.length(); j++) {
        String s2 = num.substring(i, j);
        if (s2.length() > 1 && s2.charAt(0) == '0') {
          break;
        }
        if (isAdditiveNumber(num, j, s2, addString(s1, s2))) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean isAdditiveNumber(String num, int index, String prev, String sum) {

    if (index == num.length()) {
      return true;
    }

    for (int i = index; i < num.length(); i++) {
      String temp = num.substring(index, i + 1);
      if (i > index && temp.charAt(0) == '0') {
        break;
      }

      if (temp.length() > sum.length()) {
        return false;
      }
      if (temp.equals(sum)) {
        return isAdditiveNumber(num, i + 1, temp, addString(temp, prev));
      }
    }
    return false;
  }

  public String addString(String s1, String s2) {
    StringBuilder sb = new StringBuilder();
    int prev = 0, s1i = s1.length() - 1, s2i = s2.length() - 1;
    while (s1i >= 0 || s2i >= 0) {
      int num1 = s1i >= 0 ? s1.charAt(s1i) - '0' : 0;
      int num2 = s2i >= 0 ? s2.charAt(s2i) - '0' : 0;
      int sum = num1 + num2 + prev;
      prev = 0;
      if (sum >= 10) {
        prev = sum / 10;
        sb.append(sum % 10);
      } else {
        sb.append(sum);
      }
      s1i--;
      s2i--;
    }
    if (prev != 0) {
      sb.append(prev);
    }
    return sb.reverse().toString();
  }

}

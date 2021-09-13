package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

import com.leetcode.entity.TreeNode;

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

  class NumArrayII {

    int[] prefixSum;
    int[] nums;

    public NumArrayII(int[] nums) {
      this.nums = nums;
      this.prefixSum = new int[nums.length];
      this.prefixSum[0] = nums[0];
      for (int i = 1; i < nums.length; i++) {
        this.prefixSum[i] = this.prefixSum[i - 1] + nums[i];
      }
    }

    public void update(int index, int val) {
      int temp = val - nums[index];
      nums[index] = val;
      for (int i = index; i < nums.length; i++) {
        prefixSum[i] += temp;
      }
    }

    public int sumRange(int left, int right) {
      if (left == 0) {
        return prefixSum[right];
      }
      return prefixSum[right] - prefixSum[left - 1];
    }
  }

  class NumArrayII1 {

    int[] nums;

    public NumArrayII1(int[] nums) {
      this.nums = nums;
    }

    public void update(int index, int val) {
      this.nums[index] = val;
    }

    public int sumRange(int left, int right) {
      int sum = 0;
      for (int i = left; i <= right; i++) {
        sum += this.nums[i];
      }
      return sum;
    }
  }

  class NumArrayII2 {

    int[] nums;
    int n;

    public NumArrayII2(int[] nums) {
      this.n = nums.length;
      this.nums = new int[nums.length * 2];
      for (int i = this.n; i < this.n * 2; i++) {
        this.nums[i] = nums[i - this.n];
      }
      for (int i = this.n - 1; i >= 0; i--) {
        this.nums[i] = this.nums[i * 2] + this.nums[i * 2 + 1];
      }
    }

    public void update(int index, int val) {
      int i = index + this.n;
      this.nums[i] = val;
      while (i > 0) {
        int left = (i % 2) == 0 ? i : i - 1;
        int right = (i % 2) == 0 ? i + 1 : i;
        this.nums[i / 2] = this.nums[left] + this.nums[right];
        i /= 2;
      }
    }

    public int sumRange(int left, int right) {
      int l = left + this.n;
      int r = right + this.n;
      int sum = 0;
      while (r >= l) {
        if ((l % 2) == 1) {
          sum += this.nums[l];
          l++;
        }
        if ((r % 2) == 0) {
          sum += this.nums[r];
          r--;
        }
        r /= 2;
        l /= 2;
      }
      return sum;
    }
  }

  class NumMatrixIII {

    int n;
    int colMax;
    int[] nums;

    public NumMatrixIII(int[][] matrix) {
      int rowMax = matrix.length;
      colMax = matrix[0].length;
      n = rowMax * colMax;
      this.nums = new int[n * 2];
      for (int i = 0; i < rowMax; i++) {
        for (int j = 0; j < colMax; j++) {
          nums[n + colMax * i + j] = matrix[i][j];
        }
      }
      for (int i = n - 1; i > 0; i--) {
        nums[i] = nums[i * 2] + nums[i * 2 + 1];
      }
    }

    public void update(int row, int col, int val) {
      int i = n + row * colMax + col;
      nums[i] = val;
      while (i > 0) {
        int left = (i % 2) == 0 ? i : i - 1;
        int right = (i % 2) == 0 ? i + 1 : i;
        nums[i / 2] = nums[left] + nums[right];
        i /= 2;
      }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      int res = 0;
      for (int i = row1; i <= row2; i++) {
        int left = n + i * colMax + col1;
        int right = n + i * colMax + col2;
        int temp = 0;
        while (right >= left) {
          if ((left % 2) != 0) {
            temp += nums[left];
            left++;
          }
          if ((right % 2) == 0) {
            temp += nums[right];
            right--;
          }
          left /= 2;
          right /= 2;
        }
        res += temp;
      }
      return res;
    }
  }

  class NumMatrixIV {

    int[][] matrix;
    int[][] prefixSums;

    public NumMatrixIV(int[][] matrix) {
      int row = matrix.length;
      int col = matrix[0].length;
      this.matrix = matrix;
      prefixSums = new int[row][col];
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (j == 0) {
            prefixSums[i][j] = matrix[i][j];
            continue;
          }
          prefixSums[i][j] = matrix[i][j] + prefixSums[i][j - 1];
        }
      }
    }

    public void update(int row, int col, int val) {
      int temp = val - matrix[row][col];
      for (int i = col; i < prefixSums[0].length; i++) {
        if (i == 0) {
          prefixSums[row][i] = val;
          continue;
        }
        prefixSums[row][i] += temp;
      }
      matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      int res = 0;
      for (int i = row1; i <= row2; i++) {
        if (col1 == 0) {
          res += prefixSums[i][col2];
          continue;
        }
        res += prefixSums[i][col2] - prefixSums[i][col1 - 1];
      }
      return res;
    }
  }

  public int maxProfit(int[] prices) {
    if (prices.length == 1) {
      return 0;
    }
    int[] sell = new int[prices.length];
    int[] buy = new int[prices.length];
    buy[0] = -prices[0];
    buy[1] = Math.max(buy[0], -prices[1]);
    sell[1] = Math.max(sell[0], buy[0] + prices[1]);
    for (int i = 2; i < prices.length; i++) {
      sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
      buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
    }
    int max = Integer.MIN_VALUE;
    for (int n : sell) {
      max = Math.max(max, n);
    }
    return max;
  }

  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> res = new ArrayList<>();
    if (n == 1) {
      res.add(0);
      return res;
    }

    List<List<Integer>> tree = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      tree.add(new ArrayList<>());
    }
    int[] in = new int[n];
    for (int[] edge : edges) {
      in[edge[0]]++;
      in[edge[1]]++;
      tree.get(edge[0]).add(edge[1]);
      tree.get(edge[1]).add(edge[0]);
    }

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (in[i] == 1) {
        q.offer(i);
      }
    }
    while (n > 2) {
      int size = q.size();
      n -= size;
      for (int i = 0; i < size; i++) {
        int current = q.poll();
        for (int next : tree.get(current)) {
          in[next]--;
          if (in[next] == 1) {
            q.add(next);
          }
        }
      }
    }
    while (!q.isEmpty()) {
      res.add(q.poll());
    }
    return res;
  }

  public int[][] multiply(int[][] mat1, int[][] mat2) {
    int rowMax = mat1.length;
    int colMax = mat2[0].length;
    int[][] res = new int[rowMax][colMax];
    for (int row = 0; row < rowMax; row++) {
      for (int col = 0; col < colMax; col++) {
        int temp = 0;
        for (int i = 0; i < mat1[0].length; i++) {
          if (mat1[row][i] == 0 || mat2[i][col] == 0) {
            continue;
          }
          temp += mat1[row][i] * mat2[i][col];
        }
        res[row][col] = temp;
      }
    }
    return res;
  }

  public int maxCoins(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n + 2][n + 2];
    int[] vals = new int[n + 2];
    vals[0] = vals[n + 1] = 1;
    for (int i = 1; i < n + 1; i++) {
      vals[i] = nums[i - 1];
    }
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i + 2; j < n + 2; j++) {
        int temp = vals[i] * vals[j];
        int max = 0;
        for (int k = i + 1; k < j; k++) {
          max = Math.max(max, temp * vals[k] + dp[i][k] + dp[k][j]);
        }
        dp[i][j] = max;
      }
    }
    return dp[0][n + 1];
  }

  public int nthSuperUglyNumber(int n, int[] primes) {
    if (n == 1) {
      return 1;
    }
    int[] nums = new int[n];
    int[] primesIndex = new int[primes.length];
    nums[0] = 1;
    for (int i = 1; i < n; i++) {
      int min = Integer.MAX_VALUE;
      for (int j = 0; j < primes.length; j++) {
        min = Math.min(min, primes[j] * nums[primesIndex[j]]);
      }
      nums[i] = min;
      for (int j = 0; j < primes.length; j++) {
        int temp = primes[j] * nums[primesIndex[j]];
        if (temp == min) {
          primesIndex[j]++;
        }
      }
    }
    return nums[n - 1];
  }

  public List<List<Integer>> verticalOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    Map<Integer, List<Integer>> map = new TreeMap<>();
    Map<TreeNode, Integer> ti = new HashMap<>();

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    ti.put(root, 0);

    while (!q.isEmpty()) {
      TreeNode current = q.poll();
      int index = ti.get(current);
      map.computeIfAbsent(index, k -> new ArrayList<>()).add(current.val);
      if (current.left != null) {
        q.add(current.left);
        ti.put(current.left, index - 1);
      }
      if (current.right != null) {
        q.add(current.right);
        ti.put(current.right, index + 1);
      }
    }
    return new ArrayList<>(map.values());
  }

  public int[] countSmallerArray;
  public int[] countSmallerCount;

  public List<Integer> countSmaller(int[] nums) {
    List<Integer> res = new ArrayList<>();
    initCountSmaller(nums);
    for (int i = nums.length - 1; i >= 0; i--) {
      int id = countSmallerGetId(nums[i]);
      res.add(countSmallerQuery(id - 1));
      countSmallerUpdate(id);
    }
    Collections.reverse(res);
    return res;
  }

  public void initCountSmaller(int[] nums) {
    int len = nums.length;
    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      set.add(n);
    }
    int size = set.size();
    countSmallerArray = new int[size];
    int index = 0;
    for (int n : set) {
      countSmallerArray[index++] = n;
    }
    Arrays.sort(countSmallerArray);
    countSmallerCount = new int[len + 1];
  }

  public int countSmallerGetId(int num) {
    return Arrays.binarySearch(countSmallerArray, num) + 1;
  }

  public int lowbit(int num) {
    return (num & -num);
  }

  public void countSmallerUpdate(int id) {
    while (id < countSmallerCount.length) {
      countSmallerCount[id] += 1;
      id += lowbit(id);
    }
  }

  public int countSmallerQuery(int id) {
    int res = 0;
    while (id > 0) {
      res += countSmallerCount[id];
      id -= lowbit(id);
    }
    return res;
  }

  public String removeDuplicateLetters(String s) {
    int[] charNum = new int[26];
    char[] sc = s.toCharArray();
    boolean[] visitor = new boolean[26];
    for (char c : sc) {
      charNum[c - 'a']++;
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < sc.length; i++) {
      int index = sc[i] - 'a';
      if (!visitor[index]) {
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) > sc[i]) {
          if (charNum[sb.charAt(sb.length() - 1) - 'a'] > 0) {
            visitor[sb.charAt(sb.length() - 1) - 'a'] = false;
            sb.deleteCharAt(sb.length() - 1);
          } else {
            break;
          }
        }
        sb.append(sc[i]);
        visitor[index] = true;
      }
      charNum[index]--;
    }
    return sb.toString();
  }

  private static final List<int[]> DIRECTIONS = Arrays.asList(new int[] { 1, 0 }, new int[] { -1, 0 },
      new int[] { 0, 1 }, new int[] { 0, -1 });

  public int shortestDistance(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    int[][] total = new int[row][col];
    int[][] visitor = new int[row][col];
    int timer = 0;
    int min = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == 1) {
          min = shortestDistance(total, i, j, grid, visitor, timer);
          timer++;
          for (int k = 0; k < row; k++) {
            for (int m = 0; m < col; m++) {
              System.out.print(visitor[k][m]);
              System.out.print(",");
            }
            System.out.println("");
          }
          System.out.println(min);
        }
      }
    }
    return min;
  }

  public int shortestDistance(int[][] grid, int row, int col, int[][] sourceGrid, int[][] visitor, int timer) {
    Queue<int[]> q = new LinkedList<>();
    int maxRow = grid.length;
    int maxCol = grid[0].length;
    int min = Integer.MAX_VALUE;
    int[][] tempGrid = new int[maxRow][maxCol];
    q.add(new int[] { row, col });
    while (!q.isEmpty()) {
      int[] temp = q.poll();
      int r = temp[0];
      int c = temp[1];
      for (int[] dir : DIRECTIONS) {
        int nr = r + dir[0];
        int nc = c + dir[1];
        if (nr < 0 || nc < 0 || nr >= maxRow || nc >= maxCol || tempGrid[nr][nc] != 0 || sourceGrid[nr][nc] > 0
            || visitor[nr][nc] < timer) {
          continue;
        }
        tempGrid[nr][nc] = tempGrid[r][c] + 1;
        grid[nr][nc] = tempGrid[nr][nc] + grid[nr][nc];
        visitor[nr][nc]++;
        q.add(new int[] { nr, nc });
        min = Math.min(min, grid[nr][nc]);
      }
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  public int maxProduct(String[] words) {
    int max = 0;
    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if (maxProductDiffString(words[i], words[j])) {
          max = Math.max(words[i].length() * words[j].length(), max);
        }
      }
    }
    return max;
  }

  public boolean maxProductDiffString(String s1, String s2) {
    int code1 = 0, code2 = 0;
    for (char c : s1.toCharArray()) {
      code1 |= 1 << (c - 'a');
    }
    for (char c : s2.toCharArray()) {
      code2 |= 1 << (c - 'a');
    }
    return (code1 & code2) == 0;
  }

  public int maxProductII(String[] words) {
    int n = words.length;
    int[] premake = new int[n];
    int[] lens = new int[n];

    for (int i = 0; i < n; i++) {
      int temp = 0;
      char[] tempChars = words[i].toCharArray();
      for (char c : tempChars) {
        temp |= 1 << (c - 'a');
      }
      premake[i] = temp;
      lens[i] = tempChars.length;
    }

    int max = 0;
    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if ((premake[i] & premake[j]) == 0) {
          max = Math.max(lens[i] * lens[j], max);
        }
      }
    }
    return max;
  }

  public int bulbSwitch(int n) {
    return (int) Math.sqrt(n);
  }

  public List<String> generateAbbreviations(String word) {
    List<String> res = new ArrayList<>();
    generateAbbreviationsImpl(word.toCharArray(), 0, 0, new StringBuilder(), res);
    return res;
  }

  public void generateAbbreviationsImpl(char[] word, int index, int lastChange, StringBuilder sb, List<String> res) {
    int len = word.length;
    if (index == len) {
      int size = sb.length();
      if (lastChange != 0) {
        sb.append(lastChange);
      }
      res.add(sb.toString());
      sb.setLength(size);
      return;
    }
    generateAbbreviationsImpl(word, index + 1, lastChange + 1, sb, res);

    int size = sb.length();
    if (lastChange != 0) {
      sb.append(lastChange);
    }
    sb.append(word[index]);
    generateAbbreviationsImpl(word, index + 1, 0, sb, res);
    sb.setLength(size);
  }

}

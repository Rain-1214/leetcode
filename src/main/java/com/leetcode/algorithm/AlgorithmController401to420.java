package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

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

  public int splitArray(int[] nums, int m) {
    int n = nums.length;
    int[] preSum = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      preSum[i] = preSum[i - 1] + nums[i - 1];
    }
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[0][0] = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= Math.min(m, i); j++) {
        for (int z = 0; z < i; z++) {
          dp[i][j] = Math.min(dp[i][j], Math.max(dp[z][j - 1], preSum[i] - preSum[z]));
        }
      }
    }
    return dp[n][m];
  }

  public int splitArrayII(int[] nums, int m) {
    int left = 0;
    int right = 0;
    for (int num : nums) {
      left = Math.max(left, num);
      right += num;
    }
    while (left < right) {
      int mid = (left + right) / 2;
      if (splitArrayCheck(nums, mid, m)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  public boolean splitArrayCheck(int[] nums, int mid, int m) {
    int sum = 0, count = 1;
    for (int num : nums) {
      if (sum + num > mid) {
        count++;
        sum = num;
      } else {
        sum += num;
      }
    }
    return count <= m;
  }

  public int abbreviationLen(String str) {
    int len = 0, index = 0;
    char[] cs = str.toCharArray();
    while (index < cs.length) {
      if (Character.isDigit(cs[index])) {
        while (index < cs.length && Character.isDigit(cs[index])) {
          index++;
        }
        index--;
      }
      len++;
      index++;
    }
    return len;
  }

  public String minAbbreviation(String target, String[] dictionary) {
    if (dictionary.length == 0) {
      return Integer.toString(target.length());
    }
    List<String> temp = allAbbreviation(target);
    temp.sort((a, b) -> {
      return this.abbreviationLen(a) - this.abbreviationLen(b);
    });
    for (String str : temp) {
      boolean find = true;
      for (String d : dictionary) {
        if (this.validWordAbbreviationII(d, str)) {
          find = false;
          break;
        }
      }
      if (find) {
        return str;
      }
    }
    return "";
  }

  public List<String> allAbbreviation(String target) {
    List<String> res = new ArrayList<>();
    allAbbreviation(target.toCharArray(), 0, new StringBuilder(), 0, res);
    return res;
  }

  public void allAbbreviation(char[] ca, int start, StringBuilder sb, int k, List<String> res) {
    int len = sb.length();
    if (start >= ca.length) {
      if (k > 0) {
        sb.append(k);
      }
      res.add(sb.toString());
      sb.setLength(len);
      return;
    }
    allAbbreviation(ca, start + 1, sb, k + 1, res);
    if (k > 0) {
      sb.append(k);
    }
    sb.append(ca[start]);
    allAbbreviation(ca, start + 1, sb, 0, res);
    sb.setLength(len);
  }

  public List<String> fizzBuzz(int n) {
    List<String> res = new ArrayList<String>();
    String flag3 = "Fizz", flag5 = "Buzz", flag35 = "FizzBuzz";
    for (int i = 1; i <= n; i++) {
      boolean t3 = i % 3 == 0, t5 = i % 5 == 0;
      if (t3 && t5) {
        res.add(flag35);
      } else if (t3) {
        res.add(flag3);
      } else if (t5) {
        res.add(flag5);
      } else {
        res.add(Integer.toString(i));
      }
    }
    return res;
  }

  public int numberOfArithmeticSlices(int[] nums) {
    if (nums.length < 3) {
      return 0;
    }
    int res = 0, left = 0, right = 1;
    while (left < nums.length && right < nums.length) {
      int step = nums[right] - nums[right - 1];
      while (right < nums.length && nums[right] - nums[right - 1] == step) {
        right++;
      }
      if (right - left >= 3) {
        int start = 1, end = (right - left) - 3 + 1;
        res += (end - start + 1) * (end + start) / 2;
        left = right - 1;
      } else {
        right = left + 2;
        left++;
      }
    }
    return res;
  }

  public int thirdMax(int[] nums) {
    Arrays.sort(nums);
    int index = 1, res = nums[nums.length - 1], max = nums[nums.length - 1];
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] != nums[i + 1]) {
        index++;
        res = nums[i];
        if (index == 3) {
          return res;
        }
      }
    }
    return max;
  }

  public String addStrings(String num1, String num2) {
    char[] nc1 = num1.toCharArray();
    char[] nc2 = num2.toCharArray();
    int i1 = nc1.length - 1, i2 = nc2.length - 1;
    int temp = 0;
    StringBuilder sb = new StringBuilder();
    while (i1 >= 0 || i2 >= 0) {
      int n1 = i1 >= 0 ? nc1[i1] - '0' : 0;
      int n2 = i2 >= 0 ? nc2[i2] - '0' : 0;
      int sum = n1 + n2 + temp;
      temp = 0;
      if (sum >= 10) {
        temp = 1;
        sb.append(sum % 10);
      } else {
        sb.append(sum);
      }
      i1--;
      i2--;
    }
    if (temp != 0) {
      sb.append(temp);
    }
    return sb.reverse().toString();
  }

  public boolean canPartition(int[] nums) {
    int n = nums.length;
    if (n < 2) {
      return false;
    }
    int maxNum = 0;
    int sum = 0;
    for (int num : nums) {
      sum += num;
      maxNum = Math.max(num, maxNum);
    }
    if (sum % 2 == 1) {
      return false;
    }
    int target = sum / 2;
    if (maxNum > target) {
      return false;
    }
    boolean[][] dp = new boolean[n][target + 1];
    for (int i = 0; i < n; i++) {
      dp[i][0] = true;
    }
    dp[0][nums[0]] = true;
    for (int i = 1; i < n; i++) {
      int num = nums[i];
      for (int j = 0; j <= target; j++) {
        if (j >= num) {
          dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[n - 1][target];
  }

  public boolean canPartitionII(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    if (sum % 2 != 0) {
      return false;
    }
    // 0 is not calc, 1 is can, 2 is can't
    int[][] cache = new int[nums.length][sum / 2];
    return canPartitionHelp(nums, 0, 0, sum / 2, cache);
  }

  public boolean canPartitionHelp(int[] nums, int index, int sum, int res, int[][] cache) {
    if (sum == res) {
      return true;
    }
    if (index >= nums.length || sum > res) {
      return false;
    }
    if (cache[index][sum] != 0) {
      return cache[index][sum] == 1;
    }
    boolean temp = canPartitionHelp(nums, index + 1, sum + nums[index], res, cache)
        || canPartitionHelp(nums, index + 1, sum, res, cache);
    cache[index][sum] = temp ? 1 : 2;
    return temp;
  }

  public static final int[][] dirs = new int[][] { new int[] { 0, 1 }, new int[] { 0, -1 }, new int[] { 1, 0 },
      new int[] { -1, 0 } };

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<List<Integer>> list = new ArrayList<>();
    boolean[][][] cache = new boolean[heights.length][heights[0].length][2];
    boolean[][] visitor = new boolean[heights.length][heights[0].length];
    boolean[] current = new boolean[2];

    for (int row = 0; row < heights.length; row++) {
      for (int col = 0; col < heights[0].length; col++) {
        current[0] = false;
        current[1] = false;
        pacificAtlantic(heights, row, col, cache, visitor, current);
        visitor[row][col] = true;
        cache[row][col][0] = current[0];
        cache[row][col][1] = current[1];
      }
    }
    for (int row = 0; row < heights.length; row++) {
      for (int col = 0; col < heights[0].length; col++) {
        if (cache[row][col][0] && cache[row][col][1]) {
          List<Integer> temp = new ArrayList<>();
          temp.add(row);
          temp.add(col);
          list.add(temp);
        }
      }
    }
    return list;
  }

  public void pacificAtlantic(int[][] heights, int row, int col, boolean[][][] cache, boolean[][] visitor,
      boolean[] current) {
    if (visitor[row][col]) {
      if (!current[0]) {
        current[0] = cache[row][col][0];
      }
      if (!current[1]) {
        current[1] = cache[row][col][1];
      }
      return;
    }
    if (row == 0 || col == 0) {
      current[0] = true;
    }
    if (row == heights.length - 1 || col == heights[0].length - 1) {
      current[1] = true;
    }
    if (current[0] && current[1]) {
      return;
    }
    int height = heights[row][col];
    heights[row][col] = -1;
    for (int[] dir : dirs) {
      int nr = row + dir[0];
      int nc = col + dir[1];
      if (nr < 0 || nc < 0 || nr >= heights.length || nc >= heights[0].length || heights[nr][nc] > height
          || heights[nr][nc] == -1) {
        continue;
      }
      pacificAtlantic(heights, nr, nc, cache, visitor, current);
    }
    heights[row][col] = height;
  }

  public boolean[][] pacificFlow, atlanticFlow;

  public List<List<Integer>> pacificAtlanticII(int[][] heights) {
    List<List<Integer>> res = new ArrayList<>();
    int maxRow = heights.length, maxCol = heights[0].length;
    pacificFlow = new boolean[maxRow][maxCol];
    atlanticFlow = new boolean[maxRow][maxCol];
    for (int i = 0; i < maxRow; i++) {
      pacificAtlanticIIHelp(heights, pacificFlow, i, 0, res);
      pacificAtlanticIIHelp(heights, atlanticFlow, i, maxCol - 1, res);
    }
    for (int i = 0; i < maxCol; i++) {
      pacificAtlanticIIHelp(heights, pacificFlow, 0, i, res);
      pacificAtlanticIIHelp(heights, atlanticFlow, maxRow - 1, i, res);
    }
    return res;
  }

  public void pacificAtlanticIIHelp(int[][] heights, boolean[][] flow, int row, int col, List<List<Integer>> res) {
    if (flow[row][col]) {
      return;
    }
    flow[row][col] = true;
    if (pacificFlow[row][col] && atlanticFlow[row][col]) {
      res.add(Arrays.asList(row, col));
    }
    for (int[] dir : dirs) {
      int nr = row + dir[0];
      int nc = col + dir[1];
      if (nr < 0 || nc < 0 || nr >= heights.length || nc >= heights[0].length || heights[nr][nc] < heights[row][col]) {
        continue;
      }
      pacificAtlanticIIHelp(heights, flow, nr, nc, res);
    }
  }

  public int wordsTyping(String[] sentence, int rows, int cols) {
    int[] lens = new int[sentence.length];
    for (int i = 0; i < sentence.length; i++) {
      int len = sentence[i].length();
      if (len > cols) {
        return 0;
      }
      lens[i] = len;
    }
    int row = 0, res = 0, col = 0, index = 0;
    while (row < rows) {
      while (col < cols) {
        if (col + lens[index] <= cols) {
          col += lens[index++] + 1;
          if (index >= lens.length) {
            res++;
            index = 0;
          }
        } else {
          break;
        }
      }
      row++;
      col = 0;
    }
    return res;
  }

  public int wordsTypingII(String[] sentence, int rows, int cols) {
    int[] wordSum = new int[sentence.length];
    int[] points = new int[sentence.length];

    for (int i = 0; i < sentence.length; i++) {
      int count = 0;
      int point = i;
      int col = cols;
      while (col >= sentence[point].length()) {
        col -= sentence[point++].length() + 1;
        if (point >= sentence.length) {
          count++;
          point = 0;
        }
      }
      wordSum[i] = count;
      points[i] = point;
    }

    int cur = 0, res = 0;
    for (int i = 0; i < rows; i++) {
      res += wordSum[cur];
      cur = points[cur];
    }
    return res;
  }

  public int countBattleships(char[][] board) {
    int res = 0, rowMax = board.length, colMax = board[0].length;
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (board[i][j] == 'X') {
          res++;
          countBattleships(board, i, j);
        }
      }
    }
    return res;
  }

  public void countBattleships(char[][] board, int row, int col) {
    board[row][col] = '.';
    for (int[] dir : dirs) {
      int nr = row + dir[0];
      int nc = col + dir[1];
      if (nr < 0 || nc < 0 || nr >= board.length || nc >= board[0].length || board[nr][nc] == '.') {
        continue;
      }
      countBattleships(board, nr, nc);
    }
  }

  public int countBattleshipsII(char[][] board) {
    int res = 0, rowMax = board.length, colMax = board[0].length;
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (board[i][j] == 'X') {
          if ((i > 0 && board[i - 1][j] == 'X') || (j > 0 && board[i][j - 1] == 'X')) {
            continue;
          }
          res++;
        }
      }
    }
    return res;
  }

  public int strongPasswordChecker(String password) {
    int len = password.length(), lower = 1, upper = 1, digits = 1;
    int[] sum = new int[len];
    char[] passwordChar = password.toCharArray();
    for (int i = 0; i < passwordChar.length;) {
      if (Character.isLowerCase(passwordChar[i])) {
        lower = 0;
      } else if (Character.isUpperCase(passwordChar[i])) {
        upper = 0;
      } else if (Character.isDigit(passwordChar[i])) {
        digits = 0;
      }
      int j = i;
      while (i < passwordChar.length && passwordChar[j] == passwordChar[i]) {
        i++;
      }
      sum[j] = i - j;
    }
    int miss = lower + upper + digits, res = 0;
    if (len < 6) {
      int diff = 6 - len;
      res += diff + Math.max(0, miss - diff);
    } else {
      int over = Math.max(0, len - 20), left = 0;
      res += over;
      for (int k = 1; k < 3; k++) {
        for (int i = 0; i < len && over > 0; i++) {
          if (sum[i] < 3 || sum[i] % 3 != (k - 1)) {
            continue;
          }
          if (k == 2 && over == 1) {
            sum[i] -= 1;
            over -= 1;
          } else {
            sum[i] -= k;
            over -= k;
          }
        }
      }
      for (int i = 0; i < len; i++) {
        if (sum[i] >= 3 && over > 0) {
          int need = sum[i] - 2;
          sum[i] -= over;
          over -= need;
        }
        if (sum[i] >= 3) {
          left += sum[i] / 3;
        }
      }
      res += Math.max(miss, left);
    }
    return res;
  }

}

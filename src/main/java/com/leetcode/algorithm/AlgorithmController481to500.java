package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.leetcode.entity.Robot;
import com.leetcode.entity.TreeNode;

public class AlgorithmController481to500 {

  public int magicalString(int n) {
    if (n <= 3) {
      return 1;
    }
    int[] temp = new int[n];
    temp[0] = 1;
    temp[1] = 2;
    temp[2] = 2;
    int left = 2, right = 3, count = 1;
    while (right < n) {
      if (temp[left] == 1) {
        temp[right] = temp[right - 1] == 1 ? 2 : 1;
        count += temp[right] == 1 ? 1 : 0;
      } else if (temp[left] == 2) {
        temp[right] = temp[right - 1] == 1 ? 2 : 1;
        count += temp[right] == 1 ? 1 : 0;
        if (right + 1 < n) {
          temp[right + 1] = temp[right];
          count += temp[right + 1] == 1 ? 1 : 0;
        }
        right++;
      }
      right++;
      left++;
    }
    return count;
  }

  public String licenseKeyFormatting(String s, int k) {
    char[] chars = s.toCharArray();
    StringBuilder sb = new StringBuilder();
    int temp = 0;
    for (int i = chars.length - 1; i >= 0; i--) {
      if (chars[i] == '-') {
        continue;
      }
      char c = chars[i];
      if (c >= 'a' && c <= 'z') {
        c -= 32;
      }
      sb.append(c);
      temp++;
      if (temp == k) {
        sb.append('-');
        temp = 0;
      }
    }
    if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.reverse().toString();
  }

  public String smallestGoodBase(String n) {
    long num = Long.parseLong(n);
    int mMax = (int) (Math.log(num) / Math.log(2));
    for (int m = mMax; m >= 2; m--) {
      int k = (int) Math.pow(num, 1.0 / m);
      long temp = 1, sum = 1;
      for (int i = 0; i < m; i++) {
        temp *= k;
        sum += temp;
      }
      if (sum == num) {
        return String.valueOf(k);
      }
    }
    return String.valueOf(num - 1);
  }

  public int[] findPermutation(String s) {
    char[] sc = s.toCharArray();
    int[] res = new int[sc.length + 1];
    for (int i = 0; i <= sc.length; i++) {
      res[i] = i + 1;
    }
    int left = 0, right = 0;
    while (left < sc.length) {
      if (sc[left] == 'I') {
        left++;
        continue;
      }
      while (right < sc.length && sc[right] == 'D') {
        right++;
      }
      int l = left, r = right;
      while (l < r) {
        swap(res, l, r);
        l++;
        r--;
      }
      left = right + 1;
      right = left;
    }
    return res;
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int res = 0, count = 0;
    for (int n : nums) {
      if (n == 1) {
        count++;
      } else {
        res = Math.max(res, count);
        count = 0;
      }
    }
    return Math.max(res, count);
  }

  public boolean PredictTheWinner(int[] nums) {
    return PredictTheWinner(nums, 0, nums.length - 1, 1) >= 0;
  }

  public int PredictTheWinner(int[] nums, int left, int right, int turn) {
    if (left == right) {
      return nums[left] * turn;
    }
    int leftVal = nums[left] * turn + PredictTheWinner(nums, left + 1, right, -turn);
    int rightVal = nums[right] * turn + PredictTheWinner(nums, left, right - 1, -turn);
    if (turn == 1) {
      return Math.max(leftVal, rightVal);
    } else {
      return Math.min(leftVal, rightVal);
    }
  }

  public boolean PredictTheWinnerII(int[] nums) {
    int[][] dp = new int[nums.length][nums.length];
    for (int i = 0; i < nums.length; i++) {
      dp[i][i] = nums[i];
    }
    for (int i = nums.length - 2; i >= 0; i--) {
      for (int j = i + 1; j < nums.length; j++) {
        dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
      }
    }
    return dp[0][nums.length - 1] >= 0;
  }

  public int findMaxConsecutiveOnes487(int[] nums) {
    if (nums.length == 1) {
      return 1;
    }
    int end = 0;
    int[] oneNums = new int[nums.length];
    int index = 0;
    while (index < nums.length) {
      if (nums[index] == 0) {
        oneNums[end++] = 0;
        index++;
        continue;
      }
      int n = 0;
      while (index < nums.length && nums[index] == 1) {
        index++;
        n++;
      }
      oneNums[end++] = n;
    }
    int res = 0;
    for (int i = 0; i < end; i++) {
      int current = oneNums[i];
      if (current == 0) {
        res = Math.max(res, 1);
        continue;
      }
      int left = i - 2 >= 0 ? current + 1 + oneNums[i - 2] : i - 1 >= 0 ? current + 1 : current;
      int right = i + 2 < end ? current + 1 + oneNums[i + 2] : i + 1 < end ? current + 1 : current;
      res = Math.max(res, Math.max(left, right));
    }
    return res;
  }

  public int findMaxConsecutiveOnes487II(int[] nums) {
    int res = 0, zeroPosition = 0, zeroCount = 0;
    for (int l = 0, r = 0; r < nums.length; r++) {
      if (nums[r] == 0) {
        zeroCount++;
        if (zeroCount > 1) {
          l = zeroPosition + 1;
          zeroCount--;
        }
        zeroPosition = r;
      }
      res = Math.max(res, r - l + 1);
    }
    return res;
  }

  public int findMinStep(String board, String hand) {
    char[] hands = new char[5];
    for (char c : hand.toCharArray()) {
      hands[getIndex(c)]++;
    }
    Queue<String[]> q = new LinkedList<>();
    q.add(new String[] { board, new String(hands) });

    int step = 0;
    Set<String> visited = new HashSet<>();
    while (!q.isEmpty()) {
      int size = q.size();
      step++;
      for (int i = 0; i < size; i++) {
        String[] cur = q.poll();
        String curBoard = cur[0];
        String curHand = cur[1];
        if (addBall(curBoard, curHand, visited, q)) {
          return step;
        }
      }
    }
    return -1;
  }

  public boolean addBall(String board, String hand, Set<String> cache, Queue<String[]> q) {
    char[] hands = hand.toCharArray();
    StringBuilder sb = new StringBuilder(board);
    for (int i = 0; i < board.length(); i++) {
      for (int j = 0; j < hands.length; j++) {
        char currentInsertChar = getChar(j);
        if (i > 0 && board.charAt(i - 1) == currentInsertChar) {
          continue;
        }
        if (hands[j] == 0) {
          continue;
        }
        boolean shouldInsert = false;
        if (board.charAt(i) == currentInsertChar) {
          shouldInsert = true;
        }
        if (i > 0 && board.charAt(i - 1) != currentInsertChar && board.charAt(i) == board.charAt(i - 1)) {
          shouldInsert = true;
        }
        if (!shouldInsert) {
          continue;
        }
        sb.insert(i, currentInsertChar);
        String newBoard = checkBall(sb.toString(), i);
        if (cache.contains(newBoard)) {
          sb.deleteCharAt(i);
          continue;
        }
        if (newBoard.equals("")) {
          return true;
        }
        cache.add(newBoard);
        hands[j]--;
        q.add(new String[] { newBoard, new String(hands) });
        sb.deleteCharAt(i);
        hands[j]++;
      }
    }
    return false;
  }

  public String checkBall(String board, int i) {
    StringBuilder sb = new StringBuilder(board);
    while (i >= 0 && i < sb.length()) {
      int left = i, right = i;
      char current = sb.charAt(i);
      while (left >= 0 && sb.charAt(left) == current) {
        left--;
      }
      while (right < sb.length() && sb.charAt(right) == current) {
        right++;
      }
      if (right - left > 3) {
        sb.delete(left + 1, right).toString();
        i = left >= 0 ? left : right;
      } else {
        break;
      }
    }
    return sb.toString();
  }

  public int getIndex(char c) {
    switch (c) {
      case 'R':
        return 0;
      case 'Y':
        return 1;
      case 'B':
        return 2;
      case 'G':
        return 3;
      case 'W':
        return 4;
    }
    return -1;
  }

  public char getChar(int i) {
    switch (i) {
      case 0:
        return 'R';
      case 1:
        return 'Y';
      case 2:
        return 'B';
      case 3:
        return 'G';
      case 4:
        return 'W';
    }
    return ' ';
  }

  // going clockwise : 0: 'up', 1: 'left', 2: 'down', 3: 'right'
  public final int[][] DIRECTIONS = new int[][] { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

  public void cleanRoom(Robot robot) {
    Set<Integer> visited = new HashSet<>();
    visited.add(0);
    cleanRoom(robot, 0, 0, 0, visited);
  }

  public void cleanRoom(Robot robot, int x, int y, int direction, Set<Integer> visited) {
    robot.clean();
    for (int i = 0; i < 4; i++) {
      int newDirection = (direction + i) % 4;
      int newX = x + DIRECTIONS[newDirection][0];
      int newY = y + DIRECTIONS[newDirection][1];
      if (!visited.contains(newX + newY * 200) && robot.move()) {
        visited.add(newX + newY * 200);
        cleanRoom(robot, newX, newY, newDirection, visited);
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
      }
      robot.turnLeft();
    }
  }

  public boolean hasPath(int[][] maze, int[] start, int[] destination) {
    Set<Integer> cache = new HashSet<>();
    return hasPath(maze, start[0], start[1], destination, cache);
  }

  public boolean hasPath(int[][] maze, int x, int y, int[] target, Set<Integer> cache) {
    int index = x + y * maze[0].length;
    cache.add(index);
    for (int i = 0; i < 4; i++) {
      int newX = x;
      int newY = y;
      switch (i) {
        case 0:
          for (int j = x; j >= 0; j--) {
            if (maze[j][y] != 1) {
              newX = j;
            } else {
              break;
            }
          }
          break;
        case 1:
          for (int j = y; j < maze[0].length; j++) {
            if (maze[x][j] != 1) {
              newY = j;
            } else {
              break;
            }
          }
          break;
        case 2:
          for (int j = x; j < maze.length; j++) {
            if (maze[j][y] != 1) {
              newX = j;
            } else {
              break;
            }
          }
          break;
        case 3:
          for (int j = y; j >= 0; j--) {
            if (maze[x][j] != 1) {
              newY = j;
            } else {
              break;
            }
          }
          break;
      }
      if (newX == target[0] && newY == target[1]) {
        return true;
      }
      if (cache.contains(newX + newY * maze[0].length)) {
        continue;
      }
      if (hasPath(maze, newX, newY, target, cache)) {
        return true;
      }
    }
    return false;
  }

  public List<List<Integer>> findSubsequences(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    findSubsequences(nums, 0, Integer.MIN_VALUE, new ArrayList<>(), res);
    return res;
  }

  public void findSubsequences(int[] nums, int index, int last, List<Integer> curr, List<List<Integer>> res) {
    if (index >= nums.length) {
      if (curr.size() >= 2) {
        res.add(new ArrayList<>(curr));
      }
      return;
    }

    if (nums[index] >= last) {
      curr.add(nums[index]);
      findSubsequences(nums, index + 1, nums[index], curr, res);
      curr.remove(curr.size() - 1);
    }

    if (nums[index] != last) {
      findSubsequences(nums, index + 1, last, curr, res);
    }
  }

  public int[] constructRectangle(int area) {
    int min = 1, max = area;
    int diff = Integer.MAX_VALUE;
    for (int i = min; i <= area / 2; i++) {
      if (area % i == 0 && diff > Math.abs(area / i - i)) {
        diff = Math.abs(area / i - i);
        min = i;
        max = area / i;
      }
    }
    return new int[] { Math.max(min, max), Math.min(min, max) };
  }

  public int[] constructRectangleII(int area) {
    int mid = (int) Math.sqrt(area);
    if (mid * mid == area) {
      return new int[] { mid, mid };
    }
    int left = mid, right = mid + 1;
    while (left > 0 || right <= area / 2) {
      if (left > 0 && area % left == 0) {
        return new int[] { Math.max(left, area / left), Math.min(left, area / left) };
      } else if (right <= area / 2 && area % right == 0) {
        return new int[] { Math.max(right, area / right), Math.min(right, area / right) };
      }
      left--;
      right++;
    }
    return new int[] {};
  }

  public int[] constructRectangleIII(int area) {
    int mid = (int) Math.sqrt(area);
    int left = mid;
    while (area % left != 0) {
      left--;
    }
    return new int[] { area / left, left };
  }

  public int reversePairs(int[] nums) {
    Set<Long> set = new TreeSet<>();
    for (int num : nums) {
      set.add((long) num);
      set.add((long) num * 2);
    }

    Map<Long, Integer> values = new HashMap<>();
    int index = 0;
    for (long num : set) {
      values.put(num, index++);
    }

    SegTree tree = new SegTree(values.size());
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      res += tree.query(values.get((long) nums[i] * 2) + 1, values.size() - 1);
      tree.update(values.get((long) nums[i]), 1);
    }
    return res;
  }

  class SegTree {

    int[] tree;
    int n;

    public SegTree(int n) {
      this.n = n;
      this.tree = new int[n * 2];
    }

    public int query(int l, int r) {
      int res = 0;
      int left = l + n;
      int right = r + n;
      while (right >= left) {
        if (right % 2 == 0) {
          res += tree[right--];
        }
        if (left % 2 == 1) {
          res += tree[left++];
        }
        right /= 2;
        left /= 2;
      }
      return res;
    }

    public void update(int x, int val) {
      int cur = x + n;
      tree[cur] += val;
      while (cur > 0) {
        cur /= 2;
        tree[cur] += val;
      }
    }

  }

  public int reversePairsII(int[] nums) {
    if (nums.length <= 1) {
      return 0;
    }
    return reversePairsII(nums, 0, nums.length - 1);
  }

  public int reversePairsII(int[] nums, int left, int right) {
    if (left == right) {
      return 0;
    }
    int mid = (left + right) / 2;
    int ln = reversePairsII(nums, left, mid);
    int rn = reversePairsII(nums, mid + 1, right);
    int res = 0;
    int leftIndex = left, rightIndex = mid + 1;
    while (leftIndex <= mid) {
      while (rightIndex <= right && (long) nums[leftIndex] > 2 * (long) nums[rightIndex]) {
        rightIndex++;
      }
      res += rightIndex - mid - 1;
      leftIndex++;
    }

    int[] temp = new int[right - left + 1];
    int lp = left, rp = mid + 1;
    int p = 0;
    while (p < temp.length) {
      if (lp > mid) {
        temp[p++] = nums[rp++];
      } else if (rp > right) {
        temp[p++] = nums[lp++];
      } else {
        if (nums[lp] <= nums[rp]) {
          temp[p++] = nums[lp++];
        } else {
          temp[p++] = nums[rp++];
        }
      }
    }
    System.arraycopy(temp, 0, nums, left, temp.length);
    return ln + rn + res;
  }

  public int findTargetSumWays(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(nums[0], 1);
    if (map.containsKey(-nums[0])) {
      map.put(-nums[0], 2);
    } else {
      map.put(-nums[0], 1);
    }
    for (int i = 1; i < nums.length; i++) {
      Map<Integer, Integer> temp = new HashMap<>();
      for (int key : map.keySet()) {
        temp.put(key + nums[i], temp.getOrDefault(key + nums[i], 0) + map.get(key));
        temp.put(key - nums[i], temp.getOrDefault(key - nums[i], 0) + map.get(key));
      }
      map = temp;
    }
    return map.getOrDefault(target, 0);
  }

  public int findPoisonedDuration(int[] timeSeries, int duration) {
    if (duration == 0) {
      return 0;
    }
    int start = timeSeries[0], end = timeSeries[0] + duration;
    int res = 0;
    for (int i = 1; i < timeSeries.length; i++) {
      if (timeSeries[i] > end) {
        res += end - start;
        start = timeSeries[i];
        end = timeSeries[i] + duration;
        continue;
      }
      end = timeSeries[i] + duration;
    }
    res += end - start;
    return res;
  }

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums2.length; i++) {
      int cur = nums2[i];
      map.put(cur, i);
    }
    int[] res = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      int cur = nums1[i];
      int index = map.get(cur);
      while (index < nums2.length - 1 && nums2[index + 1] <= cur) {
        index++;
      }
      res[i] = index == nums2.length - 1 ? -1 : nums2[index + 1];
    }
    return res;
  }

  public int[] nextGreaterElementII(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> s = new Stack<>();
    for (int i = nums2.length - 1; i >= 0; i--) {
      int cur = nums2[i];
      while (!s.isEmpty() && s.peek() < cur) {
        s.pop();
      }
      if (s.isEmpty()) {
        map.put(cur, -1);
      } else {
        map.put(cur, s.peek());
      }
      s.push(cur);
    }
    int[] res = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      res[i] = map.get(nums1[i]);
    }
    return res;
  }

  class Solution497 {

    int[][] rects;
    int[] sums;
    Random random = new Random();

    public Solution497(int[][] rects) {
      this.rects = rects;
      this.sums = new int[rects.length];
      sums[0] = (rects[0][2] - rects[0][0] + 1) * (rects[0][3] - rects[0][1] + 1);
      for (int i = 1; i < rects.length; i++) {
        sums[i] = sums[i - 1] + (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
      }
    }

    public int[] pick() {
      int sum = sums[sums.length - 1];
      int index = random.nextInt(sum) + 1;
      int left = 0, right = sums.length - 1;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (sums[mid] >= index) {
          if (mid == 0 || sums[mid - 1] < index) {
            left = mid;
            break;
          } else {
            right = mid - 1;
          }
        } else {
          left = mid + 1;
        }
      }

      int num = index - (left == 0 ? 0 : sums[left - 1]);
      int width = rects[left][2] - rects[left][0] + 1;
      int row = (num - 1) / width;
      int col = (num - 1) % width;
      return new int[] { rects[left][0] + col, rects[left][1] + row };
    }
  }

  public int[] findDiagonalOrder(int[][] mat) {
    int rowMax = mat.length;
    int colMax = mat[0].length;
    int[] res = new int[rowMax * colMax];
    int index = 0, dir = 1, row = 0, col = 0;
    while (index < res.length) {
      if (dir > 0) {
        while (row >= 0 && col < colMax) {
          res[index++] = mat[row][col];
          row--;
          col++;
        }
        if (row == -1 && col == colMax) {
          row = 1 >= rowMax ? 0 : 1;
          col = colMax - 1;
        } else if (row == -1) {
          row = 0;
        } else {
          col = colMax - 1;
          row += 2;
        }
      } else {
        while (row < rowMax && col >= 0) {
          res[index++] = mat[row][col];
          row++;
          col--;
        }
        if (row == rowMax && col == -1) {
          row = rowMax - 1;
          col = 1 >= colMax ? 0 : 1;
        } else if (col == -1) {
          col = 0;
        } else {
          row = rowMax - 1;
          col += 2;
        }
      }
      dir *= -1;
    }
    return res;
  }

  public String findShortestWayRes = null;
  public int findShortestWayResLen = Integer.MAX_VALUE;

  public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
    StringBuilder sb = new StringBuilder();
    findShortestWayBfs(maze, ball[0], ball[1], hole, 0, sb, new HashSet<>());
    return findShortestWayRes == null ? "impossible" : findShortestWayRes;
  }

  public boolean findShortestWayBfs(int[][] maze, int x, int y, int[] hole, int dis, StringBuilder sb,
      Set<Integer> cache) {
    int index = x * maze[0].length + y;
    if (cache.contains(index)) {
      return false;
    }
    cache.add(index);
    boolean find = false;
    for (int i = 0; i < 4; i++) {
      int newX = x;
      int newY = y;
      int tempDis = 0;
      switch (i) {
        case 3:
          for (int j = x; j >= 0; j--) {
            if (maze[j][y] != 1) {
              newX = j;
              if (j == hole[0] && y == hole[1]) {
                break;
              }
            } else {
              break;
            }
          }
          tempDis = Math.abs(newX - x);
          sb.append("u");
          break;
        case 2:
          for (int j = y; j < maze[0].length; j++) {
            if (maze[x][j] != 1) {
              newY = j;
              if (x == hole[0] && j == hole[1]) {
                break;
              }
            } else {
              break;
            }
          }
          tempDis = Math.abs(newY - y);
          sb.append("r");
          break;
        case 0:
          for (int j = x; j < maze.length; j++) {
            if (maze[j][y] != 1) {
              newX = j;
              if (j == hole[0] && y == hole[1]) {
                break;
              }
            } else {
              break;
            }
          }
          tempDis = Math.abs(newX - x);
          sb.append("d");
          break;
        case 1:
          for (int j = y; j >= 0; j--) {
            if (maze[x][j] != 1) {
              newY = j;
              if (x == hole[0] && j == hole[1]) {
                break;
              }
            } else {
              break;
            }
          }
          tempDis = Math.abs(newY - y);
          sb.append("l");
          break;
      }
      dis += tempDis;

      if (newX == hole[0] && newY == hole[1]) {
        if (dis < findShortestWayResLen) {
          findShortestWayResLen = dis;
          findShortestWayRes = sb.toString();
        }
        sb.deleteCharAt(sb.length() - 1);
        dis -= tempDis;
        find = true;
        continue;
      }
      if (findShortestWayBfs(maze, newX, newY, hole, dis, sb, cache)) {
        find = true;
      }
      sb.deleteCharAt(sb.length() - 1);
      dis -= tempDis;
    }
    cache.remove(index);
    return find;
  }

  public String findShortestWayII(int[][] maze, int[] ball, int[] hole) {
    Queue<int[]> q = new LinkedList<>();
    q.add(ball);
    String[][] strs = new String[maze.length][maze[0].length];
    strs[ball[0]][ball[1]] = "";
    int[][] dis = new int[maze.length][maze[0].length];
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[0].length; j++) {
        dis[i][j] = Integer.MAX_VALUE;
      }
    }
    dis[ball[0]][ball[1]] = 0;
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int x = cur[0];
      int y = cur[1];
      int disCur = dis[x][y];
      for (int i = 0; i < 4; i++) {
        int newX = x;
        int newY = y;
        int tempDis = 0;
        switch (i) {
          case 3:
            for (int j = x; j >= 0; j--) {
              if (maze[j][y] != 1) {
                newX = j;
                if (j == hole[0] && y == hole[1]) {
                  break;
                }
              } else {
                break;
              }
            }
            tempDis = Math.abs(newX - x);
            break;
          case 2:
            for (int j = y; j < maze[0].length; j++) {
              if (maze[x][j] != 1) {
                newY = j;
                if (x == hole[0] && j == hole[1]) {
                  break;
                }
              } else {
                break;
              }
            }
            tempDis = Math.abs(newY - y);
            break;
          case 0:
            for (int j = x; j < maze.length; j++) {
              if (maze[j][y] != 1) {
                newX = j;
                if (j == hole[0] && y == hole[1]) {
                  break;
                }
              } else {
                break;
              }
            }
            tempDis = Math.abs(newX - x);
            break;
          case 1:
            for (int j = y; j >= 0; j--) {
              if (maze[x][j] != 1) {
                newY = j;
                if (x == hole[0] && j == hole[1]) {
                  break;
                }
              } else {
                break;
              }
            }
            tempDis = Math.abs(newY - y);
            break;
        }
        String nextStr = strs[x][y] + (i == 3 ? "u" : i == 2 ? "r" : i == 0 ? "d" : "l");
        if (disCur + tempDis < dis[newX][newY]) {
          dis[newX][newY] = disCur + tempDis;
          strs[newX][newY] = nextStr;
          q.add(new int[] { newX, newY });
        } else if (disCur + tempDis == dis[newX][newY]) {
          if (strs[newX][newY].compareTo(nextStr) > 0) {
            dis[newX][newY] = disCur + tempDis;
            strs[newX][newY] = nextStr;
            q.add(new int[] { newX, newY });
          }
        }

      }
    }
    return strs[hole[0]][hole[1]] == null ? "impossible" : strs[hole[0]][hole[1]];
  }

  public String[] findWords(String[] words) {
    int[] indexs = new int[26];
    for (int i = 'a'; i <= 'z'; i++) {
      int index = i - 'a';
      switch (i) {
        case 'a':
        case 's':
        case 'd':
        case 'f':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
          indexs[index] = 1;
          break;
        case 'q':
        case 'w':
        case 'e':
        case 'r':
        case 't':
        case 'y':
        case 'u':
        case 'i':
        case 'o':
        case 'p':
          indexs[index] = 2;
          break;
        case 'z':
        case 'x':
        case 'c':
        case 'v':
        case 'b':
        case 'n':
        case 'm':
          indexs[index] = 3;
          break;
      }
    }
    List<String> res = new ArrayList<>();
    for (String s : words) {
      char[] cs = s.toCharArray();
      char firstChar = cs[0];
      if (firstChar >= 'A' && firstChar <= 'Z') {
        firstChar = (char) (firstChar + 32);
      }
      int index = indexs[firstChar - 'a'];
      for (int i = 1; i < cs.length; i++) {
        char c = cs[i];
        if (c >= 'A' && c <= 'Z') {
          c = (char) (c + 32);
        }
        if (indexs[c - 'a'] != index) {
          index = -1;
          break;
        }
      }
      if (index != -1) {
        res.add(s);
      }
    }
    return res.toArray(new String[0]);
  }

  public int curNum = Integer.MAX_VALUE;
  public int curSum = 0;
  public int prevSum = 0;

  public int[] findMode(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    findMode(root, res);
    if (curSum > prevSum) {
      return new int[] { curNum };
    } else if (curNum == prevSum) {
      res.add(curNum);
    }
    int[] resInt = new int[res.size()];
    for (int i = 0; i < res.size(); i++) {
      resInt[i] = res.get(i);
    }
    return resInt;
  }

  public void findMode(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    findMode(root.left, res);
    if (root.val != curNum) {
      if (curSum > prevSum) {
        res.clear();
        res.add(curNum);
        prevSum = curSum;
      } else if (curSum == prevSum) {
        res.add(curNum);
      }
      curSum = 1;
      curNum = root.val;
    } else {
      curSum++;
    }
    findMode(root.right, res);
  }

}

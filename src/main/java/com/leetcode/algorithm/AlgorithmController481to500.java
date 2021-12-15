package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.leetcode.entity.Robot;

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

}

package com.leetcode.algorithm;

import java.util.Arrays;

import com.leetcode.entity.TreeNode;

public class AlgorithmController561to580 {

  public int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int sum = 0;
    for (int i = 0; i < nums.length; i += 2) {
      sum += nums[i];
    }
    return sum;
  }

  int findTiltResult = 0;

  public int findTilt(TreeNode root) {
    this.findTiltHelp(root);
    return this.findTiltResult;
  }

  public int findTiltHelp(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftSum = this.findTiltHelp(root.left);
    int rightSum = this.findTiltHelp(root.right);
    this.findTiltResult += Math.abs(leftSum - rightSum);
    return leftSum + rightSum + root.val;

  }

  public String nearestPalindromic(String n) {
    long[] numbers = new long[5];
    int len = n.length();
    long numVal = Long.parseLong(n);
    numbers[0] = (long) Math.pow(10, len - 1) - 1;
    numbers[1] = (long) Math.pow(10, len) + 1;

    long prefixLong = Long.parseLong(n.substring(0, (len + 1) / 2));
    for (long i = prefixLong - 1; i <= prefixLong + 1; i++) {
      StringBuilder result = new StringBuilder();
      StringBuilder prefix = new StringBuilder(Long.toString(i));
      result.append(prefix);
      result.append(prefix.reverse().substring(len & 1));
      try {
        numbers[(int) (prefixLong + 1 - i + 2)] = Long.parseLong(result.toString());
      } catch (NumberFormatException ex) {
        continue;
      }
    }
    long result = -1;
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] == numVal) {
        continue;
      }
      if (result == -1 || (Math.abs(numbers[i] - numVal) < Math.abs(result - numVal))
          || (Math.abs(numbers[i] - numVal) == Math.abs(result - numVal) && numbers[i] < result)) {
        result = numbers[i];
      }
    }

    return Long.toString(result);

  }

  public int arrayNesting(int[] nums) {
    int[] status = new int[nums.length];
    int max = -1;
    for (int i = 0; i < nums.length; i++) {
      if (status[i] != 0) {
        continue;
      }
      int count = 0, index = i;
      while (status[i] == 0) {
        index = nums[index];
        status[index] = 1;
        count++;
      }
      max = Math.max(max, count);
    }
    return max;
  }

  public int arrayNesting2(int[] nums) {
    int max = -1;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == -1) {
        continue;
      }
      int count = 0, index = i;
      while (nums[index] != -1) {
        int t = nums[index];
        nums[index] = -1;
        index = t;
        count++;
      }
      max = Math.max(max, count);
    }
    return max;
  }

  public int[][] matrixReshape(int[][] mat, int r, int c) {
    int row = mat.length;
    int col = mat[0].length;
    if (row * col != r * c) {
      return mat;
    }
    int[][] res = new int[r][c];
    int rIndex = 0, cIndex = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        res[rIndex][cIndex] = mat[i][j];
        cIndex++;
        if (cIndex == c) {
          cIndex = 0;
          rIndex++;
        }
      }
    }
    return res;
  }

  public boolean checkInclusion(String s1, String s2) {
    char[] s1char = s1.toCharArray();
    char[] s2char = s2.toCharArray();
    if (s1char.length > s2char.length) {
      return false;
    }
    int[] s1target = new int[26];
    for (int i = 0; i < s1char.length; i++) {
      s1target[s1char[i] - 'a']++;
    }
    int[] s2sub = new int[26];
    int end = s1char.length - 1;
    int start = 0;
    for (int i = 0; i <= end; i++) {
      s2sub[s2char[i] - 'a']++;
    }
    if (Arrays.equals(s1target, s2sub)) {
      return true;
    }
    end++;
    while (end < s2char.length) {
      s2sub[s2char[start] - 'a']--;
      s2sub[s2char[end] - 'a']++;
      if (Arrays.equals(s1target, s2sub)) {
        return true;
      }
      start++;
      end++;
    }
    return false;
  }

  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (isSame(root, subRoot)) {
      return true;
    }
    if (root.left != null && this.isSubtree(root.left, subRoot)) {
      return true;
    }
    if (root.right != null && this.isSubtree(root.right, subRoot)) {
      return true;
    }
    return false;
  }

  public boolean isSame(TreeNode a, TreeNode b) {
    if (a == null && b == null) {
      return true;
    }
    if (a == null || b == null) {
      return false;
    }
    return a.val == b.val && this.isSame(a.left, b.left) && this.isSame(a.right, b.right);
  }

  public int longestLine(int[][] mat) {
    int row = mat.length;
    int col = mat[0].length;
    // 0 left 1 top left 2 top 3 top right
    int max = 0;
    int[][][] dp = new int[row][col][4];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (i == 0) {
          if (j == 0) {
            dp[i][j][0] = mat[i][j];
            dp[i][j][1] = mat[i][j];
            dp[i][j][2] = mat[i][j];
            max = Math.max(max, dp[i][j][0]);
          } else {
            dp[i][j][0] = mat[i][j] == 1 ? dp[i][j - 1][0] + 1 : 0;
            dp[i][j][1] = mat[i][j];
            dp[i][j][2] = mat[i][j];
            dp[i][j][3] = mat[i][j];
            max = Math.max(max, dp[i][j][0]);
          }
          continue;
        }
        if (j == 0) {
          if (mat[i][j] == 1) {
            dp[i][j][0] = 1;
            dp[i][j][2] = dp[i - 1][j][2] + 1;
            if (j + 1 < col) {
              dp[i][j][3] = dp[i - 1][j + 1][3] + 1;
            }
            max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][3]));
            max = Math.max(max, dp[i][j][0]);
          }
          continue;
        }
        if (j == col - 1) {
          if (mat[i][j] == 1) {
            dp[i][j][0] = dp[i][j - 1][0] + 1;
            if (j - 1 >= 0) {
              dp[i][j][1] = dp[i - 1][j - 1][1] + 1;
            }
            dp[i][j][2] = dp[i - 1][j][2] + 1;
            max = Math.max(max, Math.max(dp[i][j][0], Math.max(dp[i][j][1], dp[i][j][2])));
          }
          continue;
        }
        if (mat[i][j] == 1) {
          dp[i][j][0] = dp[i][j - 1][0] + 1;
          if (j - 1 >= 0) {
            dp[i][j][1] = dp[i - 1][j - 1][1] + 1;
          }
          dp[i][j][2] = dp[i - 1][j][2] + 1;
          if (j + 1 < col) {
            dp[i][j][3] = dp[i - 1][j + 1][3] + 1;
          }
          max = Math.max(max, Math.max(dp[i][j][0], Math.max(dp[i][j][1], Math.max(dp[i][j][2], dp[i][j][3]))));
        }
      }
    }
    return max;
  }

  // days[i][j] 代表您在第j个星期在城市i能休假的最长天数。
  // flights[i][j] 代表城市 i 到城市 j 的航空状态
  public int maxVacationDays(int[][] flights, int[][] days) {
    int n = flights.length;
    int k = days[0].length;
    int[][] dp = new int[n][k];
    for (int i = 0; i < n; i++) {
      if (i == 0 || flights[0][i] == 1) {
        dp[i][0] = days[i][0] + 1;
      }
    }
    for (int i = 1; i < k; i++) {
      for (int j = 0; j < n; j++) {
        for (int z = 0; z < n; z++) {
          if ((j == z || flights[z][j] == 1) && dp[z][i - 1] > 0) {
            dp[j][i] = Math.max(dp[z][i - 1] + days[j][i], dp[j][i]);
          }
        }
      }
    }
    int max = 0;
    for (int i = 0; i < n; i++) {
      max = Math.max(dp[i][k - 1], max);
    }
    return max - 1;
  }

  public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
    int result = 0;
    for (int i = 0; i < nuts.length; i++) {
      result += (Math.abs(nuts[i][0] - tree[0]) + Math.abs(nuts[i][1] - tree[1])) * 2;
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < nuts.length; i++) {
      int distance_tree = Math.abs(nuts[i][0] - tree[0]) + Math.abs(nuts[i][1] - tree[1]);
      int distance_squirrel = Math.abs(nuts[i][0] - squirrel[0]) + Math.abs(nuts[i][1] - squirrel[1]);
      int yes = distance_squirrel + distance_tree;
      int no = distance_tree * 2;
      min = Math.min(min, result - no + yes);
    }
    return min;
  }

  public int minDistance2(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
    int min = Integer.MAX_VALUE;
    int result = 0;
    for (int i = 0; i < nuts.length; i++) {
      int distance_tree = Math.abs(nuts[i][0] - tree[0]) + Math.abs(nuts[i][1] - tree[1]);
      result += distance_tree * 2;
      int distance_squirrel = Math.abs(nuts[i][0] - squirrel[0]) + Math.abs(nuts[i][1] - squirrel[1]);
      int t = distance_squirrel - distance_tree;
      min = Math.min(min, t);
    }
    return result + min;
  }

}

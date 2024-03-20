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

}

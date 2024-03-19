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

}

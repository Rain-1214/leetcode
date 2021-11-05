package com.leetcode.algorithm;

import java.util.Arrays;

public class AlgorithmController461to480 {
  public int hammingDistance(int x, int y) {
    int i = x ^ y, res = 0;
    while (i > 0) {
      res++;
      i &= i - 1;
    }
    return res;
  }

  public int minMoves2(int[] nums) {
    Arrays.sort(nums);
    int res = 0;
    for (int num : nums) {
      res += Math.abs(nums[nums.length / 2] - num);
    }
    return res;
  }

  public int minMoves2II(int[] nums) {
    int mid = minMovesHelp(nums, 0, nums.length - 1, nums.length / 2);
    int res = 0;
    for (int num : nums) {
      res += Math.abs(mid - num);
    }
    return res;
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public int minMovesHelp(int[] nums, int left, int right, int target) {
    if (left == right) {
      return nums[left];
    }
    int temp = nums[right];
    int i = left, index = left;
    while (index < right) {
      if (nums[index] < temp) {
        swap(nums, i, index);
        i++;
      }
      index++;
    }
    swap(nums, i, right);
    if (i == target) {
      return nums[i];
    } else if (i > target) {
      return minMovesHelp(nums, left, i - 1, target);
    } else {
      return minMovesHelp(nums, i + 1, right, target);
    }
  }
}

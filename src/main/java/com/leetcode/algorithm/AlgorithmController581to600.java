package com.leetcode.algorithm;

public class AlgorithmController581to600 {

  public int findUnsortedSubarray(int[] nums) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int right = -1;
    int left = -1;
    for (int i = 0; i < nums.length; i++) {
      int left_item = nums[i];
      if (left_item >= max) {
        max = left_item;
      } else {
        right = i;
      }
      int right_item = nums[nums.length - 1 - i];
      if (right_item <= min) {
        min = right_item;
      } else {
        left = nums.length - 1 - i;
      }
    }
    return right == -1 ? 0 : right - left + 1;
  }
}

package com.leetcode.algorithm;

public class AlgorithmController81to100 {

  public boolean search(int[] nums, int target) {
    return searchImpl(0, nums.length - 1, nums, target);
  }

  public boolean searchImpl(int start, int end, int[] nums, int target) {
    if (end < start) {
      return false;
    }
    int mid = (start + end) / 2;
    if (nums[mid] == target) {
      return true;
    }
    if (nums[mid] == nums[start] && nums[mid] == nums[end]) {
      return searchImpl(start + 1, end - 1, nums, target);
    } else if (nums[end] >= nums[mid]) {
      if (target > nums[mid] && target <= nums[end]) {
        return searchImpl(mid + 1, end, nums, target);
      } else {
        return searchImpl(start, mid - 1, nums, target);
      }
    } else {
      if (target >= nums[start] && target < nums[mid]) {
        return searchImpl(start, mid - 1, nums, target);
      } else {
        return searchImpl(mid + 1, end, nums, target);
      }
    }
  }

}

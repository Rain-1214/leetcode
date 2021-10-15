package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgorithmController441to460 {
  public int arrangeCoins(int n) {
    int left = 0, right = n;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      long count = arrangeCoinsHelp(mid);
      if (count == n) {
        return mid;
      }
      if (count > n) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return left - 1;
  }

  public long arrangeCoinsHelp(int n) {
    return (1 + (long) n) * n / 2;
  }

  public List<Integer> findDuplicates(int[] nums) {
    Set<Integer> set = new HashSet<Integer>();
    List<Integer> result = new ArrayList<Integer>();
    for (int n : nums) {
      if (set.contains(n)) {
        result.add(n);
      }
      set.add(n);
    }
    return result;
  }

  public List<Integer> findDuplicatesII(int[] nums) {
    List<Integer> result = new ArrayList<Integer>();
    for (int n : nums) {
      nums[(n - 1) % nums.length] += nums.length;
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 2 * nums.length) {
        result.add(i + 1);
      }
    }
    return result;
  }

}

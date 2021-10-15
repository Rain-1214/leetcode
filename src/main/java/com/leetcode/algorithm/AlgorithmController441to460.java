package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
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

  public int compress(char[] chars) {
    if (chars.length == 1) {
      return 1;
    }
    int left = 0, right = 1, index = 0;
    while (left < chars.length) {
      char lt = chars[left];
      while (right < chars.length && chars[right] == lt) {
        right++;
      }
      int num = right - left;
      chars[index++] = lt;
      if (num != 1) {
        int temp = index;
        while (num != 0) {
          int tempNum = num % 10;
          chars[index++] = (char) (tempNum + '0');
          num /= 10;
        }
        swap(chars, temp, index - 1);
      }
      left = right;
      right = left + 1;
    }
    return index;
  }

  public void swap(char[] a, int left, int right) {
    while (left < right) {
      char temp = a[left];
      a[left] = a[right];
      a[right] = temp;
      left++;
      right--;
    }
  }

}

package com.leetcode.algorithm;

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

}

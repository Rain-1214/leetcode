package com.leetcode.algorithm;

public class AlgorithmController121to140 {
  public int maxProfit(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }
    int minBuy = prices[0];
    int res = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > minBuy) {
        res = Math.max(prices[i] - minBuy, res);
      } else {
        minBuy = prices[i];
      }
    }
    return res;
  }

  public int maxProfit2(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }
    int res = 0;
    for (int i = 0; i < prices.length - 1; i++) {
      if (prices[i + 1] > prices[i]) {
        res += prices[i + 1] - prices[i];
      }
    }
    return res;
  }

}

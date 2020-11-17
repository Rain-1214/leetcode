package com.leetcode.algorithm;

import com.leetcode.entity.TreeNode;
import com.leetcode.tool.Print;

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

  public int maxProfit3TooSlow(int[] prices) {
    if (prices == null || prices.length < 1) {
      return 0;
    }
    int[] c = new int[3];
    int[] g = new int[3];
    for (int i = 0; i < prices.length - 1; i++) {
      int t = prices[i + 1] - prices[i];
      for (int y = c.length - 1; y > 0; y--) {
        c[y] = Math.max(g[y - 1] + (t > 0 ? t : 0), c[y] + t);
        g[y] = Math.max(c[y], g[y]);
      }
    }
    return g[2];
  }

  public int maxProfit3(int[] prices) {
    if (prices == null || prices.length < 1) {
      return 0;
    }
    int buy1 = Integer.MIN_VALUE;
    int sell1 = 0;
    int buy2 = Integer.MIN_VALUE;
    int sell2 = 0;

    for (int i = 0; i < prices.length; i++) {
      buy1 = Math.max(buy1, -prices[i]);
      sell1 = Math.max(sell1, buy1 + prices[i]);
      buy2 = Math.max(buy2, sell1 - prices[i]);
      sell2 = Math.max(sell2, buy2 + prices[i]);
    }

    return sell2;

  }

  public int maxPath = 0;

  public int maxPathSum(TreeNode root) {
   maxPathSumImpl(root);
   return this.maxPath;
  }

  public int maxPathSumImpl(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = Math.max(maxPathSumImpl(root.left), 0);
    int right = Math.max(maxPathSumImpl(root.right), 0);
    this.maxPath = Math.max(Math.max(left, right) + root.val, this.maxPath);
    return Math.max(left, right) + root.val;
  }

}

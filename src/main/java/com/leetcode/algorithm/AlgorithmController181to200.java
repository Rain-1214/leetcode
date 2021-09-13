package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.leetcode.entity.TreeNode;

public class AlgorithmController181to200 {
  public void reverseWords(char[] s) {
    reverseWordsHelp(s, 0, s.length - 1);
    int prevIndex = 0;
    for (int i = 0; i < s.length; i++) {
      if (s[i] == ' ') {
        reverseWordsHelp(s, prevIndex, i - 1);
        prevIndex = i + 1;
      }
    }
    reverseWordsHelp(s, prevIndex, s.length - 1);
  }

  public void reverseWordsHelp(char[] s, int left, int right) {
    while (left < right) {
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;
      left++;
      right--;
    }
  }

  public List<String> findRepeatedDnaSequences(String s) {
    List<String> res = new ArrayList<>();
    if (s.length() < 11) {
      return res;
    }
    Set<String> cache = new HashSet<>();
    for (int i = 0; i <= s.length() - 10; i++) {
      String t = s.substring(i, i + 10);
      if (cache.contains(t) && !res.contains(t)) {
        res.add(t);
      } else {
        cache.add(t);
      }
    }
    return res;
  }

  public int maxProfit(int k, int[] prices) {
    if (prices.length < 2) {
      return 0;
    }
    int n = prices.length;

    if (k >= n / 2) {
      return maxProfit(prices);
    }

    k = Math.min(n / 2, k);

    int[] buy = new int[k + 1];
    int[] sell = new int[k + 1];
    buy[0] = -prices[0];
    for (int i = 1; i <= k; i++) {
      buy[i] = sell[i] = Integer.MIN_VALUE / 2;
    }

    for (int i = 1; i < prices.length; i++) {
      int p = prices[i];
      buy[0] = Math.max(buy[0], -p);
      for (int j = 1; j <= k; j++) {
        buy[j] = Math.max(buy[j], sell[j] - p);
        sell[j] = Math.max(sell[j], buy[j - 1] + p);
      }
    }
    return sell[k];
  }

  public int maxProfit(int[] prices) {
    int res = 0;
    for (int i = 0; i < prices.length - 1; i++) {
      if (prices[i + 1] > prices[i]) {
        res += prices[i + 1] - prices[i];
      }
    }
    return res;
  }

  public void rotateTooSlow(int[] nums, int k) {
    int len = nums.length;
    if (k % len == 0 || len < 2) {
      return;
    }
    k = k % len;
    for (int i = 0; i < k; i++) {
      rotate(nums);
    }
  }

  public void rotate(int[] nums) {
    int len = nums.length;
    int prev = nums[len - 1];
    for (int i = 0; i < len; i++) {
      int t = nums[i];
      nums[i] = prev;
      prev = t;
    }
  }

  public void rotateTooSlowII(int[] nums, int k) {
    int len = nums.length;
    if (k % len == 0 || len < 2) {
      return;
    }
    k = k % len;
    int[] temp = new int[k];
    for (int i = 0; i < k; i++) {
      temp[i] = nums[len - k + i];
    }
    int j = 0;
    for (int i = 0; i < len; i++) {
      int t = nums[i];
      nums[i] = temp[j];
      temp[j++] = t;
      if (j >= k) {
        j = 0;
      }
    }
  }

  public void rotate(int[] nums, int k) {
    if (nums.length < 2) {
      return;
    }
    reverse(nums, 0, nums.length - 1);
    k = k % nums.length;
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  public void reverse(int[] nums, int left, int right) {
    while (left < right) {
      int t = nums[left];
      nums[left++] = nums[right];
      nums[right--] = t;
    }
  }

  public int reverseBits(int n) {
    int res = 0;
    int i = 32;
    while (i > 0) {
      res <<= 1;
      res = 1 & n | res;
      n >>= 1;
      i--;
    }
    return res;
  }

  public int hammingWeight(int n) {
    int res = 0;
    int i = 32;
    while (i-- > 0) {
      if ((1 & n) == 1) {
        res++;
      }
      n >>= 1;
    }
    return res;
  }

  public int rob(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int[] dp = new int[nums.length + 2];
    for (int i = 2; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 2] + nums[i - 2], dp[i - 1]);
    }
    return dp[dp.length - 1];
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      int len = q.size();
      for (int i = 0; i < len; i++) {
        TreeNode temp = q.poll();
        if (i == len - 1) {
          res.add(temp.val);
        }
        if (temp.left != null) {
          q.add(temp.left);
        }
        if (temp.right != null) {
          q.add(temp.right);
        }
      }
    }
    return res;
  }

  public int numIslands(char[][] grid) {
    int res = 0;
    int yMax = grid.length;
    int xMax = grid[0].length;
    for (int y = 0; y < yMax; y++) {
      for (int x = 0; x < xMax; x++) {
        if (grid[y][x] == '1') {
          res++;
          turnToLand(grid, x, y);
        }
      }
    }
    return res;
  }

  public void turnToLand(char[][] grid, int x, int y) {
    int yMax = grid.length - 1;
    int xMax = grid[0].length - 1;
    grid[y][x] = 'm';
    if (y > 0 && grid[y - 1][x] == '1') {
      turnToLand(grid, x, y - 1);
    }
    if (y < yMax && grid[y + 1][x] == '1') {
      turnToLand(grid, x, y + 1);
    }
    if (x > 0 && grid[y][x - 1] == '1') {
      turnToLand(grid, x - 1, y);
    }
    if (x < xMax && grid[y][x + 1] == '1') {
      turnToLand(grid, x + 1, y);
    }
  }

}

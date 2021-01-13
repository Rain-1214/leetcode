package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leetcode.tool.Print;

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

}

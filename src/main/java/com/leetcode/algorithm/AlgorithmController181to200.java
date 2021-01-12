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

}

package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}

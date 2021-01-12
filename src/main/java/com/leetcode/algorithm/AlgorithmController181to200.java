package com.leetcode.algorithm;

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
}

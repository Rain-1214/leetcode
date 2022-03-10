package com.leetcode.algorithm;

public class AlgorithmController541to560 {

  public String reverseStr(String s, int k) {
    char[] sc = s.toCharArray();
    for (int i = 0; i < sc.length; i += 2 * k) {
      int end = Math.min(i + k - 1, sc.length - 1);
      reverse(sc, i, end);
    }
    return String.valueOf(sc);
  }

  public char[] reverse(char[] sc, int left, int right) {
    while (left < right) {
      char temp = sc[left];
      sc[left] = sc[right];
      sc[right] = temp;
      left++;
      right--;
    }
    return sc;
  }

}

package com.leetcode.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AlgorithmController521to540 {

  public int findLUSlength(String a, String b) {
    char[] ac = a.toCharArray();
    char[] bc = b.toCharArray();
    if (ac.length != bc.length) {
      return Math.max(ac.length, bc.length);
    }
    int i = 0;
    while (i < ac.length) {
      if (ac[i] != bc[i]) {
        return Math.max(ac.length, bc.length);
      }
      i++;
    }
    return -1;
  }

  public int findLUSlength522(String[] strs) {
    Arrays.sort(strs, (a, b) -> b.length() - a.length());
    for (int i = 0; i < strs.length; i++) {
      String source = strs[i];
      boolean flag = true;
      for (int j = 0; j < strs.length; j++) {
        if (i == j) {
          continue;
        }
        if (strs[j].length() < source.length()) {
          break;
        }
        if (isSubString(source, strs[j])) {
          flag = false;
          break;
        }
      }
      if (flag) {
        return source.length();
      }
    }
    return -1;
  }

  public boolean isSubString(String source, String target) {
    int si = 0;
    for (int ti = 0; ti < target.length() && si < source.length(); ti++) {
      if (source.charAt(si) == target.charAt(ti)) {
        si++;
      }
    }
    return si == source.length();
  }

}

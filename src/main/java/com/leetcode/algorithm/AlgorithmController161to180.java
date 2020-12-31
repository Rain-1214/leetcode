package com.leetcode.algorithm;

public class AlgorithmController161to180 {

  public boolean isOneEditDistanceTooSlow(String s, String t) {
    int sLen = s.length();
    int tLen = t.length();
    StringBuilder ssb = new StringBuilder(s);
    StringBuilder tsb = new StringBuilder(t);
    for (int i = 0; i < Math.max(sLen, tLen); i++) {
      char cs = i >= sLen ? '#' : ssb.charAt(i);
      char ct = i >= tLen ? '#' : tsb.charAt(i);
      if (cs != ct) {
        if (sLen == tLen) {
          ssb.replace(i, i + 1, Character.toString(ct));
          return ssb.toString().equals(tsb.toString());
        } else if (sLen - tLen == 1) {
          ssb.delete(i, i + 1);
          return ssb.toString().equals(tsb.toString());
        } else if (tLen - sLen == 1) {
          ssb.insert(i, Character.toString(ct));
          return ssb.toString().equals(tsb.toString());
        } else {
          return false;
        }
      }
    }
    return false;
  }

  public boolean isOneEditDistance(String s, String t) {
    char[] sa = s.toCharArray();
    char[] ta = t.toCharArray();
    if (s.equals(t) || Math.abs(sa.length - ta.length) > 1) {
      return false;
    }
    int diff = 0;
    int si = 0, ti = 0;
    while (si < sa.length && ti < ta.length) {
      if (sa[si] != ta[ti]) {
        diff++;
        if (diff > 1) {
          return false;
        }
        if (sa.length > ta.length) {
          si++;
        } else if (ta.length > sa.length) {
          ti++;
        } else {
          si++;
          ti++;
        }
      } else {
        si++;
        ti++;
      }
    }
    return true;
  }

  public int findPeakElement(int[] nums) {
    int prev = nums[0] - 1;
    int next = nums[0] - 1;
    for (int i = 0; i < nums.length; i++) {
      prev = i == 0 ? nums[0] - 1 : nums[i - 1];
      next = i == nums.length - 1 ? nums[i] - 1 : nums[i + 1];
      if (nums[i] > prev && nums[i] > next) {
        return i;
      }
    }
    return 0;
  }

}

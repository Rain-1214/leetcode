package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> res = new ArrayList<>();
    for (int num : nums) {
      if (num > lower) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(lower));
        if (num - 1 > lower) {
          sb.append("->");
          sb.append(Integer.toString(num - 1));
        }
        res.add(sb.toString());
      }
      if (num >= upper) {
        return res;
      }
      lower = num + 1;
    }
    if (upper >= lower) {
      StringBuilder sb = new StringBuilder();
      sb.append(Integer.toString(lower));
      if (upper > lower) {
        sb.append("->");
        sb.append(Integer.toString(upper));
      }
      res.add(sb.toString());
    }
    return res;
  }

  public int maximumGap(int[] nums) {
    if (nums.length < 2) {
      return 0;
    }
    int len = nums.length;
    int max = Arrays.stream(nums).max().getAsInt();
    int min = Arrays.stream(nums).min().getAsInt();
    int distance = Math.max(1, (max - min) / (len - 1));
    int size = (max - min) / distance + 1;
    int[][] buckets = new int[size][2];
    for (int[] bucket : buckets) {
      Arrays.fill(bucket, -1);
    }
    for (int num : nums) {
      int temp = (num - min) / distance;
      if (buckets[temp][0] == -1) {
        buckets[temp][0] = num;
        buckets[temp][1] = num;
      } else {
        buckets[temp][0] = Math.min(buckets[temp][0], num);
        buckets[temp][1] = Math.max(buckets[temp][1], num);
      }
    }
    int res = 0;
    int[] prev = null;
    for (int[] bucket : buckets) {
      if (bucket[0] == -1) {
        continue;
      }
      if (prev != null) {
        res = Math.max(res, bucket[0] - prev[1]);
      }
      prev = bucket;
    }
    return res;
  }
}

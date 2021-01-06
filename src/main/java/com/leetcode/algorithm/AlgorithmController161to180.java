package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public int compareVersionTooSlow(String version1, String version2) {
    if (version2 == null || version2.length() == 0) {
      return 1;
    }
    String[] v1Arr = version1.split("\\.");
    String[] v2Arr = version2.split("\\.");
    for (int i = 0; i < Math.max(v1Arr.length, v2Arr.length); i++) {
      String s1 = i >= v1Arr.length ? "0" : v1Arr[i];
      String s2 = i >= v2Arr.length ? "0" : v2Arr[i];
      if (s1.equals(s2)) {
        continue;
      } else {
        int s1i = s1.length() - 1;
        int s2i = s2.length() - 1;
        int res = 0;
        while (s1i >= 0 || s2i >= 0) {
          char s1a = s1i >= 0 ? s1.charAt(s1i--) : '0';
          char s2a = s2i >= 0 ? s2.charAt(s2i--) : '0';
          if (s1a == s2a) {
            continue;
          }
          if (s1a > s2a) {
            res = 1;
          } else {
            res = -1;
          }
        }
        if (res != 0) {
          return res;
        }
      }
    }
    return 0;
  }

  public int compareVersion(String version1, String version2) {
    int len1 = version1.length();
    int len2 = version2.length();
    int i = 0, j = 0;
    while (i < len1 || j < len2) {
      int v1 = 0, v2 = 0;
      while (i < len1 && version1.charAt(i) != '.') {
        v1 = v1 * 10 + version1.charAt(i) - '0';
        i++;
      }
      while (j < len2 && version2.charAt(j) != '.') {
        v2 = v2 * 10 + version2.charAt(j) - '0';
        j++;
      }
      if (v1 != v2) {
        return v1 > v2 ? 1 : -1;
      }
      i++;
      j++;
    }
    return 0;
  }

  public String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    if (numerator < 0 && denominator > 0 || numerator >= 0 && denominator < 0) {
      sb.append("-");
    }
    long n = Math.abs(Long.valueOf(numerator));
    long d = Math.abs(Long.valueOf(denominator));
    sb.append(String.valueOf(n / d));
    long mod = n % d;
    if (mod == 0) {
      return sb.toString();
    }
    sb.append(".");
    Map<Long, Integer> loop = new HashMap<>();
    while (mod != 0) {
      if (loop.containsKey(mod)) {
        sb.insert(loop.get(mod), "(");
        sb.append(")");
        break;
      }
      loop.put(mod, sb.length());
      mod *= 10;
      sb.append(String.valueOf(mod / d));
      mod %= d;
    }
    return sb.toString();
  }

  public int[] twoSumTooSlow(int[] numbers, int target) {
    int[] res = new int[2];
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < numbers.length; i++) {
      if (map.containsKey(numbers[i])) {
        res[0] = map.get(numbers[i]) + 1;
        res[1] = i + 1;
        return res;
      }
      map.put(target - numbers[i], i);
    }
    return res;
  }

  public int[] twoSum(int[] numbers, int target) {
    int[] res = new int[2];
    int i = 0;
    int j = numbers.length - 1;
    while (i < j) {
      int sum = numbers[i] + numbers[j];
      if (sum > target) {
        j--;
      } else if (target > sum) {
        i++;
      } else {
        res[0] = i + 1;
        res[1] = j + 1;
        break;
      }
    }
    return res;
  }

  public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      n--;
      sb.append((char) n / 26 + 'A');
      n /= 26;
    }
    return sb.reverse().toString();
  }

}

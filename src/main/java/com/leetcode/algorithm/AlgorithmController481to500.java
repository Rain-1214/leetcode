package com.leetcode.algorithm;

public class AlgorithmController481to500 {

  public int magicalString(int n) {
    if (n <= 3) {
      return 1;
    }
    int[] temp = new int[n];
    temp[0] = 1;
    temp[1] = 2;
    temp[2] = 2;
    int left = 2, right = 3, count = 1;
    while (right < n) {
      if (temp[left] == 1) {
        temp[right] = temp[right - 1] == 1 ? 2 : 1;
        count += temp[right] == 1 ? 1 : 0;
      } else if (temp[left] == 2) {
        temp[right] = temp[right - 1] == 1 ? 2 : 1;
        count += temp[right] == 1 ? 1 : 0;
        if (right + 1 < n) {
          temp[right + 1] = temp[right];
          count += temp[right + 1] == 1 ? 1 : 0;
        }
        right++;
      }
      right++;
      left++;
    }
    return count;
  }

  public String licenseKeyFormatting(String s, int k) {
    char[] chars = s.toCharArray();
    StringBuilder sb = new StringBuilder();
    int temp = 0;
    for (int i = chars.length - 1; i >= 0; i--) {
      if (chars[i] == '-') {
        continue;
      }
      char c = chars[i];
      if (c >= 'a' && c <= 'z') {
        c -= 32;
      }
      sb.append(c);
      temp++;
      if (temp == k) {
        sb.append('-');
        temp = 0;
      }
    }
    if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.reverse().toString();
  }

  public String smallestGoodBase(String n) {
    long num = Long.parseLong(n);
    int mMax = (int) (Math.log(num) / Math.log(2));
    for (int m = mMax; m >= 2; m--) {
      int k = (int) Math.pow(num, 1.0 / m);
      long temp = 1, sum = 1;
      for (int i = 0; i < m; i++) {
        temp *= k;
        sum += temp;
      }
      if (sum == num) {
        return String.valueOf(k);
      }
    }
    return String.valueOf(num - 1);
  }

  public int[] findPermutation(String s) {
    char[] sc = s.toCharArray();
    int[] res = new int[sc.length + 1];
    for (int i = 0; i <= sc.length; i++) {
      res[i] = i + 1;
    }
    int left = 0, right = 0;
    while (left < sc.length) {
      if (sc[left] == 'I') {
        left++;
        continue;
      }
      while (right < sc.length && sc[right] == 'D') {
        right++;
      }
      int l = left, r = right;
      while (l < r) {
        swap(res, l, r);
        l++;
        r--;
      }
      left = right + 1;
      right = left;
    }
    return res;
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int res = 0, count = 0;
    for (int n : nums) {
      if (n == 1) {
        count++;
      } else {
        res = Math.max(res, count);
        count = 0;
      }
    }
    return Math.max(res, count);
  }

}

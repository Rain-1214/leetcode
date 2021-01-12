package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.leetcode.entity.TreeNode;
import com.leetcode.tool.Print;

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
      sb.append((char) n % 26 + 'A');
      n /= 26;
    }
    return sb.reverse().toString();
  }

  public int majorityElementTooSlow(int[] nums) {
    int max = 0;
    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int cm = map.getOrDefault(nums[i], 0) + 1;
      map.put(nums[i], cm);
      if (cm > max) {
        max = cm;
        res = nums[i];
      }
    }
    return res;
  }

  public int majorityElementTooSlowII(int[] nums) {
    Arrays.sort(nums);
    return nums[nums.length / 2];
  }

  public int majorityElement(int[] nums) {
    int max = 0;
    int val = 0;
    for (int num : nums) {
      if (max == 0) {
        val = num;
      }
      max += val == num ? 1 : -1;
    }
    return val;
  }

  class TwoSumII {

    public Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public TwoSumII() {
      this.map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
      map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
      for (Map.Entry<Integer, Integer> me : map.entrySet()) {
        int target = value - me.getKey();
        if (map.containsKey(target)) {
          if (target == me.getKey()) {
            if (me.getValue() > 1) {
              return true;
            }
          } else {
            return true;
          }
        }
      }
      return false;
    }
  }

  class TwoSum {

    public LinkedList<Integer> list;
    public int min = Integer.MAX_VALUE;
    public int max = Integer.MIN_VALUE;

    /** Initialize your data structure here. */
    public TwoSum() {
      list = new LinkedList<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
      list.add(number);
      min = Math.min(min, number);
      max = Math.max(max, number);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
      if (value < min * 2 || value > max * 2) {
        return false;
      }
      Set<Integer> set = new HashSet<>();
      for (int n : list) {
        if (set.contains(value - n)) {
          return true;
        }
        set.add(n);
      }
      return false;
    }
  }

  public int titleToNumber(String s) {
    char[] sc = s.toCharArray();
    int multiple = 0;
    int res = 0;
    for (int i = sc.length - 1; i >= 0; i--) {
      res += Math.pow(26, multiple) * (sc[i] - 'A' + 1);
      multiple++;
    }
    return res;
  }

  public int trailingZeroes(int n) {
    int zeroNum = 0;
    while (n > 0) {
      n /= 5;
      zeroNum += n;
    }
    return zeroNum;
  }

  class BSTIterator {

    public Queue<TreeNode> q = new LinkedList<>();

    public void loop(TreeNode root) {
      if (root == null) {
        return;
      }
      loop(root.left);
      q.add(root);
      loop(root.right);
    }

    public BSTIterator(TreeNode root) {
      this.loop(root);
    }

    public int next() {
      return q.poll().val;
    }

    public boolean hasNext() {
      return q.peek() != null;
    }
  }

  public int calculateMinimumHP(int[][] dungeon) {
    int x = dungeon[0].length;
    int y = dungeon.length;
    int[][] dp = new int[y][x];
    dp[y - 1][x - 1] = dungeon[y - 1][x - 1] >= 0 ? 1 : 1 - dungeon[y - 1][x - 1];
    for (int i = y - 1; i >= 0; i--) {
      for (int j = x - 1; j >= 0; j--) {
        if (i == y - 1 && j == x - 1) {
          continue;
        }
        int down = i + 1 > y - 1 ? Integer.MAX_VALUE : dp[i + 1][j];
        int right = j + 1 > x - 1 ? Integer.MAX_VALUE : dp[i][j + 1];
        if (dungeon[i][j] >= Math.min(down, right)) {
          dp[i][j] = 1;
        } else {
          dp[i][j] = Math.min(down, right) - dungeon[i][j];
        }
      }
    }
    return dp[0][0];
  }

  public String largestNumberLow(int[] nums) {
    String[] numsStr = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      numsStr[i] = Integer.toString(nums[i]);
    }

    Arrays.sort(numsStr, (a, b) -> {
      String t1 = a + b;
      String t2 = b + a;
      return t2.compareTo(t1);
    });

    if (numsStr[0].equals("0")) {
      return "0";
    }

    StringBuilder sb = new StringBuilder();
    for (String s : numsStr) {
      sb.append(s);
    }
    return sb.toString();
  }

  public String largestNumber(int[] nums) {
    largestNumberSort(nums, 0, nums.length - 1);
    if (nums[0] == 0) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < nums.length; i++) {
      sb.append(String.valueOf(nums[i]));
    }
    return sb.toString();
  }

  public void largestNumberSort(int[] nums, int start, int end) {
    if (start >= end) {
      return;
    }
    int base = nums[start];
    int index = start + 1;
    for (int i = start + 1; i <= end; i++) {
      if (large(nums[i], base)) {
        int temp = nums[i];
        nums[i] = nums[index];
        nums[index++] = temp;
      }
    }
    nums[start] = nums[index - 1];
    nums[index - 1] = base;
    largestNumberSort(nums, start, index - 2);
    largestNumberSort(nums, index, end);
  }

  public boolean large(int a, int b) {
    String as = String.valueOf(a);
    String bs = String.valueOf(b);
    int i = 0;
    int j = 0;
    while (true) {
      char ac = as.charAt(i++);
      char bc = bs.charAt(j++);
      if (ac != bc) {
        return ac > bc;
      }
      if (i >= as.length() && j >= bs.length()) {
        return false;
      } else if (i >= as.length()) {
        i = 0;
      } else if (j >= bs.length()) {
        j = 0;
      }
    }
  }

}

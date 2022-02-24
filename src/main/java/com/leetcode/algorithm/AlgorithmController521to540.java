package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

  public boolean checkSubarraySum(int[] nums, int k) {
    int len = nums.length;
    if (len < 2) {
      return false;
    }
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int sum = 0;
    for (int i = 0; i < len; i++) {
      sum = sum + nums[i];
      int temp = sum % k;
      if (map.containsKey(temp)) {
        if (i - map.get(temp) > 1) {
          return true;
        }
      } else {
        map.put(temp, i);
      }
    }
    return false;
  }

  public String findLongestWord(String s, List<String> dictionary) {
    dictionary.sort((a, b) -> {
      if (a.length() == b.length()) {
        return a.compareTo(b);
      }
      return b.length() - a.length();
    });
    char[] sc = s.toCharArray();
    for (String word : dictionary) {
      int i = 0;
      for (int j = 0; j < sc.length; j++) {
        if (i < word.length() && sc[j] == word.charAt(i)) {
          i++;
        }
      }
      if (i == word.length()) {
        return word;
      }
    }
    return "";
  }

  public int findMaxLength(int[] nums) {
    if (nums.length < 2) {
      return 0;
    }
    int[] preZero = new int[nums.length];
    int[] preOne = new int[nums.length];
    int max = 0;
    preZero[0] = nums[0] == 0 ? 1 : 0;
    preOne[0] = nums[0] == 1 ? 1 : 0;
    for (int i = 1; i < nums.length; i++) {
      preZero[i] = nums[i] == 0 ? preZero[i - 1] + 1 : preZero[i - 1];
      preOne[i] = nums[i] == 1 ? preOne[i - 1] + 1 : preOne[i - 1];
    }
    for (int i = 1; i < nums.length; i++) {
      int current = nums[i];
      for (int j = 0; j < i; j++) {
        int zeros = preZero[i - 1] - preZero[j];
        int ones = preOne[i - 1] - preOne[j];
        if (j == 0) {
          zeros = preZero[i - 1];
          ones = preOne[i - 1];
        }
        zeros += current == 0 ? 1 : 0;
        ones += current == 1 ? 1 : 0;
        if (zeros == 0 || ones == 0) {
          break;
        }
        if (zeros + ones < max) {
          break;
        }
        if (zeros == ones) {
          max = Math.max(max, zeros + ones);
        }
      }
    }
    return max;
  }

  public int findMaxLengthII(int[] nums) {
    if (nums.length < 2) {
      return 0;
    }
    int max = 0, counter = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    for (int i = 0; i < nums.length; i++) {
      counter += nums[i] == 0 ? -1 : 1;
      if (map.containsKey(counter)) {
        max = Math.max(max, i - map.get(counter));
      } else {
        map.put(counter, i);
      }
    }
    return max;
  }

  int countArrangementRes = 0;
  List<Integer>[] countArrangementList;
  boolean[] countArrangementVisited;

  public int countArrangement(int n) {
    countArrangementList = new List[n + 1];
    countArrangementVisited = new boolean[n + 1];
    for (int i = 0; i <= n; i++) {
      countArrangementList[i] = new ArrayList<>();
    }
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (i % j == 0 || j % i == 0) {
          countArrangementList[i].add(j);
        }
      }
    }
    countArrangement(n, 1);
    return countArrangementRes;
  }

  public void countArrangement(int n, int index) {
    if (index == n + 1) {
      countArrangementRes++;
      return;
    }
    for (int num : countArrangementList[index]) {
      if (!countArrangementVisited[num]) {
        countArrangementVisited[num] = true;
        countArrangement(n, index + 1);
        countArrangementVisited[num] = false;
      }
    }
  }

  public List<String> wordsAbbreviation(List<String> words) {
    String[] sa = new String[words.size()];
    for (int i = 0; i < words.size(); i++) {
      sa[i] = words.get(i);
    }
    List<String> res = new ArrayList<>();
    for (int i = 0; i < words.size(); i++) {
      res.add(null);
    }
    int preLen = 0;
    Map<String, Integer> map = new HashMap<>();
    while (true) {
      boolean flag = true;
      for (int i = 0; i < sa.length; i++) {
        String s = sa[i];
        if (s == null) {
          continue;
        }
        flag = false;
        String abbr = minimalString(s, preLen);
        if (map.containsKey(abbr)) {
          if (sa[map.get(abbr)] == null) {
            int index = map.get(abbr);
            sa[index] = words.get(index);
          }
        } else {
          map.put(abbr, i);
          res.set(i, abbr);
          sa[i] = null;
        }
      }
      if (flag) {
        break;
      }
      preLen++;
      map.clear();
    }
    return res;
  }

  public String minimalString(String str, int preLen) {
    int len = str.length();
    if (len <= 2 || preLen >= len - 2) {
      return str;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(str.charAt(0));
    int preEnd = Math.min(len - 1, preLen + 1);
    for (int i = 1; i < preEnd; i++) {
      sb.append(str.charAt(i));
    }
    sb.append(len - 2 - (preEnd - 1));
    sb.append(str.charAt(len - 1));
    if (sb.length() == len) {
      return str;
    }
    return sb.toString();
  }

}

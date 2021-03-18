package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmController241to260 {
  public List<Integer> diffWaysToCompute(String input) {
    return diffWaysToCompute(input.toCharArray(), 0, input.length() - 1);
  }

  public List<Integer> diffWaysToCompute(char[] input, int l, int r) {
    List<Integer> res = new ArrayList<>();
    int temp = 0;
    int i = l;
    for (; i <= r; i++) {
      if (Character.isDigit(input[i])) {
        temp = temp * 10 + (input[i] - '0');
      } else {
        break;
      }
    }
    if (i == r + 1) {
      res.add(temp);
      return res;
    }

    for (; i <= r; i++) {
      if (Character.isDigit(input[i])) {
        continue;
      }
      List<Integer> left = diffWaysToCompute(input, l, i - 1);
      List<Integer> right = diffWaysToCompute(input, i + 1, r);
      for (int lVal : left) {
        for (int rVal : right) {
          switch (input[i]) {
          case '+':
            res.add(lVal + rVal);
            break;
          case '-':
            res.add(lVal - rVal);
            break;
          default:
            res.add(lVal * rVal);
          }
        }
      }
    }
    return res;
  }

  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] table = new int[26];
    for (int i = 0; i < s.length(); i++) {
      table[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < s.length(); i++) {
      table[t.charAt(i) - 'a']--;
      if (table[t.charAt(i) - 'a'] < 0) {
        return false;
      }
    }
    return true;
  }

  public boolean isAnagramII(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] table = new int[26];
    for (int c : s.toCharArray()) {
      table[c - 'a']++;
    }
    for (int c : t.toCharArray()) {
      table[c - 'a']--;
      if (table[c - 'a'] < 0) {
        return false;
      }
    }
    return true;
  }

  public int shortestDistance(String[] wordsDict, String word1, String word2) {
    int distance = Integer.MAX_VALUE;
    int i1 = -1;
    int i2 = -1;
    for (int i = 0; i < wordsDict.length; i++) {
      String t = wordsDict[i];
      if (t.equals(word1)) {
        i1 = i;
      } else if (t.equals(word2)) {
        i2 = i;
      }
      if (i1 != -1 && i2 != -1) {
        distance = Math.min(Math.abs(i1 - i2), distance);
      }
    }
    return distance;
  }

  class WordDistance {

    public Map<String, Map<String, Integer>> cache = new HashMap<>();
    public String[] table;

    public WordDistance(String[] wordsDict) {
      this.table = wordsDict;
    }

    public int shortest(String word1, String word2) {
      Map<String, Integer> temp = this.cache.getOrDefault(word1, new HashMap<>());
      if (temp.containsKey(word2)) {
        return temp.get(word2);
      }
      int min = this.table.length;
      for (int i = 0; i < this.table.length; i++) {
        if (this.table[i].equals(word1)) {
          int j = 1;
          while (i - j >= 0 || i + j < this.table.length) {
            if (i - j >= 0 && this.table[i - j].equals(word2)
                || i + j < this.table.length && this.table[i + j].equals(word2)) {
              break;
            }
            j++;
          }
          min = Math.min(min, j);
        }
      }
      temp.put(word2, min);
      this.cache.put(word1, temp);
      Map<String, Integer> temp2 = this.cache.getOrDefault(word2, new HashMap<>());
      temp2.put(word1, min);
      this.cache.put(word2, temp2);
      return min;
    }
  }

  class WordDistanceII {

    public Map<String, ArrayList<Integer>> cache = new HashMap<>();

    public WordDistanceII(String[] wordsDict) {
      for (int i = 0; i < wordsDict.length; i++) {
        this.cache.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
      }
    }

    public int shortest(String word1, String word2) {
      int min = Integer.MAX_VALUE;
      int i1 = 0, i2 = 0;
      ArrayList<Integer> l1 = this.cache.get(word1);
      ArrayList<Integer> l2 = this.cache.get(word2);
      while (i1 < l1.size() && i2 < l2.size()) {
        min = Math.min(min, Math.abs(l1.get(i1) - l2.get(i2)));
        if (l1.get(i1) < l2.get(i2)) {
          i1++;
        } else {
          i2++;
        }
      }
      return min;
    }
  }
}

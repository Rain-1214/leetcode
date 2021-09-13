package com.leetcode.algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgorithmController421to440 {

  public int findMaximumXOR(int[] nums) {
    if (nums.length <= 1) {
      return 0;
    }
    int res = -1;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        res = Math.max(res, nums[i] ^ nums[j]);
      }
    }
    return res;
  }

  public int findMaximumXORII(int[] nums) {
    int res = 0;

    for (int i = 30; i >= 0; i--) {
      Set<Integer> set = new HashSet<Integer>();
      for (int num : nums) {
        set.add(num >> i);
      }
      int temp = res * 2 + 1;
      boolean find = false;
      for (int num : nums) {
        if (set.contains(temp ^ (num >> i))) {
          find = true;
          break;
        }
      }
      res = find ? temp : temp - 1;
    }
    return res;
  }

  public class Trie {
    public Trie left = null;
    public Trie right = null;
  }

  public int findMaximumXORIII(int[] nums) {
    int res = 0;
    Trie root = new Trie();
    for (int i = 1; i < nums.length; i++) {
      add(root, nums[i - 1]);
      res = Math.max(res, check(root, nums[i]));
    }
    return res;
  }

  public void add(Trie root, int val) {
    Trie head = root;
    for (int i = 30; i >= 0; i--) {
      int temp = val >> i;
      if ((temp & 1) == 1) {
        if (head.left == null) {
          head.left = new Trie();
        }
        head = head.left;
      } else {
        if (head.right == null) {
          head.right = new Trie();
        }
        head = head.right;
      }
    }
  }

  public int check(Trie root, int val) {
    int res = 0;
    Trie head = root;
    for (int i = 30; i >= 0; i--) {
      int temp = val >> i;
      if ((temp & 1) == 1) {
        if (head.right == null) {
          res <<= 1;
          head = head.left;
        } else {
          res <<= 1;
          res += 1;
          head = head.right;
        }
      } else {
        if (head.left == null) {
          res <<= 1;
          head = head.right;
        } else {
          res <<= 1;
          res += 1;
          head = head.left;
        }
      }
    }
    return res;
  }

  public boolean validWordSquare(List<String> words) {
    int n = words.size(), index = 0;
    while (index < n) {
      int rowR = index, colR = 0;
      int rowD = 0, colD = index;
      String right = words.get(rowR);
      while (colR < right.length()) {
        if (rowD >= n) {
          return false;
        }
        String temp = words.get(rowD);
        if (colD >= temp.length()) {
          return false;
        }
        if (right.charAt(colR) != temp.charAt(colD)) {
          return false;
        }
        colR++;
        rowD++;
      }
      if (rowD < n && colD < words.get(rowD).length()) {
        return false;
      }
      index++;
    }
    return true;
  }

}

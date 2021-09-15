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

  // -----------------------------a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,
  // q, r, s, t, u, v, w, x, y, z
  // -----------------------------0, 1, 2, 3, 4, 5, 6, 7, 8,
  // 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25
  public int[] zero = new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 };
  public int[] one = new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  public int[] two = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 };
  public int[] three = new int[] { 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 };
  public int[] four = new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 };
  public int[] five = new int[] { 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 };
  public int[] six = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 };
  public int[] seven = new int[] { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 };
  public int[] eight = new int[] { 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 };
  public int[] nine = new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

  public String originalDigits(String s) {
    int[] letters = new int[26];
    for (char c : s.toCharArray()) {
      letters[c - 'a']++;
    }
    StringBuilder sb = new StringBuilder();
    originalDigits(letters, sb);
    return sb.toString();
  }

  public boolean originalDigits(int[] letters, StringBuilder sb) {
    int sum = 0;
    for (int num : letters) {
      sum += num;
    }
    if (sum == 0) {
      return true;
    }
    int len = sb.length();
    for (int i = 0; i <= 9; i++) {
      int[] temp = getLetterNum(i);
      boolean flag = true;
      for (int j = 0; j < letters.length; j++) {
        if (letters[j] < temp[j]) {
          flag = false;
        }
        letters[j] -= temp[j];
      }
      if (flag) {
        sb.append(i);
        if (originalDigits(letters, sb)) {
          return true;
        } else {
          sb.setLength(len);
        }
      }
      for (int j = 0; j < letters.length; j++) {
        letters[j] += temp[j];
      }
    }
    return false;
  }

  public int[] getLetterNum(int letter) {
    switch (letter) {
      case 1:
        return one;
      case 2:
        return two;
      case 3:
        return three;
      case 4:
        return four;
      case 5:
        return five;
      case 6:
        return six;
      case 7:
        return seven;
      case 8:
        return eight;
      case 9:
        return nine;
      case 0:
        return zero;
      default:
        return new int[0];
    }
  }

  public String originalDigitsII(String s) {
    int[] letters = new int[26];
    for (char c : s.toCharArray()) {
      letters[c - 'a']++;
    }
    int[] nums = new int[10];
    nums[0] = letters[25];
    nums[2] = letters[22];
    nums[4] = letters[20];
    nums[6] = letters[23];
    nums[8] = letters[6];
    nums[3] = letters[7] - nums[8];
    nums[5] = letters[5] - nums[4];
    nums[7] = letters[18] - nums[6];
    nums[9] = letters[8] - nums[5] - nums[6] - nums[8];
    nums[1] = letters[13] - nums[7] - 2 * nums[9];

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= 9; i++) {
      for (int j = 0; j < nums[i]; j++) {
        sb.append(i);
      }
    }
    return sb.toString();
  }

  public int characterReplacement(String s, int k) {
    char[] sc = s.toCharArray();
    int index = 0, res = 0;
    while (index < sc.length) {
      int temp = k, start = index, nextIndex = -1;
      char c = sc[index];
      while (temp >= 0 && index < sc.length) {
        if (sc[index] != c) {
          temp--;
          if (nextIndex == -1) {
            nextIndex = index;
          }
          if (temp < 0) {
            break;
          }
        }
        index++;
      }
      if (index == sc.length) {
        if (temp >= 0) {
          while (temp >= 0 && start > 0) {
            if (sc[start] != c) {
              temp--;
              if (temp < 0) {
                break;
              }
            }
            start--;
          }
        }
        res = Math.max(res, index - start);
        break;
      } else {
        res = Math.max(res, index - start);
      }
      index = nextIndex;
    }

    return res;
  }

  public int characterReplacementII(String s, int k) {
    int[] nums = new int[26];
    char[] sc = s.toCharArray();
    int left = 0, right = 0, len = sc.length, maxNum = 0;
    while (right < len) {
      nums[sc[right] - 'A']++;
      maxNum = Math.max(nums[sc[right] - 'A'], maxNum);
      while (right - left + 1 - maxNum > k) {
        nums[sc[left] - 'A']--;
        left++;
      }
      right++;
    }
    return right - left;
  }

}

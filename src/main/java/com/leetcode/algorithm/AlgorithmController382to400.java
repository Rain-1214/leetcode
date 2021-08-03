package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.NestedInteger;

public class AlgorithmController382to400 {

  class Solution382 {

    public Map<Integer, ListNode> map;
    public Random random;

    /**
     * @param head The linked list's head. Note that the head is guaranteed to be
     *             not null, so it contains at least one node.
     */
    public Solution382(ListNode head) {
      this.map = new HashMap<>();
      this.random = new Random();
      int index = 0;
      while (head != null) {
        map.put(index, head);
        head = head.next;
      }
    }

    /** Returns a random node's value. */
    public int getRandom() {
      int len = map.size();
      return this.map.get(this.random.nextInt(len)).val;
    }
  }

  class Solution382II {

    public List<ListNode> list = new ArrayList<>();
    public Random random = new Random();
    public int n;

    /**
     * @param head The linked list's head. Note that the head is guaranteed to be
     *             not null, so it contains at least one node.
     */
    public Solution382II(ListNode head) {
      while (head != null) {
        this.list.add(head);
        head = head.next;
      }
      this.n = this.list.size();
    }

    /** Returns a random node's value. */
    public int getRandom() {
      return this.list.get(this.random.nextInt(this.n)).val;
    }
  }

  public boolean canConstruct(String ransomNote, String magazine) {
    int[] chars = new int[26];
    for (char c : magazine.toCharArray()) {
      chars[c - 'a']++;
    }
    for (char c : ransomNote.toCharArray()) {
      int index = c - 'a';
      if (chars[index] == 0) {
        return false;
      } else {
        chars[index]--;
      }
    }
    return true;
  }

  class Solution384 {

    int[] nums;
    int[] origin;
    Random random;

    public Solution384(int[] nums) {
      this.random = new Random();
      this.nums = nums;
      this.origin = Arrays.copyOf(nums, nums.length);
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
      return this.origin;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
      for (int i = nums.length; i > 0; i--) {
        swap(nums, i - 1, random.nextInt(i));
      }
      return nums;
    }

    public void swap(int[] nums, int x, int y) {
      int temp = nums[x];
      nums[x] = nums[y];
      nums[y] = temp;
    }
  }

  public int deserializeIndex = 0;

  public NestedInteger deserialize(String s) {
    char[] ca = s.toCharArray();
    return deserialize(ca);
  }

  public NestedInteger deserialize(char[] ca) {
    NestedInteger res = new NestedInteger();
    if (ca[deserializeIndex] == '[') {
      deserializeIndex++;
      int val = 0;
      boolean negative = false, isEmpty = true;
      while (deserializeIndex < ca.length) {
        if (ca[deserializeIndex] == '[') {
          res.add(deserialize(ca));
          continue;
        }
        if (ca[deserializeIndex] == '-') {
          negative = true;
          deserializeIndex++;
          continue;
        }
        if (ca[deserializeIndex] == ',') {
          if (!isEmpty) {
            res.add(new NestedInteger(negative ? 0 - val : val));
            val = 0;
            isEmpty = true;
            negative = false;
          }
          deserializeIndex++;
          continue;
        }
        if (ca[deserializeIndex] == ']') {
          if (!isEmpty) {
            res.add(new NestedInteger(negative ? 0 - val : val));
            isEmpty = true;
            negative = false;
          }
          deserializeIndex++;
          break;
        }
        if (isEmpty) {
          isEmpty = false;
        }
        val = val * 10 + (ca[deserializeIndex++] - '0');
      }
      return res;
    }
    int val = 0;
    boolean negative = false;
    while (deserializeIndex < ca.length) {
      if (ca[deserializeIndex] == '-') {
        negative = true;
        deserializeIndex++;
        continue;
      }
      val = val * 10 + (ca[deserializeIndex++] - '0');
    }
    res.setInteger(negative ? 0 - val : val);
    return res;
  }

  public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
      if (i <= n) {
        res.add(i);
        lexicalOrderHelper(n, i, res);
      }
    }
    return res;
  }

  public void lexicalOrderHelper(int n, int start, List<Integer> res) {
    if (start > n) {
      return;
    }
    for (int i = 0; i < 10; i++) {
      int val = start * 10 + i;
      if (val <= n) {
        res.add(val);
        lexicalOrderHelper(n, val, res);
      }
    }
  }

  public int firstUniqChar(String s) {
    char[] sa = s.toCharArray();
    int[] nums = new int[26];
    for (char c : sa) {
      nums[c - 'a']++;
    }
    for (int i = 0; i < sa.length; i++) {
      if (nums[sa[i] - 'a'] == 1) {
        return i;
      }
    }
    return -1;
  }

  public int firstUniqCharII(String s) {
    int res = s.length() + 1;
    for (char i = 'a'; i <= 'z'; i++) {
      int leftIndex = s.indexOf(i);
      if (leftIndex != -1) {
        int rightIndex = s.lastIndexOf(i);
        if (leftIndex == rightIndex && leftIndex < res) {
          res = leftIndex;
        }
      }

    }
    return res == s.length() + 1 ? -1 : res;
  }

  public int lengthLongestPath(String input) {
    int max = 0, current = 0, index = 0;
    Stack<Integer> s = new Stack<Integer>();
    char[] sc = input.toCharArray();
    while (index < sc.length) {
      int currentLen = 0;
      if (sc[index] == '\n') {
        if (index + 1 < sc.length && sc[index + 1] != '\t' && !s.isEmpty()) {
          while (!s.isEmpty()) {
            int val = s.pop();
            current -= val;
          }
        }
        index++;
        continue;
      }

      if (sc[index] == '\t') {
        int tempLen = 0;
        while (index < sc.length && sc[index] == '\t') {
          tempLen++;
          index++;
        }

        if (tempLen < s.size()) {
          while (tempLen != s.size()) {
            int val = s.pop();
            current -= val;
          }
        }
      }
      boolean isFile = false;

      while (index < sc.length) {
        if (Character.isLetter(sc[index]) || Character.isDigit(sc[index]) || sc[index] == ' ') {
          currentLen++;
          index++;
        } else if (sc[index] == '.') {
          currentLen++;
          index++;
          isFile = true;
        } else {
          break;
        }
      }
      if (isFile) {
        max = Math.max(max, currentLen + current + s.size());
        isFile = false;
        continue;
      }
      s.add(currentLen);
      current += currentLen;
    }
    return max;
  }

}

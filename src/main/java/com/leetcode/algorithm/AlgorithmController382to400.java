package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.leetcode.entity.ListNode;

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

}

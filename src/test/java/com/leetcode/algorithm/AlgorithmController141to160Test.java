package com.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

import com.leetcode.entity.ListNode;

import org.junit.Test;

public class AlgorithmController141to160Test {

  private AlgorithmController141to160 algorithmController141to160 = new AlgorithmController141to160();

  @Test
  public void reorderList() {
    ListNode l = new ListNode(1);
    ListNode l2 = new ListNode(2);
    ListNode l3 = new ListNode(3);
    ListNode l4 = new ListNode(4);
    ListNode l5 = new ListNode(5);
    l.next = l2;
    l2.next = l3;
    l3.next = l4;
    this.algorithmController141to160.reorderList(l);
    System.out.println(l);
  }

  @Test
  public void LRUCache() {
    AlgorithmController141to160.LRUCache l = new AlgorithmController141to160.LRUCache(2);
    l.put(1, 1);
    l.put(2, 2);
    System.out.println(l.get(1));
    l.put(3, 3);
    System.out.println(l.get(2));
    l.put(4, 4);
    System.out.println(l.get(1));
    System.out.println(l.get(3));
    System.out.println(l.get(4));
  }

  @Test
  public void maxPoints() {
    int[][] l = new int[6][2];
    l[0] = new int[] { 3, 2 };
    l[1] = new int[] { 3, 2 };
    l[2] = new int[] { 5, 3 };
    l[3] = new int[] { 4, 1 };
    l[4] = new int[] { 2, 3 };
    l[5] = new int[] { 1, 4 };
    // System.out.println(this.algorithmController141to160.maxPoints(l));
    Map<Map<Integer, Integer>, Integer> m = new HashMap<>();
    Map<Integer, Integer> m1 = new HashMap<>();
    m1.put(1, 1);
    m.put(m1, 100);
    System.out.print(m.get(m1));
    Map<Integer, Integer> m2 = new HashMap<>();
    m2.put(1, 1);
    System.out.print(m.get(m2));
  }

  @Test
  public void reverseWords() {
    System.out.println(this.algorithmController141to160.reverseWords("  world hello   "));
  }

}
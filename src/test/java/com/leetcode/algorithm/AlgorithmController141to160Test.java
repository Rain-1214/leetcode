package com.leetcode.algorithm;

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

}
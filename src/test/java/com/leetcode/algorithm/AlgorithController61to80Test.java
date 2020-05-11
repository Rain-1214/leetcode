package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;

import org.junit.Test;

public class AlgorithController61to80Test {
  
  private AlgorithController61to80 algorithController61to80 = new AlgorithController61to80();

  @Test
  public void rotateRight() {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    this.algorithController61to80.rotateRight(head, 2);
    ListNode head2 = new ListNode(0);
    head2.next = new ListNode(1);
    head2.next.next = new ListNode(2);
    this.algorithController61to80.rotateRight(head2, 4);
    ListNode head3 = new ListNode(1);
    this.algorithController61to80.rotateRight(head3, 0);
    this.algorithController61to80.rotateRight(null, 0);
  }

}
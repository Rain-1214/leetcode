package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;

import org.junit.Assert;
import org.junit.Test;

public class AlgorithmController81to100Test {

  private AlgorithmController81to100 algorithmController81to100 = new AlgorithmController81to100();

  @Test
  public void search() {
    Assert.assertEquals(this.algorithmController81to100.search(new int[] { 2, 5, 6, 0, 0, 1, 2 }, 3), false);
    Assert.assertEquals(this.algorithmController81to100.search(new int[] { 3, 1, 1 }, 3), true);
  }

  @Test
  public void deleteDuplicates() {
    ListNode test1 = new ListNode(1);
    test1.next = new ListNode(2);
    test1.next.next = new ListNode(3);
    test1.next.next.next = new ListNode(3);
    test1.next.next.next.next = new ListNode(4);
    test1.next.next.next.next.next = new ListNode(4);
    test1.next.next.next.next.next.next = new ListNode(5);
    System.out.println(this.algorithmController81to100.deleteDuplicates(test1).toString());
  }
}
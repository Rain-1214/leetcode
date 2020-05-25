package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;

import org.junit.Assert;
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

  @Test
  public void uniquePaths() {
    System.out.println(this.algorithController61to80.uniquePaths(3, 2));
  }

  @Test
  public void minPathSum() {
    int[][] test = new int[3][3];
    test[0] = new int[] { 1, 3, 1 };
    test[1] = new int[] { 1, 5, 1 };
    test[2] = new int[] { 4, 2, 1 };
    this.algorithController61to80.minPathSum(test);
  }

  @Test
  public void isNumber() {
    System.out.println(this.algorithController61to80.isNumber("2e0"));
  }

  @Test
  public void plusOne() {
    this.algorithController61to80.plusOne(new int[] { 9 });
  }

  @Test
  public void addBinary() {
    System.out.println(this.algorithController61to80.addBinary("11", "1"));
    ;
  }

  @Test
  public void mySqrt() {
    System.out.println(this.algorithController61to80.mySqrt(5));
  }

  @Test
  public void climbStairs() {
    System.out.println(this.algorithController61to80.climbStairs(4));
  }

  @Test
  public void simplifyPath() {
    Assert.assertEquals(this.algorithController61to80.simplifyPath("/home/"), "/home");
    Assert.assertEquals(this.algorithController61to80.simplifyPath("/../"), "/");
    Assert.assertEquals(this.algorithController61to80.simplifyPath("/home//foo/"), "/home/foo");
    Assert.assertEquals(this.algorithController61to80.simplifyPath("/a/./b/../../c/"), "/c");
    Assert.assertEquals(this.algorithController61to80.simplifyPath("/a/../../b/../c//.//"), "/c");
    Assert.assertEquals(this.algorithController61to80.simplifyPath("/a//b////c/d//././/.."), "/a/b/c");
  }

}
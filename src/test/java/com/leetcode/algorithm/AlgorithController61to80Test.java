package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;
import com.leetcode.tool.Print;

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

  @Test
  public void minDistance() {
    System.out.println(this.algorithController61to80.minDistance("horse", "ros"));
    System.out.println(this.algorithController61to80.minDistance("intention", "execution"));
  }

  @Test
  public void searchMatrix() {
    int[][] test1 = new int[1][2];
    test1[0] = new int[] { 1, 3 };
    System.out.println(this.algorithController61to80.searchMatrix(test1, 3));
  }

  @Test
  public void sortColors() {
    int[] test1 = new int[] { 2, 0, 2, 1, 1, 0 };
    this.algorithController61to80.sortColors(test1);
    Assert.assertArrayEquals(test1, new int[] { 0, 0, 1, 1, 2, 2 });
    int[] test2 = new int[] { 2, 0, 1 };
    this.algorithController61to80.sortColors(test2);
    Assert.assertArrayEquals(test2, new int[] { 0, 1, 2 });
    int[] test3 = new int[] { 0, 1 };
    this.algorithController61to80.sortColors(test3);
    Assert.assertArrayEquals(test3, new int[] { 0, 1 });
    int[] test4 = new int[] { 0, 2 };
    this.algorithController61to80.sortColors(test4);
    Assert.assertArrayEquals(test4, new int[] { 0, 2 });
    int[] test5 = new int[] { 0, 0, 1, 0, 1, 1 };
    this.algorithController61to80.sortColors(test5);
    System.out.println(Print.printArray(test5));
    Assert.assertArrayEquals(test5, new int[] { 0, 0, 0, 1, 1, 1 });
  }

  @Test
  public void minWindow() {
    Assert.assertEquals(this.algorithController61to80.minWindow("ADOBECODEBANC", "ABC"), "BANC");
    Assert.assertEquals(this.algorithController61to80.minWindow("a", "a"), "a");
    Assert.assertEquals(this.algorithController61to80.minWindow("aa", "aa"), "aa");
  }

  @Test
  public void combine() {
    System.out.println(this.algorithController61to80.combine(4, 2));
  }

  @Test
  public void exist() {
    char[][] board = new char[3][4];
    board[0] = new char[] { 'A', 'B', 'C', 'E' };
    board[1] = new char[] { 'S', 'D', 'C', 'S' };
    board[2] = new char[] { 'A', 'D', 'E', 'E' };
    System.out.println(this.algorithController61to80.exist(board, "SEE"));
    char[][] board2 = new char[3][4];
    board2[0] = new char[] { 'a', 'a', 'a', 'a' };
    board2[1] = new char[] { 'a', 'a', 'a', 'a' };
    board2[2] = new char[] { 'a', 'a', 'a', 'a' };
    System.out.println(this.algorithController61to80.exist(board2, "aaaaaaaaaaaaa"));
  }

}
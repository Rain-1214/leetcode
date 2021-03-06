package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.TreeNode;

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

  @Test
  public void largestRectangleArea() {
    int[] test1 = new int[] { 2, 1, 5, 6, 2, 3 };
    Assert.assertEquals(this.algorithmController81to100.largestRectangleArea(test1), 10);
    int[] test2 = new int[] { 6 };
    Assert.assertEquals(this.algorithmController81to100.largestRectangleArea(test2), 6);
    int[] test3 = new int[] { 5, 5, 1, 7, 1, 1, 5, 2, 7, 6 };
    Assert.assertEquals(this.algorithmController81to100.largestRectangleArea(test3), 12);
  }

  @Test
  public void maximalRectangle() {
    char[][] test1 = new char[6][5];
    test1[0] = new char[] { '1', '1', '1', '0', '1' };
    test1[1] = new char[] { '1', '1', '0', '1', '0' };
    test1[2] = new char[] { '0', '1', '1', '1', '0' };
    test1[3] = new char[] { '1', '1', '1', '1', '0' };
    test1[4] = new char[] { '1', '1', '1', '1', '1' };
    test1[5] = new char[] { '0', '0', '0', '0', '0' };
    System.out.println(this.algorithmController81to100.maximalRectangle(test1));
  }

  @Test
  public void partition() {
    ListNode test1 = new ListNode(1);
    test1.next = new ListNode(4);
    test1.next.next = new ListNode(3);
    test1.next.next.next = new ListNode(2);
    test1.next.next.next.next = new ListNode(5);
    test1.next.next.next.next.next = new ListNode(2);
    System.out.println(this.algorithmController81to100.partition(test1, 3).toString());
  }

  @Test
  public void subsetsWithDup() {
    int[] test1 = new int[] { 4, 4, 4, 1, 4 };
    System.out.println(this.algorithmController81to100.subsetsWithDup(test1));
  }

  @Test
  public void numDecodings() {
    System.out.println(this.algorithmController81to100.numDecodings("122221"));
  }

  @Test
  public void reverseBetween() {
    ListNode test1 = new ListNode(1);
    test1.next = new ListNode(2);
    test1.next.next = new ListNode(3);
    test1.next.next.next = new ListNode(4);
    test1.next.next.next.next = new ListNode(5);
    System.out.println(this.algorithmController81to100.reverseBetween(test1, 1, 5).toString());
  }

  @Test
  public void restoreIpAddresses() {
    System.out.println(this.algorithmController81to100.restoreIpAddresses("010010"));
  }

  @Test
  public void genereateTrees() {
    this.algorithmController81to100.generateTrees(3);
  }

  @Test
  public void isInterleave() {
    System.out.println(this.algorithmController81to100.isInterLeaveDP("aa", "ab", "abaa"));
  }

  @Test
  public void isValidBST() {
    TreeNode test = new TreeNode(2, new TreeNode(1), new TreeNode(3));
    System.out.println(this.algorithmController81to100.isValidBST(test));
  }

}
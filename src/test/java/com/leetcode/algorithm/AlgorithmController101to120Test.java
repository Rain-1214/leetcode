package com.leetcode.algorithm;

import com.leetcode.entity.TreeNode;

import org.junit.Test;

public class AlgorithmController101to120Test {

  private AlgorithmController101to120 algorithmController81to100 = new AlgorithmController101to120();

  @Test
  public void buildTree() {
    int[] preorder = new int[] { 1, 2, 4, 5, 3, 6, 7 };
    int[] inorder = new int[] { 4, 2, 5, 1, 6, 3, 7 };
    TreeNode a = this.algorithmController81to100.buildTree(preorder, inorder);
  }

  @Test
  public void buildTree106() {
    int[] inorder = new int[] { 4, 2, 5, 1, 6, 3, 7 };
    int[] postorder = new int[] { 4, 5, 2, 6, 7, 3, 1 };
    TreeNode a = this.algorithmController81to100.buildTree106(inorder, postorder);
  }

  @Test
  public void numDistinct() {
    System.out.println(this.algorithmController81to100.numDistinct("babgbag", "bag"));
  }

}
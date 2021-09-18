package com.leetcode.entity;

public class MatrixNode {
  public boolean val;
  public boolean isLeaf;
  public MatrixNode topLeft;
  public MatrixNode topRight;
  public MatrixNode bottomLeft;
  public MatrixNode bottomRight;

  public MatrixNode() {
    this.val = false;
    this.isLeaf = false;
    this.topLeft = null;
    this.topRight = null;
    this.bottomLeft = null;
    this.bottomRight = null;
  }

  public MatrixNode(boolean val, boolean isLeaf) {
    this.val = val;
    this.isLeaf = isLeaf;
    this.topLeft = null;
    this.topRight = null;
    this.bottomLeft = null;
    this.bottomRight = null;
  }

  public MatrixNode(boolean val, boolean isLeaf, MatrixNode topLeft, MatrixNode topRight, MatrixNode bottomLeft,
      MatrixNode bottomRight) {
    this.val = val;
    this.isLeaf = isLeaf;
    this.topLeft = topLeft;
    this.topRight = topRight;
    this.bottomLeft = bottomLeft;
    this.bottomRight = bottomRight;
  }
}

package com.leetcode.algorithm;

import com.leetcode.entity.TreeNode;

public class AlgorithmController601to620 {
  class StringIterator {

    private String compressedString;
    private char currentChar;
    private int currentCount;
    private int index;
    private boolean hasNext;

    public StringIterator(String compressedString) {
      this.compressedString = compressedString;
      this.index = 0;
      this.hasNext = true;
      this.computeCurrentChar();
    }

    public boolean isNumber(char c) {
      return c >= '0' && c <= '9';
    }

    public boolean isLetter(char c) {
      return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public void computeCurrentChar() {
      this.currentChar = this.compressedString.charAt(this.index);
      this.currentCount = 0;
      this.index++;
      char c = this.compressedString.charAt(this.index);
      while (isNumber(c)) {
        this.currentCount = this.currentCount * 10 + (c - '0');
        this.index++;
        if (this.index >= this.compressedString.length()) {
          this.hasNext = false;
          break;
        }
        c = this.compressedString.charAt(this.index);
      }
    }

    public char next() {
      if (!this.hasNext && this.currentCount == 0) {
        return ' ';
      }
      if (this.currentCount == 0) {
        this.computeCurrentChar();
      }
      this.currentCount--;
      return this.currentChar;
    }

    public boolean hasNext() {
      if (this.hasNext) {
        return true;
      }
      return this.currentCount > 0;
    }
  }

  public boolean canPlaceFlowers(int[] flowerbed, int n) {
    var len = flowerbed.length;
    var count = 0;
    for (int i = 0; i < len; i++) {
      if (flowerbed[i] == 1) {
        continue;
      }
      var left = i == 0 ? 0 : flowerbed[i - 1];
      var right = i == len - 1 ? 0 : flowerbed[i + 1];
      if (left == 0 && right == 0) {
        flowerbed[i] = 1;
        count++;
      }
    }
    return count >= n;
  }

  public String tree2str(TreeNode root) {
    if (root == null) {
      return "";
    }
    var sb = new StringBuilder();
    sb.append(root.val);
    String left = tree2str(root.left);
    String right = tree2str(root.right);
    if (left.length() > 0) {
      sb.append("(");
      sb.append(left);
      sb.append(")");
    }
    if (left.length() == 0 && right.length() > 0) {
      sb.append("()");
    }
    if (right.length() > 0) {
      sb.append("(");
      sb.append(right);
      sb.append(")");
    }
    return sb.toString();
  }

  public String tree2str2(TreeNode root) {
    var sb = new StringBuilder();
    tree2str2Helper(root, sb);
    return sb.toString();

  }

  public void tree2str2Helper(TreeNode root, StringBuilder sb) {
    if (root == null) {
      return;
    }
    sb.append(root.val);
    if (root.left == null && root.right == null) {
      return;
    }
    sb.append("(");
    tree2str2Helper(root.left, sb);
    sb.append(")");
    if (root.right != null) {
      sb.append("(");
      tree2str2Helper(root.right, sb);
      sb.append(")");
    }
  }

}

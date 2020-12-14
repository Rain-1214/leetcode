package com.leetcode.entity.randomNode;

public class Node {
  public int val;
  public Node next;
  public Node random;

  public Node(int _val) {
    this.val = _val;
    this.next = null;
    this.random = null;
  }

  public Node(int val, Node next, Node random) {
    this.val = val;
    this.next = next;
    this.random = random;
  }

}

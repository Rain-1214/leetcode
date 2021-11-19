package com.leetcode.algorithm;

import org.junit.Test;

public class AlgorithmController461to480Test {

  private AlgorithmController461to480 algorithmController461to480 = new AlgorithmController461to480();

  @Test
  public void binarySearchBigger() {
    int[] nums = new int[] { 1, 3, 4, 5 };
    int target = 2;
    System.out.println(this.algorithmController461to480.binarySearchSmall(nums, target));
  }

}
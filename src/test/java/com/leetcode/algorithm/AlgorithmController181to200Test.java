package com.leetcode.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class AlgorithmController181to200Test {

  private AlgorithmController181to200 algorithmController161to180 = new AlgorithmController181to200();

  @Test
  public void largestNumber() {
    int[] test = new int[] { 3, 3, 5, 0, 0, 3, 1, 4 };
    int s = this.algorithmController161to180.maxProfit(3, test);
    System.out.println(s);
    Assert.assertEquals(s, 8);
    int[] test2 = new int[] { 3, 2, 6, 5, 0, 3 };
    int s1 = this.algorithmController161to180.maxProfit(2, test2);
    Assert.assertEquals(s1, 7);

  }

  @Test
  public void reverseBits() {
    System.out.println(Integer.parseInt("11111111111111111111111111111101", 2));
    // this.algorithmController161to180.reverseBits(Integer.parseInt("11111111111111111111111111111101",
    // 2));
  }

}
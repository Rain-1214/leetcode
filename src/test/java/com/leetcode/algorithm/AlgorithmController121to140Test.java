package com.leetcode.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class AlgorithmController121to140Test {

  private AlgorithmController121to140 algorithmController121to140 = new AlgorithmController121to140();

  @Test
  public void maxProfit3() {
    int[] test = new int[] { 3, 3, 5, 0, 0, 3, 1, 4 };
    Assert.assertEquals(this.algorithmController121to140.maxProfit3(test), 6);
    int[] test2 = new int[] { 1,2,4,2,5,7,2,4,9,0 };
    Assert.assertEquals(this.algorithmController121to140.maxProfit3(test2), 13);
  }

}
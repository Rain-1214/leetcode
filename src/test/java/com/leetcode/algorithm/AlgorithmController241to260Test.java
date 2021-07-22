package com.leetcode.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class AlgorithmController241to260Test {

  private AlgorithmController241to260 algorithmController241to260 = new AlgorithmController241to260();
  private AlgorithmController361to380 algorithmController361to380 = new AlgorithmController361to380();

  @Test
  public void largestNumber() {
    System.out.println(this.algorithmController241to260.diffWaysToCompute("2*3-4*5"));
  }

  @Test
  public void getFactors() {
    this.algorithmController361to380.getMoneyAmount(5);
  }

}
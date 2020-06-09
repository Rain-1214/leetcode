package com.leetcode.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class AlgorithmController81to100Test {

  private AlgorithmController81to100 algorithmController81to100 = new AlgorithmController81to100();

  @Test
  public void search() {
    Assert.assertEquals(this.algorithmController81to100.search(new int[]{2,5,6,0,0,1,2}, 3), false);
    Assert.assertEquals(this.algorithmController81to100.search(new int[]{3,1,1}, 3), true);
  }
}
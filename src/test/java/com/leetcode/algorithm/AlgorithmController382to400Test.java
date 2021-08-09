package com.leetcode.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class AlgorithmController382to400Test {

  private AlgorithmController382to400 algorithmController361to380 = new AlgorithmController382to400();

  @Test
  public void largestNumber() {
    System.out.println(this.algorithmController361to380.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
  }

  @Test
  public void validUtf8() {
    System.out.println(this.algorithmController361to380.validUtf8(new int[] { 240, 162, 138, 147, 145 }));
  }

  @Test
  public void longestSubstring() {
    System.out.println(this.algorithmController361to380.longestSubstring("aaabb", 3));
  }

}
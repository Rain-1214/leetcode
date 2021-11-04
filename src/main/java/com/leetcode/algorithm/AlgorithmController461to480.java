package com.leetcode.algorithm;

public class AlgorithmController461to480 {
  public int hammingDistance(int x, int y) {
    int i = x ^ y, res = 0;
    while (i > 0) {
      res++;
      i &= i - 1;
    }
    return res;
  }
}

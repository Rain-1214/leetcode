package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AlgorithmController121to140Test {

  private AlgorithmController121to140 algorithmController121to140 = new AlgorithmController121to140();

  @Test
  public void maxProfit3() {
    int[] test = new int[] { 3, 3, 5, 0, 0, 3, 1, 4 };
    Assert.assertEquals(this.algorithmController121to140.maxProfit3(test), 6);
    int[] test2 = new int[] { 1, 2, 4, 2, 5, 7, 2, 4, 9, 0 };
    Assert.assertEquals(this.algorithmController121to140.maxProfit3(test2), 13);
  }

  @Test
  public void isPalindrome() {
    this.algorithmController121to140.isPalindrome("A man, a plan, a canal:Panama");
  }

  @Test
  public void findLadders() {
    List<String> case1 = new ArrayList<>();
    case1.add("hot");
    case1.add("dot");
    case1.add("dog");
    case1.add("lot");
    case1.add("log");
    case1.add("cog");
    List<List<String>> res = this.algorithmController121to140.findLadders("hit", "cog", case1);
    System.out.println(res);
    List<String> case2 = new ArrayList<>();
    case2.add("a");
    case2.add("b");
    case2.add("c");
    List<List<String>> res2 = this.algorithmController121to140.findLadders("a", "c", case2);
    System.out.println(res2);
  }

}
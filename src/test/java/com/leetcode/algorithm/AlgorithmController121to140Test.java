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
    List<String> case2 = new ArrayList<>();
    case2.add("a");
    case2.add("b");
    case2.add("c");
    List<List<String>> res2 = this.algorithmController121to140.findLadders("a", "c", case2);

    int res3 = this.algorithmController121to140.ladderLength("a", "c", case2);
    System.out.println(res3);
  }

  @Test
  public void partition() {
    System.out.println(this.algorithmController121to140.partition("aabaa"));
  }

  @Test
  public void minCut() {
    this.algorithmController121to140.minCut("abcdefedcba");
  }

  @Test
  public void canCompleteCircuit() {
    this.algorithmController121to140.canCompleteCircuit(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 });
  }

  @Test
  public void wordBreak() {
    List<String> s = new ArrayList<>();
    s.add("a");
    Assert.assertEquals(this.algorithmController121to140.wordBreak("aaaaaaa", s), true);
    List<String> s1 = new ArrayList<>();
    s1.add("leet");
    s1.add("code");
    Assert.assertEquals(this.algorithmController121to140.wordBreak("leetcode", s1), true);
    Assert.assertEquals(this.algorithmController121to140.wordBreak("a", new ArrayList<>()), false);
    List<String> s2 = new ArrayList<>();
    s2.add("b");
    Assert.assertEquals(this.algorithmController121to140.wordBreak("a", s2), false);
  }

}
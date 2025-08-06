package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AlgorithmController581to600Test {

  private AlgorithmController581to600 algorithController581to600 = new AlgorithmController581to600();

  @Test
  public void testKillProcess() {
    List<Integer> pid = new ArrayList<>();
    pid.add(1);
    pid.add(3);
    pid.add(10);
    pid.add(5);
    List<Integer> ppid = new ArrayList<>();
    ppid.add(3);
    ppid.add(0);
    ppid.add(5);
    ppid.add(3);
    System.out.println(this.algorithController581to600.killProcess(pid, ppid, 5));
  }

  @Test
  public void testMinDistance() {
    System.out.println(this.algorithController581to600.minDistance("sea", "eat"));
  }

  @Test
  public void testLongestCommonSubsequence() {
    System.out.println(this.algorithController581to600.longestCommonSubsequence("abcde", "ace"));
  }

  @Test
  public void testOuterTrees() {
    int[][] trees = new int[][] { { 1, 1 }, { 2, 2 }, { 2, 0 }, { 2, 4 }, { 3, 3 }, { 4, 2 } };
    System.out.println(Arrays.deepToString(this.algorithController581to600.outerTrees(trees)));
  }
}

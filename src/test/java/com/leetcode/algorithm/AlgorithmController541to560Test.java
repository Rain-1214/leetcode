package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AlgorithmController541to560Test {

  private AlgorithmController541to560 algorithmController461to480 = new AlgorithmController541to560();

  @Test
  public void testLeastBricks() {
    List<List<Integer>> wall = new ArrayList<>();
    wall.add(Arrays.asList(100000000, 100000000));
    wall.add(Arrays.asList(100000000, 100000000));
    System.out.println(this.algorithmController461to480.leastBricks3(wall));
  }

  @Test
  public void nextGreaterElement() {
    System.out.println(this.algorithmController461to480.nextGreaterElement(12222333));
  }
}

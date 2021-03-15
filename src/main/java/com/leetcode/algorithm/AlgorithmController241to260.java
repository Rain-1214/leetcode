package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmController241to260 {
  public List<Integer> diffWaysToCompute(String input) {
    return diffWaysToCompute(input.toCharArray(), 0, input.length() - 1);
  }

  public List<Integer> diffWaysToCompute(char[] input, int l, int r) {
    List<Integer> res = new ArrayList<>();
    int temp = 0;
    int i = l;
    for (; i <= r; i++) {
      if (Character.isDigit(input[i])) {
        temp = temp * 10 + (input[i] - '0');
      } else {
        break;
      }
    }
    if (i == r + 1) {
      res.add(temp);
      return res;
    }

    for (; i <= r; i++) {
      if (Character.isDigit(input[i])) {
        continue;
      }
      List<Integer> left = diffWaysToCompute(input, l, i - 1);
      List<Integer> right = diffWaysToCompute(input, i + 1, r);
      for (int lVal : left) {
        for (int rVal : right) {
          switch (input[i]) {
          case '+':
            res.add(lVal + rVal);
            break;
          case '-':
            res.add(lVal - rVal);
            break;
          default:
            res.add(lVal * rVal);
          }
        }
      }
    }
    return res;
  }
}

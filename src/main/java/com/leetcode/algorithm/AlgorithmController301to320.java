package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgorithmController301to320 {

  public char[] removeInvalidParenthesesSc;
  public int removeInvalidParenthesesLen;
  public Set<String> removeInvalidParenthesesSet;

  public List<String> removeInvalidParentheses(String s) {
    this.removeInvalidParenthesesLen = s.length();
    this.removeInvalidParenthesesSet = new HashSet<>();
    this.removeInvalidParenthesesSc = s.toCharArray();

    int leftRemove = 0;
    int rightRemove = 0;
    for (char c : removeInvalidParenthesesSc) {
      if (c == '(') {
        leftRemove++;
      } else if (c == ')') {
        if (leftRemove == 0) {
          rightRemove++;
        }
        if (leftRemove > 0) {
          leftRemove--;
        }
      }
    }
    removeInvalidParentheses(0, 0, 0, leftRemove, rightRemove, new StringBuilder());
    return new ArrayList<>(this.removeInvalidParenthesesSet);
  }

  public void removeInvalidParentheses(int index, int leftCount, int rightCount, int leftRemove, int rightRemove,
      StringBuilder sb) {
    if (index == this.removeInvalidParenthesesLen) {
      if (leftRemove == 0 && rightRemove == 0) {
        this.removeInvalidParenthesesSet.add(sb.toString());
      }
      return;
    }

    char currentChar = this.removeInvalidParenthesesSc[index];
    if (currentChar == '(' && leftRemove > 0) {
      this.removeInvalidParentheses(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, sb);
    }
    if (currentChar == ')' && rightRemove > 0) {
      this.removeInvalidParentheses(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, sb);
    }

    sb.append(currentChar);

    if (currentChar != '(' && currentChar != ')') {
      this.removeInvalidParentheses(index + 1, leftCount, rightCount, leftRemove, rightRemove, sb);
    } else if (currentChar == '(') {
      this.removeInvalidParentheses(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, sb);
    }
    if (leftCount > rightCount) {
      this.removeInvalidParentheses(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, sb);
    }
    sb.deleteCharAt(sb.length() - 1);
  }

  public int minAreaLeft, maxAreaRight, minAreaTop, maxAreaBottom;

  public int minArea(char[][] image, int x, int y) {
    if (image.length == 0 || image[0].length == 0) {
      return 0;
    }
    minAreaLeft = maxAreaRight = y;
    minAreaTop = maxAreaBottom = x;
    minAreaHelp(image, x, y);

    return (maxAreaRight - minAreaLeft + 1) * (maxAreaBottom - minAreaTop + 1);
  }

  public void minAreaHelp(char[][] image, int x, int y) {
    if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] == '0') {
      return;
    }
    image[x][y] = '0';
    minAreaLeft = Math.min(y, minAreaLeft);
    maxAreaRight = Math.max(y, maxAreaRight);
    minAreaTop = Math.min(x, minAreaTop);
    maxAreaBottom = Math.max(x, maxAreaBottom);
    minAreaHelp(image, x - 1, y);
    minAreaHelp(image, x + 1, y);
    minAreaHelp(image, x, y - 1);
    minAreaHelp(image, x, y + 1);

  }

  class NumArray {

    int[] nums;

    public NumArray(int[] nums) {
      if (nums.length == 0) {
        this.nums = new int[0];
        return;
      }
      this.nums = new int[nums.length];
      this.nums[0] = nums[0];
      for (int i = 1; i < nums.length; i++) {
        this.nums[i] = this.nums[i - 1] + nums[i];
      }
    }

    public int sumRange(int left, int right) {
      if (left == 0) {
        return this.nums[right];
      }
      return this.nums[right] - this.nums[left - 1];
    }
  }

}

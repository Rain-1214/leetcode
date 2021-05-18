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

}
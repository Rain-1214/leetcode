package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AlgorithmController401to420 {

  public List<String> readBinaryWatch(int turnedOn) {
    List<String> res = new ArrayList<>();
    int[] current = new int[10];
    int[] weight = new int[10];
    int temp = 1;
    for (int i = 0; i < 10; i++) {
      weight[i] = temp;
      temp *= 2;
      if (i == 3) {
        temp = 1;
      }
    }
    readBinaryWatchHelper(0, turnedOn, current, res, weight);
    return res;
  }

  public void readBinaryWatchHelper(int start, int num, int[] current, List<String> res, int[] weight) {
    if (num == 0) {
      String temp = generateTimeString(current, weight);
      if (temp != null) {
        res.add(temp);
      }
      return;
    }
    for (int i = start; i < current.length; i++) {
      current[i] = 1;
      readBinaryWatchHelper(i + 1, num - 1, current, res, weight);
      current[i] = 0;
    }
  }

  public String generateTimeString(int[] current, int[] weight) {
    StringBuilder sb = new StringBuilder();
    int temp = 0;
    for (int i = 0; i < 4; i++) {
      temp += current[i] * weight[i];
    }
    if (temp >= 12) {
      return null;
    }
    sb.append(temp);
    temp = 0;
    for (int i = 4; i < 10; i++) {
      temp += current[i] * weight[i];
    }
    if (temp >= 60) {
      return null;
    }
    sb.append(':');
    if (temp < 10) {
      sb.append('0');
    }
    sb.append(temp);
    return sb.toString();
  }

  public String removeKdigits(String num, int k) {
    Deque<Integer> dq = new LinkedList<Integer>();
    char[] ca = num.toCharArray();
    int i = 1;
    dq.addLast(ca[0] - '0');
    while (i < ca.length) {
      int current = ca[i] - '0';
      if (!dq.isEmpty() && current < dq.peekLast() && k > 0) {
        while (!dq.isEmpty() && current < dq.peekLast() && k > 0) {
          dq.removeLast();
          k--;
        }
      }
      dq.addLast(current);
      i++;
    }
    while (k > 0) {
      dq.removeLast();
      k--;
    }
    if (dq.size() == 0) {
      return "0";
    }
    while (!dq.isEmpty() && dq.peek() == 0) {
      dq.pop();
    }
    if (dq.size() == 0) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    while (!dq.isEmpty()) {
      sb.append(dq.pop());
    }
    return sb.toString();
  }
}

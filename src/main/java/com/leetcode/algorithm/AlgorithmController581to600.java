package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class AlgorithmController581to600 {

  public int findUnsortedSubarray(int[] nums) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int right = -1;
    int left = -1;
    for (int i = 0; i < nums.length; i++) {
      int left_item = nums[i];
      if (left_item >= max) {
        max = left_item;
      } else {
        right = i;
      }
      int right_item = nums[nums.length - 1 - i];
      if (right_item <= min) {
        min = right_item;
      } else {
        left = nums.length - 1 - i;
      }
    }
    return right == -1 ? 0 : right - left + 1;
  }

  public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < pid.size(); i++) {
      map.put(pid.get(i), new ArrayList<>());
    }
    for (int i = 0; i < ppid.size(); i++) {
      int parentId = ppid.get(i);
      if (parentId == 0) {
        continue;
      }
      map.get(parentId).add(pid.get(i));
    }
    List<Integer> result = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.add(kill);
    while (!queue.isEmpty()) {
      int current = queue.poll();
      result.add(current);
      List<Integer> children = map.get(current);
      queue.addAll(children);
    }
    return result;
  }
}

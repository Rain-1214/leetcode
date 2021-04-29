package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmController281to300 {

  public class ZigzagIterator {

    List<List<Integer>> pool = new ArrayList<>();
    int index = 0;
    List<Integer> listIndex;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
      this.listIndex = new ArrayList<Integer>();
      if (v1.size() > 0) {
        pool.add(v1);
        this.listIndex.add(0);
      }
      if (v2.size() > 0) {
        pool.add(v2);
        this.listIndex.add(0);
      }
    }

    public int next() {
      List<Integer> temp = pool.get(index);
      int currentIndex = listIndex.get(index);
      int res = temp.get(currentIndex);
      if (currentIndex + 1 >= temp.size()) {
        pool.remove(index);
        listIndex.remove(index);
      } else {
        listIndex.set(index, currentIndex + 1);
        index++;
      }
      if (index >= pool.size()) {
        index = 0;
      }
      return res;
    }

    public boolean hasNext() {
      return !pool.isEmpty();
    }
  }

  public List<String> addOperators(String num, int target) {
    List<String> res = new ArrayList<>();
    if (num == null || num.length() == 0) {
      return res;
    }
    addOperators(res, "", num, target, 0, 0, 0);
    return res;
  }

  public void addOperators(List<String> res, String path, String num, int target, int index, long current,
      long prevCurrent) {
    if (index == num.length()) {
      if (current == target) {
        res.add(path);
      }
      return;
    }

    for (int i = index; i < num.length(); i++) {
      if (i != index && num.charAt(index) == '0') {
        break;
      }
      long temp = Long.parseLong(num.substring(index, i + 1));
      if (index == 0) {
        addOperators(res, path + temp, num, target, i + 1, temp, temp);
        continue;
      }
      addOperators(res, path + '+' + temp, num, target, i + 1, current + temp, temp);
      addOperators(res, path + '-' + temp, num, target, i + 1, current - temp, -temp);
      addOperators(res, path + '*' + temp, num, target, i + 1, current - prevCurrent + prevCurrent * temp,
          prevCurrent * temp);
    }
  }

  public void moveZeroes(int[] nums) {
    int[] notZero = new int[nums.length];
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        notZero[j++] = i;
      }
    }
    for (int x = 0; x < nums.length; x++) {
      if (x >= j) {
        nums[x] = 0;
        continue;
      }
      nums[x] = nums[notZero[x]];
    }
  }

  public void moveZeroesII(int[] nums) {
    int zeroIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        if (i == zeroIndex) {
          zeroIndex++;
        } else {
          nums[zeroIndex++] = nums[i];
        }
      }
    }
    for (int i = zeroIndex; i < nums.length; i++) {
      nums[i] = 0;
    }
  }

}

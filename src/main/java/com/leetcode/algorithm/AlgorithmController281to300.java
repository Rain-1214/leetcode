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

}

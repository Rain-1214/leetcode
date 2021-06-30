package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.leetcode.entity.NestedInteger;

public class AlgorithmController341to360 {

  public class NestedIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
      LinkedList<Integer> list = new LinkedList<>();
      this.transformList(nestedList, list);
      this.iterator = list.iterator();
    }

    public void transformList(List<NestedInteger> nestedList, LinkedList<Integer> list) {
      for (NestedInteger nested : nestedList) {
        if (nested.isInteger()) {
          list.add(nested.getInteger());
        } else {
          transformList(nested.getList(), list);
        }
      }
    }

    @Override
    public Integer next() {
      return iterator.next();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }
  }

  public boolean isPowerOfFour(int n) {
    if (n < 1) {
      return false;
    }
    while (n > 1) {
      if (n % 4 == 0) {
        n /= 4;
      } else {
        return false;
      }
    }
    return n == 1;
  }

  public int integerBreak(int n) {
    int[] dp = new int[n + 1];
    dp[2] = 1;
    for (int i = 3; i <= n; i++) {
      int max = 0;
      for (int j = 1; j < i; j++) {
        max = Math.max(Math.max(j * (i - j), j * dp[i - j]), max);
      }
      dp[i] = max;
    }
    return dp[n];
  }

  public void reverseString(char[] s) {
    int left = 0, right = s.length - 1;
    while (left < right) {
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;
      left++;
      right--;
    }
  }

  public String reverseVowels(String s) {
    char[] sa = s.toCharArray();
    int left = 0, right = sa.length - 1;
    Set<Character> dic = new HashSet<>();
    dic.add('a');
    dic.add('e');
    dic.add('i');
    dic.add('o');
    dic.add('u');
    dic.add('A');
    dic.add('E');
    dic.add('I');
    dic.add('O');
    dic.add('U');
    while (left < right) {
      if (!dic.contains(sa[left])) {
        left++;
        continue;
      } else if (!dic.contains(sa[right])) {
        right--;
        continue;
      } else {
        char temp = sa[left];
        sa[left] = sa[right];
        sa[right] = temp;
        left++;
        right--;
      }
    }
    return new String(sa);
  }

  public String reverseVowelsII(String s) {
    char[] sa = s.toCharArray();
    int left = 0, right = sa.length - 1;
    while (left < right) {
      if (!isVowels(sa[left])) {
        left++;
        continue;
      } else if (!isVowels(sa[right])) {
        right--;
        continue;
      } else {
        char temp = sa[left];
        sa[left] = sa[right];
        sa[right] = temp;
        left++;
        right--;
      }
    }
    return new String(sa);
  }

  public boolean isVowels(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
        || c == 'U';
  }

  class MovingAverage {

    public int maxSize;
    public int currentSize;
    public LinkedList<Integer> q;
    public int sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
      this.maxSize = size;
      this.currentSize = 0;
      this.sum = 0;
      this.q = new LinkedList<>();
    }

    public double next(int val) {
      if (currentSize == maxSize) {
        this.sum -= q.pop();
      } else {
        this.currentSize++;
      }
      q.add(val);
      this.sum += val;
      return (double) this.sum / this.currentSize;
    }
  }

  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    int[][] heap = new int[map.size()][2];
    int index = 0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int key = entry.getKey(), value = entry.getValue();
      heap[index][0] = key;
      heap[index++][1] = value;
    }
    for (int i = heap.length / 2 - 1; i >= 0; i--) {
      adjustHeap(heap, i, heap.length);
    }
    int[] res = new int[k];
    for (int i = 0; i < k; i++) {
      res[i] = heap[0][0];
      int[] temp = heap[0];
      heap[0] = heap[heap.length - 1 - i];
      heap[heap.length - 1 - i] = temp;
      adjustHeap(heap, 0, heap.length - 1 - i);
    }
    return res;
  }

  public void adjustHeap(int[][] heap, int i, int right) {
    int[] temp = heap[i];
    for (int j = i * 2 + 1; j < right; j = j * 2 + 1) {
      if (j + 1 < right && heap[j][1] < heap[j + 1][1]) {
        j++;
      }
      if (heap[j][1] > temp[1]) {
        heap[i] = heap[j];
        i = j;
      } else {
        break;
      }
    }
    heap[i] = temp;
  }

  class TicTacToe {

    int[][] board;
    int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
      this.n = n;
      this.board = new int[n][n];
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * 
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1:
     *         Player 1 wins. 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
      board[row][col] = player;
      boolean rowFlag = true, colFlag = true;
      for (int i = 0; i < n; i++) {
        if (board[row][i] != player) {
          rowFlag = false;
        }
        if (board[i][col] != player) {
          colFlag = false;
        }
      }
      if (rowFlag || colFlag) {
        return player;
      }

      if (row == col || row == n - 1 - col) {
        boolean leftFlag = true, rightFlag = true;
        for (int i = 0; i < n; i++) {
          if (board[i][i] != player) {
            leftFlag = false;
          }
          if (board[i][n - 1 - i] != player) {
            rightFlag = false;
          }
        }
        if (leftFlag || rightFlag) {
          return player;
        }
      }
      return 0;
    }
  }

  class TicTacToeII {

    int n;
    int[][] row, col, diagonal;

    /** Initialize your data structure here. */
    public TicTacToeII(int n) {
      this.n = n;
      this.row = new int[3][n];
      this.col = new int[3][n];
      this.diagonal = new int[3][2];
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * 
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1:
     *         Player 1 wins. 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
      if (++this.row[player][row] == n) {
        return player;
      }
      if (++this.col[player][col] == n) {
        return player;
      }
      if (row == col && ++this.diagonal[player][0] == n) {
        return player;
      }
      if (row == n - 1 - col && ++this.diagonal[player][1] == n) {
        return player;
      }
      return 0;
    }
  }

  public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> newSet = new HashSet<>();
    for (int num: nums1) {
      set1.add(num);
    }
    for (int num: nums2) {
      if (set1.contains(num)) {
        newSet.add(num);
      }
    }
    int[] res = new int[newSet.size()];
    Iterator<Integer> i = newSet.iterator();
    int index = 0;
    while(i.hasNext()) {
      res[index++] = i.next();
    }
    return res;
  }

  public int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, int[]> map = new HashMap<>();
    for (int num: nums1) {
      int[] temp = map.getOrDefault(num, new int[2]);
      temp[0]++;
      map.put(num, temp);
    }
    for (int num: nums2) {
      if (map.containsKey(num)) {
        map.get(num)[1]++;
      }
    }
    List<Integer> list = new ArrayList<>();
    for (Map.Entry<Integer, int[]> entry: map.entrySet()) {
      int key = entry.getKey();
      int[] value = entry.getValue();
      if (value[1] == 0) {
        continue;
      }
      int len = Math.min(value[0], value[1]);
      for (int i = 0; i < len; i++) {
        list.add(key);
      }
    }
    int[] res = new int[list.size()];
    int i = 0;
    for (int v: list) {
      res[i++] = v;
    }
    return res;
  }


  public boolean[] patterns = new boolean[9];
  public int numberOfPatterns(int m, int n) {
    int res = 0;
    for (int len = m; len <= n; len++) {
      res += numberOfPatternsHelp(-1, len);
    }
    return res;
  }

  public int numberOfPatternsHelp(int last, int len) {
    if (len == 0) {
      return 1;
    }
    int sum = 0;
    for (int i = 0; i < 9; i++) {
      if (patternIsValid(i, last)) {
        patterns[i] = true;
        sum += numberOfPatternsHelp(i, len - 1);
        patterns[i] = false;
      }
    }
    return sum;
  }

  public boolean patternIsValid(int value, int last) {
    if (patterns[value]) {
      return false;
    }
    if (last == -1) {
      return true;
    }
    if (((last + value) % 2) == 1) {
      return true;
    }
    int mid = (last + value) / 2;
    if (mid == 4) {
      return patterns[mid];
    }
    if ((value % 3 != last % 3) && (value / 3 != last / 3)) {
      return true;
    }
    return patterns[mid];
  }
  

}

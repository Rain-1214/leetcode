package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.leetcode.entity.TreeNode;

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

  class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> iterator;
    Integer top;

    public PeekingIterator(Iterator<Integer> iterator) {
      // initialize any member here.
      this.iterator = iterator;
      if (this.iterator.hasNext()) {
        top = this.iterator.next();
      }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
      return top;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
      int temp = top;
      if (this.iterator.hasNext()) {
        this.top = this.iterator.next();
      } else {
        this.top = null;
      }
      return temp;
    }

    @Override
    public boolean hasNext() {
      return top != null;
    }
  }

  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (p.right != null) {
      TreeNode temp = p.right;
      while (temp.left != null) {
        temp = temp.left;
      }
      return temp;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode last = null;

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.add(root);
        root = root.left;
      }

      TreeNode temp = stack.pop();

      if (p == last) {
        return temp;
      }
      last = temp;
      root = temp.right;
    }
    return null;
  }

  private static final int EMPTY = Integer.MAX_VALUE;
  private static final int GATE = 0;
  private static final List<int[]> DIRECTIONS = Arrays.asList(new int[] { 1, 0 }, new int[] { -1, 0 },
      new int[] { 0, 1 }, new int[] { 0, -1 });

  public void wallsAndGates(int[][] rooms) {
    Queue<int[]> q = new LinkedList<>();
    int row = rooms.length;
    int col = rooms[0].length;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (rooms[i][j] == GATE) {
          q.add(new int[] { i, j });
        }
      }
    }

    while (!q.isEmpty()) {
      int[] temp = q.poll();
      int r = temp[0];
      int c = temp[1];

      for (int[] direction : DIRECTIONS) {
        int nr = r + direction[0];
        int nc = c + direction[1];
        if (nr < 0 || nc < 0 || nr >= row || nc >= col || rooms[nr][nc] != EMPTY) {
          continue;
        }
        rooms[nr][nc] = rooms[r][c] + 1;
        q.add(new int[] { nr, nc });
      }
    }
  }

  public int findDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      if (set.contains(n)) {
        return n;
      }
      set.add(n);
    }
    return -1;
  }

  public int findDuplicateII(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == nums[i + 1]) {
        return nums[i];
      }
    }
    return -1;
  }

  public int findDuplicateIII(int[] nums) {
    int slow = 0, fast = 0;
    do {
      slow = nums[slow];
      fast = nums[nums[fast]];
    } while (slow != fast);
    slow = 0;
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[fast];
    }
    return slow;
  }

  class ValidWordAbbr {

    public Map<String, String> map;

    public ValidWordAbbr(String[] dictionary) {
      this.map = new HashMap<>();
      for (String s : dictionary) {
        String sort = getSort(s);
        if (map.containsKey(sort)) {
          String temp = map.get(sort);
          if (temp == null || temp.equals(s)) {
            continue;
          } else {
            map.put(sort, null);
          }
        } else {
          map.put(sort, s);
        }
      }
    }

    public String getSort(String s) {
      if (s.length() <= 2) {
        return s;
      } else {
        return new String(s.charAt(0) + Integer.toString(s.length() - 2) + s.charAt(s.length() - 1));
      }
    }

    public boolean isUnique(String word) {
      String sort = getSort(word);
      if (!map.containsKey(sort)) {
        return true;
      }
      String temp = map.get(sort);
      if (temp == null) {
        return false;
      }
      return temp.equals(word);
    }
  }

  public static final int[][] dir = new int[][] { new int[] { -1, -1 }, new int[] { -1, 0 }, new int[] { -1, 1 },
      new int[] { 0, 1 }, new int[] { 1, 1 }, new int[] { 1, 0 }, new int[] { 1, -1 }, new int[] { 0, -1 } };

  public void gameOfLife(int[][] board) {

    int[][] newBoard = new int[board.length][board[0].length];

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        int cell = getCellNum(board, row, col);
        boolean currentAlive = board[row][col] == 1;
        if (cell < 2 && currentAlive) {
          newBoard[row][col] = 0;
        } else if (cell <= 3 && currentAlive) {
          newBoard[row][col] = 1;
        } else if (cell > 3 && currentAlive) {
          newBoard[row][col] = 0;
        }
        if (cell == 3 && !currentAlive) {
          newBoard[row][col] = 1;
        }
      }
    }

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        board[row][col] = newBoard[row][col];
      }
    }
  }

  public int getCellNum(int[][] board, int row, int col) {
    int cell = 0;
    for (int[] d : dir) {
      int nextRow = row + d[0];
      int nextCol = col + d[1];
      if (nextRow < 0 || nextCol < 0 || nextRow >= board.length || nextCol >= board[0].length) {
        continue;
      }
      if (board[nextRow][nextCol] == 1) {
        cell++;
      }
    }
    return cell;
  }

  public boolean wordPattern(String pattern, String s) {
    Map<Character, String> c2s = new HashMap<>();
    Map<String, Character> s2c = new HashMap<>();
    String[] sa = s.split(" ");
    if (sa.length != pattern.length()) {
      return false;
    }
    for (int i = 0; i < pattern.length(); i++) {
      char temp = pattern.charAt(i);
      boolean c2sLive = c2s.containsKey(temp);
      boolean s2cLive = s2c.containsKey(sa[i]);
      if (c2sLive || s2cLive) {
        if (!(c2sLive && s2cLive)) {
          return false;
        }
        if (!(c2s.get(temp).equals(sa[i])) || s2c.get(sa[i]) != temp) {
          return false;
        }
        continue;
      }
      c2s.put(temp, sa[i]);
      s2c.put(sa[i], temp);
    }
    return true;
  }

  public boolean wordPatternMatch(String pattern, String s) {
    return wordPatternMatch(pattern, s, 0, new HashMap<>(), new HashMap<>());
  }

  public boolean wordPatternMatch(String pattern, String s, int patternIndex, Map<Character, String> c2s,
      Map<String, Character> s2c) {

    if (patternIndex == pattern.length()) {
      return s.length() == 0;
    }
    if (s.length() == 0) {
      return patternIndex == pattern.length();
    }

    char c = pattern.charAt(patternIndex);
    if (c2s.containsKey(c)) {
      if (s.startsWith(c2s.get(c))) {
        return wordPatternMatch(pattern, s.substring(c2s.get(c).length()), patternIndex + 1, c2s, s2c);
      } else {
        return false;
      }
    }
    for (int i = 0; i < s.length(); i++) {
      String tempS = s.substring(0, i);
      if (s2c.containsKey(tempS)) {
        continue;
      }
      s2c.put(tempS, c);
      c2s.put(c, tempS);
      if (wordPatternMatch(pattern, s.substring(i + 1), patternIndex + 1, c2s, s2c)) {
        return true;
      }
      s2c.remove(tempS);
      c2s.remove(c);
    }
    return false;
  }

  public boolean canWinNim(int n) {
    return n % 4 != 0;
  }

  public List<String> generatePossibleNextMoves(String currentState) {
    List<String> res = new ArrayList<>();
    if (currentState.length() < 2) {
      return res;
    }
    char[] ca = currentState.toCharArray();
    for (int i = 0; i < ca.length - 1; i++) {
      if (ca[i] == '+' && ca[i + 1] == '+') {
        ca[i] = '-';
        ca[i + 1] = '-';
        res.add(new String(ca));
        ca[i] = '+';
        ca[i + 1] = '+';
      }
    }
    return res;
  }

  public boolean canWin(String currentState) {
    if (currentState.length() == 1) {
      return false;
    }
    return canWin(currentState.toCharArray(), new HashMap<>());
  }

  public boolean canWin(char[] currentState, Map<String, Boolean> cache) {
    String current = new String(currentState);
    Boolean visited = cache.get(current);
    if (visited != null) {
      return visited;
    }
    for (int i = 0; i < currentState.length - 1; i++) {
      if (currentState[i] == '+' && currentState[i + 1] == '+') {
        currentState[i] = '-';
        currentState[i + 1] = '-';
        if (!canWin(currentState, cache)) {
          currentState[i] = '+';
          currentState[i + 1] = '+';
          cache.put(current, true);
          return true;
        }
        currentState[i] = '+';
        currentState[i + 1] = '+';
      }
    }
    cache.put(current, false);
    return false;
  }

  class MedianFinder {

    PriorityQueue<Integer> big;
    PriorityQueue<Integer> small;

    /** initialize your data structure here. */
    public MedianFinder() {
      this.big = new PriorityQueue<>();
      this.small = new PriorityQueue<>((x, y) -> y - x);
    }

    public void addNum(int num) {
      if (small.size() == big.size()) {
        big.add(num);
        small.add((big.poll()));
      } else {
        small.add(num);
        big.add(small.poll());
      }
    }

    public double findMedian() {
      if (big.size() == small.size()) {
        return (double) (big.peek() + small.peek()) / 2;
      } else {
        return small.peek();
      }
    }
  }

  public int minTotalDistance(int[][] grid) {
    List<int[]> persons = new ArrayList<>();
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == 1) {
          persons.add(new int[] { row, col });
        }
      }
    }

    int min = Integer.MAX_VALUE;

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        int temp = 0;
        for (int[] person : persons) {
          temp += Math.abs(row - person[0]) + Math.abs(col - person[1]);
        }
        min = Math.min(min, temp);
      }
    }
    return min;

  }

  public int minTotalDistanceII(int[][] grid) {
    List<Integer> rowPoints = new ArrayList<>();
    List<Integer> colPoints = new ArrayList<>();
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == 1) {
          rowPoints.add(row);
        }
      }
    }

    for (int col = 0; col < grid[0].length; col++) {
      for (int row = 0; row < grid.length; row++) {
        if (grid[row][col] == 1) {
          colPoints.add(col);
        }
      }
    }

    return computeDistance(rowPoints) + computeDistance(colPoints);

  }

  public int computeDistance(List<Integer> potions) {
    int i = 0, j = potions.size() - 1, res = 0;
    while (i < j) {
      res += potions.get(j) - potions.get(i);
      i++;
      j--;
    }
    return res;
  }

  public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      preorder(root, sb);
      return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      index = 0;
      String[] sa = data.split(",");
      return buildTree(sa);
    }

    public void preorder(TreeNode root, StringBuilder sb) {
      if (root == null) {
        sb.append("x,");
        return;
      }
      sb.append(root.val);
      sb.append(",");
      preorder(root.left, sb);
      preorder(root.right, sb);
    }

    public int index = 0;

    public TreeNode buildTree(String[] sa) {
      if (sa[index].equals("x")) {
        index++;
        return null;
      }
      TreeNode root = new TreeNode(Integer.parseInt(sa[index++]));
      root.left = buildTree(sa);
      root.right = buildTree(sa);
      return root;
    }

  }

}

package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.swing.border.Border;

import com.leetcode.entity.ListNode;

public class AlgorithmController201to220 {

  public int rangeBitwiseAnd(int m, int n) {
    while (n > m) {
      n &= n - 1;
    }
    return n;
  }

  public int rangeBitwiseAndII(int m, int n) {
    int bit = 0;
    while (m != n) {
      m >>= 1;
      n >>= 1;
      bit++;
    }
    return n << bit;
  }

  public boolean isHappy(int n) {
    Set<Integer> cache = new HashSet<>();
    while (true) {
      n = sumSquaresOfDigits(n);
      if (n == 1) {
        return true;
      } else if (cache.contains(n)) {
        return false;
      }
      cache.add(n);
    }
  }

  public int sumSquaresOfDigits(int n) {
    int res = 0;
    while (n > 0) {
      res += Math.pow(n % 10, 2);
      n /= 10;
    }
    return res;
  }

  public ListNode removeElements(ListNode head, int val) {
    ListNode temp = new ListNode();
    temp.next = head;
    ListNode index = temp;
    while (index != null && index.next != null) {
      if (index.next.val == val) {
        index.next = findDiffNode(index.next, val);
      }
      index = index.next;
    }
    return temp.next;
  }

  public ListNode findDiffNode(ListNode node, int val) {
    if (node == null) {
      return null;
    }
    return node.val != val ? node : findDiffNode(node.next, val);
  }

  public ListNode removeElementsII(ListNode head, int val) {
    if (head == null) {
      return null;
    }
    if (head.val == val) {
      return removeElementsII(head.next, val);
    }
    ListNode curr = head.next;
    ListNode prev = head;
    while (curr != null) {
      if (curr.val == val) {
        prev.next = curr.next;
      } else {
        prev = curr;
      }
      curr = curr.next;
    }
    return head;
  }

  public int countPrimes(int n) {
    int[] primes = new int[n];
    int res = 0;
    for (int i = 2; i < n; i++) {
      if (primes[i] == 0) {
        res++;
        if ((long) i * i < n) {
          for (int j = i * i; j < n; j += i) {
            primes[j] = 1;
          }
        }
      }
    }
    return res;
  }

  public boolean isIsomorphic(String s, String t) {
    if (s == null || t == null) {
      return s.equals(t);
    }
    Map<Character, Character> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char sa = s.charAt(i);
      char ta = t.charAt(i);
      if (map.containsKey(sa)) {
        if (map.get(sa) != ta) {
          return false;
        }
      } else if (map.containsValue(ta)) {
        return false;
      } else {
        map.put(sa, ta);
      }
    }
    return true;
  }

  public boolean isIsomorphicII(String s, String t) {
    char[] sa = s.toCharArray();
    char[] ta = t.toCharArray();
    int[] saIndex = new int[256];
    int[] taIndex = new int[256];
    for (int i = 0; i < sa.length; i++) {
      if (saIndex[sa[i]] != taIndex[ta[i]]) {
        return false;
      }
      saIndex[sa[i]] = i + 1;
      taIndex[ta[i]] = i + 1;
    }
    return true;
  }

  public ListNode reverseList(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode prev = null;
    ListNode current = head;
    while (current.next != null) {
      ListNode t = current.next;
      current.next = prev;
      prev = current;
      current = t;
    }
    current.next = prev;
    return current;
  }

  public boolean flag = true;
  public int[] visitor;
  public List<List<Integer>> edges;

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    visitor = new int[numCourses];
    edges = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      edges.add(new ArrayList<>());
    }
    for (int i = 0; i < prerequisites.length; i++) {
      edges.get(prerequisites[i][0]).add(prerequisites[i][1]);
    }
    for (int i = 0; i < numCourses && flag; i++) {
      if (visitor[i] == 0) {
        canFinish(i);
      }
    }
    return flag;
  }

  public void canFinish(int i) {
    visitor[i] = 1;
    for (int v : edges.get(i)) {
      if (visitor[v] == 0) {
        canFinish(v);
        if (!flag) {
          break;
        }
      } else if (visitor[v] == 1) {
        flag = false;
        return;
      }
    }
    visitor[i] = 2;
  }

  public boolean canFinishII(int numCourses, int[][] prerequisites) {
    int[] dep = new int[numCourses];
    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      list.add(new ArrayList<>());
    }
    for (int[] p : prerequisites) {
      list.get(p[1]).add(p[0]);
      dep[p[0]]++;
    }
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (dep[i] == 0) {
        q.offer(i);
      }
    }
    int visitor = 0;
    while (!q.isEmpty()) {
      visitor++;
      int t = q.poll();
      for (int val : list.get(t)) {
        dep[val]--;
        if (dep[val] == 0) {
          q.offer(val);
        }
      }
    }
    return visitor == numCourses;
  }

  class Trie {

    class TrieNode {
      private TrieNode[] nodes;
      private boolean isEnd;

      public TrieNode() {
        nodes = new TrieNode[26];
        isEnd = false;
      }

      public boolean containsKey(char c) {
        return nodes[c - 'a'] != null;
      }

      public TrieNode getNode(char c) {
        return nodes[c - 'a'];
      }

      public void putNode(char c, TrieNode node) {
        nodes[c - 'a'] = node;
      }

      public void setIsEnd() {
        isEnd = true;
      }

      public boolean getIsEnd() {
        return isEnd;
      }

    }

    public TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
      root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
      TrieNode node = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (!node.containsKey(c)) {
          node.putNode(c, new TrieNode());
        }
        node = node.getNode(c);
      }
      node.setIsEnd();
    }

    public TrieNode getPrefix(String word) {
      TrieNode node = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (node.containsKey(c)) {
          node = node.getNode(c);
        } else {
          return null;
        }
      }
      return node;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
      TrieNode node = getPrefix(word);
      return node != null && node.getIsEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
      TrieNode node = getPrefix(prefix);
      return node != null;
    }
  }

  public int minSubArrayLen(int s, int[] nums) {
    int res = Integer.MAX_VALUE;
    int window = 0;
    int left = 0;
    for (int i = 0; i < nums.length; i++) {
      window += nums[i];
      if (window >= s) {
        for (int j = left; j <= i; j++) {
          window -= nums[j];
          if (window < s) {
            res = Math.min(res, i - j + 1);
            left = j + 1;
            break;
          }
        }
      }
    }
    return res == Integer.MAX_VALUE ? 0 : res;
  }

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    visitor = new int[numCourses];
    edges = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      edges.add(new ArrayList<>());
    }
    for (int[] p : prerequisites) {
      edges.get(p[1]).add(p[0]);
    }
    Stack<Integer> s = new Stack<>();
    for (int i = 0; i < numCourses && flag; i++) {
      if (visitor[i] == 0) {
        findOrder(i, s);
      }
    }
    if (!flag) {
      return new int[0];
    }
    int[] res = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      res[i] = s.pop();
    }
    return res;
  }

  public void findOrder(int i, Stack<Integer> s) {
    visitor[i] = 1;
    for (int val : edges.get(i)) {
      if (visitor[val] == 0) {
        findOrder(val, s);
      } else if (visitor[val] == 1) {
        flag = false;
        return;
      }
    }
    visitor[i] = 2;
    s.add(i);
  }

  class WordDictionary {

    class TrieNode {
      private TrieNode[] nodes;
      private boolean isEnd;

      public TrieNode() {
        nodes = new TrieNode[26];
        isEnd = false;
      }

      public boolean containsKey(char c) {
        return nodes[c - 'a'] != null;
      }

      public TrieNode getNode(char c) {
        return nodes[c - 'a'];
      }

      public void putNode(char c, TrieNode node) {
        nodes[c - 'a'] = node;
      }

      public void setIsEnd() {
        isEnd = true;
      }

      public boolean getIsEnd() {
        return isEnd;
      }

    }

    public TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
      root = new TrieNode();
    }

    public void addWord(String word) {
      TrieNode node = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (!node.containsKey(c)) {
          node.putNode(c, new TrieNode());
        }
        node = node.getNode(c);
      }
      node.setIsEnd();
    }

    public boolean search(String word) {
      TrieNode node = getWordNode(word, root, 0);
      return node != null && node.getIsEnd();
    }

    public TrieNode getWordNode(String word, TrieNode current, int index) {
      TrieNode node = current;
      for (int i = index; i < word.length(); i++) {
        char c = word.charAt(i);
        if (c == '.') {
          for (int j = 0; j < node.nodes.length; j++) {
            if (node.nodes[j] != null) {
              TrieNode m = getWordNode(word, node.nodes[j], i + 1);
              if (m != null && m.getIsEnd()) {
                return m;
              }
            }
          }
          return null;
        } else {
          if (!node.containsKey(c)) {
            return null;
          }
          node = node.getNode(c);
        }
      }
      return node;
    }

  }

  class WordsTrie {
    Map<Character, WordsTrie> children;
    String word;

    public WordsTrie() {
      word = null;
      children = new HashMap<Character, WordsTrie>();
    }
  }

  public WordsTrie root;
  public List<String> findWordsRes;

  public List<String> findWords(char[][] board, String[] words) {
    findWordsRes = new ArrayList<>();
    root = new WordsTrie();
    for (String w : words) {
      WordsTrie node = root;
      for (Character c : w.toCharArray()) {
        if (node.children.containsKey(c)) {
          node = node.children.get(c);
        } else {
          WordsTrie t = new WordsTrie();
          node.children.put(c, t);
          node = t;
        }
      }
      node.word = w;
    }
    for (int y = 0; y < board.length; y++) {
      for (int x = 0; x < board[0].length; x++) {
        if (root.children.containsKey(board[y][x])) {
          findWords(x, y, board, root);
        }
      }
    }
    return findWordsRes;
  }

  public void findWords(int x, int y, char[][] board, WordsTrie current) {
    char letter = board[y][x];
    WordsTrie temp = current.children.get(letter);
    if (temp.word != null) {
      findWordsRes.add(temp.word);
      temp.word = null;
    }

    board[y][x] = '#';

    int[] xOffset = { -1, 0, 1, 0 };
    int[] yOffset = { 0, -1, 0, 1 };
    for (int i = 0; i < 4; i++) {
      int nextX = x + xOffset[i];
      int nextY = y + yOffset[i];
      if (nextX < 0 || nextY < 0 || nextX >= board[0].length || nextY >= board.length) {
        continue;
      }
      if (temp.children.containsKey(board[nextY][nextX]) && board[nextY][nextX] != '#') {
        findWords(nextX, nextY, board, temp);
      }
    }

    board[y][x] = letter;
    if (temp.children.isEmpty()) {
      current.children.remove(letter);
    }
  }

  public int rob(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    int m1 = 0;
    int[] dp = new int[nums.length + 2];
    for (int i = 0; i < nums.length - 1; i++) {
      dp[i + 2] = Math.max(dp[i + 1], dp[i] + nums[i]);
    }
    m1 = dp[dp.length - 2];
    Arrays.fill(dp, 0);
    for (int i = 1; i < nums.length; i++) {
      dp[i + 2] = Math.max(dp[i + 1], dp[i] + nums[i]);
    }
    return Math.max(m1, dp[dp.length - 1]);
  }

  public String shortestPalindrome(String s) {
    if (s.length() <= 1) {
      return s;
    }
    char[] sa = s.toCharArray();
    int right = sa.length - 1;
    while (right > 0) {
      if (sa[right] == sa[0]) {
        int l = 0;
        int r = right;
        while (r >= 0 && l < sa.length && sa[r] == sa[l]) {
          r--;
          l++;
        }
        if (l > r) {
          break;
        }
      }
      right--;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = sa.length - 1; i > right; i--) {
      sb.append(sa[i]);
    }
    sb.append(s);
    return sb.toString();
  }

  public Random r = new Random();

  public int findKthLargest(int[] nums, int k) {
    return findKthLargest(nums, 0, nums.length - 1, k - 1);
  }

  public int findKthLargest(int[] nums, int left, int right, int k) {
    int i = quickSort(nums, left, right);
    if (i == k) {
      return nums[i];
    } else {
      return i < k ? findKthLargest(nums, i + 1, right, k) : findKthLargest(nums, left, i - 1, k);
    }
  }

  public int quickSort(int[] nums, int left, int right) {
    int temp = r.nextInt(right - left + 1) + left;
    swap(nums, left, temp);
    int bash = nums[left];
    int j = left + 1;
    for (int i = left + 1; i <= right; i++) {
      if (nums[i] > bash) {
        swap(nums, i, j++);
      }
    }
    swap(nums, left, j - 1);
    return j - 1;
  }

  public void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public int findKthLargestII(int[] nums, int k) {
    for (int i = nums.length / 2 - 1; i >= 0; i--) {
      adjustHeap(nums, i, nums.length);
    }
    for (int i = nums.length - 1; i >= 0; i--) {
      k--;
      swap(nums, 0, i);
      if (k == 0) {
        return nums[i];
      }
      adjustHeap(nums, 0, i);
    }
    return 0;
  }

  public void adjustHeap(int[] nums, int i, int right) {
    int temp = nums[i];
    for (int j = 2 * i + 1; j < right; j = 2 * j + 1) {
      if (j + 1 < right && nums[j] < nums[j + 1]) {
        j++;
      }
      if (temp < nums[j]) {
        nums[i] = nums[j];
        i = j;
      } else {
        break;
      }
    }
    nums[i] = temp;
  }

  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> res = new ArrayList<>();
    combinationSum3(res, new ArrayList<>(), 1, n, 0, k);
    return res;
  }

  public void combinationSum3(List<List<Integer>> res, List<Integer> current, int index, int sum, int currentSum,
      int maxLen) {
    for (int i = index; i <= 9; i++) {
      current.add(i);
      if (currentSum + i == sum && current.size() == maxLen) {
        List<Integer> temp = new ArrayList<>(current);
        res.add(temp);
        current.remove(current.size() - 1);
        return;
      }
      combinationSum3(res, current, i + 1, sum, currentSum + i, maxLen);
      current.remove(current.size() - 1);
    }
  }

  public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      if (set.contains(n)) {
        return true;
      }
      set.add(n);
    }
    return false;
  }

  public boolean containsDuplicateII(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
        return true;
      }
    }
    return false;
  }

}

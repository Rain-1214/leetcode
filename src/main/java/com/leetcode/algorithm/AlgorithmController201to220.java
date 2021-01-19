package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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

}

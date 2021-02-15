package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import com.leetcode.entity.TreeNode;

public class AlgorithmController221to241 {
  public int maximalSquare(char[][] matrix) {
    int y = matrix.length;
    int x = matrix[0].length;
    int[][] dp = new int[y][x];
    int res = 0;

    for (int i = 0; i < y; i++) {
      for (int j = 0; j < x; j++) {
        if (matrix[i][j] == '1') {
          if (i == 0 || j == 0) {
            dp[i][j] = 1;
          } else {
            dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
          }
          res = Math.max(dp[i][j], res);
        }

      }
    }
    return res * res;
  }

  public int countNodes;

  public int countNodes(TreeNode root) {
    countNodesHelp(root);
    return countNodes;
  }

  public void countNodesHelp(TreeNode root) {
    if (root == null) {
      return;
    }
    this.countNodes += 1;
    this.countNodes(root.left);
    this.countNodes(root.right);
  }

  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    if (E >= C || F >= D || A >= G || B >= H) {
      return (C - A) * (D - B) + (G - E) * (H - F);
    }
    int left = Math.max(A, E);
    int right = Math.min(C, G);
    int top = Math.min(D, H);
    int bottom = Math.max(B, F);
    return (C - A) * (D - B) - (right - left) * (top - bottom) + (G - E) * (H - F);
  }

  public int calculate(String s) {
    Stack<Integer> sk = new Stack<>();
    int operator = 0;
    int res = 0;
    int sign = 1;
    for (int i = 0; i < s.length(); i++) {
      char ca = s.charAt(i);
      if (Character.isDigit(ca)) {
        operator = operator * 10 + (ca - '0');
      } else if (ca == '+') {
        res += sign * operator;
        sign = 1;
        operator = 0;
      } else if (ca == '-') {
        res += sign * operator;
        sign = -1;
        operator = 0;
      } else if (ca == '(') {
        sk.add(res);
        sk.add(sign);
        res = 0;
        sign = 1;
      } else if (ca == ')') {
        res += sign * operator;
        res *= sk.pop();
        res += sk.pop();
        operator = 0;
      }
    }
    return res + (sign * operator);
  }

  class MyStack {

    Queue<Integer> q;

    int last;

    /** Initialize your data structure here. */
    public MyStack() {
      this.q = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
      this.q.offer(x);
      this.last = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
      Queue<Integer> temp = new LinkedList<>();
      int x = 0;
      while (!q.isEmpty()) {
        this.last = x;
        x = q.poll();
        if (!q.isEmpty()) {
          temp.offer(x);
        }
      }
      q = temp;
      return x;
    }

    /** Get the top element. */
    public int top() {
      return this.last;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
      return this.q.isEmpty();
    }
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    invertTree(root.left);
    invertTree(root.right);
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
    return root;
  }

  public int calculateII(String s) {
    char[] ca = s.toCharArray();
    Stack<Integer> sk = new Stack<>();
    int operator = 0;
    char sign = '+';
    for (int i = 0; i < ca.length; i++) {
      char c = ca[i];
      if (Character.isDigit(c)) {
        operator = operator * 10 + (c - '0');
      }
      if ((!Character.isDigit(c) && c != ' ') || i == ca.length - 1) {
        if (sign == '+') {
          sk.add(operator);
        } else if (sign == '-') {
          sk.add(-operator);
        } else if (sign == '*') {
          sk.add(sk.pop() * operator);
        } else if (sign == '/') {
          sk.add(sk.pop() / operator);
        }
        sign = c;
        operator = 0;
      }
    }
    int res = 0;
    while (!sk.isEmpty()) {
      res += sk.pop();
    }
    return res;
  }

  public List<Integer> majorityElement(int[] nums) {
    List<Integer> res = new ArrayList<>();
    Map<Integer, Integer> cache = new HashMap<>();
    int size = nums.length / 3 + 1;
    for (int num : nums) {
      int time = cache.getOrDefault(num, 0) + 1;
      if (time > size) {
        continue;
      }
      if (time == size) {
        res.add(num);
      }
      cache.put(num, time);
    }
    return res;
  }

  public List<Integer> majorityElementII(int[] nums) {
    List<Integer> res = new ArrayList<>();
    int max1 = nums[0];
    int max1Cand = 0;

    int max2 = nums[0];
    int max2Cand = 0;

    for (int num : nums) {
      if (num == max1) {
        max1Cand++;
        continue;
      }
      if (num == max2) {
        max2Cand++;
        continue;
      }

      if (max1Cand == 0) {
        max1 = num;
        max1Cand++;
        continue;
      }

      if (max2Cand == 0) {
        max2 = num;
        max2Cand++;
        continue;
      }

      max1Cand--;
      max2Cand--;
    }

    max1Cand = 0;
    max2Cand = 0;
    for (int num : nums) {
      if (num == max1) {
        max1Cand++;
      }
      if (num == max2 && max2 != max1) {
        max2Cand++;
      }
    }
    if (max2Cand > nums.length / 3) {
      res.add(max2);
    }
    if (max1Cand > nums.length / 3) {
      res.add(max1);
    }
    return res;
  }

  public int kthSmallestTime = 0;

  public int kthSmallest(TreeNode root, int k) {
    if (root == null) {
      return -1;
    }
    int val = kthSmallest(root.left, k);
    if (val != -1) {
      return val;
    }
    kthSmallestTime += 1;
    if (k == kthSmallestTime) {
      return root.val;
    }
    val = kthSmallest(root.right, k);
    if (val != -1) {
      return val;
    }
    return -1;
  }

}

package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.leetcode.entity.ListNode;
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

  public boolean isPowerOfTwo(int n) {
    if (n == 0) {
      return false;
    }
    long x = (long) n;
    return (x & (x - 1)) == 0;
  }

  public boolean isPowerOfTwoII(int n) {
    if (n == 0) {
      return false;
    }
    long x = (long) n;
    return (x & -x) == x;
  }

  class MyQueue {

    public Stack<Integer> s;
    public Stack<Integer> cache;

    public Integer top = null;

    /** Initialize your data structure here. */
    public MyQueue() {
      this.s = new Stack<>();
      this.cache = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
      this.s.add(x);
      if (this.top == null) {
        this.top = x;
      }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
      while (!s.isEmpty()) {
        cache.add(s.pop());
      }
      int t = cache.pop();
      if (!cache.isEmpty()) {
        this.top = cache.peek();
      } else {
        this.top = null;
      }
      while (!cache.isEmpty()) {
        s.add(cache.pop());
      }
      return t;
    }

    /** Get the front element. */
    public int peek() {
      return this.top;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return this.s.isEmpty();
    }
  }

  class MyQueueII {

    public Stack<Integer> s;
    public Stack<Integer> reverse;

    public Integer top = null;

    /** Initialize your data structure here. */
    public MyQueueII() {
      this.s = new Stack<>();
      this.reverse = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
      if (this.s.isEmpty()) {
        this.top = x;
      }
      this.s.add(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
      if (this.reverse.isEmpty()) {
        while (!this.s.isEmpty()) {
          this.reverse.add(this.s.pop());
        }
      }
      return this.reverse.pop();
    }

    /** Get the front element. */
    public int peek() {
      if (!this.reverse.isEmpty()) {
        return this.reverse.peek();
      }
      return this.top;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return this.s.isEmpty() && this.reverse.isEmpty();
    }
  }

  public int countDigitOne(int n) {
    int count = 0;
    for (long i = 1; i <= n; i *= 10) {
      long div = i * 10;
      count += (n / div) * i + Math.min(Math.max(n % div - i + 1, 0), i);
    }
    return count;
  }

  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    ListNode fast = head;
    ListNode slow = head;
    ListNode prev = null;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      ListNode temp = slow.next;
      slow.next = prev;
      prev = slow;
      slow = temp;
    }
    ListNode tail = slow.next;
    if (fast.next == null) {
      slow = prev;
    } else {
      slow.next = prev;
    }
    while (slow != null) {
      if (slow.val != tail.val) {
        return false;
      }
      slow = slow.next;
      tail = tail.next;
    }
    return true;
  }

  // .......
  public boolean isPalindromeII(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    ListNode newHead = isPalindromeIIHelp(slow.next);

    while (head != null && newHead != null) {
      if (head.val != newHead.val) {
        return false;
      }
      head = head.next;
      newHead = newHead.next;
    }

    return true;
  }

  public ListNode isPalindromeIIHelp(ListNode head) {
    if (head.next == null) {
      return head;
    }
    ListNode newHead = isPalindromeIIHelp(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    Queue<Integer> queue = new LinkedList<>();
    TreeNode f = root;
    while (f.val != p.val) {
      queue.add(f.val);
      f = f.val > p.val ? f.left : f.right;
    }
    queue.add(p.val);
    f = root;
    TreeNode prev = root;
    while (true) {
      if (queue.peek() != f.val) {
        return prev;
      }
      queue.poll();
      if (queue.isEmpty()) {
        return f;
      }
      prev = f;
      f = f.val > q.val ? f.left : f.right;
    }
  }

  public TreeNode lowestCommonAncestorIITooSlow(TreeNode root, TreeNode p, TreeNode q) {
    Stack<TreeNode> pp = new Stack<>();
    Stack<TreeNode> qp = new Stack<>();
    lowestCommonAncestorIIHelp(root, p, pp);
    lowestCommonAncestorIIHelp(root, q, qp);
    TreeNode pPrev = null;
    TreeNode qPrev = null;
    while (!pp.isEmpty() && !qp.isEmpty()) {
      if (pp.peek().val != qp.peek().val) {
        return pPrev;
      }
      pPrev = pp.pop();
      qPrev = qp.pop();
    }
    return pp.isEmpty() ? pPrev : qPrev;
  }

  public boolean lowestCommonAncestorIIHelp(TreeNode root, TreeNode target, Stack<TreeNode> path) {
    if (root == null) {
      return false;
    }
    if (root.val == target.val || lowestCommonAncestorIIHelp(root.left, target, path)
        || lowestCommonAncestorIIHelp(root.right, target, path)) {
      path.add(root);
      return true;
    }
    return false;
  }

  public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == q || root == p) {
      return root;
    }
    TreeNode left = lowestCommonAncestorII(root.left, p, q);
    TreeNode right = lowestCommonAncestorII(root.right, p, q);
    if (left == null) {
      return right;
    }
    if (right == null) {
      return left;
    }
    if (left != null && right != null) {
      return root;
    }
    return null;
  }

  // ... delete?
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }

  public int[] productExceptSelf(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return nums;
    }
    int len = nums.length;
    int[] positive = new int[len];
    int[] reverse = new int[len];
    positive[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      positive[i] = positive[i - 1] * nums[i];
    }
    reverse[len - 1] = nums[len - 1];
    for (int i = len - 2; i >= 0; i--) {
      reverse[i] = reverse[i + 1] * nums[i];
    }
    int[] res = new int[len];
    res[0] = reverse[1];
    res[len - 1] = positive[len - 2];
    for (int i = 1; i < len - 1; i++) {
      res[i] = positive[i - 1] * reverse[i + 1];
    }
    return res;
  }

  public int[] productExceptSelfII(int[] nums) {
    int len = nums.length;
    int[] res = new int[len];
    int m = 1;
    for (int i = 0; i < len; i++) {
      res[i] = m;
      m *= nums[i];
    }
    int n = 1;
    for (int i = len - 1; i >= 0; i--) {
      res[i] = res[i] * n;
      n *= nums[i];
    }
    return res;
  }

  public int[] maxSlidingWindow(int[] nums, int k) {
    int[] res = new int[nums.length - k + 1];
    LinkedList<Integer> l = new LinkedList<>();
    l.add(nums[0]);
    for (int i = 1; i < k; i++) {
      insertLinkedList(l, nums[i]);
    }
    res[0] = l.peek();
    for (int i = k; i < nums.length; i++) {
      l.remove(new Integer(nums[i - k]));
      insertLinkedList(l, nums[i]);
      res[i - k + 1] = l.peek();
    }
    return res;
  }

  public void insertLinkedList(LinkedList<Integer> l, int val) {
    if (l.isEmpty() || val <= l.getLast()) {
      l.add(val);
      return;
    }
    for (int i = 0; i < l.size(); i++) {
      if (val >= l.get(i)) {
        l.add(i, val);
        break;
      }
    }
  }

  public int[] maxSlidingWindowII(int[] nums, int k) {
    int[] res = new int[nums.length - k + 1];
    PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 > o2 ? -1 : 1;
      }
    });
    for (int i = 0; i < k; i++) {
      q.add(nums[i]);
    }
    res[0] = q.peek();
    for (int i = k; i < nums.length; i++) {
      q.remove(new Integer(nums[i - k]));
      q.add(nums[i]);
      res[i - k + 1] = q.peek();
    }
    return res;
  }

  public int[] maxSlidingWindowIII(int[] nums, int k) {
    int[] res = new int[nums.length - k + 1];
    PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0] > o2[0] ? -1 : 1;
      }
    });
    for (int i = 0; i < k; i++) {
      q.add(new int[] { nums[i], i });
    }
    res[0] = q.peek()[0];
    for (int i = k; i < nums.length; i++) {
      q.add(new int[] { nums[i], i });
      while (q.peek()[1] <= i - k) {
        q.poll();
      }

      res[i - k + 1] = q.peek()[0];
    }
    return res;
  }

  public int[] maxSlidingWindowIV(int[] nums, int k) {
    int[] res = new int[nums.length - k + 1];
    Deque<Integer> q = new LinkedList<>();
    for (int i = 0; i < k; i++) {
      while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]) {
        q.pollLast();
      }
      q.offerLast(i);
    }
    res[0] = q.peekFirst();
    for (int i = k; i < nums.length - 1; i++) {
      while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]) {
        q.pollLast();
      }
      q.offerLast(i);
      while (q.peekFirst() <= i - k) {
        q.pollFirst();
      }
      res[i - k + 1] = nums[q.peekFirst()];
    }
    return res;
  }

  public boolean searchMatrix(int[][] matrix, int target) {
    return searchMatrix(matrix, 0, 0, target);
  }

  public boolean searchMatrix(int[][] matrix, int x, int y, int target) {
    if (x >= matrix[0].length || y >= matrix.length || matrix[y][x] > target) {
      return false;
    }
    if (target == matrix[y][x]) {
      return true;
    }
    return searchMatrix(matrix, x + 1, y, target) || searchMatrix(matrix, x, y + 1, target);
  }

  public boolean searchMatrixII(int[][] matrix, int target) {
    int xMax = matrix[0].length;
    int yMax = matrix.length;
    int x = 0;
    int y = yMax;
    while (matrix[y][x] != target) {
      if (matrix[y][x] > target) {
        y--;
      } else {
        x++;
      }
      if (x == xMax || y < 0) {
        return false;
      }
    }
    return true;
  }

}

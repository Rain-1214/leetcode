package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.Read4;
import com.leetcode.entity.TreeNode;

public class AlgorithmController141to160 extends Read4 {

  public boolean hasCycleTooSlow(ListNode head) {
    return hasCycle(head, new HashSet<>());
  }

  public boolean hasCycle(ListNode head, Set<ListNode> set) {
    if (head.next == null) {
      return true;
    }
    if (set.contains(head)) {
      return false;
    }
    set.add(head);
    return hasCycle(head.next, set);
  }

  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode slow = head.next;
    ListNode fast = head.next.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }

  public ListNode detectCycleTooSlow(ListNode head) {
    return detectCycle(head, new HashSet<>());
  }

  public ListNode detectCycle(ListNode head, Set<ListNode> set) {
    if (head.next == null) {
      return null;
    }
    if (set.contains(head)) {
      return head;
    }
    set.add(head);
    return detectCycle(head.next, set);
  }

  /**
   * 2 * slow(distance) = fast(distance)
   * https://web.archive.org/web/20160401024212/http://learningarsenal.info:80/index.php/2015/08/24/detecting-start-of-a-loop-in-singly-linked-list/
   */
  public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return null;
    }
    ListNode slow = head.next;
    ListNode fast = head.next.next;
    while (slow != fast) {
      if (fast.next == null || fast.next.next == null) {
        return null;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    slow = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow;
  }

  public void reorderListToolSlow(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return;
    }
    ListNode slow = head.next;
    ListNode fast = head.next.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode last = slow.next;
    slow.next = null;
    Stack<ListNode> s = new Stack<>();
    while (last != null) {
      s.push(last);
      last = last.next;
    }
    ListNode current = head;
    while (!s.isEmpty()) {
      ListNode next = current.next;
      current.next = s.pop();
      current.next.next = next;
      current = next;
    }
  }

  public ListNode reverse(ListNode node) {
    if (node == null || node.next == null) {
      return node;
    }
    ListNode prev = null;
    ListNode curr = node;
    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = temp;
    }
    return prev;
  }

  public void reorderList(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode flow = slow.next;
    slow.next = null;
    flow = reverse(flow);
    ListNode current = head;
    ListNode node = flow;
    while (current != null && node != null) {
      ListNode c = current.next;
      ListNode n = node.next;

      current.next = node;
      node.next = c;

      current = c;
      node = n;
    }
  }

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    preorderTraversal(root, res);
    return res;
  }

  public void preorderTraversal(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    res.add(root.val);
    preorderTraversal(root.left, res);
    preorderTraversal(root.right, res);
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    postorderTraversal(root, res);
    return res;
  }

  public void postorderTraversal(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    postorderTraversal(root.left, res);
    postorderTraversal(root.right, res);
    res.add(root.val);
  }

  class LRUCacheTooSlow {

    public int capacity;
    public LinkedList<Integer> q = new LinkedList<>();
    public Map<Integer, Integer> dic = new HashMap<>();

    public LRUCacheTooSlow(int capacity) {
      this.capacity = capacity;
    }

    public int get(int key) {
      if (dic.containsKey(key)) {
        q.remove(new Integer(key));
        q.addFirst(key);
        return dic.get(key);
      }
      return -1;
    }

    public void put(int key, int value) {
      if (dic.containsKey(key)) {
        dic.put(key, value);
        q.remove(new Integer(key));
        q.addFirst(key);
        return;
      }
      if (q.size() == capacity) {
        int last = q.removeLast();
        dic.remove(last);
      }
      q.addFirst(key);
      dic.put(key, value);
    }

  }

  public static class LRUCache {

    class DLinkNode {
      public int val = 0;
      public int key = 0;
      public DLinkNode prev = null;
      public DLinkNode next = null;

      public DLinkNode() {
      }

      public DLinkNode(int key, int val) {
        this.val = val;
        this.key = key;
      }

      public DLinkNode(int val, DLinkNode prev, DLinkNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
      }

      @Override
      public String toString() {
        int maxSize = 0;
        String res = "";
        DLinkNode node = this;
        while (node != null) {
          res += node.val;
          node = node.next;
          maxSize++;
          if (maxSize > 100) {
            break;
          }
        }
        return res;
      }

    }

    public int capacity;
    public int size = 0;
    public Map<Integer, DLinkNode> cache = new HashMap<>();
    public DLinkNode head = null;
    public DLinkNode tail = null;

    public LRUCache(int capacity) {
      this.capacity = capacity;
      head = new DLinkNode();
      tail = new DLinkNode();
      head.next = tail;
      tail.prev = head;
    }

    public int get(int key) {
      if (!cache.containsKey(key)) {
        return -1;
      }
      DLinkNode node = cache.get(key);
      moveToHead(node);
      return node.val;
    }

    public void moveToHead(DLinkNode node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
      node.next = head.next;
      head.next.prev = node;
      node.prev = head;
      head.next = node;
    }

    public void addNode(DLinkNode node) {
      DLinkNode h1 = head.next;
      head.next = node;
      node.next = h1;
      node.prev = head;
      h1.prev = node;
    }

    public void removeLast() {
      DLinkNode last = tail.prev;
      last.prev.next = tail;
      tail.prev = last.prev;
      cache.remove(last.key);
    }

    public void put(int key, int value) {
      if (cache.containsKey(key)) {
        DLinkNode node = cache.get(key);
        node.val = value;
        moveToHead(node);
        return;
      }
      if (size == capacity) {
        removeLast();
      } else {
        size++;
      }
      DLinkNode temp = new DLinkNode(key, value);
      addNode(temp);
      cache.put(key, temp);
    }

  }

  public ListNode insertionSortListTooSlow(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode newHead = new ListNode(Integer.MIN_VALUE);
    ListNode current = head;
    while (current != null) {
      ListNode temp = current.next;
      ListNode newCurrent = newHead;
      while (newCurrent.next != null && current.val >= newCurrent.next.val) {
        newCurrent = newCurrent.next;
      }
      current.next = newCurrent.next;
      newCurrent.next = current;
      current = temp;
    }
    return newHead.next;
  }

  public ListNode insertionSortList(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode newHead = new ListNode(Integer.MIN_VALUE);
    newHead.next = head;
    ListNode current = head;
    while (current != null && current.next != null) {
      if (current.val <= current.next.val) {
        current = current.next;
      } else {
        ListNode shouldMove = current.next;
        current.next = shouldMove.next;
        ListNode temp = newHead;
        while (shouldMove.val > temp.next.val) {
          temp = temp.next;
        }
        shouldMove.next = temp.next;
        temp.next = shouldMove;
      }
    }

    return newHead.next;
  }

  /**
   * emmmm....... 对于已经排序过的大部分的List 快排效率太低了。 emmmm.......
   * 大佬说要先检查是不是排好了。。orz。。。WTF！！！！！！
   */
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    boolean sortedAsc = true;
    int prevVal = head.val;
    ListNode curr = head.next;

    while (curr != null && sortedAsc) {
      sortedAsc = sortedAsc && curr.val >= prevVal;
      prevVal = curr.val;
      curr = curr.next;
    }

    if (sortedAsc) {
      return head;
    }

    ListNode mid = head;
    ListNode left = new ListNode(0);
    ListNode right = new ListNode(0);
    ListNode c = head.next;
    ListNode lc = left;
    ListNode rc = right;
    while (c != null) {
      if (c.val <= mid.val) {
        lc.next = c;
        lc = lc.next;
      } else {
        rc.next = c;
        rc = rc.next;
      }
      c = c.next;
    }
    lc.next = null;
    rc.next = null;
    left = sortList(left.next);
    right = sortList(right.next);
    mid.next = right;
    if (left != null) {
      lc = left;
      while (lc.next != null) {
        lc = lc.next;
      }
      lc.next = mid;
      return left;
    }
    return mid;
  }

  public ListNode sortListTooSlow(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode slow = head;
    ListNode fast = head;
    ListNode slowLast = head;
    while (fast != null && fast.next != null) {
      slowLast = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    slowLast.next = null;
    return mergeListNode(sortListTooSlow(head), sortListTooSlow(slow));
  }

  public ListNode mergeListNode(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    ListNode temp = new ListNode(0);
    ListNode current = temp;
    while (l1 != null || l2 != null) {
      if (l2.val < l1.val) {
        current.next = l2;
        l2 = l2.next;
      } else {
        current.next = l1;
        l1 = l1.next;
      }
      current = current.next;
    }
    if (l1 != null) {
      current.next = l1;
    }
    if (l2 != null) {
      current.next = l2;
    }
    return temp.next;
  }

  public int maxPoints(int[][] points) {
    int max = 0;
    Map<Integer, Map<Integer, Integer>> m = new HashMap<>();
    for (int i = 0; i < points.length; i++) {
      m.clear();
      int duplicate = 1;
      int curMax = 0;
      for (int j = i + 1; j < points.length; j++) {
        if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
          duplicate += 1;
          continue;
        }
        int dx = points[j][0] - points[i][0];
        int dy = points[j][1] - points[i][1];
        int d = gcd(dy, dx);
        dx /= d;
        dy /= d;
        Map<Integer, Integer> t = m.getOrDefault(dx, new HashMap<>());
        int cp = t.getOrDefault(dy, 0) + 1;
        t.put(dy, cp);
        curMax = Math.max(curMax, cp);
        m.put(dx, t);
      }
      max = Math.max(max, duplicate + curMax);
    }
    return max;
  }

  public int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  public int evalRPN(String[] tokens) {
    Stack<Integer> s = new Stack<>();
    Set<String> set = new HashSet<>();
    set.add("+");
    set.add("-");
    set.add("*");
    set.add("/");
    for (String t : tokens) {
      if (set.contains(t)) {
        int n2 = s.pop();
        int n1 = s.pop();
        if (t.equals("+")) {
          s.push(n1 + n2);
        } else if (t.equals("-")) {
          s.push(n1 - n2);
        } else if (t.equals("*")) {
          s.push(n1 * n2);
        } else {
          s.push(n1 / n2);
        }
      } else {
        s.push(Integer.parseInt(t));
      }
    }
    return s.pop();
  }

  public String reverseWords(String s) {
    char[] sca = s.toCharArray();
    int si = 0;
    int fi = 1;
    StringBuilder sb = new StringBuilder();
    while (si < sca.length) {
      if (sca[si] == ' ') {
        si++;
        continue;
      }
      if (sb.length() > 0) {
        sb.insert(0, ' ');
      }
      fi = si + 1;
      while (fi < sca.length && sca[fi] != ' ') {
        fi++;
      }
      sb.insert(0, s.substring(si, fi));
      si = fi;
    }
    return sb.toString();
  }

  public int maxProduct(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int min = nums[0];
    int max = nums[0];
    int res = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int c = nums[i];
      int tempMin = min;
      min = Math.min(Math.min(min * c, max * c), c);
      max = Math.max(Math.max(tempMin * c, max * c), c);
      res = Math.max(res, max);
    }
    return res;
  }

  public int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = (left + right) / 2;
      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return nums[right];
  }

  public int findMinII(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = (left + right) / 2;
      if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
        left++;
        right--;
        continue;
      }
      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return nums[right];
  }

  class MinStack {

    public ArrayList<Integer> l;
    public int min;

    /** initialize your data structure here. */
    public MinStack() {
      this.l = new ArrayList<Integer>();
      this.min = Integer.MAX_VALUE;
    }

    public void push(int x) {
      l.add(x);
      min = Math.min(x, min);
    }

    public void pop() {
      int t = l.get(l.size() - 1);
      l.remove(l.size() - 1);
      if (t == min) {
        min = _getMin();
      }
    }

    public int top() {
      return l.get(l.size() - 1);
    }

    public int getMin() {
      return min;
    }

    public int _getMin() {
      int min = Integer.MAX_VALUE;
      for (int m : l) {
        min = Math.min(m, min);
      }
      return min;
    }
  }

  public TreeNode upsideDownBinaryTree(TreeNode root) {
    if (root == null || root.left == null) {
      return root;
    }
    TreeNode l = root.left;
    TreeNode r = root.right;
    TreeNode t = upsideDownBinaryTree(l);
    root.left = null;
    root.right = null;
    l.left = r;
    l.right = root;
    return t;
  }

  public int read(char[] buf, int n) {
    char[] temp = new char[4];
    for (int i = 0; i < n; i += 4) {
      int c = read4(temp);
      for (int j = 0; j < c; j++) {
        buf[i + j] = temp[j];
        // 注掉也能过。。但是可能会在某些情况下多写
        // if (i + j == n) {
        // break;
        // }
        temp[j] = ' ';
      }
      if (c < 4) {
        return Math.min(i + c, n);
      }
    }
    return n;
  }

  public char[] cache = new char[4];
  public int index = 0;
  public int max = 0;

  public int readII(char[] buf, int n) {

    for (int i = 0; i < n; i++) {
      if (index == max) {
        max = read4(cache);
        index = 0;
        if (max == 0) {
          return i;
        }
      }
      buf[i] = cache[index++];
    }
    return n;
  }

  public int lengthOfLongestSubstringTwoDistinctTooSlow(String s) {
    char[] ca = s.toCharArray();
    int len = ca.length;
    if (len < 2) {
      return len;
    }
    Map<Character, Integer> cache = new HashMap<>();
    int left = 0, right = 0;
    int charNum = 0;
    int max = 0;
    while (left < len && right < len) {
      if (cache.containsKey(ca[right])) {
        cache.put(ca[right], cache.getOrDefault(ca[right], 0) + 1);
        right++;
      } else {
        if (charNum == 2) {
          while (charNum == 2) {
            int ln = cache.get(ca[left]);
            if (ln == 1) {
              cache.remove(ca[left]);
              left++;
              charNum--;
              break;
            } else {
              cache.put(ca[left], ln - 1);
            }
            left++;
          }
        } else {
          charNum++;
          cache.put(ca[right], 1);
          right++;
        }
      }
      max = Math.max(max, right - left);
    }
    return max;
  }

  public int lengthOfLongestSubstringTwoDistinct(String s) {
    char[] ca = s.toCharArray();
    int len = ca.length;
    if (len < 2) {
      return len;
    }
    char[] cache = new char[128];
    int left = 0, right = 0;
    int charNum = 0;
    int max = 0;
    while (left < len && right < len) {
      if (cache[ca[right]] != 0) {
        cache[ca[right]]++;
        right++;
      } else {
        if (charNum == 2) {
          while (charNum == 2) {
            cache[ca[left]]--;
            if (cache[ca[left]] == 0) {
              left++;
              charNum--;
              break;
            }
            left++;
          }
        } else {
          charNum++;
          cache[ca[right]]++;
          right++;
        }
      }
      max = Math.max(max, right - left);
    }
    return max;
  }

  public ListNode getIntersectionNodeTooSlow(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    Set<ListNode> set = new HashSet<>();
    ListNode c = headA;
    while (c != null) {
      set.add(c);
      c = c.next;
    }
    c = headB;
    while (c != null) {
      if (set.contains(c)) {
        return c;
      }
      c = c.next;
    }
    return null;
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode a = headA;
    ListNode b = headB;
    while (a != b) {
      a = a == null ? headB : a.next;
      b = b == null ? headA : b.next;
    }
    return a;
  }

}

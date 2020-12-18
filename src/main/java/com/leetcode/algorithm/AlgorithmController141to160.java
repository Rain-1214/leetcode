package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.TreeNode;

public class AlgorithmController141to160 {

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

}

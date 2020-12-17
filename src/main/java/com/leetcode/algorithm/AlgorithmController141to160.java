package com.leetcode.algorithm;

import java.util.HashSet;
import java.util.Set;

import com.leetcode.entity.ListNode;

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
}

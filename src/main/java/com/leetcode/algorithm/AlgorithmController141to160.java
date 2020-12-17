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

}

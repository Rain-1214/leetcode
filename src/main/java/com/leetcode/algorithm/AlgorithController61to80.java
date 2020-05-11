package com.leetcode.algorithm;

import java.util.*;

import com.leetcode.entity.ListNode;

public class AlgorithController61to80 {

  public ListNode rotateRight(ListNode head, int k) {
    int len = 0;
    ListNode currentNode = head;
    while(currentNode != null) {
      len++;
      currentNode = currentNode.next;
    }
    if (len <= 1 || k % len == 0) {
      return head;
    }
    int point = k;
    if (k > len) {
      point = k % len;
    }
    currentNode = head;
    int newLen = 1;
    ListNode result = null;
    while(currentNode != null) {
      if (newLen == len - point) {
        result = currentNode.next;
        currentNode.next = null;
        ListNode tempResult = result;
        while(true) {
          if (tempResult.next == null) {
            tempResult.next = head;
            break;
          } 
          tempResult = tempResult.next;
        }
        break;
      }
      currentNode = currentNode.next;
      newLen++;
    }
    return result;
  }

}

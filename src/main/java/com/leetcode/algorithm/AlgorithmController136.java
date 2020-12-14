package com.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

import com.leetcode.entity.randomNode.Node;

public class AlgorithmController136 {

  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }
    Map<Node, Node> map = new HashMap<>();
    return copyRandomList(head, map);
  }

  public Node copyRandomList(Node head, Map<Node, Node> map) {
    if (map.get(head) != null) {
      return map.get(head);
    }
    Node n = new Node(head.val);
    map.put(head, n);
    if (head.next != null) {
      n.next = copyRandomList(head.next, map);
    }
    if (head.random != null) {
      n.random = copyRandomList(head.random, map);
    }
    return n;
  }
}

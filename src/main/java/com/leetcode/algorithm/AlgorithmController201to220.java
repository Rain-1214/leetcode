package com.leetcode.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

}

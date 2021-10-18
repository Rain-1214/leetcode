package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.leetcode.entity.ListNode;

public class AlgorithmController441to460 {
  public int arrangeCoins(int n) {
    int left = 0, right = n;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      long count = arrangeCoinsHelp(mid);
      if (count == n) {
        return mid;
      }
      if (count > n) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return left - 1;
  }

  public long arrangeCoinsHelp(int n) {
    return (1 + (long) n) * n / 2;
  }

  public List<Integer> findDuplicates(int[] nums) {
    Set<Integer> set = new HashSet<Integer>();
    List<Integer> result = new ArrayList<Integer>();
    for (int n : nums) {
      if (set.contains(n)) {
        result.add(n);
      }
      set.add(n);
    }
    return result;
  }

  public List<Integer> findDuplicatesII(int[] nums) {
    List<Integer> result = new ArrayList<Integer>();
    for (int n : nums) {
      nums[(n - 1) % nums.length] += nums.length;
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 2 * nums.length) {
        result.add(i + 1);
      }
    }
    return result;
  }

  public int compress(char[] chars) {
    if (chars.length == 1) {
      return 1;
    }
    int left = 0, right = 1, index = 0;
    while (left < chars.length) {
      char lt = chars[left];
      while (right < chars.length && chars[right] == lt) {
        right++;
      }
      int num = right - left;
      chars[index++] = lt;
      if (num != 1) {
        int temp = index;
        while (num != 0) {
          int tempNum = num % 10;
          chars[index++] = (char) (tempNum + '0');
          num /= 10;
        }
        swap(chars, temp, index - 1);
      }
      left = right;
      right = left + 1;
    }
    return index;
  }

  public void swap(char[] a, int left, int right) {
    while (left < right) {
      char temp = a[left];
      a[left] = a[right];
      a[right] = temp;
      left++;
      right--;
    }
  }

  public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
    if (seqs.size() == 0) {
      return false;
    }
    Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
    List<Integer>[] topological = new List[org.length + 1];
    for (int i = 0; i < org.length; i++) {
      indexMap.put(org[i], i);
      topological[i] = new ArrayList<Integer>();
    }
    topological[org.length] = new ArrayList<Integer>();
    int[] in = new int[org.length + 1];
    for (List<Integer> list : seqs) {
      for (int i = 0; i < list.size(); i++) {
        if (i == 0) {
          int curr = list.get(i);
          if (!indexMap.containsKey(curr)) {
            return false;
          }
          continue;
        }
        int prev = list.get(i - 1);
        int curr = list.get(i);
        if (!indexMap.containsKey(prev) || !indexMap.containsKey(curr)) {
          return false;
        }
        if (indexMap.get(curr) <= indexMap.get(prev)) {
          return false;
        }
        in[curr]++;
        topological[prev].add(curr);
      }
    }

    int q = -1;
    for (int i = 1; i < in.length; i++) {
      if (in[i] == 0) {
        if (q != -1) {
          return false;
        }
        q = i;
      }
    }
    while (q != -1) {
      List<Integer> list = topological[q];
      q = -1;
      for (int temp : list) {
        in[temp]--;
        if (in[temp] == 0) {
          if (q != -1) {
            return false;
          }
          q = temp;
        }
      }
    }
    for (int i = 1; i < in.length; i++) {
      if (in[i] != 0) {
        return false;
      }
    }
    return true;
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    l1 = reverse(l1);
    l2 = reverse(l2);
    int temp = 0;
    ListNode res = new ListNode();
    ListNode head = res;
    while (l1 != null || l2 != null) {
      res.next = new ListNode();
      res = res.next;
      int val1 = l1 == null ? 0 : l1.val;
      int val2 = l2 == null ? 0 : l2.val;
      int val = val1 + val2 + temp;
      temp = val >= 10 ? 1 : 0;
      res.val = val >= 10 ? val % 10 : val;
      l1 = l1 == null ? null : l1.next;
      l2 = l2 == null ? null : l2.next;
    }
    if (temp != 0) {
      res.next = new ListNode(1);
    }
    res = head.next;
    res = reverse(res);
    return res;
  }

  public ListNode reverse(ListNode l) {
    ListNode h = l;
    ListNode prev = null;
    while (h != null) {
      ListNode next = h.next;
      h.next = prev;
      prev = h;
      h = next;
    }
    return prev;
  }

}

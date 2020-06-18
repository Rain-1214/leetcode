package com.leetcode.algorithm;

import java.util.Arrays;
import java.util.Stack;

import com.leetcode.entity.ListNode;
import com.leetcode.tool.Print;

public class AlgorithmController81to100 {

  public boolean search(int[] nums, int target) {
    return searchImpl(0, nums.length - 1, nums, target);
  }

  public boolean searchImpl(int start, int end, int[] nums, int target) {
    if (end < start) {
      return false;
    }
    int mid = (start + end) / 2;
    if (nums[mid] == target) {
      return true;
    }
    if (nums[mid] == nums[start] && nums[mid] == nums[end]) {
      return searchImpl(start + 1, end - 1, nums, target);
    } else if (nums[end] >= nums[mid]) {
      if (target > nums[mid] && target <= nums[end]) {
        return searchImpl(mid + 1, end, nums, target);
      } else {
        return searchImpl(start, mid - 1, nums, target);
      }
    } else {
      if (target >= nums[start] && target < nums[mid]) {
        return searchImpl(start, mid - 1, nums, target);
      } else {
        return searchImpl(mid + 1, end, nums, target);
      }
    }
  }

  public ListNode deleteDuplicatesII(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode currentNode = head;
    boolean jump = false;
    if (currentNode.val == currentNode.next.val) {
      jump = true;
      int val = currentNode.val;
      while (currentNode.val == val) {
        currentNode = currentNode.next;
        if (currentNode == null) {
          return null;
        }
      }
    }
    if (jump) {
      return deleteDuplicatesII(currentNode);
    } else {
      head.next = deleteDuplicatesII(head.next);
      return head;
    }
  }

  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null){
      return head;
    }
    ListNode currentNode = head;
    while (currentNode != null && currentNode.next != null) {
      if (currentNode.val == currentNode.next.val) {
        int val = currentNode.val;
        ListNode nextPoint = currentNode;
        while(nextPoint != null && nextPoint.val == val) {
          nextPoint = nextPoint.next;
        }
        currentNode.next = nextPoint;
        currentNode = nextPoint;
      } else {
        currentNode = currentNode.next;
      }
    }
    return head;
  }

  public int largestRectangleArea(int[] heights) {
    int res = 0;
    Stack<Integer> sk = new Stack<>();
    int[] hs = new int[heights.length + 1];
    hs = Arrays.copyOf(heights, heights.length + 1);
    for (int i = 0; i < hs.length;) {
      if (sk.empty() || hs[i] >= hs[sk.peek()]) {
        sk.push(i++);
      } else {
        int top = sk.pop();
        res = Math.max(res, hs[top] * (sk.empty() ? i : i - sk.peek() - 1));
      }
    }
    return res;
  }

  public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    int row = matrix.length;
    int column = matrix[0].length;
    int res = 0;
    int[] height = new int[column];
    for (int i = 0; i < row; i++) {
      char[] currentRow = matrix[i];
      for (int y = 0; y < column; y++) {
        if (currentRow[y] == '1') {
          height[y]++;
        } else {
          height[y] = 0;
        }
      }
      res = Math.max(res, this.largestRectangleArea(height));
    }
    return res;
  }

}

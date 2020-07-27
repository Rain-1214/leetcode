package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.TreeNode;
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
    if (head == null || head.next == null) {
      return head;
    }
    ListNode currentNode = head;
    while (currentNode != null && currentNode.next != null) {
      if (currentNode.val == currentNode.next.val) {
        int val = currentNode.val;
        ListNode nextPoint = currentNode;
        while (nextPoint != null && nextPoint.val == val) {
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

  public ListNode partitionSoSlow(ListNode head, int x) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode res = new ListNode();
    ListNode currentRes = res;
    List<Integer> temp = new ArrayList<>();
    while (head != null) {
      if (head.val < x) {
        currentRes.next = new ListNode(head.val);
        currentRes = currentRes.next;
      } else {
        temp.add(head.val);
      }
      head = head.next;
    }
    if (temp.size() > 0) {
      for (int val : temp) {
        currentRes.next = new ListNode(val);
        currentRes = currentRes.next;
      }
    }
    return res.next;
  }

  public ListNode partition(ListNode head, int x) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode res = new ListNode();
    partitionImpl(res, head, x, true);
    ListNode currentRes = res;
    while (currentRes.next != null) {
      currentRes = currentRes.next;
    }
    partitionImpl(currentRes, head, x, false);
    return res.next;
  }

  public ListNode partitionImpl(ListNode res, ListNode currentNode, int x, boolean flag) {
    if (currentNode == null) {
      return null;
    }
    if (flag) {
      if (currentNode.val < x) {
        res.next = new ListNode(currentNode.val);
        return partitionImpl(res.next, currentNode.next, x, flag);
      } else {
        return partitionImpl(res, currentNode.next, x, flag);
      }
    } else {
      if (currentNode.val >= x) {
        res.next = new ListNode(currentNode.val);
        return partitionImpl(res.next, currentNode.next, x, flag);
      } else {
        return partitionImpl(res, currentNode.next, x, flag);
      }
    }
  }

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1, j = n - 1, x = m + n - 1;
    while (j >= 0) {
      if (i >= 0) {
        nums1[x--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
      } else {
        nums1[x--] = nums2[j--];
      }
    }
  }

  public List<Integer> grayCodeTooMath(int n) {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < 1 << n; i++) {
      res.add(i ^ i >> 1);
    }
    return res;
  }

  public List<Integer> grayCode(int n) {
    List<Integer> res = new ArrayList<>();
    res.add(0);
    for (int i = 1; i <= n; i++) {
      int num = 1 << (i - 1);
      for (int j = res.size() - 1; j >= 0; j--) {
        res.add(res.get(j) + num);
      }
    }
    return res;
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    subsetsWithDupImpl(nums, res, new ArrayList<>(), 0);
    res.add(new ArrayList<Integer>());
    return res;
  }

  public void subsetsWithDupImpl(int[] nums, List<List<Integer>> res, List<Integer> current, int start) {
    if (start == nums.length) {
      return;
    }
    List<Integer> temp = new ArrayList<>(current);
    for (int i = start; i < nums.length; i++) {
      temp.add(nums[i]);
      res.add(new ArrayList<>(temp));
      subsetsWithDupImpl(nums, res, temp, i + 1);
      temp.remove(temp.size() - 1);
      while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
        i++;
      }
    }
  }

  public int numDecodings(String s) {
    if (s == null || s.charAt(0) == '0') {
      return 0;
    }
    int[] dp = new int[s.length() + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; i++) {
      char c = s.charAt(i - 1);
      dp[i] = c == '0' ? 0 : dp[i - 1];
      if (i > 1) {
        char prevC = s.charAt(i - 2);
        if (prevC == '1' || prevC == '2' && c < '7') {
          dp[i] += dp[i - 2];
        }
      }
    }
    return dp[s.length()];
  }

  public ListNode reverseBetween(ListNode head, int m, int n) {
    ListNode current = head;
    ListNode afterHeadCurrent = head;
    int[] nums = new int[n - m + 1];
    int i = 1;
    while (current != null) {
      if (i < m - 1) {
        afterHeadCurrent = afterHeadCurrent.next;
      } else if (i >= m) {
        nums[i - m] = current.val;
      }
      if (i == n) {
        ListNode temp = new ListNode();
        ListNode currentTemp = temp;
        for (int y = nums.length - 1; y >= 0; y--) {
          currentTemp.next = new ListNode(nums[y]);
          currentTemp = currentTemp.next;
        }
        currentTemp.next = current.next;
        afterHeadCurrent.next = temp.next;
        if (m == 1) {
          return temp.next;
        }
        break;
      }
      current = current.next;
      i++;
    }
    return head;
  }

  public List<String> restoreIpAddresses(String s) {
    List<String> res = new ArrayList<>();
    restoreIpAddressesImpl(s, 0, new ArrayList<String>(), res);
    return res;
  }

  public void restoreIpAddressesImpl(String s, int start, List<String> temp, List<String> res) {
    String current = "";
    if (temp.size() == 3) {
      if (s.length() - start > 3) {
        return;
      }
      String last = s.substring(start);
      if ((!last.equals("0") && last.startsWith("0")) || Integer.parseInt(last) > 255) {
        return;
      }
      res.add(String.join(".", temp) + "." + last);
      return;
    }
    for (int i = start; i < start + 3 && i < s.length() - 1; i++) {
      current += s.charAt(i);
      if (Integer.parseInt(current) > 255) {
        break;
      }
      temp.add(current);
      restoreIpAddressesImpl(s, i + 1, temp, res);
      temp.remove(temp.size() - 1);
      if (current.equals("0")) {
        break;
      }
    }
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    inorderTraversalImpl(root, res);
    return res;
  }

  public void inorderTraversalImpl(TreeNode root, List<Integer> res) {
    if (root.left != null) {
      inorderTraversalImpl(root.left, res);
    }
    res.add(root.val);
    if (root.right != null) {
      inorderTraversalImpl(root.right, res);
    }
  }

  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return Collections.emptyList();
    }
    return generateTreesImpl(1, n);
  }

  public List<TreeNode> generateTreesImpl(int start, int end) {
    List<TreeNode> res = new ArrayList<>();
    if (start > end) {
      res.add(null);
      return res;
    }
    for (int i = start; i <= end; i++) {
      List<TreeNode> left = generateTreesImpl(start, i - 1);
      List<TreeNode> right = generateTreesImpl(i + 1, end);

      for (int x = 0; x < left.size(); x++) {
        for (int y = 0; y < right.size(); y++) {
          TreeNode root = new TreeNode(i);
          root.left = left.get(x);
          root.right = right.get(y);
          res.add(root);
        }
      }
    }
    return res;
  }

  public int numTrees(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] += dp[j] * dp[i - j - 1];
      }
    }
    return dp[n];
  }

  public boolean isInterleave(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) {
      return false;
    }
    Set<Integer> cache = new HashSet<>();
    return isInterleaveImple(s1, 0, s2, 0, s3, 0, cache);
  }

  public boolean isInterleaveImple(String s1, int i, String s2, int j, String s3, int x, Set<Integer> cache) {
    int key = i + j * s3.length();
    if (cache.contains(key))
      return false;
    if (i == s1.length())
      return s2.substring(j).equals(s3.substring(x));
    if (j == s2.length())
      return s1.substring(i).equals(s3.substring(x));
    if ((s1.charAt(i) == s3.charAt(x) && isInterleaveImple(s1, i + 1, s2, j, s3, x + 1, cache))
        || (s2.charAt(j) == s3.charAt(x) && isInterleaveImple(s1, i, s2, j + 1, s3, x + 1, cache))) {
      return true;
    }
    cache.add(key);
    return false;
  }

  public boolean isInterLeaveDP(String s1, String s2, String s3) {
    int n = s1.length();
    int m = s2.length();
    if (n + m != s3.length()) {
      return false;
    }
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for (int i = 1; i <= n; i++) {
      dp[0][i] = dp[0][i - 1] && s1.charAt(i - 1) == s3.charAt(i - 1);
    }
    for (int i = 1; i <= m; i++) {
      dp[i][0] = dp[i - 1][0] && s2.charAt(i - 1) == s3.charAt(i - 1);
    }
    for (int i = 1; i <= m; i++) {
      for (int y = 1; y <= n; y++) {
        dp[i][y] = (dp[i][y - 1] && s1.charAt(y - 1) == s3.charAt(i + y - 1))
            || (dp[i - 1][y] && s2.charAt(i - 1) == s3.charAt(i - 1 + y));
      }
    }
    return dp[m][n];
  }

  public boolean isValidBST2Slow(TreeNode root) {
    List<Integer> temp = inorderTraversal(root);
    if (temp.size() < 2) {
      return true;
    }
    int max = temp.get(0);
    for (int i = 1; i < temp.size(); i++) {
      if (temp.get(i) > max) {
        max = temp.get(i);
      } else {
        return false;
      }
    }
    return true;
  }

  public long a = Long.MIN_VALUE;

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (!isValidBST(root.left)) {
      return false;
    }
    if (root.val <= a) {
      return false;
    }
    a = root.val;
    if (!isValidBST(root.right)) {
      return false;
    }
    return true;
  }

  public TreeNode temp;
  public TreeNode small;
  public TreeNode big;

  public void recoverTree(TreeNode root) {
    recoverTreeImpl(root);
    if (small != null && big != null) {
      int t = small.val;
      small.val = big.val;
      big.val = t;
    }
  }

  public void recoverTreeImpl(TreeNode root) {
    if (root == null) {
      return;
    }
    recoverTreeImpl(root.left);
    if (temp != null && root.val <= temp.val) {
      small = root;
      if (big == null) {
        big = temp;
      }
    }
    temp = root;
    recoverTreeImpl(root.right);
  }

}

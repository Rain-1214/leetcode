package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.Node;
import com.leetcode.entity.TreeNode;
import com.leetcode.tool.Print;

public class AlgorithmController101to120 {
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isSymmetricImpl(root.left, root.right);
  }

  public boolean isSymmetricImpl(TreeNode left, TreeNode right) {
    if (left == null || right == null) {
      return left == right;
    }
    if (left.val != right.val) {
      return false;
    }
    return isSymmetricImpl(left.left, right.right) && isSymmetricImpl(left.right, right.left);
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    levelOrderImpl(root, 0, res);
    return res;
  }

  public void levelOrderImpl(TreeNode node, int level, List<List<Integer>> res) {
    if (node == null) {
      return;
    }
    if (res.size() <= level) {
      List<Integer> temp = new ArrayList<>();
      temp.add(node.val);
      res.add(temp);
    } else {
      res.get(level).add(node.val);
    }
    levelOrderImpl(node.left, level + 1, res);
    levelOrderImpl(node.right, level + 1, res);
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    if (root == null) {
      return res;
    }
    q.offer(root);
    boolean flag = true;
    while (!q.isEmpty()) {
      int len = q.size();
      LinkedList<Integer> temp = new LinkedList<>();
      for (int i = 0; i < len; i++) {
        TreeNode c = q.poll();
        if (flag) {
          temp.add(c.val);
        } else {
          temp.addFirst(c.val);
        }
        if (c.left != null) {
          q.add(c.left);
        }
        if (c.right != null) {
          q.add(c.right);
        }
      }
      res.add(temp);
      flag = !flag;
    }
    return res;
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder == null || inorder == null || preorder.length != inorder.length) {
      return null;
    }
    return buildTree(preorder, inorder, 0, preorder.length - 1, 0);
  }

  public TreeNode buildTree(int[] preorder, int[] inorder, int start, int end, int index) {
    if (start > end) {
      return null;
    }
    TreeNode node = new TreeNode(preorder[index]);
    int inIndex = start;
    while (preorder[index] != inorder[inIndex]) {
      inIndex++;
    }
    node.left = buildTree(preorder, inorder, start, inIndex - 1, index + 1);
    node.right = buildTree(preorder, inorder, inIndex + 1, end, index + inIndex - start + 1);
    return node;
  }

  public TreeNode buildTree106(int[] inorder, int[] postorder) {
    if (inorder == null || postorder == null || inorder.length != postorder.length) {
      return null;
    }
    return buildTree106(inorder, postorder, 0, postorder.length - 1, postorder.length - 1);
  }

  public TreeNode buildTree106(int[] inorder, int[] postorder, int start, int end, int index) {
    if (start > end) {
      return null;
    }
    TreeNode node = new TreeNode(postorder[index]);
    int inIndex = start;
    while (postorder[index] != inorder[inIndex]) {
      inIndex++;
    }
    node.right = buildTree106(inorder, postorder, inIndex + 1, end, index - 1);
    node.left = buildTree106(inorder, postorder, start, inIndex - 1, index - (end - inIndex) - 1);
    return node;
  }

  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    levelOrderBottom(root, 0, res);
    return res;
  }

  public void levelOrderBottom(TreeNode root, int deep, List<List<Integer>> res) {
    int size = res.size();
    if (size == deep) {
      List<Integer> temp = new ArrayList<>();
      temp.add(root.val);
      res.add(0, temp);
    } else {
      res.get(size - deep - 1).add(root.val);
    }
    if (root.left != null) {
      levelOrderBottom(root.left, deep + 1, res);
    }
    if (root.right != null) {
      levelOrderBottom(root.right, deep + 1, res);
    }
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums.length == 0) {
      return null;
    }
    return sortedArrayToBST(nums, 0, nums.length - 1);
  }

  public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    }
    if (start == end) {
      return new TreeNode(nums[start]);
    }
    int mid = (start + end) / 2;
    if ((start + end) % 2 != 0) {
      mid += 1;
    }
    TreeNode temp = new TreeNode(nums[mid]);
    temp.left = sortedArrayToBST(nums, start, mid - 1);
    temp.right = sortedArrayToBST(nums, mid + 1, end);
    return temp;
  }

  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      return new TreeNode(head.val);
    }
    ListNode slow = head;
    ListNode fast = head;
    ListNode slowLast = head;
    while (fast.next != null && fast.next.next != null) {
      slowLast = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    fast = slow.next;
    slowLast.next = null;
    TreeNode temp = new TreeNode(slow.val);
    if (head != slow) {
      temp.left = sortedListToBST(head);
    }
    temp.right = sortedListToBST(fast);
    return temp;
  }

  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isBalancedImpl(root) != -1;
  }

  public int isBalancedImpl(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = isBalancedImpl(root.left);
    int right = isBalancedImpl(root.right);
    if (left == -1 || right == -1) {
      return -1;
    }
    if (Math.abs(right - left) <= 1) {
      return Math.max(left, right) + 1;
    }
    return -1;
  }

  public int minDepthSoSlow(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null)
      return minDepthSoSlow(root.right) + 1;
    if (root.right == null)
      return minDepthSoSlow(root.left) + 1;
    return Math.min(minDepthSoSlow(root.left), minDepthSoSlow(root.right)) + 1;
  }

  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> q = new LinkedList<>();
    int res = 1;
    q.offer(root);
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        TreeNode temp = q.poll();
        if (temp.left == null && temp.right == null) {
          return res;
        }
        if (temp.left != null) {
          q.offer(temp.left);
        }
        if (temp.right != null) {
          q.offer(temp.right);
        }
      }
      res += 1;
    }
    return res;
  }

  public boolean hasPathSumSoMemory(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }
    return hasPathSumImpl(root, 0, sum);
  }

  public boolean hasPathSumImpl(TreeNode root, int currentSum, int sum) {
    if (root == null) {
      return false;
    }
    if (root.left == null && root.right == null) {
      return root.val + currentSum == sum;
    }
    return hasPathSumImpl(root.left, currentSum + root.val, sum)
        || hasPathSumImpl(root.right, currentSum + root.val, sum);
  }

  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }
    sum = sum - root.val;
    if (root.left == null && root.right == null) {
      return sum == 0;
    }
    return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
  }

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    pathSum(root, new LinkedList<Integer>(), res, sum);
    return res;
  }

  public void pathSum(TreeNode root, LinkedList<Integer> list, List<List<Integer>> res, int sum) {
    list.add(root.val);
    sum = sum - root.val;
    if (root.left == null && root.right == null) {
      if (sum == 0) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.addAll(list);
        res.add(temp);
      }
      list.removeLast();
      return;
    }
    if (root.left != null) {
      pathSum(root.left, list, res, sum);
    }
    if (root.right != null) {
      pathSum(root.right, list, res, sum);
    }
    list.removeLast();
  }

  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      flatten(root.left);
    }
    if (root.right != null) {
      flatten(root.right);
    }
    TreeNode temp = root.right;
    root.right = root.left;
    root.left = null;
    TreeNode current = root;
    while (current.right != null) {
      current = current.right;
    }
    current.right = temp;
  }

  public int numDistinctSlow(String s, String t) {
    if (s == null || t == null) {
      return 0;
    }
    int sn = s.length(), tn = t.length();
    if (tn > sn) {
      return 0;
    }
    if (s.equals(t)) {
      return 1;
    }
    int[][] dp = new int[sn][tn];
    dp[0][0] = s.charAt(0) == t.charAt(0) ? 1 : 0;
    char tfc = t.charAt(0);
    for (int i = 1; i < sn; i++) {
      char sc = s.charAt(i);
      if (sc == tfc) {
        dp[i][0] = dp[i - 1][0] + 1;
      } else {
        dp[i][0] = dp[i - 1][0];
      }
    }
    Print.print2DIntArray(dp);
    for (int i = 1; i < sn; i++) {
      for (int y = 1; y < tn; y++) {
        char sc = s.charAt(i);
        char tc = t.charAt(y);
        if (sc == tc) {
          dp[i][y] = dp[i - 1][y - 1] + dp[i - 1][y];
        } else {
          dp[i][y] = dp[i - 1][y];
        }
      }
    }
    Print.print2DIntArray(dp);
    return dp[sn - 1][tn - 1];
  }

  public int numDistinct(String s, String t) {
    if (s == null || t == null) {
      return 0;
    }
    char[] sca = s.toCharArray(), tca = t.toCharArray();
    int sn = s.length(), tn = t.length();
    int[][] dp = new int[sn + 1][tn + 1];
    dp[0][0] = 1;
    for (int i = 1; i <= sn; i++) {
      dp[i][0] = 1;
      for (int y = 1; y <= tn; y++) {
        char sc = sca[i - 1];
        char tc = tca[y - 1];
        if (sc == tc) {
          dp[i][y] = dp[i - 1][y - 1] + dp[i - 1][y];
        } else {
          dp[i][y] = dp[i - 1][y];
        }
      }
    }
    Print.print2DIntArray(dp);
    return dp[sn][tn];
  }

  public Node connectSoSlow(Node root) {
    if (root == null) {
      return root;
    }
    Queue<Node> q = new LinkedList<>();
    q.add(root);
    while(!q.isEmpty()) {
      int n = q.size();
      for (int i = 0; i < n; i++) {
        Node current = q.poll();
        if (i != n - 1) {
          current.next = q.peek();
        } else {
          current.next = null;
        }
        if (current.left != null) {
          q.add(current.left);
          q.add(current.right);
        }
      }
    }
    return root;
  }

  public Node connect(Node root) {
    if (root == null) {
      return root;
    }
    if (root.left != null) {
      root.left.next = root.right;
      if (root.next != null) {
        root.right.next = root.next.left;
      }
    }
    connect(root.left);
    connect(root.right);
    return root;
  }

  public Node connect117tooSlow(Node root) {
    if (root == null) {
      return root;
    }
    if (root.left != null && root.right != null) {
      root.left.next = root.right;
      if (root.next != null) {
        root.right.next = root.next.left;
      }
    } else if (root.next != null) {
      if (root.left != null) {
        root.left.next = root.next.left;
      } else {
        root.right.next = root.next.left;
      }
    }
    if (root.left != null) {
      
    }
    connect117tooSlow(root.left);
    connect117tooSlow(root.right);
    return root;
  }

  public Node connect117(Node root) {
    if (root == null) {
      return root;
    }
    if (root.left != null) {
      if (root.right != null) {
        root.left.next = root.right;
      } else {
        root.left.next = connect117Help(root.next);
      }
    }
    if (root.right != null) {
      root.right.next = connect117Help(root.next);
    }
    connect117Help(root.right);
    connect117Help(root.left);
    return root;
  }

  public Node connect117Help(Node root) {
    if (root == null) {
      return null;
    }
    if (root.left != null) {
      return root.left;
    }
    if (root.right != null) {
      return root.right;
    }
    return connect117Help(root.next);
  }

}

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

import com.leetcode.entity.ListChilNode;
import com.leetcode.entity.MatrixNode;
import com.leetcode.entity.NTreeNode;
import com.leetcode.entity.TreeNode;

public class AlgorithmController421to440 {

  public int findMaximumXOR(int[] nums) {
    if (nums.length <= 1) {
      return 0;
    }
    int res = -1;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        res = Math.max(res, nums[i] ^ nums[j]);
      }
    }
    return res;
  }

  public int findMaximumXORII(int[] nums) {
    int res = 0;

    for (int i = 30; i >= 0; i--) {
      Set<Integer> set = new HashSet<Integer>();
      for (int num : nums) {
        set.add(num >> i);
      }
      int temp = res * 2 + 1;
      boolean find = false;
      for (int num : nums) {
        if (set.contains(temp ^ (num >> i))) {
          find = true;
          break;
        }
      }
      res = find ? temp : temp - 1;
    }
    return res;
  }

  public class Trie {
    public Trie left = null;
    public Trie right = null;
  }

  public int findMaximumXORIII(int[] nums) {
    int res = 0;
    Trie root = new Trie();
    for (int i = 1; i < nums.length; i++) {
      add(root, nums[i - 1]);
      res = Math.max(res, check(root, nums[i]));
    }
    return res;
  }

  public void add(Trie root, int val) {
    Trie head = root;
    for (int i = 30; i >= 0; i--) {
      int temp = val >> i;
      if ((temp & 1) == 1) {
        if (head.left == null) {
          head.left = new Trie();
        }
        head = head.left;
      } else {
        if (head.right == null) {
          head.right = new Trie();
        }
        head = head.right;
      }
    }
  }

  public int check(Trie root, int val) {
    int res = 0;
    Trie head = root;
    for (int i = 30; i >= 0; i--) {
      int temp = val >> i;
      if ((temp & 1) == 1) {
        if (head.right == null) {
          res <<= 1;
          head = head.left;
        } else {
          res <<= 1;
          res += 1;
          head = head.right;
        }
      } else {
        if (head.left == null) {
          res <<= 1;
          head = head.right;
        } else {
          res <<= 1;
          res += 1;
          head = head.left;
        }
      }
    }
    return res;
  }

  public boolean validWordSquare(List<String> words) {
    int n = words.size(), index = 0;
    while (index < n) {
      int rowR = index, colR = 0;
      int rowD = 0, colD = index;
      String right = words.get(rowR);
      while (colR < right.length()) {
        if (rowD >= n) {
          return false;
        }
        String temp = words.get(rowD);
        if (colD >= temp.length()) {
          return false;
        }
        if (right.charAt(colR) != temp.charAt(colD)) {
          return false;
        }
        colR++;
        rowD++;
      }
      if (rowD < n && colD < words.get(rowD).length()) {
        return false;
      }
      index++;
    }
    return true;
  }

  // -----------------------------a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,
  // q, r, s, t, u, v, w, x, y, z
  // -----------------------------0, 1, 2, 3, 4, 5, 6, 7, 8,
  // 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25
  public int[] zero = new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 };
  public int[] one = new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  public int[] two = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 };
  public int[] three = new int[] { 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 };
  public int[] four = new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 };
  public int[] five = new int[] { 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 };
  public int[] six = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 };
  public int[] seven = new int[] { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 };
  public int[] eight = new int[] { 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 };
  public int[] nine = new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

  public String originalDigits(String s) {
    int[] letters = new int[26];
    for (char c : s.toCharArray()) {
      letters[c - 'a']++;
    }
    StringBuilder sb = new StringBuilder();
    originalDigits(letters, sb);
    return sb.toString();
  }

  public boolean originalDigits(int[] letters, StringBuilder sb) {
    int sum = 0;
    for (int num : letters) {
      sum += num;
    }
    if (sum == 0) {
      return true;
    }
    int len = sb.length();
    for (int i = 0; i <= 9; i++) {
      int[] temp = getLetterNum(i);
      boolean flag = true;
      for (int j = 0; j < letters.length; j++) {
        if (letters[j] < temp[j]) {
          flag = false;
        }
        letters[j] -= temp[j];
      }
      if (flag) {
        sb.append(i);
        if (originalDigits(letters, sb)) {
          return true;
        } else {
          sb.setLength(len);
        }
      }
      for (int j = 0; j < letters.length; j++) {
        letters[j] += temp[j];
      }
    }
    return false;
  }

  public int[] getLetterNum(int letter) {
    switch (letter) {
      case 1:
        return one;
      case 2:
        return two;
      case 3:
        return three;
      case 4:
        return four;
      case 5:
        return five;
      case 6:
        return six;
      case 7:
        return seven;
      case 8:
        return eight;
      case 9:
        return nine;
      case 0:
        return zero;
      default:
        return new int[0];
    }
  }

  public String originalDigitsII(String s) {
    int[] letters = new int[26];
    for (char c : s.toCharArray()) {
      letters[c - 'a']++;
    }
    int[] nums = new int[10];
    nums[0] = letters[25];
    nums[2] = letters[22];
    nums[4] = letters[20];
    nums[6] = letters[23];
    nums[8] = letters[6];
    nums[3] = letters[7] - nums[8];
    nums[5] = letters[5] - nums[4];
    nums[7] = letters[18] - nums[6];
    nums[9] = letters[8] - nums[5] - nums[6] - nums[8];
    nums[1] = letters[13] - nums[7] - 2 * nums[9];

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= 9; i++) {
      for (int j = 0; j < nums[i]; j++) {
        sb.append(i);
      }
    }
    return sb.toString();
  }

  public int characterReplacement(String s, int k) {
    char[] sc = s.toCharArray();
    int index = 0, res = 0;
    while (index < sc.length) {
      int temp = k, start = index, nextIndex = -1;
      char c = sc[index];
      while (temp >= 0 && index < sc.length) {
        if (sc[index] != c) {
          temp--;
          if (nextIndex == -1) {
            nextIndex = index;
          }
          if (temp < 0) {
            break;
          }
        }
        index++;
      }
      if (index == sc.length) {
        if (temp >= 0) {
          while (temp >= 0 && start > 0) {
            if (sc[start] != c) {
              temp--;
              if (temp < 0) {
                break;
              }
            }
            start--;
          }
        }
        res = Math.max(res, index - start);
        break;
      } else {
        res = Math.max(res, index - start);
      }
      index = nextIndex;
    }

    return res;
  }

  public int characterReplacementII(String s, int k) {
    int[] nums = new int[26];
    char[] sc = s.toCharArray();
    int left = 0, right = 0, len = sc.length, maxNum = 0;
    while (right < len) {
      nums[sc[right] - 'A']++;
      maxNum = Math.max(nums[sc[right] - 'A'], maxNum);
      while (right - left + 1 - maxNum > k) {
        nums[sc[left] - 'A']--;
        left++;
      }
      right++;
    }
    return right - left;
  }

  public class DictionaryTree {
    public DictionaryTree[] children;
    public boolean isEnd;

    public DictionaryTree() {
      this.children = new DictionaryTree[26];
    }

    public void add(String str) {
      char[] ca = str.toCharArray();
      DictionaryTree current = this;
      for (int i = 0; i < ca.length; i++) {
        int index = ca[i] - 'a';
        if (current.children[index] == null) {
          current.children[index] = new DictionaryTree();
        }
        current = current.children[index];
      }
      current.isEnd = true;
    }

    public List<String> getWordWithPrefix(String str, int maxLength) {
      DictionaryTree current = this;
      List<String> res = new ArrayList<>();
      char[] ca = str.toCharArray();
      StringBuilder sb = new StringBuilder();
      sb.append(str);
      for (int i = 0; i < ca.length; i++) {
        int index = ca[i] - 'a';
        if (current.children[index] == null) {
          return res;
        }
        current = current.children[index];
      }
      getAllWords(current, sb, res, maxLength);
      return res;
    }

    private void getAllWords(DictionaryTree root, StringBuilder sb, List<String> res, int maxLength) {
      if (sb.length() > maxLength) {
        return;
      }
      if (root.isEnd) {
        res.add(sb.toString());
      }
      int len = sb.length();
      for (int i = 0; i < root.children.length; i++) {
        if (root.children[i] != null) {
          sb.append((char) (i + 'a'));
          getAllWords(root.children[i], sb, res, maxLength);
          sb.setLength(len);
        }
      }
    }
  }

  public List<List<String>> wordSquares(String[] words) {
    List<List<String>> res = new ArrayList<>();
    DictionaryTree root = new DictionaryTree();
    for (String word : words) {
      root.add(word);
    }
    for (int i = 0; i < words.length; i++) {
      LinkedList<String> current = new LinkedList<>();
      current.add(words[i]);
      if (words[i].length() == 1) {
        res.add(current);
        continue;
      }
      wordSquares(root, current, words[i].length(), res);
    }
    return res;
  }

  public void wordSquares(DictionaryTree root, LinkedList<String> current, int maxRow, List<List<String>> res) {
    int currentRow = current.size();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < currentRow; i++) {
      String temp = current.get(i);
      if (currentRow >= temp.length()) {
        break;
      }
      sb.append(temp.charAt(currentRow));
    }
    int maxLen = current.get(currentRow - 1).length();
    List<String> strings = root.getWordWithPrefix(sb.toString(), maxLen);
    if (currentRow == maxRow - 1) {
      for (String s : strings) {
        List<String> temp = new ArrayList<>();
        for (String s1 : current) {
          temp.add(s1);
        }
        temp.add(s);
        res.add(temp);
      }
      return;
    } else {
      for (String s : strings) {
        current.add(s);
        wordSquares(root, current, maxRow, res);
        current.removeLast();
      }
    }
  }

  public TreeNode treeToDoublyListRes = null;
  public TreeNode treeToDoublyListCurrent = null;

  public TreeNode treeToDoublyList(TreeNode root) {
    if (root == null) {
      return root;
    }
    treeToDoublyListHelp(root);
    treeToDoublyListCurrent.right = treeToDoublyListRes;
    treeToDoublyListRes.left = treeToDoublyListCurrent;
    TreeNode head = treeToDoublyListRes.right;
    TreeNode prev = treeToDoublyListRes;
    while (head != treeToDoublyListRes) {
      head.left = prev;
      prev = head;
      head = head.right;
    }
    return treeToDoublyListRes;
  }

  public void treeToDoublyListHelp(TreeNode root) {
    if (root == null) {
      return;
    }
    treeToDoublyListHelp(root.left);
    if (treeToDoublyListRes == null) {
      treeToDoublyListRes = root;
      treeToDoublyListCurrent = root;
    } else {
      treeToDoublyListCurrent.right = root;
      treeToDoublyListCurrent = root;
    }
    treeToDoublyListHelp(root.right);
  }

  public MatrixNode construct(int[][] grid) {
    MatrixNode node = new MatrixNode();
    int len = grid.length;
    if (len == 1) {
      node.isLeaf = true;
      node.val = grid[0][0] == 1;
      return node;
    }
    construct(grid, node, len / 2, len / 2, len / 2);
    return node;
  }

  public MatrixNode construct(int[][] grid, MatrixNode node, int row, int col, int radius) {
    if (radius == 1) {
      node.topLeft = new MatrixNode(grid[row - 1][col - 1] == 1, true);
      node.topRight = new MatrixNode(grid[row - 1][col] == 1, true);
      node.bottomLeft = new MatrixNode(grid[row][col - 1] == 1, true);
      node.bottomRight = new MatrixNode(grid[row][col] == 1, true);
      checkMatrixNode(node);
      return node;
    }
    int nextRadius = radius / 2;
    node.topLeft = construct(grid, new MatrixNode(), row - nextRadius, col - nextRadius, nextRadius);
    node.topRight = construct(grid, new MatrixNode(), row - nextRadius, col + nextRadius, nextRadius);
    node.bottomLeft = construct(grid, new MatrixNode(), row + nextRadius, col - nextRadius, nextRadius);
    node.bottomRight = construct(grid, new MatrixNode(), row + nextRadius, col + nextRadius, nextRadius);
    checkMatrixNode(node);
    return node;
  }

  public void checkMatrixNode(MatrixNode node) {
    boolean isAllLeaf = node.topLeft.isLeaf && node.topRight.isLeaf && node.bottomLeft.isLeaf
        && node.bottomRight.isLeaf;
    boolean isAllValSame = false;
    boolean val = true;
    if (node.topLeft.val == node.topRight.val && node.topRight.val == node.bottomLeft.val
        && node.bottomLeft.val == node.bottomRight.val) {
      isAllValSame = true;
      val = node.topLeft.val;
    }
    if (isAllValSame) {
      node.val = val;
      if (isAllLeaf) {
        node.isLeaf = true;
        node.topLeft = null;
        node.topRight = null;
        node.bottomLeft = null;
        node.bottomRight = null;
      } else {
        node.isLeaf = false;
      }
      return;
    } else {
      isAllLeaf = false;
    }
    node.isLeaf = isAllLeaf;
    node.val = val;
  }

  class Codec {
    // Encodes a tree to a single string.
    public String serialize(NTreeNode root) {
      if (root == null) {
        return "#";
      }
      StringBuilder sb = new StringBuilder();
      Queue<NTreeNode> q = new LinkedList<>();
      q.add(root);
      q.add(null);
      while (!q.isEmpty()) {
        int len = q.size();
        for (int i = 0; i < len; i++) {
          if (q.peek() == null) {
            sb.append("#");
            q.poll();
          }
          while (q.peek() != null) {
            NTreeNode c = q.poll();
            sb.append(c.val);
            if (q.peek() != null) {
              sb.append(',');
            }
            if (c.children.size() > 0) {
              for (NTreeNode n : c.children) {
                q.add(n);
              }
            } else {
              q.add(null);
            }
            q.add(null);
          }
        }
        if (!q.isEmpty()) {
          q.poll();
        }
        sb.append("#");
      }
      return sb.toString();
    }

    // Decodes your encoded data to tree.
    public NTreeNode deserialize(String data) {
      if (data.equals("#")) {
        return null;
      }
      char[] sc = data.toCharArray();
      int index = 0, val = 0;
      while (Character.isDigit(sc[index])) {
        val = val * 10 + (sc[index] - '0');
        index++;
      }
      NTreeNode root = new NTreeNode(val, new ArrayList<>());
      Queue<NTreeNode> q = new LinkedList<>();
      q.add(root);
      index++;
      while (index < sc.length) {
        NTreeNode c = q.poll();
        if (sc[index] == '#') {
          index += 2;
          continue;
        }
        if (Character.isDigit(sc[index])) {
          while (sc[index] != '#') {
            if (sc[index] == ',') {
              index++;
              continue;
            }
            val = 0;
            while (Character.isDigit(sc[index])) {
              val = val * 10 + (sc[index] - '0');
              index++;
            }
            NTreeNode temp = new NTreeNode(val, new ArrayList<>());
            c.children.add(temp);
            q.add(temp);
          }
        }
        index++;
      }
      return root;
    }
  }

  class CodecII {
    // Encodes a tree to a single string.
    public String serialize(NTreeNode root) {
      if (root == null) {
        return "";
      }
      StringBuilder sb = new StringBuilder();
      inorder(root, sb);
      return sb.toString();
    }

    public void inorder(NTreeNode root, StringBuilder sb) {
      sb.append(root.val);
      sb.append('(');
      for (NTreeNode node : root.children) {
        inorder(node, sb);
      }
      sb.append(')');
    }

    // Decodes your encoded data to tree.
    public NTreeNode deserialize(String data) {
      if (data.length() == 0) {
        return null;
      }
      return deserialize(data.toCharArray(), new int[1]);
    }

    public NTreeNode deserialize(char[] data, int[] idx) {
      int val = getInt(data, idx);

      ArrayList<NTreeNode> list = new ArrayList<>();
      while (idx[0] < data.length) {
        if (data[idx[0]] == '(') {
          idx[0]++;
          continue;
        }
        if (data[idx[0]] == ')') {
          idx[0]++;
          break;
        }
        list.add(deserialize(data, idx));
      }
      return new NTreeNode(val, list);
    }

    public int getInt(char[] data, int[] idx) {
      int val = 0;
      while (Character.isDigit(data[idx[0]])) {
        val = val * 10 + (data[idx[0]] - '0');
        idx[0]++;
      }
      return val;
    }
  }

  public List<List<Integer>> levelOrder(NTreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    Queue<NTreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      int len = q.size();
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < len; i++) {
        NTreeNode c = q.poll();
        list.add(c.val);
        for (NTreeNode node : c.children) {
          q.add(node);
        }
      }
      res.add(list);
    }
    return res;
  }

  public List<List<Integer>> levelOrderII(NTreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    levelOrderII(root, 0, res);
    return res;
  }

  public void levelOrderII(NTreeNode root, int level, List<List<Integer>> res) {
    if (res.size() <= level) {
      res.add(new ArrayList<>());
    }
    res.get(level).add(root.val);
    for (NTreeNode node : root.children) {
      levelOrderII(node, level + 1, res);
    }
  }

  public ListChilNode flatten(ListChilNode head) {
    ListChilNode current = head;
    while (current != null) {
      if (current.child != null) {
        ListChilNode next = flatten(current.child);
        ListChilNode end = next;
        while (end.next != null) {
          end = end.next;
        }
        ListChilNode temp = current.next;
        current.next = next;
        current.child = null;
        next.prev = current;
        end.next = temp;
        if (temp != null) {
          temp.prev = end;
        }
        current = temp;
      } else {
        current = current.next;
      }
    }
    return head;
  }

  class Codec431 {
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(NTreeNode root) {
      if (root == null) {
        return null;
      }
      return inorder(root);
    }

    public TreeNode inorder(NTreeNode root) {
      TreeNode res = new TreeNode(root.val);
      TreeNode current = res.left;
      for (NTreeNode node : root.children) {
        if (current == null) {
          res.left = inorder(node);
          current = res.left;
        } else {
          current.right = inorder(node);
          current = current.right;
        }
      }
      return res;
    }

    // Decodes your binary tree to an n-ary tree.
    public NTreeNode decode(TreeNode root) {
      if (root == null) {
        return null;
      }
      return decodeHelp(root);
    }

    public NTreeNode decodeHelp(TreeNode root) {
      NTreeNode res = new NTreeNode(root.val, new ArrayList<>());
      TreeNode current = root.left;
      while (current != null) {
        res.children.add(decodeHelp(current));
        current = current.right;
      }
      return res;
    }
  }

  class AllOne {

    public class DLinkNode {
      public int val;
      public String key;
      public DLinkNode prev;
      public DLinkNode next;

      public DLinkNode() {
      }

      public DLinkNode(int val, String key) {
        this.val = val;
        this.key = key;
      }

      @Override
      public String toString() {
        DLinkNode c = this;
        StringBuilder sb = new StringBuilder();
        while (c != null) {
          sb.append(c.key);
          sb.append(c.val);
          sb.append(',');
          c = c.next;
        }
        return sb.toString();
      }
    }

    public Map<String, DLinkNode> map;
    public Map<Integer, DLinkNode> last;
    public Map<Integer, DLinkNode> head;
    public DLinkNode globalHead;
    public DLinkNode globalLast;

    /** Initialize your data structure here. */
    public AllOne() {
      this.map = new HashMap<>();
      this.last = new HashMap<>();
      this.head = new HashMap<>();
      this.globalHead = new DLinkNode();
      this.globalLast = new DLinkNode();
      this.globalHead.val = -1;
      this.globalLast.val = -1;
    }

    public void removeNodeFromList(DLinkNode node) {
      if (node.prev != null) {
        node.prev.next = node.next;
      }
      if (node.next != null) {
        node.next.prev = node.prev;
      }
      node.prev = null;
      node.next = null;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
      if (this.map.containsKey(key)) {
        this.moveToLast(this.map.get(key));
      } else {
        this.insert(key);
      }
    }

    public void moveToLast(DLinkNode node) {
      DLinkNode last = this.last.get(node.val);
      DLinkNode head = this.head.get(node.val);
      if (last == head) {
        this.last.remove(node.val);
        this.head.remove(node.val);
        node.val++;
        this.head.put(node.val, node);
        if (!this.last.containsKey(node.val)) {
          this.last.put(node.val, node);
        }
        return;
      } else if (last == node) {
        this.last.put(node.val, node.prev);
        node.val++;
        this.head.put(node.val, node);
        if (!this.last.containsKey(node.val)) {
          this.last.put(node.val, node);
        }
        return;
      } else if (head == node) {
        this.head.put(node.val, node.next);
      }
      removeNodeFromList(node);
      node.val++;
      node.next = last.next;
      last.next.prev = node;

      last.next = node;
      node.prev = last;
      this.head.put(node.val, node);
      if (!this.last.containsKey(node.val)) {
        this.last.put(node.val, node);
      }
    }

    public void insert(String key) {
      DLinkNode node = new DLinkNode(1, key);
      DLinkNode currentHead = globalHead.next;
      if (currentHead != null) {
        node.next = currentHead;
        currentHead.prev = node;
        node.prev = globalHead;
        globalHead.next = node;
      } else {
        globalHead.next = node;
        node.prev = globalHead;

        node.next = globalLast;
        globalLast.prev = node;
      }
      this.map.put(key, node);
      this.head.put(1, node);
      if (!this.last.containsKey(1)) {
        this.last.put(1, node);
      }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data
     * structure.
     */
    public void dec(String key) {
      DLinkNode node = this.map.get(key);
      if (node.val == 1) {
        this.remove(node);
      } else {
        this.moveToHead(node);
      }
    }

    public void moveToHead(DLinkNode node) {
      DLinkNode last = this.last.get(node.val);
      DLinkNode head = this.head.get(node.val);
      if (last == head) {
        this.last.remove(node.val);
        this.head.remove(node.val);
        node.val--;
        this.last.put(node.val, node);
        if (!this.head.containsKey(node.val)) {
          this.head.put(node.val, node);
        }
        return;
      } else if (head == node) {
        this.head.put(node.val, node.next);
        node.val--;
        this.last.put(node.val, node);
        if (!this.head.containsKey(node.val)) {
          this.head.put(node.val, node);
        }
        return;
      } else if (last == node) {
        this.last.put(node.val, node.prev);
      }
      removeNodeFromList(node);
      node.val--;
      head.prev.next = node;
      node.prev = head.prev;
      node.next = head;
      head.prev = node;
      this.last.put(node.val, node);
      if (!this.head.containsKey(node.val)) {
        this.head.put(node.val, node);
      }
    }

    public void remove(DLinkNode node) {
      DLinkNode last = this.last.get(node.val);
      DLinkNode head = this.head.get(node.val);
      this.map.remove(node.key);
      if (last == head) {
        this.last.remove(node.val);
        this.head.remove(node.val);
      } else if (last == node) {
        this.last.put(node.val, node.prev);
      } else if (head == node) {
        this.head.put(node.val, node.next);
      }
      removeNodeFromList(node);
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
      if (globalLast.prev == null || globalLast.prev == globalHead) {
        return "";
      }
      return globalLast.prev.key;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
      if (globalHead.next == null || globalHead.next == globalLast) {
        return "";
      }
      return globalHead.next.key;
    }
  }

  public char[] minMutationDic = new char[] { 'A', 'C', 'T', 'G' };

  public int minMutation(String start, String end, String[] bank) {
    char[] sc = start.toCharArray();
    char[] ec = end.toCharArray();
    Set<String> bankSet = new HashSet<>();
    Set<String> cache = new HashSet<>();
    for (String s : bank) {
      bankSet.add(s);
    }
    if (!bankSet.contains(end)) {
      return -1;
    }
    return minMutation(sc, ec, bankSet, cache, 0);
  }

  public int minMutation(char[] start, char[] end, Set<String> bank, Set<String> cache, int level) {
    int res = Integer.MAX_VALUE;
    for (int i = 0; i < start.length; i++) {
      char temp = start[i];
      for (char c : minMutationDic) {
        if (temp == c) {
          continue;
        }
        start[i] = c;
        if (sameCharArr(start, end)) {
          return 1 + level;
        }
        String tempStr = new String(start);
        if (bank.contains(tempStr) && !cache.contains(tempStr)) {
          cache.add(tempStr);
          int tempRes = minMutation(start, end, bank, cache, 1 + level);
          cache.remove(tempStr);
          if (tempRes != -1) {
            res = Math.min(res, tempRes);
          }
        }
      }
      start[i] = temp;
    }
    return res == Integer.MAX_VALUE ? -1 : res;
  }

  public boolean sameCharArr(char[] a, char[] b) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i]) {
        return false;
      }
    }
    return true;
  }

  public int countSegments(String s) {
    char[] sc = s.toCharArray();
    if (sc.length == 0) {
      return 0;
    }
    int index = 0, res = 0;
    while (index < sc.length) {
      if (sc[index] == ' ') {
        index++;
        continue;
      }
      res++;
      while (index < sc.length && sc[index] != ' ') {
        index++;
      }
    }
    return res;

  }

  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length <= 1) {
      return 0;
    }
    Arrays.sort(intervals, (a, b) -> {
      return a[1] - b[1];
    });
    int right = intervals[0][1];
    int res = 1;
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] >= right) {
        right = intervals[i][1];
        res++;
      }
    }
    return intervals.length - res;
  }

  public int[] findRightInterval(int[][] intervals) {
    int len = intervals.length;
    Map<Integer, Integer> indexMap = new HashMap<>();
    int[][] copy = new int[len][2];
    for (int i = 0; i < len; i++) {
      copy[i] = intervals[i];
      indexMap.put(intervals[i][0], i);
    }
    Arrays.sort(copy, (a, b) -> {
      return a[0] - b[0];
    });
    int[] res = new int[len];
    for (int i = 0; i < len; i++) {
      int val = findRightInterval(copy, intervals[i][1]);
      res[i] = val == -1 ? -1 : indexMap.get(copy[val][0]);
    }
    return res;
  }

  public int findRightInterval(int[][] intervals, int val) {
    int left = 0, right = intervals.length - 1;
    while (left < right) {
      int mid = (left + right) / 2;
      if (intervals[mid][0] >= val) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    if (intervals[left][0] < val) {
      return -1;
    }
    return left;
  }

  public int[] findRightIntervalII(int[][] intervals) {
    int len = intervals.length;
    Map<int[], Integer> indexMap = new HashMap<>();
    int[][] copy = new int[len][2];
    for (int i = 0; i < len; i++) {
      copy[i] = intervals[i];
      indexMap.put(intervals[i], i);
    }
    Arrays.sort(intervals, (a, b) -> {
      return a[0] - b[0];
    });
    Arrays.sort(copy, (a, b) -> {
      return a[1] - b[1];
    });
    int[] res = new int[len];
    int index = 0, endIndex = 0;
    while (endIndex < len) {
      int[] temp = copy[endIndex];
      while (index < len && intervals[index][0] < temp[1]) {
        index++;
      }
      res[indexMap.get(temp)] = index >= len ? -1 : indexMap.get(intervals[index]);
      endIndex++;
    }
    return res;
  }

  public int pathSumRes = 0;
  public int pathSumTarget = 0;

  public int pathSum(TreeNode root, int targetSum) {
    this.pathSumTarget = targetSum;
    pathSumHelp(root, targetSum);
    return pathSumRes;
  }

  public void pathSumHelp(TreeNode root, int targetSum) {
    if (root == null) {
      return;
    }
    if (targetSum == root.val) {
      pathSumRes += 1;
    }
    pathSumHelp(root.left, this.pathSumTarget);
    pathSumHelp(root.right, this.pathSumTarget);

    pathSumHelp(root.left, targetSum - root.val);
    pathSumHelp(root.right, targetSum - root.val);
  }

}

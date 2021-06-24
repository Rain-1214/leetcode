package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.TreeNode;

public class AlgorithmController321to340 {

  public int[] maxNumber(int[] nums1, int[] nums2, int k) {
    int len1 = nums1.length, len2 = nums2.length;
    int[] res = new int[k];
    for (int i = Math.max(0, k - len2); i <= Math.min(k, len1); i++) {
      int[] sub1 = subSequence(nums1, i);
      int[] sub2 = subSequence(nums2, k - i);
      int[] merge = maxNumberMerge(sub1, sub2);
      if (maxNumberCompare(merge, 0, res, 0) > 0) {
        System.arraycopy(merge, 0, res, 0, k);
      }
    }
    return res;
  }

  public int[] maxNumberMerge(int[] nums1, int[] nums2) {
    if (nums1.length == 0) {
      return nums2;
    }
    if (nums2.length == 0) {
      return nums1;
    }
    int[] res = new int[nums1.length + nums2.length];
    int i1 = 0, i2 = 0;
    for (int i = 0; i < res.length; i++) {
      if (maxNumberCompare(nums1, i1, nums2, i2) > 0) {
        res[i] = nums1[i1++];
      } else {
        res[i] = nums2[i2++];
      }
    }
    return res;
  }

  public int[] maxSubsequence(int[] nums, int k) {
    int length = nums.length;
    int[] stack = new int[k];
    int top = -1;
    int remain = length - k;
    for (int i = 0; i < length; i++) {
      int num = nums[i];
      while (top >= 0 && stack[top] < num && remain > 0) {
        top--;
        remain--;
      }
      if (top < k - 1) {
        stack[++top] = num;
      } else {
        remain--;
      }
    }
    return stack;
  }

  public int[] subSequence(int[] nums, int k) {
    int[] stack = new int[k];
    int i = 0;
    int canIgnoreLength = nums.length - k;
    int index = -1;
    while (i < nums.length) {
      int temp = nums[i++];
      while (index >= 0 && stack[index] < temp && canIgnoreLength > 0) {
        index--;
        canIgnoreLength--;
      }
      if (index < k - 1) {
        stack[++index] = temp;
      } else {
        canIgnoreLength--;
      }
    }
    return stack;
  }

  public int maxNumberCompare(int[] nums1, int i1, int[] nums2, int i2) {
    int len1 = nums1.length, len2 = nums2.length;
    while (i1 < len1 && i2 < len2) {
      if (nums1[i1] != nums2[i2]) {
        return nums1[i1] - nums2[i2];
      }
      i1++;
      i2++;
    }
    return (len1 - i1) - (len2 - i2);
  }

  public int coinChange(int[] coins, int amount) {
    if (amount < 1) {
      return 0;
    }
    return coinChangeHelp(coins, amount, new int[amount]);
  }

  public int coinChangeHelp(int[] coins, int amount, int[] cache) {
    if (amount == 0) {
      return 0;
    }
    if (amount < 0) {
      return -1;
    }
    if (cache[amount - 1] != 0) {
      return cache[amount - 1];
    }
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int temp = coinChangeHelp(coins, amount - coin, cache);
      if (temp >= 0 && temp < min) {
        min = temp + 1;
      }
    }
    cache[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
    return cache[amount - 1];
  }

  public int coinChangeII(int[] coins, int amount) {
    if (amount < 1) {
      return 0;
    }
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (i - coins[j] >= 0) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }

  public int countComponents(int n, int[][] edges) {
    UnionFind unionFind = new UnionFind(n);
    for (int[] edge : edges) {
      unionFind.merge(edge[0], edge[1]);
    }
    return unionFind.getCount();
  }

  public class UnionFind {

    private int[] set;
    private int count;

    public UnionFind(int n) {
      this.set = new int[n];
      for (int i = 0; i < n; i++) {
        this.set[i] = i;
      }
      this.count = n;
    }

    public int find(int n) {
      if (set[n] == n) {
        return n;
      }
      int root = find(set[n]);
      set[n] = root;
      return set[n];
    }

    public int getCount() {
      return this.count;
    }

    public void merge(int n, int m) {
      int rootA = find(n);
      int rootB = find(m);
      if (rootA == rootB) {
        return;
      }
      set[rootA] = rootB;
      count--;
    }
  }

  public void wiggleSort(int[] nums) {
    int len = nums.length;
    if (len < 2) {
      return;
    }
    int[] res = new int[len];
    res = Arrays.copyOf(nums, len);
    Arrays.sort(res);
    int left = len % 2 == 0 ? len / 2 - 1 : len / 2;
    reverseArray(res, 0, left);
    reverseArray(res, left + 1, len - 1);
    int leftIndex = 0, rightIndex = left + 1;
    for (int i = 0; i < len; i++) {
      if (i % 2 == 0) {
        nums[i] = res[leftIndex++];
      } else {
        nums[i] = res[rightIndex++];
      }
    }
  }

  public void reverseArray(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
      start++;
      end--;
    }
  }

  public void wiggleSortII(int[] nums) {
    int[] bucket = new int[5001];
    for (int i = 0; i < nums.length; i++) {
      bucket[nums[i]]++;
    }
    int j = 5000;
    for (int i = 1; i < nums.length; i += 2) {
      while (bucket[j] == 0) {
        j--;
      }
      nums[i] = j;
      bucket[j]--;
    }

    for (int i = 0; i < nums.length; i += 2) {
      while (bucket[j] == 0) {
        j--;
      }
      nums[i] = j;
      bucket[j]--;
    }
  }

  public int maxSubArrayLen(int[] nums, int k) {
    if (nums.length == 1) {
      return nums[0] == k ? 1 : 0;
    }
    int max = 0;
    Map<Integer, Integer> cache = new HashMap<>();
    int sum = 0;
    cache.put(0, 0);
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (!cache.containsKey(sum)) {
        cache.put(sum, i + 1);
      }
    }

    for (int i = nums.length - 1; i >= 0; i--) {
      if (cache.containsKey(sum - k)) {
        max = Math.max(max, i - cache.get(sum - k) + 1);
      }
      sum -= nums[i];
    }
    return max;
  }

  public int maxSubArrayLenII(int[] nums, int k) {
    if (nums.length == 1) {
      return nums[0] == k ? 1 : 0;
    }
    int max = 0;
    Map<Integer, Integer> cache = new HashMap<>();
    int[] sum = new int[nums.length + 1];
    cache.put(0, 0);
    for (int i = 1; i < sum.length; i++) {
      sum[i] = sum[i - 1] + nums[i - 1];
      if (!cache.containsKey(sum[i])) {
        cache.put(sum[i], i);
      }
    }
    for (int i = sum.length - 1; i >= 0; i--) {
      if (cache.containsKey(sum[i] - k)) {
        max = Math.max(max, i - cache.get(sum[i] - k));
      }
    }
    return max;
  }

  public boolean isPowerOfThree(int n) {
    if (n < 1) {
      return false;
    }
    while (n % 3 == 0) {
      n /= 3;
    }
    return n == 1;
  }

  public boolean isPowerOfThreeII(int n) {
    return n > 0 && 1162261467 % n == 0;
  }

  public int countRangeSum(int[] nums, int lower, int upper) {
    int len = nums.length;
    long[] preSum = new long[len + 1];
    preSum[0] = 0;
    for (int i = 1; i <= len; i++) {
      preSum[i] = preSum[i - 1] + nums[i - 1];
    }
    Set<Long> set = new TreeSet<>();
    for (long n : preSum) {
      set.add(n);
      set.add(n - lower);
      set.add(n - upper);
    }
    int size = set.size();
    Map<Long, Integer> map = new HashMap<>();
    int id = 0;
    for (long n : set) {
      map.put(n, id++);
    }
    int[] tree = new int[size * 2];
    int res = 0;
    for (int i = 0; i <= len; i++) {
      int left = map.get(preSum[i] - upper) + size;
      int right = map.get(preSum[i] - lower) + size;
      res += treeRangeSum(tree, left, right);
      treeInsert(tree, map.get(preSum[i]) + size);
    }
    return res;
  }

  public void treeInsert(int[] nums, int index) {
    nums[index]++;
    while (index > 0) {
      int left = index % 2 == 0 ? index : index - 1;
      int right = index % 2 == 0 ? index + 1 : index;
      nums[index / 2] = nums[left] + nums[right];
      index /= 2;
    }
  }

  public long treeRangeSum(int[] nums, int left, int right) {
    int res = 0;
    while (right >= left) {
      if (right % 2 == 0) {
        res += nums[right];
        right--;
      }
      if (left % 2 != 0) {
        res += nums[left];
        left++;
      }
      left /= 2;
      right /= 2;
    }
    return res;
  }

  public ListNode oddEvenList(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode even = head.next;
    if (even == null) {
      return head;
    }
    ListNode temp = head;
    int index = 1;
    ListNode lastNode = head;
    while (temp != null) {
      if (temp.next == null) {
        lastNode.next = even;
        break;
      }
      ListNode next = temp.next;
      temp.next = temp.next.next;
      temp = next;
      index++;
      if (index % 2 != 0) {
        lastNode = temp;
      }
    }
    return head;
  }

  private static final int[][] DIRECTIONS = new int[][] { new int[] { 1, 0 }, new int[] { -1, 0 }, new int[] { 0, 1 },
      new int[] { 0, -1 } };

  public int longestIncreasingPath(int[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;
    int[][] cache = new int[row][col];
    int max = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        max = Math.max(max, longestIncreasingPath(matrix, i, j, cache));
      }
    }
    return max;
  }

  public int longestIncreasingPath(int[][] matrix, int row, int col, int[][] cache) {
    int maxRow = matrix.length;
    int maxCol = matrix[0].length;
    if (cache[row][col] != 0) {
      return cache[row][col];
    }
    int temp = matrix[row][col];
    for (int[] dir : DIRECTIONS) {
      int nr = row + dir[0];
      int nc = col + dir[1];
      if (nr >= 0 && nc >= 0 && nr < maxRow && nc < maxCol && temp < matrix[nr][nc]) {
        cache[row][col] = Math.max(cache[row][col], longestIncreasingPath(matrix, nr, nc, cache) + 1);
      }
    }
    if (cache[row][col] == 0) {
      cache[row][col] = 1;
    }
    return cache[row][col];
  }

  public int minPatches(int[] nums, int n) {
    long sum = 1;
    int len = nums.length, index = 0;
    int res = 0;
    while (sum <= n) {
      if (index < len && nums[index] <= sum) {
        sum += nums[index++];
      } else {
        sum *= 2;
        res++;
      }
    }
    return res;
  }

  public boolean isValidSerialization(String preorder) {
    int len = preorder.length();
    Stack<Integer> stack = new Stack<>();
    int index = 0;
    stack.add(1);
    while (index < len) {
      if (stack.isEmpty()) {
        return false;
      }
      if (preorder.charAt(index) == ',') {
        index++;
      } else if (preorder.charAt(index) == '#') {
        int temp = stack.pop();
        if (temp - 1 > 0) {
          stack.add(temp - 1);
        }
        index++;
      } else {
        while (index < len && preorder.charAt(index) != ',') {
          index++;
        }
        int temp = stack.pop();
        if (temp - 1 > 0) {
          stack.add(temp - 1);
        }
        stack.add(2);
      }
    }
    return stack.isEmpty();
  }

  public boolean isValidSerializationII(String preorder) {
    int len = preorder.length();
    int index = 0;
    int slot = 1;
    while (index < len) {
      if (slot == 0) {
        return false;
      }
      if (preorder.charAt(index) == ',') {
        index++;
      } else if (preorder.charAt(index) == '#') {
        slot--;
        index++;
      } else {
        while (index < len && preorder.charAt(index) != ',') {
          index++;
        }
        slot++;
      }
    }
    return slot == 0;
  }

  public int isValidSerializationIndex = 0;

  public boolean isValidSerializationIII(String preorder) {
    return isValidSerializationIIIHelp(preorder) && isValidSerializationIndex >= preorder.length();
  }

  public boolean isValidSerializationIIIHelp(String preorder) {
    if (isValidSerializationIndex >= preorder.length()) {
      return false;
    }
    if (preorder.charAt(isValidSerializationIndex) == '#') {
      isValidSerializationIndex += 2;
      return true;
    }
    while (isValidSerializationIndex < preorder.length()
        && Character.isDigit(preorder.charAt(isValidSerializationIndex))) {
      isValidSerializationIndex++;
    }
    isValidSerializationIndex++;
    if (!isValidSerializationIIIHelp(preorder)) {
      return false;
    }
    if (!isValidSerializationIIIHelp(preorder)) {
      return false;
    }
    return true;
  }

  public List<String> findItinerary(List<List<String>> tickets) {
    Map<String, List<String>> graph = new HashMap<>();
    for (List<String> ticket : tickets) {
      List<String> tos = graph.getOrDefault(ticket.get(0), new ArrayList<>());
      tos.add(ticket.get(1));
      graph.put(ticket.get(0), tos);
    }
    for (List<String> list : graph.values()) {
      Collections.sort(list);
    }
    int resLen = tickets.size() + 1;
    LinkedList<String> res = new LinkedList<>();
    res.add("JFK");
    findItinerary(graph, "JFK", res, resLen);
    return res;
  }

  public boolean findItinerary(Map<String, List<String>> graph, String current, LinkedList<String> res, int len) {
    if (!graph.containsKey(current)) {
      return false;
    }
    List<String> tos = graph.get(current);
    for (int i = 0; i < tos.size(); i++) {
      String temp = tos.get(i);
      if (temp.equals("#")) {
        continue;
      }
      res.addLast(temp);
      tos.set(i, "#");
      if (res.size() == len) {
        return true;
      }
      if (findItinerary(graph, temp, res, len)) {
        return true;
      }
      res.removeLast();
      tos.set(i, temp);
    }
    return false;
  }

  public List<String> findItineraryII(List<List<String>> tickets) {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    for (List<String> ticket : tickets) {
      PriorityQueue<String> tos = graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>());
      tos.add(ticket.get(1));
    }
    LinkedList<String> res = new LinkedList<>();
    findItineraryII(graph, "JFK", res);
    return res;
  }

  public void findItineraryII(Map<String, PriorityQueue<String>> graph, String from, LinkedList<String> res) {
    PriorityQueue<String> tos = graph.get(from);
    while (tos != null && !tos.isEmpty()) {
      findItineraryII(graph, tos.remove(), res);
    }
    res.addFirst(from);
  }

  int largestBSTSubtreeRes = 0;

  public int largestBSTSubtree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    largestBSTSubtreeHelp(root);
    return largestBSTSubtreeRes;
  }

  public int[] largestBSTSubtreeHelp(TreeNode root) {
    if (root.left == null && root.right == null) {
      largestBSTSubtreeRes = Math.max(largestBSTSubtreeRes, 1);
      return new int[] { root.val, root.val, 1 };
    }

    int left = root.val, right = root.val, size = 1;
    boolean valid = true;
    if (root.left != null) {
      int[] leftTree = largestBSTSubtreeHelp(root.left);
      if (leftTree[2] != -1 && left > leftTree[1]) {
        left = leftTree[0];
        size += leftTree[2];
      } else {
        valid = false;
      }
    }
    if (root.right != null) {
      int[] rightTree = largestBSTSubtreeHelp(root.right);
      if (rightTree[2] != -1 && rightTree[0] > root.val) {
        right = rightTree[1];
        size += rightTree[2];
      } else {
        valid = false;
      }
    }
    if (!valid) {
      return new int[] { -1, -1, -1 };
    }
    int[] res = new int[3];
    res[0] = left;
    res[1] = right;
    res[2] = size;
    largestBSTSubtreeRes = Math.max(size, largestBSTSubtreeRes);
    return res;
  }

  public boolean increasingTriplet(int[] nums) {
    if (nums.length < 3) {
      return false;
    }
    int small = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] <= small) {
        small = nums[i];
      } else if (nums[i] <= mid) {
        mid = nums[i];
      } else {
        return true;
      }
    }
    return false;
  }

  public boolean isSelfCrossing(int[] distance) {
    if (distance.length <= 3) {
      return false;
    }
    for (int i = 3; i < distance.length; i++) {
      if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
        return true;
      }
      if (i < 4) {
        continue;
      }
      if (distance[i - 1] == distance[i - 3] && distance[i] >= distance[i - 2] - distance[i - 4]) {
        return true;
      }
      if (i < 5) {
        continue;
      }
      if (distance[i - 1] >= distance[i - 3] - distance[i - 5] && distance[i - 1] <= distance[i - 3]
          && distance[i] >= distance[i - 2] - distance[i - 4] && distance[i - 2] >= distance[i]
          && distance[i - 2] >= distance[i - 4]) {
        return true;
      }
    }
    return false;
  }

  public boolean isSelfCrossingII(int[] distance) {
    if (distance.length <= 3) {
      return false;
    }
    int i = 2, len = distance.length;
    while (i < len && distance[i] > distance[i - 2]) {
      i++;
    }
    if (i == len) {
      return false;
    }
    if ((i == 3 && distance[i] == distance[i - 2]) || (i >= 4 && distance[i] >= distance[i - 2] - distance[i - 4])) {
      distance[i - 1] -= distance[i - 3];
    }
    i++;
    while (i < len && distance[i] < distance[i - 2]) {
      i++;
    }
    return i != len;
  }

  public class DictionaryNode {

    public int index;
    public int[] children = new int[26];

    public DictionaryNode() {
      this.index = -1;
    }
  }

  public List<DictionaryNode> dictionaryTree = new ArrayList<>();

  public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> res = new ArrayList<>();
    dictionaryTree.add(new DictionaryNode());
    for (int i = 0; i < words.length; i++) {
      insertIntoDictionaryTree(words[i], i);
    }
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      int wordLen = word.length();
      for (int j = 0; j <= wordLen; j++) {
        if (isPalindrome(word, j, wordLen - 1)) {
          int temp = findInDictionaryTree(word, 0, j - 1);
          if (temp != -1 && temp != i) {
            res.add(Arrays.asList(i, temp));
          }
        }
        if (j > 0 && isPalindrome(word, 0, j - 1)) {
          int temp = findInDictionaryTree(word, j, wordLen - 1);
          if (temp != -1 && temp != i) {
            res.add(Arrays.asList(temp, i));
          }
        }
      }
    }
    return res;
  }

  public void insertIntoDictionaryTree(String s, int index) {
    int start = 0, len = s.length();
    for (int i = 0; i < len; i++) {
      DictionaryNode temp = dictionaryTree.get(start);
      int tempIndex = s.charAt(i) - 'a';
      if (temp.children[tempIndex] == 0) {
        dictionaryTree.add(new DictionaryNode());
        temp.children[tempIndex] = dictionaryTree.size() - 1;
      }
      start = temp.children[tempIndex];
    }
    dictionaryTree.get(start).index = index;
  }

  public int findInDictionaryTree(String s, int left, int right) {
    int start = 0;
    for (int i = right; i >= left; i--) {
      DictionaryNode temp = dictionaryTree.get(start);
      int tempIndex = s.charAt(i) - 'a';
      if (temp.children[tempIndex] == 0) {
        return -1;
      }
      start = temp.children[tempIndex];
    }
    return dictionaryTree.get(start).index;
  }

  public boolean isPalindrome(String s, int left, int right) {
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  public int rob(TreeNode root) {
    int[] res = robImpl(root);
    return Math.max(res[0], res[1]);
  }

  public int[] robImpl(TreeNode root) {
    if (root == null) {
      return new int[] { 0, 0 };
    }
    int[] left = robImpl(root.left);
    int[] right = robImpl(root.right);
    int maxDo = left[1] + right[1] + root.val;
    int maxNotDo = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    return new int[] { maxDo, maxNotDo };
  }

  public int[] countBits(int n) {
    int[] res = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      int temp = i;
      int count = 0;
      while (temp > 0) {
        temp = temp & (temp - 1);
        count++;
      }
      res[i] = count;
    }
    return res;
  }

  public int[] countBitsII(int n) {
    int[] res = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      res[i] = res[i >> 1] + (i & 1);
    }
    return res;
  }

}

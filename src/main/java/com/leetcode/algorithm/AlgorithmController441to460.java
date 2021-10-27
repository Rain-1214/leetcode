package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.TreeNode;

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

  public int numberOfArithmeticSlices(int[] nums) {
    if (nums.length < 3) {
      return 0;
    }
    int res = 0;
    Map<Long, Integer>[] cache = new Map[nums.length];
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        long tolerance = (long) nums[j] - (long) nums[i];
        res += numberOfArithmeticSlices(nums, j + 1, nums[j], 2, tolerance, cache);
      }
    }
    return res;
  }

  public int numberOfArithmeticSlices(int[] nums, int start, int prev, int num, long tolerance,
      Map<Long, Integer>[] cache) {
    if (start >= nums.length) {
      return 0;
    }
    Map<Long, Integer> map = cache[start];
    if (map != null && map.containsKey(tolerance)) {
      return map.get(tolerance);
    }
    int res = 0;
    for (int i = start; i < nums.length; i++) {
      int temp = nums[i];
      if ((long) temp - (long) prev == tolerance) {
        res++;
        res += numberOfArithmeticSlices(nums, i + 1, temp, num + 1, tolerance, cache);
      }
    }
    if (map == null) {
      map = new HashMap<Long, Integer>();
    }
    map.put(tolerance, res);
    cache[start] = map;
    return res;
  }

  public int numberOfArithmeticSlicesII(int[] nums) {
    if (nums.length < 3) {
      return 0;
    }
    Map<Long, Integer>[] dp = new Map[nums.length];
    for (int i = 0; i < nums.length; i++) {
      dp[i] = new HashMap<Long, Integer>();
    }
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        long diff = (long) nums[i] - (long) nums[j];
        int temp = dp[j].getOrDefault(diff, 0);
        res += temp;
        dp[i].put(diff, dp[i].getOrDefault(diff, 0) + temp + 1);
      }
    }
    return res;
  }

  public int numberOfBoomerangs(int[][] points) {
    if (points.length < 3) {
      return 0;
    }
    int len = points.length;
    int[][] data = new int[len][len];

    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (i == j) {
          continue;
        }
        int x = Math.abs(points[i][0] - points[j][0]);
        int y = Math.abs(points[i][1] - points[j][1]);
        data[i][j] = x * x + y * y;
      }
    }

    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < len; i++) {
      map.clear();
      for (int j = 0; j < len; j++) {
        if (i == j) {
          continue;
        }
        if (map.containsKey(data[i][j])) {
          res += 2 * map.get(data[i][j]);
        }
        map.put(data[i][j], map.getOrDefault(data[i][j], 0) + 1);
      }
    }
    return res;
  }

  public List<Integer> findDisappearedNumbers(int[] nums) {
    int max = 0;
    List<Integer> res = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      set.add(n);
      max = Math.max(max, n);
    }
    max = Math.max(max, nums.length);
    for (int i = 1; i <= max; i++) {
      if (!set.contains(i)) {
        res.add(i);
      }
    }
    return res;
  }

  public List<Integer> findDisappearedNumbersII(int[] nums) {
    List<Integer> res = new ArrayList<>();
    int len = nums.length;
    for (int i = 0; i < len; i++) {
      int temp = (nums[i] - 1) % len;
      nums[temp] += len;
    }
    for (int i = 0; i < len; i++) {
      if (nums[i] <= len) {
        res.add(i + 1);
      }
    }
    return res;
  }

  public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      if (root == null) {
        return "";
      }
      StringBuilder sb = new StringBuilder();
      serialize(root, sb);
      return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
      if (root == null) {
        sb.append("#");
        return;
      }
      sb.append(root.val);
      sb.append('(');
      serialize(root.left, sb);
      serialize(root.right, sb);
      sb.append(')');
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      if (data.length() == 0) {
        return null;
      }
      return deserialize(data.toCharArray(), new int[] { 0 });
    }

    public TreeNode deserialize(char[] data, int[] idx) {
      if (data[idx[0]] == '#') {
        idx[0]++;
        return null;
      }
      TreeNode root = new TreeNode();
      int val = 0;
      while (idx[0] < data.length) {
        if (Character.isDigit(data[idx[0]])) {
          val = val * 10 + (data[idx[0]] - '0');
          idx[0]++;
        } else {
          break;
        }
      }
      root.val = val;
      idx[0]++;
      root.left = deserialize(data, idx);
      root.right = deserialize(data, idx);
      idx[0]++;
      return root;
    }
  }

  public TreeNode deleteParent = null;

  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
      return root;
    }
    TreeNode target = findNode(root, key);
    if (target == null) {
      return root;
    }
    if (deleteParent == null) {
      return mergeTreeNode(root);
    }
    boolean isLeft = deleteParent.left != null && deleteParent.left.val == key;
    if (isLeft) {
      deleteParent.left = mergeTreeNode(target);
    } else {
      deleteParent.right = mergeTreeNode(target);
    }
    return root;
  }

  public TreeNode mergeTreeNode(TreeNode root) {
    if (root.right == null) {
      return root.left;
    }
    if (root.left == null) {
      return root.right;
    }
    TreeNode target = root.right;
    while (target.left != null) {
      target = target.left;
    }
    target.left = root.left;
    return root.right;
  }

  public TreeNode findNode(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    if (root.val == key) {
      return root;
    }
    TreeNode temp = root.val > key ? findNode(root.left, key) : findNode(root.right, key);
    if (deleteParent == null && temp != null) {
      deleteParent = root;
    }
    return temp;
  }

  public String frequencySort(String s) {
    char[] sa = s.toCharArray();
    int[][] nums = new int[62][2];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = new int[] { i, 0 };
    }
    for (char a : sa) {
      if (a >= 'a' && a <= 'z') {
        nums[a - 'a'][1]++;
      } else if (a >= 'A' && a <= 'Z') {
        nums[(a - 'A') + 26][1]++;
      } else {
        nums[(a - '0') + 52][1]++;
      }
    }
    Arrays.sort(nums, (a, b) -> b[1] - a[1]);
    int index = 0;
    for (int i = 0; i < nums.length; i++) {
      int temp = nums[i][0];
      int n = 0;
      if (temp >= 0 && temp < 26) {
        while (n < nums[i][1]) {
          sa[index++] = (char) (temp + 'a');
          n++;
        }
      } else if (temp >= 26 && temp < 52) {
        while (n < nums[i][1]) {
          sa[index++] = (char) (temp - 26 + 'A');
          n++;
        }
      } else {
        while (n < nums[i][1]) {
          sa[index++] = (char) (temp - 52 + '0');
          n++;
        }
      }
    }
    return new String(sa);
  }

  public String frequencySortII(String s) {
    char[] sa = s.toCharArray();
    int[] nums = new int[128];

    for (char a : sa) {
      nums[a]++;
    }
    int index = 0;
    while (index < sa.length) {
      int maxIndex = 0;
      for (int j = 0; j < nums.length; j++) {
        if (nums[j] > nums[maxIndex]) {
          maxIndex = j;
        }
      }

      while (nums[maxIndex] > 0) {
        sa[index++] = (char) maxIndex;
        nums[maxIndex]--;
      }
    }

    return new String(sa);
  }

  public int findMinArrowShots(int[][] points) {
    if (points.length == 0) {
      return 0;
    }

    Arrays.sort(points, (a, b) -> {
      if (a[1] > b[1]) {
        return 1;
      } else if (a[1] < b[1]) {
        return -1;
      } else {
        return 0;
      }
    });

    int p = points[0][1];
    int res = 1;
    for (int[] point: points) {
      if (point[0] > p) {
        p = point[1];
        res ++;
      }
    }
    return res;
  }

  public int minMoves(int[] nums) {
    int min = Integer.MAX_VALUE;
    for (int n: nums) {
      min = Math.min(n, min);
    }
    int res = 0;
    for (int n: nums) {
      res += n - min;
    }
    return res;
  }

  public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    int[][] temp = new int[][]{nums1, nums2, nums3, nums4};
    Map<Long, Integer>[] cache = new Map[]{new HashMap<>(), new HashMap<>(),new HashMap<>(),new HashMap<>()};
    return fourSumCount(temp, 0, cache, 0);
  }

  public int fourSumCount(int[][] nums, int index, Map<Long, Integer>[] caches, long target) {
    int res = 0;
    int[] temp = nums[index];
    Map<Long, Integer> cache = caches[index];
    if (cache.containsKey(target)) {
      return cache.get(target);
    }
    if (index == nums.length - 1) {
      for (int n: temp) {
        if (n + target == 0) {
          res++;
        }
      }
      cache.put(target, res);
      return res;
    }
    for (int i = 0; i < temp.length; i++) {
      res += fourSumCount(nums, index + 1, caches, target + temp[i]);
    }
    cache.put(target, res);
    return res;
  }

  public int fourSumCountII(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i: nums1) {
      for (int j : nums2) {
        map.merge(i + j, 1, Integer::sum);
      }
    }

    int res = 0;
    for (int i: nums3) {
      for (int j: nums4) {
        int temp = -i - j;
        if (map.containsKey(temp)) {
          res += map.get(temp);
        }
      }
    }
    return res;
  }

  public int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);
    Arrays.sort(s);
    int gi = g.length - 1, si = s.length -1;
    int res = 0;
    while (gi >= 0 && si >= 0) {
      int sCurrent = s[si];
      int gCurrent = g[gi];
      if (sCurrent >= gCurrent) {
        gi--;
        si--;
        res++;
      } else {
        while(gi >= 0 && sCurrent < g[gi]) {
          gi--;
        }
        if (gi < 0) {
          break;
        } else {
          res++;
          gi--;
          si--;
        }
      }
    }
    return res;
  }

}

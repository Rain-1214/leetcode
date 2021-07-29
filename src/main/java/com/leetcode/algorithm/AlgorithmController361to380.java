package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
<<<<<<< HEAD
import java.util.Random;
=======
>>>>>>> master
import java.util.Set;
import java.util.TreeSet;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.NestedInteger;
import com.leetcode.entity.TreeNode;
import com.leetcode.tool.GuessGame;
import com.leetcode.tool.Print;

public class AlgorithmController361to380 {

  public int maxKilledEnemies(char[][] grid) {
    int max = 0;
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == '0') {
          max = Math.max(max, maxKilledEnemiesHelp(grid, row, col));
        }
      }
    }
    return max;
  }

  public int maxKilledEnemiesHelp(char[][] grid, int row, int col) {
    int rowMax = grid.length;
    int colMax = grid[0].length;
    int num = 0;
    for (int i = row; i >= 0; i--) {
      char current = grid[i][col];
      if (current == 'W') {
        break;
      }
      if (current == 'E') {
        num++;
      }
    }
    for (int i = row; i < rowMax; i++) {
      char current = grid[i][col];
      if (current == 'W') {
        break;
      }
      if (current == 'E') {
        num++;
      }
    }
    for (int i = col; i >= 0; i--) {
      char current = grid[row][i];
      if (current == 'W') {
        break;
      }
      if (current == 'E') {
        num++;
      }
    }
    for (int i = col; i < colMax; i++) {
      char current = grid[row][i];
      if (current == 'W') {
        break;
      }
      if (current == 'E') {
        num++;
      }
    }
    return num;
  }

  class HitCounter {

    public Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public HitCounter() {
      this.map = new HashMap<>();
    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
      this.map.put(timestamp, this.map.getOrDefault(timestamp, 0) + 1);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
      int sum = 0;
      for (int i = timestamp; i > timestamp - 300; i--) {
        if (this.map.containsKey(i)) {
          sum += this.map.get(i);
        }
      }
      return sum;
    }
  }

  class HitCounterII {

    public Queue<Integer> q;
    public int count;

    /** Initialize your data structure here. */
    public HitCounterII() {
      this.q = new LinkedList<Integer>();
      this.count = 0;
    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
      this.q.add(timestamp);
      this.count++;
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
      if (q.size() == 0) {
        return 0;
      }
      while (q.peek() < timestamp - 299) {
        q.poll();
        count--;
        if (q.size() == 0) {
          return 0;
        }
      }
      return count;
    }
  }

  class HitCounterIII {

    public ArrayList<Integer> list;

    /** Initialize your data structure here. */
    public HitCounterIII() {
      this.list = new ArrayList<Integer>();
    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
      this.list.add(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
      int sum = 0;
      for (int i = list.size() - 1; i >= 0; i--) {
        if (list.get(i) < timestamp - 300) {
          break;
        }
        sum++;
      }
      return sum;
    }
  }

  public int maxSumSubmatrix(int[][] matrix, int k) {
    int res = Integer.MIN_VALUE;
    int rowMax = matrix.length, colMax = matrix[0].length;
    for (int i = 0; i < rowMax; i++) {
      int[] preSum = new int[colMax];
      for (int j = i; j < rowMax; j++) {
        for (int z = 0; z < colMax; z++) {
          preSum[z] = preSum[z] + matrix[j][z];
        }
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int area = 0;
        for (int n : preSum) {
          area += n;
          Integer temp = set.ceiling(area - k);
          if (temp != null) {
            res = Math.max(res, area - temp);
          }
          set.add(area);
        }
      }
    }
    return res;
  }

  public int depthSumInverse(List<NestedInteger> nestedList) {
    int deep = findNestedIntegerDeep(nestedList, 1);
    return depthSumInverse(nestedList, deep);
  }

  public int depthSumInverse(List<NestedInteger> nestedList, int deep) {
    int sum = 0;
    for (int i = 0; i < nestedList.size(); i++) {
      NestedInteger temp = nestedList.get(i);
      if (temp.isInteger()) {
        sum += temp.getInteger() * deep;
      } else {
        sum += depthSumInverse(temp.getList(), deep - 1);
      }
    }
    return sum;
  }

  public int findNestedIntegerDeep(List<NestedInteger> nestedList, int deep) {
    int res = deep;
    for (int i = 0; i < nestedList.size(); i++) {
      NestedInteger temp = nestedList.get(i);
      if (!temp.isInteger()) {
        res = Math.max(res, findNestedIntegerDeep(temp.getList(), deep + 1));
      }
    }
    return res;
  }

  public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
    if (jug1Capacity + jug2Capacity < targetCapacity) {
      return false;
    }
    if (jug1Capacity == 0 || jug2Capacity == 0) {
      return targetCapacity == 0 || jug2Capacity + jug1Capacity == targetCapacity;
    }
    return targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;
  }

  public int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    while (root.left != null || root.right != null) {
      List<Integer> temp = new ArrayList<>();
      findLeaves(root, temp);
      res.add(temp);
    }
    List<Integer> rootVal = new ArrayList<>();
    rootVal.add(root.val);
    res.add(rootVal);
    return res;
  }

  public void findLeaves(TreeNode root, List<Integer> res) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      if (root.left.left == null && root.left.right == null) {
        res.add(root.left.val);
        root.left = null;
      } else {
        findLeaves(root.left, res);
      }
    }
    if (root.right != null) {
      if (root.right.left == null && root.right.right == null) {
        res.add(root.right.val);
        root.right = null;
      } else {
        findLeaves(root.right, res);
      }
    }
  }

  public boolean isPerfectSquare(int num) {
    for (int i = 1; i * i <= num; i++) {
      if (i * i == num) {
        return true;
      }
    }
    return false;
  }

  public boolean isPerfectSquareII(int num) {
    if (num < 2) {
      return true;
    }
    long left = 2, right = num / 2, mid, res;
    while (left <= right) {
      mid = (left + right) / 2;
      res = mid * mid;
      if (res == num) {
        return true;
      }
      if (res < num) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return false;
  }

  public List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);
    int len = nums.length;
    int[] dp = new int[len];
    dp[0] = 1;
    Arrays.fill(dp, 1);
    int maxSize = 1;
    int maxVal = nums[0];
    for (int i = 1; i < len; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] % nums[j] == 0) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      if (dp[i] > maxSize) {
        maxSize = dp[i];
        maxVal = nums[i];
      }
    }

    List<Integer> res = new ArrayList<>();
    if (maxSize == 1) {
      res.add(nums[0]);
      return res;
    }
    for (int i = len - 1; i >= 0; i--) {
      if (maxVal % nums[i] == 0 && dp[i] == maxSize) {
        res.add(nums[i]);
        maxSize--;
        maxVal = nums[i];
      }
    }
    return res;
  }

  public ListNode plusOne(ListNode head) {
    boolean pre = plusOneHelper(head);
    if (pre) {
      return new ListNode(1, head);
    }
    return head;
  }

  public boolean plusOneHelper(ListNode head) {
    if (head == null) {
      return true;
    }
    if (plusOneHelper(head.next)) {
      if (head.val == 9) {
        head.val = 0;
        return true;
      }
      head.val += 1;
      return false;
    }
    return false;
  }

  public int[] getModifiedArray(int length, int[][] updates) {
    int[] nums = new int[length];
    for (int[] update : updates) {
      getModifiedArrayHelper(nums, update[0], update[1], update[2]);
    }
    return nums;
  }

  public void getModifiedArrayHelper(int[] nums, int start, int end, int val) {
    for (int i = start; i <= end; i++) {
      nums[i] += val;
    }
  }

  public int[] getModifiedArrayII(int length, int[][] updates) {
    int[] nums = new int[length];
    for (int[] update : updates) {
      nums[update[0]] += update[2];
      if (update[1] + 1 < length) {
        nums[update[1] + 1] -= update[2];
      }
    }
    for (int i = 1; i < length; i++) {
      nums[i] += nums[i - 1];
    }
    return nums;
  }

  public int getSum(int a, int b) {
    return (b == 0) ? a : getSum(a ^ b, (a & b) << 1);
  }

  public static final int superPowMod = 1337;

  public int power(int a, int k) {
    if (k == 0) {
      return 1;
    }
    int res = 1;
    a %= superPowMod;
    while (k > 0) {
      res *= a;
      res %= superPowMod;
      k--;
    }
    return res;
  }

  public int powerII(int a, int k) {
    if (k == 0) {
      return 1;
    }
    a %= superPowMod;

    if (k % 2 == 1) {
      return (a * power(a, k - 1)) % superPowMod;
    } else {
      int temp = power(a, k / 2);
      return (temp * temp) % superPowMod;
    }
  }

  public int superPowHelper(int a, int[] b, int index) {
    if (index < 0) {
      return 1;
    }
    int temp = power(a, b[index]);
    int temp2 = power(superPowHelper(a, b, index - 1), 10);

    return temp * temp2 % superPowMod;
  }

  public int superPow(int a, int[] b) {
    return superPowHelper(a, b, b.length - 1);
  }

  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> tempRes = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    for (int n1 : nums1) {
      for (int n2 : nums2) {
        List<Integer> temp = new ArrayList<>();
        temp.add(n1);
        temp.add(n2);
        tempRes.add(temp);
      }
    }
    if (k >= nums1.length * nums2.length) {
      return tempRes;
    }

    int len = tempRes.size();
    for (int i = len / 2 - 1; i >= 0; i--) {
      sortHeap(tempRes, i, len);
    }

    for (int i = 0; i < k; i++) {
      res.add(tempRes.get(0));
      swap(tempRes, 0, len - 1 - i);
      sortHeap(tempRes, 0, len - 1 - i);
    }

    return res;
  }

  public void sortHeap(List<List<Integer>> list, int a, int length) {
    List<Integer> temp = list.get(a);
    int tempVal = temp.get(0) + temp.get(1);
    for (int i = a * 2 + 1; i < length; i = i * 2 + 1) {
      if (i + 1 < length && getListValue(list, i) > getListValue(list, i + 1)) {
        i++;
      }
      if (tempVal > getListValue(list, i)) {
        list.set(a, list.get(i));
        a = i;
      }
    }
    list.set(a, temp);
  }

  public int getListValue(List<List<Integer>> list, int index) {
    return list.get(index).get(0) + list.get(index).get(1);
  }

  public void swap(List<List<Integer>> list, int a, int b) {
    List<Integer> t = list.get(a);
    list.set(a, list.get(b));
    list.set(b, t);
  }

  public List<List<Integer>> kSmallestPairsII(int[] nums1, int[] nums2, int k) {
    int len1 = nums1.length, len2 = nums2.length;
    List<List<Integer>> res = new ArrayList<>();
    if (len1 == 0 || len2 == 0 || k == 0) {
      return res;
    }
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
      return (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]);
    });
    for (int i = 0; i < len1; i++) {
      pq.offer(new int[] { i, 0 });
    }
    while (!pq.isEmpty() && k > 0) {
      int[] temp = pq.poll();
      if (temp[1] + 1 < len2) {
        pq.offer(new int[] { temp[0], temp[1] + 1 });
      }
      res.add(Arrays.asList(nums1[temp[0]], nums2[temp[1]]));
      k--;
    }
    return res;
  }

  public class Solution extends GuessGame {
    public int guessNumber(int n) {
      int left = 1, right = n;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        int temp = this.guess(mid);
        if (temp == 0) {
          return mid;
        } else if (temp == 1) {
          left = mid + 1;
        } else if (temp == -1) {
          right = mid - 1;
        }
      }
      return 1;
    }
  }

  public int getMoneyAmount(int n) {
    int[][] dp = new int[n + 1][n + 1];
    for (int len = 2; len <= n; len++) {
      for (int start = 1; start <= n - len + 1; start++) {
        int min = Integer.MAX_VALUE;
        for (int i = start; i < start + len - 1; i++) {
          min = Math.min(min, i + Math.max(dp[start][i - 1], dp[i + 1][start + len - 1]));
        }
        dp[start][start + len - 1] = min;
      }
    }
    return dp[1][n];
  }

  public int wiggleMaxLength(int[] nums) {
    int len = nums.length;
    if (len < 2) {
      return 1;
    }
    int[] up = new int[len];
    int[] down = new int[len];
    up[0] = down[0] = 1;
    for (int i = 1; i < len; i++) {
      if (nums[i] > nums[i - 1]) {
        up[i] = Math.max(up[i - 1], down[i - 1] + 1);
        down[i] = down[i - 1];
      } else if (nums[i] < nums[i - 1]) {
        down[i] = Math.max(down[i - 1], up[i - 1] + 1);
        up[i] = up[i - 1];
      } else {
        up[i] = up[i - 1];
        down[i] = down[i - 1];
      }
    }
    return Math.max(up[len - 1], down[len - 1]);
  }

  public int wiggleMaxLengthII(int[] nums) {
    int len = nums.length;
    if (len < 2) {
      return 1;
    }
    int preDiff = nums[1] - nums[0];
    int res = preDiff == 0 ? 1 : 2;
    for (int i = 2; i < len; i++) {
      int diff = nums[i] - nums[i - 1];
      if (diff < 0 && preDiff >= 0 || diff > 0 && preDiff <= 0) {
        res++;
        preDiff = diff;
      }
    }
    return res;
  }

  public Map<Integer, Integer> combinationSum4Cache = new HashMap<Integer, Integer>();

  public int combinationSum4(int[] nums, int target) {
    if (target < 0) {
      return 0;
    }
    if (combinationSum4Cache.containsKey(target)) {
      return combinationSum4Cache.get(target);
    }
    int sum = 0;
    for (int num : nums) {
      if (target == num) {
        sum++;
        continue;
      }
      sum += combinationSum4(nums, target - num);
    }
    combinationSum4Cache.put(target, sum);
    return sum;
  }

  public int combinationSum4II(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; i++) {
      for (int num : nums) {
        if (num <= i) {
          dp[i] += dp[i - num];
        }
      }
    }
    return dp[target];
  }

  public int kthSmallest(int[][] matrix, int k) {
    int len = matrix.length;
    int left = matrix[0][0];
    int right = matrix[len - 1][len - 1];
    while (left < right) {
      int mid = (left + right) / 2;
      if (kthSmallest(matrix, k, mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  public boolean kthSmallest(int[][] matrix, int k, int mid) {
    int num = 0, len = matrix.length;
    int row = len - 1;
    int col = 0;
    while (col < len && row >= 0) {
      if (matrix[row][col] <= mid) {
        col++;
        num += row + 1;
      } else {
        row--;
      }
    }
    return num >= k;
  }

  class PhoneDirectory {

    Queue<Integer> directory;
    Set<Integer> used;

    /**
     * Initialize your data structure here
     * 
     * @param maxNumbers - The maximum numbers that can be stored in the phone
     *                   directory.
     */
    public PhoneDirectory(int maxNumbers) {
      this.directory = new LinkedList<>();
      this.used = new HashSet<>();
      for (int i = 0; i < maxNumbers; i++) {
        directory.add(i);
      }
    }

    /**
     * Provide a number which is not assigned to anyone.
     * 
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
      if (directory.isEmpty()) {
        return -1;
      }
      int temp = directory.poll();
      used.add(temp);
      return temp;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
      return !used.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
      if (!used.contains(number)) {
        return;
      }
      used.remove(number);
      directory.add(number);
    }
  }

  class RandomizedSet {

    public Random random;
    public Map<Integer, Integer> map;
    public List<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedSet() {
      this.random = new Random();
      this.map = new HashMap<>();
      this.list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
      if (map.containsKey(val)) {
        return false;
      }
      int size = list.size();
      map.put(val, size);
      list.add(val);
      return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified
     * element.
     */
    public boolean remove(int val) {
      if (!map.containsKey(val)) {
        return false;
      }
      int index = map.get(val);
      int lastValue = list.get(list.size() - 1);
      map.put(lastValue, index);
      list.set(index, lastValue);
      list.remove(list.size() - 1);
      map.remove(val);
      return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
      return list.get(random.nextInt(list.size()));
    }
  }

  class RandomizedCollection {

    public Random random;
    public Map<Integer, Set<Integer>> map;
    public List<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
      this.random = new Random();
      this.map = new HashMap<>();
      this.list = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not
     * already contain the specified element.
     */
    public boolean insert(int val) {
      boolean res = !map.containsKey(val);
      Set<Integer> q = map.getOrDefault(val, new HashSet<Integer>());
      q.add(list.size());
      map.put(val, q);
      list.add(val);
      return res;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained
     * the specified element.
     */
    public boolean remove(int val) {
      boolean res = map.containsKey(val);
      if (!res) {
        return res;
      }
      int len = list.size();
      int lastValue = list.get(len - 1);

      Set<Integer> lastValueIndex = map.get(lastValue);
      Set<Integer> currentValueIndex = map.get(val);
      int currentIndex = currentValueIndex.iterator().next();
      currentValueIndex.remove(currentIndex);

      lastValueIndex.add(currentIndex);
      lastValueIndex.remove(len - 1);
      list.set(currentIndex, lastValue);
      if (currentValueIndex.isEmpty()) {
        map.remove(val);
      }
      list.remove(len - 1);
      return res;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
      return list.get(random.nextInt(list.size()));
    }
  }

}

package com.leetcode.algorithm;

import java.util.Arrays;

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

}

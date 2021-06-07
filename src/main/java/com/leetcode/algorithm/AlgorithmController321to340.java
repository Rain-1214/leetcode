package com.leetcode.algorithm;

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

}

package com.leetcode.algorithm;

import java.util.Arrays;

import com.leetcode.entity.TreeNode;

public class AlgorithmController561to580 {

  public int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int sum = 0;
    for (int i = 0; i < nums.length; i += 2) {
      sum += nums[i];
    }
    return sum;
  }

  int findTiltResult = 0;

  public int findTilt(TreeNode root) {
    this.findTiltHelp(root);
    return this.findTiltResult;
  }

  public int findTiltHelp(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftSum = this.findTiltHelp(root.left);
    int rightSum = this.findTiltHelp(root.right);
    this.findTiltResult += Math.abs(leftSum - rightSum);
    return leftSum + rightSum + root.val;

  }

}

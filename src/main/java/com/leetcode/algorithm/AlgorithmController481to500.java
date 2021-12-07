package com.leetcode.algorithm;

public class AlgorithmController481to500 {

  public int magicalString(int n) {
    if (n <= 3) {
      return 1;
    }
    int[] temp = new int[n];
    temp[0] = 1;
    temp[1] = 2;
    temp[2] = 2;
    int left = 2, right = 3, count = 1;
    while (right < n) {
      if (temp[left] == 1) {
        temp[right] = temp[right - 1] == 1 ? 2 : 1;
        count += temp[right] == 1 ? 1 : 0;
      } else if (temp[left] == 2) {
        temp[right] = temp[right - 1] == 1 ? 2 : 1;
        count += temp[right] == 1 ? 1 : 0;
        if (right + 1 < n) {
          temp[right + 1] = temp[right];
          count += temp[right + 1] == 1 ? 1 : 0;
        }
        right++;
      }
      right++;
      left++;
    }
    return count;
  }

  public String licenseKeyFormatting(String s, int k) {
    char[] chars = s.toCharArray();
    StringBuilder sb = new StringBuilder();
    int temp = 0;
    for (int i = chars.length - 1; i >= 0; i--) {
      if (chars[i] == '-') {
        continue;
      }
      char c = chars[i];
      if (c >= 'a' && c <= 'z') {
        c -= 32;
      }
      sb.append(c);
      temp++;
      if (temp == k) {
        sb.append('-');
        temp = 0;
      }
    }
    if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.reverse().toString();
  }

  public String smallestGoodBase(String n) {
    long num = Long.parseLong(n);
    int mMax = (int) (Math.log(num) / Math.log(2));
    for (int m = mMax; m >= 2; m--) {
      int k = (int) Math.pow(num, 1.0 / m);
      long temp = 1, sum = 1;
      for (int i = 0; i < m; i++) {
        temp *= k;
        sum += temp;
      }
      if (sum == num) {
        return String.valueOf(k);
      }
    }
    return String.valueOf(num - 1);
  }

  public int[] findPermutation(String s) {
    char[] sc = s.toCharArray();
    int[] res = new int[sc.length + 1];
    for (int i = 0; i <= sc.length; i++) {
      res[i] = i + 1;
    }
    int left = 0, right = 0;
    while (left < sc.length) {
      if (sc[left] == 'I') {
        left++;
        continue;
      }
      while (right < sc.length && sc[right] == 'D') {
        right++;
      }
      int l = left, r = right;
      while (l < r) {
        swap(res, l, r);
        l++;
        r--;
      }
      left = right + 1;
      right = left;
    }
    return res;
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int res = 0, count = 0;
    for (int n : nums) {
      if (n == 1) {
        count++;
      } else {
        res = Math.max(res, count);
        count = 0;
      }
    }
    return Math.max(res, count);
  }

  public boolean PredictTheWinner(int[] nums) {
    return PredictTheWinner(nums, 0, nums.length - 1, 1) >= 0;
  }

  public int PredictTheWinner(int[] nums, int left, int right, int turn) {
    if (left == right) {
      return nums[left] * turn;
    }
    int leftVal = nums[left] * turn + PredictTheWinner(nums, left + 1, right, -turn);
    int rightVal = nums[right] * turn + PredictTheWinner(nums, left, right - 1, -turn);
    if (turn == 1) {
      return Math.max(leftVal, rightVal);
    } else {
      return Math.min(leftVal, rightVal);
    }
  }

  public boolean PredictTheWinnerII(int[] nums) {
    int[][] dp = new int[nums.length][nums.length];
    for (int i = 0; i < nums.length; i++) {
      dp[i][i] = nums[i];
    }
    for (int i = nums.length - 2; i >= 0; i--) {
      for (int j = i + 1; j < nums.length; j++) {
        dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
      }
    }
    return dp[0][nums.length - 1] >= 0;
  }

  public int findMaxConsecutiveOnes487(int[] nums) {
    if (nums.length == 1) {
      return 1;
    }
    int end = 0;
    int[] oneNums = new int[nums.length];
    int index = 0;
    while (index < nums.length) {
      if (nums[index] == 0) {
        oneNums[end++] = 0;
        index++;
        continue;
      }
      int n = 0;
      while(index < nums.length && nums[index] == 1) {
        index++;
        n++;
      }
      oneNums[end++] = n;
    }
    int res = 0;
    for (int i = 0; i < end; i++) {
      int current = oneNums[i];
      if (current == 0) {
        res = Math.max(res, 1);
        continue;
      }
      int left = i - 2 >= 0 ? current + 1 + oneNums[i - 2] : i - 1 >= 0 ? current + 1 : current;
      int right = i + 2 < end ? current + 1 + oneNums[i + 2] : i + 1 < end ? current + 1 : current;
      res = Math.max(res, Math.max(left, right));
    }
    return res;
  }

  public int findMaxConsecutiveOnes487II(int[] nums) {
    int res = 0, zeroPosition = 0, zeroCount = 0;
    for (int l = 0, r = 0; r < nums.length; r++) {
      if (nums[r] == 0) {
        zeroCount++;
        if (zeroCount > 1) {
          l = zeroPosition + 1;
          zeroCount--;
        }
        zeroPosition = r;
      }
      res = Math.max(res, r - l + 1);
    }
    return res;
  }

}

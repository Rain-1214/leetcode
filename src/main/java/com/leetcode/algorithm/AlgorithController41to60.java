package com.leetcode.algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithController41to60 {

    public void rotate(int[][] matrix) {
//        int[][] result = new int[matrix.length][matrix.length];
//        for(int i = 0; i < matrix.length; i++) {
//            for(int y = 0; y < matrix[i].length; y++) {
//                result[matrix.length - 1 - i][y] = matrix[i][y];
//            }
//        }
//        matrix = result;
    }

    public int lengthOfLastWord(String s) {
        s = s.trim();
        if (!s.contains(" ")) {
            return s.length();
        }
        int lastIndexOf = s.trim().lastIndexOf(" ");
        if (lastIndexOf > 0) {
            return s.substring(lastIndexOf + 1).length();
        } else {
            return 0;
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2Impl(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    public void combinationSum2Impl(List<List<Integer>> resultList, List<Integer> currentList, int[] candidates, int startIndex, int target) {
        if (target > 0) {
            for (int i = startIndex; i < candidates.length; i++) {
                if (i > startIndex && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                currentList.add(candidates[i]);
                combinationSum2Impl(resultList, currentList, candidates, i + 1, target - candidates[i]);
                currentList.remove(currentList.size() - 1);
            }
        } else if (target == 0) {
            resultList.add(new ArrayList<>(currentList));
        }
    }

    public int firstMissingPositive(int[] nums) {
        int maxNum = 0;
        for (int i = 0; i < nums.length;i++) {
            if (nums[i] < 0) {
                continue;
            }
            if (nums[i] > maxNum) {
                maxNum = nums[i];
            }
        }
        if (maxNum <= 0) {
            return 1;
        }
        int[] tempArray = new int[maxNum + 1];
        for (int y = 0; y < nums.length; y++) {
            if (nums[y] < 0) {
                continue;
            }
            tempArray[nums[y]] = 1;
        }
        for (int z = 1; z < tempArray.length; z++) {
            if (tempArray[z] != 1) {
                return z;
            }
        }
        return tempArray.length;
    }

    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }
        int left = 0;
        int right = n - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int result = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] > maxLeft) {
                    maxLeft = height[left];
                } else {
                    result += maxLeft - height[left];
                }
                left++;
            } else {
                if (height[right] > maxRight) {
                    maxRight = height[right];
                } else {
                    result += maxRight - height[right];
                }
                right--;
            }
        }
        return result;
    }

    public String multiply(String num1, String num2) {
        int num1len = num1.length();
        int num2len = num2.length();
        int[] resultArray = new int[num1len + num2len];
        for (int i = num1len - 1; i >= 0; i--) {
            for (int y = num2len - 1; y >= 0; y--) {
                int n1 = num1.charAt(i) - '0';
                int n2 = num2.charAt(y) - '0';
                int sum = n1 * n2 + resultArray[i + y + 1];
                resultArray[i + y] += sum / 10;
                resultArray[i + y + 1] = sum % 10;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int num: resultArray) {
            if (!(stringBuffer.length() == 0 && num == 0)) {
                stringBuffer.append(num);
            }
        }
        return stringBuffer.length() == 0 ? "0" : stringBuffer.toString();
    }


    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }
        int sLen = s.length();
        int pLen = p.length();
        return sLen != 0 && pLen != 0 && this.isMatch(0, 0, s, p);
    }

    private boolean isMatch(int start, int end, String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int sIndex = start;
        int pIndex = end;
        while (sIndex < sLen || pIndex < pLen) {
            char curSChar = s.charAt(sIndex);
            char curPChar = p.charAt(pIndex);
            if (curSChar == curPChar || curPChar == '?') {
                sIndex++;
                pIndex++;
            } else if (curPChar == '*') {
                if (!this.isMatch(sIndex + 1, pIndex, s, p)) {
                    pIndex++;
                } else {
                    return true;
                }
            }
        }
        return true;
    }


}



































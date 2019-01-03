package com.leetcode.algorithm;

import java.awt.*;
import java.util.*;
import java.util.List;

public class AlgorithController41to60 {

    public void rotate(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            for(int y = 0; y < matrix[i].length; y++) {
                result[matrix.length - 1 - i][y] = matrix[i][y];
            }
        }
        matrix = result;
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


//    public boolean isMatch(String str, String pattern) {
////        if (s.equals(p)) {
////            return true;
////        }
////        return this.isMatch(0, 0, s, p);
//        int s = 0, p = 0, match = 0, starIdx = -1;
//        while (s < str.length()){
//            // advancing both pointers
//            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
//                s++;
//                p++;
//            }
//            // * found, only advancing pattern pointer
//            else if (p < pattern.length() && pattern.charAt(p) == '*'){
//                starIdx = p;
//                match = s;
//                p++;
//            }
//            // last pattern pointer was *, advancing string pointer
//            else if (starIdx != -1){
//                p = starIdx + 1;
//                match++;
//                s = match;
//            }
//            //current pattern pointer is not star, last patter pointer was not *
//            //characters do not match
//            else return false;
//        }
//
//        //check for remaining characters in pattern
//        while (p < pattern.length() && pattern.charAt(p) == '*')
//            p++;
//
//        return p == pattern.length();
//    }

    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int sIndex = 0;
        int pIndex = 0;
        int cacheSIndex = 0;
        int cachePIndex = -1;
        while (sIndex < sLen) {
            if (pIndex < pLen && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?')) {
                sIndex++;
                pIndex++;
            } else if (pIndex < pLen && p.charAt(pIndex) == '*') {
                cacheSIndex = sIndex;
                cachePIndex = pIndex;
                pIndex++;
            } else if (cachePIndex != -1) {
                pIndex = cachePIndex + 1;
                cacheSIndex += 1;
                sIndex = cacheSIndex;
            } else {
                return false;
            }
        }
        while ((pIndex < pLen) && p.charAt(pIndex) == '*') {
            pIndex++;
        }
        return pIndex == pLen;
    }

    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }
        int jumpTime = 0;
        for (int i = 0; i < n - 1; i++) {
            int y = 1;
            int jumpMaxDistance = 0;
            int jumpMaxIndex = i + y;
            while (y <= nums[i]) {
                int currentMax = nums[i + y] + i + y;
                if (i + y >= n - 1) {
                    return jumpTime + 1;
                } else if (currentMax > jumpMaxDistance) {
                    jumpMaxDistance = currentMax;
                    jumpMaxIndex = i + y;
                }
                y++;
            }
            i = jumpMaxIndex - 1;
            jumpTime++;
        }
        return jumpTime;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 1) {
            return result;
        }
        findList(result, nums, 0);
        return result;
    }

    private void findList(List<List<Integer>> result, int[] nums, int start) {
        if (start == nums.length - 1) {
            List<Integer> tempList = new ArrayList<>();
            tempList.add(nums[start]);
            result.add(tempList);
        } else {
            findList(result, nums, start + 1);
            List<List<Integer>> tempResult = new ArrayList<>(result);
            result.clear();
            for (List<Integer> currentList : tempResult) {
                for (int y = 0; y <= currentList.size(); y++) {
                    List<Integer> tempList = new ArrayList<>(currentList);
                    tempList.add(y, nums[start]);
                    result.add(tempList);
                }
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 1) {
            return result;
        }
        Set<List<Integer>> set = new HashSet<>();
        findListUnique(result, nums, 0, set);
        return result;
    }

    private void findListUnique(List<List<Integer>> result, int[] nums, int start, Set<List<Integer>> set) {
        if (start == nums.length - 1) {
            List<Integer> tempList = new ArrayList<>();
            tempList.add(nums[start]);
            result.add(tempList);
        } else {
            findListUnique(result, nums, start + 1, set);
            List<List<Integer>> tempResult = new ArrayList<>(result);
            result.clear();
            for (List<Integer> currentList : tempResult) {
                for (int y = 0; y <= currentList.size(); y++) {
                    List<Integer> tempList = new ArrayList<>(currentList);
                    tempList.add(y, nums[start]);
                    if (!set.contains(tempList)) {
                        set.add(tempList);
                        result.add(tempList);
                    }
                }
            }
        }
    }
}



































package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AlgorithController41to60 {

    public void rotate(int[][] matrix) {
        // int[][] result = new int[matrix.length][matrix.length];
        // for(int i = 0; i < matrix.length; i++) {
        // for(int y = 0; y < matrix[i].length; y++) {
        // result[matrix.length - 1 - i][y] = matrix[i][y];
        // }
        // }
        // matrix = result;
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

    public void combinationSum2Impl(List<List<Integer>> resultList, List<Integer> currentList, int[] candidates,
            int startIndex, int target) {
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
        int[] tempNums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                continue;
            }
            tempNums[nums[i]] = 1;
        }
        for (int y = 1; y < tempNums.length; y++) {
            if (tempNums[y] != 1) {
                return y;
            }
        }
        return tempNums.length;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix.length == 0) {
            return result;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int time = 0;
        while (time < row && time < column) {
            for (int i = time; i < column; i++) {
                result.add(matrix[time][i]);
            }
            for (int y = time + 1; y < row; y++) {
                result.add(matrix[y][column - 1]);
            }
            for (int x = column - 2; x >= time && (row != time + 1); x--) {
                result.add(matrix[row - 1][x]);
            }
            for (int z = row - 2; z > time && (column != time + 1); z--) {
                result.add(matrix[z][time]);
            }
            time++;
            row--;
            column--;
        }
        return result;
    }
}

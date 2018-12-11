package com.leetcode.algorithm;

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

}



































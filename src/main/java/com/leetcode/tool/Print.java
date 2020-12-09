package com.leetcode.tool;

public class Print {
    public static void print2DCharArray (char[][] charArray) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < charArray.length; i++) {
            stringBuffer.append("[");
            for (int y = 0; y < charArray[i].length; y++) {
                stringBuffer.append(charArray[i][y]);
                if (y != charArray[i].length - 1) {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("]");
            stringBuffer.append("\n");
        }
        System.out.println(stringBuffer.toString());
    }
    public static void print2DIntArray (int[][] charArray) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < charArray.length; i++) {
            stringBuffer.append("[");
            for (int y = 0; y < charArray[i].length; y++) {
                stringBuffer.append(charArray[i][y]);
                if (y != charArray[i].length - 1) {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("]");
            stringBuffer.append("\n");
        }
        System.out.println(stringBuffer.toString());
    }

    public static void print2DBooleanArray (boolean[][] charArray) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < charArray.length; i++) {
            stringBuffer.append("[");
            for (int y = 0; y < charArray[i].length; y++) {
                stringBuffer.append(charArray[i][y]);
                if (y != charArray[i].length - 1) {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("]");
            stringBuffer.append("\n");
        }
        System.out.println(stringBuffer.toString());
    }

    public static String printArray (int[] value) {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i : value) {
            stringBuffer.append(i + ",");
        }
        return stringBuffer.toString();
    }

}

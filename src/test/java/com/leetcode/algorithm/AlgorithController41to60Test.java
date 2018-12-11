package com.leetcode.algorithm;

import com.leetcode.tool.Print;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlgorithController41to60Test {

    private AlgorithController41to60 algorithController41to60 = new AlgorithController41to60();

    @Test
    public void rotate() {
        int[] test1 = new int[]{1,2,3};
        int[] test2 = new int[]{4,5,6};
        int[] test3 = new int[]{7,8,9};
        int[][] test = new int[][]{test1,test2,test3};
        Print.print2DIntArray(test);
        this.algorithController41to60.rotate(test);
        Print.print2DIntArray(test);
    }

    @Test
    public void lengthOfLastWord() {
        int result = this.algorithController41to60.lengthOfLastWord("Hello World");
        System.out.println(result);
    }
}
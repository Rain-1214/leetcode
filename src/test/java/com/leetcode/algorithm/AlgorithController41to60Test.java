package com.leetcode.algorithm;

import com.leetcode.tool.Print;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AlgorithController41to60Test {

    private AlgorithController41to60 algorithController41to60 = new AlgorithController41to60();

    @Test
    public void rotate() {
        int[] test1 = new int[] { 1, 2, 3 };
        int[] test2 = new int[] { 4, 5, 6 };
        int[] test3 = new int[] { 7, 8, 9 };
        int[][] test = new int[][] { test1, test2, test3 };
        Print.print2DIntArray(test);
        this.algorithController41to60.rotate(test);
        Print.print2DIntArray(test);
    }

    @Test
    public void lengthOfLastWord() {
        int result = this.algorithController41to60.lengthOfLastWord("Hello World");
        System.out.println(result);
    }

    @Test
    public void combinationSum() {
        int[] test = new int[] { 10, 1, 2, 7, 6, 1, 5 };
        List<List<Integer>> result = this.algorithController41to60.combinationSum2(test, 8);
        System.out.println(result);
    }

    @Test
    public void firstMissingPositive() {
        int[] test = new int[] { 1, 2 };
        int result = this.algorithController41to60.firstMissingPositive(test);
        System.out.println(result);
    }

    @Test
    public void spiralOrder() {
        int[][] test = new int[3][3];
        test[0] = new int[] { 1, 2, 3 };
        test[1] = new int[] { 4, 5, 6 };
        test[2] = new int[] { 7, 8, 9 };
        List res1 = new ArrayList<Integer>();
        Collections.addAll(res1, 1, 2, 3, 6, 9, 8, 7, 4, 5);
        Assert.assertEquals(algorithController41to60.spiralOrder(test), res1);

        int[][] test2 = new int[3][4];
        test2[0] = new int[] { 1, 2, 3, 4 };
        test2[1] = new int[] { 5, 6, 7, 8 };
        test2[2] = new int[] { 9, 10, 11, 12 };
        List res2 = new ArrayList<Integer>();
        Collections.addAll(res2, 1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
        Assert.assertEquals(algorithController41to60.spiralOrder(test2), res2);

        int[][] test3 = new int[1][2];
        test3[0] = new int[] { 2, 3 };
        List res3 = new ArrayList<Integer>();
        Collections.addAll(res3, 2, 3);
        Assert.assertEquals(algorithController41to60.spiralOrder(test3), res3);

    }

}
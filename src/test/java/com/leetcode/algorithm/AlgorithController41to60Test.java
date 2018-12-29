package com.leetcode.algorithm;

import com.leetcode.tool.Print;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void combinationSum() {
        int[] test = new int[]{10,1,2,7,6,1,5};
        List<List<Integer>> result = this.algorithController41to60.combinationSum2(test, 8);
        System.out.println(result);
    }

    @Test
    public void firstMissingPositive() {
        int[] test = new int[]{1,2};
        int result = this.algorithController41to60.firstMissingPositive(test);
        System.out.println(result);
    }

    @Test
    public void trap() {
        int[] test= new int[]{4233,42,5,436,6,57,7,546,452,3242,12};
        int result = this.algorithController41to60.trap(test);
        System.out.println(result);
    }

    @Test
    public void multiply() {
        System.out.println(this.algorithController41to60.multiply("123456789","987654321"));
    }

    @Test
    public void isMatch(){
        String sKey = "s";
        String pKey = "p";
        String valueKey = "value";
        List<HashMap> list = new ArrayList<>();
        HashMap<String, String> test1 = new HashMap<>();
        test1.put(sKey, "aa");
        test1.put(pKey, "a");
        test1.put(valueKey, "false");
        list.add(test1);
        HashMap<String, String> test2 = new HashMap<>();
        test2.put(sKey, "aa");
        test2.put(pKey, "*");
        test2.put(valueKey, "true");
        list.add(test2);
        HashMap<String, String> test3 = new HashMap<>();
        test3.put(sKey, "cb");
        test3.put(pKey, "?a");
        test3.put(valueKey, "false");
        list.add(test3);
        HashMap<String, String> test4 = new HashMap<>();
        test4.put(sKey, "adceb");
        test4.put(pKey, "*a*b");
        test4.put(valueKey, "true");
        list.add(test4);
        HashMap<String, String> test5 = new HashMap<>();
        test5.put(sKey, "acdcb");
        test5.put(pKey, "a*c?b");
        test5.put(valueKey, "false");
        list.add(test5);
        HashMap<String, String> test6 = new HashMap<>();
        test6.put(sKey, "");
        test6.put(pKey, "*");
        test6.put(valueKey, "true");
        list.add(test6);
        HashMap<String, String> test7 = new HashMap<>();
        test7.put(sKey, "");
        test7.put(pKey, "a");
        test7.put(valueKey, "false");
        list.add(test7);
        HashMap<String, String> test8 = new HashMap<>();
        test8.put(sKey, "aa");
        test8.put(pKey, "a*");
        test8.put(valueKey, "true");
        list.add(test8);
        HashMap<String, String> test9 = new HashMap<>();
        test9.put(sKey, "a");
        test9.put(pKey, "a*");
        test9.put(valueKey, "true");
        list.add(test9);
        for (HashMap<String, String> map : list) {
            boolean result = this.algorithController41to60.isMatch(map.get(sKey), map.get(pKey));
            if (Boolean.toString(result).equals(map.get(valueKey))) {
                System.out.println("验证成功");
            } else {
                System.out.println("验证失败了,s:" + map.get(sKey) + ",p:" + map.get(pKey) + ",期望值:" + map.get(valueKey) + "当前值:" + Boolean.toString(result));
            }
        }
    }

}
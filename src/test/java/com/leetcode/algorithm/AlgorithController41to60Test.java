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
        int[] test1 = new int[]{1,2,3,4,8};
        int[] test2 = new int[]{1,2,3,4,8};
        int[] test3 = new int[]{1,2,3,4,8};
        int[] test4 = new int[]{1,2,3,4,8};
        int[][] test = new int[][]{test1,test2,test3,test4};

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
        HashMap<String, String> test10 = new HashMap<>();
        test10.put(sKey, "aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba");
        test10.put(pKey, "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*");
        test10.put(valueKey, "true");
        list.add(test10);
        for (HashMap<String, String> map : list) {
            boolean result = this.algorithController41to60.isMatch(map.get(sKey), map.get(pKey));
            if (Boolean.toString(result).equals(map.get(valueKey))) {
                System.out.println("验证成功");
            } else {
                System.out.println("验证失败了,s:" + map.get(sKey) + ",p:" + map.get(pKey) + ",期望值:" + map.get(valueKey) + "当前值:" + Boolean.toString(result));
            }
        }
    }

    @Test
    public void jump() {
        String valueKey = "value";
        String resultKey = "result";
        ArrayList<HashMap<String, String>> testList = new ArrayList<>();
        HashMap<String, String> test1 = new HashMap<>();
        test1.put(valueKey, "2,3,1,1,4");
        test1.put(resultKey, "2");
        testList.add(test1);
        HashMap<String, String> test2 = new HashMap<>();
        test2.put(valueKey, "3,2,1,1,1,2,3,4,2,5,6,1241,3,4,5,6,7,8,4,24,5,6442,24,5,12");
        test2.put(resultKey, "6");
        testList.add(test2);
        HashMap<String, String> test3 = new HashMap<>();
        test3.put(valueKey, "3,2,1,1,1,2,3,4,2,5,6,5,3,4,5,6,7,8,4,24,5,6442,24,5,12");
        test3.put(resultKey, "8");
        testList.add(test3);
        HashMap<String, String> test4 = new HashMap<>();
        test4.put(valueKey, ",");
        test4.put(resultKey, "0");
        testList.add(test4);
        HashMap<String, String> test5 = new HashMap<>();
        test5.put(valueKey, "0,0,0,0,0,0,0,0,0,1");
        test5.put(resultKey, "9");
        testList.add(test5);
        for(HashMap<String, String> map: testList){
            String[] input = map.get(valueKey).split(",");
            int[] intInput = new int[input.length];
            for (int y = 0;y < input.length;y++) {
                intInput[y] = Integer.parseInt(input[y]);
            }
            String result = Integer.toString(this.algorithController41to60.jump(intInput));
            if (result.equals(map.get(resultKey))) {
                System.out.println("验证成功");
            } else {
                System.out.println("验证失败:输入值：" + Print.printArray(intInput) + "输出值:" + result + "期望值：" + map.get(resultKey));
            }
        }
    }

    @Test
    public void permute() {
        List<List<Integer>> result = this.algorithController41to60.permute(new int[]{1,2,3});
        System.out.println(result);
    }

    @Test
    public void permuteUnique() {
        List<List<Integer>> result = this.algorithController41to60.permuteUnique(new int[]{1,1,2});
        System.out.println(result);
    }

    @Test
    public void groupAnagrams() {
        String[] test = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(this.algorithController41to60.groupAnagrams(test));
    }

    @Test
    public void test() {
        System.out.println(this.algorithController41to60.solveNQueens(4));
    }

}
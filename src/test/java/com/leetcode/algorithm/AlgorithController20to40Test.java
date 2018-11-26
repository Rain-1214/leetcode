package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;
import com.leetcode.tool.Print;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithController20to40Test {

    private AlgorithController20to40 algorithController20to40 = new AlgorithController20to40();

    @Test
    public void mergeTwoLists() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = this.algorithController20to40.mergeTwoLists(l1, l2);
        System.out.print(l3);
    }

    @Test
    public void removeElement() {
        int[] test = new int[]{4,5};
        int a = this.algorithController20to40.removeElement(test, 4);
        System.out.print(a);
    }

    @Test
    public void strStr() {
        String hello = "hellow";
        String needle = "ll";
        System.out.print(this.algorithController20to40.strStr(hello, needle));
    }

    @Test
    public void mergeKLists() {
        ListNode[] lists = new ListNode[3];
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        lists[0] = l1;
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        lists[1] = l2;
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        lists[2] = l3;
        ListNode[] lists1 = new ListNode[1];
        lists1[0] = new ListNode();
        ListNode result = this.algorithController20to40.mergeKLists(lists1);
        System.out.print(result);
    }

    @Test
    public void divide() {
        int result = this.algorithController20to40.divide(87, 4);
        System.out.print(result);
    }

    @Test
    public void findSubstring() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word","good","best","good"};
        List<Integer> result = this.algorithController20to40.findSubstring(s, words);
        for(Integer str: result) {
            System.out.print(str);
            System.out.print(",");
        }
    }

    @Test
    public void nextPermutation() {
        int[] nums = new int[]{5,1,1};
        this.algorithController20to40.nextPermutation(nums);
        for(int num:nums) {
            System.out.print(num);
            System.out.print(",");
        }
    }

    @Test
    public void longestValidParentheses() throws Exception {
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("(()", 2);
        testMap.put("(()())", 6);
        testMap.put("())))", 2);
        testMap.put("((())()", 6);
        testMap.put(")))(())((", 4);
        for (Map.Entry<String, Integer> mapEntry: testMap.entrySet()) {
            int result = this.algorithController20to40.longestValidParentheses(mapEntry.getKey());
            if (result != mapEntry.getValue()) {
                throw new Exception("当前值:" + mapEntry.getKey() + ";计算值:" + result + ";预期值:" + mapEntry.getValue());
            }
        }
        System.out.println("测试完成");
    }

    @Test
    public void search() {
        int result = this.algorithController20to40.search(new int[]{4,5,6,7,0,1,2}, 5);
        System.out.println(result);
    }

    @Test
    public void searchRange(){
        int result = this.algorithController20to40.leftDichotomy(new int[]{0,1,1,2,3,5,5,5,8,9}, 0, 9,5);
        System.out.println(result);
    }

    @Test
    public void isValidSudoku() {
//        [".","1",".","5","2",".",".",".","."],
//        [".",".",".",".",".","6","4","3","."],
//        [".",".",".",".",".",".",".",".","."],
//        ["5",".",".",".",".",".","9",".","."],
//        [".",".",".",".",".",".",".","5","."],
//        [".",".",".","5",".",".",".",".","."],
//        ["9",".",".",".",".","3",".",".","."],
//        [".",".","6",".",".",".",".",".","."],
//        [".",".",".",".",".",".",".",".","."]
        char[] params  = new char[]{'.','1','.','5','2','.','.','.','.'};
        char[] params1 = new char[]{'.','.','.','.','.','6','4','3','.'};
        char[] params2 = new char[]{'.','.','.','.','.','.','.','.','.'};
        char[] params3 = new char[]{'5','.','.','.','.','.','9','.','.'};
        char[] params4 = new char[]{'.','.','.','.','.','.','.','5','.'};
        char[] params5 = new char[]{'.','.','.','5','.','.','.','.','.'};
        char[] params6 = new char[]{'9','.','.','.','.','3','.','.','.'};
        char[] params7 = new char[]{'.','.','6','.','.','.','.','.','.'};
        char[] params8 = new char[]{'.','.','.','.','.','.','.','.','.'};
        char[][] test = new char[][]{params, params1, params2, params3, params4, params5, params6, params7, params8};
        boolean result = this.algorithController20to40.isValidSudoku(test);
        System.out.println(result);
    }

    @Test
    public void solveSudoku() {
        char[] params  = new char[]{'5','3','.','.','7','.','.','.','.'};
        char[] params1 = new char[]{'6','.','.','1','9','5','.','.','.'};
        char[] params2 = new char[]{'.','9','8','.','.','.','.','6','.'};
        char[] params3 = new char[]{'8','.','.','.','6','.','.','.','3'};
        char[] params4 = new char[]{'4','.','.','8','.','3','.','.','1'};
        char[] params5 = new char[]{'7','.','.','.','2','.','.','.','6'};
        char[] params6 = new char[]{'.','6','.','.','.','.','2','8','.'};
        char[] params7 = new char[]{'.','.','.','4','1','9','.','.','5'};
        char[] params8 = new char[]{'.','.','.','.','8','.','.','7','9'};
        char[][] test = new char[][]{params, params1, params2, params3, params4, params5, params6, params7, params8};
        this.algorithController20to40.solveSudoku(test);
        Print.print2DCharArray(test);
//        boolean result = this.algorithController20to40.checkBoardValid(test,4,8);
//        System.out.println(result);
    }

    @Test
    public void countAndSay() {
        System.out.println(this.algorithController20to40.countAndSay(4));
    }

}



























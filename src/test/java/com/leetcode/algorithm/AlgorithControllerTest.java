package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.Tool;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AlgorithControllerTest {

    private AlgorithController algorithController;
    private Tool tool;

    @Before
    public void beforeTest () {
        this.tool = new Tool();
        this.algorithController = new AlgorithController();
    }

    @Test
    public void isMatch() {
        String s = "mississippi";
        String p = "mis*is*ip*.";
        String s1 = "aaa";
        String p1 = "ab*a*c*a";
        String s2 = "a";
        String p2 = "ab*";
        Map<String, String> testData = new HashMap<>();
        testData.put(s, p);
        testData.put(s1, p1);
        testData.put(s2, p2);
        testData.put("aab", "c*a*b");
        testData.put("a", "ab*a");
        for (Map.Entry<String, String> entry : testData.entrySet()) {
            boolean result = this.algorithController.isMatch(entry.getKey(), entry.getValue());
            System.out.print("key 是" + entry.getKey() + ",value 是" + entry.getValue() + ",测试结果：" + result);
            System.out.print("\n");
        }
    }

    @Test
    public void maxArea() {
        int[] heights = new int[15001];
        for (int i = 1; i <= 15000; i++) {
            heights[i] = i;
        }
        System.out.print(this.algorithController.maxArea(heights));
    }

    @Test
    public void intToRoman() {
        System.out.print(this.algorithController.intToRoman(1994));
    }

    @Test
    public void addTwoNumbers() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        System.out.print(this.algorithController.addTwoNumbers(l1, l2));
    }

    @Test
    public void romanToInt() {
        System.out.print(this.algorithController.romanToInt("MCMXCIV"));
    }

    @Test
    public void longestCommonPrefix() {
        System.out.print(this.algorithController.longestCommonPrefix(new String[] {"aa","a"}));
    }

    @Test
    public void sort() {
        int[] a = new int[] {9, 5,98,745,13,1,568,489,643,21};
        this.tool.sort(a);
        for (int i : a) {
            System.out.print(i);
            System.out.print(",");
        }
    }

    @Test
    public void threeSum() {
        List list = this.algorithController.threeSum(new int[] {-4,-1,-1, 0,1,2,});
        System.out.print(list);
    }

    @Test
    public void threeSumClosest() {
        int result = this.algorithController.threeSumClosest(new int[] {1,2,5,10,11}, 12);
        System.out.print(result);
    }

    @Test
    public void reserve() {
        ListNode listNode = new ListNode(1);
        ListNode a = new ListNode(2);
        listNode.next = a;
        a.next = new ListNode(3);
        ListNode result = this.algorithController.reverse1(listNode);
        System.out.print(result);
    }

    @Test
    public void test1() {
        ListNode a = new ListNode(1);
        ListNode b = a;
        ListNode c = a;
        b = null;
        System.out.print(c);
    }

    @Test
    public void letterCombinations() {
        List<String> test = this.algorithController.letterCombinations("23");
        for (String str: test) {
            System.out.print(str);
            System.out.print(",");
        }
    }

    @Test
    public void fourSum() {
        List list = this.algorithController.fourSum(new int[] {0,0,0,0}, 0);
        System.out.print(list);
    }

    @Test
    public void removeNthFromEnd() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        ListNode list = this.algorithController.removeNthFromEnd(l1, 5);
        System.out.print(list);
    }

    @Test
    public void isValid() {
        System.out.print(this.algorithController.isValid("(("));
    }

}










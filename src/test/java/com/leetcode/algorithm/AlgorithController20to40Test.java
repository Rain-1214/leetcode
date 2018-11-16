package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;
import org.junit.Test;

import java.util.List;

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
    public void longestValidParentheses() {
        int[] test = new int[2];
        System.out.print(test[1]);
    }

}



























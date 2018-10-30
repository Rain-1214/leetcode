package com.leetcode.entity;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode (int val) {
        this.val = val;
    }

    public ListNode () {}

    private int deep = 0;

    @Override
    public String toString() {
        String result = "";
        ListNode l1 = this;
        this.deep = 0;
        while (l1 != null) {
            deep++;
            result += l1.val + ",";
            l1 = l1.next;
            if (deep >= 100) {
                break;
            }
        }
        return result;
    }
}

package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.Tool;

public class AlgorithController20to40 {

    private Tool tool = new Tool();

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode result = new ListNode(Math.min(l1.val, l2.val));
        ListNode tempResult = result;
        ListNode temp1 = l1.val > l2.val ? l1 : l1.next;
        ListNode temp2 = l2.val >= l1.val ? l2 : l2.next;
        while (temp1 != null || temp2 != null) {
            if (temp1 != null && temp2 != null) {
                if (temp1.val > temp2.val) {
                    tempResult.next = new ListNode(temp2.val);
                    temp2 = temp2.next;
                } else {
                    tempResult.next = new ListNode(temp1.val);
                    temp1 = temp1.next;
                }
                tempResult = tempResult.next;
            } else {
                tempResult.next = temp1 == null ? temp2 : temp1;
                break;
            }
        }
        return result;
    }

    public int removeElement(int[] nums, int val) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            for (int y: nums) {
                System.out.print(y);
            }
            System.out.print("\n");
            if (nums[i] == val) {
                if (i == nums.length - 1) {
                    return i;
                } else {
                    int targetIndex = i + 1;
                    while (nums[targetIndex] == val) {
                        targetIndex++;
                        if (targetIndex == nums.length) {
                            return i;
                        }
                    }
                    int temp = nums[targetIndex];
                    nums[targetIndex] = nums[i];
                    nums[i] = temp;
                    result--;
                }
            }
        }
        return result;
    }
}

package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public int searchInsert(int[] nums, int target) {
        for (int i = 0;i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        addParentheses(result, "", 0, 0, n);
        return result;
    }

    public void addParentheses (List<String> result, String currentStr, int leftNum, int rightNum, int maxNum) {
        if (currentStr.length() >= maxNum *2) {
            result.add(currentStr);
            return;
        }

        if (leftNum < maxNum) {
            addParentheses(result, currentStr + "(", leftNum++, rightNum, maxNum);
        }
        if (leftNum > rightNum) {
            addParentheses(result, currentStr + ")", leftNum, rightNum++, maxNum);
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode result = new ListNode();
        ListNode tempRef = result;
        boolean first = true;
        int complete = 0;
        while (complete < lists.length) {
            int currentMinNumIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                ListNode currentListNode = lists[i];
                System.out.print("currentListNode:");
                System.out.print(currentListNode);
                System.out.print("\n");
                if (currentListNode == null) {
                    if (first) {
                        complete++;
                    }
                    continue;
                }
                if (currentMinNumIndex == -1) {
                    currentMinNumIndex = i;
                    continue;
                }
                if (currentListNode.val < lists[currentMinNumIndex].val) {
                    currentMinNumIndex = i;
                }
            }
            if (currentMinNumIndex == -1) {
                return null;
            }
            first = false;
            tempRef.val = lists[currentMinNumIndex].val;
            lists[currentMinNumIndex] = lists[currentMinNumIndex].next;
            if (lists[currentMinNumIndex] == null) {
                complete++;
            }
            if (complete != lists.length) {
                tempRef.next = new ListNode();
                tempRef = tempRef.next;
            }
            System.out.print("currentMinNumIndex");
            System.out.print(currentMinNumIndex);
            System.out.print("\n");
            System.out.print("result");
            System.out.print(result);
            System.out.print("\n");
            System.out.print("complete");
            System.out.print(complete);
            System.out.print("\n");
        }
        return result;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode currentList = head;
        ListNode nextList = head.next;
        while (currentList != null && nextList != null) {
            int tempValue = currentList.val;
            currentList.val = nextList.val;
            nextList.val = tempValue;
            currentList = nextList.next == null ? null : nextList.next;
            nextList = currentList != null && currentList.next != null ? currentList.next : null;
        }
        return head;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tempHead = head;
        ListNode modifyHead = head;
        int[] valueArray = new int[k];
        while (tempHead != null) {
            int i;
            for (i = 0; i < k; i++) {
                if (tempHead == null) {
                    break;
                }
                valueArray[i] = tempHead.val;
                tempHead = tempHead.next;
            }
            if (i != k) {
                break;
            }
            for (int y = valueArray.length - 1; y >= 0; y--) {
                modifyHead.val = valueArray[y];
                modifyHead = modifyHead.next;
            }
        }
        return head;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        long tempDividend = Math.abs((long) dividend);
        long tempDivisor = Math.abs((long) divisor);
        if (tempDivisor > tempDividend) {
            return 0;
        }
        int digits = 0;
        while (tempDividend >= tempDivisor) {
            tempDivisor <<= 1;
            digits++;
        }
        int result = 0;
        tempDivisor >>= digits;
        digits--;
        while (digits >= 0) {
            if (tempDividend >= (tempDivisor << digits)) {
                tempDividend -= tempDivisor << digits;
                result += 1 << digits;
            }
            digits--;
        }
        return ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) ? result : 0 - result;
    }

//    public int divide(int dividend, int divisor) {
//        if (dividend == Integer.MIN_VALUE && (divisor == 1 || divisor == -1)) {
//            return divisor == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
//        }
//        return (int)divideLong(dividend, divisor);
//    }
//
//    public long divideLong(long dd, long dv){
//        boolean isPos = (dd > 0 && dv > 0) || (dd < 0 && dv < 0);
//        dd = Math.abs(dd);
//        dv = Math.abs(dv);
//        int digits = 0;// 通过将除数乘2,乘4,乘8,一直乘下去,找到商的最高的次方// 比如87/4=21,商的最高次方是4,即2^4=16,即4 * 16 < 87
//        while(dv <= dd){
//            dv <<= 1;
//            digits++;
//        }
//        System.out.println(digits);
//        System.out.println("-----");
//        // 重置除数,把最高次方减1得到实际最高位,刚才循环中多加了一次
//        long res = 0;
//        dv >>= digits;
//        digits--;// 看商的每一位是否应该为1
//        while(digits >= 0){
//            System.out.println("-----");
//            System.out.println(res);
//            System.out.println(digits);
//            System.out.println(dd);
//            System.out.println(dv);
//            if(dd >= (dv << digits)){
//                dd -= dv << digits;
//                res += 1 << digits;
//            }
//            digits--;
//            System.out.println("++++++");
//            System.out.println(res);
//            System.out.println(digits);
//            System.out.println(dd);
//            System.out.println(dv);
//            System.out.println("-----");
//        }
//        return isPos ? res : - res;
//    }

}

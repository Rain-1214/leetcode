package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.Tool;
import com.leetcode.tool.Print;

import java.io.*;
import java.util.*;

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

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words.length == 0) {
            return result;
        }
        int windowWidth = words[0].length();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() != windowWidth) {
                return result;
            }
        }
        String[] strings = words.clone();
        for (int y = 0; y <= s.length() - windowWidth * words.length; y++) {
            String currentStr = s.substring(y, y + windowWidth);
            System.out.println(currentStr);
            if (arrayIncludes(strings, currentStr)) {
                int currentIndex = y;
                int matching = 1;
                while (matching < words.length) {
                    currentIndex += windowWidth;
                    String tempStr = s.substring(currentIndex, currentIndex + windowWidth);
                    System.out.println(tempStr);
                    if (arrayIncludes(strings, tempStr)) {
                        matching++;
                    } else {
                        break;
                    }
                }
                System.out.println("********");
                if (matching == words.length) {
                    result.add(y);
                }
                strings = words.clone();
            }
        }
        return result;
    }

    public Boolean arrayIncludes(String[] strings, String target) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(target)) {
                strings[i] = "";
                return true;
            }
        }
        return false;
    }


    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        int currentIndex = nums.length - 1;
        while (currentIndex >= 1 && nums[currentIndex] <= nums[currentIndex - 1]) {
            currentIndex--;
        }
        if (currentIndex == 0) {
            reserve(nums, 0, nums.length - 1);
            return;
        } else {
            System.out.print(currentIndex);
            int y = nums.length - 1;
            while (y > currentIndex - 1) {
                if (nums[y] > nums[currentIndex - 1]) {
                    break;
                }
                y--;
            }
            int temp = nums[y];
            nums[y] = nums[currentIndex - 1];
            nums[currentIndex - 1] = temp;
            reserve(nums, currentIndex, nums.length - 1);
        }
    }

    public void reserve(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        for (int i = start; i <= (start + end) / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[end + start - i];
            nums[end + start - i] = temp;
        }
    }

    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n < 2) {
            return 0;
        }
        List<ArrayList<Integer>> parenthesesIndexs = new ArrayList<>();
        int leftParenthesesNum = 0;
        int rightParenthesesNum = 1;
        char leftParentheses = '(';
        char rightParentheses = ')';
        for (int i = 0;i < n;i++) {
            if (s.charAt(i) == leftParentheses) {
                ArrayList<Integer> tempLeftList = new ArrayList<>();
                tempLeftList.add(leftParenthesesNum);
                tempLeftList.add(i);
                parenthesesIndexs.add(tempLeftList);
            }
            if (s.charAt(i) == rightParentheses && parenthesesIndexs.size() > 0) {
                ArrayList<Integer> tempList = parenthesesIndexs.get(parenthesesIndexs.size() - 1);
                if (tempList.get(0) == leftParenthesesNum) {
                    parenthesesIndexs.remove(parenthesesIndexs.size() - 1);
                } else {
                    ArrayList<Integer> tempRightList = new ArrayList<>();
                    tempRightList.add(rightParenthesesNum);
                    tempRightList.add(i);
                    parenthesesIndexs.add(tempRightList);
                }
                // parenthesesIndexs.remove(parenthesesIndexs.size() - 1);
            } else if (s.charAt(i) == rightParentheses && parenthesesIndexs.size() == 0) {
                ArrayList<Integer> tempRightList = new ArrayList<>();
                tempRightList.add(rightParenthesesNum);
                tempRightList.add(i);
                parenthesesIndexs.add(tempRightList);
            }
        }
        if (parenthesesIndexs.size() == 0) {
            return n;
        }
        int maxLength = 0;
        int tempIndex = n;
        for (int y = parenthesesIndexs.size() - 1; y >= 0; y--) {
            int index = parenthesesIndexs.get(y).get(1);
            maxLength = tempIndex - index - 1 > maxLength ? tempIndex - index - 1 : maxLength;
            tempIndex = index;
        }
        maxLength = tempIndex > maxLength ? tempIndex : maxLength;
        return maxLength;
    }

    public int search(int[] nums, int target) {
        return customDichotomy(nums, 0, nums.length - 1, target);
    }

    public int customDichotomy(int[] nums, int startIndex, int endIndex, int target) {
        System.out.println("startIndex:" + startIndex + "; endIndex:" + endIndex);
        if (startIndex > endIndex) {
            return -1;
        }
        int centerIndex = startIndex + (endIndex - startIndex) / 2;
        if (nums[centerIndex] == target) {
            return centerIndex;
        }
        if (nums[endIndex] > nums[centerIndex]) {
            if (target > nums[centerIndex] && target <= nums[endIndex]) {
                return customDichotomy(nums, centerIndex + 1, endIndex, target);
            } else {
                return customDichotomy(nums, startIndex, centerIndex - 1, target);
            }
        } else {
            if (target >= nums[startIndex] && target < nums[centerIndex]) {
                return customDichotomy(nums, startIndex, centerIndex - 1, target);
            } else {
                return customDichotomy(nums, centerIndex + 1, endIndex, target);
            }
        }
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1,-1};
        }
        int leftIndex = leftDichotomy(nums, 0, nums.length - 1, target);
        if (leftIndex == -1) {
            return new int[]{-1,-1};
        } else {
            for (int i = leftIndex; i < nums.length; i++) {
                if (nums[i] != target) {
                    return new int[]{leftIndex, i - 1};
                }
            }
            return new int[]{leftIndex, nums.length - 1};
        }
    }

    public int leftDichotomy(int[] nums, int start, int end, int target) {
        if (start >= end) {
            return nums[start] == target ? start : -1;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] >= target) {
            return leftDichotomy(nums, start, mid, target);
        } else {
            return leftDichotomy(nums, mid + 1, end, target);
        }
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!checkRepeatChar(board[i])) {
                return false;
            }
        }
        int currnetColumn = 0;
        HashSet<Character> tempSet = new HashSet<>();
        for (int i = 0; i <= 9; i++) {
            if (i == 9) {
                tempSet.clear();
                i = -1;
                currnetColumn++;
                if (currnetColumn == 9) {
                    break;
                }
                continue;
            }
            if (board[i][currnetColumn] != '.') {
                if (tempSet.contains(board[i][currnetColumn])) {
                    return false;
                } else {
                    tempSet.add(board[i][currnetColumn]);
                }
            }
        }
        tempSet.clear();
        for (int i = 0; i < 9; i++) {
            int x = i / 3 * 3;
            int y = (i % 3) * 3;
            int xMax = x + 3;
            int yMax = y + 3;
            int tempY = y;
            for (; x < xMax; x++) {
                y = tempY;
                for (; y < yMax; y++) {
                    if (board[x][y] != '.') {
                        if (tempSet.contains(board[x][y])) {
                            return false;
                        } else {
                            tempSet.add(board[x][y]);
                        }
                    }
                }
            }
            tempSet.clear();
        }
        return true;
    }

    public boolean checkRepeatChar (char[] charArray) {
        HashSet<Character> tempHashSet = new HashSet<>();
        for (char tempChar: charArray) {
            if (tempHashSet.contains(tempChar)) {
                return false;
            } else if (tempChar != '.'){
                tempHashSet.add(tempChar);
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        if (board.length == 0 || board == null) {
            return;
        }
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[col].length; row++) {
                if (board[col][row] != '.') {
                    continue;
                }
                for (char z = '1'; z <= '9'; z++) {
                    board[col][row] = z;
                    if (checkBoardValid(board, row, col)) {
                        if (solve(board)) {
                            return true;
                        } else {
                            board[col][row] = '.';
                        }
                    } else {
                        board[col][row] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean checkBoardValid (char[][] board, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (board[y][i] == board[y][x] && i != x) {
                return false;
            }
            if (board[i][x] == board[y][x] && i != y) {
                return false;
            }
            if (board[i / 3 + (y / 3) * 3][i % 3 + (x / 3) * 3] == board[y][x] && (i / 3 + (y / 3) * 3 != y) && (i % 3 + (x / 3) * 3 != x)) {
                return false;
            }
        }
        return true;
    }

    public String countAndSay(int n) {
        if (n <= 0) {
            return null;
        }
        if (n == 1) {
            return "1";
        }
        String currentStr = countAndSay(--n);
        StringBuffer sb = new StringBuffer();
        char currentChar = currentStr.charAt(0);
        int currentCharNum = 1;
        for (int i = 1; i < currentStr.length(); i++) {
            char tempChar = currentStr.charAt(i);
            if (currentChar == tempChar) {
                currentCharNum++;
            } else {
                sb.append(Integer.toString(currentCharNum) + currentChar);
                currentChar = tempChar;
                currentCharNum = 1;
            }
        }
        sb.append(Integer.toString(currentCharNum) + currentChar);
        return sb.toString();
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(result, new ArrayList<>(), 0, candidates, target);
        return result;
    }

    public void combinationSum(List<List<Integer>> result, ArrayList<Integer> list, int startIndex, int[] candidates, int target) {
        if (target > 0) {
            for (int i = startIndex; i < candidates.length; i++) {
                list.add(candidates[i]);
                combinationSum(result, list, i, candidates, target - candidates[i]);
                list.remove(list.size() - 1);
            }
        } else if (target == 0) {
            result.add(new ArrayList<>(list));
        }
    }

}



































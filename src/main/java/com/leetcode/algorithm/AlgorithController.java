package com.leetcode.algorithm;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.Tool;

import java.util.*;

public class AlgorithController {

    private Tool tool = new Tool();

    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (!s.isEmpty() && ((s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) && isMatch(s.substring(1), p));
        } else {
            return (!s.isEmpty() && (((s.charAt(0) == p.charAt(0)) || (p.charAt(0) == '.')))) && isMatch(s.substring(1), p.substring(1));
        }
    }

    public int maxArea(int[] height) {
        int maxArea = 0, rightRef = height.length - 1, leftRef = 0;
        while (leftRef < rightRef) {
            maxArea = Math.max(maxArea, Math.min(height[rightRef], height[leftRef]) * (rightRef - leftRef));
            if (height[rightRef] > height[leftRef]) {
                leftRef++;
            } else {
                rightRef--;
            }
        }
        return maxArea;
    }

    public String intToRoman(int num) {
        Map<Integer, Map<Integer, String>> map = new HashMap<>();
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "I");
        map1.put(5, "V");
        map.put(1, map1);
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "X");
        map2.put(5, "L");
        map.put(10, map2);
        Map<Integer, String> map3 = new HashMap<>();
        map3.put(1, "C");
        map3.put(5, "D");
        map.put(100, map3);
        Map<Integer, String> map4 = new HashMap<>();
        map4.put(1, "M");
        map.put(1000, map4);
        int currentRef = 1;
        StringBuffer result = new StringBuffer();
        while (num != 0) {
            int currentNum = num % 10;
            num /= 10;
            Map<Integer, String> tempMap = map.get(currentRef);
            if (currentNum < 4) {
                this.insertStrIntoStringBuffter(result, tempMap.get(1), currentNum);
            } else if (currentNum == 4) {
                result.insert(0, tempMap.get(5));
                result.insert(0, tempMap.get(1));
            } else if (currentNum == 5) {
                result.insert(0, tempMap.get(5));
            } else if (currentNum < 9) {
                this.insertStrIntoStringBuffter(result, tempMap.get(1), currentNum - 5);
                result.insert(0, tempMap.get(5));
            } else if (currentNum == 9) {
                result.insert(0, map.get(currentRef * 10).get(1));
                result.insert(0, tempMap.get(1));
            }
            currentRef *= 10;
        }
        return result.toString();
    }

    public void insertStrIntoStringBuffter(StringBuffer stringBuffer, String insertStr, int times) {
        if (times == 0) {
            return;
        }
        for (int i = times; i > 0; i--) {
            stringBuffer.insert(0, insertStr);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int tempNum = target - nums[i];
            if (map.containsKey(tempNum)) {
                result = new int[] {map.get(tempNum), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode result = null;
        ListNode currentList = null;
        while (l1 != null || l2 != null || carry != 0) {
            int currentVal = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            if (currentVal >= 10) {
                carry = 1;
                currentVal = currentVal % 10;
            } else {
                carry = 0;
            }
            if (result == null) {
                result = new ListNode(currentVal);
                currentList = result;
            } else {
                currentList.next = new ListNode(currentVal);
                currentList = currentList.next;
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        Set<Character> set = new HashSet<>();
        int startIndex = 0;
        for (int i = 0; i < s.length();) {
            Character character = s.charAt(i);
            if (set.contains(character)) {
                set.remove(s.charAt(startIndex++));
            } else {
                set.add(s.charAt(i++));
                maxLength = Math.max(maxLength, set.size());
            }
        }
        return maxLength;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int centerIndex = nums1.length / 2;
        while (centerIndex <= nums1.length && centerIndex >= 0) {
            int anotherCenterIndex = (nums1.length + nums2.length) / 2 - centerIndex;
            if (nums1[centerIndex - 1] > nums2[anotherCenterIndex]) {
                centerIndex--;
            } else if (nums2[anotherCenterIndex - 1] > nums1[centerIndex]) {
                centerIndex++;
            } else {
                int maxLeft = 0;
                if (centerIndex == 0) {
                    maxLeft = nums2[anotherCenterIndex - 1];
                } else if (anotherCenterIndex == 0) {
                    maxLeft = nums1[centerIndex - 1];
                } else {
                    maxLeft = Math.min(nums1[centerIndex - 1], nums2[anotherCenterIndex - 1]);
                }
                if ((nums1.length + nums2.length) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (centerIndex == nums1.length) {
                    minRight = nums2[anotherCenterIndex];
                } else if (anotherCenterIndex == nums2.length) {
                    minRight = nums1[centerIndex];
                } else {
                    minRight = Math.min(nums1[centerIndex], nums2[anotherCenterIndex]);
                }

                return (minRight + maxLeft) / 2.0;
            }
        }
        return 0.0;
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i != s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i+1))) {
                result += map.get(s.charAt(i+1)) - map.get(s.charAt(i));
                i++;
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int y = 0;
            while (y < Math.min(strs[i].length(), prefix.length())) {
                if (prefix.charAt(y) == strs[i].charAt(y)) {
                    y++;
                } else {
                    break;
                }
            }
            prefix = prefix.substring(0, y);
        }
        return prefix;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            List<Integer> list = new ArrayList<>();
            return new ArrayList(list);
        }
        List<List<Integer>> list = new ArrayList<>();
        this.tool.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int currentTarget = nums[i];
            if (currentTarget > 0) {
                break;
            }
            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            int minRef = i + 1;
            int maxRef = nums.length - 1;
            while (minRef < maxRef) {
                if (nums[i] + nums[minRef] + nums[maxRef] < 0) {
                    minRef++;
                } else if (nums[i] + nums[minRef] + nums[maxRef] > 0) {
                    maxRef--;
                } else {
                    if (minRef < i + 2 || nums[minRef] != nums[minRef - 1]) {
                        List tempList = new ArrayList<Integer>();
                        tempList.add(nums[i]);
                        tempList.add(nums[minRef]);
                        tempList.add(nums[maxRef]);
                        list.add(tempList);
                    }
                    minRef++;
                    maxRef--;
                }
            }
        }
        return list;
    }

    public int threeSumClosest(int[] nums, int target) {
        this.tool.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        int distance = Math.abs(target - result);
        for (int i = 0; i < nums.length - 2; i++) {
            int currentNum = nums[i];
            int y = i + 1;
            int x = nums.length - 1;
            while (y != x) {
                int currentSum = currentNum + nums[y] + nums[x];
                if (Math.abs(target - currentSum) < distance) {
                    result = currentSum;
                    distance = Math.abs(target - currentSum);
                }
                if (currentSum > target) {
                    x--;
                }
                if (currentSum < target) {
                    y++;
                }
                if (currentSum == target) {
                    return target;
                }
            }
        }
        return result;
    }

    public ListNode reverse(ListNode listNode) {
        ListNode afterList = new ListNode(listNode.val);
        ListNode ref = listNode.next;
        ListNode result = null;
        while (ref != null) {
            result = new ListNode(ref.val);
            result.next = afterList;
            afterList = result;
            ref = ref.next;
        }
        return result;
    }

    public ListNode reverse1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reHead = reverse1(head.next);
        System.out.print("head:");
        System.out.print(head);
        System.out.print("\n");
        System.out.print("rehead:");
        System.out.print(reHead);
        System.out.print("\n");
        head.next.next = head;
        System.out.print("head:");
        System.out.print(head);
        System.out.print("\n");
        System.out.print("rehead:");
        System.out.print(reHead);
        System.out.print("\n");
        head.next = null;
        System.out.print("head:");
        System.out.print(head);
        System.out.print("\n");
        System.out.print("rehead:");
        System.out.print(reHead);
        System.out.print("\n");
        System.out.print("-----------");
        System.out.print("\n");
        return reHead;
    }

    public List<String> letterCombinations(String digits) {
        Map map = new HashMap<Character, String[]>();
        map.put('2', new String[] {"a", "b", "c"});
        map.put('3', new String[] {"d", "e", "f"});
        map.put('4', new String[] {"g", "h", "i"});
        map.put('5', new String[] {"j", "k", "l"});
        map.put('6', new String[] {"m", "n", "o"});
        map.put('7', new String[] {"p", "q", "r", "s"});
        map.put('8', new String[] {"t", "u", "v"});
        map.put('9', new String[] {"w", "x", "y", "z"});
        List<String> result = new LinkedList<>();
        for (int i = 0; i < digits.length(); i++) {
            Character currentNum = digits.charAt(i);
            String[] currentStrArray = (String[]) map.get(currentNum);
            List<String> newResult = new LinkedList<>();
            for (int y = 0; y < currentStrArray.length; y++) {
                if (result.size() == 0) {
                    newResult.add(currentStrArray[y]);
                } else {
                    for (String str: result) {
                        newResult.add(str + currentStrArray[y]);
                    }
                }
            }
            result = newResult;
        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 4) {
            return new ArrayList(list);
        }
        this.tool.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int y = i + 1; y < nums.length - 2;y++) {
                if (y >= i + 2 && nums[y] == nums[y - 1]) {
                    continue;
                }
                int minRef = y + 1;
                int maxRef = nums.length - 1;
                while (minRef < maxRef) {
                    if (nums[i] + nums[y] + nums[minRef] + nums[maxRef] < target) {
                        minRef++;
                    } else if (nums[i] + nums[y] + nums[minRef] + nums[maxRef] > target) {
                        maxRef--;
                    } else {
                        if (minRef < y + 2 || nums[minRef] != nums[minRef - 1]) {
                            List tempList = new ArrayList<Integer>();
                            tempList.add(nums[i]);
                            tempList.add(nums[y]);
                            tempList.add(nums[minRef]);
                            tempList.add(nums[maxRef]);
                            list.add(tempList);
                        }
                        minRef++;
                        maxRef--;
                    }
                }
            }
        }
        return list;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tempList = head;
        if (tempList.next == null && n == 1) {
            return null;
        }
        int deep = 1;
        while (tempList.next != null) {
            deep++;
            tempList = tempList.next;
        }
        if (deep == n) {
            return head.next;
        }
        tempList = head;
        while (tempList.next != null) {
            if (deep == n + 1) {
                tempList.next = tempList.next.next;
                break;
            }
            deep--;
            tempList = tempList.next;
        }
        return head;
    }

    public boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        } else if (s.length() < 2) {
            return false;
        }
        List<Character> left = new ArrayList();
        Map<Character, Character> map = new HashMap();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
        for (int i = 0; i < s.length(); i++) {
            Character currentChar = s.charAt(i);
            if (map.containsKey(currentChar)) {
                left.add(currentChar);
            } else {
                int lastIndex = left.size();
                if (lastIndex == 0) {
                    return false;
                }
                Character prevLeft = left.get(lastIndex - 1);
                if (map.get(prevLeft) != currentChar) {
                    return false;
                }
                left.remove(lastIndex - 1);
            }
        }
        return left.size() == 0;
    }



}





























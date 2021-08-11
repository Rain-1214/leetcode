package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import com.leetcode.entity.ListNode;
import com.leetcode.entity.NestedInteger;
import com.leetcode.tool.Print;

public class AlgorithmController382to400 {

  class Solution382 {

    public Map<Integer, ListNode> map;
    public Random random;

    /**
     * @param head The linked list's head. Note that the head is guaranteed to be
     *             not null, so it contains at least one node.
     */
    public Solution382(ListNode head) {
      this.map = new HashMap<>();
      this.random = new Random();
      int index = 0;
      while (head != null) {
        map.put(index, head);
        head = head.next;
      }
    }

    /** Returns a random node's value. */
    public int getRandom() {
      int len = map.size();
      return this.map.get(this.random.nextInt(len)).val;
    }
  }

  class Solution382II {

    public List<ListNode> list = new ArrayList<>();
    public Random random = new Random();
    public int n;

    /**
     * @param head The linked list's head. Note that the head is guaranteed to be
     *             not null, so it contains at least one node.
     */
    public Solution382II(ListNode head) {
      while (head != null) {
        this.list.add(head);
        head = head.next;
      }
      this.n = this.list.size();
    }

    /** Returns a random node's value. */
    public int getRandom() {
      return this.list.get(this.random.nextInt(this.n)).val;
    }
  }

  public boolean canConstruct(String ransomNote, String magazine) {
    int[] chars = new int[26];
    for (char c : magazine.toCharArray()) {
      chars[c - 'a']++;
    }
    for (char c : ransomNote.toCharArray()) {
      int index = c - 'a';
      if (chars[index] == 0) {
        return false;
      } else {
        chars[index]--;
      }
    }
    return true;
  }

  class Solution384 {

    int[] nums;
    int[] origin;
    Random random;

    public Solution384(int[] nums) {
      this.random = new Random();
      this.nums = nums;
      this.origin = Arrays.copyOf(nums, nums.length);
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
      return this.origin;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
      for (int i = nums.length; i > 0; i--) {
        swap(nums, i - 1, random.nextInt(i));
      }
      return nums;
    }

    public void swap(int[] nums, int x, int y) {
      int temp = nums[x];
      nums[x] = nums[y];
      nums[y] = temp;
    }
  }

  public int deserializeIndex = 0;

  public NestedInteger deserialize(String s) {
    char[] ca = s.toCharArray();
    return deserialize(ca);
  }

  public NestedInteger deserialize(char[] ca) {
    NestedInteger res = new NestedInteger();
    if (ca[deserializeIndex] == '[') {
      deserializeIndex++;
      int val = 0;
      boolean negative = false, isEmpty = true;
      while (deserializeIndex < ca.length) {
        if (ca[deserializeIndex] == '[') {
          res.add(deserialize(ca));
          continue;
        }
        if (ca[deserializeIndex] == '-') {
          negative = true;
          deserializeIndex++;
          continue;
        }
        if (ca[deserializeIndex] == ',') {
          if (!isEmpty) {
            res.add(new NestedInteger(negative ? 0 - val : val));
            val = 0;
            isEmpty = true;
            negative = false;
          }
          deserializeIndex++;
          continue;
        }
        if (ca[deserializeIndex] == ']') {
          if (!isEmpty) {
            res.add(new NestedInteger(negative ? 0 - val : val));
            isEmpty = true;
            negative = false;
          }
          deserializeIndex++;
          break;
        }
        if (isEmpty) {
          isEmpty = false;
        }
        val = val * 10 + (ca[deserializeIndex++] - '0');
      }
      return res;
    }
    int val = 0;
    boolean negative = false;
    while (deserializeIndex < ca.length) {
      if (ca[deserializeIndex] == '-') {
        negative = true;
        deserializeIndex++;
        continue;
      }
      val = val * 10 + (ca[deserializeIndex++] - '0');
    }
    res.setInteger(negative ? 0 - val : val);
    return res;
  }

  public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
      if (i <= n) {
        res.add(i);
        lexicalOrderHelper(n, i, res);
      }
    }
    return res;
  }

  public void lexicalOrderHelper(int n, int start, List<Integer> res) {
    if (start > n) {
      return;
    }
    for (int i = 0; i < 10; i++) {
      int val = start * 10 + i;
      if (val <= n) {
        res.add(val);
        lexicalOrderHelper(n, val, res);
      }
    }
  }

  public int firstUniqChar(String s) {
    char[] sa = s.toCharArray();
    int[] nums = new int[26];
    for (char c : sa) {
      nums[c - 'a']++;
    }
    for (int i = 0; i < sa.length; i++) {
      if (nums[sa[i] - 'a'] == 1) {
        return i;
      }
    }
    return -1;
  }

  public int firstUniqCharII(String s) {
    int res = s.length() + 1;
    for (char i = 'a'; i <= 'z'; i++) {
      int leftIndex = s.indexOf(i);
      if (leftIndex != -1) {
        int rightIndex = s.lastIndexOf(i);
        if (leftIndex == rightIndex && leftIndex < res) {
          res = leftIndex;
        }
      }

    }
    return res == s.length() + 1 ? -1 : res;
  }

  public int lengthLongestPath(String input) {
    int max = 0, current = 0, index = 0;
    Stack<Integer> s = new Stack<Integer>();
    char[] sc = input.toCharArray();
    while (index < sc.length) {
      int currentLen = 0;
      if (sc[index] == '\n') {
        if (index + 1 < sc.length && sc[index + 1] != '\t' && !s.isEmpty()) {
          while (!s.isEmpty()) {
            int val = s.pop();
            current -= val;
          }
        }
        index++;
        continue;
      }

      if (sc[index] == '\t') {
        int tempLen = 0;
        while (index < sc.length && sc[index] == '\t') {
          tempLen++;
          index++;
        }

        if (tempLen < s.size()) {
          while (tempLen != s.size()) {
            int val = s.pop();
            current -= val;
          }
        }
      }
      boolean isFile = false;

      while (index < sc.length) {
        if (Character.isLetter(sc[index]) || Character.isDigit(sc[index]) || sc[index] == ' ') {
          currentLen++;
          index++;
        } else if (sc[index] == '.') {
          currentLen++;
          index++;
          isFile = true;
        } else {
          break;
        }
      }
      if (isFile) {
        max = Math.max(max, currentLen + current + s.size());
        isFile = false;
        continue;
      }
      s.add(currentLen);
      current += currentLen;
    }
    return max;
  }

  public int lengthLongestPathII(String input) {
    if (input.length() == 0) {
      return 0;
    }
    int res = 0;
    int[] levelCache = new int[input.length() + 1];
    for (String s : input.split("\n")) {
      int level = s.lastIndexOf('\t') + 2;
      int len = s.length() - level + 1;
      if (s.contains(".")) {
        res = Math.max(res, levelCache[level - 1] + len);
      } else {
        levelCache[level] = levelCache[level - 1] + len + 1;
      }
    }
    return res;
  }

  public char findTheDifference(String s, String t) {
    int[] nums = new int[26];
    for (char c : s.toCharArray()) {
      nums[c - 'a']++;
    }
    for (char c : t.toCharArray()) {
      nums[c - 'a']--;
    }
    for (int i = 0; i < 26; i++) {
      if (nums[i] == -1) {
        return (char) (i + 'a');
      }
    }
    return 'a';
  }

  public int lastRemaining(int n) {
    Stack<Integer> left = new Stack<>();
    Stack<Integer> right = new Stack<>();
    for (int i = n; i >= 1; i--) {
      left.add(i);
    }
    boolean remove = true;
    while (left.size() > 1) {
      int size = left.size();
      int temp = 0;
      boolean currentRemove = remove;
      while (temp < size) {
        if (currentRemove) {
          left.pop();
        } else {
          right.push(left.pop());
        }
        currentRemove = !currentRemove;
        temp++;
      }
      Stack<Integer> t = right;
      right = left;
      left = t;
    }
    return left.pop();
  }

  public int lastRemainingII(int n) {
    return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemainingII(n / 2));
  }

  public boolean isRectangleCover(int[][] rectangles) {
    int area = 0;
    int bottom = Integer.MAX_VALUE;
    int left = Integer.MAX_VALUE;
    int right = Integer.MIN_VALUE;
    int top = Integer.MIN_VALUE;
    Set<String> set = new HashSet<>();
    for (int[] reachable : rectangles) {
      area += (reachable[2] - reachable[0]) * (reachable[3] - reachable[1]);
      bottom = Math.min(bottom, reachable[1]);
      left = Math.min(left, reachable[0]);
      top = Math.max(top, reachable[3]);
      right = Math.max(right, reachable[2]);
      String[] points = { reachable[0] + " " + reachable[1], reachable[2] + " " + reachable[3],
          reachable[0] + " " + reachable[3], reachable[2] + " " + reachable[1] };
      for (String p : points) {
        if (set.contains(p)) {
          set.remove(p);
        } else {
          set.add(p);
        }
      }
    }
    String leftBottom = left + " " + bottom;
    String leftTop = left + " " + top;
    String rightBottom = right + " " + bottom;
    String rightTop = right + " " + top;
    if (set.size() != 4 || !set.contains(leftBottom) || !set.contains(leftTop) || !set.contains(rightBottom)
        || !set.contains(rightTop)) {
      return false;
    }
    return area == (right - left) * (top - bottom);
  }

  public boolean isSubsequence(String s, String t) {
    char[] sa = s.toCharArray();
    char[] ta = t.toCharArray();
    if (sa.length == 0) {
      return true;
    }
    int si = 0, ti = 0;
    while (ti < ta.length) {
      if (ta[ti] == sa[si]) {
        ti++;
        si++;
        if (si >= sa.length) {
          break;
        }
      } else {
        ti++;
      }
    }
    return si >= sa.length;
  }

  public boolean validUtf8(int[] data) {
    int base = 1 << 7, base6 = 1 << 6;
    for (int i = 0; i < data.length; i++) {
      int num = 0, temp = base, val = data[i];
      while ((val & temp) == temp && temp > 0) {
        num++;
        temp >>= 1;
      }
      if (num == 1 || num > 4) {
        return false;
      }
      if (num == 0) {
        continue;
      }
      num--;
      i++;
      while (num > 0) {
        if (i >= data.length) {
          return false;
        }
        int tempVal = data[i];
        if ((tempVal & base) != base || (tempVal & base6) != 0) {
          return false;
        }
        num--;
        if (num > 0) {
          i++;
        }
      }
    }
    return true;
  }

  public int decodeStringIndex = 0;

  public String decodeString(String s) {
    char[] sa = s.toCharArray();
    return decodeString(sa);
  }

  public String decodeString(char[] ca) {
    int time = 0;
    StringBuilder sb = new StringBuilder();
    while (decodeStringIndex < ca.length) {
      char c = ca[decodeStringIndex++];
      if (Character.isLetter(c)) {
        sb.append(c);
        continue;
      }
      if (Character.isDigit(c)) {
        time = time * 10 + (c - '0');
        continue;
      }
      if (c == '[') {
        String temp = decodeString(ca);
        for (int j = 0; j < time; j++) {
          sb.append(temp);
        }
        time = 0;
      }
      if (c == ']') {
        return sb.toString();
      }
    }
    return sb.toString();
  }

  public int longestSubstring(String s, int k) {
    return longestSubstringHelper(s.toCharArray(), 0, s.length() - 1, k);
  }

  public int longestSubstringHelper(char[] ca, int l, int r, int k) {
    int[] nums = new int[26];
    for (int i = l; i <= r; i++) {
      nums[ca[i] - 'a']++;
    }
    int split = -1;
    for (int i = 0; i < 26; i++) {
      if (nums[i] > 0 && nums[i] < k) {
        split = i;
        break;
      }
    }
    if (split == -1) {
      return r - l + 1;
    }

    int i = l;
    int res = 0;
    char splitChar = (char) (split + 'a');
    while (i <= r) {
      while (i <= r && ca[i] == splitChar) {
        i++;
      }
      if (i > r) {
        break;
      }
      int start = i;
      while (i <= r && ca[i] != splitChar) {
        i++;
      }
      int len = longestSubstringHelper(ca, start, i - 1, k);

      res = Math.max(res, len);

    }
    return res;
  }

  public int longestSubstringII(String s, int k) {
    int res = 0, len = s.length();
    char[] ca = s.toCharArray();
    int[] letterNum = new int[26];
    for (int num = 1; num < 26; num++) {
      Arrays.fill(letterNum, 0);
      int l = 0, total = 0, sum = 0;
      for (int r = 0; r < len; r++) {
        int index = ca[r] - 'a';
        letterNum[index]++;

        if (letterNum[index] == 1) {
          total++;
        }

        if (letterNum[index] == k) {
          sum++;
        }

        while (total > num) {
          letterNum[ca[l] - 'a']--;
          if (letterNum[ca[l] - 'a'] == 0) {
            total--;
          }
          if (letterNum[ca[l] - 'a'] == k - 1) {
            sum--;
          }
          l++;
        }
        if (total == sum) {
          res = Math.max(res, r - l + 1);
        }
      }
    }
    return res;
  }

  public int maxRotateFunction(int[] nums) {
    int n = nums.length;
    int sum = 0;
    int res = 0;
    for (int i = 0; i < n; i++) {
      sum += nums[i];
      res += i * nums[i];
    }
    int prevSum = res;
    for (int i = 1; i < n; i++) {
      int currentRes = prevSum + sum - n * nums[n - i];
      res = Math.max(res, currentRes);
      prevSum = currentRes;
    }
    return res;
  }

  public int integerReplacement(int n) {
    if (n == 2147483647) {
      return 32;
    }
    return integerReplacementHelper(n, 0);
  }

  public int integerReplacementHelper(int n, int deep) {
    if (n == 1) {
      return deep;
    }
    if (n % 2 == 0) {
      return integerReplacementHelper(n / 2, deep + 1);
    }
    return Math.min(integerReplacementHelper(n - 1, deep + 1), integerReplacementHelper(n + 1, deep + 1));
  }

  public int integerReplacementII(int n) {
    int res = 0;
    while (n != 1) {
      if ((n & 1) == 0) {
        n /= 2;
      } else {
        if ((n & 3) == 3 && n > 3) {
          n = n / 2 + 1;
          res++;
        } else {
          n -= 1;
        }
      }
      res++;
    }
    return res;
  }

  class Solution398 {

    public Map<Integer, ArrayList<Integer>> map;
    public Random random;

    public Solution398(int[] nums) {
      this.map = new HashMap<>();
      this.random = new Random();
      for (int i = 0; i < nums.length; i++) {
        ArrayList<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
        list.add(i);
        map.put(nums[i], list);
      }
    }

    public int pick(int target) {
      List<Integer> list = this.map.get(target);
      return list.get(this.random.nextInt(list.size()));
    }
  }

  class Solution398II {

    public int[] nums;
    public Random random;

    public Solution398II(int[] nums) {
      this.nums = nums;
      this.random = new Random();
    }

    public int pick(int target) {
      int n = 0, res = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] == target) {
          n++;
          int temp = random.nextInt(n);
          if (temp == 0) {
            res = i;
          }
        }
      }
      return res;
    }
  }
}

package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.leetcode.entity.TreeNode;

public class AlgorithmController521to540 {

  public int findLUSlength(String a, String b) {
    char[] ac = a.toCharArray();
    char[] bc = b.toCharArray();
    if (ac.length != bc.length) {
      return Math.max(ac.length, bc.length);
    }
    int i = 0;
    while (i < ac.length) {
      if (ac[i] != bc[i]) {
        return Math.max(ac.length, bc.length);
      }
      i++;
    }
    return -1;
  }

  public int findLUSlength522(String[] strs) {
    Arrays.sort(strs, (a, b) -> b.length() - a.length());
    for (int i = 0; i < strs.length; i++) {
      String source = strs[i];
      boolean flag = true;
      for (int j = 0; j < strs.length; j++) {
        if (i == j) {
          continue;
        }
        if (strs[j].length() < source.length()) {
          break;
        }
        if (isSubString(source, strs[j])) {
          flag = false;
          break;
        }
      }
      if (flag) {
        return source.length();
      }
    }
    return -1;
  }

  public boolean isSubString(String source, String target) {
    int si = 0;
    for (int ti = 0; ti < target.length() && si < source.length(); ti++) {
      if (source.charAt(si) == target.charAt(ti)) {
        si++;
      }
    }
    return si == source.length();
  }

  public boolean checkSubarraySum(int[] nums, int k) {
    int len = nums.length;
    if (len < 2) {
      return false;
    }
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int sum = 0;
    for (int i = 0; i < len; i++) {
      sum = sum + nums[i];
      int temp = sum % k;
      if (map.containsKey(temp)) {
        if (i - map.get(temp) > 1) {
          return true;
        }
      } else {
        map.put(temp, i);
      }
    }
    return false;
  }

  public String findLongestWord(String s, List<String> dictionary) {
    dictionary.sort((a, b) -> {
      if (a.length() == b.length()) {
        return a.compareTo(b);
      }
      return b.length() - a.length();
    });
    char[] sc = s.toCharArray();
    for (String word : dictionary) {
      int i = 0;
      for (int j = 0; j < sc.length; j++) {
        if (i < word.length() && sc[j] == word.charAt(i)) {
          i++;
        }
      }
      if (i == word.length()) {
        return word;
      }
    }
    return "";
  }

  public int findMaxLength(int[] nums) {
    if (nums.length < 2) {
      return 0;
    }
    int[] preZero = new int[nums.length];
    int[] preOne = new int[nums.length];
    int max = 0;
    preZero[0] = nums[0] == 0 ? 1 : 0;
    preOne[0] = nums[0] == 1 ? 1 : 0;
    for (int i = 1; i < nums.length; i++) {
      preZero[i] = nums[i] == 0 ? preZero[i - 1] + 1 : preZero[i - 1];
      preOne[i] = nums[i] == 1 ? preOne[i - 1] + 1 : preOne[i - 1];
    }
    for (int i = 1; i < nums.length; i++) {
      int current = nums[i];
      for (int j = 0; j < i; j++) {
        int zeros = preZero[i - 1] - preZero[j];
        int ones = preOne[i - 1] - preOne[j];
        if (j == 0) {
          zeros = preZero[i - 1];
          ones = preOne[i - 1];
        }
        zeros += current == 0 ? 1 : 0;
        ones += current == 1 ? 1 : 0;
        if (zeros == 0 || ones == 0) {
          break;
        }
        if (zeros + ones < max) {
          break;
        }
        if (zeros == ones) {
          max = Math.max(max, zeros + ones);
        }
      }
    }
    return max;
  }

  public int findMaxLengthII(int[] nums) {
    if (nums.length < 2) {
      return 0;
    }
    int max = 0, counter = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    for (int i = 0; i < nums.length; i++) {
      counter += nums[i] == 0 ? -1 : 1;
      if (map.containsKey(counter)) {
        max = Math.max(max, i - map.get(counter));
      } else {
        map.put(counter, i);
      }
    }
    return max;
  }

  int countArrangementRes = 0;
  List<Integer>[] countArrangementList;
  boolean[] countArrangementVisited;

  public int countArrangement(int n) {
    countArrangementList = new List[n + 1];
    countArrangementVisited = new boolean[n + 1];
    for (int i = 0; i <= n; i++) {
      countArrangementList[i] = new ArrayList<>();
    }
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (i % j == 0 || j % i == 0) {
          countArrangementList[i].add(j);
        }
      }
    }
    countArrangement(n, 1);
    return countArrangementRes;
  }

  public void countArrangement(int n, int index) {
    if (index == n + 1) {
      countArrangementRes++;
      return;
    }
    for (int num : countArrangementList[index]) {
      if (!countArrangementVisited[num]) {
        countArrangementVisited[num] = true;
        countArrangement(n, index + 1);
        countArrangementVisited[num] = false;
      }
    }
  }

  public List<String> wordsAbbreviation(List<String> words) {
    String[] sa = new String[words.size()];
    for (int i = 0; i < words.size(); i++) {
      sa[i] = words.get(i);
    }
    List<String> res = new ArrayList<>();
    for (int i = 0; i < words.size(); i++) {
      res.add(null);
    }
    int preLen = 0;
    Map<String, Integer> map = new HashMap<>();
    while (true) {
      boolean flag = true;
      for (int i = 0; i < sa.length; i++) {
        String s = sa[i];
        if (s == null) {
          continue;
        }
        flag = false;
        String abbr = minimalString(s, preLen);
        if (map.containsKey(abbr)) {
          if (sa[map.get(abbr)] == null) {
            int index = map.get(abbr);
            sa[index] = words.get(index);
          }
        } else {
          map.put(abbr, i);
          res.set(i, abbr);
          sa[i] = null;
        }
      }
      if (flag) {
        break;
      }
      preLen++;
      map.clear();
    }
    return res;
  }

  public String minimalString(String str, int preLen) {
    int len = str.length();
    if (len <= 2 || preLen >= len - 2) {
      return str;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(str.charAt(0));
    int preEnd = Math.min(len - 1, preLen + 1);
    for (int i = 1; i < preEnd; i++) {
      sb.append(str.charAt(i));
    }
    sb.append(len - 2 - (preEnd - 1));
    sb.append(str.charAt(len - 1));
    if (sb.length() == len) {
      return str;
    }
    return sb.toString();
  }

  class Solution528 {

    int[] sum;
    Random random;

    public Solution528(int[] w) {
      int count = 0;
      for (int i = 0; i < w.length; i++) {
        count += w[i];
      }
      int index = 0;
      this.sum = new int[count];
      for (int i = 0; i < w.length; i++) {
        for (int j = 0; j < w[i]; j++) {
          sum[index++] = i;
        }
      }
      random = new Random();
    }

    public int pickIndex() {
      return sum[random.nextInt(sum.length)];
    }
  }

  class Solution528II {

    int[] sum;
    int count;
    Random random;

    public Solution528II(int[] w) {
      this.random = new Random();
      this.sum = new int[w.length];
      this.sum[0] = w[0];
      for (int i = 1; i < w.length; i++) {
        this.sum[i] = w[i] + sum[i - 1];
      }
    }

    public int pickIndex() {
      int index = random.nextInt(sum[sum.length - 1]) + 1;
      int left = 0, right = sum.length - 1;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (sum[mid] >= index) {
          if (mid == 0 || sum[mid - 1] < index) {
            return mid;
          } else {
            right = mid - 1;
          }
        } else {
          left = mid + 1;
        }
      }
      return left;
    }
  }

  public char[][] updateBoard(char[][] board, int[] click) {
    int row = click[0], col = click[1];
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
      return board;
    }
    if (board[row][col] != 'M' && board[row][col] != 'E') {
      return board;
    }
    if (board[row][col] == 'M') {
      board[row][col] = 'X';
      return board;
    }
    int mineNum = findMineNum(board, row, col);
    if (mineNum > 0) {
      board[row][col] = (char) (mineNum + '0');
      return board;
    }
    board[row][col] = 'B';
    updateBoard(board, new int[] { row - 1, col });
    updateBoard(board, new int[] { row + 1, col });
    updateBoard(board, new int[] { row, col - 1 });
    updateBoard(board, new int[] { row, col + 1 });
    updateBoard(board, new int[] { row - 1, col - 1 });
    updateBoard(board, new int[] { row - 1, col + 1 });
    updateBoard(board, new int[] { row + 1, col - 1 });
    updateBoard(board, new int[] { row + 1, col + 1 });
    return board;
  }

  public int findMineNum(char[][] board, int row, int col) {
    int rowMax = board.length;
    int colMax = board[0].length;
    int startRow = Math.max(0, row - 1);
    int endRow = Math.min(rowMax - 1, row + 1);
    int startCol = Math.max(0, col - 1);
    int endCol = Math.min(colMax - 1, col + 1);
    int count = 0;
    for (int i = startRow; i <= endRow; i++) {
      for (int j = startCol; j <= endCol; j++) {
        if (board[i][j] == 'M') {
          count++;
        }
      }
    }
    return count;
  }

  public int getMinimumDifferencePrev = Integer.MAX_VALUE;
  public int getMinimumDifferenceMin = Integer.MAX_VALUE;

  public int getMinimumDifference(TreeNode root) {
    if (root == null) {
      return 0;
    }
    getMinimumDifference(root.left);
    getMinimumDifferenceMin = Math.min(getMinimumDifferenceMin, Math.abs(root.val - getMinimumDifferencePrev));
    getMinimumDifferencePrev = root.val;
    getMinimumDifference(root.right);
    return getMinimumDifferenceMin;
  }

  public int findLonelyPixel(char[][] picture) {
    int rowMax = picture.length;
    int colMax = picture[0].length;
    int[] rowDp = new int[rowMax];
    int[] colDp = new int[colMax];
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (picture[i][j] == 'B') {
          rowDp[i]++;
          colDp[j]++;
        }
      }
    }
    int sum = 0;
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (picture[i][j] == 'B' && rowDp[i] == 1 && colDp[j] == 1) {
          sum++;
        }
      }
    }
    return sum;
  }

  public int findPairs(int[] nums, int k) {
    Set<Integer> alreadyAddSmall = new HashSet<>();
    Set<Integer> alreadyAddBig = new HashSet<>();
    Set<Integer> prev = new HashSet<>();
    int sum = 0;
    for (int n : nums) {
      if (alreadyAddBig.contains(n) || alreadyAddSmall.contains(n)) {
        continue;
      }
      if (prev.contains(n - k) && !alreadyAddBig.contains(n - k)) {
        alreadyAddSmall.add(n);
        sum++;
      }
      if (k != 0 && prev.contains(n + k) && !alreadyAddSmall.contains(n + k)) {
        alreadyAddBig.add(n);
        sum++;
      }
      prev.add(n);
    }
    return sum;
  }

  public int findBlackPixel(char[][] picture, int target) {
    int rowMax = picture.length;
    int colMax = picture[0].length;
    int[] rowDp = new int[rowMax];
    int[] colDp = new int[colMax];
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (picture[i][j] == 'B') {
          rowDp[i]++;
          colDp[j]++;
        }
      }
    }
    int sum = 0;
    for (int i = 0; i < colMax; i++) {
      if (colDp[i] == target && checkSame(picture, i, rowDp, target)) {
        sum += target;
      }
    }
    return sum;
  }

  public boolean checkSame(char[][] picture, int col, int[] rowDp, int target) {
    char[] first = null;
    int colMax = picture[0].length;
    int rowMax = picture.length;
    for (int i = 0; i < rowMax; i++) {
      if (picture[i][col] != 'B') {
        continue;
      }
      if (rowDp[i] != target) {
        return false;
      }
      if (first == null) {
        first = picture[i];
      } else {
        for (int j = 0; j < colMax; j++) {
          if (first[j] != picture[i][j]) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public class Codec {

    public String dic = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public HashMap<String, String> map = new HashMap<>();
    public Random random = new Random();

    public String getStr() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 6; i++) {
        sb.append(dic.charAt(random.nextInt(dic.length())));
      }
      return sb.toString();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
      String shortUrl = getStr();
      while (map.containsKey(shortUrl)) {
        shortUrl = getStr();
      }
      map.put(shortUrl, longUrl);
      return "http://tinyurl.com/" + shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
      return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
  }

  public int index = 0;

  public TreeNode str2tree(String s) {
    if (s.length() == 0) {
      return null;
    }
    TreeNode t = new TreeNode();
    boolean isNeg = false;
    if (s.charAt(index) == '-') {
      isNeg = true;
      index++;
    }
    int num = 0;
    while (index < s.length()) {
      char c = s.charAt(index);
      if (c >= '0' && c <= '9') {
        num = num * 10 + (c - '0');
      } else {
        break;
      }
      index++;
    }
    if (isNeg) {
      num = -num;
    }
    t.val = num;
    if (index < s.length()) {
      if (s.charAt(index) == ')') {
        index++;
        return t;
      }
      if (index < s.length() && s.charAt(index) == '(') {
        index++;
        t.left = str2tree(s);
      }
      if (index < s.length() && s.charAt(index) == '(') {
        index++;
        t.right = str2tree(s);
      }
      if (index < s.length() && s.charAt(index) == ')') {
        index++;
      }
    }
    return t;
  }

  public String complexNumberMultiply(String num1, String num2) {
    int[] f = getNumberFormComplexNumber(num1);
    int[] s = getNumberFormComplexNumber(num2);
    StringBuilder sb = new StringBuilder();
    sb.append(f[0] * s[0] - f[1] * s[1]).append("+").append(f[0] * s[1] + f[1] * s[0]).append("i");
    return sb.toString();
  }

  public int[] getNumberFormComplexNumber(String num1) {
    int[] res = new int[2];
    char[] nc = num1.toCharArray();
    boolean isNeg = false;
    int index = 0;
    if (nc[index] == '-') {
      isNeg = true;
      index++;
    }
    int first = 0;
    while (Character.isDigit(nc[index])) {
      first = first * 10 + (nc[index] - '0');
      index++;
    }
    if (isNeg) {
      first = -first;
    }
    index++;
    int second = 0;
    isNeg = false;
    if (nc[index] == '-') {
      isNeg = true;
      index++;
    }
    while (Character.isDigit(nc[index])) {
      second = second * 10 + (nc[index] - '0');
      index++;
    }
    if (isNeg) {
      second = -second;
    }
    res[0] = first;
    res[1] = second;
    return res;
  }

  public int convertBSTSum = 0;

  public TreeNode convertBST(TreeNode root) {
    if (root == null) {
      return root;
    }
    convertBST(root.right);
    root.val += convertBSTSum;
    convertBSTSum = root.val;
    convertBST(root.left);
    return root;
  }

  public int findMinDifference(List<String> timePoints) {
    int[] l = new int[timePoints.size()];
    for (int i = 0; i < timePoints.size(); i++) {
      l[i] = getMinFromStr(timePoints.get(i));
    }
    Arrays.sort(l);
    int min = Integer.MAX_VALUE;
    for (int i = 1; i < l.length; i++) {
      min = Math.min(min, l[i] - l[i - 1]);
    }
    min = Math.min(min, l[0] + 24 * 60 - l[l.length - 1]);
    return min;
  }

  public int getMinFromStr(String str) {
    int sum = 0, cur = 0;
    for (char c : str.toCharArray()) {
      if (c == ':') {
        sum += cur * 60;
        cur = 0;
        continue;
      }
      cur = cur * 10 + (c - '0');
    }
    return sum + cur;
  }

  public int singleNonDuplicate(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == nums[mid ^ 1]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return nums[left];
  }

  public int singleNonDuplicateII(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }
    int left = 0, right = nums.length - 1;
    while (left < right) {
      int mid = (left + right) / 2;
      if (mid % 2 == 0) {
        if (nums[mid] == nums[mid + 1]) {
          left = mid + 2;
        } else {
          right = mid - 1;
        }
      } else {
        if (nums[mid] == nums[mid - 1]) {
          left = mid + 1;
        } else {
          right = mid - 2;
        }
      }
    }
    return nums[left];
  }

}

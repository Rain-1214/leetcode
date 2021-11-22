package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlgorithmController461to480 {
  public int hammingDistance(int x, int y) {
    int i = x ^ y, res = 0;
    while (i > 0) {
      res++;
      i &= i - 1;
    }
    return res;
  }

  public int minMoves2(int[] nums) {
    Arrays.sort(nums);
    int res = 0;
    for (int num : nums) {
      res += Math.abs(nums[nums.length / 2] - num);
    }
    return res;
  }

  public int minMoves2II(int[] nums) {
    int mid = minMovesHelp(nums, 0, nums.length - 1, nums.length / 2);
    int res = 0;
    for (int num : nums) {
      res += Math.abs(mid - num);
    }
    return res;
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public int minMovesHelp(int[] nums, int left, int right, int target) {
    if (left == right) {
      return nums[left];
    }
    int temp = nums[right];
    int i = left, index = left;
    while (index < right) {
      if (nums[index] < temp) {
        swap(nums, i, index);
        i++;
      }
      index++;
    }
    swap(nums, i, right);
    if (i == target) {
      return nums[i];
    } else if (i > target) {
      return minMovesHelp(nums, left, i - 1, target);
    } else {
      return minMovesHelp(nums, i + 1, right, target);
    }
  }

  public static final int[][] dirs = new int[][] { new int[] { 0, 1 }, new int[] { 0, -1 }, new int[] { 1, 0 },
      new int[] { -1, 0 } };

  public int islandPerimeter(int[][] grid) {
    int rowMax = grid.length;
    int colMax = grid[0].length;
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (grid[i][j] == 1) {
          return islandPerimeterHelp(grid, i, j, rowMax, colMax);
        }
      }
    }
    return 0;
  }

  public int islandPerimeterHelp(int[][] grid, int i, int j, int rowMax, int colMax) {
    int res = 0;
    grid[i][j] = -1;
    for (int[] dir : dirs) {
      int x = i + dir[0], y = j + dir[1];
      if (x < 0 || x >= rowMax || y < 0 || y >= colMax || grid[x][y] == 0) {
        res++;
      } else if (grid[x][y] == 1) {
        res += islandPerimeterHelp(grid, x, y, rowMax, colMax);
      }
    }
    return res;
  }

  public int islandPerimeterII(int[][] grid) {
    int rowMax = grid.length;
    int colMax = grid[0].length;
    int res = 0;
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (grid[i][j] == 1) {
          res += 4;
          if (i > 0 && grid[i - 1][j] == 1) {
            res -= 2;
          }
          if (j > 0 && grid[i][j - 1] == 1) {
            res -= 2;
          }
        }
      }
    }
    return res;
  }

  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    if (maxChoosableInteger >= desiredTotal) {
      return true;
    }
    if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
      return false;
    }
    Boolean[] used = new Boolean[1 << maxChoosableInteger];

    return canIWinHelp(used, desiredTotal, 0, maxChoosableInteger);
  }

  public boolean canIWinHelp(Boolean[] used, int desiredTotal, int cur, int maxChoosableInteger) {
    if (used[cur] != null) {
      return used[cur];
    }
    for (int i = 1; i <= maxChoosableInteger; i++) {
      int temp = 1 << (i - 1);
      if ((temp & cur) != 0) {
        continue;
      }
      if (i >= desiredTotal || !canIWinHelp(used, desiredTotal - i, cur | temp, maxChoosableInteger)) {
        used[cur] = true;
        return true;
      }
    }
    used[cur] = false;
    return false;
  }

  public int minTransferRes = Integer.MAX_VALUE;

  public int minTransfers(int[][] transactions) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int[] transaction : transactions) {
      int from = transaction[0], to = transaction[1], amount = transaction[2];
      map.put(from, map.getOrDefault(from, 0) - amount);
      map.put(to, map.getOrDefault(to, 0) + amount);
    }
    List<Integer> list = new ArrayList<>(map.values());
    minTransfersHelp(0, 0, list);
    return minTransferRes;
  }

  public void minTransfersHelp(int start, int current, List<Integer> amounts) {
    if (start > minTransferRes) {
      return;
    }
    while (start < amounts.size() && amounts.get(start) == 0) {
      start++;
    }
    if (start == amounts.size()) {
      minTransferRes = Math.min(minTransferRes, current);
      return;
    }

    for (int i = start + 1; i < amounts.size(); i++) {
      if (amounts.get(start) * amounts.get(i) < 0) {
        int temp = amounts.get(start);
        amounts.set(i, amounts.get(i) + temp);
        minTransfersHelp(start + 1, current + 1, amounts);
        amounts.set(i, amounts.get(i) - temp);
      }
    }
  }

  public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
    if (n1 == 0) {
      return 0;
    }
    int s1Count = 0, s2Count = 0, index = 0;
    Map<Integer, int[]> map = new HashMap<>();
    int[] prefix = new int[2];
    int[] loop = new int[2];
    while (true) {
      ++s1Count;
      for (int i = 0; i < s1.length(); i++) {
        if (s1.charAt(i) == s2.charAt(index)) {
          index++;
        }
        if (index == s2.length()) {
          index = 0;
          ++s2Count;
        }
      }
      if (s1Count == n1) {
        return s2Count / n2;
      }
      if (map.containsKey(index)) {
        int[] temp = map.get(index);
        int preS1Count = temp[0];
        int preS2Count = temp[1];
        prefix = new int[] { preS1Count, preS2Count };
        loop = new int[] { s1Count - preS1Count, s2Count - preS2Count };
        break;
      } else {
        map.put(index, new int[] { s1Count, s2Count });
      }
    }
    int ans = prefix[1] + (n1 - prefix[0]) / loop[0] * loop[1];
    int suffix = (n1 - prefix[0]) % loop[0];
    for (int i = 0; i < suffix; i++) {
      for (int j = 0; j < s1.length(); j++) {
        if (s1.charAt(j) == s2.charAt(index)) {
          index++;
        }
        if (index == s2.length()) {
          index = 0;
          ans++;
        }
      }
    }
    return ans / n2;
  }

  public int findSubstringInWraproundString(String p) {
    char[] chars = p.toCharArray();
    int[] count = new int[26];
    int max = 1;
    for (int i = 0; i < chars.length; i++) {
      if (i > 0 && (chars[i] - chars[i - 1] == 1 || chars[i] - chars[i - 1] == -25)) {
        max++;
      } else {
        max = 1;
      }
      count[chars[i] - 'a'] = Math.max(count[chars[i] - 'a'], max);
    }
    int res = 0;
    for (int i = 0; i < 26; i++) {
      res += count[i];
    }
    return res;
  }

  public final String ipv4 = "IPv4";
  public final String ipv6 = "IPv6";
  public final String invalid = "Neither";

  public String validIPAddress(String queryIP) {
    if (queryIP.contains(":")) {
      if (validIPv6Address(queryIP)) {
        return ipv6;
      } else {
        return invalid;
      }
    } else if (queryIP.contains(".")) {
      if (validIPv4Address(queryIP)) {
        return ipv4;
      } else {
        return invalid;
      }
    }
    return invalid;
  }

  public boolean validIPv4Address(String queryIP) {
    if (queryIP.startsWith(".") || queryIP.endsWith(".")) {
      return false;
    }
    String[] parts = queryIP.split("\\.");
    if (parts.length != 4) {
      return false;
    }
    for (String part : parts) {
      if (part.length() == 0 || part.length() > 3) {
        return false;
      }
      if (part.charAt(0) == '0' && part.length() > 1) {
        return false;
      }
      for (int i = 0; i < part.length(); i++) {
        if (!Character.isDigit(part.charAt(i))) {
          return false;
        }
      }
      int num = Integer.parseInt(part);
      if (num < 0 || num > 255) {
        return false;
      }
    }
    return true;
  }

  public boolean validIPv6Address(String queryIP) {
    if (queryIP.startsWith(":") || queryIP.endsWith(":")) {
      return false;
    }
    String[] parts = queryIP.split(":");
    if (parts.length != 8) {
      return false;
    }
    for (String part : parts) {
      if (part.length() == 0 || part.length() > 4) {
        return false;
      }
      for (int i = 0; i < part.length(); i++) {
        if (!Character.isDigit(part.charAt(i)) && !(part.charAt(i) >= 'a' && part.charAt(i) <= 'f')
            && !(part.charAt(i) >= 'A' && part.charAt(i) <= 'F')) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isConvex(List<List<Integer>> points) {
    int len = points.size(), temp = 0;
    for (int i = 0; i < len; i++) {
      int x1 = points.get((i + 1) % len).get(0) - points.get(i).get(0);
      int y1 = points.get((i + 1) % len).get(1) - points.get(i).get(1);

      int x2 = points.get((i + 2) % len).get(0) - points.get((i + 1) % len).get(0);
      int y2 = points.get((i + 2) % len).get(1) - points.get((i + 1) % len).get(1);

      int t = x1 * y2 - x2 * y1;
      if (t != 0) {
        if (t * 1l * temp < 0) {
          return false;
        }
        temp = t;
      }
    }
    return true;
  }

  class Rand10Base {
    int rand7() {
      return 1;
    }
  }

  class Solution extends Rand10Base {
    public int rand10() {
      int first = rand7(), second = rand7();
      while (first > 6) {
        first = rand7();
      }
      while (second > 5) {
        second = rand7();
      }
      return first % 2 == 0 ? second + 5 : second;
    }
  }

  public String encode(String s) {
    int len = s.length();
    String[][] dp = new String[len][len];
    StringBuilder sb = new StringBuilder();
    for (int currentLen = 1; currentLen <= len; currentLen++) {
      for (int i = 0; i + currentLen - 1 < len; i++) {
        int j = i + currentLen - 1;
        dp[i][j] = repeatedSubstringPattern(s, i, j, dp);
        if (currentLen > 4) {
          for (int k = i; k < j; k++) {
            sb.append(dp[i][k]).append(dp[k + 1][j]);
            if (sb.length() < dp[i][j].length()) {
              dp[i][j] = sb.toString();
            }
            sb.setLength(0);
          }
        }
      }
    }
    return dp[0][len - 1];
  }

  public String repeatedSubstringPattern(String s, int left, int right, String[][] dp) {
    String temp = s.substring(left, right + 1);
    if (temp.length() <= 4) {
      return temp;
    }
    int index = (temp + temp).indexOf(temp, 1);
    if (index != temp.length()) {
      int num = temp.length() / index;
      return num + "[" + dp[left][left + index - 1] + "]";
    }
    return temp;
  }

  class DictionaryTree {
    boolean isEnd = false;
    DictionaryTree[] children = new DictionaryTree[26];

    public void insert(String word) {
      DictionaryTree node = this;
      for (int i = 0; i < word.length(); i++) {
        int index = word.charAt(i) - 'a';
        if (node.children[index] == null) {
          node.children[index] = new DictionaryTree();
        }
        node = node.children[index];
      }
      node.isEnd = true;
    }
  }

  public int level = 0;

  public List<String> findAllConcatenatedWordsInADict(String[] words) {
    DictionaryTree root = new DictionaryTree();
    for (String word : words) {
      if (word.length() > 0) {
        root.insert(word);
      }
    }
    List<String> res = new ArrayList<>();
    Map<String, Boolean> map = new HashMap<>();
    for (String word : words) {
      level = 0;
      if (word.length() > 0 && isConcatenated(word, root, 0, map) && level > 0) {
        res.add(word);
      }
    }
    return res;
  }

  public boolean isConcatenated(String word, DictionaryTree root, int start, Map<String, Boolean> cache) {
    String temp = word.substring(start);
    if (cache.containsKey(temp)) {
      if (level == 0) {
        level = 1;
      }
      return cache.get(temp);
    }
    int len = word.length();
    level++;
    DictionaryTree node = root;
    for (int i = start; i < len; i++) {
      int index = word.charAt(i) - 'a';
      if (node.children[index] == null) {
        level--;
        cache.put(temp, false);
        return false;
      }
      node = node.children[index];
      if (node.isEnd) {
        if (i == len - 1) {
          level--;
          return true;
        } else if (isConcatenated(word, root, i + 1, cache)) {
          cache.put(temp, true);
          return true;
        }
      }
    }
    level--;
    cache.put(temp, false);
    return false;
  }

  public boolean makesquare(int[] matchsticks) {
    int sum = 0;
    for (int i : matchsticks) {
      sum += i;
    }
    if (sum % 4 != 0) {
      return false;
    }
    Arrays.sort(matchsticks);
    int[] set = new int[4];
    int slide = sum / 4;
    reverse(matchsticks);
    return makesquareHelp(set, slide, matchsticks, 0);
  }

  public void reverse(int[] nums) {
    int len = nums.length;
    for (int i = 0; i < len / 2; i++) {
      int temp = nums[i];
      nums[i] = nums[len - 1 - i];
      nums[len - 1 - i] = temp;
    }
  }

  public boolean makesquareHelp(int[] set, int slide, int[] matchsticks, int matIndex) {
    if (matIndex == matchsticks.length) {
      return set[0] == slide && set[1] == slide && set[2] == slide;
    }
    for (int i = 0; i < 4; i++) {
      if (set[i] + matchsticks[matIndex] <= slide) {
        set[i] += matchsticks[matIndex];
        if (makesquareHelp(set, slide, matchsticks, matIndex + 1)) {
          return true;
        }
        set[i] -= matchsticks[matIndex];
      }
    }
    return false;
  }

  public int findMaxForm(String[] strs, int m, int n) {
    int len = strs.length;
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i < len; i++) {
      String str = strs[i];
      int zeros = 0, ones = 0;
      for (char c : str.toCharArray()) {
        if (c == '0') {
          zeros++;
        } else {
          ones++;
        }
      }
      for (int j = m; j >= zeros; j--) {
        for (int k = n; k >= ones; k--) {
          dp[j][k] = Math.max(dp[j][k], dp[j - zeros][k - ones] + 1);
        }
      }
    }
    return dp[m][n];
  }

  public int findRadius(int[] houses, int[] heaters) {
    Arrays.sort(houses);
    Arrays.sort(heaters);
    int res = 0;
    for (int i = 0; i < houses.length; i++) {
      int bigger = binarySearchBigger(heaters, houses[i]);
      int small = binarySearchSmall(heaters, houses[i]);
      if (bigger == -1) {
        res = Math.max(res, Math.abs(heaters[small] - houses[i]));
      } else if (small == -1) {
        res = Math.max(res, Math.abs(houses[i] - heaters[bigger]));
      } else {
        res = Math.max(res, Math.min(Math.abs(houses[i] - heaters[bigger]), Math.abs(houses[i] - heaters[small])));
      }
    }
    return res;
  }

  public int binarySearchBigger(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < target) {
        left = mid + 1;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        return mid;
      }
    }
    if (left >= nums.length || nums[left] < target) {
      return -1;
    }
    return left;
  }

  public int binarySearchSmall(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > target) {
        right = mid - 1;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        return mid;
      }
    }
    if (right < 0 || nums[right] > target) {
      return -1;
    }
    return right;
  }

  public int findComplement(int num) {
    int res = 0;
    int temp = num;
    while (temp > 0) {
      res = (res << 1) + 1;
      temp = temp >> 1;
    }
    return res ^ num;
  }

  public int totalHammingDistance(int[] nums) {
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        res += hammingDistance(nums[i], nums[j]);
      }
    }
    return res;
  }

  public int totalHammingDistanceII(int[] nums) {
    int res = 0, len = nums.length;
    for (int i = 0; i < 30; i++) {
      int temp = 0;
      for (int n : nums) {
        temp += (n >> i) & 1;
      }
      res += temp * (len - temp);
    }
    return res;
  }

}

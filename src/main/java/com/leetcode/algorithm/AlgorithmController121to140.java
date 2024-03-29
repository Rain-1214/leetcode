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
import java.util.Map.Entry;

import com.leetcode.entity.TreeNode;
import com.leetcode.tool.Node;

public class AlgorithmController121to140 {
  public int maxProfit(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }
    int minBuy = prices[0];
    int res = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > minBuy) {
        res = Math.max(prices[i] - minBuy, res);
      } else {
        minBuy = prices[i];
      }
    }
    return res;
  }

  public int maxProfit2(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }
    int res = 0;
    for (int i = 0; i < prices.length - 1; i++) {
      if (prices[i + 1] > prices[i]) {
        res += prices[i + 1] - prices[i];
      }
    }
    return res;
  }

  public int maxProfit3TooSlow(int[] prices) {
    if (prices == null || prices.length < 1) {
      return 0;
    }
    int[] c = new int[3];
    int[] g = new int[3];
    for (int i = 0; i < prices.length - 1; i++) {
      int t = prices[i + 1] - prices[i];
      for (int y = c.length - 1; y > 0; y--) {
        c[y] = Math.max(g[y - 1] + (t > 0 ? t : 0), c[y] + t);
        g[y] = Math.max(c[y], g[y]);
      }
    }
    return g[2];
  }

  public int maxProfit3(int[] prices) {
    if (prices == null || prices.length < 1) {
      return 0;
    }
    int buy1 = Integer.MIN_VALUE;
    int sell1 = 0;
    int buy2 = Integer.MIN_VALUE;
    int sell2 = 0;

    for (int i = 0; i < prices.length; i++) {
      buy1 = Math.max(buy1, -prices[i]);
      sell1 = Math.max(sell1, buy1 + prices[i]);
      buy2 = Math.max(buy2, sell1 - prices[i]);
      sell2 = Math.max(sell2, buy2 + prices[i]);
    }

    return sell2;

  }

  public int maxPath = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    maxPathSumImpl(root);
    return this.maxPath;
  }

  public int maxPathSumImpl(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = Math.max(maxPathSumImpl(root.left), 0);
    int right = Math.max(maxPathSumImpl(root.right), 0);
    this.maxPath = Math.max(left + right + root.val, this.maxPath);
    return Math.max(left, right) + root.val;
  }

  public boolean isPalindromeValid(char c) {
    return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  public boolean isPalindrome(String s) {
    if (s == null) {
      return false;
    }
    int n = s.length();
    if (n < 2) {
      return true;
    }
    int l = 0, r = n - 1;
    while (l < r) {
      char left = s.charAt(l);
      char right = s.charAt(r);
      if (!isPalindromeValid(left)) {
        l++;
        continue;
      }
      if (!isPalindromeValid(right)) {
        r--;
        continue;
      }
      if (left != right) {
        if ((left >= '0' && left <= '9') || (right >= '0' && right <= '9')) {
          return false;
        } else if (Math.abs(right - left) != 32) {
          return false;
        } else {
          l++;
          r--;
        }
      } else {
        l++;
        r--;
      }
    }
    return true;
  }

  public int minLevel = Integer.MAX_VALUE;

  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> res = new ArrayList<>();
    HashSet<String> use = new HashSet<>(wordList);
    List<String> currentLevelUse = new ArrayList<>();
    if (!use.contains(endWord)) {
      return res;
    }
    Queue<LinkedList<String>> path = new LinkedList<>();
    LinkedList<String> temp = new LinkedList<>();
    temp.add(beginWord);
    path.add(temp);
    int level = 1, minLevel = Integer.MAX_VALUE;
    while (!path.isEmpty()) {
      LinkedList<String> currentPath = path.poll();
      if (currentPath.size() > level) {
        for (String u : currentLevelUse) {
          use.remove(u);
        }
        currentLevelUse.clear();
        level = currentPath.size();
        if (level >= minLevel) {
          continue;
        }
      }
      String last = currentPath.getLast();
      for (int i = 0; i < last.length(); i++) {
        char[] charArray = last.toCharArray();
        for (char c = 'a'; c <= 'z'; c++) {
          charArray[i] = c;
          String sbs = new String(charArray);
          if (use.contains(sbs)) {
            currentLevelUse.add(sbs);
            LinkedList<String> l = new LinkedList<>();
            l.addAll(currentPath);
            l.add(sbs);
            if (sbs.equals(endWord)) {
              minLevel = level;
              res.add(l);
            } else {
              path.add(l);
            }
          }
        }
      }
    }
    return res;
  }

  // ToDo: ...
  // public List<List<String>> findLadders2(String beginWord, String endWord,
  // List<String> wordList) {
  // // 结果
  // List<List<String>> res = new ArrayList<>();
  // if (wordList == null)
  // return res;
  // // bfs搜索所用的字典
  // Set<String> dicts = new HashSet<>(wordList);
  // if (!dicts.contains(endWord))
  // return res;
  // if (dicts.contains(beginWord))
  // dicts.remove(beginWord);
  // // bfs搜索最短路径所用的开始和结束的字典
  // Set<String> endList = new HashSet<>(), beginList = new HashSet<>();
  // // 每个点所对应的邻接点，list
  // Map<String, List<String>> map = new HashMap<>();
  // beginList.add(beginWord);
  // endList.add(endWord);
  // bfs(map, beginList, endList, beginWord, endWord, dicts, false);
  // // dfs的前进路线保存list
  // List<String> subList = new ArrayList<>();
  // subList.add(beginWord);
  // dfs(map, res, subList, beginWord, endWord);
  // return res;
  // }

  // void dfs(Map<String, List<String>> map, List<List<String>> result,
  // List<String> subList, String beginWord,
  // String endWord) {
  // if (beginWord.equals(endWord)) {
  // result.add(new ArrayList<>(subList));
  // return;
  // }
  // if (!map.containsKey(beginWord)) {
  // return;
  // }
  // for (String word : map.get(beginWord)) {
  // subList.add(word);
  // dfs(map, result, subList, word, endWord);
  // subList.remove(subList.size() - 1);
  // }
  // }

  // // reverse是双端bfs的一个优化
  // public void bfs(Map<String, List<String>> map, Set<String> beginList,
  // Set<String> endList, String beginWord,
  // String endWord, Set<String> wordList, boolean reverse) {
  // if (beginList.size() == 0)
  // return;
  // wordList.removeAll(beginList);
  // boolean finish = false;
  // Set<String> temp = new HashSet<>();
  // for (String str : beginList) {
  // char[] charr = str.toCharArray();
  // for (int chI = 0; chI < charr.length; chI++) {
  // char old = charr[chI];
  // for (char ch = 'a'; ch <= 'z'; ch++) {
  // if (ch == old)
  // continue;
  // charr[chI] = ch;
  // String newstr = new String(charr);
  // if (!wordList.contains(newstr)) {
  // continue;
  // }
  // // 若是在某一层找到了最后的节点，就直接标记找到了，即一票决定。这里因为要找所有的最短路径，所以循环还是要继续的。
  // if (endList.contains(newstr)) {
  // finish = true;
  // } else {
  // temp.add(newstr);
  // }
  // // 无论怎么变换方向，永远用开始方向的字符做key，是为了后面的dfs，单一方向搜索
  // String key = reverse ? newstr : str;
  // String value = reverse ? str : newstr;
  // if (!map.containsKey(key)) {
  // map.put(key, new ArrayList<>());
  // }
  // map.get(key).add(value);

  // }
  // charr[chI] = old;
  // }
  // }
  // if (!finish) {
  // if (temp.size() > endList.size()) {
  // bfs(map, endList, temp, beginWord, endWord, wordList, !reverse);
  // } else {
  // bfs(map, temp, endList, beginWord, endWord, wordList, reverse);
  // }
  // }
  // }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    HashSet<String> use = new HashSet<>(wordList);
    Queue<String> path = new LinkedList<>();
    Set<String> endSet = new HashSet<>();
    endSet.add(endWord);
    int level = 1;
    path.add(beginWord);
    while (!path.isEmpty()) {
      int len = path.size();
      System.out.println(path);
      for (int l = 0; l < len; l++) {
        String prev = path.poll();
        for (int i = 0; i < prev.length(); i++) {
          char[] charArray = prev.toCharArray();
          for (char c = 'a'; c <= 'z'; c++) {
            charArray[i] = c;
            String sbs = new String(charArray);
            if (use.contains(sbs)) {
              if (endSet.contains(sbs)) {
                return level + 1;
              } else {
                use.remove(sbs);
                path.add(sbs);
              }
            }
          }
        }
      }
      level++;
    }
    return 0;
  }

  public int longestConsecutiveTooSlow(int[] nums) {
    if (nums.length <= 0) {
      return 0;
    }
    Set<Integer> dic = new HashSet<>();
    for (int n : nums) {
      dic.add(n);
    }
    int max = 1;
    for (int i = 0; i < nums.length; i++) {
      if (dic.size() <= 0) {
        break;
      }
      int t = nums[i];
      int right = t;
      int left = t;
      while (true) {
        if (dic.contains(++right)) {
          dic.remove(right);
        } else {
          break;
        }
      }
      while (true) {
        if (dic.contains(--left)) {
          dic.remove(left);
        } else {
          break;
        }
      }
      max = Math.max(max, right - left - 1);
    }
    return max;
  }

  public int longestConsecutive(int[] nums) {
    if (nums.length <= 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (int n : nums) {
      max = Math.max(max, n);
      min = Math.min(min, n);
    }
    int[] dic = new int[max - min + 1];
    for (int n : nums) {
      dic[n - min] = 1;
    }
    int res = 0;
    int currentRes = 0;
    for (int i = 0; i < max - min + 1; i++) {
      if (dic[i] == 1) {
        currentRes++;
        res = Math.max(res, currentRes);
      } else {
        currentRes = 0;
      }
    }
    return res;
  }

  public int sumNumbers(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return sumNumbersImpl(root, 0);
  }

  public int sumNumbersImpl(TreeNode root, int parentVal) {
    if (root == null) {
      return 0;
    }
    int val = parentVal * 10 + root.val;
    int left = sumNumbersImpl(root.left, val);
    int right = sumNumbersImpl(root.right, val);
    if (left == 0 && right == 0) {
      return val;
    }
    return left + right;
  }

  public void solve(char[][] board) {
    if (board.length < 2 || board[0].length < 2) {
      return;
    }
    int xLen = board[0].length;
    int yLen = board.length;
    for (int y = 0; y < yLen; y++) {
      solveHelp(0, y, board);
      solveHelp(xLen - 1, y, board);
    }
    for (int x = 0; x < xLen; x++) {
      solveHelp(x, 0, board);
      solveHelp(x, yLen - 1, board);
    }
    for (int y = 0; y < xLen; y++) {
      for (int x = 0; x < yLen; x++) {
        if (board[x][y] == 'O') {
          board[x][y] = 'X';
        }
        if (board[x][y] == 't') {
          board[x][y] = 'O';
        }
      }
    }
  }

  public void solveHelp(int x, int y, char[][] board) {
    if (x < 0 || y < 0 || y >= board.length || x >= board[0].length) {
      return;
    }
    if (board[y][x] == 'X' || board[y][x] == 't') {
      return;
    }
    board[y][x] = 't';
    solveHelp(x - 1, y, board);
    solveHelp(x + 1, y, board);
    solveHelp(x, y - 1, board);
    solveHelp(x, y + 1, board);
  }

  public List<List<String>> partition(String s) {
    List<List<String>> res = new ArrayList<>();
    partitionImpl(s, 0, new LinkedList<>(), res);
    return res;
  }

  public void partitionImpl(String sb, int index, LinkedList<String> current, List<List<String>> res) {
    int sLen = sb.length();
    if (index >= sLen) {
      res.add(new ArrayList<>(current));
      return;
    }
    for (int i = index; i < sLen; i++) {
      if (!isPalindrome(sb, index, i)) {
        continue;
      }
      current.add(sb.substring(index, i + 1));
      partitionImpl(sb, i + 1, current, res);
      current.removeLast();
    }

  }

  public boolean isPalindrome(String s, int b, int e) {
    while (b < e) {
      if (s.charAt(b++) != s.charAt(e--)) {
        return false;
      }
    }
    return true;
  }

  public int minCutTooSlow(String s) {
    if (s.isEmpty()) {
      return 0;
    }
    int sLen = s.length();
    boolean[][] p = new boolean[sLen][sLen];
    int[] dp = new int[sLen];
    char[] ca = s.toCharArray();
    for (int i = 0; i < sLen; ++i) {
      dp[i] = i;
      for (int j = 0; j <= i; ++j) {
        if (ca[i] == ca[j] && (i - j < 2 || p[j + 1][i - 1])) {
          p[j][i] = true;
          dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j - 1] + 1);
        }
      }
    }
    return dp[sLen - 1];
  }

  public int minCut(String s) {
    int length = s.length();
    int[] cuts = new int[length];
    char[] ar = s.toCharArray();
    Arrays.fill(cuts, length);
    for (int index = 0; index < length; index++) {
      fill(index, index, cuts, ar);
      fill(index, index + 1, cuts, ar);
    }
    return cuts[length - 1];
  }

  private void fill(int left, int right, int[] cuts, char[] ar) {
    while (left >= 0 && right < cuts.length && ar[left] == ar[right]) {
      cuts[right] = Math.min(cuts[right], left == 0 ? 0 : cuts[left - 1] + 1);
      left--;
      right++;
    }
  }

  public Map<Integer, Node> graphPool = new HashMap<>();

  public Node cloneGraph(Node node) {
    if (node == null) {
      return node;
    }
    if (this.graphPool.containsKey(node.val)) {
      return this.graphPool.get(node.val);
    }
    Node temp = new Node(node.val);
    this.graphPool.put(node.val, temp);
    for (Node n : node.neighbors) {
      temp.neighbors.add(cloneGraph(n));
    }
    return temp;
  }

  public int canCompleteCircuitTooSlow(int[] gas, int[] cost) {
    int temp = 0;
    for (int i = 0; i < gas.length; i++) {
      if (gas[i] < cost[i]) {
        continue;
      }
      int index = i + 1 == gas.length ? 0 : i + 1;
      temp = gas[i] - cost[i];
      while (index != i) {
        temp = temp + gas[index] - cost[index];
        System.out.print(index);
        System.out.println(temp);
        if (temp < 0) {
          break;
        }
        index = index + 1 == gas.length ? 0 : index + 1;
      }
      if (index == i && temp >= 0) {
        return temp;
      }
    }
    return -1;
  }

  public int canCompleteCircuit(int[] gas, int[] cost) {
    int total = 0, current = 0, start = 0;
    for (int i = 0; i < gas.length; i++) {
      total += gas[i] - cost[i];
      current += gas[i] - cost[i];
      if (current < 0) {
        start = i + 1;
        current = 0;
      }
    }
    return total < 0 ? -1 : start;
  }

  public int candy(int[] ratings) {
    int[] t = new int[ratings.length];
    Arrays.fill(t, 1);
    int len = ratings.length;
    for (int i = 0; i < len - 1; i++) {
      if (ratings[i + 1] > ratings[i]) {
        t[i + 1] = t[i] + 1;
      }
    }
    int res = 0;
    for (int i = len - 1; i > 0; i--) {
      if (ratings[i - 1] > ratings[i]) {
        t[i - 1] = Math.max(t[i] + 1, t[i - 1]);
      }
      res += t[i];
    }
    res += t[0];
    return res;
  }

  public int singleNumber(int[] nums) {
    Set<Integer> t = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (!t.contains(nums[i])) {
        t.add(nums[i]);
      } else {
        t.remove(nums[i]);
      }
    }
    return t.iterator().next();
  }

  public int singleNumberII(int[] nums) {
    Map<Integer, Integer> t = new HashMap<>();
    for (int n : nums) {
      t.put(n, t.getOrDefault(n, 0) + 1);
    }
    for (Entry<Integer, Integer> e : t.entrySet()) {
      if (e.getValue() == 1) {
        return e.getKey();
      }
    }
    return 0;
  }

  public boolean wordBreak(String s, List<String> wordDict) {
    if (wordDict == null || wordDict.size() == 0) {
      return false;
    }
    int[] memo = new int[s.length()];
    return wordBreakHelper(s, 0, wordDict, memo);
  }

  public boolean wordBreakHelper(String s, int start, List<String> wordDict, int[] memo) {
    if (start > s.length() - 1) {
      return true;
    }
    if (memo[start] != 0) {
      return memo[start] == 1;
    }
    for (String cd : wordDict) {
      if (s.indexOf(cd, start) == start && wordBreakHelper(s, start + cd.length(), wordDict, memo)) {
        memo[start] = 1;
        return true;
      }
    }
    memo[start] = -1;
    return false;
  }

  public boolean wordBreakTooSlow(String s, List<String> wordDict) {
    if (wordDict == null || wordDict.size() == 0) {
      return false;
    }
    Set<String> set = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] && set.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[dp.length - 1];
  }

  public List<String> wordBreakIIWTF(String s, List<String> wordDict) {
    List<String> res = new ArrayList<>();
    // WTF??????
    if (wordDict == null || wordDict.size() == 0 || s.length() > 100) {
      return res;
    }
    wordBreakHelperII(s, wordDict, new StringBuilder(), res);
    return res;
  }

  public void wordBreakHelperII(String s, List<String> wordDict, StringBuilder sb, List<String> res) {
    if (s.length() != 0) {
      sb.append(" ");
      return;
    }
    for (String cd : wordDict) {
      if (s.startsWith(cd)) {
        StringBuilder nsb = new StringBuilder(sb);
        nsb.append(cd);
        if (s.equals(cd)) {
          res.add(nsb.toString());
        } else {
          wordBreakHelperII(s, wordDict, nsb, res);
        }
      }
    }
  }

  public List<String> wordBreakII(String s, List<String> wordDict) {
    List<String> res = new ArrayList<>();
    if (wordDict == null || wordDict.size() == 0) {
      return res;
    }
    return wordBreakHelperII(s, wordDict, new HashMap<>());
  }

  public List<String> wordBreakHelperII(String s, List<String> wordDict, Map<String, List<String>> memo) {
    if (memo.containsKey(s)) {
      return memo.get(s);
    }
    List<String> res = new ArrayList<>();
    for (String cd : wordDict) {
      if (!s.startsWith(cd)) {
        continue;
      }
      if (s.length() == cd.length()) {
        res.add(cd);
        continue;
      }
      List<String> t = wordBreakHelperII(s.substring(cd.length()), wordDict, memo);
      for (String st : t) {
        StringBuilder sb = new StringBuilder();
        sb.append(cd).append(" ").append(st);
        res.add(sb.toString());
      }
    }
    memo.put(s, res);
    return res;
  }

}

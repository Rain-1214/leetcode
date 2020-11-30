package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.leetcode.entity.TreeNode;
import com.leetcode.tool.Print;

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

}

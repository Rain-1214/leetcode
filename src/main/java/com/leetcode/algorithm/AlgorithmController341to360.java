package com.leetcode.algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.leetcode.entity.NestedInteger;

public class AlgorithmController341to360 {

  public class NestedIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
      LinkedList<Integer> list = new LinkedList<>();
      this.transformList(nestedList, list);
      this.iterator = list.iterator();
    }

    public void transformList(List<NestedInteger> nestedList, LinkedList<Integer> list) {
      for (NestedInteger nested : nestedList) {
        if (nested.isInteger()) {
          list.add(nested.getInteger());
        } else {
          transformList(nested.getList(), list);
        }
      }
    }

    @Override
    public Integer next() {
      return iterator.next();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }
  }

  public boolean isPowerOfFour(int n) {
    if (n < 1) {
      return false;
    }
    while (n > 1) {
      if (n % 4 == 0) {
        n /= 4;
      } else {
        return false;
      }
    }
    return n == 1;
  }

  public int integerBreak(int n) {
    int[] dp = new int[n + 1];
    dp[2] = 1;
    for (int i = 3; i <= n; i++) {
      int max = 0;
      for (int j = 1; j < i; j++) {
        max = Math.max(Math.max(j * (i - j), j * dp[i - j]), max);
      }
      dp[i] = max;
    }
    return dp[n];
  }

  public void reverseString(char[] s) {
    int left = 0, right = s.length - 1;
    while (left < right) {
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;
      left++;
      right--;
    }
  }

  public String reverseVowels(String s) {
    char[] sa = s.toCharArray();
    int left = 0, right = sa.length - 1;
    Set<Character> dic = new HashSet<>();
    dic.add('a');
    dic.add('e');
    dic.add('i');
    dic.add('o');
    dic.add('u');
    dic.add('A');
    dic.add('E');
    dic.add('I');
    dic.add('O');
    dic.add('U');
    while (left < right) {
      if (!dic.contains(sa[left])) {
        left++;
        continue;
      } else if (!dic.contains(sa[right])) {
        right--;
        continue;
      } else {
        char temp = sa[left];
        sa[left] = sa[right];
        sa[right] = temp;
        left++;
        right--;
      }
    }
    return new String(sa);
  }

  public String reverseVowelsII(String s) {
    char[] sa = s.toCharArray();
    int left = 0, right = sa.length - 1;
    while (left < right) {
      if (!isVowels(sa[left])) {
        left++;
        continue;
      } else if (!isVowels(sa[right])) {
        right--;
        continue;
      } else {
        char temp = sa[left];
        sa[left] = sa[right];
        sa[right] = temp;
        left++;
        right--;
      }
    }
    return new String(sa);
  }

  public boolean isVowels(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
        || c == 'U';
  }

}

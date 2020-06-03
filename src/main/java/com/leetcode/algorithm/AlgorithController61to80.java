package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leetcode.entity.ListNode;
import com.leetcode.tool.Print;

public class AlgorithController61to80 {

  public ListNode rotateRight(ListNode head, int k) {
    int len = 0;
    ListNode currentNode = head;
    while (currentNode != null) {
      len++;
      currentNode = currentNode.next;
    }
    if (len <= 1 || k % len == 0) {
      return head;
    }
    int point = k;
    if (k > len) {
      point = k % len;
    }
    currentNode = head;
    int newLen = 1;
    ListNode result = null;
    while (currentNode != null) {
      if (newLen == len - point) {
        result = currentNode.next;
        currentNode.next = null;
        ListNode tempResult = result;
        while (true) {
          if (tempResult.next == null) {
            tempResult.next = head;
            break;
          }
          tempResult = tempResult.next;
        }
        break;
      }
      currentNode = currentNode.next;
      newLen++;
    }
    return result;
  }

  public int uniquePaths(int m, int n) {
    int[][] board = new int[m][n];
    return countPath(0, 0, m, n, board);
  }

  public int countPath(int i, int j, int m, int n, int[][] board) {
    if (i >= m || j >= n) {
      return 0;
    }
    if (i == m - 1 && j == n - 1) {
      return 1;
    }
    if (board[i][j] != 0) {
      return board[i][j];
    }
    board[i][j] = countPath(i + 1, j, m, n, board) + countPath(i, j + 1, m, n, board);
    return board[i][j];
  }

  public int uniquePathsWithObstaclesSoSlow(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[][] board = new int[m][n];
    return countPathWithObstacles(0, 0, m, n, board, obstacleGrid);
  }

  public int countPathWithObstacles(int i, int j, int m, int n, int[][] board, int[][] sourceBoard) {
    if (i >= m || j >= n || sourceBoard[i][j] == 1) {
      return 0;
    }
    if (i == m - 1 && j == n - 1) {
      return 1;
    }
    if (board[i][j] != 0) {
      return board[i][j];
    }
    board[i][j] = countPathWithObstacles(i + 1, j, m, n, board, sourceBoard)
        + countPathWithObstacles(i, j + 1, m, n, board, sourceBoard);
    return board[i][j];
  }

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int rowLen = obstacleGrid[0].length;
    int[] dp = new int[rowLen];
    dp[0] = 1;
    for (int[] row : obstacleGrid) {
      for (int i = 0; i < row.length; i++) {
        if (row[i] == 1) {
          dp[i] = 0;
        } else if (i > 0) {
          dp[i] += dp[i - 1];
        }
      }
    }
    return dp[rowLen - 1];
  }

  public int minPathSum(int[][] grid) {
    int width = grid[0].length;
    int[] dp = new int[width];
    dp[0] = grid[0][0];
    for (int y = 0; y < grid.length; y++) {
      int[] row = grid[y];
      for (int i = 0; i < row.length; i++) {
        if (y == 0 && i > 0) {
          dp[i] = row[i] + dp[i - 1];
        } else if (i == 0 && y > 0) {
          dp[i] = row[i] + dp[i];
        } else if (i > 0 && y > 0) {
          dp[i] = Math.min(row[i] + dp[i - 1], row[i] + dp[i]);
        }
      }
    }
    return dp[width - 1];
  }

  public boolean isNumber(String s) {
    s = s.trim();
    int state = 0;
    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case '+':
        case '-':
          if (state == 0) {
            state = 1;
          } else if (state == 4) {
            state = 6;
          } else {
            return false;
          }
          break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          if (state == 0 || state == 1 || state == 2) {
            state = 2;
          } else if (state == 7 || state == 8) {
            state = 8;
          } else if (state == 3) {
            state = 3;
          } else if (state == 4 || state == 5 || state == 6) {
            state = 5;
          } else {
            return false;
          }
          break;
        case '.':
          if (state == 0 || state == 1) {
            state = 7;
          } else if (state == 2) {
            state = 3;
          } else {
            return false;
          }
          break;
        case 'e':
          if (state == 2 || state == 3 || state == 8) {
            state = 4;
          } else {
            return false;
          }
          break;
        default:
          return false;

      }
    }
    return state == 2 || state == 5 || state == 3 || state == 8;
  }

  public int[] plusOne(int[] digits) {
    for (int i = digits.length - 1; i >= 0; i--) {
      if (++digits[i] > 9) {
        digits[i] = 0;
      } else {
        return digits;
      }
      ;
    }
    int[] result = new int[digits.length + 1];
    result[0] = 1;
    return result;
  }

  public String addBinarySoSlow(String a, String b) {
    int i = a.length() - 1;
    int y = b.length() - 1;
    String result = "";
    boolean flag = false;
    while (i >= 0 || y >= 0) {
      Character aChar = i >= 0 ? a.charAt(i) : '0';
      Character bChar = y >= 0 ? b.charAt(y) : '0';
      if (aChar == '1' && bChar == '1') {
        result = (flag ? '1' : '0') + result;
        flag = true;
      } else if (aChar == '0' && bChar == '0') {
        result = (flag ? '1' : '0') + result;
        flag = false;
      } else {
        result = (flag ? '0' : '1') + result;
      }
      i--;
      y--;
    }
    if (flag) {
      result = '1' + result;
    }
    return result;
  }

  public String addBinary(String a, String b) {
    int i = a.length() - 1;
    int y = b.length() - 1;
    StringBuilder sb = new StringBuilder();
    boolean flag = false;
    while (i >= 0 || y >= 0) {
      Character aChar = i >= 0 ? a.charAt(i) : '0';
      Character bChar = y >= 0 ? b.charAt(y) : '0';
      if (aChar == '1' && bChar == '1') {
        sb.append(flag ? '1' : '0');
        flag = true;
      } else if (aChar == '0' && bChar == '0') {
        sb.append(flag ? '1' : '0');
        flag = false;
      } else {
        sb.append(flag ? '0' : '1');
      }
      i--;
      y--;
    }
    if (flag) {
      sb.append('1');
    }
    return sb.reverse().toString();
  }

  public int mySqrt(int x) {
    int left = 2;
    int right = x / 2;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (Math.pow(mid, 2) <= x && Math.pow(mid + 1, 2) > x) {
        return mid;
      }
      if (Math.pow(mid, 2) > x) {
        right = left + (right - left) / 2;
      }
      if (Math.pow(mid, 2) < x) {
        left = left + (right - left) / 2;
      }
    }
    return left - 1;
  }

  public int climbStairs(int n) {
    if (n <= 3) {
      return n;
    }
    int[] dp = new int[n];
    dp[0] = 1;
    dp[1] = 2;
    for (int i = 2; i < n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n - 1];
  }

  public String simplifyPath(String path) {
    if (path.length() == 0) {
      return path;
    }
    ArrayList<String> tempPath = new ArrayList<>();
    String[] paths = path.split("/");
    for (int i = 0; i < paths.length; i++) {
      String p = paths[i];
      if (p.equals(".") || p.equals("")) {
        continue;
      } else if (p.equals("..")) {
        if (tempPath.size() > 0) {
          tempPath.remove(tempPath.size() - 1);
          continue;
        } else {
          continue;
        }
      } else {
        tempPath.add(p);
      }
    }
    return "/" + String.join("/", tempPath);
  }

  public int minDistance(String word1, String word2) {
    if (word1 == null && word2 != null) {
      return word2.length();
    }
    if (word1 != null && word2 == null) {
      return word1.length();
    }
    if (word1 == null && word2 == null) {
      return 0;
    }
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i <= word1.length(); i++) {
      dp[i][0] = i;
    }
    for (int y = 0; y <= word2.length(); y++) {
      dp[0][y] = y;
    }
    for (int i = 1; i <= word1.length(); i++) {
      for (int y = 1; y <= word2.length(); y++) {
        int delete = Math.min(dp[i][y - 1], dp[i - 1][y]) + 1;
        int replace = dp[i - 1][y - 1];
        if (word1.charAt(i - 1) != word2.charAt(y - 1)) {
          replace++;
        }
        dp[i][y] = Math.min(delete, replace);
      }
    }
    return dp[word1.length()][word2.length()];
  }

  public void setZeroes(int[][] matrix) {
    Set<Integer> rowFlag = new HashSet<>();
    Set<Integer> colFlag = new HashSet<>();
    int colLen = matrix[0].length;
    int rowLen = matrix.length;
    for (int i = 0; i < rowLen; i++) {
      for (int y = 0; y < colLen; y++) {
        if (matrix[i][y] == 0) {
          rowFlag.add(i);
          colFlag.add(y);
        }
      }
    }
    for (int row : rowFlag) {
      for (int i = 0; i < colLen; i++) {
        matrix[row][i] = 0;
      }
    }
    for (int col : colFlag) {
      for (int i = 0; i < rowLen; i++) {
        matrix[i][col] = 0;
      }
    }
  }

  public boolean searchMatrix(int[][] matrix, int target) {
    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i].length == 0) {
        continue;
      }
      if (matrix[i][0] <= target && matrix[i][matrix[i].length - 1] >= target) {
        return findTargetInArray(matrix[i], target);
      }
    }
    return false;
  }

  public boolean findTargetInArray(int[] arr, int target) {
    if (arr.length < 1) {
      return false;
    }
    int left = 0;
    int right = arr.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] == target) {
        return true;
      }
      if (arr[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return false;
  }

  public void sortColorsSoBad(int[] nums) {
    if (nums.length <= 1) {
      return;
    }
    int flag = 0;
    int left = 0;
    int right = 1;
    while (flag < 3 && left < nums.length - 1) {
      int leftValue = nums[left];
      if (leftValue == flag) {
        left++;
        right = left + 1;
        continue;
      }
      int rightValue = nums[right];
      if (rightValue == flag) {
        nums[right] = nums[left];
        nums[left] = flag;
        left++;
      }
      right++;
      if (right >= nums.length) {
        flag++;
        right = left + 1;
      }
    }
  }

  public void sortColors(int[] nums) {
    if (nums.length <= 1) {
      return;
    }
    int zero = 0, one = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0)
        zero++;
      if (nums[i] == 1)
        one++;
    }
    for (int i = 0; i < nums.length; i++) {
      if (i < zero)
        nums[i] = 0;
      else if (i < zero + one)
        nums[i] = 1;
      else
        nums[i] = 2;
    }
  }

  public String minWindowSoSlow(String s, String t) {
    if (s == null || t == null || s.length() < t.length()) {
      return "";
    }
    Set<Character> tcl = new HashSet<>();
    for (char c : t.toCharArray()) {
      tcl.add(c);
    }
    String result = "";
    for (int i = 0; i <= s.length() - t.length(); i++) {
      char currentChar = s.charAt(i);
      if (tcl.contains(currentChar)) {
        String children = findChildren(s.substring(i), t);
        if (children.length() != 0) {
          if (result.equals("") || children.length() < result.length()) {
            result = children;
          }
        }
      }
    }
    return result;
  }

  public String findChildren(String s, String t) {
    Map<Character, Integer> tcl = new HashMap<>();
    for (char c : t.toCharArray()) {
      if (tcl.containsKey(c)) {
        tcl.put(c, tcl.get(c) + 1);
      } else {
        tcl.put(c, 1);
      }
    }
    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      if (tcl.containsKey(currentChar)) {
        if (tcl.get(currentChar) == 1) {
          tcl.remove(currentChar);
        } else {
          tcl.put(currentChar, tcl.get(currentChar) - 1);
        }
        if (tcl.size() == 0) {
          return i == s.length() - 1 ? s.substring(0) : s.substring(0, i + 1);
        }
      }
    }
    return "";
  }

  public String minWindow(String s, String t) {
    if (s == null || t == null || s.length() < t.length()) {
      return "";
    }
    int[] tCharNum = new int[256];
    Set<Integer> matchSet = new HashSet<>();
    for (int i = 0; i < t.length(); i++) {
      int charCode = t.charAt(i);
      tCharNum[charCode]++;
      matchSet.add(charCode);
    }
    int left = 0;
    int[] sCharNum = new int[256];
    String window = "";
    for (int i = 0; i < s.length(); i++) {
      int charCode = s.charAt(i);
      sCharNum[charCode]++;
      if (tCharNum[charCode] == sCharNum[charCode]) {
        matchSet.remove(charCode);
      }
      if (matchSet.isEmpty()) {
        for (int j = left; j < s.length(); j++) {
          int tempCharCode = s.charAt(j);
          sCharNum[tempCharCode]--;
          if (sCharNum[tempCharCode] < tCharNum[tempCharCode]) {
            if (window.length() == 0 || window.length() > (i - j + 1)) {
              window = s.substring(j, i + 1);
            }
            matchSet.add(tempCharCode);
            left = j + 1;
            break;
          }
        }
      }
    }
    return window;
  }

  public List<List<Integer>> combineSoSlow(int n, int k) {
    if (k > n) {
      return new ArrayList<>();
    }
    List<List<Integer>>[][] dp = new List[k][n];
    for (int i = 0; i < n; i++) {
      List<List<Integer>> temp = new ArrayList<>();
      for (int y = 1; y <= i + 1; y++) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(y);
        temp.add(list);
      }
      dp[0][i] = temp;
    }
    for (int i = 1; i < k; i++) {
      for (int y = i; y < n; y++) {
        List<List<Integer>> temp = new ArrayList<>();
        for (List<Integer> item : dp[i - 1][y - 1]) {
          ArrayList<Integer> list = new ArrayList<Integer>(item);
          list.add(y + 1);
          temp.add(list);
        }
        if (y > i) {
          temp.addAll(dp[i][y - 1]);
        }
        dp[i][y] = temp;
      }
    }
    return dp[k - 1][n - 1];
  }

  public List<List<Integer>> combine(int n, int k) {
    if (k > n) {
      return new ArrayList<>();
    }
    List<List<Integer>> result = new ArrayList<>();
    combineImpl(n, k, 1, result, new ArrayList<>());
    return result;
  }

  private void combineImpl(int n, int k, int currentN, List<List<Integer>> result, List<Integer> currentList) {
    if (currentList.size() == k) {
      result.add(new ArrayList<>(currentList));
      return;
    }
    for (int i = currentN; i <= n - (k - currentList.size()) + 1; i++) {
      currentList.add(i);
      combineImpl(n, k, i + 1, result, currentList);
      currentList.remove(currentList.size() - 1);
    }
  }

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    result.add(new ArrayList<>());
    subsetsImpl(nums, 0, result, new ArrayList<>());
    return result;
  }

  public void subsetsImpl(int[] nums, int currentN, List<List<Integer>> result, ArrayList<Integer> currentList) {
    for (int i = currentN; i < nums.length; i++) {
      currentList.add(nums[i]);
      result.add(new ArrayList<>(currentList));
      subsetsImpl(nums, currentN + 1, result, currentList);
      currentList.remove(currentList.size() - 1);
    }
  }

  public boolean exist(char[][] board, String word) {
    int[][] flag = new int[board.length][board[0].length];
    for (int y = 0; y < board.length; y++) {
      for (int x = 0; x < board[0].length; x++) {
        if (board[y][x] == word.charAt(0)) {
          if (word.length() == 1) {
            return true;
          } else {
            flag[y][x] = -1;
            if (existImpl(board, flag, word, x, y, 0)) {
              return true;
            }
            flag[y][x] = 0;
          }
        }
      }
    }
    return false;
  }

  private boolean existImpl(char[][] board, int[][] flag, String word, int x, int y, int index) {
    char currentChar = word.charAt(index + 1);
    boolean lastChar = index + 1 == word.length() - 1;
    for (int i = 0; i < 4; i++) {
      int nextX = 0, nextY = 0;
      if (i == 0) {
        nextX = x;
        nextY = y - 1;
        if (nextY < 0) {
          continue;
        }
      }
      if (i == 1) {
        nextX = x + 1;
        nextY = y;
        if (nextX >= board[0].length) {
          continue;
        }
      }
      if (i == 2) {
        nextX = x;
        nextY = y + 1;
        if (nextY >= board.length) {
          continue;
        }
      }
      if (i == 3) {
        nextX = x - 1;
        nextY = y;
        if (nextX < 0) {
          continue;
        }
      }
      if (board[nextY][nextX] == currentChar && flag[nextY][nextX] != -1) {
        flag[nextY][nextX] = -1;
        if (lastChar || existImpl(board, flag, word, nextX, nextY, index + 1)) {
          return true;
        }
        flag[nextY][nextX] = 0;
      }
    }
    return false;
  }

}

package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leetcode.entity.TreeNode;
// import com.leetcode.entity.QuadTreeNode.Node;
import com.leetcode.entity.NTreeNode.Node;
import com.leetcode.tool.Print;

public class AlgorithmController541to560 {

  public String reverseStr(String s, int k) {
    char[] sc = s.toCharArray();
    for (int i = 0; i < sc.length; i += 2 * k) {
      int end = Math.min(i + k - 1, sc.length - 1);
      reverse(sc, i, end);
    }
    return String.valueOf(sc);
  }

  public char[] reverse(char[] sc, int left, int right) {
    while (left < right) {
      char temp = sc[left];
      sc[left] = sc[right];
      sc[right] = temp;
      left++;
      right--;
    }
    return sc;
  }

  public int[][] updateMatrix(int[][] mat) {
    int rowMax = mat.length, colMax = mat[0].length;
    int[][] dp = new int[rowMax][colMax];
    int flag = (int) Math.pow(10, 7);
    for (int i = 0; i < rowMax; i++) {
      for (int j = 0; j < colMax; j++) {
        if (i == 0 && j == 0) {
          dp[i][j] = mat[i][j] == 0 ? 0 : flag;
          continue;
        }
        if (mat[i][j] == 0) {
          dp[i][j] = 0;
          continue;
        }
        if (i == 0) {
          dp[i][j] = dp[i][j - 1] + 1;
          continue;
        }
        if (j == 0) {
          dp[i][j] = dp[i - 1][j] + 1;
          continue;
        }
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
      }
    }
    for (int i = rowMax - 1; i >= 0; i--) {
      for (int j = colMax - 1; j >= 0; j--) {
        if (i == rowMax - 1 && j == colMax - 1) {
          continue;
        }
        if (mat[i][j] == 0) {
          continue;
        }
        if (i == rowMax - 1) {
          dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
          continue;
        }
        if (j == colMax - 1) {
          dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
          continue;
        }
        dp[i][j] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1]) + 1);
      }
    }
    return dp;
  }

  public int diameterOfBinaryTreeRes = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    return Math.max(diameterOfBinaryTreeHelp(root) - 1, diameterOfBinaryTreeRes);
  }

  public int diameterOfBinaryTreeHelp(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = diameterOfBinaryTreeHelp(root.left);
    int right = diameterOfBinaryTreeHelp(root.right);
    diameterOfBinaryTreeRes = Math.max(diameterOfBinaryTreeRes, left + right);
    return Math.max(left, right) + 1;
  }

  public String findContestMatch(int n) {
    String[] teams = new String[n];
    for (int i = 0; i < n; i++) {
      teams[i] = Integer.toString(i + 1);
    }
    for (; n > 1; n /= 2) {
      for (int i = 0; i < n; i++) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(teams[i]);
        sb.append(',');
        sb.append(teams[n - 1 - i]);
        sb.append(')');
        teams[i] = sb.toString();
      }
    }
    return teams[0];
  }

  public List<Integer> boundaryOfBinaryTreeRes;

  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    boundaryOfBinaryTreeRes = new ArrayList<>();
    boundaryOfBinaryTreeRes.add(root.val);
    boundaryOfBinaryTreeLeft(root.left);
    if (root.left != null || root.right != null) {
      boundaryOfBinaryTreeBottom(root);
    }
    boundaryOfBinaryTreeRight(root.right);
    return boundaryOfBinaryTreeRes;
  }

  public void boundaryOfBinaryTreeLeft(TreeNode root) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      return;
    }
    if (root.left != null) {
      boundaryOfBinaryTreeRes.add(root.val);
      boundaryOfBinaryTreeLeft(root.left);
    } else {
      boundaryOfBinaryTreeRes.add(root.val);
      boundaryOfBinaryTreeLeft(root.right);
    }
  }

  public void boundaryOfBinaryTreeBottom(TreeNode root) {
    if (root == null) {
      return;
    }
    boundaryOfBinaryTreeBottom(root.left);
    if (root.left == null && root.right == null) {
      boundaryOfBinaryTreeRes.add(root.val);
    }
    boundaryOfBinaryTreeBottom(root.right);
  }

  public void boundaryOfBinaryTreeRight(TreeNode root) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      return;
    }
    if (root.right != null) {
      boundaryOfBinaryTreeRight(root.right);
      boundaryOfBinaryTreeRes.add(root.val);
    } else {
      boundaryOfBinaryTreeRight(root.left);
      boundaryOfBinaryTreeRes.add(root.val);
    }
  }

  public Map<String, Integer> removeBoxesMap;

  public String turnIntArrayToString(int[] boxes) {
    StringBuilder sb = new StringBuilder();
    for (int n : boxes) {
      sb.append(n);
      sb.append(',');
    }
    return sb.toString();
  }

  public int removeBoxes(int[] boxes) {
    removeBoxesMap = new HashMap<>();
    return removeBoxesHelp(boxes);
  }

  public int removeBoxesHelp(int[] boxes) {
    if (boxes.length == 0) {
      return 0;
    }
    String key = turnIntArrayToString(boxes);
    if (removeBoxesMap.containsKey(key)) {
      return removeBoxesMap.get(key);
    }
    int max = 0;
    for (int i = 0; i < boxes.length; i++) {
      int index = i;
      while (i < boxes.length && boxes[index] == boxes[i]) {
        i++;
      }
      int count = i - index;
      int temp = count * count;
      int[] newBoxes = new int[boxes.length - count];
      for (int j = 0; j < boxes.length; j++) {
        if (j < index) {
          newBoxes[j] = boxes[j];
        } else if (j >= i) {
          newBoxes[j - count] = boxes[j];
        }
      }
      max = Math.max(max, removeBoxesHelp(newBoxes) + temp);
      i--;
    }
    removeBoxesMap.put(key, max);
    return max;
  }

  public int removeBoxes2(int[] boxes) {
    int len = boxes.length;
    int[][][] dp = new int[len][len][len];
    return removeBoxesHelp2(boxes, dp, 0, len - 1, 0);
  }

  public int removeBoxesHelp2(int[] boxes, int[][][] dp, int l, int r, int k) {
    if (l > r) {
      return 0;
    }
    if (dp[l][r][k] == 0) {
      int realR = r, realK = k;
      while (realR > l && boxes[realR] == boxes[realR - 1]) {
        realR--;
        realK++;
      }
      dp[l][r][k] = removeBoxesHelp2(boxes, dp, l, realR - 1, 0) + (realK + 1) * (realK + 1);
      for (int i = l; i < r; i++) {
        if (boxes[i] == boxes[r]) {
          dp[l][r][k] = Math.max(dp[l][r][k],
              removeBoxesHelp2(boxes, dp, l, i, k + 1) + removeBoxesHelp2(boxes, dp, i + 1, r - 1, 0));
        }
      }
    }

    return dp[l][r][k];
  }

  public int findCircleNum(int[][] isConnected) {
    int len = isConnected.length, res = 0;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (isConnected[i][j] == 1) {
          res++;
          findCircleNumDfs(isConnected, i);
        }
      }
    }
    return res;
  }

  public void findCircleNumDfs(int[][] isConnected, int target) {
    int len = isConnected.length;
    for (int i = 0; i < len; i++) {
      if (isConnected[target][i] == 1) {
        isConnected[target][i] = 0;
        isConnected[i][target] = 0;
        if (target != i) {
          findCircleNumDfs(isConnected, i);
        }
      }
    }
  }

  public int findCircleNum2(int[][] isConnected) {
    UnionFind uf = new UnionFind(isConnected.length);
    int len = isConnected.length;
    for (int i = 0; i < len; i++) {
      for (int j = i + 1; j < len; j++) {
        if (isConnected[i][j] == 1) {
          uf.merge(i, j);
        }
      }
    }
    return uf.findRootNum();
  }

  public class UnionFind {

    public int[] parent;

    public UnionFind(int n) {
      parent = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    public void merge(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX != rootY) {
        parent[rootX] = rootY;
      }
    }

    public int find(int x) {
      if (parent[x] == x) {
        return x;
      }
      int temp = find(parent[x]);
      parent[x] = temp;
      return temp;
    }

    public int findRootNum() {
      int res = 0;
      for (int i = 0; i < parent.length; i++) {
        if (parent[i] == i) {
          res++;
        }
      }
      return res;
    }

  }

  public int findCircleNum3(int[][] isConnected) {
    int len = isConnected.length, res = 0;
    boolean[] visited = new boolean[len];
    for (int i = 0; i < len; i++) {
      if (!visited[i]) {
        res++;
        findCircleNum3dfs(isConnected, i, visited);
      }
    }

    return res;
  }

  public void findCircleNum3dfs(int[][] isConnected, int target, boolean[] visited) {
    int len = isConnected.length;
    if (visited[target]) {
      return;
    }
    visited[target] = true;
    for (int i = 0; i < len; i++) {
      if (isConnected[target][i] == 1) {
        findCircleNum3dfs(isConnected, i, visited);
      }
    }
  }

  public boolean splitArray(int[] nums) {
    int len = nums.length;
    if (len <= 6) {
      return false;
    }
    int[] preSum = new int[len];
    preSum[0] = nums[0];
    for (int i = 1; i < len; i++) {
      preSum[i] = nums[i] + preSum[i - 1];
    }
    for (int i = 3; i < nums.length - 3; i++) {
      HashSet<Integer> set = new HashSet<>();
      for (int j = 1; j < i - 1; j++) {
        if (preSum[j - 1] == preSum[i - 1] - preSum[j]) {
          set.add(preSum[j - 1]);
        }
      }
      for (int j = i + 2; j < nums.length - 1; j++) {
        if (preSum[nums.length - 1] - preSum[j] == preSum[j - 1] - preSum[i]
            && set.contains(preSum[j - 1] - preSum[i])) {
          return true;
        }
      }
    }
    return false;
  }

  public int longestConsecutiveRes = 0;

  public int longestConsecutive(TreeNode root) {
    if (root == null) {
      return longestConsecutiveRes;
    }
    int leftBig = longestConsecutive(root.left, root.val, 0, true);
    int leftSmall = longestConsecutive(root.left, root.val, 0, false);

    int rightBig = longestConsecutive(root.right, root.val, 0, true);
    int rightSmall = longestConsecutive(root.right, root.val, 0, false);

    int temp1 = leftBig + rightSmall + 1;
    int temp2 = leftSmall + rightBig + 1;
    longestConsecutiveRes = Math.max(Math.max(temp1, temp2), longestConsecutiveRes);
    longestConsecutive(root.left);
    longestConsecutive(root.right);
    return longestConsecutiveRes;
  }

  public int longestConsecutive(TreeNode root, int parentNum, int currentMax, boolean isBig) {
    if (root == null) {
      return currentMax;
    }
    int target = parentNum + (isBig ? 1 : -1);
    if (root.val == target) {
      currentMax += 1;
      return Math.max(longestConsecutive(root.left, root.val, currentMax, isBig),
          longestConsecutive(root.right, root.val, currentMax, isBig));
    }
    return currentMax;
  }

  public int longestConsecutiveII(TreeNode root) {
    longestConsecutiveIIHelp(root);
    return longestConsecutiveRes;
  }

  public int[] longestConsecutiveIIHelp(TreeNode root) {
    if (root == null) {
      return new int[] { 0, 0 };
    }
    int big = 1, small = 1;
    if (root.left != null) {
      int[] leftArr = longestConsecutiveIIHelp(root.left);
      if (root.val == root.left.val + 1) {
        big = leftArr[0] + 1;
      } else if (root.val == root.left.val - 1) {
        small = leftArr[1] + 1;
      }
    }
    if (root.right != null) {
      int[] rightArr = longestConsecutiveIIHelp(root.right);
      if (root.val == root.right.val + 1) {
        big = Math.max(big, rightArr[0] + 1);
      } else if (root.val == root.right.val - 1) {
        small = Math.max(small, rightArr[1] + 1);
      }
    }
    longestConsecutiveRes = Math.max(longestConsecutiveRes, big + small - 1);
    return new int[] { big, small };
  }

  public boolean checkRecord(String s) {
    char[] ac = s.toCharArray();
    int aNum = 0, lNum = 0;
    for (int i = 0; i < ac.length; i++) {
      if (ac[i] == 'A') {
        aNum++;
      }
      if (ac[i] == 'L') {
        lNum++;
      } else {
        lNum = 0;
      }
      if (aNum >= 2 || lNum >= 3) {
        return false;
      }
    }

    return true;
  }

  public int checkRecord(int n) {
    int[][][] dp = new int[n + 1][2][3];
    dp[0][0][0] = 1;
    int mod = (int) Math.pow(10, 9) + 7;
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= 1; j++) {
        for (int k = 0; k <= 2; k++) {
          dp[i][j][0] = (dp[i - 1][j][k] + dp[i][j][0]) % mod;
        }
      }

      for (int k = 0; k <= 2; k++) {
        dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][k]) % mod;
      }

      for (int j = 0; j <= 1; j++) {
        for (int k = 1; k <= 2; k++) {
          dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % mod;
        }
      }
    }

    int sum = 0;
    for (int i = 0; i <= 1; i++) {
      for (int j = 0; j <= 2; j++) {
        sum = (sum + dp[n][i][j]) % mod;
      }
    }
    return sum;
  }

  public int checkRecordII(int n) {
    long[][] dp = new long[2][3];
    dp[0][0] = 1;
    dp[1][0] = 1;
    dp[0][1] = 1;
    int mod = (int) Math.pow(10, 9) + 7;
    for (int i = 1; i < n; i++) {
      long old00 = dp[0][0];
      long old01 = dp[0][1];
      long old02 = dp[0][2];
      long old10 = dp[1][0];
      long old11 = dp[1][1];
      long old12 = dp[1][2];

      dp[0][0] = (old00 + old01 + old02) % mod;
      dp[0][1] = old00;
      dp[0][2] = old01;
      dp[1][0] = (old00 + old01 + old02 + old10 + old11 + old12) % mod;
      dp[1][1] = old10;
      dp[1][2] = old11;
    }

    long sum = 0;
    for (int i = 0; i <= 1; i++) {
      for (int j = 0; j <= 2; j++) {
        sum = (sum + dp[i][j]) % mod;
      }
    }
    return (int) sum;
  }

  public String optimalDivision(int[] nums) {
    StringBuilder sb = new StringBuilder();
    sb.append(nums[0]);
    if (nums.length > 1) {
      sb.append('/');
    }
    if (nums.length > 2) {
      sb.append('(');
    }
    for (int i = 1; i < nums.length; i++) {
      sb.append(Integer.toString(nums[i]));
      if (i < nums.length - 1) {
        sb.append('/');
      }
    }
    if (nums.length > 2) {
      sb.append(')');
    }
    return sb.toString();
  }

  public int leastBricks(List<List<Integer>> wall) {
    int x = wall.size();
    int y = 0;
    for (int i = 0; i < wall.get(0).size(); i++) {
      y += wall.get(0).get(i);
    }
    if (y == 1) {
      return x;
    }
    int[] preWidth = new int[x];
    int[] preWidthIndex = new int[x];
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < x; i++) {
      preWidth[i] = wall.get(i).get(0);
    }
    for (int i = 1; i < y; i++) {
      int currentMin = 0;
      for (int j = 0; j < x; j++) {
        if (i < preWidth[j]) {
          currentMin += 1;
        } else if (i == preWidth[j] && preWidthIndex[j] < wall.get(j).size() - 1) {
          preWidthIndex[j] += 1;
          preWidth[j] += wall.get(j).get(preWidthIndex[j]);
        }
      }
      min = Math.min(min, currentMin);
    }
    return min;

  }

  public int leastBricks2(List<List<Integer>> wall) {
    int x = wall.size();
    List<Set<Integer>> list = new ArrayList<>();
    Set<Integer> shouldTry = new HashSet<>();
    int wallWidth = 0;
    for (int i = 0; i < x; i++) {
      Set<Integer> set = new HashSet<>();
      int preWidth = 0;
      for (int j = 0; j < wall.get(i).size(); j++) {
        int current = wall.get(i).get(j);
        preWidth += current;
        shouldTry.add(preWidth);
        set.add(preWidth);
      }
      wallWidth = preWidth;
      list.add(set);
    }
    int min = x;
    for (int i : shouldTry) {
      if (i == wallWidth) {
        continue;
      }
      int current = 0;
      for (Set<Integer> set : list) {
        if (set.contains(i)) {
          continue;
        }
        current++;
      }
      min = Math.min(min, current);
    }
    return min;
  }

  public int leastBricks3(List<List<Integer>> wall) {
    Map<Integer, Integer> map = new HashMap<>();
    int x = wall.size();
    int max = 0;
    for (int i = 0; i < x; i++) {
      int preWidth = 0;
      for (int j = 0; j < wall.get(i).size() - 1; j++) {
        int current = wall.get(i).get(j);
        preWidth += current;
        int temp = map.getOrDefault(preWidth, 0) + 1;
        max = Math.max(max, temp);
        map.put(preWidth, temp);
      }
    }
    return x - max;
  }

  public int nextGreaterElement(int n) {
    int length = 0;
    int temp = n;
    while (temp != 0) {
      length++;
      temp /= 10;
    }
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = n % 10;
      n /= 10;
    }
    int targetIndex = length + 1;
    int sourceIndex = length + 1;
    for (int i = 0; i < length; i++) {
      if (i > targetIndex) {
        break;
      }
      for (int j = i + 1; j < length; j++) {
        if (j > targetIndex) {
          break;
        }
        if (arr[i] > arr[j]) {
          if (targetIndex == j && arr[sourceIndex] < arr[i]) {
            break;
          }
          targetIndex = j;
          sourceIndex = i;
          break;
        }
      }
    }
    if (targetIndex == length + 1) {
      return -1;
    }
    int[] nums = new int[10];
    int t = arr[targetIndex];
    arr[targetIndex] = arr[sourceIndex];
    arr[sourceIndex] = t;
    for (int i = 0; i < targetIndex; i++) {
      nums[arr[i]] += 1;
    }
    int index = 0;
    for (int i = nums.length - 1; i >= 0; i--) {
      if (nums[i] == 0) {
        continue;
      }
      while (nums[i] != 0) {
        arr[index++] = i;
        nums[i]--;
      }
    }
    long result = 0;
    for (int i = length - 1; i >= 0; i--) {
      result = result * 10 + arr[i];
    }
    if (result > Integer.MAX_VALUE) {
      return -1;
    }
    return (int) result;
  }

  public String reverseWords(String s) {
    char[] chars = s.toCharArray();
    int start = 0;
    int end = 0;
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == ' ') {
        end = i - 1;
        reverse(chars, start, end);
        start = i + 1;
      }
    }
    reverse(chars, start, chars.length - 1);
    return new String(chars);
  }

  // public Node intersect(Node quadTree1, Node quadTree2) {
  // Node result = new Node();
  // if (quadTree1.isLeaf && quadTree2.isLeaf) {
  // result.val = quadTree1.val || quadTree2.val;
  // result.isLeaf = true;
  // return result;
  // }
  // result.isLeaf = false;
  // Node topLeft1 = quadTree1.topLeft;
  // if (topLeft1 == null) {
  // topLeft1 = quadTree1;
  // }
  // Node topLeft2 = quadTree2.topLeft;
  // if (topLeft2 == null) {
  // topLeft2 = quadTree2;
  // }
  // result.topLeft = intersect(topLeft1, topLeft2);
  // Node topRight1 = quadTree1.topRight;
  // if (topRight1 == null) {
  // topRight1 = quadTree1;
  // }
  // Node topRight2 = quadTree2.topRight;
  // if (topRight2 == null) {
  // topRight2 = quadTree2;
  // }
  // result.topRight = intersect(topRight1, topRight2);
  // Node bottomLeft1 = quadTree1.bottomLeft;
  // if (bottomLeft1 == null) {
  // bottomLeft1 = quadTree1;
  // }
  // Node bottomLeft2 = quadTree2.bottomLeft;
  // if (bottomLeft2 == null) {
  // bottomLeft2 = quadTree2;
  // }
  // result.bottomLeft = intersect(bottomLeft1, bottomLeft2);
  // Node bottomRight1 = quadTree1.bottomRight;
  // if (bottomRight1 == null) {
  // bottomRight1 = quadTree1;
  // }
  // Node bottomRight2 = quadTree2.bottomRight;
  // if (bottomRight2 == null) {
  // bottomRight2 = quadTree2;
  // }
  // result.bottomRight = intersect(bottomRight1, bottomRight2);
  // if (result.topLeft.isLeaf && result.topRight.isLeaf &&
  // result.bottomLeft.isLeaf && result.bottomRight.isLeaf
  // && result.topLeft.val == result.topRight.val && result.topLeft.val ==
  // result.bottomLeft.val
  // && result.topLeft.val == result.bottomRight.val) {
  // result.val = result.topLeft.val;
  // result.isLeaf = true;
  // result.topLeft = null;
  // result.topRight = null;
  // result.bottomLeft = null;
  // result.bottomRight = null;
  // }
  // return result;
  // }

  int max = 0;

  public void maxDepth(Node root, int current) {
    if (root == null) {
      return;
    }
    if (current > max) {
      max = current;
    }
    for (Node node : root.children) {
      maxDepth(node, current + 1);
    }
  }

  public int maxDepth(Node root) {
    maxDepth(root, 1);
    return this.max;
  }

  public int subarraySum(int[] nums, int k) {
    int n = nums.length;
    int[][] sums = new int[n][n];
    for (int i = 0; i < n; i++) {
      sums[i][i] = nums[i];
    }
    for (int l = 0; l < n; l++) {
      for (int r = l + 1; r < n; r++) {
        sums[l][r] = sums[l][r - 1] + nums[r];
      }
    }
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if (sums[i][j] == k) {
          count++;
        }
      }
    }
    return count;
  }

  public int subarraySum2(int[] nums, int k) {
    int n = nums.length;
    int[] sums = new int[n];
    int count = 0;
    for (int l = 0; l < n; l++) {
      sums[l] = nums[l];
      if (nums[l] == k) {
        count++;
      }
      for (int r = l + 1; r < n; r++) {
        sums[r] = sums[r - 1] + nums[r];
        if (sums[r] == k) {
          count++;
        }
      }
    }
    return count;
  }

  public int subarraySum3(int[] nums, int k) {
    int count = 0;
    Map<Integer, Integer> map = new HashMap<>();
    int preSum = 0;
    for (int i = 0; i < nums.length; i++) {
      preSum += nums[i];
      if (preSum == k) {
        count++;
      }
      if (map.containsKey(preSum - k)) {
        count += map.get(preSum - k);
      }
      map.put(preSum, map.getOrDefault(preSum, 0) + 1);
    }
    return count;
  }

}

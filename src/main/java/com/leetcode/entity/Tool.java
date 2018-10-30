package com.leetcode.entity;

public class Tool {

    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int startIndex, int endIndex) {
        if (nums.length <= 1) {
            return;
        }
        if (startIndex >= endIndex) {
            return;
        }
        int key = nums[startIndex];
        int j = endIndex;
        int i = startIndex;
        while (j > i) {
            while (j > i && nums[j] > key) {
                j--;
            }
            while (j > i && nums[i] <= key) {
                i++;
            }
            if (j > i) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i];
        nums[i] = nums[startIndex];
        nums[startIndex] = temp;
        quickSort(nums, startIndex, i - 1);
        quickSort(nums, i + 1, endIndex);
    }

}

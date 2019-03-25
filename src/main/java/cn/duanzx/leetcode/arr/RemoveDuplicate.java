package cn.duanzx.leetcode.arr;


import java.util.Arrays;

public class RemoveDuplicate {
    public static void main(String[] args)throws Exception {
        int[] nums = new int[]{1};
        removeDuplicates(nums);
        for(int num:nums){
            System.out.println(num);
        }
    }

    public static int removeDuplicates(int[] nums) {
        int num = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) {
                int start = i;
                count++;
                while (start < nums.length - 1) {
                    nums[start] = nums[start + 1];
                    start++;
                }
                continue;
            }
            num = nums[i];
        }
        nums = Arrays.copyOf(nums, nums.length - count);
        return nums.length;
    }
}

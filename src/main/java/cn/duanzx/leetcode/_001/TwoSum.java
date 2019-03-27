package cn.duanzx.leetcode._001;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    private int[] arr = new int[]{2, 7, 11, 15};

    /**
     * 简单实现
     */
    @Test
    public void test() {
        int[] result = twoSum1(arr, 9);
        System.out.println(String.format("%d,%d", result[0], result[1]));
    }
    /**
     * 挨个遍历方法 , 假设数组长度为n
     * 0 ->  1,2,,3...... n-1
     * .
     * .
     * n-2 -> n-1
     * */
    public int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        for (int start = 0; start < n-1; start++) {
            int limit = start+1;
            while (limit < n) {
                if ((nums[start] + nums[limit]) == target) {
                    return new int[]{start, limit};
                }
                limit++;
            }
        }
        return new int[]{-1, -1};
    }
/**
 * 使用Hash表存储遍历到的数组元素 , key = 实际值 ， value = 角标值
 * 每次遍历到新元素的时候，就进行判断 arr[i] + map.get(target-arr[i]) == target
 * */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i<nums.length;i++){
            int other = target - nums[i];
            if(map.containsKey(other)){
                return new int[]{i,map.get(other)};
            }
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }


}

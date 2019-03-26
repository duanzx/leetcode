package cn.duanzx.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TwoSum001 {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
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

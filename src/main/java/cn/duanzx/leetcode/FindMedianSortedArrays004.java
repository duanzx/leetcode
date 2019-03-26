package cn.duanzx.leetcode;

import org.junit.jupiter.api.Test;

public class FindMedianSortedArrays004 {
    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * 示例 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * 则中位数是 2.0
     * 示例 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * 则中位数是 (2 + 3)/2 = 2.5
     * */
    /**
     * 分析：
     * 1.理解中位数的概念：又称中点数，中值。中位数是按照顺序排列的一组数据中居于中间位置的数，即在这组数据中，有一半的数据比他大，有一半的数据比他小
     * 2.举例：[1,2,3,4,5] 的中位数是3 ， [1,2,3,4]的中位数是 (float)（2+3）/2 = 2.5
     * 3.分析两个有序数组元素的排列情况：
     * [1,2,3,4,5],[6,7,8]
     * [1,2],[6,7,8]
     * [1,3,5],[2,4,6]
     * 4.求两个有序数组的中位数：
     * 忽略时间复杂度的话，可以将两个数组合并到一个大的数组中，然后对新的数组排序（用复杂度少的算法：插入，冒泡），然后根据数组的长度是奇数还是偶数进行选择
     * 如果是奇数7中位数 7/2+1 = 4 -> arr[3],如果是偶数6,中位数6/2=3, (float)(arr[2] + arr[3])/2
     */
    @Test
    public void test() {
        int[] nums1 = new int[]{1, 3, 5};
        int[] nums2 = new int[]{2, 4, 6, 8};
        findMedianSortedArrays1(nums1, nums2);
    }

    /**
     * 冒泡排序
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] arr = new int[m + n];
        for (int i = 0; i < m + n; i++) {
            if (i < m) {//0 -> m-1
                arr[i] = nums1[i];
                continue;
            }
            arr[i] = nums2[i - m];//m-1 -> m+n-1
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int j = i + 1;
            while (j < arr.length) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                j++;
            }
        }
        int mid = (m + n) / 2;
        if ((m + n) % 2 == 1) {
            return  arr[mid+1];
        }
        return (double) (arr[mid-1]+arr[mid])/2;
    }

    /**
     * 插入排序
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        return 0.0;
    }

    /**
     * 题解分析
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        return 0.0;
    }


}

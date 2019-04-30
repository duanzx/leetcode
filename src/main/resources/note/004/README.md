# 寻找两个有序数组的中位数
## 描述
```$xslt
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
你可以假设 nums1 和 nums2 不会同时为空。
示例 1:
nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:
nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
在
```
### 思路1 合并排序
     1.首先理解中位数的概念
        中位数又称中点数、中值，是按照顺序排列的一组数据中居于中间位置的数，即在这组数据中，
        有一半的数据比他大，有一半的数据比他小。
     2.如果数组长度是奇数，则中位数就是中间的数，如果长度是偶数，则是中间两个数的平均数。
        比如[1,2,3,4,5] 的中位数是3 ， [1,2,3,4]的中位数是 (float)（2+3）/2 = 2.5
     3.两个有序数组元素的排列有多种情况：
        [1,2,3,4,5],[6,7,8]
        [1,2],[6,7,8]
        [1,3,5],[2,4,6]
     4.忽略时间复杂度的话，可以将两个数组合并到一个大的数组中，然后对新的数组排序（用复杂度少的算法：插入，冒泡)
        然后再对排序后的大数组求中位数
```$xslt
//使用选择排序对合并后的数组进行排序
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] arr = new int[m + n];
        for (int i = 0; i < m + n; i++) {
            if (i < m) {
                arr[i] = nums1[i];
                continue;
            }
            arr[i] = nums2[i - m];
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
            return arr[mid];
        }
        return (double) (arr[mid - 1] + arr[mid]) / 2;
    }
```
```$xslt
//使用冒泡排序对合并后的数组进行排序
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] arr = new int[m + n];
        for (int i = 0; i < m + n; i++) {
            if (i < m) {
                arr[i] = nums1[i];
                continue;
            }
            arr[i] = nums2[i - m];
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        int mid = (m + n) / 2;
        if ((m + n) % 2 == 1) {
            return arr[mid];
        }
        return (double) (arr[mid - 1] + arr[mid]) / 2;
    }

```
```$xslt
//使用插入排序对合并后的数组进行排序
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] arr = new int[m + n];
        for (int i = 0; i < m + n; i++) {
            if (i < m) {
                arr[i] = nums1[i];
                continue;
            }
            arr[i] = nums2[i - m];
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                int j = i;
                int temp = arr[j];
                while (j > 0 && arr[j - 1] > temp) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = temp;
            }
        }
        int mid = (m + n) / 2;
        if ((m + n) % 2 == 1) {
            return arr[mid];
        }
        return (double) (arr[mid - 1] + arr[mid]) / 2;
    }
```
### 思路2 二分查找
    
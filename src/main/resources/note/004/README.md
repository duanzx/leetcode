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
### 思路2 根据中位数定义使用推导方程
     1.由于中位数是将一个集合划分为两个长度相等的子集，并且其中一个子集中的元素总是大于另一个子集中的元素
        假设中位数划分的两个数组分别为：A[m] , B[n], 合并后的数组为C[m+n] (这个是我们假想的合并后排序好的数组)
        假设中位数在数组A的i位置和数组B的j位置将C进行了等分
        此时A被分成了两部分left_A {A[0],A[1],...,A[i-1]} |  right_A {A[i],A[i+1],...,A[m-1]}；
        此时B被分成了两部分left_B {B[0],B[1],...,B[j-1]} |  right_B{B[j],B[j+1],...,B[n-1]}；
        根据定义：         left_C = left_A + left_B      |  right_C = right_A + right_B
        并且 left_C的长度和right_C的长度是相等的，并且left_C里的任意元素总是小于right_C里的任意元素
        所以有：
         length(left_C) == length(right_C) &&  max(left_C) <= min(right_C)
        length(left_A) + length(left_B) == length(right_A) + length(right_B); && (A[i-1] <= A[i] && A[i-1] <= B[j] && B[j-1] <= B[j] && B[j-1] <= A[i])
       (i-1-0+1) + (j-1-0+1) == (m-1-i+1) + (n-1-j+1) && (A[i-1] <= B[j] && B[j-1] <= A[i])
        i+j == (m+n)/2 && (A[i-1] <= B[j] && B[j-1] <= A[i])
        当 m+n 为奇数时候， 中位数就是C数组右半部分的最小数：min(right_C) = min(A[i],B[j])
        当 m+n 为偶数时候,  中位数就是C数组左半部分的最大值与C数组右半部分的最小值的平均数：(max(left_C) + min(right_C))/2.0
     2.观察C数组可知道：max(left_C) = max(A[i-1],B[j-1])；min(right_C) = min(A[i],B[j])
        只要能够确定 i 或j 的值就能得到要求的中位数
     3.接下来确认i的取值范围，
        i最小可以为0，此时left_A为空集，  left_A              | A[0],A[1],...,A[i-1]
        i最大可以为m,此时right_A为空集, A[0],A[1],...,A[i-1] |  right_A
     4.由于 j = (m+n)/2 -i , 并且j必须是大于等于0, 所有当i取最大值的时候，也要保证j大于等于0,也就是 (m+n)/2 - m >= 0 ,也就是 n >= m
     5.接下来遍历数组，并使用上述推导的条件对符合中位数的（i,j）进行判断
```$xslt
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (n < m) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        if (m == 0) {
            return n % 2 == 1 ? nums2[n / 2] : (nums2[n / 2] + nums2[n / 2 - 1]) / 2.0;
        }
        for (int i = 0; i <= m; i++) {
            int j = (m + n) / 2 - i;
            int leftA = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int rightA = i == m ? Integer.MAX_VALUE : nums1[i];
            int leftB = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int rightB = j == n ? Integer.MAX_VALUE : nums2[j];
//            A[i-1] <= B[j] && B[j-1] <= A[i]
            if (leftA <= rightB && leftB <= rightA) {
//                (max(left_C) + min(right_C))/2.0
                if ((m + n) % 2 == 0) {
                    return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
                }
//                min(right_C) = min(A[i],B[j])
                return Math.min(rightA, rightB);
            }
        }
        return 0.0;
    }
```
### 思路3 使用二分查找遍历数组,满足时间复杂度 O(log(m + n))
        1.现在回到时间复杂度O(log(m + n)) ，也就是说只有在两个数组里进行二分查找才可能满足条件
        2.首先确认二分查找的条件
          A[i-1] <= B[j] && B[j-1] <= A[i] ， 此时找到了目标对象i , 所以停止搜索
          B[j-1] > A[i] ， 这意味着A[i]太小，必须增大i，此时j会减小，B[j-1]会减小,A[i]会增大，则可能满足条件
          A[i-1] > B[j], 这意味着A[i-1]太大,必须减小i, 此时j会增大,B[j]会增大,A[i-1]会减小，则可能满足条件
```$xslt
    public double findMedianSortedArrays5(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (n < m) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        if (m == 0) {
            return n % 2 == 1 ? nums2[n / 2] : (nums2[n / 2] + nums2[n / 2 - 1]) / 2.0;
        }
        int low = 0;
        int high = m;
        while (low <= high){
            int i = (low + high) >> 1;
            int j = (m + n) / 2 - i;
            int leftA = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int rightA = i == m ? Integer.MAX_VALUE : nums1[i];
            int leftB = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int rightB = j == n ? Integer.MAX_VALUE : nums2[j];
//            A[i-1] <= B[j] && B[j-1] <= A[i]
            if(leftA <= rightB && leftB <= rightA){
                if ((m + n) % 2 == 0) {
                    return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
                }
                return Math.min(rightA, rightB);
            }
//            A[i-1] > B[j] , 说明i的值有点大，要缩小范围
            if(leftA > rightB){
                high = i-1;
            }
//            B[j-1] > A[i]，说明i的值有点小，要增大范围
            if(leftB > rightA){
                low = i+1;
            }
        }
        return 0.0;
    }
```
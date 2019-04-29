package cn.duanzx.leetcode._005;

import org.junit.Test;

public class LongestPalindrome {
    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 示例 1：
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * 输入: "cbbd"
     * 输出: "bb"
     */
    /**
     * 分析回文字符串的规律发现：首位字母必须是相同的才可以
     * 遍历字符串
     * 判断当前字符是否在字符串中重复出现
     * 如果没有出现：回文字符串是该元素本身
     * 如果出现一次,将出现的位置作为回文字符串的末尾元素，找出中间的元素，依次前后比较，
     * 如果全部比较通过，则确认字符串确定，如果有一个没有通过则放弃
     * 如果出现多次，将出现的位置存到数组里，遍历数组重复上面的动作，如果通过，则终止循环
     */

    @Test
    public void test1() {
        System.out.println(longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        String substring = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (checkLP(s, i, j)) {
                    substring = substring.length() > (j - i + 1) ? substring : s.substring(i, j + 1);
                    break;
                }
            }
        }
        return substring;
    }

    public String longestPalindrome1(String s) {
        String substring = "";
        int[] indexArr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char target = s.charAt(i);
            int count = 0;
            for (int j = s.length() - 1; j > i; j--) {
                if (s.charAt(j) == target) {
                    indexArr[count++] = j;
                }
            }
            if (count == 0) {
                substring = substring.length() > 1 ? substring : s.substring(i, i + 1);
                continue;
            }
            for (int x = 0; x < count; x++) {
                int limit = indexArr[x];
                if (checkLP(s, i, limit)) {
                    substring = substring.length() > (limit - i + 1) ? substring : s.substring(i, limit + 1);
                    break;
                }
            }
        }
        return substring;
    }

    private boolean checkLP(String s, int start, int limit) {
        int len = limit - start + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(start + i) != s.charAt(limit - i)) {
                return false;
            }
        }
        return true;
    }


}

package cn.duanzx.leetcode._003;

import org.junit.jupiter.api.Test;

public class LengthOfLongestSubstring {

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("abcabbc"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (null == s) {
            return 0;
        }
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            String substring = s.substring(i, i + 1);
            for (int j = i + 1; j < s.length(); j++) {
                String next = s.substring(j, j + 1);
                if (substring.indexOf(next) != -1) {
                    break;
                }
                substring += next;
            }
            maxLength = Math.max(substring.length(), maxLength);
        }
        return maxLength;
    }
}

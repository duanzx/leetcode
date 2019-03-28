package cn.duanzx.leetcode._003;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring {

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring3("abcaefagh12345"));
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

    public int lengthOfLongestSubstring3(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int i = 0, j = 0;
        int n = s.length();
        int max = 0;
        while (i < n && j < n) {
            if (!map.containsKey(s.charAt(j)) || i > map.get(s.charAt(j))) {
                map.put(s.charAt(j), j);
                max = Math.max(max, j - i + 1);
                j++;
            } else {
                i = map.get(s.charAt(j)) + 1;
            }
        }
        return max;
    }

    public int lengthOfLongestSubstring1(String s) {
        s = "anviaj";
        if (null == s) {
            return 0;
        }
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        int maxLength = 0;
        int lastIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            int j = (lastIndex > 0 && lastIndex > i) ? lastIndex + 1 : i + 1;
            String substring = s.substring(i, j);
            while (j < s.length()) {
                String next = s.substring(j, j + 1);
                if (substring.indexOf(next) != -1) {
                    break;
                }
                lastIndex = j;
                substring += next;
                j++;
            }
            maxLength = Math.max(maxLength, substring.length());
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<Character>();
        return 2;
    }
}

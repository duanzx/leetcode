package cn.duanzx.leetcode._003;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring {

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring4("abcaefagh12345"));
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

    public int lengthOfLongestSubstring1(String s) {
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
        if (null == s) {
            return 0;
        }
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        s = "tmmzuxt";
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int startIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            //abcabc -> bca
            if (!map.containsKey(cs) || startIndex > map.get(cs)) {
                map.put(cs, i);
                maxLength = Math.max(maxLength, i - startIndex + 1);
                continue;
            }
            startIndex = map.get(cs) + 1;
            map.put(cs, i);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring3(String s) {
        if (null == s) {
            return 0;
        }
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int startIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char mkey = s.charAt(i);
            int mindex = map.containsKey(mkey) ? map.get(mkey) : -1;
            if (mindex < 0 || startIndex > mindex) {
                map.put(mkey, i);
                maxLength = Math.max(maxLength, i - startIndex + 1);
                continue;
            }
            startIndex = mindex + 1;
            map.put(mkey, i);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring4(String s) {
        if (null == s) {
            return 0;
        }
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        int i = 0, j = 0;
        int maxLength = 0;
        Set<Character> set = new HashSet<Character>();
        while (i < s.length() && j < s.length()) {
            Character cs = s.charAt(j);
            if (!set.contains(cs)) {
                set.add(cs);
                maxLength = Math.max(maxLength, j - i + 1);
                j++;
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring5(String s) {
        if (null == s) {
            return 0;
        }
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int i = 0, j = 0;
        while (i < s.length() && j < s.length()) {
            Character cs = s.charAt(j);
            if (!map.containsKey(cs)) {
                map.put(cs, j);
                maxLength = Math.max(maxLength, j - i + 1);
                j++;
            } else {
                map.remove(s.charAt(i));
                i++;
            }
        }
        return maxLength;
    }
}

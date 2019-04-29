# 最长回文子串
## 描述
```
    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    示例 1：
    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
    示例 2：
    输入: "cbbd"
    输出: "bb"

```
## 思路1 暴力枚举
    1.首先理解什么是回文字符串，其实就是首尾相同的字符串，如：aba, aa, abba
    2.判断回文字符串的方法就是分别取首尾元素，判断二者是否相等。如果全部相同则就是回文字符串。
    3.使用暴力枚举法对将每个字符串可能存在的回文字符串进行判断
    4.将判断回文字符串的方法单独抽取出来，然后遍历字符串，将可能存在的回文字符串都使用该方法进行判断
    5.假设字符串是 abcdefg ,则可能的回文字符串就是
       a            
       ab           b       
       abc          bc      c       
       abcd         bcd     cd      d       
       abcde        bcde    cde     de      e   
       abcdef       bcdef   cdef    def     ef      f       
       abcdefg      bcdefg  cdefg   defg    efg     fg      g
    代码如下：
```$xslt
 public String longestPalindrome(String s) {
        String substring = "";
        for(int i = 0;i<s.length();i++){
            for(int j = i;j<s.length();j++){
                if(checkLP(s,i,j)){
                    substring = substring.length() > (j-i+1)?substring:s.substring(i,j+1);
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
```
### 思路2 查找记忆法
    1.根据回文字符串的定义，首部的字符和尾部的字符必须相等
    2.假设当前字符位置为i ,如果该字符下次出现的位置为j，则可以确定字符串[i,j]是一个回文字符串
    3.列举当前字符下次出现的位置有三种， 没有出现；出现一次；出现多次
    4.如果没有出现，则说明字符串[i,j]不是回文字符串，跳到下次循环
    5.如果出现一次，则对字符串[i,j]进行回文字符串判断
    6.如果出现多次，则对字符串[i,next1],[i,next2],[i,next3]分别进行回文字符串的判断
    7.题目的要求是求最大的回文字符串，则我们优先对[i,next1],[i,next2],[i,next3]中长度多的字符串进行判断，如果满足条件
    就不再对长度短的字符串进行判断。
    abcabcecbacba
    根据a的下次出现位置得到回文字符串
    abcabcecbacba
    abcabcecba
    abca
    如果第一个字符串满足条件，就不再对下面的字符串进行判断了
    代码如下：
```$xslt
    public String longestPalindrome(String s) {
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
```
    
    
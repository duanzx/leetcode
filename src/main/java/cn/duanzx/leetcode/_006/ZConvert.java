package cn.duanzx.leetcode._006;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ZConvert {
    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     * 请你实现这个将字符串进行指定行数变换的函数：
     * string convert(string s, int numRows);
     * 示例 1:
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 示例 2:
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     *
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     * */

    /**
     * 题目的关键在于理清结果字符串中各个字符的位置关系:
     * 当行数为3 时候：
     * 1        5       9       13      第一行 等差数列(d=4) 2 * 3-1-1
     * 2    4   6    8  10  12  14  16  第二行 等差数列(d=2)
     * 3        7       11      15      第三行 等差数列(d=4)
     * 当行数为4 时候：
     * 1            7           13  第一行 等差数列(d=6) 2*4-2
     * 2        6   8       12  14  第二行 数列（d=4,d=2）
     * 3    5       9   11      15  第三行 数列(d = 2,d=4)
     * 4            10          16  第四行 等差数列(d=6)
     * 当行数为 5 时候：
     * 1                9                   第一行 等差数列(d=8) 2*5-2
     * 2            8   10          16      第二行 数列(d=6,d=2)
     * 3        7       11      15          第三行 数列(d=4)
     * 4    6           12  14              第四行 数列 d=2,d=6
     * 5                13                  第五行 数列 d=8
     * 方案一：
     * 定义List集合分别存储每一行的字符在原字符串中的角标
     * 如果行数为3，则有：
     * List(0) {1,5,9,13}
     * List(1) {2,4,6,8,10,12,14,16}
     * List(2) {3,7,11,15}
     * 1.首先根据等差数列公式，求出第一行中所有的字符的位置，并存储到对应集合中
     * 2.然后遍历该集合，对集合中每一个字符做如下操作：
     * 3.设起点为当前字符位置，终点为同一行中下一个字符的位置（如果没有，取字符串的长度）
     * 4.从起点递增，并将字符位置存储到第二行，第三行的集合中
     * 5.如果起点增加的个数超过行数，则将后续递增的字符依次存储到第三行，第二行中
     * 6.然后遍历所有的集合，并根据集合中字符的值，从字符串中取出对应的字符，并拼接成字符串
     */

    @Test
    public void test() {
        System.out.println(convert1("PAYPALISHIRING", 3));
    }

    /**
     * P        A       H       N
     * A    P   L   S   I   I   G
     * Y        I       R
     */

    public String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows < 2) {
            return s;
        }
        List<List<Integer>> totalList = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            totalList.add(new ArrayList<Integer>());
        }
        int d = 2 * numRows - 2;
        for (int i = 0; i < s.length(); i += d) {
            totalList.get(0).add(i);
        }
        for (int i = 0; i < totalList.get(0).size(); i++) {
            int start = totalList.get(0).get(i);
            int limit = (i == totalList.get(0).size() - 1) ? s.length() : totalList.get(0).get(i + 1);
            int end = limit - start;
            int lastRow = numRows - 1;
            for (int j = 1; j < end; j++) {
                start++;
                if (j < numRows) {
                    totalList.get(j).add(start);
                    continue;
                }
                lastRow--;
                totalList.get(lastRow).add(start++);
            }
        }
        StringBuffer str = new StringBuffer();
        for (List<Integer> list : totalList) {
            for (Integer index : list) {
                str.append(s.charAt(index));
            }
        }
        return str.toString();
    }

    public String convert1(String s, int numRows) {
        if (s.length() <= numRows || numRows < 2) {
            return s;
        }
        StringBuffer[] totalList = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            totalList[i] = new StringBuffer();
        }
        int d = 2 * numRows - 2;
        for (int i = 0; i < s.length(); i += d) {
            totalList[0].append(i).append(",");
        }
        String[] firstArr = totalList[0].toString().split(",");
        for (int i = 0; i < firstArr.length; i++) {
            int start = Integer.parseInt(firstArr[i]);
            int limit = (i == firstArr.length - 1) ? s.length() : Integer.parseInt(firstArr[i + 1]);
            int end = limit - start;
            int lastRow = numRows - 1;
            for (int j = 1; j < end; j++) {
                start++;
                if (j < numRows) {
                    totalList[j].append(start).append(",");
                    continue;
                }
                lastRow--;
                totalList[lastRow].append(start).append(",");
            }
        }
        StringBuffer str = new StringBuffer();
        for (StringBuffer sb : totalList) {
            for (String index : sb.toString().split(",")) {
                str.append(s.charAt(Integer.parseInt(index)));
            }
        }
        return str.toString();
    }

    /**
     * 方法一：按行排序 todo
     * 通过从左向右迭代字符串，我们可以轻松
     **/
    public String convert2(String s, int numRows) {
        return null;
    }

    /**
     * 方法二：按行访问 todo
     * 通过从左向右迭代字符串，我们可以轻松
     **/
    public String convert3(String s, int numRows) {
        return null;
    }

}

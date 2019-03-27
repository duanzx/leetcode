package cn.duanzx.leetcode._002;


import org.junit.jupiter.api.Test;

public class AddTwoNumbers {
    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void testParseInt() {
        String s = "1000000000000000000000000000001";
//        [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
        System.out.println(Double.parseDouble(s));
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l1.next = l12;
        l12.next = l13;
        ListNode l2 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l2.next = l22;
        l22.next = l23;
//     ListNode listNode =    addTwoNumbers(l1,l2);
        ListNode listNode = addTwoNumbers(new ListNode(5), new ListNode(5));
        System.out.println(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String s1 = getString(l1);
        if (s1.length() == 0) {
            return l2;
        }
        String s2 = getString(l2);
        if (s2.length() == 0) {
            return l1;
        }
        int carry = 0;
        StringBuffer s3 = new StringBuffer();
        int n = s1.length() > s2.length() ? s1.length() : s2.length();
        for (int i = 0; i < n; i++) {
            int a = i < s1.length() ? Integer.parseInt(s1.substring(i, i + 1)) : 0;
            int b = i < s2.length() ? Integer.parseInt(s2.substring(i, i + 1)) : 0;
            int sum = a + b + carry;
            int c = sum % 10;
            carry = sum / 10;
            s3.append(c);
        }
        if (carry > 0) {
            s3.append(carry);
        }
        ListNode head = new ListNode(0);
        ListNode nextNode = new ListNode(Integer.parseInt(s3.substring(0, 1)));
        head.next = nextNode;
        for (int i = 1; i < s3.length(); i++) {
            ListNode tmp = new ListNode(Integer.parseInt(s3.substring(i, i + 1)));
            nextNode.next = tmp;
            nextNode = tmp;
        }
        return head.next;
    }

    public String getString(ListNode listNode) {
        StringBuffer stringBuffer = new StringBuffer();
        ListNode tmp = listNode;
        while (tmp != null) {
            stringBuffer.append(String.valueOf(tmp.val));
            tmp = tmp.next;
        }
        return stringBuffer.toString();
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode listNode1 = l1;
        ListNode listNode2 = l2;

        return null;
    }
}

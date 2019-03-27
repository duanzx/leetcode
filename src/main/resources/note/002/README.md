# 两数相加
## 描述
```
    给出两个 非空 的链表用来表示两个非负的整数。
    其中，它们各自的位数是按照 逆序 的方式存储的并且它们的每个节点只能存储一位数字。
    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    示例：
      输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
      输出：7 -> 0 -> 8
      原因：342 + 465 = 807
```
```
public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
```
## 思路 1
    首先要理解链表的存取顺序是先进后出的
    也就是说先进来的数是最高位3，最后进来的数是最低位4。先出来的是最低位4，最后出来的是最高位3
    1.依次从分别从两个链表中取出每个数字并存储起来（可以用数组存储，也可以用字符串存储，为了节省内存开销采用字符串）
    2.根据上面的操作，我们得到两个字符串 243 ；564
    3.然后从低位开始两两计算，将计算后的结果存入新的字符串里面
    4.如果相加大于10，则高位进1，低位取%10后的数字
    5.创建链表将字符串存入链表中
```
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String s1 = getString(l1);
        if(s1.length() == 0){
            return l2;
        }
        String s2 = getString(l2);
        if(s2.length() == 0){
            return l1;
        }
        int carry = 0;
        StringBuffer s3 = new StringBuffer();
        int n = s1.length() > s2.length() ? s1.length():s2.length();
        for(int i = 0;i<n;i++){
            int a = i < s1.length()?Integer.parseInt(s1.substring(i,i+1)):0;
            int b = i<s2.length()?Integer.parseInt(s2.substring(i,i+1)):0;
            int sum = a+b+carry;
            int c = sum%10;
            carry = sum/10;
            s3.append(c);
        }
        if(carry > 0){
            s3.append(carry);
        }
        ListNode head = new ListNode(0);
        ListNode nextNode = new ListNode(Integer.parseInt(s3.substring(0,1)));
        head.next = nextNode;
        for (int i = 1; i < s3.length(); i++) {
            ListNode tmp = new ListNode(Integer.parseInt(s3.substring(i,i+1)));
            nextNode.next = tmp;
            nextNode = tmp;
        }
        return head.next;
    }
    public String getString(ListNode listNode){
        StringBuffer stringBuffer = new StringBuffer();
        ListNode tmp = listNode;
        while (tmp != null) {
            stringBuffer.append(String.valueOf(tmp.val));
            tmp = tmp.next;
        }
        return stringBuffer.toString();
    }
```
## 思路 2
    上面的思路是先将数字从链表里全部取出来后在进行计算，其实也可以在链表取出的时候进行计算
        
        
        
                
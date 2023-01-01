package SingleLinkedList;

public class ReverseList {
    public static void main(String[] args) {

    }
}

//解法一迭代
class soulution{
    public ListNode reverseList(ListNode head){
        //定义两个指针前一pre(null),当前节点cur初始化为head
        ListNode pre=null,cur=head;
        //局部反转,当前节点下一节点指向前一节点,cur.next=pre,然后pre跟cur都向后移动,
        // 循环直到cur下一节点为null,退出循环
        while (cur!=null){
            //获取cur的下一节点
            ListNode next = cur.next;
            //当前节点下一节点指向pre
            cur.next=pre;
            //pre往后移动
            pre=cur;
            //cur向后移动
            cur=next;
        }
        return pre;
    }
}
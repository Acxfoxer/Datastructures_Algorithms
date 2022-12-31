package SingleLinkedList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//新浪面试题
// 查找单链表中的倒数第k个结点 【新浪面试题】
public class GetKthFromEnd {
    public static void main(String[] args) {

    }
}
//解法一,栈/队列
class solution{
    public ListNode getKthFromEnd(ListNode head, int k) {
        //创建队列,利用Deque<T>类模拟栈
        Deque<ListNode> arr = new ArrayDeque<>();
        //将链表中的节点全部压入队列中
        while (head!=null){
           arr.addLast(head);
           head=head.next;
        }
        //定义返回的链表节点
        ListNode listNode=null;
        while (k-->0){
            listNode=arr.getLast();
        }
        return listNode;
    }
}
//解法二双指针(快慢指针)
class solution1{
    public ListNode getKthFromEnd(ListNode head, int k) {
       //定义两个节点,作为快慢指针,快指针先移动k次,然后快慢指针一起移动
        //当快指针指向null,则慢指针所在的节点为需要的节点
        ListNode lower=head,faster=head;
        //定义一个变量,表示快指针移动步数
        int count =0;
        while (faster!=null){
            if(count>=k){
                lower=lower.next;
            }
            faster=faster.next;
            count++;
        }
        return lower;
    }

}
//解法三差值法
/*先统计出链表有效节点个数,
然后根据差值,遍历链表得到所需节点
* */
class solution2{
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head==null){
            return null;
        }
        int length = getLength(head);
        if(k<=0||length<k){
            return null;
        }
        for (int i = 0; i <length-k; i++) {
            head=head.next;
        }
        return head;
    }
    public static int  getLength(ListNode head ){
        if(head==null){
            return 0;
        }
        int count=0;//统计个数
        while (head!=null){
            count++;
            head=head.next;
        }
        return count;
    }
}
//自定义单链表,无头节点
class ListNode{
    public int val;
    public ListNode next;
    public ListNode(int num){
        this.val= num;
    }
}

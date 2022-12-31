package SingleLinkedList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//����������
// ���ҵ������еĵ�����k����� �����������⡿
public class GetKthFromEnd {
    public static void main(String[] args) {

    }
}
//�ⷨһ,ջ/����
class solution{
    public ListNode getKthFromEnd(ListNode head, int k) {
        //��������,����Deque<T>��ģ��ջ
        Deque<ListNode> arr = new ArrayDeque<>();
        //�������еĽڵ�ȫ��ѹ�������
        while (head!=null){
           arr.addLast(head);
           head=head.next;
        }
        //���巵�ص�����ڵ�
        ListNode listNode=null;
        while (k-->0){
            listNode=arr.getLast();
        }
        return listNode;
    }
}
//�ⷨ��˫ָ��(����ָ��)
class solution1{
    public ListNode getKthFromEnd(ListNode head, int k) {
       //���������ڵ�,��Ϊ����ָ��,��ָ�����ƶ�k��,Ȼ�����ָ��һ���ƶ�
        //����ָ��ָ��null,����ָ�����ڵĽڵ�Ϊ��Ҫ�Ľڵ�
        ListNode lower=head,faster=head;
        //����һ������,��ʾ��ָ���ƶ�����
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
//�ⷨ����ֵ��
/*��ͳ�Ƴ�������Ч�ڵ����,
Ȼ����ݲ�ֵ,��������õ�����ڵ�
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
        int count=0;//ͳ�Ƹ���
        while (head!=null){
            count++;
            head=head.next;
        }
        return count;
    }
}
//�Զ��嵥����,��ͷ�ڵ�
class ListNode{
    public int val;
    public ListNode next;
    public ListNode(int num){
        this.val= num;
    }
}

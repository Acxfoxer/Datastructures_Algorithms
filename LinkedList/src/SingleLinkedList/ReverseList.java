package SingleLinkedList;

public class ReverseList {
    public static void main(String[] args) {

    }
}

//�ⷨһ����
class soulution{
    public ListNode reverseList(ListNode head){
        //��������ָ��ǰһpre(null),��ǰ�ڵ�cur��ʼ��Ϊhead
        ListNode pre=null,cur=head;
        //�ֲ���ת,��ǰ�ڵ���һ�ڵ�ָ��ǰһ�ڵ�,cur.next=pre,Ȼ��pre��cur������ƶ�,
        // ѭ��ֱ��cur��һ�ڵ�Ϊnull,�˳�ѭ��
        while (cur!=null){
            //��ȡcur����һ�ڵ�
            ListNode next = cur.next;
            //��ǰ�ڵ���һ�ڵ�ָ��pre
            cur.next=pre;
            //pre�����ƶ�
            pre=cur;
            //cur����ƶ�
            cur=next;
        }
        return pre;
    }
}
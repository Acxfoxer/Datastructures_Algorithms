package DoubleLinkedList;

//˫������
public class testDoubleLinkedList {
}

//�Զ���˫������ʵ��,��ɾ�Ĳ�
class DoubleLinkedList{

}

//�Զ���˫������ڵ�
class ListNode{
    private ListNode pre;//ǰһ�ڵ�,Ĭ��Ϊnull
    private  ListNode next;//��һ�ڵ�,Ĭ��Ϊnull
    private int val;

    public ListNode() {
    }

    public ListNode(ListNode pre, ListNode next, int val) {
        this.pre = pre;
        this.next = next;
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "pre=" + pre +
                ", next=" + next +
                ", val=" + val +
                '}';
    }
}
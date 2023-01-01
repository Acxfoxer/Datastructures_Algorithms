package DoubleLinkedList;

//双向链表
public class testDoubleLinkedList {
}

//自定义双向链表实现,增删改查
class DoubleLinkedList{

}

//自定义双向链表节点
class ListNode{
    private ListNode pre;//前一节点,默认为null
    private  ListNode next;//下一节点,默认为null
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
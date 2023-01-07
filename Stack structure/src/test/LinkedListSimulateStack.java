package test;

public class LinkedListSimulateStack {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.push(1);
        list.push(2);
        list.push(3);
        list.traverseList();
        System.out.println("----------------");
        Integer peak = (Integer) list.peak();
        System.out.println("最顶部的为:"+peak);
        Integer pop = (Integer) list.pop();
        Integer pop1 = (Integer) list.pop();
        System.out.println(pop+"出栈");
        System.out.println(pop1+"出栈");

    }

}
//链表模拟栈的pop peak push功能
class LinkedList{
    private ListNode first;//头节点

    //模拟栈的push
    public void push(Object data){
        ListNode listNode =new ListNode(data);
       if(first==null){
           first=listNode;
       }else {
           ListNode node = first;
           while (node.next!=null){
               node=node.next;
           }

           node.next=listNode;
       }
    }
    //遍历方法
    public void traverseList(){
        isEmpty();
        ListNode node = first;
        while (node!=null){
            System.out.println(node.data);
            node=node.next;
        }
    }
    //模拟栈的peak,
    public Object peak(){
        isEmpty();
        ListNode node = first;
        while (node.next!=null)
            node=node.next;
        return node.data;
    }
    //模拟栈的pop
    public Object pop(){
        isEmpty();
        ListNode node = first;
        while (node.next.next!=null){
            node=node.next;
        }
        Object data =node.next.data;
        node.next=null;
        return data;
    }
    //判断是否链表为空
    public void isEmpty(){
        if(first==null){
            System.out.println("为空,什么都没有");
            throw new NullPointerException("链表为空");
        }
    }
}
//自定义节点
class ListNode{
    Object data;
    ListNode next;

    public ListNode(Object data) {
        this.data = data;
    }

    public ListNode() {
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
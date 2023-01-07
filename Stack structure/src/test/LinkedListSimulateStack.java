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
        System.out.println("�����Ϊ:"+peak);
        Integer pop = (Integer) list.pop();
        Integer pop1 = (Integer) list.pop();
        System.out.println(pop+"��ջ");
        System.out.println(pop1+"��ջ");

    }

}
//����ģ��ջ��pop peak push����
class LinkedList{
    private ListNode first;//ͷ�ڵ�

    //ģ��ջ��push
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
    //��������
    public void traverseList(){
        isEmpty();
        ListNode node = first;
        while (node!=null){
            System.out.println(node.data);
            node=node.next;
        }
    }
    //ģ��ջ��peak,
    public Object peak(){
        isEmpty();
        ListNode node = first;
        while (node.next!=null)
            node=node.next;
        return node.data;
    }
    //ģ��ջ��pop
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
    //�ж��Ƿ�����Ϊ��
    public void isEmpty(){
        if(first==null){
            System.out.println("Ϊ��,ʲô��û��");
            throw new NullPointerException("����Ϊ��");
        }
    }
}
//�Զ���ڵ�
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
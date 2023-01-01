package DoubleLinkedList;


//双向链表
public class testDoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedList db = new DoubleLinkedList();
        DoubleLinkedList db1 = new DoubleLinkedList();
        ListNode node = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        db.addFirst(node);
        db.addFirst(node1);
        db.addFirst(node2);
        db.addFirst(node3);
        db.addFirst(node4);
        db.list();
        System.out.println("--------------------------");
        db1.addLast(node);
        db1.addLast(node1);
        db1.addLast(node2);
        db1.addLast(node3);
        db1.addLast(node4);
        db1.list();
        try {
            db1.removeByIndex(4);
        } catch (Exception e) {
            System.out.println("超出边界");
        }
        db1.list();
    }
}

//自定双向链表实现,增删改查
class DoubleLinkedList{
    private ListNode headNode;//定义头节点
    private  ListNode tail;//定义尾节点
    private  int size;//定义链表长度

    public DoubleLinkedList() {
    }

    public DoubleLinkedList(int size) {
        this.size = size;
    }

    public DoubleLinkedList(ListNode headNode, ListNode tail, int size) {
        this.headNode = headNode;
        this.tail = tail;
        this.size = size;
    }

    //遍历方法
    public void list(){
        if(headNode==null){
            System.out.println("链表为空");
            return;
        }
        while(headNode!=null){
            System.out.println(headNode);
            headNode=headNode.next;
        }
    }
    //尾插法要想给链表的尾部插入一个元素，首先先要创建一个新的节点newNode，若链表为空，就是将链表的头节点和尾节点都指向新创建的节点，若链表不为空，
    // 则要要让链表的最后一个节点的下一个节点指向newNode，然后让newNode的前一个节点指向原先的最后一个节点
    public void addLast(Object obj){
        //定义中间变量
        ListNode newNode = new ListNode(null,null,obj);
        //判断头节点是否为空
        if(headNode==null){
            headNode=tail=newNode;
        }else {
            //创建一个指针指向双向链表尾节点
            /*ListNode node = this.tail;
            node.next=newNode;
            newNode.pre=node;
            //更新尾部节点指针
            this.tail=newNode;*/
            tail.next=newNode;
            newNode.pre=tail;
            //更新尾部节点指针
            tail=newNode;
        }
        size++;
    }
    //头插入
    // 在当前链表的头部插入一个节点，让当前链表的头结点head前驱指向要插入的节点node，然后让node的后继指向head，然后让head = node，让node成为链表的头结点
    public void addFirst(Object obj){
        ListNode node = new ListNode(null,null,obj);
        if(headNode==null){
            headNode=tail=node;
        }else {
            //创建一个指针指向双向链表尾节点
            //ListNode newHeadNode = this.headNode;
            headNode.pre=node;
            node.next=headNode;
            headNode=node;
        }
        size++;
    }
    //根据下标删除元素
    public void removeByIndex(int index){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("超出边界");
        }
        ListNode node=new ListNode(index);
        removeNode(node);
    }
    /*删除当前双向列表的节点
    * 分治法
    * */
    public void removeNode(ListNode node){
        ListNode successor = node.next;
        ListNode pre = node.pre;
        //先处理node前面的内容
        if(pre==null){
            headNode=successor;
        }else {
            pre.next=successor;
            node.pre=null;
        }
        if(successor==null){
            tail=pre;

        }else {
            successor.pre=pre;
            node.next=null;
        }
        size--;
    }

    // 删除节点
    // 思路
    // 1.由于头节点head不能动,所以需要一个辅助变量 temp来找到待删除节点的前一个节点
    // 2.说明我们在比较时，是temp.next.value 和 需要删除的节点的value比较
    public void del(Object val) {
        ListNode temp = headNode;
        boolean flag = false; // 标志是否找到带删除的节点
        while (true) {
            if (temp.next == null) { // 已经到链表的最后
                break;
            }
            if (temp.next.data == val) { // 找到了带删除的节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        // 判断flag
        if (flag) { // 说明找到了
            // 可以删除
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的节点"+val+"不存在");
        }
    }
}

//自定义双向链表节点
class ListNode{ ;
    ListNode pre;//前一节点,默认为null
    public ListNode next;//下一节点,默认为null
    ListNode tail;//尾部节点
    Object data;

    public ListNode() {
    }

    public ListNode(Object data) {
        this.data = data;
    }

    public ListNode(ListNode pre, ListNode tail, Object data) {
        this.pre = pre;
        this.tail = tail;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "data=" + data +
                '}';
    }
}
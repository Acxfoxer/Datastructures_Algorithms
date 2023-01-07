package DoubleLinkedList;


//双向链表
public class testDoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedList db = new DoubleLinkedList();
        DoubleLinkedList db1 = new DoubleLinkedList();
        db.addFirst(0);
        db.addFirst(1);
        db.addFirst(2);
        db.addFirst(3);
        db.addFirst(4);
        //删除索引为3的节点
        boolean removeByIndex = db.remove(3);
        if(removeByIndex){
            System.out.println("删除索引为3的节点删除成功");
        }
        boolean removeByValue = db.removeByValue(2);
        if(removeByValue){
            System.out.println("根据val值删除成功");
        }
        db.list();
        System.out.println("--------------------------");
        db1.addLast(1);
        db1.addLast(2);
        db1.addLast(3);
        db1.addLast(4);
        db1.addLast(5);
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
    //根据索引添加
    public void add(int index,Object obj){
        if(checkIndex(index)){
            if(index==0){
                addFirst(obj);
            }else if(index==size){
                addLast(obj);
            }else {
                ListNode node = getNode(index);
                ListNode newNode = new ListNode(obj);
                node.pre.next=newNode;
                node.pre =newNode;
                size++;
            }
        }
    }
    //判断索引越界
    public boolean checkIndex(int index){
        return index >= 0 && index <= size - 1;
    }

    /**获取当前索引的节点
     * 二分法
     * @param index 要获取的索引的节点
     */
    public ListNode getNode(int index){
        //为了提高效率，可以先让索引值i与中间值比较，再确定是从前到后遍历还是从后到前遍历
        ListNode node;
        if(index<(this.size>>1)){
            //从前向后遍历
            node = this.headNode;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }else{
            //从后遍历
            node = this.tail;
            for (int i = this.size-1; i >index; i--) {
                node = node.pre;
            }
        }
        return node;
    }
    /*删除当前双向列表的节点
    * 分治法
    * */
    public void unlink(ListNode node){
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
    //根据索引删除
    public boolean remove(int index){
        //先检查索引是否越界
        if(checkIndex(index)){
            ListNode p = this.headNode;
            //遍历找到要删除的结点
            for (int j = 0; j < index; j++) {
                p = p.next;
            }
            unlink(p);
        }
        return true;
    }

    // 根据节点数据数据删除某一节点
    // 思路
    // 1.由于头节点head不能动,所以需要一个辅助节点node来找到待删除节点的前一个节点
    // 2.说明我们在比较时，是temp.next.value 和 需要删除的节点的value比较
    public boolean removeByValue(Object val) {
        //先判断是否为null
        ListNode node = this.headNode;
        if (val == null) {
            while (node != null) {
                if (node.data == null) {
                    //如果找到结点就调用删除结点的方法
                    unlink(node);
                    return true;
                }
                node = node.next;
            }
        } else {
            while (node != null) {
                if (node.data.equals(val)) {
                    //如果找到结点就调用删除结点的方法
                    unlink(node);
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    //替换节点
    public void replaceNode(){

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
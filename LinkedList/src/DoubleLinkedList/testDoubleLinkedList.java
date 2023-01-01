package DoubleLinkedList;


//˫������
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
            System.out.println("�����߽�");
        }
        db1.list();
    }
}

//�Զ�˫������ʵ��,��ɾ�Ĳ�
class DoubleLinkedList{
    private ListNode headNode;//����ͷ�ڵ�
    private  ListNode tail;//����β�ڵ�
    private  int size;//����������

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

    //��������
    public void list(){
        if(headNode==null){
            System.out.println("����Ϊ��");
            return;
        }
        while(headNode!=null){
            System.out.println(headNode);
            headNode=headNode.next;
        }
    }
    //β�巨Ҫ��������β������һ��Ԫ�أ�������Ҫ����һ���µĽڵ�newNode��������Ϊ�գ����ǽ������ͷ�ڵ��β�ڵ㶼ָ���´����Ľڵ㣬������Ϊ�գ�
    // ��ҪҪ����������һ���ڵ����һ���ڵ�ָ��newNode��Ȼ����newNode��ǰһ���ڵ�ָ��ԭ�ȵ����һ���ڵ�
    public void addLast(Object obj){
        //�����м����
        ListNode newNode = new ListNode(null,null,obj);
        //�ж�ͷ�ڵ��Ƿ�Ϊ��
        if(headNode==null){
            headNode=tail=newNode;
        }else {
            //����һ��ָ��ָ��˫������β�ڵ�
            /*ListNode node = this.tail;
            node.next=newNode;
            newNode.pre=node;
            //����β���ڵ�ָ��
            this.tail=newNode;*/
            tail.next=newNode;
            newNode.pre=tail;
            //����β���ڵ�ָ��
            tail=newNode;
        }
        size++;
    }
    //ͷ����
    // �ڵ�ǰ�����ͷ������һ���ڵ㣬�õ�ǰ�����ͷ���headǰ��ָ��Ҫ����Ľڵ�node��Ȼ����node�ĺ��ָ��head��Ȼ����head = node����node��Ϊ�����ͷ���
    public void addFirst(Object obj){
        ListNode node = new ListNode(null,null,obj);
        if(headNode==null){
            headNode=tail=node;
        }else {
            //����һ��ָ��ָ��˫������β�ڵ�
            //ListNode newHeadNode = this.headNode;
            headNode.pre=node;
            node.next=headNode;
            headNode=node;
        }
        size++;
    }
    //�����±�ɾ��Ԫ��
    public void removeByIndex(int index){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("�����߽�");
        }
        ListNode node=new ListNode(index);
        removeNode(node);
    }
    /*ɾ����ǰ˫���б�Ľڵ�
    * ���η�
    * */
    public void removeNode(ListNode node){
        ListNode successor = node.next;
        ListNode pre = node.pre;
        //�ȴ���nodeǰ�������
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

    // ɾ���ڵ�
    // ˼·
    // 1.����ͷ�ڵ�head���ܶ�,������Ҫһ���������� temp���ҵ���ɾ���ڵ��ǰһ���ڵ�
    // 2.˵�������ڱȽ�ʱ����temp.next.value �� ��Ҫɾ���Ľڵ��value�Ƚ�
    public void del(Object val) {
        ListNode temp = headNode;
        boolean flag = false; // ��־�Ƿ��ҵ���ɾ���Ľڵ�
        while (true) {
            if (temp.next == null) { // �Ѿ�����������
                break;
            }
            if (temp.next.data == val) { // �ҵ��˴�ɾ���Ľڵ��ǰһ���ڵ�
                flag = true;
                break;
            }
            temp = temp.next; // temp���ƣ�����
        }
        // �ж�flag
        if (flag) { // ˵���ҵ���
            // ����ɾ��
            temp.next = temp.next.next;
        } else {
            System.out.println("Ҫɾ���Ľڵ�"+val+"������");
        }
    }
}

//�Զ���˫������ڵ�
class ListNode{ ;
    ListNode pre;//ǰһ�ڵ�,Ĭ��Ϊnull
    public ListNode next;//��һ�ڵ�,Ĭ��Ϊnull
    ListNode tail;//β���ڵ�
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
package DoubleLinkedList;


//˫������
public class testDoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedList db = new DoubleLinkedList();
        DoubleLinkedList db1 = new DoubleLinkedList();
        db.addFirst(0);
        db.addFirst(1);
        db.addFirst(2);
        db.addFirst(3);
        db.addFirst(4);
        //ɾ������Ϊ3�Ľڵ�
        boolean removeByIndex = db.remove(3);
        if(removeByIndex){
            System.out.println("ɾ������Ϊ3�Ľڵ�ɾ���ɹ�");
        }
        boolean removeByValue = db.removeByValue(2);
        if(removeByValue){
            System.out.println("����valֵɾ���ɹ�");
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
    //�����������
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
    //�ж�����Խ��
    public boolean checkIndex(int index){
        return index >= 0 && index <= size - 1;
    }

    /**��ȡ��ǰ�����Ľڵ�
     * ���ַ�
     * @param index Ҫ��ȡ�������Ľڵ�
     */
    public ListNode getNode(int index){
        //Ϊ�����Ч�ʣ�������������ֵi���м�ֵ�Ƚϣ���ȷ���Ǵ�ǰ����������ǴӺ�ǰ����
        ListNode node;
        if(index<(this.size>>1)){
            //��ǰ������
            node = this.headNode;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }else{
            //�Ӻ����
            node = this.tail;
            for (int i = this.size-1; i >index; i--) {
                node = node.pre;
            }
        }
        return node;
    }
    /*ɾ����ǰ˫���б�Ľڵ�
    * ���η�
    * */
    public void unlink(ListNode node){
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
    //��������ɾ��
    public boolean remove(int index){
        //�ȼ�������Ƿ�Խ��
        if(checkIndex(index)){
            ListNode p = this.headNode;
            //�����ҵ�Ҫɾ���Ľ��
            for (int j = 0; j < index; j++) {
                p = p.next;
            }
            unlink(p);
        }
        return true;
    }

    // ���ݽڵ���������ɾ��ĳһ�ڵ�
    // ˼·
    // 1.����ͷ�ڵ�head���ܶ�,������Ҫһ�������ڵ�node���ҵ���ɾ���ڵ��ǰһ���ڵ�
    // 2.˵�������ڱȽ�ʱ����temp.next.value �� ��Ҫɾ���Ľڵ��value�Ƚ�
    public boolean removeByValue(Object val) {
        //���ж��Ƿ�Ϊnull
        ListNode node = this.headNode;
        if (val == null) {
            while (node != null) {
                if (node.data == null) {
                    //����ҵ����͵���ɾ�����ķ���
                    unlink(node);
                    return true;
                }
                node = node.next;
            }
        } else {
            while (node != null) {
                if (node.data.equals(val)) {
                    //����ҵ����͵���ɾ�����ķ���
                    unlink(node);
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    //�滻�ڵ�
    public void replaceNode(){

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
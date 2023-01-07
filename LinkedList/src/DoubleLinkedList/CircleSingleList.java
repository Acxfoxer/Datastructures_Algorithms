package DoubleLinkedList;


//Josephu����Ϊ������Ϊ1��2���� n��n����Χ��һȦ��Լ�����Ϊk��1<=k<=n�����˴�1��ʼ������
// ����m ���Ǹ��˳��У�������һλ�ִ�1��ʼ����������m���Ǹ����ֳ��У�
// �������ƣ�ֱ�������˳���Ϊֹ���ɴ˲���һ�����ӱ�ŵ����С�
public class CircleSingleList {
    public static void main(String[] args) {
        CircleSingleLinkedList cr = new CircleSingleLinkedList();
        cr.add(5);
        cr.traverseList();
        cr.out(1,2);
    }
}

//����������ʵ��
class CircleSingleLinkedList{
        private PeopleNode first;//ͷ�ڵ�
        // ��¼С������
        private int nums;

    public CircleSingleLinkedList() {
    }

    /**
     *����������ӷ���
     * @param num ����
     */
    public void add(int num){
        if(num<1){
            System.out.println("��ӵ����ִ���");
            return;
        }
        this.nums=num;
        PeopleNode temp =null;
        //�����м����
        for (int i = 1; i <= num; i++) {
            PeopleNode child = new PeopleNode(i);
            if(this.first==null){
                this.first= child;
                temp=first;
            }else {
                assert temp != null;
                temp.next=child;
                temp=temp.next;
            }
        }
        temp.next=this.first;
    }

    /**��������������
     * traverseList
     */
    public void traverseList(){
        if(first==null){
            System.out.println("Ϊ��,ʲô��û��");
            return;
        }
        //ָ���������,���ı�ԭ����״̬
        PeopleNode node = first;
        System.out.println(node.no);
        node=node.next;
        while (node!=first){
            System.out.println(node.no);
            node=node.next;
        }
    }

    /**
     * �ӵ�һ��С����ʼ,ÿ����С����Ȧ,����һ��С����Ȧ˳��
     * @param startNo �ӵڼ���С����ʼ
     * @param countNum ������
     */
    public void out(int startNo,int countNum){
        if(first==null||startNo<1||startNo>this.nums){
            System.out.println("��������,��������");
        }
        PeopleNode stopNode = first;//ָ��ɾ���ڵ��ǰһ�ڵ�
        while (stopNode.next!=first){
                stopNode=stopNode.next;
        }
        PeopleNode newNode=first;
        // ������ƶ�startId -1���ڵ㡣����stopNodeҲ�����ƶ�
        for (int i = 0; i < startNo-1; i++) {
            newNode = newNode.next;
            stopNode = stopNode.next;
        }
        //ѭ��������Ȧ
        while (newNode!=stopNode){
            if(newNode!=null){
                // ���ƶ���Ҫ��Ȧ�Ľڵ㡣����stopNodeҲ�����ƶ�
                for (int i = 0; i < countNum-1; i++) {
                    newNode=newNode.next;
                    stopNode=stopNode.next;
                }
                //ѭ������,newNode����λ�ü�ΪҪ��Ȧ��С��
                System.out.println("��ȦС�����Ϊ"+newNode);
                // ��Ȧ�Ľڵ�û�б�����, �ᱻ��������,�Ͽ���ʱС������
                newNode=newNode.next;
                stopNode.next=newNode;
            }
        }
        System.out.println("����Ȧ��С������: " + newNode);
    }
}

//�Զ���ڵ���
class PeopleNode{
     Integer no;//���
     PeopleNode next;

    public PeopleNode(Integer num) {
        this.no = num;
    }

    @Override
    public String toString() {
        return "PeopleNode{" +
                "no=" + no ;
    }
}
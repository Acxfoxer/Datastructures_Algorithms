package SingleLinkedList;

public class LinkedList {
    public static void main(String[] args) {
        /*��ϰ
        ʹ�ô�headͷ�ĵ�������ʵ�� �Cˮ�Ӣ�����а����

        1)��ɶ�Ӣ���������ɾ�Ĳ������ ע: ɾ�����޸�,���ҿ��Կ���ѧԱ������ɣ�Ҳ�ɴ�ѧԱ���
        2)��һ�ַ��������Ӣ��ʱ��ֱ����ӵ������β��
        3)�ڶ��ַ�ʽ�����Ӣ��ʱ������������Ӣ�۲��뵽ָ��λ��
                (�������������������ʧ�ܣ���������ʾ)*/
        //����һ��
        //1.�����ڵ�
        HeroNode hero1 = new HeroNode(1,"�ν�","��ʱ��");
        HeroNode hero2 = new HeroNode(2,"¬����","������");
        HeroNode hero3 = new HeroNode(3,"����","�Ƕ���");
        HeroNode hero4 = new HeroNode(4,"����ʤ","������");
        HeroNode hero5 = new HeroNode(5,"��ʤ","��");
        HeroNode hero6 = new HeroNode(6,"³����","����");
        //����һ������
        UnilateralLinkedList uniLinkedList = new UnilateralLinkedList();

        //������ݵ�����
        /*uniLinkedList.add(hero);
        uniLinkedList.add(hero2);
        uniLinkedList.add(hero3);
        uniLinkedList.add(hero4);
        uniLinkedList.add(hero5);
        uniLinkedList.add(hero7);
        uniLinkedList.list();*/
        //����˳�����
        uniLinkedList.addByOrder(hero1);
        uniLinkedList.addByOrder(hero6);
        uniLinkedList.addByOrder(hero2);
        uniLinkedList.addByOrder(hero3);
        uniLinkedList.addByOrder(hero4);
        uniLinkedList.addByOrder(hero5);
        uniLinkedList.list();
        System.out.println("-------------------------------------");
        //�޸�ָ���ڵ���Ϣ
        HeroNode hero9=new HeroNode(9,"����","����");
        HeroNode hero8=new HeroNode(8,"����","����");
        uniLinkedList.addByOrder(hero9);
        uniLinkedList.update(hero8);
        uniLinkedList.list();
        System.out.println("-------------------------------------");
        //ɾ��ָ���ڵ�
        HeroNode hero7=new HeroNode(7,"","");
        uniLinkedList.delete(hero7);
        uniLinkedList.list();
        System.out.println("----------------------------------------");
        //��ȡͷjiedian
        HeroNode head = uniLinkedList.getHead();
        //���÷�����ѯ��Ч�ڵ���
        int nodeCount = getNodeCount(head);
        System.out.printf("������Ч�ڵ����Ϊ%d",nodeCount);
        System.out.println("-------------------------------------------------------");
        //���Ժϲ�������������(��ͷ�ڵ�)
        UnilateralLinkedList uniLinkedList1 = new UnilateralLinkedList();
        UnilateralLinkedList uniLinkedList2 = new UnilateralLinkedList();
        uniLinkedList1.addByOrder(hero9);
        uniLinkedList1.addByOrder(hero7);
        uniLinkedList1.addByOrder(hero8);
        uniLinkedList1.addByOrder(hero3);
        uniLinkedList1.addByOrder(hero5);
        System.out.println("uniLinkedList1Ϊ:");
        uniLinkedList1.list();
        uniLinkedList2.addByOrder(hero2);
        uniLinkedList2.addByOrder(hero4);
        uniLinkedList2.addByOrder(hero6);
        uniLinkedList2.addByOrder(hero1);
        System.out.println("uniLinkedList2Ϊ:");
        uniLinkedList2.list();

        System.out.println("�ϲ��������Ϊ:");
        UnilateralLinkedList unilateralLinkedList = uniLinkedList1.mergeTwoLinkedList(uniLinkedList1, uniLinkedList2);
        unilateralLinkedList.list();

    }
    //���ҵ���������Ч�ڵ����,����ͷ�ڵ�
    public static int getNodeCount(HeroNode headNode){
        if(headNode.next==null){
            return 0;
        }
        int count=0;//��¼��Ч�ڵ�ĸ���,����Ϊnull
        HeroNode str = headNode;//������ʱ����
        while (str.next!=null){
            count++;
            str=str.next;
        }
        return count;
    }

}

//����һ����������(��ͷ�ڵ�)��
class UnilateralLinkedList{
    private HeroNode headNode = new HeroNode(0, "", "");

    public UnilateralLinkedList(HeroNode node) {
        this.headNode =node;
    }

    public UnilateralLinkedList() {
        headNode=new HeroNode();
    }

    //����ͷ�ڵ�
    public HeroNode getHead() {
        return headNode;
    }
    //��ӽڵ㵽��������
    //˼·���������Ǳ�ŵ�˳��ʱ
    //1.�ҵ���ǰ��������ڵ�
    //2.���������ڵ��next��ָ������µĽڵ�
    public void add(HeroNode heroNode){
        //�������ͷ�ڵ�,ͷ�ڵ�����ֻ����һ������ĵ�ַ,����ֵ
        HeroNode listNode = headNode;
        //�ж���һ���ڵ��Ƿ�Ϊ��
        while (listNode.next != null) {
           //��Ϊ��,��ڵ������
            listNode = listNode.next;
        }
        //�˳�ѭ��,��Ϊ���������һ���ڵ�
        //���һ���ڵ�ָ��ǿ��½ڵ�
        listNode.next = heroNode;
    }
    //��ʾ����[����]
    public void list(){
        //�ж������Ƿ�Ϊ��
        if(headNode.next == null){
            System.out.println("����Ϊ�գ�����");
            return;
        }
        //����ͷ�ڵ�head���ܶ�,������Ҫһ���������� temp
        HeroNode linkedNode = headNode;
        while (true) {
            if(linkedNode == null){
                break;
            }
            //�ж��Ƿ���������
            //�����Ϊ�գ�����ڵ����Ϣ
            System.out.println(linkedNode);
            //ע�⣡��������next����(��Ϊ������ƶ�,�������ѭ��)
            linkedNode = linkedNode.next;
        }
    }
    //��������,��Ӣ�۲��뵽ָ��λ��
    public void addByOrder(HeroNode heroNode){
        HeroNode linkedNode = headNode;
        boolean add_flag =false ;//�����ж��Ƿ������ͬ����,Ĭ��Ϊfalse;
        //��ѭ������id�жϲ���λ��
        while (true){
            if(linkedNode.next==null){
                break;
            }
            //���ڸ�����id��ͬ,����ʾ�޷�����
            if(linkedNode.next.id== headNode.id){
                add_flag=true;
                break;
            }
            //������һ���ڵ����һ���ڵ�id����Ҫ�����id,���ʾ�����λ��ΪlinkedNode��linkedNode.next֮��,����ѭ��
            else if (linkedNode.next.id>heroNode.id){
                break;
            }
            //����,������ǰ����
            linkedNode=linkedNode.next;
        }
        //�жϱ���booleanֵ
        if(add_flag){
            System.out.println("��Ŵ���");
        }else {
            //���뵽������,linkedNode�ĺ���\
            heroNode.next=linkedNode.next;
            linkedNode.next=heroNode;
        }
    }
    //�޸Ľڵ���Ϣ
    public void update(HeroNode heroNode){
        //�жϽڵ��Ƿ�Ϊ��
        if(headNode.next==null){
            System.out.println("����Ϊ��");
            return;
        }
        //������ʱ����
        HeroNode linkedNode = headNode.next;
        //����id����ҵ���Ҫ�޸ĵĽڵ�,
        boolean update_flag = false;//�ж��Ƿ��ҵ��ýڵ����
        while (linkedNode!=null){
            if(linkedNode.id==heroNode.id){
                update_flag=true;
                break;
            }
            linkedNode=linkedNode.next;
        }
        if(update_flag){
            linkedNode.name = heroNode.name;
            linkedNode.nickName = heroNode.nickName;
        }else {
            System.out.printf("û���ҵ����Ϊ %d �Ľڵ�.\n",heroNode.id);
        }
    }
    //ɾ���ڵ���Ϣ
    public void delete(HeroNode heroNode){
        //�жϽڵ��Ƿ�Ϊ��
        if(heroNode==null){
            return;
        }
        //������ʱ����
        HeroNode linkedNode = headNode;
        //����һ���ж��Ƿ���ڸ�id�Ľڵ�
        boolean isExist_flag =false;
        while(linkedNode.next!=null){
            if(linkedNode.next.id== heroNode.id){
                isExist_flag=true;
                break;
            }
            //�����������
            linkedNode=linkedNode.next;
        }
        if(isExist_flag){
            //����ɾ��
            linkedNode.next=linkedNode.next.next;
        }else {
            System.out.printf("�޷�ɾ��idΪ%d�Ľڵ�,��Ϊ�䲻����",heroNode.id);
        }
    }


    //�ϲ���������ĵ������ϲ�֮���������Ȼ���򡾿κ���ϰ.��
    public UnilateralLinkedList mergeTwoLinkedList(UnilateralLinkedList list1 ,UnilateralLinkedList list2){
        if(list1.headNode.next==null){
            return list2;
        }else if(list2.headNode.next==null){
            return list1;
        }
        HeroNode newNode= new HeroNode();
        HeroNode node = newNode;
        HeroNode node1 = list1.headNode.next;
        HeroNode node2 = list2.headNode.next;
        while (node1!=null&&node2!=null){
            if(node1.id<node2.id){
                node.next=node1;
                node1=node1.next;
            }else {
                node.next=node2;
                node2=node2.next;
            }
            node=node.next;
        }
        if(node1 == null){
            node.next=node2;
        }else{
            node.next=node1;
        }
        return new UnilateralLinkedList(newNode);
    }
}
//����һ��HeroNode,ÿ��HeroNode�������һ���ڵ�
class HeroNode {
    public int id;//��ʾ����
    public String name;//�佫����
    public String nickName;    //����,�ǳ�
    public HeroNode next;    //ָ����һ���ڵ�
    //������
    public HeroNode(int id, String name, String nickName) {
        super();
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode() {
    }

    //Ϊ����ʾ���㣬��дtoString����
    @Override
    public String toString() {
        return "UnilateralLinkedList.HeroNode [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
    }
}

package SingleLinkedList;

public class LinkedList {
    public static void main(String[] args) {
        /*练习
        使用带head头的单向链表实现 –水浒英雄排行榜管理

        1)完成对英雄人物的增删改查操作， 注: 删除和修改,查找可以考虑学员独立完成，也可带学员完成
        2)第一种方法在添加英雄时，直接添加到链表的尾部
        3)第二种方式在添加英雄时，根据排名将英雄插入到指定位置
                (如果有这个排名，则添加失败，并给出提示)*/
        //测试一下
        //1.创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"公孙胜","入云龙");
        HeroNode hero5 = new HeroNode(5,"关胜","大刀");
        HeroNode hero6 = new HeroNode(6,"鲁智深","大嘴");
        //创建一个链表
        UnilateralLinkedList uniLinkedList = new UnilateralLinkedList();

        //添加数据到链表
        /*uniLinkedList.add(hero);
        uniLinkedList.add(hero2);
        uniLinkedList.add(hero3);
        uniLinkedList.add(hero4);
        uniLinkedList.add(hero5);
        uniLinkedList.add(hero7);
        uniLinkedList.list();*/
        //按照顺序添加
        uniLinkedList.addByOrder(hero1);
        uniLinkedList.addByOrder(hero6);
        uniLinkedList.addByOrder(hero2);
        uniLinkedList.addByOrder(hero3);
        uniLinkedList.addByOrder(hero4);
        uniLinkedList.addByOrder(hero5);
        uniLinkedList.list();
        System.out.println("-------------------------------------");
        //修改指定节点信息
        HeroNode hero9=new HeroNode(9,"张三","李四");
        HeroNode hero8=new HeroNode(8,"王五","炖鸡");
        uniLinkedList.addByOrder(hero9);
        uniLinkedList.update(hero8);
        uniLinkedList.list();
        System.out.println("-------------------------------------");
        //删除指定节点
        HeroNode hero7=new HeroNode(7,"","");
        uniLinkedList.delete(hero7);
        uniLinkedList.list();
        System.out.println("----------------------------------------");
        //获取头jiedian
        HeroNode head = uniLinkedList.getHead();
        //调用方法查询有效节点数
        int nodeCount = getNodeCount(head);
        System.out.printf("链表有效节点个数为%d",nodeCount);
        System.out.println("-------------------------------------------------------");
        //测试合并两个有序单链表(带头节点)
        UnilateralLinkedList uniLinkedList1 = new UnilateralLinkedList();
        UnilateralLinkedList uniLinkedList2 = new UnilateralLinkedList();
        uniLinkedList1.addByOrder(hero9);
        uniLinkedList1.addByOrder(hero7);
        uniLinkedList1.addByOrder(hero8);
        uniLinkedList1.addByOrder(hero3);
        uniLinkedList1.addByOrder(hero5);
        System.out.println("uniLinkedList1为:");
        uniLinkedList1.list();
        uniLinkedList2.addByOrder(hero2);
        uniLinkedList2.addByOrder(hero4);
        uniLinkedList2.addByOrder(hero6);
        uniLinkedList2.addByOrder(hero1);
        System.out.println("uniLinkedList2为:");
        uniLinkedList2.list();

        System.out.println("合并后的链表为:");
        UnilateralLinkedList unilateralLinkedList = uniLinkedList1.mergeTwoLinkedList(uniLinkedList1, uniLinkedList2);
        unilateralLinkedList.list();

    }
    //查找单链表中有效节点个数,传入头节点
    public static int getNodeCount(HeroNode headNode){
        if(headNode.next==null){
            return 0;
        }
        int count=0;//记录有效节点的个数,即不为null
        HeroNode str = headNode;//创建临时变量
        while (str.next!=null){
            count++;
            str=str.next;
        }
        return count;
    }

}

//定义一个单项链表(带头节点)类
class UnilateralLinkedList{
    private HeroNode headNode = new HeroNode(0, "", "");

    public UnilateralLinkedList(HeroNode node) {
        this.headNode =node;
    }

    public UnilateralLinkedList() {
        headNode=new HeroNode();
    }

    //返回头节点
    public HeroNode getHead() {
        return headNode;
    }
    //添加节点到单向链表
    //思路，当不考虑编号的顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next域指向这个新的节点
    public void add(HeroNode heroNode){
        //单链表带头节点,头节点里面只存下一个链表的地址,不存值
        HeroNode listNode = headNode;
        //判断下一个节点是否为空
        while (listNode.next != null) {
           //不为空,则节点向后移
            listNode = listNode.next;
        }
        //退出循环,即为单链表最后一个节点
        //最后一个节点指向非空新节点
        listNode.next = heroNode;
    }
    //显示链表[遍历]
    public void list(){
        //判断链表是否为空
        if(headNode.next == null){
            System.out.println("链表为空！！！");
            return;
        }
        //由于头节点head不能动,所以需要一个辅助变量 temp
        HeroNode linkedNode = headNode;
        while (true) {
            if(linkedNode == null){
                break;
            }
            //判断是否到链表的最后
            //如果不为空，输出节点的信息
            System.out.println(linkedNode);
            //注意！！！！将next后移(因为不向后移动,会造成死循环)
            linkedNode = linkedNode.next;
        }
    }
    //根据排名,将英雄插入到指定位置
    public void addByOrder(HeroNode heroNode){
        HeroNode linkedNode = headNode;
        boolean add_flag =false ;//用来判断是否存在相同排名,默认为false;
        //用循环根据id判断插入位置
        while (true){
            if(linkedNode.next==null){
                break;
            }
            //存在跟插入id相同,则提示无法插入
            if(linkedNode.next.id== headNode.id){
                add_flag=true;
                break;
            }
            //当存在一个节点的下一个节点id大于要插入的id,则表示插入的位置为linkedNode与linkedNode.next之间,跳出循环
            else if (linkedNode.next.id>heroNode.id){
                break;
            }
            //后移,遍历当前链表
            linkedNode=linkedNode.next;
        }
        //判断变量boolean值
        if(add_flag){
            System.out.println("编号存在");
        }else {
            //插入到链表中,linkedNode的后面\
            heroNode.next=linkedNode.next;
            linkedNode.next=heroNode;
        }
    }
    //修改节点信息
    public void update(HeroNode heroNode){
        //判断节点是否为空
        if(headNode.next==null){
            System.out.println("链表为空");
            return;
        }
        //创建临时变量
        HeroNode linkedNode = headNode.next;
        //根据id编号找到需要修改的节点,
        boolean update_flag = false;//判断是否找到该节点变量
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
            System.out.printf("没有找到编号为 %d 的节点.\n",heroNode.id);
        }
    }
    //删除节点信息
    public void delete(HeroNode heroNode){
        //判断节点是否为空
        if(heroNode==null){
            return;
        }
        //创建临时变量
        HeroNode linkedNode = headNode;
        //定义一个判断是否存在该id的节点
        boolean isExist_flag =false;
        while(linkedNode.next!=null){
            if(linkedNode.next.id== heroNode.id){
                isExist_flag=true;
                break;
            }
            //不存在则后移
            linkedNode=linkedNode.next;
        }
        if(isExist_flag){
            //可以删除
            linkedNode.next=linkedNode.next.next;
        }else {
            System.out.printf("无法删除id为%d的节点,因为其不存在",heroNode.id);
        }
    }


    //合并两个有序的单链表，合并之后的链表依然有序【课后练习.】
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
//定义一个HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int id;//表示排名
    public String name;//武将姓名
    public String nickName;    //别名,昵称
    public HeroNode next;    //指向下一个节点
    //构造器
    public HeroNode(int id, String name, String nickName) {
        super();
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode() {
    }

    //为了显示方便，重写toString方法
    @Override
    public String toString() {
        return "UnilateralLinkedList.HeroNode [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
    }
}

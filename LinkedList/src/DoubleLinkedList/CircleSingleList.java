package DoubleLinkedList;


//Josephu问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
// 数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，
// 依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
public class CircleSingleList {
    public static void main(String[] args) {
        CircleSingleLinkedList cr = new CircleSingleLinkedList();
        cr.add(5);
        cr.traverseList();
        cr.out(1,2);
    }
}

//单向环形链表实现
class CircleSingleLinkedList{
        private PeopleNode first;//头节点
        // 记录小孩数量
        private int nums;

    public CircleSingleLinkedList() {
    }

    /**
     *环形链表添加方法
     * @param num 人数
     */
    public void add(int num){
        if(num<1){
            System.out.println("添加的数字错误");
            return;
        }
        this.nums=num;
        PeopleNode temp =null;
        //设置中间变量
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

    /**遍历环形链表方法
     * traverseList
     */
    public void traverseList(){
        if(first==null){
            System.out.println("为空,什么都没有");
            return;
        }
        //指针操作链表,不改变原链表状态
        PeopleNode node = first;
        System.out.println(node.no);
        node=node.next;
        while (node!=first){
            System.out.println(node.no);
            node=node.next;
        }
    }

    /**
     * 从第一个小孩开始,每两个小孩出圈,生成一个小孩出圈顺序
     * @param startNo 从第几个小孩开始
     * @param countNum 数几下
     */
    public void out(int startNo,int countNum){
        if(first==null||startNo<1||startNo>this.nums){
            System.out.println("参数有误,重新输入");
        }
        PeopleNode stopNode = first;//指向删除节点的前一节点
        while (stopNode.next!=first){
                stopNode=stopNode.next;
        }
        PeopleNode newNode=first;
        // 先向后移动startId -1个节点。其中stopNode也跟着移动
        for (int i = 0; i < startNo-1; i++) {
            newNode = newNode.next;
            stopNode = stopNode.next;
        }
        //循环计数出圈
        while (newNode!=stopNode){
            if(newNode!=null){
                // 先移动到要出圈的节点。其中stopNode也跟着移动
                for (int i = 0; i < countNum-1; i++) {
                    newNode=newNode.next;
                    stopNode=stopNode.next;
                }
                //循环结束,newNode所在位置即为要出圈的小孩
                System.out.println("出圈小孩编号为"+newNode);
                // 出圈的节点没有被引用, 会被垃圾回收,断开此时小孩连接
                newNode=newNode.next;
                stopNode.next=newNode;
            }
        }
        System.out.println("最后出圈的小孩数据: " + newNode);
    }
}

//自定义节点雷
class PeopleNode{
     Integer no;//编号
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
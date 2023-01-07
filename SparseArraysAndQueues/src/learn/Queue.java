import java.util.Scanner;

public class Queue {
    public static void main(String[] args) {
        //数组模仿队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        CircleArray circleArray = new CircleArray(5);
        Scanner sc = new Scanner(System.in);
        IO:while (true){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("l(length):队列有效元素个数");
            System.out.println("h(head):查看队列头的数据");
            switch (sc.next().charAt(0)){
                case 's':
                    circleArray.showQueue();
                    break;
                case 'a':
                    circleArray.addQueue(sc.nextInt());
                    break;
                case 'g':
                    try {
                        int queue = circleArray.getQueue();
                        System.out.print("取出数据成功,");
                        System.out.println("根据先进先出原则取出的数据为"+queue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headQueue = circleArray.getHeadQueue();
                        System.out.println("队列头部数据为:"+headQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'l':
                    int size = circleArray.size();
                    System.out.printf("环形队列的有效数字个数为%d\n",size);
                    break;
                case 'e':
                    sc.close();
                    break IO;
                default:
                    break ;
            }
        }
        System.out.println("程序退出---");
    }
}
//数组模拟队列,编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize; // 表示数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 该数组用于存放数据，模拟队列

    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.front = -1;
        this.rear = -1;
        this.arr = new int[this.maxSize];
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;// 为空即true,不空为false
    }
    //判断队列是否存满
    public boolean isFull(){
        return maxSize==rear+1;
    }

    //添加方法,入列
    public void addQueue(int num){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列已满,无法继续添加");
            return;
        }
        //队列没满,添加数据进队列
        rear++;
        arr[rear]=num;
    }

    //出列方法
    public int getQueue(){
        //先判断是否为空
        if(isEmpty()){
            throw new RuntimeException("队列里面没有数据,无法取出");
        }
        //队列不空,取出数据
        front++;
        return arr[front];
    }

    //显示队列头部数据,不是取出数据
    public int getHeadQueue(){
        if(isEmpty()){
            throw  new RuntimeException("队列为空,队列头部数据不存在!");
        }
        return arr[front+1];
    }
    //显示全部数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空,里面没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            //从头到尾打印队列每一个数字
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
}
/*
* 优化思路如下:
1.  front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
front 的初始值 = 0
2.  rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
rear 的初始值 = 0
3. 当队列满时，条件是  (rear  + 1) % maxSize == front 【满】
4. 对队列为空的条件， rear == front 空
5. 当我们这样分析， 队列中有效的数据的个数   (rear + maxSize - front) % maxSize   // rear = 1 front = 0
6. 综上，就可以在原来的队列上修改得到，一个环形队列。*/
class CircleArray {
    private int maxSize; // 表示数组的最大容量
    private int front; // 队列头,默认指向队列的第一个元素,初始值为0
    private int rear; // 默认只想队列的最后一个元素的后一个位置
    private int[] arr; // 该数组用于存放数据，模拟队列

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
    }

    //判断队列是否存满
    public boolean isFull(){
        //模拟环形数列,是否存满的条件就是real的下一个位置是不是又回到了front,具体代码逻辑如下
        return (rear+1)%maxSize==front;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }
    //队列添加元素
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满,无法继续添加");
            return;
        }
        arr[rear]=n;
        rear = (rear + 1) % maxSize; // 将 rear 后移, 这里必须考虑取模
    }
    //队列取出元素
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空,无法取出数据");
        }
        int num = arr[front];
        front=(front+1)%maxSize;
        return num;
    }
    //显示队列所有元素
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列里面数据为空");
            return;
        }
        for (int i = 0; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }
    //显示队列头部数据
    public int getHeadQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
    // 求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}
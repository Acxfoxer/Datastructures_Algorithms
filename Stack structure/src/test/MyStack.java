package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//数组模拟栈
class SimulationStack {
    //定义数组容量
    private int[] arr;
    //栈中有效元素个数
    private int size;//默认为0
    public SimulationStack(){
        //构造方法中定义栈的数据容量
        arr=new int[20];
    }

    //判断栈中元素是否存满
    public boolean isFull(){
        if(this.size==this.arr.length){
            return true;
        };
        return false;
    }

    //压栈
    public void push(int num){
        //栈满了进行扩容,调用Arrays.copy0f方法,每次扩容一倍
        if(isFull()){
            this.arr = Arrays.copyOf(this.arr,2*arr.length);
        }
        //没满,将元素存入栈顶,有效元素加一
        arr[this.size++]=num;
    }

    //获取栈顶元素peek(),不属于弹栈,需要判断栈是否为空
    public int peek(){
        if(isEmpty()){
            //抛出异常
            System.out.println("栈为空,无法取值");
            return -1;
        }
        return this.arr[this.size-1];

    }

    //判断栈是否为空
    public boolean isEmpty(){
        return this.size == 0;
    }

    //弹栈,同样需要判断栈是否为空
    public int pop(){
        if(isEmpty()){
            //抛出异常
            System.out.println("栈为空,无法取值");
            return -1;
        }
        int topNum = this.arr[this.size - 1];
        this.size--;
        return topNum;
    }

    //java自带栈结构
    public static void test(){
        Stack<Integer> stack = new Stack<>();//创建一个栈
        //向栈中存放1，2，3个元素
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());//获取栈顶元素 但是不删除 结果为3
        System.out.println(stack.pop());//弹出栈顶元素 删除  结果为3
        System.out.println(stack.peek());//获取栈顶元素 但是不删除 结果为2
        System.out.println(stack.empty());//判断栈中元素是否为空
        System.out.println(stack.isEmpty());//判断是否为空
    }
}
public class MyStack{
    public static void main(String[] args) {
        //创建数组模拟栈的对象
        SimulationStack simulationStack = new SimulationStack();
        //压栈
        simulationStack.push(2);
        simulationStack.push(3);
        simulationStack.push(4);
        simulationStack.push(5);
        simulationStack.push(6);
        simulationStack.push(7);
        simulationStack.push(8);
        simulationStack.push(9);
        simulationStack.push(10);
        simulationStack.push(11);
        //取栈顶元素,不会删除栈顶原来的元素
        System.out.println(simulationStack.peek());
        System.out.println(simulationStack.peek());//2次都是11
        //定义一个集合来接受数据
        List<Integer> list= new ArrayList<>();
        //弹栈会删除元素
        for (int i = 0; i < 30; i++) {
            int pop = simulationStack.pop();
            if (pop==-1){
                break;
            }
            list.add(pop);
        }
        //结果为压栈的逆序
        System.out.println(list);
        String str ="()()";
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            System.out.println(aChar);
        }
    }

}
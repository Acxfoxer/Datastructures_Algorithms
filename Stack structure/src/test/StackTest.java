package test;

import java.util.Arrays;
import java.util.Random;

//栈的存储结构
class StackTest {
    public int[] elem;//开辟一个栈的空间
    public int usedSize;//栈实际用的长度

    public StackTest() {
        this.elem = new int[10];//设置栈的空间为10个元素的空间
    }

    //push往栈中存放元素要判断栈满没满
    public boolean isFull() {
        //数据个数==长度了
        if (this.usedSize == this.elem.length) {
            return true;
        }
        return false;
    }

    public void push(int item) {
        if (isFull()) {
            //满了进行扩容
            this.elem = Arrays.copyOf(this.elem, 2 * elem.length);
        }
        //没满，存放到数组最后的位置
        this.elem[this.usedSize] = item;
        this.usedSize++;
    }


    //pop删除栈顶元素要判断栈是否为空
    public boolean empty(){
        //数据个数为空的时候
        return this.usedSize==0;
    }

    public int pop() throws RuntimeException {
        if (empty()) {
            throw new RuntimeException("栈空了");
        }
        int val=this.elem[this.usedSize-1];
        this.usedSize--;
        return val;
    }

    //获取栈顶元素
    public int peek(){
        if(empty()){
            throw new RuntimeException("栈空了");
        }
        return this.elem[this.usedSize-1];
    }


    public static void main(String[] args) {
        StackTest myStack = new StackTest();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.peek());//获取栈顶元素 但是不删除 结果为3
        System.out.println(myStack.pop());//弹出栈顶元素 删除  结果为3
        System.out.println(myStack.peek());//获取栈顶元素 但是不删除 结果为2
        System.out.println(myStack.empty());//结果为false
        System.out.println(myStack.pop());//2
        System.out.println(myStack.pop());//1
        System.out.println(myStack.pop());//证明为空报异常
        String str = "123";

    }
}

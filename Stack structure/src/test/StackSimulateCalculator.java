package test;


import test.utils.Utils;

import java.util.ArrayList;
import java.util.List;

//中缀表达式
public class StackSimulateCalculator {
    static ArrayStack numStack = new ArrayStack(15);
    static ArrayStack operStack = new ArrayStack(15);
    public static void main(String[] args) {
        //创建工具类对象
        Utils utils = new Utils();
    //实现一个计算器,式子如String str = 75-42/2+3*5-22-15+1,使用栈的思路,这不考虑小括号
        String str = "75-42/2+3*5-22-15+1";
        //创建符号栈,跟数字栈
        ArrayStack operStack = new ArrayStack(15);
        ArrayStack numStack = new ArrayStack(15);
        //去除空格
        String ss = str.replaceAll(" ", "");
        //转换成字符数组
        char[] chars = ss.toCharArray();
        int index=0;//字符数组索引
        String s ="";//拼接数字
        char ch = '0';//定义每次入参的字符,初始化为0
        int first =0;//数字栈先出栈的数字
        int last  = 0; //数字栈后出栈的数字
        int oper = 0;//符号栈出栈的栈顶符号
        int result = 0;//计算完毕的结果
        //循环将数字与运算符放入各自的栈中,出循环条件,索引指向数组最后一个元素
        while (index<=chars.length-1){
            ch = chars[index];
            //判断第一位是不是操作符
            if(utils.isOperator(ch)){
                //如果索引为0,0压入数字栈
                if(index==0){
                    numStack.push(0);
                }
                //先判断符号栈是否为空
                if(!operStack.isEmpty()){
                    //不为空,则判断符号栈中栈顶符号与将要入栈符号优先级
                    if(utils.priority(ch)>= operStack.peek()){
                        //将要入栈的大于栈顶则入栈
                        operStack.push(ch);
                    }else {
                        //如果小于,则符号栈弹出栈顶符号,数字栈弹出2个数字运算完毕在将结果放入数字栈
                        first=numStack.pop();
                        last = numStack.pop();
                        oper= operStack.pop();
                        //调用计算方法
                        result =utils.cal(first,last,oper);
                        //计算完毕的结果再入数字栈
                        numStack.push(result);
                    }
                }
                else {
                    //为空,符号直接入符号栈栈
                    operStack.push(ch);
                }
            } else {
                //为数字,拼接字符串
                s+=ch;
                //判断当前索引位置,在数组最后以为,直接入数字栈
                if(index==chars.length-1){
                    numStack.push(Integer.parseInt(s));
                }else {
                    //当下一个为符号时,则将拼接的数字入数字栈
                    if(utils.isOperator(chars[index+1])){
                        numStack.push(Integer.parseInt(s));
                        //入数字栈后,重置s
                        s="";
                    }
                }
            }
            index++;
        }
        //上面循环结束后,就直接取出数字栈,符号栈进行计算
        //符号栈为空则停止循环
        while (!operStack.isEmpty()){
            first=numStack.pop();
            last = numStack.pop();
            oper=operStack.pop();
            result =  utils.cal(first,last,oper);
            //将结果压入数字栈
            numStack.push(result);
        }
        //循环再次结束,弹出数字栈栈顶即为结果
        result = numStack.pop();
        System.out.println("计算结果="+result);
    }

}
// 定义一个类,表示栈
class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组,模拟栈,数据就放在该数组
    private int top = -1; // top表示栈,初始化为-1

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈满了！！！");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈-pop,将栈顶的数据返回
    public int pop() {
        // 先判断栈是否空
        if (isEmpty()) {
            // 抛出异常
            System.out.println("栈为空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈的情况[便利栈],遍历时,需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据！！！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
    //显示栈顶元素
    public int peek(){
        if (isEmpty()) {
            System.out.println("栈空,没有数据！！！");
        }
        return stack[top];
    }

}

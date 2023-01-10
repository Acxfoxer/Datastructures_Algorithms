package test;

import test.utils.Utils;


import java.util.Arrays;
import java.util.List;


//逆波兰表达式
public class PolandNotation {
    static Utils utils = new Utils();
    public static void main(String[] args) {
        ArrayStack1 stack = new ArrayStack1(15);
        //传入逆波兰表达式(后缀表达式)
        String suffixExpression = "3 4 + 5 x 6 -";
        //调整后的字符串
        String string = suffixExpression.replaceAll(" ", "");
        //利用Stream流转为list集合
        List<String> list = Arrays.stream(string.split("")).toList();
        //调用计算方法
        int result = calculator(list);
        System.out.println(list);
        System.out.println(result);
    }
    //计算方法
    //逆波兰表达式的计算方法
    //1.从左到右扫描,将数字压入栈
    //2.遇到运算符,弹出栈顶,与次栈顶,计算后,结果在入栈
    //3。重复2,直到求得最终结果
    public static int calculator(List<String> list){
        ArrayStack1 numStack = new ArrayStack1(15);
        for (String str : list) {
            if(str.matches("\\d+")){//d表示0-9,后面跟+表示一个或者多个
                numStack.push(Integer.parseInt(str));
            }else {
                int first = numStack.pop();
                int last = numStack.pop();
                int res =0;//记录运算结果
                switch (str) {
                    case "+" -> res = last + first;
                    case "-" -> res = last - first;
                    case "x" -> res = last * first;
                    case "/" -> res = last / first;
                    default -> {
                        throw new ArithmeticException("表达式有误");
                    }
                }
                //switch结束后,将结果压入数栈
                numStack.push(res);
            }
        }
        return numStack.pop();
    }
}
//自定义类,闪现栈
class ArrayStack1{
    private int[] arrStack;//数组模拟栈,数据放入数组内
    private int size;//栈的容量->这表示数组容量
    private int top =-1;//表示索引,初始为-1,表示为空

    public ArrayStack1(int size) {
        this.size = size;
        this.arrStack = new int[size];
    }
    //判断是否为空
    public boolean isEmpty(){
        return top==-1;
    }
    //判断是否满
    public boolean isFull(){
        return top==size-1;
    }
    //入栈
    public void push(int num){
        //先判断是否为存满
        if(isFull()){
            System.out.println("已满,无法入栈");
        }else {
            top++;
            arrStack[top]=num;
        }
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            System.out.println("栈空,无法出栈");
        }
        int value = arrStack[top];
        top--;
        return value;
    }

    //查看栈顶元素
    public int peek(){
        if(isEmpty()){
            System.out.println("栈空,无法出栈");
        }
        return arrStack[top];
    }
}
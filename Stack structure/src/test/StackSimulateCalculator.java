package test;


import test.utils.Utils;

import java.util.ArrayList;
import java.util.List;

//��׺���ʽ
public class StackSimulateCalculator {
    static ArrayStack numStack = new ArrayStack(15);
    static ArrayStack operStack = new ArrayStack(15);
    public static void main(String[] args) {
        //�������������
        Utils utils = new Utils();
    //ʵ��һ��������,ʽ����String str = 75-42/2+3*5-22-15+1,ʹ��ջ��˼·,�ⲻ����С����
        String str = "75-42/2+3*5-22-15+1";
        //��������ջ,������ջ
        ArrayStack operStack = new ArrayStack(15);
        ArrayStack numStack = new ArrayStack(15);
        //ȥ���ո�
        String ss = str.replaceAll(" ", "");
        //ת�����ַ�����
        char[] chars = ss.toCharArray();
        int index=0;//�ַ���������
        String s ="";//ƴ������
        char ch = '0';//����ÿ����ε��ַ�,��ʼ��Ϊ0
        int first =0;//����ջ�ȳ�ջ������
        int last  = 0; //����ջ���ջ������
        int oper = 0;//����ջ��ջ��ջ������
        int result = 0;//������ϵĽ��
        //ѭ���������������������Ե�ջ��,��ѭ������,����ָ���������һ��Ԫ��
        while (index<=chars.length-1){
            ch = chars[index];
            //�жϵ�һλ�ǲ��ǲ�����
            if(utils.isOperator(ch)){
                //�������Ϊ0,0ѹ������ջ
                if(index==0){
                    numStack.push(0);
                }
                //���жϷ���ջ�Ƿ�Ϊ��
                if(!operStack.isEmpty()){
                    //��Ϊ��,���жϷ���ջ��ջ�������뽫Ҫ��ջ�������ȼ�
                    if(utils.priority(ch)>= operStack.peek()){
                        //��Ҫ��ջ�Ĵ���ջ������ջ
                        operStack.push(ch);
                    }else {
                        //���С��,�����ջ����ջ������,����ջ����2��������������ڽ������������ջ
                        first=numStack.pop();
                        last = numStack.pop();
                        oper= operStack.pop();
                        //���ü��㷽��
                        result =utils.cal(first,last,oper);
                        //������ϵĽ����������ջ
                        numStack.push(result);
                    }
                }
                else {
                    //Ϊ��,����ֱ�������ջջ
                    operStack.push(ch);
                }
            } else {
                //Ϊ����,ƴ���ַ���
                s+=ch;
                //�жϵ�ǰ����λ��,�����������Ϊ,ֱ��������ջ
                if(index==chars.length-1){
                    numStack.push(Integer.parseInt(s));
                }else {
                    //����һ��Ϊ����ʱ,��ƴ�ӵ�����������ջ
                    if(utils.isOperator(chars[index+1])){
                        numStack.push(Integer.parseInt(s));
                        //������ջ��,����s
                        s="";
                    }
                }
            }
            index++;
        }
        //����ѭ��������,��ֱ��ȡ������ջ,����ջ���м���
        //����ջΪ����ֹͣѭ��
        while (!operStack.isEmpty()){
            first=numStack.pop();
            last = numStack.pop();
            oper=operStack.pop();
            result =  utils.cal(first,last,oper);
            //�����ѹ������ջ
            numStack.push(result);
        }
        //ѭ���ٴν���,��������ջջ����Ϊ���
        result = numStack.pop();
        System.out.println("������="+result);
    }

}
// ����һ����,��ʾջ
class ArrayStack {
    private int maxSize; // ջ�Ĵ�С
    private int[] stack; // ����,ģ��ջ,���ݾͷ��ڸ�����
    private int top = -1; // top��ʾջ,��ʼ��Ϊ-1

    // ������
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // ջ��
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // ջ��
    public boolean isEmpty() {
        return top == -1;
    }

    // ��ջ
    public void push(int value) {
        // ���ж�ջ�Ƿ���
        if (isFull()) {
            System.out.println("ջ���ˣ�����");
            return;
        }
        top++;
        stack[top] = value;
    }

    // ��ջ-pop,��ջ�������ݷ���
    public int pop() {
        // ���ж�ջ�Ƿ��
        if (isEmpty()) {
            // �׳��쳣
            System.out.println("ջΪ��");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // ��ʾջ�����[����ջ],����ʱ,��Ҫ��ջ����ʼ��ʾ����
    public void list() {
        if (isEmpty()) {
            System.out.println("ջ��,û�����ݣ�����");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
    //��ʾջ��Ԫ��
    public int peek(){
        if (isEmpty()) {
            System.out.println("ջ��,û�����ݣ�����");
        }
        return stack[top];
    }

}

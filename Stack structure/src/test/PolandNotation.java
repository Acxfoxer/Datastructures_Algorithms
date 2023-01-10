package test;

import test.utils.Utils;


import java.util.Arrays;
import java.util.List;


//�沨�����ʽ
public class PolandNotation {
    static Utils utils = new Utils();
    public static void main(String[] args) {
        ArrayStack1 stack = new ArrayStack1(15);
        //�����沨�����ʽ(��׺���ʽ)
        String suffixExpression = "3 4 + 5 x 6 -";
        //��������ַ���
        String string = suffixExpression.replaceAll(" ", "");
        //����Stream��תΪlist����
        List<String> list = Arrays.stream(string.split("")).toList();
        //���ü��㷽��
        int result = calculator(list);
        System.out.println(list);
        System.out.println(result);
    }
    //���㷽��
    //�沨�����ʽ�ļ��㷽��
    //1.������ɨ��,������ѹ��ջ
    //2.���������,����ջ��,���ջ��,�����,�������ջ
    //3���ظ�2,ֱ��������ս��
    public static int calculator(List<String> list){
        ArrayStack1 numStack = new ArrayStack1(15);
        for (String str : list) {
            if(str.matches("\\d+")){//d��ʾ0-9,�����+��ʾһ�����߶��
                numStack.push(Integer.parseInt(str));
            }else {
                int first = numStack.pop();
                int last = numStack.pop();
                int res =0;//��¼������
                switch (str) {
                    case "+" -> res = last + first;
                    case "-" -> res = last - first;
                    case "x" -> res = last * first;
                    case "/" -> res = last / first;
                    default -> {
                        throw new ArithmeticException("���ʽ����");
                    }
                }
                //switch������,�����ѹ����ջ
                numStack.push(res);
            }
        }
        return numStack.pop();
    }
}
//�Զ�����,����ջ
class ArrayStack1{
    private int[] arrStack;//����ģ��ջ,���ݷ���������
    private int size;//ջ������->���ʾ��������
    private int top =-1;//��ʾ����,��ʼΪ-1,��ʾΪ��

    public ArrayStack1(int size) {
        this.size = size;
        this.arrStack = new int[size];
    }
    //�ж��Ƿ�Ϊ��
    public boolean isEmpty(){
        return top==-1;
    }
    //�ж��Ƿ���
    public boolean isFull(){
        return top==size-1;
    }
    //��ջ
    public void push(int num){
        //���ж��Ƿ�Ϊ����
        if(isFull()){
            System.out.println("����,�޷���ջ");
        }else {
            top++;
            arrStack[top]=num;
        }
    }
    //��ջ
    public int pop(){
        if(isEmpty()){
            System.out.println("ջ��,�޷���ջ");
        }
        int value = arrStack[top];
        top--;
        return value;
    }

    //�鿴ջ��Ԫ��
    public int peek(){
        if(isEmpty()){
            System.out.println("ջ��,�޷���ջ");
        }
        return arrStack[top];
    }
}
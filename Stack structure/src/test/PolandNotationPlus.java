package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//��׺���ʽת��׺���ʽ,
public class PolandNotationPlus {
    public static void main(String[] args) {
        String s = "12+((2+3)*43)-5";
        List<String> list = toComboNum(s);
        List<String> suffixExpressionList = parseSuffixExpressionList(list);
        int result = calculator(suffixExpressionList);
        System.out.println(result);

    }
    /*
    ��׺���ʽת��׺���ʽ����,
    ˫ջ,ջ�Ӷ���\����
    1) ��ʼ������ջ: �����ջ s1 �ʹ����м�����ջ s2;
    2) ��������ɨ����׺���ʽ;
    3) ����������ʱ������ѹ s2;
    4) ���������ʱ���Ƚ����� s1 ջ������������ȼ�:
    1.��� s1 Ϊ�գ���ջ�������Ϊ�����š�(������ֱ�ӽ����������ջ;
    2.���������ȼ���ջ��������ĸߣ�Ҳ�������ѹ�� s1;
    3.���򣬽� s1 ջ���������������ѹ�뵽 s2 �У��ٴ�ת��(4-1)�� s1 ���µĶ��������Ƚ�
    5) ��������ʱ:
    (1) ����������š�(������ֱ��ѹ�� s1
    (2)����������š�)���������ε��� s1  �������������ѹ�� s2��ֱ������������Ϊֹ����ʱ����һ�����Ŷ���
    6) �ظ����� 2 �� 5��ֱ�����ʽ�����ұ�
    7) �� s1 ��ʣ�����������ε�����ѹ�� s2
    8) ���ε��� s2 �е�Ԫ�ز���������������Ϊ��׺���ʽ��Ӧ�ĺ�׺��
     */
    public static List<String> parseSuffixExpressionList(List<String> list){
        //��ʼ��һ������ջ
        Stack<String> stack = new Stack<>();
        //ʵ����һ�����϶���
        List<String> list1 = new ArrayList<>();
        for (String s : list) {
            //������ʽ,�ж��Ƿ�������
            if(s.matches("\\d+")){
                list1.add(s);
            }else if(s.equals("(")){//�Ƿ���С����,С����ֱ����ջ
                    stack.push(s);
            }else if(s.equals(")")){//��������,�����ջ��ȫ����ջ,����ӵ�������,ֱ������(Ϊ��
                while (!stack.peek().equals("(")){
                    list1.add(stack.pop());
                }
                //ѭ������,����ջ����С����
                stack.pop();
            }else {
                //����������������,����Ҫ������ջ��ÿһ��Ԫ�ؽ��бȽ�
                while (stack.size()!=0&&calPriority(stack.peek())>=calPriority(s)){
                    //����ջ��ջ,����ӵ�������,
                        list1.add(stack.pop());
                }
                stack.push(s);
            }
        }
        //ѭ������,������ջʣ��ĵ���,����ӵ�����
        while (stack.size()!=0){
            list1.add(stack.pop());
        }
        return list1;
    }
    //ƴ�Ӷ������
    public static  List<String> toComboNum(String str){
        String ss = str.replaceAll(" ", "");
        char[] chars = ss.toCharArray();

        List<String> list = new ArrayList<>();
        int index=0;//����ָ��
        while (index<chars.length){
            if(chars[index] <48||chars[index]>57){
                //�������ʾ�������
                list.add(chars[index] +"");
                index++;
            }else {
                String string = "";
                //���������,��Ҫ���Ƕ�λ��
                    while (index < chars.length&&chars[index] >= 48 && chars[index] <= 57) {
                        string+=chars[index];
                        index++;
                    };
                list.add(string);
                }
            }
        return list;
    }
    //�������ȼ��ķ���
    public static int calPriority(String s){
        int priority=0;//���ȼ�
        switch (s) {
            case "+" -> priority = 0;
            case "-" -> priority = 0;
            case "*" -> priority = 1;
            case "/" -> priority = 1;
            case "(" -> priority = -1;
            default -> {
                System.out.println("����δ֪�����,������");
                break;
            }
        }
        return priority;
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
                    case "*" -> res = last * first;
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

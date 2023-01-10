package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//中缀表达式转后缀表达式,
public class PolandNotationPlus {
    public static void main(String[] args) {
        String s = "12+((2+3)*43)-5";
        List<String> list = toComboNum(s);
        List<String> suffixExpressionList = parseSuffixExpressionList(list);
        int result = calculator(suffixExpressionList);
        System.out.println(result);

    }
    /*
    中缀表达式转后缀表达式方法,
    双栈,栈加队列\集合
    1) 初始化两个栈: 运算符栈 s1 和储存中间结果的栈 s2;
    2) 从左至右扫描中缀表达式;
    3) 遇到操作数时，将其压 s2;
    4) 遇到运算符时，比较其与 s1 栈顶运算符的优先级:
    1.如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈;
    2.否则，若优先级比栈顶运算符的高，也将运算符压入 s1;
    3.否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4-1)与 s1 中新的顶运算符相比较
    5) 遇到括号时:
    (1) 如果是左括号“(”，则直接压入 s1
    (2)如果是右括号“)”，则依次弹出 s1  顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
    6) 重复步骤 2 至 5，直到表达式的最右边
    7) 将 s1 中剩余的运算符依次弹出并压入 s2
    8) 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表
     */
    public static List<String> parseSuffixExpressionList(List<String> list){
        //初始化一个符号栈
        Stack<String> stack = new Stack<>();
        //实例化一个集合对象
        List<String> list1 = new ArrayList<>();
        for (String s : list) {
            //正则表达式,判断是否是数字
            if(s.matches("\\d+")){
                list1.add(s);
            }else if(s.equals("(")){//是否是小括号,小括号直接入栈
                    stack.push(s);
            }else if(s.equals(")")){//是右括号,则符号栈的全部出栈,并添加到集合中,直到遇到(为主
                while (!stack.peek().equals("(")){
                    list1.add(stack.pop());
                }
                //循环结束,符号栈弹出小括号
                stack.pop();
            }else {
                //上诉条件都不满足,则需要跟符号栈的每一个元素进行比较
                while (stack.size()!=0&&calPriority(stack.peek())>=calPriority(s)){
                    //符号栈出栈,并添加到集合中,
                        list1.add(stack.pop());
                }
                stack.push(s);
            }
        }
        //循环结束,将符号栈剩余的弹出,并添加到集合
        while (stack.size()!=0){
            list1.add(stack.pop());
        }
        return list1;
    }
    //拼接多个数字
    public static  List<String> toComboNum(String str){
        String ss = str.replaceAll(" ", "");
        char[] chars = ss.toCharArray();

        List<String> list = new ArrayList<>();
        int index=0;//定义指针
        while (index<chars.length){
            if(chars[index] <48||chars[index]>57){
                //满足则表示是运算符
                list.add(chars[index] +"");
                index++;
            }else {
                String string = "";
                //如果是数字,需要考虑多位数
                    while (index < chars.length&&chars[index] >= 48 && chars[index] <= 57) {
                        string+=chars[index];
                        index++;
                    };
                list.add(string);
                }
            }
        return list;
    }
    //计算优先级的方法
    public static int calPriority(String s){
        int priority=0;//优先级
        switch (s) {
            case "+" -> priority = 0;
            case "-" -> priority = 0;
            case "*" -> priority = 1;
            case "/" -> priority = 1;
            case "(" -> priority = -1;
            default -> {
                System.out.println("存在未知运算符,请重试");
                break;
            }
        }
        return priority;
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
                    case "*" -> res = last * first;
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

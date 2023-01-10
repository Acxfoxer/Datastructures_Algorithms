package test.utils;

public class Utils {
    //判断是不是运算符
    public boolean isOperator(char ch){
        return ch=='+'||ch=='-'||ch=='*'||ch=='/';
    }
    //判断是不是左括号
    public boolean isLKuo(char ch){
        return ch=='('||ch==')';
    }
    //判断运算符的优先级
    public int priority(int ch){
        if(ch=='('){
            return 3;
        }
        if(ch==')'){
            return 2;
        }
        if(ch=='*'||ch=='/'){
            return 1;
        }else if(ch=='+'||ch=='-'){
            return 0;
        }else {
            return -1;
        }
    }
    //判断字符是不是数字
    public boolean isNum(char ch){
        return (ch-48)>0&&(ch-48)<9;
    }



    /**
     * 计算方法
     * @param firstNum 数字栈先出栈的
     * @param lastNum  数字栈后出栈的
     * @param oper     运算符
     * @return  返回计算后的结果
     */
    public int cal(int firstNum ,int lastNum,int oper){
        int result=0;
        switch (oper) {
            case '+' -> result = firstNum + lastNum;
            case '-' -> result = lastNum - firstNum;    //注意顺序
            case '*' -> result = firstNum * lastNum;
            case '/' -> result = lastNum / firstNum;
            default -> {
            }
        }
        return result;
    }
}

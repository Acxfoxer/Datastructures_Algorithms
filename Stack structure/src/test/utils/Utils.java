package test.utils;

public class Utils {
    //�ж��ǲ��������
    public boolean isOperator(char ch){
        return ch=='+'||ch=='-'||ch=='*'||ch=='/';
    }
    //�ж��ǲ���������
    public boolean isLKuo(char ch){
        return ch=='('||ch==')';
    }
    //�ж�����������ȼ�
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
    //�ж��ַ��ǲ�������
    public boolean isNum(char ch){
        return (ch-48)>0&&(ch-48)<9;
    }



    /**
     * ���㷽��
     * @param firstNum ����ջ�ȳ�ջ��
     * @param lastNum  ����ջ���ջ��
     * @param oper     �����
     * @return  ���ؼ����Ľ��
     */
    public int cal(int firstNum ,int lastNum,int oper){
        int result=0;
        switch (oper) {
            case '+' -> result = firstNum + lastNum;
            case '-' -> result = lastNum - firstNum;    //ע��˳��
            case '*' -> result = firstNum * lastNum;
            case '/' -> result = lastNum / firstNum;
            default -> {
            }
        }
        return result;
    }
}

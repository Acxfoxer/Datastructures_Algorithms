package SingleLinkedList;

import java.util.ArrayList;
import java.util.Stack;

//�ٶ�������
//��β��ͷ��ӡ������ ���ٶȣ�Ҫ��ʽ1��������� �� ��ʽ2��Stackջ��
public class ReversePrint {
    public static void main(String[] args) {

    }
}
class Solution3{
    //�ⷨһ:ջ����취
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        int count=0;
        while (head!=null){
            stack.push(head);
            head=head.next;
            count++;
        }
        int[] newArr = new int[count];
        for (int i = 0; i <count ; i++) {
            newArr[i]=stack.pop().val;
        }
        return newArr;
    }
    //�������ܵݹ������
    ArrayList<Integer> tmp = new ArrayList<Integer>();
    //�ⷨ��:�ݹ�
    public int[] reversePrint1(ListNode head) {
        recursion(head);
        int[] arr = new int[tmp.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=tmp.get(i);
        }
        return arr;
    }
    //�����ݹ麯��,��������
    void recursion(ListNode head){
        if(head==null){
            return;
        }
        recursion(head.next);
        tmp.add(head.val);
    }
}

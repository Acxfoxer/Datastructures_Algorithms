package SingleLinkedList;

import java.util.ArrayList;
import java.util.Stack;

//百度面试题
//从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
public class ReversePrint {
    public static void main(String[] args) {

    }
}
class Solution3{
    //解法一:栈解决办法
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
    //用来接受递归的数据
    ArrayList<Integer> tmp = new ArrayList<Integer>();
    //解法二:递归
    public int[] reversePrint1(ListNode head) {
        recursion(head);
        int[] arr = new int[tmp.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=tmp.get(i);
        }
        return arr;
    }
    //创建递归函数,返回数组
    void recursion(ListNode head){
        if(head==null){
            return;
        }
        recursion(head.next);
        tmp.add(head.val);
    }
}

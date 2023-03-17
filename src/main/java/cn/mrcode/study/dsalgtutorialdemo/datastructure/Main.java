package cn.mrcode.study.dsalgtutorialdemo.datastructure;
public class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        ListNode listNode5 = new ListNode(2);
        ListNode listNode6 = new ListNode(4);
        ListNode listNode7 = new ListNode(5);
        listNode5.next=listNode6;
        listNode6.next=listNode7;
        ListNode listNode = solution.mergeTwoLists(listNode1, listNode5);
        while (listNode!= null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //设置一个哑节点 作为 结果链表 的开头
        ListNode temp = new ListNode(0);
        ListNode move = temp;
        ListNode l1 = list1;
        ListNode l2 = list2;
        while (l1!=null && l2!=null){
            //找到开头元素
            if (l1.val > l2.val) {
                move.next = l2;
                l2 = l2.next;
            }else  {
                move.next = l1;
                l1 = l1.next;
            }
            move = move.next;
        }
        if (l1!=null) {
            move.next = l1;
        }
        if (l2!=null) {
            move.next = l2;
        }
        return temp.next;
    }
}
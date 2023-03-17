package cn.mrcode.study.dsalgtutorialdemo.datastructure.linkedlist;

/**
 * 双向链表测试
 */
public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        ListNode hero1 = new ListNode(1, "宋江", "及时雨");
        ListNode hero2 = new ListNode(2, "卢俊义", "玉麒麟");
        ListNode hero3 = new ListNode(3, "吴用", "智多星");
        ListNode hero4 = new ListNode(4, "林冲", "豹子头");

        // 测试新增
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.print();
    }
}

/**
 * 双向链表的操作实现
 */
class DoubleLinkedList {
    private ListNode head = new ListNode(0, "", "");

    /**
     * 添加：将节点添加到链表尾部
     *
     * @param node
     */
    public void add(ListNode node) {
        ListNode temp = this.head;
        // 找到链表的末尾
        while (temp.next!= null) {
            temp = temp.next;
        }
        // 将新节点添加到末尾的节点上
        temp.next = node;
        node.pre = temp;
    }


    /**
     * 打印链表
     */
    public void print() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        ListNode cur = head.next;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }
}

/**
 * 链表中的一个节点：英雄节点
 */
class ListNode {
    public int no;
    public String name;
    public String nickName;
    public ListNode next;
    public ListNode pre;

    public ListNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    /**
     * 为了显示方便，重写
     *
     * @return
     */
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

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
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.print();

        ListNode hero5 = new ListNode(4, "林冲33", "豹子头33");
        doubleLinkedList.update(hero5);
        doubleLinkedList.print();
        doubleLinkedList.delete(3);
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
        while (temp.next != null) {
            temp = temp.next;
        }
        // 将新节点添加到末尾的节点上
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 更新：以 id 匹配，更新链表中找到的节点；与单向链表的逻辑一样
     *
     * @param newNode
     */
    public void update(ListNode newNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        ListNode temp = this.head.next;
        while (temp != null) {
            if (temp.no == newNode.no) {
                temp.name = newNode.name;
                temp.nickName = newNode.nickName;
                break;
            }
            temp = temp.next;
        }
        if (temp == null) {
            System.out.printf("未找到编号为 %d 的英雄", newNode.no);
        }
    }

    /**
     * <pre>
     * 删除：按编号匹配，将其删除；
     *  思路：直接找到该节点，然后自我删除
     * </pre>
     *
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        ListNode temp = this.head.next;
        while (temp != null) {
            if (temp.no == no) {
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                temp.pre.next = temp.next;
                break;
            }
            temp = temp.next;
        }
        if (temp == null) {
            System.out.printf("未找到编号为 %d 的英雄", no);
        }
    }

    /**
     * <pre>
     *  按编号顺序添加
     *  思路：
     *     1. 从头遍历节点，
     *     2. 找到比目标大的节点：插入到该节点之前（升序）
     *     2. 如果已经存在相同编号的节点，则提示不允许添加
     *
     * </pre>
     *
     * @param node
     */
    public void addByOrder(ListNode node) {
        ListNode temp = head;
        boolean exist = false;  // 添加的节点是否已经在链表中存在

        while (true) {
            // 已到列表尾部
            if (temp.next == null) {
                break;
            }
            // 已找到
            if (temp.next.no > node.no) {
                break;
            }

            // 已存在该编号
            if (temp.next.no == node.no) {
                exist = true;
                break;
            }
            temp = temp.next;
        }
        if (exist) {
            System.out.printf("准备插入的英雄编号 %d 已经存在，不能加入 \n", node.no);
            return;
        }

        // 把节点插入到 temp 和 temp.next 之间
        // temp  ->  node  -> temp.next
        node.next = temp.next;
        node.pre = temp;
        temp.next = node;
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

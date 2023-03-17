package cn.mrcode.study.dsalgtutorialdemo.datastructure.linkedlist;

import java.util.Stack;

/**
 * 单向链表测试
 */

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);  // 添加顺序提前
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();
//        HeroNode hero5 = new HeroNode(6, "林冲33", "豹子头33");
//        singleLinkedList.update(hero5);
//        singleLinkedList.list();
//        singleLinkedList.delete(6);
//        singleLinkedList.list();
//        System.out.println(singleLinkedList.findLastIndexNode(1));
//        singleLinkedList.reverse();
//        singleLinkedList.list();
        singleLinkedList.reversePrint();

    }
}

/**
 * 单向链表
 */
class SingleLinkedList {
    //创建头节点
    // 头节点，不保存任何数据，只是用来作为一个起始点
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点无序实现
     * <pre>
     *     不考虑编号顺序时：
     *      1. 找到当前链表的最后节点
     *      2. 将最后整个节点的 next 指向新的节点
     * </pre>
     *
     * @param node
     */
    public void add(HeroNode node) {
        HeroNode temp = this.head;
        while (temp.next != null) {
            // 找到链表的最后,就退出循环
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * <pre>
     * 添加节点，考虑排序
     *  1. 先找到该节点要添加的位置
     *  2. 改变前一个和该节点的 next 指向
     * </pre>
     *
     * @param node
     */
    public void addByOrder(HeroNode node) {
        //由于head变量不能动，动了就无法从头遍历了，使用辅助变量完成添加
        HeroNode temp = this.head;
        //添加的节点是否已经在链表中存在
        boolean exist = false;
        while (temp.next != null) {
//            if (temp.next == null) {
//                //如果是链表尾,则跳出循环
//                break;
//            }
            //如果当前节点的next编号，大于目标节点编号，则找到
            //应该将目标节点添加到temp与next之间
            if (temp.next.no > node.no) {
                break;
            }
            //如果他们相等，则表示链表中已经存在目标节点了
            if (temp.next.no == node.no) {
                exist = true;
                break;
            }
            //没找到，则后移temp，继续寻找
            temp = temp.next;
        }
        if (exist) {
            System.out.printf("准备插入的英雄编号 %d 已经存在，不能加入 \n", node.no);
            return;
        }
        //把节点插入到temp和temp.next 之间
        //temp-> node -> temp.next
        node.next = temp.next;
        temp.next = node;

    }

    /**
     * 根据 no 属性找到匹配的节点，进行修改，但是不修改next节点
     *
     * @param newNode
     */
    public void update(HeroNode newNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = this.head;
        boolean exist = false;//是否找到要修改的节点
        while (temp.next != null) {
            if (temp.next.no == newNode.no) {
                temp.next.name = newNode.name;
                temp.next.nickName = newNode.nickName;
                exist = true;
                break;
            }
            temp = temp.next;
        }
        if (!exist) {
            System.out.printf("未找到编号为 %d 的好汉 \n", newNode.no);
        }
    }

    /**
     * <pre>
     *   按编号删除节点
     *   1. 找到要删除的前一个节点
     *   2. 然后将这个前一个节点的 next 指向变更到要删除节点的 next 节点
     * </pre>
     *
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean exist = false;  // 是否找到要删除的节点
        HeroNode temp = this.head;
        while (temp.next != null){
            if (temp.next.no==no){
                temp.next = temp.next.next;
                exist = true;
                break;
            }
            temp=temp.next;
        }
        if (!exist) {
            System.out.printf("未找到匹配的编号 %d \n", no);
            return;
        }
    }

    /**
     * 获取链表长度
     * @return
     */
    public int length() {
        if (head.next==null){
            return 0;
        }
        HeroNode temp = this.head.next;
        int sum = 0;
        while (temp != null) {
            sum++;
            temp=temp.next;
        }
        return sum;
    }

    /**
     * <pre>
     *  查找单链表中的倒数第 k 个结点
     *
     *  思路：
     *   1. 获得该链表节点的个数 size
     *   2. 从第一个节点循环开始，到 size-index 结束
     * <pre/>
     * @param index 倒数第几个节点
     * @return
     */
    public HeroNode findLastIndexNode(int index) {
        int size = length();
        if (size == 0) {
            return null;  // 空链表
        }

        // 校验 index
        if (index <= 0 || index > size) {
            return null;
        }

        HeroNode temp = this.head.next;
        // 循环查找
        for (int i = 0; i < size-index; i++) {
            temp=temp.next;
        }
        return temp;
    }

    /**
     * 单链表的反转
     *
     * 思路：
     *   1.定义一个新的 reverseHead 节点
     *   2.从原链表中依次取出节点，并 始终添加到 reverseHead 的第一个节点
     *   3.将原 head 节点的 next 指向 reverseHead.next
     */
    public void reverse() {
        if (this.head.next==null){
            return;
        }
        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode temp = this.head.next;
        HeroNode next = null;
        while (temp!=null){
            next=temp.next;
            temp.next = reverseHead.next;
            reverseHead.next=temp;
            temp=next;
        }
        this.head.next=reverseHead.next;
    }

    /**
     * <pre>
     * 逆序打印链表：使用栈先进后出的特点达到(不破坏原链表的结构)
     * </pre>
     */
    public void reversePrint() {
        if (this.head.next==null){
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = this.head.next;
        Stack<HeroNode> stack = new Stack<>();
        while (temp!= null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 打印链表中的数据
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = this.head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

/**
 * 链表中的一个节点：英雄节点
 */
@SuppressWarnings("all")
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
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
//                ", next=" + next + '\'' +
                '}';
    }
}
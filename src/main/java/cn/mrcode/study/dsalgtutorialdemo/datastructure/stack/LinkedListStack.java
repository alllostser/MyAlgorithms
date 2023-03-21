package cn.mrcode.study.dsalgtutorialdemo.datastructure.stack;

/**
 * 链表实现栈; 单向链表
 */
class LinkedListStackTest{
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.print();
//        stack.push(5);
        System.out.println("pop 数据：" + stack.pop2());
    }
}
public class LinkedListStack {
    int maxSize; // 最大支持数
    int size; // 当前栈中元素个数
    // 用来记录栈顶的元素
    Node top;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 是否已满
     *
     * @return
     */
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈已满");
        }
        Node node = new Node(value);
        node.next = top;
        top = node;
        size++;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int value = top.value;
        top = top.next;
        size--;
        return value;
    }


    /**
     * 出栈
     *
     * @return
     */
    public int pop2() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        // top 保存的是最后入栈的元素，直接从 top 取出即可
        Node temp = top;
        top = temp.next;
        size--;
        return temp.value;
    }



    /**
     * 显示栈中数据，从栈顶开始显示，也就是按出栈的顺序显示
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("栈已空");
            return;
        }
        Node cur = top;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }








    class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}

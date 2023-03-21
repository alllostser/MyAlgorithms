package cn.mrcode.study.dsalgtutorialdemo.datastructure.stack;

/**
 * 数组模拟栈
 */
public class ArrayStack {
    // 数据存储
    private final int[] data;
    // 栈顶位置
    private int top = -1;
    // 栈最大数量
    private final int size;
    public ArrayStack(int maxSize) {
        this.size = maxSize;
        data = new int[maxSize];
    }

    /**
     * 是否已满
     *
     * @return
     */
    public boolean isFull() {
        return size - 1 == top;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
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
        data[++top] = value;
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
        return data[top--];
    }

    /**
     * 获取栈顶元素
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return data[top];
    }

    /**
     * 显示栈中数据，从栈顶开始显示，也就是按出栈的顺序显示
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("栈中无数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("index=%d, value=%d \n", i, data[i]);
        }
    }

    static class ArrayStackTest {

        public static void main(String[] args) {
            ArrayStackTest stackTest = new ArrayStackTest();
            stackTest.pushTest();
        }

        public void pushTest() {
            ArrayStack stack = new ArrayStack(4);
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
            stack.print();
            stack.push(5);
        }


        public void popTest() {
            ArrayStack stack = new ArrayStack(4);
            stack.push(1);
            stack.push(2);
            stack.print();
            System.out.println("pop 数据：" + stack.pop());
            stack.print();
            System.out.println("pop 数据：" + stack.pop());
            stack.print();
        }
    }
}

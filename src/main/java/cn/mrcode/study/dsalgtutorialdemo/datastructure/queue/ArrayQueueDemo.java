package cn.mrcode.study.dsalgtutorialdemo.datastructure.queue;
/**
 * 数组模拟队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        queue.put(1);
        queue.put(2);
        queue.put(3);
        System.out.println("查看队列中的数据");
        queue.show();
        System.out.println("查看队列头数据：" + queue.head());
        System.out.println("查看队列尾数据：" + queue.tail());
//        queue.add(4);
        System.out.println("获取队列数据：" + queue.get());
        System.out.println("查看队列中的数据");
        queue.show();
        System.out.println("获取队列数据：" + queue.get());
        System.out.println("查看队列中的数据");
        queue.show();
        System.out.println("获取队列数据：" + queue.get());
        System.out.println("查看队列中的数据");
        queue.show();
    }
}

class ArrayQueue {
    private int maxSize; // 队列最大容量
    private int front; // 队列头,指向队列头的前一个位置
    private int rear; // 队列尾，指向队列尾的数据（及最后一个数据）
    private int arr[]; // 用于存储数据，模拟队列

    public ArrayQueue(int maxSize){
        this.maxSize=maxSize;
        this.arr = new int[maxSize];
        this.front=-1;
        this.rear=-1;
    }

    //队列添加元素
    public void put(int n){
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[++rear]=n;
    }
    //取出元素
    public int get(){
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[++front];
    }

    //队列是否已满
    public boolean isFull(){
        return rear==maxSize-1;
    }


    //队列是否为空
    public boolean isEmpty(){
        return front==rear;
    }

    /**
     * 显示队列中的数据
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front+1; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d \n", i, arr[i]);
        }
    }

    /**
     * 查看队列的头部数据，注意：不是取出数据，只是查看
     *
     * @return
     */
    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[front + 1]; // front 指向队列头前一个元素，取头要 +1
    }

    /**
     * 查看队尾数据
     *
     * @return
     */
    public int tail() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[rear];
    }
}
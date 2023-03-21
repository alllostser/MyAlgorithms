package cn.mrcode.study.dsalgtutorialdemo.datastructure.josepfu;
/*
约瑟夫（Josephu）问题，也就是丢手帕问题，他的规则如下
有编号为 1 ~ n 的 n 个人围坐在一起
约定编号为 K( 1 <= k <=n) 的人从 1 开始报数
数到 m 的那个人出列，它的下一位又从 1 开始报数
循环以上过程，直到所有人都出列，并列出出列人的编号。
该问题其实可以使用 **单循环链表（单向环形链表）**来解决，思路如下：
    1.先构成一个有 n 个节点的单循环链表
    2.然后由 k 节点起从 1 开始计数
    3.计数到 m 时，对应节点从链表中删除，然后从下一个节点又从 1 开始计数
循环以上过程，直到最后一个节点从链表中删除，算法结束
 */

/**
 * 单向环形链表实现
 */
public class CircleSingleLinkedList {
    Boy first = null;

    /**
     * 添加几个小孩：这里的添加至少用于初始化时，构建一个约舍夫丢手帕的 n 个孩子，与传统的入队列还不一样
     *
     * @param nums
     */
    public void add(int nums) {
        if (nums < 1) {
            System.out.println("至少要添加一个");
            return;
        }
        Boy cur = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            // 初始化 first 节点
            if (first == null) {
                first = boy;
                boy.next = first; // 自己和自己构成环状
                cur = first;
                continue;
            }
            if (cur != null) {
                cur.next = boy;
            }
            boy.next = first;
            cur = boy;
        }
    }

    /**
     * 游戏开始，计算出圈顺序
     *
     * @param startNo  从第几个小孩开始数
     * @param countNum 数几下
     * @param nums     参与该游戏的小孩有多少个
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 进行一个数据校验
        if (first == null ||  // 环形队列没有构建
                countNum < 1 ||  // 每次至少数 1 下
                startNo > nums  // 开始小孩不能大于参与游戏的人数
        ) {
            System.out.println("参数有误，请重新输入");
        }
        // 1. 初始化辅助变量到  first 的后面
        Boy helper = first;
        // 当 helper.next = first 时，就说明已经定位了
        while (helper.next != first) {
            helper = helper.next;
        }

        // 2. 定位 first 和 helper 在 startNo 位置
        // first 初始在最开始，移动到 startNo 位置
        for (int i = 0; i < startNo - 1; i++) {
            helper = first;
            first = first.next;
        }

        // 为了测试方便，这里添加一个日志输出
        System.out.printf("定位到位置： %d \n", startNo);
        print();

        // 3. 开始报数 和 出圈
        while (true) {
            // 当队列中只剩下一个人的时候，跳出循环，因为最后一个必然是他自己出队列
            if (helper == first) {
                break;
            }
            // 报数：每次报数 m-1
            for (int i = 0; i < countNum - 1; i++) {
                // 因为 helper 永远在 first 后面，只要在 first 移动时，指向 first 原来所在的位置即可
                helper = first;
                first = first.next;
            }
            // 出圈
            System.out.printf("出圈小孩编号 %d \n", first.no);
            first = first.next;  // first 重置为下一次报数的小孩节点上
            helper.next = first; // helper 重置为下一次报数的小孩节点上
        }
        System.out.printf("最后剩下小孩编号 %d \n", first.no);

    }

    /**
     * 打印队列
     */
    public void print() {
        if (first == null) {
            System.out.println("队列为空");
            return;
        }
        Boy cur = first;
        while (true) {
            System.out.printf("小孩编号 %d \n", cur.no);
            cur = cur.next;
            // 如果和 first 一致，则标识已经走了一圈了
            if (cur == first) {
                return;
            }
        }
    }
}

/**
 * 小孩节点
 */
class Boy {
    int no;
    Boy next;

    public Boy(int no) {
        this.no = no;
    }
}

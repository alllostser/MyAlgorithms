package cn.mrcode.study.dsalgtutorialdemo.datastructure.josepfu;

/**
 * 约瑟夫问题测试
 */
public class JosepfuTest {
    /**
     * 添加测试
     */
    public static void main(String[] args) throws Exception {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(50);
        System.out.println("构建环形队列");
        circleSingleLinkedList.print();

        // 开始玩游戏
        // 正确的输出顺序为：2、4、1、5、3
        circleSingleLinkedList.countBoy(1, 2, 50);
    }
}
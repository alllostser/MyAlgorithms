package cn.mrcode.study.dsalgtutorialdemo.datastructure.stack;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 计算器代码实现
 */
public class Calculator  {
    // 使用前面章节实现过的 数组模拟栈，来当我们 计算器中用来存储数据和符号的 容器
    private ArrayStack numStack = new ArrayStack(10); // 数组栈
    private ArrayStack operStack = new ArrayStack(10); // 符号栈

    /**
     * 第一步：扫描表达式
     */
    public void scan(String expression) {
        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/') {
                operStack.push(chars[i]);
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                numStack.push(chars[i]);
            }

        }

    }
}

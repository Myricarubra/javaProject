package day2;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZhangDong on 2017/12/25.
 */
// 模拟阻塞队列
public class MyQueue {
    // 1、创建容器
    private LinkedList<Object> list = new LinkedList<Object>();

    // 2、需要一个计数器
    private AtomicInteger count = new AtomicInteger();

    // 3、指定上限和下限
    private int minSize = 0;
    private int maxSize;

    // 4、构造方法
    public MyQueue(int length) {
        this.maxSize = length;
    }

    // 5、初始化一个对象，用于加锁
    private final Object lock = new Object();

    // put、take实现

}

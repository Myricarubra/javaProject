package day3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by ZhangDong on 2017/12/25.
 */
public class Tickets {
    public static void main(String[] args) {
        final Vector<String> tickets = new Vector<String>();

        for (int i = 0; i < 1000; i++) {
            tickets.add("火车票" + i);
        }

//        Map m = Collections.synchronizedMap(new HashMap<String, String>());

        for (int i = 0; i < 10; i++) {
            new Thread("线程" + i) {
                public void run() {
                    while (true) {
                        if (tickets.isEmpty()) break;
                        System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                    }
                }
            }.start();
        }
    }
}

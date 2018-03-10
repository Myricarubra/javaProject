package basic.proxytest.impl;

import basic.proxytest.ifce.ISale;

/**
 * Created by ZhangDong on 2018/3/10.
 */
public class MobileSale implements ISale {
    @Override
    public int mobile(String model) {
        System.out.println("手机型号----" + model);
        return 8888;
    }
}

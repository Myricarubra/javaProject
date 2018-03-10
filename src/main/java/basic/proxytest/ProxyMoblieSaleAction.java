package basic.proxytest;

import basic.proxytest.ifce.ISale;
import basic.proxytest.impl.MobileSale;
import basic.proxytest.util.ProxyMobileSale;
import org.junit.Test;

/**
 * Created by ZhangDong on 2018/3/10.
 */
public class ProxyMoblieSaleAction {

    @Test
    public void saleByProxy() {
        ISale sale = ProxyMobileSale.getProxy(200, ISale.class, MobileSale.class);
        System.out.println("sale by proxy");
        int money = sale.mobile("iphoneX");
        System.out.println(money);
    }
}

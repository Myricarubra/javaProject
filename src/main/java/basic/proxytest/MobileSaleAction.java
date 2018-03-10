package basic.proxytest;

import basic.proxytest.impl.MobileSale;
import org.junit.Test;

/**
 * Created by ZhangDong on 2018/3/10.
 */
public class MobileSaleAction {

    @Test
    public void saleMobile() {
        MobileSale mobileSale = new MobileSale();
        System.out.println("Apple官网");
        int money = mobileSale.mobile("iphoneX");
        System.out.println("价格" + money);
    }
}

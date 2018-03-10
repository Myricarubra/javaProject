package basic.proxytest.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ZhangDong on 2018/3/10.
 */
@SuppressWarnings("unchecked")
public class ProxyMobileSale {
    public static <T> T getProxy(final int discountCoupon, final Class<?> interfaceClass, final Class<?> implementsClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Integer returnValue = (Integer) method.invoke(implementsClass.newInstance(), args);
                return returnValue - discountCoupon;
            }
        });
    }
}

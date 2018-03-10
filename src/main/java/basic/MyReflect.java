package basic;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * Created by ZhangDong on 2018/3/9.
 */
public class MyReflect {
    public String className = null;
    public Class personClass = null;

    /**
     * 反射Person类
     *
     * @throws ClassNotFoundException
     */
    @Before
    public void init() throws ClassNotFoundException {
        className = "basic.Person";
        personClass = Class.forName(className);
    }

    /**
     * 获取class文件对象
     */
    @Test
    public void getClassName() {
        System.out.println(personClass);
    }

    /**
     * 获取class文件对象（方式2）
     */
    @Test
    public void getClassName2() {
        System.out.println(Person.class);
    }

    /**
     * 创建class文件实例对象
     * 底层调用空参数的构造方法
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void getNewInstance() throws IllegalAccessException, InstantiationException {
        System.out.println(personClass.newInstance());
    }

    /**
     * 获取非私有构造参数
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getPublicConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = personClass.getConstructor(Long.class, String.class);
        Person person = (Person) constructor.newInstance(1100L, "yyyyyyx");
        System.out.println(person.toString());
    }

    /**
     * 获取私有构造函数
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getPrivateConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = personClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true); // 强制取消Java的权限检测
        Person person = (Person) constructor.newInstance("yyyyyyx");
        System.out.println(person.toString());
    }

    /**
     * 获取非私有成员变量
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getNotPrivateField() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor constructor = personClass.getConstructor(Long.class, String.class);
        Object obj = constructor.newInstance(1000L, "yyyyyyx");
        Field field = personClass.getField("name");
        field.set(obj, "zddddddd");
        System.out.println(field.get(obj));
    }

    /**
     * 获取私有成员变量
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getPrivateField() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor constructor = personClass.getConstructor(Long.class);
        Object o = constructor.newInstance(110000L);
        Field field = personClass.getDeclaredField("id");
        field.setAccessible(true); // 强制取消Java的权限检测
        field.set(o, 2222222L);
        System.out.println(field.get(o));
    }

    /**
     * 获取非私有成员函数
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getNotPrivateMethod() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println(personClass.getMethod("toString"));

        Object object = personClass.newInstance(); // 获取空参构造函数
        Method method = personClass.getMethod("toString");
        object = method.invoke(object);
        System.out.println(object);
    }

    /**
     * 获取私有成员函数
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getPrivateMethod() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object object = personClass.newInstance();
        Method method = personClass.getDeclaredMethod("getSomeThing");
        method.setAccessible(true); // 取消Java权限限制
        object = method.invoke(object);
        System.out.println(object);
    }

    /**
     * 其他方法
     */
    @Test
    public void otherMethod() throws ClassNotFoundException {
        // 获取当前加载这个class的类加载器对象
        System.out.println(personClass.getClassLoader());

        // 获取某个类实现的所有接口
        Class[] interfaces = personClass.getInterfaces();
        for (Class ifce : interfaces) {
            System.out.println(ifce);
        }

        // 放射当前类的父类
        System.out.println(personClass.getGenericSuperclass());

        /**
         * 根据名字获得一个输入流
         */
        // path 不以'/'开头时默认是从此类所在包下查找文件，以'/'开头是从ClassPath根目录下寻找。最终还是有ClassLoader获取资源
        System.out.println(personClass.getResourceAsStream("/log4j.properties"));
        System.out.println(personClass.getResourceAsStream("log4j.properties"));

        // 判断当前class是数组
        System.out.println(personClass.isArray());
        //noinspection InstantiatingObjectToGetClassObject
        System.out.println(new String[2].getClass().isArray());

        // 判断枚举类
        System.out.println(personClass.isEnum());
        System.out.println(Class.forName("basic.City").isEnum());

        // 判断接口
        System.out.println(personClass.isInterface());
        System.out.println(Class.forName("basic.TestInterface").isInterface());
    }
}

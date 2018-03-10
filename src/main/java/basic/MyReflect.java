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
     * ����Person��
     *
     * @throws ClassNotFoundException
     */
    @Before
    public void init() throws ClassNotFoundException {
        className = "basic.Person";
        personClass = Class.forName(className);
    }

    /**
     * ��ȡclass�ļ�����
     */
    @Test
    public void getClassName() {
        System.out.println(personClass);
    }

    /**
     * ��ȡclass�ļ����󣨷�ʽ2��
     */
    @Test
    public void getClassName2() {
        System.out.println(Person.class);
    }

    /**
     * ����class�ļ�ʵ������
     * �ײ���ÿղ����Ĺ��췽��
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void getNewInstance() throws IllegalAccessException, InstantiationException {
        System.out.println(personClass.newInstance());
    }

    /**
     * ��ȡ��˽�й������
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
     * ��ȡ˽�й��캯��
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
        constructor.setAccessible(true); // ǿ��ȡ��Java��Ȩ�޼��
        Person person = (Person) constructor.newInstance("yyyyyyx");
        System.out.println(person.toString());
    }

    /**
     * ��ȡ��˽�г�Ա����
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
     * ��ȡ˽�г�Ա����
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
        field.setAccessible(true); // ǿ��ȡ��Java��Ȩ�޼��
        field.set(o, 2222222L);
        System.out.println(field.get(o));
    }

    /**
     * ��ȡ��˽�г�Ա����
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

        Object object = personClass.newInstance(); // ��ȡ�ղι��캯��
        Method method = personClass.getMethod("toString");
        object = method.invoke(object);
        System.out.println(object);
    }

    /**
     * ��ȡ˽�г�Ա����
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
        method.setAccessible(true); // ȡ��JavaȨ������
        object = method.invoke(object);
        System.out.println(object);
    }

    /**
     * ��������
     */
    @Test
    public void otherMethod() throws ClassNotFoundException {
        // ��ȡ��ǰ�������class�������������
        System.out.println(personClass.getClassLoader());

        // ��ȡĳ����ʵ�ֵ����нӿ�
        Class[] interfaces = personClass.getInterfaces();
        for (Class ifce : interfaces) {
            System.out.println(ifce);
        }

        // ���䵱ǰ��ĸ���
        System.out.println(personClass.getGenericSuperclass());

        /**
         * �������ֻ��һ��������
         */
        // path ����'/'��ͷʱĬ���ǴӴ������ڰ��²����ļ�����'/'��ͷ�Ǵ�ClassPath��Ŀ¼��Ѱ�ҡ����ջ�����ClassLoader��ȡ��Դ
        System.out.println(personClass.getResourceAsStream("/log4j.properties"));
        System.out.println(personClass.getResourceAsStream("log4j.properties"));

        // �жϵ�ǰclass������
        System.out.println(personClass.isArray());
        //noinspection InstantiatingObjectToGetClassObject
        System.out.println(new String[2].getClass().isArray());

        // �ж�ö����
        System.out.println(personClass.isEnum());
        System.out.println(Class.forName("basic.City").isEnum());

        // �жϽӿ�
        System.out.println(personClass.isInterface());
        System.out.println(Class.forName("basic.TestInterface").isInterface());
    }
}

package com.itcast.thread.threadlocal;

import java.util.Random;

/**
 * 当线程结束之后，会自动清理与此线程相关的ThreadLocal变量。
 * 怎么知道线程结束了呢？
 * ThreadDeathEvent ThreadDeathRequest
 * <p>
 * 本类通过ThreadLocal实现每个线程持有的变量的值，是跟本线程相关的。
 *
 * @author adminitrator
 */
public class ThreadLocalTest {
    static ThreadLocal<Integer> x = new ThreadLocal<>();
//	static ThreadLocal<MyThreadScopeData> myData = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int data = new Random().nextInt();
                System.out.println(Thread.currentThread().getName() + "has data: " + data);
//					MyThreadScopeData md = new MyThreadScopeData("name" + data, data);
//					myData.set(md);
                MyThreadScopeData.getThreadInstance().setAge(data);
                MyThreadScopeData.getThreadInstance().setName("name" + data);
                x.set(data);
                new A().get();
                new B().get();
            }).start();
        }
    }

    static class A {
        public int get() {
            System.out.println("A " + Thread.currentThread().getName() + "has data: " + x.get());
//			System.out.println("A " + Thread.currentThread().getName()+ "has data: " + myData.get());
            System.out.println("A " + Thread.currentThread().getName() + "has data: " + MyThreadScopeData.getThreadInstance());
            return x.get();
        }
    }

    static class B {
        public int get() {
            System.out.println("B " + Thread.currentThread().getName() + "has data: " + x.get());
            System.out.println("B " + Thread.currentThread().getName() + "has data: " + MyThreadScopeData.getThreadInstance());
            return x.get();
        }
    }
}

/**
 * 使用ThreadLocal创建单例模式，对于每个线程来说，可以设置与本线程相关的数据
 */
class MyThreadScopeData {
    //region 把 ThreadLocal 方法想要存放的实体内部，进行赋值
    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<>();

    public static MyThreadScopeData getThreadInstance() {
        MyThreadScopeData instance = map.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }
    //endregion

    private String name;

    private int age;

    public MyThreadScopeData(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    private MyThreadScopeData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyThreadScopeData [name=" + name + ", age=" + age + "]";
    }

}
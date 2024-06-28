package com.rectangle.onlinehospital.utils;

public class ThreadLocalUtil {
    // 提供 ThreadLocal 对象
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal<>();

    // 根据键值获取
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    // 存储键值对
    public static void set(Object object) {
        THREAD_LOCAL.set(object);
    }

    // 清除 ThreadLocal
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}

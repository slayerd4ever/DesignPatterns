package com.slayerd.designpatterns.singleton.principle;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉式，支持延迟加载，线程安全
 * @author slayerd
 * @since 2023-04-03
 */
public class SingletonExample2 {
    private final AtomicLong atomicLong = new AtomicLong(0);
    private static SingletonExample2 instance;

    private SingletonExample2(){}

    public static synchronized SingletonExample2 getInstance(){
        if (instance == null) {
            return new SingletonExample2();
        }
        return instance;
    }

    public Long getId(){
        return atomicLong.incrementAndGet();
    }
}

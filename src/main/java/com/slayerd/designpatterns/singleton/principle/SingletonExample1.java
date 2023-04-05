package com.slayerd.designpatterns.singleton.principle;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉式，不支持延迟加载，线程安全
 * @author slayerd
 * @since 2023-04-03
 */
public class SingletonExample1 {
    private final AtomicLong atomicLong = new AtomicLong(0);
    private static final SingletonExample1 instance = new SingletonExample1();

    private SingletonExample1(){}

    public static SingletonExample1 getInstance(){
        return instance;
    }

    public Long getId(){
        return atomicLong.incrementAndGet();
    }
}

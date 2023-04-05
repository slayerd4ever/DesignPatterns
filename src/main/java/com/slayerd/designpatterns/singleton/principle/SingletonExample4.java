package com.slayerd.designpatterns.singleton.principle;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 静态内部类，支持延迟加载，线程安全
 * @author slayerd
 * @since 2023-04-03
 */
public class SingletonExample4 {
    private final AtomicLong atomicLong = new AtomicLong(0);

    private static class SingletonCreater{
        private static final SingletonExample4 instance = new SingletonExample4();
    }

    public SingletonExample4 getInstance(){
        return SingletonCreater.instance;
    }

    public Long getId(){
        return atomicLong.incrementAndGet();
    }
}

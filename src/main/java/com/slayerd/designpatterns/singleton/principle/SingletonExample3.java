package com.slayerd.designpatterns.singleton.principle;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 双重检测，支持延迟加载，线程安全
 * @author slayerd
 * @since 2023-04-03
 */
public class SingletonExample3 {
    private final AtomicLong atomicLong = new AtomicLong(0);
    private static volatile SingletonExample3 instance ;

    private SingletonExample3(){}

    public static SingletonExample3 getInstance(){
        if (instance == null) {
            synchronized (SingletonExample3.class){
                if (instance == null){
                    return new SingletonExample3();
                }
            }
        }
        return instance;
    }

    public Long getId(){
        return atomicLong.incrementAndGet();
    }
}

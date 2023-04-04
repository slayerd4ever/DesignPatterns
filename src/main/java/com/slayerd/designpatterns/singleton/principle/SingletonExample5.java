package com.slayerd.designpatterns.singleton.principle;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 枚举，线程安全，不支持延迟加载
 * @author slayerd
 * @since 2023-04-04
 */
public enum SingletonExample5 {
    INSTANCE;
    private AtomicLong atomicLong = new AtomicLong(0);

    public Long getId(){
        return atomicLong.incrementAndGet();
    }
}

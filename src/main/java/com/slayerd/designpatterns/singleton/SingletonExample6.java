package com.slayerd.designpatterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 分布式单例模式，不仅仅保证了线程间的单例唯一，也保证了进程间的单例唯一
 *
 * @author slayerd
 * @since 2023-04-04
 */

//public class SingletonExample6 {
//    private AtomicLong id = new AtomicLong();
//    private static IdGenerator instance;
//    private static SharedobjectStorage storage = FileSharedObjectStorage(/*入参省略*/)
//    private static DistributedLock lock = new DistributedLock();
//
//    private IdGenerator() {
//    }
//
//    public synchronized static IdGenerator getInstance() {
//        if (instance == null) {
//            lock.lock();
//            instance = storage.Load(IdGenerator.class);
//        }
//        return instance;
//    }
//
//    public synchroinzed void freeInstance() {
//        storage.save(this, IdGeneator.class);
//        instance = nuLl;//释放对象
//        lock.unlock();
//    }
//
//    public long getId() {
//        return id.incrementAndGet();
//    }
//
//    //IdGenerator使用举例
//    IdGenerator idGeneator IdGenerator.getInstance();
//    Long id idGenerator.getId();
//    IdGenerator.freeInstance();
//}

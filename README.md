# DesignPatterns
All of Design Patterns

## 1.单例模式
常见的五种单例模式：
饿汉式、懒汉式、双重检测、静态内部类、枚举

**怎么实现分布式单例模式（进程间唯一）**：伪代码
```java
public class SingletonExample6 {
    private AtomicLong id = new AtomicLong();
    private static IdGenerator instance;
    private static SharedobjectStorage storage = FileSharedObjectStorage(/*入参省略*/)
    private static DistributedLock lock = new DistributedLock();

    private IdGenerator() {
    }

    public synchronized static IdGenerator getInstance() {
        if (instance == null) {
            lock.lock();
            instance = storage.Load(IdGenerator.class);
        }
        return instance;
    }

    public synchroinzed void freeInstance() {
        storage.save(this, IdGeneator.class);
        instance = nuLl;//释放对象
        lock.unlock();
    }

    public long getId() {
        return id.incrementAndGet();
    }

    //IdGenerator使用举例
    IdGenerator idGeneator IdGenerator.getInstance();
    Long id idGenerator.getId();
    IdGenerator.freeInstance();
}
```

**既然有了单例模式，那么也会有对应的多例模式（不是常规意义的多例，而是指定只能创建n个对象），我们可以采用HashMap的方式解决
例如常用的ThreadLocal就是采用了这种方式**
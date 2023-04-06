package com.slayerd.cases.dependenceinjection;

import com.slayerd.cases.dependenceinjection.interfaces.ApplicationContext;
import com.slayerd.cases.dependenceinjection.interfaces.impl.XmlConfigApplicationContext;
import com.slayerd.cases.dependenceinjection.pojo.RedisPool;

/**
 * 测试DI注入
 * @author slayerd
 * @since 2023/4/6
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new XmlConfigApplicationContext("config/beans.xml");
        RedisPool redisPool = (RedisPool) applicationContext.getBean("redisPool");
        redisPool.test();
    }
}

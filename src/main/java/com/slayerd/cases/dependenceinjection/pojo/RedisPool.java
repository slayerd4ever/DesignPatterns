package com.slayerd.cases.dependenceinjection.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Redis连接池
 *
 * @author slayerd
 * @since 2023/4/6
 */
@Data
@NoArgsConstructor
public class RedisPool {
    private Redis redis;

    public RedisPool(Redis redis) {
        this.redis = redis;
    }
    public void test(){
        System.out.println("DI注入成功");
    }
}

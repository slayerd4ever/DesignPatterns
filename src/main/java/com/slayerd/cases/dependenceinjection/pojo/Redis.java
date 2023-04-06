package com.slayerd.cases.dependenceinjection.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Redis配置类
 * @author slayerd
 * @since 2023/4/6
 */
@Data
@NoArgsConstructor
public class Redis {
    private String ipAddress;
    private String port;

    public Redis(String ipAddress, String port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }
}

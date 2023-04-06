package com.slayerd.cases.dependenceinjection.interfaces;

import com.slayerd.cases.dependenceinjection.pojo.BeanDefinition;

import java.io.InputStream;
import java.util.List;

/**
 * Bean解析接口
 * @author slayerd
 * @since 2023/4/4
 */
public interface BeanParser {
    List<BeanDefinition> parse(InputStream inputStream);
    List<BeanDefinition> parse(String configContent);
}

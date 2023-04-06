package com.slayerd.cases.dependenceinjection.interfaces.impl;

import com.slayerd.cases.dependenceinjection.pojo.BeanDefinition;
import com.slayerd.cases.dependenceinjection.interfaces.BeanParser;

import java.io.InputStream;
import java.util.List;

/**
 * 解析xml文件类
 * @author slayerd
 * @since 2023/4/6
 */
public class XmlBeanConfigParser implements BeanParser {
    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        return null;
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        return null;
    }
}

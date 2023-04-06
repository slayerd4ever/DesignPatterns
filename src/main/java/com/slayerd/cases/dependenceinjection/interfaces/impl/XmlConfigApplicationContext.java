package com.slayerd.cases.dependenceinjection.interfaces.impl;

import com.slayerd.cases.dependenceinjection.factory.BeanFactory;
import com.slayerd.cases.dependenceinjection.interfaces.ApplicationContext;
import com.slayerd.cases.dependenceinjection.pojo.BeanDefinition;
import com.slayerd.exceptions.NoSuchBeanDefinitionException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 对外暴露的接口实现类
 *
 * @author slayerd
 * @since 2023/4/6
 */
public class XmlConfigApplicationContext implements ApplicationContext {
    private BeanFactory beanFactory;
    private XmlBeanConfigParser parser;

    public XmlConfigApplicationContext(String classPath) {
        this.beanFactory = new BeanFactory();
        this.parser = new XmlBeanConfigParser();
        loadBeanDefinition(classPath);
    }

    /**
     * 加载Bean元数据
     *
     * @param classPath xml文件路径
     */
    private void loadBeanDefinition(String classPath) {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getResourceAsStream("/" + classPath);
            if (inputStream == null) {
                throw new RuntimeException("config can not found:" + classPath);
            }
            List<BeanDefinition> beanDefinitions = parser.parse(inputStream);
            beanFactory.addBeanDefinitions(beanDefinitions);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        try {
            return beanFactory.getBean(beanId);
        } catch (NoSuchBeanDefinitionException e) {
            throw new RuntimeException("Bean:" + beanId + " is not found!");
        }
    }
}

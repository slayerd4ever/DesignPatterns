package com.slayerd.cases.dependenceinjection.interfaces;

/**
 *
 * @author slayerd
 * @since 2023/4/6
 */
public interface ApplicationContext {
    Object getBean(String beanId);
}

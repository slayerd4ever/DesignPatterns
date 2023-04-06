package com.slayerd.exceptions;

/**
 * Bean未被定义异常
 * @author slayerd
 * @since 2023/4/6
 */
public class NoSuchBeanDefinitionException extends Throwable {
    public NoSuchBeanDefinitionException(String message) {
        super(message);
    }
}

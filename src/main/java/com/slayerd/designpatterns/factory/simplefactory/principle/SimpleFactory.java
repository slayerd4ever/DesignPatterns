package com.slayerd.designpatterns.factory.simplefactory.principle;

/**
 * 简单工厂模式,通过工厂创建不同的产品
 *
 * @author slayerd
 * @since 2023-04-04
 */
public class SimpleFactory {
    public static Object creater(String flag, int i) {
        switch (flag) {
            case "string": {
                return new String();
            }
            case "integer": {
                return new Integer(i);
            }
            case "long": {
                return new Long(i);
            }
            default:
                throw new IllegalArgumentException("传入参数：flag不合法");
        }
    }
}


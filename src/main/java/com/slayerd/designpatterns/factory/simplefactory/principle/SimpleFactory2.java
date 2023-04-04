package com.slayerd.designpatterns.factory.simplefactory.principle;

import java.util.HashMap;

/**
 * 简单工厂+单例模式。通过工厂创建不同的产品（唯一性）,只是举例，有代码逻辑不合理的地方勿纠结
 * @author slayerd
 * @since 2023/4/4
 */
public class SimpleFactory2 {
    private static HashMap<String,Object> map = new HashMap<>();
    static {
        map.put("string",new String());
        map.put("integer",new Integer(0));
        map.put("long",new Long(0));
    }

    public static Object creater(String flag){
        switch (flag){
            case "string": return map.get(flag);
            case "integer": return map.get(flag);
            case "long": return map.get(flag);
            default:return null;
        }
    }
}

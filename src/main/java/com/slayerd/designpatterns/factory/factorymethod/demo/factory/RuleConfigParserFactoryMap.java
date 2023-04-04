package com.slayerd.designpatterns.factory.factorymethod.demo.factory;

import com.slayerd.designpatterns.factory.factorymethod.demo.factory.impl.JsonConfigParserFactory;
import com.slayerd.designpatterns.factory.factorymethod.demo.factory.impl.PropertiesConfigParserFactory;
import com.slayerd.designpatterns.factory.factorymethod.demo.factory.impl.XmlConfigParserFactory;

import java.util.HashMap;

/**
 * 生产工厂的工厂类,简单工厂模式+单例模式
 * @author slayerd
 * @since 2023/4/4
 */
public class RuleConfigParserFactoryMap {
    private static HashMap<String,IRuleConfigParserFactory> map = new HashMap<>();
    static {
        map.put("json",new JsonConfigParserFactory());
        map.put("xml",new XmlConfigParserFactory());
        map.put("propertites",new PropertiesConfigParserFactory());
    }

    public static IRuleConfigParserFactory createFactory(String flag){
        if (flag == null || flag.isEmpty()){
            return null;
        }
        return map.get(flag);
    }
}

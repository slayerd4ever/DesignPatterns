package com.slayerd.designpatterns.factory.simplefactory.demo.factory;

import com.slayerd.designpatterns.factory.simplefactory.demo.parser.IRuleConfigParser;
import com.slayerd.designpatterns.factory.simplefactory.demo.parser.impl.JsonConfigParser;
import com.slayerd.designpatterns.factory.simplefactory.demo.parser.impl.PropertiesConfigParser;
import com.slayerd.designpatterns.factory.simplefactory.demo.parser.impl.XmlConfigParser;

import java.util.HashMap;

/**
 * 工厂类：生产服务调用者所需要的产品，此工厂类一并实现了单例模式
 * @author slayerd
 * @since 2023/4/4
 */
public class RuleConfigFactory{
    private static HashMap<String,IRuleConfigParser> map = new HashMap<>();
    static {
        map.put("json",new JsonConfigParser());
        map.put("propertites",new PropertiesConfigParser());
        map.put("xml",new XmlConfigParser());
    }
    public static IRuleConfigParser createParser(String flag) {
        if (flag == null || flag.isEmpty()){
            return null;
        }
        return map.get(flag);
    }
}

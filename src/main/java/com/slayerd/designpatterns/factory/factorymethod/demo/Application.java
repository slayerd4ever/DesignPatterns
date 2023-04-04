package com.slayerd.designpatterns.factory.factorymethod.demo;

import com.slayerd.designpatterns.factory.factorymethod.demo.factory.IRuleConfigParserFactory;
import com.slayerd.designpatterns.factory.factorymethod.demo.factory.RuleConfigParserFactoryMap;
import com.slayerd.designpatterns.factory.factorymethod.demo.factory.impl.JsonConfigParserFactory;
import com.slayerd.designpatterns.factory.factorymethod.demo.parser.IRuleConfigParser;
import com.slayerd.designpatterns.factory.simplefactory.demo.factory.RuleConfigFactory;

/**
 * 测试demo
 * @author slayerd
 * @since 2023/4/4
 */
public class Application {
    public static void main(String[] args) {
        //需要JsonConfigParser产品
        IRuleConfigParser parser = new JsonConfigParserFactory().createParser();
        parser.parser();

        //若是所有产品都可能需要，那么将这些产品的工厂类全部写在这里并不友好：代码可读性，代码设计变得更复杂，不如用简单工厂（前提是产品数量稳定，不经常新增产品），于是我们可以用简单工厂模式再包装一层
        IRuleConfigParserFactory factory = RuleConfigParserFactoryMap.createFactory("xml");
        factory.createParser().parser();
    }
}

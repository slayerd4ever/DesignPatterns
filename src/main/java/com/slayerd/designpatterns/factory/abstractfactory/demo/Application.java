package com.slayerd.designpatterns.factory.abstractfactory.demo;

import com.slayerd.designpatterns.factory.abstractfactory.demo.factory.impl.JsonConfigParserFactory;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.IRuleConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.ISystemConfigParser;

/**
 * 测试demo
 * @author slayerd
 * @since 2023/4/4
 */
public class Application {
    public static void main(String[] args) {
        JsonConfigParserFactory factory = new JsonConfigParserFactory();
        ISystemConfigParser systemParser = factory.createSystemParser();
        IRuleConfigParser ruleParser = factory.createRuleParser();
        systemParser.parser();
        ruleParser.parser();
    }
}

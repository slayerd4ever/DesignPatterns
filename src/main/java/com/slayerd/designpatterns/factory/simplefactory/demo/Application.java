package com.slayerd.designpatterns.factory.simplefactory.demo;

import com.slayerd.designpatterns.factory.simplefactory.demo.factory.RuleConfigFactory;
import com.slayerd.designpatterns.factory.simplefactory.demo.parser.IRuleConfigParser;

/**
 * 测试demo
 * @author slayerd
 * @since 2023/4/4
 */
public class Application {
    public static void main(String[] args) {
        IRuleConfigParser parser = RuleConfigFactory.createParser("xml");
        parser.parser();
    }
}

package com.slayerd.designpatterns.factory.abstractfactory.demo.factory.impl;


import com.slayerd.designpatterns.factory.abstractfactory.demo.factory.IConfigParserFactory;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.IRuleConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.ISystemConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.impl.XmlConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.impl.XmlSystemConfigParser;

/**
 * 工厂：XmlConfigParserFactory
 * @author slayerd
 * @since 2023/4/4
 */
public class XmlConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}

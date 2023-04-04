package com.slayerd.designpatterns.factory.abstractfactory.demo.factory.impl;

import com.slayerd.designpatterns.factory.abstractfactory.demo.factory.IConfigParserFactory;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.IRuleConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.ISystemConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.impl.PropertiesConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.impl.PropertiesSystemConfigParser;

/**
 * 工厂：PropertiesConfigParserFactory
 * @author slayerd
 * @since 2023/4/4
 */
public class PropertiesConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new PropertiesConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new PropertiesSystemConfigParser();
    }
}

package com.slayerd.designpatterns.factory.factorymethod.demo.factory.impl;

import com.slayerd.designpatterns.factory.factorymethod.demo.factory.IRuleConfigParserFactory;
import com.slayerd.designpatterns.factory.factorymethod.demo.parser.IRuleConfigParser;
import com.slayerd.designpatterns.factory.factorymethod.demo.parser.impl.PropertiesConfigParser;

/**
 * 工厂：PropertiesConfigParserFactory
 * @author slayerd
 * @since 2023/4/4
 */
public class PropertiesConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new PropertiesConfigParser();
    }
}

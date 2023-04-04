package com.slayerd.designpatterns.factory.abstractfactory.demo.factory.impl;

import com.slayerd.designpatterns.factory.abstractfactory.demo.factory.IConfigParserFactory;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.IRuleConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.ISystemConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.impl.JsonConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.impl.JsonSystemConfigParser;

/**
 * 工厂：JsonConfigParserFactory
 * @author slayerd
 * @since 2023/4/4
 */
public class JsonConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}

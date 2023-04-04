package com.slayerd.designpatterns.factory.abstractfactory.demo.factory;

import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.IRuleConfigParser;
import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.ISystemConfigParser;

/**
 * 工厂接口
 * @author slayerd
 * @since 2023/4/4
 */
public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();
    ISystemConfigParser createSystemParser();
}

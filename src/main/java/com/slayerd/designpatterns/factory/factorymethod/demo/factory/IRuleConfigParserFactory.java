package com.slayerd.designpatterns.factory.factorymethod.demo.factory;

import com.slayerd.designpatterns.factory.factorymethod.demo.parser.IRuleConfigParser;

/**
 * 工厂接口
 * @author slayerd
 * @since 2023/4/4
 */
public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser();
}

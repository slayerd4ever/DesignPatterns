package com.slayerd.designpatterns.factory.abstractfactory.demo.parser.impl;

import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.IRuleConfigParser;

/**
 * 产品：XmlConfigParser
 * @author slayerd
 * @since 2023/4/4
 */
public class XmlConfigParser implements IRuleConfigParser {
    @Override
    public void parser() {
        System.out.println("XmlConfigParser生效");
    }
}

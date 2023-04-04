package com.slayerd.designpatterns.factory.abstractfactory.demo.parser.impl;

import com.slayerd.designpatterns.factory.abstractfactory.demo.parser.ISystemConfigParser;

/**
 * 产品：XmlSystemConfigParser
 * @author slayerd
 * @since 2023/4/4
 */
public class XmlSystemConfigParser implements ISystemConfigParser {
    @Override
    public void parser() {
        System.out.println("XmlSystemConfigParser生效");
    }
}

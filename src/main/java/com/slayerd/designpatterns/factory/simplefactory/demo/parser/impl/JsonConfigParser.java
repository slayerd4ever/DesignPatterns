package com.slayerd.designpatterns.factory.simplefactory.demo.parser.impl;

import com.slayerd.designpatterns.factory.simplefactory.demo.parser.IRuleConfigParser;

/**
 * 产品：JsonConfigParser
 * @author slayerd
 * @since 2023/4/4
 */
public class JsonConfigParser implements IRuleConfigParser {
    @Override
    public void parser() {
        System.out.println("JsonConfigParser生效");
    }
}

package com.slayerd.designpatterns.prototype;

import com.slayerd.designpatterns.prototype.demo.BeanCopyDemo;
import com.slayerd.designpatterns.prototype.demo.pojo.Book;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 测试原型模式
 *
 * @author slayerd
 * @since 2023/4/7
 */
public class Application {
    public static void main(String[] args) {
        HashMap<Integer, Book> source = new HashMap<>();
        source.put(1, new Book("高数", new BigDecimal("16.9")));
        source.put(2, new Book("JAVA从入门到精通", new BigDecimal("98.9")));
        source.put(3, new Book("文言文解析", new BigDecimal("61")));
        System.out.println("改变前的source:" + source);
        HashMap<Integer, Book> target = new HashMap<>();
        target = BeanCopyDemo.deep(source, target);
        System.out.println("改变后的source:" + source);
        System.out.println("改变后的target" + target);
    }
}

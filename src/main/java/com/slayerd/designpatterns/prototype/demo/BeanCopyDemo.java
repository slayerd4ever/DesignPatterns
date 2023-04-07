package com.slayerd.designpatterns.prototype.demo;

import com.slayerd.designpatterns.prototype.demo.pojo.Book;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 浅拷贝和深拷贝
 * @author slayerd
 * @since 2023/4/7
 */
public class BeanCopyDemo {
    public static HashMap<Integer, Book> shallow(HashMap<Integer, Book> source, HashMap<Integer,Book> target){
        target = (HashMap<Integer, Book>) source.clone();
        HashMap<Integer, Book> newBooks = getNewBook();
        for (Map.Entry<Integer, Book> entry : newBooks.entrySet()) {
            if (target.containsKey(entry.getKey())) {
                target.get(entry.getKey()).setPrice(entry.getValue().getPrice());
            }else {
                target.put(entry.getKey(),entry.getValue());
            }
        }
        return target;
    }

    public static HashMap<Integer, Book> deep(HashMap<Integer, Book> source, HashMap<Integer,Book> target){
        target = (HashMap<Integer, Book>) source.clone();
        HashMap<Integer, Book> newBooks = getNewBook();
        for (Map.Entry<Integer, Book> entry : newBooks.entrySet()) {
            if (target.containsKey(entry.getKey())) {
                target.remove(entry.getKey());
                target.put(entry.getKey(),entry.getValue());
            }else {
                target.put(entry.getKey(),entry.getValue());
            }
        }
        return target;
    }

    private static HashMap<Integer, Book> getNewBook() {
        HashMap<Integer, Book> newBooks = new HashMap<>();
        newBooks.put(1,new Book("高数",new BigDecimal("26")));
        newBooks.put(4,new Book("政治",new BigDecimal("83")));
        return newBooks;
    }

}

package com.slayerd.designpatterns.prototype.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 书籍类
 * @author slayerd
 * @since 2023/4/7
 */
@Data
@AllArgsConstructor
public class Book {
    private String name;
    private BigDecimal price;
}

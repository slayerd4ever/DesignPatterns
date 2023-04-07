package com.slayerd.designpatterns.builder.demo.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 产品类
 * @author slayerd
 * @since 2023/4/7
 */
@Data
public class Product {
    private String name;
    private BigDecimal price;

}

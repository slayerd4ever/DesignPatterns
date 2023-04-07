package com.slayerd.designpatterns.builder.demo.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车类
 * @author slayerd
 * @since 2023/4/7
 */
public class ShoppingCart {
    private static final int CART_MAX_NUM = 20;
    private static final int CART_MIN_NUM = 0;

    //产品数量
    private int productNum;
    //购物车ID
    private int cartId;
    //购物车最大容量
    private int cartCapacity;
    //购物车剩余容量
    private int capacityRemaining;
    //产品列表
    private List<Product> productList;
    //产品总额
    private BigDecimal totalPrice;

    public ShoppingCart(int productNum, int cartId, int cartCapacity, int capacityRemaining, List<Product> productList, BigDecimal totalPrice) {
        this.productNum = productNum;
        this.cartId = cartId;
        this.cartCapacity = cartCapacity;
        this.capacityRemaining = capacityRemaining;
        this.productList = productList;
        this.totalPrice = totalPrice;
    }
}

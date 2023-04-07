package com.slayerd.designpatterns.builder.demo;

import com.slayerd.designpatterns.builder.demo.pojo.Product;
import com.slayerd.designpatterns.builder.demo.pojo.ShoppingCartByBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试demo
 * @author slayerd
 * @since 2023/4/7
 */
public class Application {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setName("薯片");
        product.setPrice(new BigDecimal("10"));
        Product product1 = new Product();
        product1.setName("可乐");
        product1.setPrice(new BigDecimal("3"));
        Product product2 = new Product();
        product2.setName("辣椒酱");
        product2.setPrice(new BigDecimal("15"));
        list.add(product);
        list.add(product1);
        list.add(product2);

        ShoppingCartByBuilder cart = new ShoppingCartByBuilder.Builder()
                .setCartId(0)
                .setCartCapacity(15)
                .setProductNum(3)
                .setProductList(list)
                .setCapacityRemaining(12)
                .build();

        System.out.println(cart);
    }
}

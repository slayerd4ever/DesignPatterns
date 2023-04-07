package com.slayerd.designpatterns.builder.demo.pojo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 建造者模式下的购物车
 *
 * @author slayerd
 * @since 2023/4/7
 */
public class ShoppingCartByBuilder {
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

    public ShoppingCartByBuilder(Builder builder) {
        this.cartId = builder.cartId;
        this.cartCapacity = builder.cartCapacity;
        this.productNum = builder.productNum;
        this.capacityRemaining = builder.capacityRemaining;
        this.productList = builder.productList;
        this.totalPrice = builder.totalPrice;
    }

    public int getProductNum() {
        return productNum;
    }

    public int getCartId() {
        return cartId;
    }

    public int getCartCapacity() {
        return cartCapacity;
    }

    public int getCapacityRemaining() {
        return capacityRemaining;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCartByBuilder{" +
                "productNum=" + productNum +
                ", cartId=" + cartId +
                ", cartCapacity=" + cartCapacity +
                ", capacityRemaining=" + capacityRemaining +
                ", productList=" + productList +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private static final int CART_MAX_NUM = 20;
        private static final int CART_MIN_NUM = 0;
        //产品数量
        private int productNum = 0;
        //购物车ID
        private int cartId;
        //购物车最大容量
        private int cartCapacity;
        //购物车剩余容量
        private int capacityRemaining;
        //产品列表
        private List<Product> productList;
        //产品总额
        private BigDecimal totalPrice = BigDecimal.ZERO;

        public Builder setProductNum(int productNum) {
            this.productNum = productNum;
            return this;
        }

        public Builder setCartId(int cartId) {
            this.cartId = cartId;
            return this;
        }

        public Builder setCartCapacity(int cartCapacity) {
            this.cartCapacity = cartCapacity;
            return this;
        }

        public Builder setCapacityRemaining(int capacityRemaining) {
            this.capacityRemaining = capacityRemaining;
            return this;
        }

        public Builder setProductList(List<Product> productList) {
            this.productList = productList;
            return this;
        }

        public ShoppingCartByBuilder build() {
            if (cartId < 0) {
                throw new IllegalArgumentException("购物车id应大于0");
            }
            if (cartCapacity > CART_MAX_NUM || cartCapacity < CART_MIN_NUM) {
                throw new IllegalArgumentException("购物车容量应在0-20之间");
            }
            if (productNum > CART_MAX_NUM || productNum < CART_MIN_NUM) {
                throw new IllegalArgumentException("购物车容量应在0-20之间");
            }
            if (safe(productList).size() != productNum) {
                throw new IllegalArgumentException("购物车容量应在0-20之间");
            }
            if (productNum + capacityRemaining != cartCapacity) {
                throw new IllegalArgumentException("产品数量+购物车剩余容量必须等于购物车容量");
            }
            safe(productList).forEach(item -> totalPrice = totalPrice.add(item.getPrice()));
            return new ShoppingCartByBuilder(this);
        }

        public List<Product> safe(List<Product> productList) {
            if (productList == null) {
                return Collections.emptyList();
            } else return productList;
        }
    }
}

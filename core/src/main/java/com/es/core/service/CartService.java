package com.es.core.service;

import com.es.core.entity.cart.Cart;
import com.es.core.entity.book.Book;
import com.es.core.entity.order.OutOfStockException;

import java.math.BigDecimal;

public interface CartService {

    Cart getCart();

    void addPhone(Long phoneId, Long quantity) throws OutOfStockException;

    /**
     * @param items
     * key: {@link Book#id}
     * value: quantity
     */
    void update(Long phoneId, Long phoneQuantity);
    void clear();
    void remove(Long phoneId);
    long getTotalQuantity();

    BigDecimal getTotalCost();
}

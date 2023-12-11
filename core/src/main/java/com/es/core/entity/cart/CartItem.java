package com.es.core.entity.cart;

import com.es.core.entity.book.Book;

public class CartItem {

    private Book book;
    private Long quantity;

    public Book getPhone() {
        return book;
    }

    public void setPhone(Book book) {
        this.book = book;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

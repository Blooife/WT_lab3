package com.es.core.dao;

import com.es.core.entity.book.Book;
import com.es.core.entity.book.sort.SortField;
import com.es.core.entity.book.sort.SortOrder;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> get(Long key);

    List<Book> findAll(int offset, int limit, SortField sortField, SortOrder sortOrder, String query);

    Long numberByQuery(String query);
}

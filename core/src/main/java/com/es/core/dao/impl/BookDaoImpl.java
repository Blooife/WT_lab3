package com.es.core.dao.impl;

import com.es.core.dao.BookDao;
import com.es.core.entity.book.Book;
import com.es.core.entity.book.sort.SortField;
import com.es.core.entity.book.sort.SortOrder;
import com.es.core.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private final SessionFactory sessionFactory;

    public BookDaoImpl() {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Optional<Book> get(Long key) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Book.class, key));
        }
    }

    @Override
    public List<Book> findAll(int offset, int limit, SortField sortField, SortOrder sortOrder, String query) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);

            criteriaQuery.select(root);
            if (query != null && !query.isEmpty()) {
                criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + query.toLowerCase() + "%"));
            }
            if (sortField != null && sortOrder != null) {
                Order order = sortOrder == SortOrder.ASC ? criteriaBuilder.asc(root.get(sortField.name().toLowerCase()))
                        : criteriaBuilder.desc(root.get(sortField.name().toLowerCase()));
                criteriaQuery.orderBy(order);
            }

            Query<Book> queryResult = session.createQuery(criteriaQuery);
            queryResult.setFirstResult(offset);
            queryResult.setMaxResults(limit);

            return queryResult.getResultList();
        }
    }

    @Override
    public Long numberByQuery(String query) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            Root<Book> root = countQuery.from(Book.class);

            countQuery.select(criteriaBuilder.count(root));
            if (query != null && !query.isEmpty()) {
                countQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + query.toLowerCase() + "%"));
            }

            return session.createQuery(countQuery).getSingleResult();
        }
    }
}

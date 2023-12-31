package com.es.core.utils;

import com.es.core.entity.book.Book;
import com.es.core.entity.order.Order;
import com.es.core.entity.order.OrderItem;
import com.es.core.entity.book.color.Color;
import com.es.core.entity.book.stock.Stock;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Color.class);
                configuration.addAnnotatedClass(Stock.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(OrderItem.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}

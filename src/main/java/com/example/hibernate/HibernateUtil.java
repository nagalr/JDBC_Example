package com.example.hibernate;

import com.example.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

/**
 * Created by ronnen on 18-Jan-2021
 */


public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

//    private static SessionFactory buildSessionFactory() {
//        try {
//            return new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("build SessionFactory failed :" + ex);
//            throw new ExceptionInInitializerError(ex);
//        } finally {
//            close();
//        }
//    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        try {

            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost:8889/ecomm");
            properties.put(Environment.USER, "root");
            properties.put(Environment.PASS, "root");
            properties.put(Environment.FORMAT_SQL, "true");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "create");
            properties.put(Environment.POOL_SIZE, "5");

            return new Configuration()
                    .setProperties(properties)
                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("build SessionFactory failed :" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void close() {
        // Close all cached and active connection pools
        getSessionFactory().close();
    }
}

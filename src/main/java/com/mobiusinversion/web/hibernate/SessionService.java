package com.mobiusinversion.web.hibernate;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

public class SessionService {

    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;
    private static String hibernateDialect;
    private static String hibernateShowSql;
    private static String hibernateHbm2ddlAuto;
    private static SessionFactory sessionFactory;
    private static Properties properties = getProperties();

    static {
        driverClassName = properties.getProperty("jdbc.driverClassName");
        url = properties.getProperty("jdbc.url");
        username = properties.getProperty("jdbc.username");
        password = properties.getProperty("jdbc.password");
        hibernateDialect = properties.getProperty("hibernate.dialect");
        hibernateShowSql = properties.getProperty("hibernate.show_sql");
        hibernateHbm2ddlAuto = properties.getProperty("hibernate.hbm2ddl.auto");
        sessionFactory = getSessionFactory();
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        InputStream inputStream = SessionService.class.getResourceAsStream("/application.properties");
        try {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    public static Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        return properties;
    }

    public static SessionFactory getSessionFactory() {
        AnnotationSessionFactoryBean annotationSessionFactoryBean = new AnnotationSessionFactoryBean();
        annotationSessionFactoryBean.setDataSource(getDataSource());
        annotationSessionFactoryBean.setHibernateProperties(getHibernateProperties());
        annotationSessionFactoryBean.setPackagesToScan("com.mobiusinversion.web");
        try {
            Method method = annotationSessionFactoryBean.getClass().getDeclaredMethod("getSessionFactory",AnnotationSessionFactoryBean.class);
            method.setAccessible(true);
            return (SessionFactory) method.invoke(annotationSessionFactoryBean);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }


}

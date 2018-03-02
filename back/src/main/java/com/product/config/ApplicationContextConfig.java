package com.product.config;

import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@ComponentScan("com.product")
public class ApplicationContextConfig {
	public static Logger logger = Logger.getLogger("ApplicationContextConfig");
	
	@Bean(name = "myDataSource")
	public DataSource getDataSource() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/userdatabase");
		dataSource.setUsername("root");
		dataSource.setPassword("gts@1234");
		System.out.println("mysql database connection");
		return dataSource;
	}
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean  getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionBuilder = new LocalSessionFactoryBean();
		sessionBuilder.setDataSource(getDataSource());
		sessionBuilder.setHibernateProperties(getHibernateProperties());
		sessionBuilder.setPackagesToScan(new String[] {"com.product.model"});
		return sessionBuilder;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}

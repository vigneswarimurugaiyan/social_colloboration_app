package com.maven.socialappbackend.config;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.maven.socialappbackend.dao.blogcommentdao;
import com.maven.socialappbackend.dao.blogcommentimpl;
import com.maven.socialappbackend.dao.blogdao;
import com.maven.socialappbackend.dao.blogdaoimpl;
import com.maven.socialappbackend.dao.forumcommentdao;
import com.maven.socialappbackend.dao.forumcommentimpl;
import com.maven.socialappbackend.dao.forumdao;
import com.maven.socialappbackend.dao.forumdaoimpl;
import com.maven.socialappbackend.dao.frienddao;
import com.maven.socialappbackend.dao.frienddaoimpl;
import com.maven.socialappbackend.dao.jobdao;
import com.maven.socialappbackend.dao.jobdaoimpl;
import com.maven.socialappbackend.dao.userdao;
import com.maven.socialappbackend.dao.userdaoimpl;
import com.maven.socialappbackend.model.userdetail;
@Configuration
@EnableTransactionManagement
@ComponentScan("com.maven.socialappbackend")
public class ApplicationConfig {
	   //1.Creating a DataSource Object which is used for LocalSessionFactory
		public DataSource getOracleDataSource()
		{
			DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
			driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
			driverManagerDataSource.setUsername("System");
			driverManagerDataSource.setPassword("bharathy");
			return driverManagerDataSource;
		}
		
		//2.Creating Hibernate Properties which is used by LocalSessionFactory
		public Properties getHibernateProperties()
		{
			Properties properties=new Properties();
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
			return properties;
		}
		
		@Bean
		public SessionFactory getSessionFactory()
		{
			LocalSessionFactoryBuilder localSessionFactoryBuilder=new LocalSessionFactoryBuilder(getOracleDataSource());
			localSessionFactoryBuilder.addProperties(getHibernateProperties());
			localSessionFactoryBuilder.scanPackages("com.maven.socialappbackend.model");
			System.out.println("SessionFactory Bean Created");
			return localSessionFactoryBuilder.buildSessionFactory();
			
		}
		
		@Bean
		public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
		{
			return new HibernateTransactionManager(sessionFactory);
		}
		
		@Bean(name = "userDAO")
		public userdao getuserdao(SessionFactory sessionFactory)
		{
			System.out.println("user DAO object Created");
			return new userdaoimpl(sessionFactory);
		}
		@Bean(name = "blogDAO")
		public blogdao getblogdao(SessionFactory sessionFactory)
		{
			System.out.println("blog DAO object Created");
			return new blogdaoimpl(sessionFactory);
		}
		@Bean(name = "blogcommentDAO")
		public blogcommentdao getblogcommentdao(SessionFactory sessionFactory)
		{
			System.out.println("blogcomment DAO object Created");
			return new blogcommentimpl(sessionFactory);
		}
		@Bean(name = "forumDAO")
		public forumdao getforumdao(SessionFactory sessionFactory)
		{
			System.out.println("forum DAO object Created");
			return new forumdaoimpl(sessionFactory);
		}
	   @Bean(name = "forumcommentDAO")
		public forumcommentdao getforumcommentdao(SessionFactory sessionFactory)
		{
			System.out.println("forumcomment DAO object Created");
			return new forumcommentimpl(sessionFactory);
		}
	   @Bean(name = "jobDAO")
		public jobdao getjobdao(SessionFactory sessionFactory)
		{
			System.out.println("job DAO object Created");
			return new jobdaoimpl(sessionFactory);
		}
	   @Bean(name = "friendDAO")
	 		public frienddao getfrienddao(SessionFactory sessionFactory)
	 		{
	 			System.out.println("friend DAO object Created");
	 			return new frienddaoimpl(sessionFactory);
	 		}

}


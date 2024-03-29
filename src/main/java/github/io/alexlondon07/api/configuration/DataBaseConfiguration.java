package github.io.alexlondon07.api.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration {

	@Bean	
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean	 sessionfactoryBean = new LocalSessionFactoryBean();
		sessionfactoryBean.setDataSource(dataSource());
		sessionfactoryBean.setPackagesToScan("github.io.alexlondon07.api.models");
		sessionfactoryBean.setHibernateProperties(hibernateProperties());
		return sessionfactoryBean;
	}
		
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		//LOCAL
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");				
		dataSource.setUrl("jdbc:mysql://localhost:3307/api_rest");
		dataSource.setUsername("api_rest");
		dataSource.setPassword("api_rest");
		return dataSource;
	}
	
	
	public Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("show_sql", "true");
		return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		return hibernateTransactionManager;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springdemo.restapi.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author Dell
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.springdemo.restapi")
@PropertySource({"classpath:persistence-mysql.properties"})
public class DemoAppConfig implements WebMvcConfigurer{
    
    // set the variable to hold the properties
    @Autowired
    private Environment env;
    
    //set up a logger for diagnostics
    private Logger logger = Logger.getLogger(getClass().getName());
    
    @Bean
    public ViewResolver viewResolver(){
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        
        // set prefix 
        viewResolver.setPrefix("/WEB-INF/view/");
       
        //set suffix
        viewResolver.setSuffix(".jsp");
        
        // return the view Resovler
        return viewResolver;
    }
    
    @Bean
    public DataSource myDataSource(){
        
        // create connection pool
        ComboPooledDataSource theDataSource = new ComboPooledDataSource();
        
        try {
            //set the jdbc driver class
            theDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException ex) {
            throw new RuntimeException(ex);
        }
        
        //log the connection props
        //just to make sure we are really reading data from properties file
        logger.info(">>> jdbc.url="+env.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user="+env.getProperty("jdbc.user"));
        
        //set database connection props
        theDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        theDataSource.setUser(env.getProperty("jdbc.user"));
        theDataSource.setPassword(env.getProperty("jdbc.password"));
        
        //set connection pool props
        theDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        theDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        theDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        theDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
        
        //return the value
        return theDataSource;
    }
    
    private Properties getHibernateProperties() {

	// set hibernate properties
	Properties props = new Properties();
        
        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
	return props;				
    }

    
    // need a helper method
    // read enviroment property and convert to int    
    private int getIntProperty(String propName){
    
        String propVal = env.getProperty(propName);
        
        // now convert to int
        int intPropVal = Integer.parseInt(propVal);
        
        return intPropVal;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        
        // create session factorys
	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
	// set the properties
	sessionFactory.setDataSource(myDataSource());
	sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
	sessionFactory.setHibernateProperties(getHibernateProperties());
		
	return sessionFactory;
    }
	
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
	// setup transaction manager based on session factory
	HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }	
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
          registry.addViewController("/").setViewName("index");
    }
    
}

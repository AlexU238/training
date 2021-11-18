package com.u238.training.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableTransactionManagement //used only for one manager
@EnableAspectJAutoProxy
@ComponentScan("com.u238.training")
@PropertySource({"classpath:persistence-mysql.properties"})
public class SpringConfig implements WebMvcConfigurer {

    private Environment env;

    @Autowired
    public SpringConfig(@Qualifier("environment") Environment env) {
        this.env = env;
    }

    private Logger logger = Logger.getLogger(getClass().getName());

    // Bean for ViewResolver
    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    public DataSource MySQLDataSource(String url) {

        // connection pool
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driver"));
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        //log url and user
        logger.info("jdbc.url=" + env.getProperty(url));
        logger.info("jdbc.user=" + env.getProperty("jdbc.user"));

        // set database connection props
        dataSource.setJdbcUrl(env.getProperty(url));
        dataSource.setUser(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props
        dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return dataSource;
    }

    @Bean
    public DataSource userDataSource(){
        return MySQLDataSource("jdbc.url1");
    }

    @Bean
    public DataSource studentDataSource(){
        return MySQLDataSource("jdbc.url2");
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

        return props;
    }


    // need a helper method
    // read environment property and convert to int
    private int getIntProperty(String propName) {

        //Might be null
        String propVal = env.getProperty(propName);

        return Integer.parseInt(propVal);
    }


    public LocalSessionFactoryBean SessionFactory(DataSource dataSource){

        // session factory
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }


    @Bean
    public LocalSessionFactoryBean userSessionFactory(){
        return SessionFactory(userDataSource());
    }

    @Bean
    public LocalSessionFactoryBean studentSessionFactory(){
        return SessionFactory(studentDataSource());
    }


     private HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean(name = "userTransactionManager")
    @Primary
    @Autowired
    public HibernateTransactionManager userTransactionManager(@Qualifier("userSessionFactory") SessionFactory userSessionFactory){
        return transactionManager(userSessionFactory);
    }

    @Bean
    @Autowired
    public HibernateTransactionManager studentTransactionManager(@Qualifier("studentSessionFactory") SessionFactory userSessionFactory){
        return transactionManager(userSessionFactory);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}

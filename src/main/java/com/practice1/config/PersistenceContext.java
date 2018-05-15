package com.practice1.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Alireza on 1/6/2018.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.practice1.repository","com.practice1.model"})
@Profile({"dev","rand"})
public class PersistenceContext {

    @Bean(destroyMethod = "close")
    DataSource dataSource(Environment env){
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
        dataSourceConfig.setConnectionTimeout(10000);
        dataSourceConfig.setValidationTimeout(10000);
        dataSourceConfig.setLeakDetectionThreshold(10000);
        return new HikariDataSource(dataSourceConfig);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.practice1.model", "com.practice1.repository");
        Properties jpaProperties = new Properties();
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
//        jpaProperties.put("hibernate.ejb.naming_strategy",
//                env.getRequiredProperty("hibernate.ejb.naming_strategy")
//        );
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql",
                env.getRequiredProperty("hibernate.show_sql")
        );
        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql",
                env.getRequiredProperty("hibernate.format_sql")
        );
        jpaProperties.put("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
        jpaProperties.put("hibernate.hikari.dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        jpaProperties.put("hibernate.hikari.dataSource.url", env.getProperty("db.url"));
        jpaProperties.put("hibernate.hikari.dataSource.user", env.getProperty("db.username"));
        jpaProperties.put("hibernate.hikari.dataSource.password", env.getProperty("db.password"));
        jpaProperties.put("hibernate.hikari.maximumPoolSize", "20");
        jpaProperties.put("hibernate.hikari.minimumIdle", "2");
        jpaProperties.put("hibernate.hikari.idleTimeout", "10000");
        jpaProperties.put("hibernate.hikari.dataSource.cachePrepStmts", "true");
        jpaProperties.put("hibernate.hikari.dataSource.prepStmtCacheSize", "250");
        jpaProperties.put("hibernate.hikari.dataSource.prepStmtCacheSqlLimit", "2048");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}

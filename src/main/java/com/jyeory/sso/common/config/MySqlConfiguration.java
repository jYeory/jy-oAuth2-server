package com.jyeory.sso.common.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
	basePackages = "com.jyeory.sso.web.repository",
	entityManagerFactoryRef = "mysqlEntityManager",
	transactionManagerRef = "mysqlTransactionManager"
)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class MySqlConfiguration {

	private static final String DEFAULT_IMPLICIT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy";
	private static final String DEFAULT_PHYSICAL_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy";
	private static final String PERSISTANCE_UNIT_NAME = "mysqlEntityManager";
	
	@Autowired
    private Environment env;
	
    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan( new String[] { "com.jyeory.sso.oauth.entity", "com.jyeory.sso.web.entity" });
        emf.setPersistenceUnitName(PERSISTANCE_UNIT_NAME);
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        vendorAdapter.setDatabase(Database.MYSQL);
        emf.setJpaVendorAdapter(vendorAdapter);
        
        JpaProperties jpaProperties = new JpaProperties();
        jpaProperties.setGenerateDdl(false);
        jpaProperties.setShowSql(true);
        
        HibernateProperties hibernateProperties = new HibernateProperties();
        hibernateProperties.setDdlAuto("none");
        
        String propertyPrefix = "spring.jpa.properties.";
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
        properties.put("hibernate.hbm2ddl.auto", env.getProperty(propertyPrefix+"hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty(propertyPrefix+"hibernate.dialect"));
        properties.put("hibernate.format_sql", env.getProperty(propertyPrefix+"hibernate.format_sql"));
        properties.put("hibernate.show_sql", env.getProperty(propertyPrefix+"hibernate.show_sql"));
        properties.put("hibernate.cache.use_query_cache", env.getProperty(propertyPrefix+"hibernate.cache.use_query_cache"));
        properties.put("hibernate.naming.implicit-strategy", DEFAULT_IMPLICIT_NAMING_STRATEGY);
        properties.put("hibernate.naming.physical-strategy", DEFAULT_PHYSICAL_NAMING_STRATEGY);
        
        emf.setJpaPropertyMap(properties);
        return emf;
    }

    @Bean(name="mysqlDatasource")
	public DataSource dataSource() {
    	String propertyPrefix = "spring.datasource.";
    	
        String className = env.getProperty(propertyPrefix + "driver-class-name");
        String jdbcUrl = env.getProperty(propertyPrefix + "jdbc-url");
        String username = env.getProperty(propertyPrefix + "username");
        String password = env.getProperty(propertyPrefix + "password");
        
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(className);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        
    	return new HikariDataSource(config);
	}
    
    @Bean(name="mysqlTransactionManager")
    @Primary
    public PlatformTransactionManager mysqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource());
        transactionManager.setEntityManagerFactory(mysqlEntityManager().getObject());
        return transactionManager;
    }
}

package com.agnext.unification.multidbconfig;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "nafedEntityManagerFactory", transactionManagerRef = "nafedTransactionManager", basePackages = {
		"com.agnext.unification.repository.nafed" })
public class NafedDbConfig {
	@Primary
	@Bean(name = "nafedDataSource")
	@ConfigurationProperties(prefix = "spring.nafed.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	@Primary
	@Bean(name = "nafedEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean nafedEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("nafedDataSource") DataSource nafedDataSource) {
		return builder.dataSource(nafedDataSource).packages("com.agnext.unification.entity.nafed").build();
	}
	@Primary
	@Bean(name = "nafedTransactionManager")
	public PlatformTransactionManager nafedTransactionManager(
			@Qualifier("nafedEntityManagerFactory") EntityManagerFactory nafedEntityManagerFactory) {
		return new JpaTransactionManager(nafedEntityManagerFactory);
	}
}

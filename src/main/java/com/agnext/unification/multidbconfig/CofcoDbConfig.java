package com.agnext.unification.multidbconfig;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "cofcoEntityManagerFactory", transactionManagerRef = "cofcoTransactionManager", basePackages = {
		"com.agnext.unification.repository.cofco" })
public class CofcoDbConfig {

	@Bean(name = "cofcoDataSource")
	@ConfigurationProperties(prefix = "spring.cofco.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "cofcoEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean cofcoEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("cofcoDataSource") DataSource cofcoDataSource) {
		return builder.dataSource(cofcoDataSource).packages("com.agnext.unification.entity.cofco")
				.build();
	}

	@Bean(name = "cofcoTransactionManager")
	public PlatformTransactionManager cofcoTransactionManager(
			@Qualifier("cofcoEntityManagerFactory") EntityManagerFactory cofcoEntityManagerFactory) {
		return new JpaTransactionManager(cofcoEntityManagerFactory);
	}
}

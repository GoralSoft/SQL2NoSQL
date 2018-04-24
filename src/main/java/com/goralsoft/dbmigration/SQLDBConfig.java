package com.goralsoft.dbmigration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
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
@EnableJpaRepositories(entityManagerFactoryRef = "tacwEntityManagerFactory",
    transactionManagerRef = "tacwTransactionManager", basePackages = {"com.goralsoft.dbmigration.mssql.repo"})
public class SQLDBConfig {

  @Bean(name = "tacwDataSource")
  //@ConfigurationProperties(prefix = "bar.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create()
    		.url("jdbc:sqlserver://localhost:1433;databaseName=TACW;integratedSecurity=true;")
    		//.username("")
    		//.password("")
    		.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    		.build();
  }

  @Bean(name = "tacwEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean tacwEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("tacwDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource).packages("com.goralsoft.dbmigration.mssql.domain").persistenceUnit("TACW")
        .build();
  }

  @Bean(name = "tacwTransactionManager")
  public PlatformTransactionManager tacwTransactionManager(
      @Qualifier("tacwEntityManagerFactory") EntityManagerFactory tacwEntityManagerFactory) {
    return new JpaTransactionManager(tacwEntityManagerFactory);
  }

}

package com.goralsoft.dbmigration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@EnableMongoRepositories
public class NOSQLDBConfig extends AbstractMongoConfiguration {

  @Override
  protected String getDatabaseName() {
    return "cms";
  }

  @Override
  public Mongo mongo() throws Exception {
    return new MongoClient();
  }

  @Override
  protected String getMappingBasePackage() {
    return "goralsoft.com.dbmigration.mongo.repo";
  }
}

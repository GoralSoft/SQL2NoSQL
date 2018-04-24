package com.goralsoft.dbmigration.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.goralsoft.dbmigration.mongo.domain.Papers;

public interface MPapersRepository extends MongoRepository<Papers,String> {
	public Papers findByPapercode(String papercode);
	public Papers findByPapername(String papername);	
	public long deleteByPapercode(String papercode);	
}

package com.goralsoft.dbmigration.mongo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.goralsoft.dbmigration.mongo.domain.Students;

public interface MStudentRepository extends MongoRepository<Students, String>{
	public Students findByRegno(String regno);
	//public List<Students> findByNameineng(String nameineng);	
}

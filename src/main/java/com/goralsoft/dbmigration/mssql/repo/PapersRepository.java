package com.goralsoft.dbmigration.mssql.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.goralsoft.dbmigration.mssql.domain.Papers;

@Repository
public interface PapersRepository extends JpaRepository<Papers, String> {

	@Query(value="select top 2 papercode,papername from papers", nativeQuery = true)
	public List<Papers> TopPaper();
	
	public Papers findById(String papercode);
  
}
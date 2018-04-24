package com.goralsoft.dbmigration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.goralsoft.dbmigration.mongo.repo.MPapersRepository;
import com.goralsoft.dbmigration.mssql.domain.Papers;
import com.goralsoft.dbmigration.mssql.repo.PapersRepository;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



@SpringBootApplication
//@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class })
public class SQL2NOSQLApplication implements CommandLineRunner {
	@Autowired
	private PapersRepository paperRepo;
	
	@Autowired
	private MPapersRepository mongo_paperRepo;
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(SQL2NOSQLApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SQL2NOSQLApplication.class, args);
	}

	/*
	@Autowired 	
	public NoToSqlApplication(PapersRepository p,goralsoft.com.mongo.repo.PapersRepository mp) {
		// TODO Auto-generated constructor stub
		this.paperRepo=p;
		this.mongo_paperRepo=mp;
	}
	*/
	
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("----------------------------------MSSQL Papers---------------------------------");
		/*
		for(Papers p:paperRepo.findAll())
		{
			System.out.println(p);
			
		}*/
		
		List<Papers> mssqlpapers=paperRepo.findAll();
		System.out.println(mssqlpapers);
		
		SaveToMongo(mssqlpapers);	
		
		System.out.println("----------------------------------MONGO Papers---------------------------------");
		List<com.goralsoft.dbmigration.mongo.domain.Papers> mongopapers=mongo_paperRepo.findAll();
		System.out.println(mongopapers);		
		
	}
	
	private static String getJsonSchema(Class clazz) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
	    //There are other configuration options you can set.  This is the one I needed.
	    mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

	    com.fasterxml.jackson.databind.jsonschema.JsonSchema schema = mapper.generateJsonSchema(clazz);

	    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
	}
	
	private List<Document> ConvertDoc(List<Papers> p) {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		List<Document> lst=new ArrayList<>();
		for(Papers ps:p)
		{
			String str=gson.toJson(ps);
			lst.add(Document.parse(str));
		}
		return lst;
	}

	private List<Document> ConvertListDoc(List<Map<String,Object>> p) {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		List<Document> lst=new ArrayList<>();
		for(Map<String,Object> ps:p)
		{
			String str=gson.toJson(ps);
			lst.add(Document.parse(str));
		}
		return lst;
	}

	private void SaveToMongo(List<Papers> p) {
		// TODO Auto-generated method stub
		mongo_paperRepo.deleteAll();
		
		for(Papers ps:p)			
		mongo_paperRepo.save(new com.goralsoft.dbmigration.mongo.domain.Papers(ps.getId(),ps.getPapername()));
	}
}

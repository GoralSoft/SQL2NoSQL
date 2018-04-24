package com.goralsoft.dbmigration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.goralsoft.dbmigration.mongo.domain.Papers;
import com.goralsoft.dbmigration.mongo.domain.Students;
import com.goralsoft.dbmigration.mongo.repo.MPapersRepository;
import com.goralsoft.dbmigration.mongo.repo.MStudentRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)

@RequestMapping("/model")
public class DBController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	Gson gson=new Gson();
	private MPapersRepository mongo_paperRepo;
	//private MStudentRepository mongo_studentRepo;
	
	@RequestMapping(value = "/savePaper", method = {RequestMethod.POST})
	public void savePaper(@RequestBody Papers paper) {
		//System.out.println(paper+"....");
	    //return paper;		
		System.out.println("Save Start...");
		mongo_paperRepo.save(paper);
		System.out.println("Save done..."+gson.toJson(paper));
	}
	@RequestMapping(value = "/updatePaper/{papercode}", method = {RequestMethod.PUT})
	public void updatePaper(@PathVariable("papercode") String pcode, @RequestBody Papers paper ) 
    {        
        //Query where_qry = new Query(org.springframework.data.mongodb.core.query.Criteria.where("papercode").is(pcode));
        // Execute the query and find one matching entry
		System.out.println("Update Paper starts..."+pcode);
        Papers p = mongo_paperRepo.findByPapercode(pcode);//query, Person.class);
        //p.setPapername(pname);
        p.setPapername(paper.getPapername());
        mongo_paperRepo.save(p);
        System.out.println("Udpate Done..."+gson.toJson(p));
    }
	@Autowired
	public DBController(MPapersRepository mPapersRepo) {
		// TODO Auto-generated constructor stub
		this.mongo_paperRepo=mPapersRepo;
		//this.mongo_studentRepo=mStudentsRepo;
	}	
	
	@RequestMapping(value = "/getPapers")
	public String getPapers()	{	
		//System.out.println("inside get...");
		List<com.goralsoft.dbmigration.mongo.domain.Papers> mpapers=mongo_paperRepo.findAll();
		return gson.toJson(mpapers);				
	}
	@RequestMapping("/getPaper/{papercode}")
	public String getPaper(@PathVariable("papercode") String papercode) {
		System.out.println("A Paper Select start for..."+papercode);
	    com.goralsoft.dbmigration.mongo.domain.Papers p = mongo_paperRepo.findByPapercode(papercode);
	    System.out.println("Select Done..."+gson.toJson(p));
	    return gson.toJson(p);
	    
	}
	
	@DeleteMapping("/deletePaper/{papercode}")
	public void deletePaper(@PathVariable("papercode") String papercode) {
		System.out.println("Delete start for the paper..."+papercode);
	    com.goralsoft.dbmigration.mongo.domain.Papers p = mongo_paperRepo.findByPapercode(papercode);	    
	    mongo_paperRepo.delete(p);
	    System.out.println("Delete Done..."+gson.toJson(p));
	    //mongo_paperRepo.deleteByPapercode(papercode);
	    //return gson.toJson(p);
	}

}

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
import com.goralsoft.dbmigration.mongo.domain.Students;
import com.goralsoft.dbmigration.mongo.repo.MStudentRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)

@RequestMapping("/student")
public class StudentController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	Gson gson=new Gson();
	//private MPapersRepository mongo_paperRepo;
	private MStudentRepository mongo_studentRepo;
	
	@Autowired
	public StudentController(MStudentRepository msRepo) {
		// TODO Auto-generated constructor stub
		//this.mongo_paperRepo=mPapersRepo;
		this.mongo_studentRepo=msRepo;
	}
	
	@RequestMapping(value = "/saveStudent", method = {RequestMethod.POST})
	public void saveStudent(@RequestBody Students student) {			
		//System.out.println("Save Start..."+student);
		mongo_studentRepo.save(student);
		System.out.println("Save done..."+gson.toJson(student));
	}
	@RequestMapping(value = "/updateStudent/{regno}", method = {RequestMethod.PUT})
	public void updateStudent(@PathVariable("regno") String regno, @RequestBody Students st ) 
    {        
        //Query where_qry = new Query(org.springframework.data.mongodb.core.query.Criteria.where("papercode").is(pcode));
        // Execute the query and find one matching entry
		System.out.println("Update Student starts..."+regno);
        Students s = mongo_studentRepo.findByRegno(regno);//query, Person.class);
        //p.setPapername(pname);
        s.setNameineng(st.getNameineng());
        mongo_studentRepo.save(s);
        System.out.println("Udpate Done..."+gson.toJson(s));
    }	
	
	@RequestMapping(value = "/getStudents")
	public List<Students> getStudents()	{	
		//System.out.println("inside getStudents...");
		List<Students> mstudents=mongo_studentRepo.findAll();
		System.out.println(mstudents);
		return mstudents;				
	}	
	
	@RequestMapping("/getStudent/{regno}")
	public String getStudent(@PathVariable("regno") String rno) {
		System.out.println("Student Select start for..."+rno);
	    Students s = mongo_studentRepo.findByRegno(rno);
	    System.out.println("Select Done..."+gson.toJson(s));
	    return gson.toJson(s);	    
	}
	
	@DeleteMapping("/deleteStudent/{regno}")
	public void deleteStudent(@PathVariable("regno") String rno) {
		System.out.println("Delete start for the student..."+rno);
	    Students s = mongo_studentRepo.findByRegno(rno);	    
	    mongo_studentRepo.delete(s);
	    System.out.println("Delete Done..."+gson.toJson(s));
	    //mongo_paperRepo.deleteByPapercode(papercode);
	    //return gson.toJson(p);
	}
}

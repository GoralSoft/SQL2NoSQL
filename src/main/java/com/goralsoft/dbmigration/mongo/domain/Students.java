package com.goralsoft.dbmigration.mongo.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Students {
	@Id
	private String id;
	private String Regno;
	private String nameineng;
	private String course;
	//private String course_name;
	private String deptid;
	//private String dept_name;
	private Date dob;
	private Integer Batch;
	
	public Students() {
		
	}
	
	public Students(String regno,String name,String course,String dept, Date dob,Integer batch) {
		this.Regno=regno;
		this.nameineng=name;
		this.course=course;
		this.deptid=dept;
		this.dob=dob;
		this.Batch=batch;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegno() {
		return Regno;
	}

	public void setRegno(String regno) {
		this.Regno = regno;
	}

	public String getNameineng() {
		return nameineng;
	}

	public void setNameineng(String nameineng) {
		this.nameineng = nameineng;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course_id) {
		this.course = course_id;
	}

	

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String dept_id) {
		this.deptid = dept_id;
	}

	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getBatch() {
		return Batch;
	}

	public void setBatch(Integer batch) {
		this.Batch = batch;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", Regno=" + Regno + ", nameineng=" + nameineng + ", course=" + course
				+ ", deptid=" + deptid + ", dob=" + dob
				+ ", batch=" + Batch + "]";
	}

	
	
	
	
}

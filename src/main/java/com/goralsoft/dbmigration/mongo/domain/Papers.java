package com.goralsoft.dbmigration.mongo.domain;

import org.springframework.data.annotation.Id;

public class Papers {

	
	@Id
	private String id;
	
	public String papercode;		
	
	public String papername;
	
	public Papers() {
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public Papers(String papercode,String papername) {	
		this.papercode=papercode;
		this.papername = papername;
	}	
	
	public String getPapercode() {
		return papercode;
	}
	
	public void setPapercode(String papercode) {
		this.papercode = papercode;
	}
	
	
	public String getPapername() {
		return papername;
	}
	
	public void setPapername(String papername) {
		this.papername = papername;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Papers [papercode=" + papercode + ", papername=" + papername + "]";
	}
	
	
	
	
}

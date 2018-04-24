package com.goralsoft.dbmigration.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Papers")
public class Papers {

  @Id
  //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="bar_id_seq")
  //@SequenceGenerator(name="bar_id_seq", sequenceName="bar_id_seq", allocationSize=1)
  @Column(name = "papercode")
  private String id;

  @Column(name = "papername")
  private String papername;

  Papers(String papername) {
    this.papername = papername;
  }

  Papers() {
    // Default constructor needed by JPA
  }

  public String getPapers() {
    return papername;
  } 
  

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Papers [id=" + id + ", papername=" + papername + "]";
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

/**
 * @return the papername
 */
public String getPapername() {
	return papername;
}

/**
 * @param papername the papername to set
 */
public void setPapername(String papername) {
	this.papername = papername;
}
 
public String getTopPaper()
{
	return "Papers [id=" + id + ", papername=" + papername + "]";
}
  
}
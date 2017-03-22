package com.carpart.entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
public class Log{
	@Id
	@GeneratedValue
	private Long id;
	private Long userid;
	private Short loglevel;
	private Timestamp operatetime;
	private Short operatetype;
	private String logcontent;
	private String broswer;//用户浏览器类型
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name = "loglevel")
	public Short getLoglevel() {
		return this.loglevel;
	}

	public void setLoglevel(Short loglevel) {
		this.loglevel = loglevel;
	}

	@Column(name = "operatetime", nullable = false, length = 35)
	public Timestamp getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(Timestamp operatetime) {
		this.operatetime = operatetime;
	}

	@Column(name = "operatetype")
	public Short getOperatetype() {
		return this.operatetype;
	}

	public void setOperatetype(Short operatetype) {
		this.operatetype = operatetype;
	}

	@Column(name = "logcontent", nullable = false, length = 2000)
	public String getLogcontent() {
		return this.logcontent;
	}

	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}

	@Column(name = "note", length = 300)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Column(name = "broswer", length = 100)
	public String getBroswer() {
		return broswer;
	}

	public void setBroswer(String broswer) {
		this.broswer = broswer;
	}

}
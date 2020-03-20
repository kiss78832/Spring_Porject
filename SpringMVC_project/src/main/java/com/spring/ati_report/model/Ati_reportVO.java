package com.spring.ati_report.model;

import java.sql.Timestamp;

public class Ati_reportVO implements java.io.Serializable{
	private String report_id;
	private String article_id;
	private String sf_id;
	private String member_id;
	private Integer report_status;
	@Override
	public String toString() {
		return "Ati_reportVO [report_id=" + report_id + ", article_id=" + article_id + ", sf_id=" + sf_id
				+ ", member_id=" + member_id + ", report_status=" + report_status + ", report_time=" + report_time
				+ "]";
	}
	private Timestamp report_time;
	
	
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getSf_id() {
		return sf_id;
	}
	public void setSf_id(String sf_id) {
		this.sf_id = sf_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Integer getReport_status() {
		return report_status;
	}
	public void setReport_status(Integer report_status) {
		this.report_status = report_status;
	}
	public Timestamp getReport_time() {
		return report_time;
	}
	public void setReport_time(Timestamp report_time) {
		this.report_time = report_time;
	}
	
	
	
	
}

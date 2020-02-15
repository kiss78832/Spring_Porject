package com.news.model;

import java.sql.Timestamp;

public class NewsVO implements java.io.Serializable{
	private String news_id;
	private String sf_id;
	private byte[] news_image;
	private String news_content;
	private Timestamp news_time;
	private String news_src;
	
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getSf_id() {
		return sf_id;
	}
	public void setSf_id(String sf_id) {
		this.sf_id = sf_id;
	}
	public byte[] getNews_image() {
		return news_image;
	}
	public void setNews_image(byte[] news_image) {
		this.news_image = news_image;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public Timestamp getNews_time() {
		return news_time;
	}
	public void setNews_time(Timestamp news_time) {
		this.news_time = news_time;
	}
	public String getNews_src() {
		return news_src;
	}
	public void setNews_src(String news_src) {
		this.news_src = news_src;
	}

}


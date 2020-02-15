package com.article.model;

import java.sql.Timestamp;

public class ArticleVO implements java.io.Serializable {

	private String member_id;
	private String article_id;
	private String article_title;
	private String article_content;
	private String article_image;
	private Timestamp article_time;
	private Integer watched_c;
	private Integer like_c;
	private String tag;
	private String article_image_2;
	private String article_image_3;
	private String image_css;
	
	
	public String getImage_css() {
		return image_css;
	}

	public void setImage_css(String image_css) {
		this.image_css = image_css;
	}



	public String getArticle_image_2() {
		return article_image_2;
	}

	public void setArticle_image_2(String article_image_2) {
		this.article_image_2 = article_image_2;
	}

	public String getArticle_image_3() {
		return article_image_3;
	}

	public void setArticle_image_3(String article_image_3) {
		this.article_image_3 = article_image_3;
	}



	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getArticle_id() {
		return article_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public String getArticle_image() {
		return article_image;
	}

	public void setArticle_image(String article_image) {
		this.article_image = article_image;
	}

	public Timestamp getArticle_time() {
		return article_time;
	}

	public void setArticle_time(Timestamp article_time) {
		this.article_time = article_time;
	}

	public Integer getWatched_c() {
		return watched_c;
	}

	public void setWatched_c(Integer watched_c) {
		this.watched_c = watched_c;
	}

	public Integer getLike_c() {
		return like_c;
	}

	public void setLike_c(Integer like_c) {
		this.like_c = like_c;
	}


	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}

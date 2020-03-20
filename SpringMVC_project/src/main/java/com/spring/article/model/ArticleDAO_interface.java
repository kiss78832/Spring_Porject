package com.spring.article.model;

import java.util.*;



public interface ArticleDAO_interface {
	public void insert(ArticleVO articleVO);
    public void update(ArticleVO articleVO);
    public void delete(String article_id);
    public ArticleVO findByPrimaryKey(String article_id);
    public List<ArticleVO> getAll();
    
    /*新增方法*/
    public List<ArticleVO> find_tag(String element);
    public Integer msg_count(String article_id);
    public List<ArticleVO> getAll_status(Integer article_status);
}

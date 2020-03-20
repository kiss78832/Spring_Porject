package com.spring.article.model;

import java.util.List;


public class ArticleService {
	
	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleJNDI();
	}

	public ArticleVO addArticle(String member_id, String article_title, String article_content,String article_image
			,Integer like_c,Integer watched_c,String tag,String article_image_2,String article_image_3, String image_css) {

		
		ArticleVO articleVO = new ArticleVO();
		articleVO.setMember_id(member_id);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_image(article_image);
		articleVO.setWatched_c(like_c);
		articleVO.setLike_c(watched_c);
		articleVO.setTag(tag);
		articleVO.setArticle_image_2(article_image_2);
		articleVO.setArticle_image_3(article_image_3);
		articleVO.setImage_css(image_css);
		dao.insert(articleVO);

		return articleVO;
	}

	public ArticleVO updateArticle(String article_id,String member_id, String article_title, String article_content,String article_image
			,Integer like_c,Integer watched_c,String tag,String article_image_2,String article_image_3,String image_css) {

		ArticleVO articleVO = new ArticleVO();
		
		articleVO.setArticle_id(article_id);
		articleVO.setMember_id(member_id);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_image(article_image);
		articleVO.setWatched_c(like_c);
		articleVO.setLike_c(watched_c);
		articleVO.setTag(tag);
		articleVO.setArticle_image(article_image_2);
		articleVO.setArticle_image(article_image_3);
		articleVO.setImage_css(image_css);
		dao.update(articleVO);
		
		return articleVO;
	}
	
	public ArticleVO updateArticle(ArticleVO articleVO) {
		dao.update(articleVO);
		return articleVO;
	}

	public void deleteArticle(String article_id){
		dao.delete(article_id);
	}

	public ArticleVO getOneArticle(String article_id) {
		return dao.findByPrimaryKey(article_id);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
	
	public List<ArticleVO> getArticle_tag(String element) {
		return dao.find_tag(element);
		
	}
	
	public Integer getArticleMsg(String article_id) {
		return dao.msg_count(article_id);
	}
	/*2019-09-19新增*/
	public List<ArticleVO> getAll_status(Integer article_status) {
		return dao.getAll_status(article_status);
		
	}
	
	public static void main(String[] args) {

		ArticleService  dao = new ArticleService();	
//		dao.updateArticle("AR000009","A001","安安你好","安安你好嗎",null,6,6,6,"fuck","11","22");
		

		
	}
		
}

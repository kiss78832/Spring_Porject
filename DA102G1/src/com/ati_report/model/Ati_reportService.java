package com.ati_report.model;

import java.util.List;


public class Ati_reportService {
	
	private Ati_reportDAO_interface dao;

	public Ati_reportService() {
		dao = new Ati_reportJNDI();
	}

	public Ati_reportVO addAti(String article_id, String sf_id, String member_id
			,Integer report_status) {

		Ati_reportVO atiVO = new Ati_reportVO();
		atiVO.setArticle_id(article_id);
		atiVO.setSf_id(sf_id);
		atiVO.setMember_id(member_id);
		atiVO.setReport_status(report_status);
		dao.insert(atiVO);
		
		return atiVO;
	}

	public Ati_reportVO updateAti(String report_id, String article_id, String sf_id, String member_id
			,Integer report_status){

		Ati_reportVO atiVO = new Ati_reportVO();
		
		atiVO.setReport_id(report_id);
		atiVO.setArticle_id(article_id);
		atiVO.setSf_id(sf_id);
		atiVO.setMember_id(member_id);
		atiVO.setReport_status(report_status);
		dao.update(atiVO);
		
		return atiVO;
	}

	public void deleteAti(String article_id) {
		dao.delete(article_id);
	}

	public Ati_reportVO getOneAti(String article_id) {
		return dao.findByPrimaryKey(article_id);
	}

	public List<Ati_reportVO> getAll() {
		return dao.getAll();
	}
	
	public static void main(String[] args) {

		Ati_reportService  dao = new Ati_reportService();	
//		dao.addAti("AR000008","S000001","A001",1);
//		dao.updateAti("AT000023","AR000018","S000001","A001",10);
		System.out.println(dao.getOneAti("AR000001").toString());
	}
		
}

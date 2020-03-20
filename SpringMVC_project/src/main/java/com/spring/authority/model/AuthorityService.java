package com.spring.authority.model;

import java.util.List;

public class AuthorityService {
	
	private AuthorityDAO_interface dao;
	
	public AuthorityService() {
		dao = new AuthorityDAO();
	}
	
	public AuthorityVO addAuthority(String sf_id,String fun_num) {
		AuthorityVO authorityVO = new AuthorityVO();
		
		authorityVO.setFun_num(fun_num);
		authorityVO.setSf_id(sf_id);
		
		dao.insert(authorityVO);
		
		return authorityVO;
	}
	
	public AuthorityVO updateAuthority(String sf_id,String fun_num) {
		AuthorityVO authorityVO = new AuthorityVO();
		
		authorityVO.setFun_num(fun_num);
		authorityVO.setSf_id(sf_id);
		
		dao.insert(authorityVO);
		
		return authorityVO;
	}
	
	public void deleteAuthority(String sf_id) {
		dao.delete( sf_id);
	}
	
	public List<AuthorityVO> findByPrimaryKey(String sf_id) {
		return dao.findByPrimaryKey(sf_id);
	}
	
	public List<AuthorityVO> getAll(){
		return dao.getAll();
	}

}

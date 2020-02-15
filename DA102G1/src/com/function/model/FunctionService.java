package com.function.model;

import java.util.List;

public class FunctionService {
	
	private FunctionDAO_interface dao;
	
	public FunctionService() {
		dao = new FunctionDAO();
	}
	
	
	public FunctionVO addFunction(String fun_num,String fun_name,
			String fun_url) {
		
		FunctionVO functionVO = new FunctionVO();
		
		functionVO.setFun_name(fun_name);
		functionVO.setFun_num(fun_num);
		functionVO.setFun_url(fun_url);
		
		dao.insert(functionVO);
		
		return functionVO;
	}
	
	public FunctionVO updateFunction(String fun_num,String fun_name,
			String fun_url) {
		
		FunctionVO functionVO = new FunctionVO();
		
		functionVO.setFun_name(fun_name);
		functionVO.setFun_num(fun_num);
		functionVO.setFun_url(fun_url);
		
		dao.update(functionVO);
		
		return functionVO;
	}
	
	public void deleteFunction(String fun_num) {
		dao.delete(fun_num);
	}
	
	public FunctionVO getOneFunction(String fun_num) {
		return dao.findByPrimaryKey(fun_num);
	}
	
	public List<FunctionVO> getAll(){
		return dao.getAll();
	}

}

package com.spring.authority.model;

public class AuthorityVO {

	private String sf_id;
	private String fun_num;
	
	
	public String getSf_id() {
		return sf_id;
	}
	public void setSf_id(String sf_id) {
		this.sf_id = sf_id;
	}
	public String getFun_num() {
		return fun_num;
	}
	public void setFun_num(String fun_num) {
		this.fun_num = fun_num;
	}
	
	
}


//<c:forEach var="authorityVO" items="${functionSvc.getAll()}"><c:set var="same" value="0"/>
//<c:forEach var="myAuthorityVO" items="${authoritySvc.findByPrimaryKey(staffVO.sf_id) }">
//<c:set var="same" value="${same+1}"/>
//<c:if test="${(authorityVO.fun_num).equals(myAuthorityVO.fun_num)}">
//	<div class="auth">
//	<input type="checkbox" checked data-toggle="toggle" data-onstyle="warning" name="authData" 
//	class="funCheck" value="${functionSvc.getOneFunction(authorityVO.fun_num).fun_num}">
//	<br>${functionSvc.getOneFunction(authorityVO.fun_num).fun_name}
//	<c:set var="same" value="0"/>
//	</div>
//</c:if>	
//<c:if test="${same==authoritySvc.findByPrimaryKey(staffVO.sf_id).size()}">
//	<div class="auth">
//	<input type="checkbox"  data-toggle="toggle" data-onstyle="warning" name="authData" 
//	class="funCheck" value="${functionSvc.getOneFunction(authorityVO.fun_num).fun_num}">
//	<br>${functionSvc.getOneFunction(authorityVO.fun_num).fun_name}
//	</div>
//</c:if>
//</c:forEach>
//</c:forEach>
//<input type="hidden" name="action" value="authChange">
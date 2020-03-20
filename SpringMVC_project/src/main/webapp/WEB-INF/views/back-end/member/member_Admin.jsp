<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.spring.member.model.*,java.util.*"%>
<% 	
	MemberService memSvc = new  MemberService(); 
	List<MemberVO> list = memSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>Island Peak accountManager</title>
	<meta charset="utf-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/backgroundContext.css">
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/body.css">

<style type="text/css">
		  /*本地字體引入*/
  @font-face{
    font-family: Russo;
    src:url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
     unicode-range: U+00-024F;
  }
  @font-face{
    font-family: Noto;
    src:url('/DA102G1/assets/fonts/NotoSansTC-Regular.otf');
    unicode-range: U+4E00-9FFF;
  }


	.memHead b {
		font-size:24px;
	}
	.memHead{
		margin-top: 35px;
		margin-bottom: 20px;
	}
	
	.memfm select,button{
		display: inline-block;
		position: relative;
		text-align:center;
	}

</style>


</head>
<body>
<div class="container-fluid">
	<div class="row">
	
		<jsp:include page="/WEB-INF/common/adminNavbar.jsp" flush="true"/>

		<div class="col-lg-12 contentbox" >
		  <img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
		
		  
			<jsp:include page="/WEB-INF/common/adminSideBar.jsp" flush="true"/>
			
		  
			<div class="col-lg-10 col-md-10 cb rightContent">
		     
<table class="table table-hover table-dark table-striped table-bordered table-responsive-lg " style="color:white;">
 	<thead>
	  <tr>
	    <th scope="col">帳號</th>
	    <th scope="col">密碼</th>
	    <th scope="col">姓名</th>
	    <th scope="col">性別</th>
	    <th scope="col">生日</th>
	    <th scope="col">手機</th>
	    <th scope="col">Email</th>
	    <th scope="col">驗證狀態</th>
	    <th scope="col">註冊時間</th>
	    <th scope="col">攻略點數</th>
	    <th scope="col">攻略進度</th>
	
	  </tr>
	</thead>
<tbody>
	<%@ include file="pages/listAll.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1 %>">
		<tr>
			<td>${memberVO.member_id}</td>
			<td>${memberVO.password}</td>
			<td>${memberVO.m_name}</td>
			<td>${memberVO.gender}</td>
			<td>${memberVO.birthday}</td>
			<td>${memberVO.cellphone}</td>
			<td>${memberVO.m_email}</td>
			<td>${memberVO.validation}</td>
			<td>${memberVO.registered}</td>
			<td>${memberVO.adventure_point}</td>
			<td>${memberVO.raiders_rate}</td>
		</tr>
	</c:forEach>
  </tbody>
</table>

<%@ include file="pages/changePage.file" %>
		
		  </div>
		
		</div>


	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
	</div>
</div>




<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
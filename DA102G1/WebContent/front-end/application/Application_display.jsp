<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.staff.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="com.info.model.*"%>
<%@page import="com.application.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="mSvc" class="com.member.model.MemberService"/>


<% 
	ApplicationService appSvc = new ApplicationService();
	pageContext.setAttribute("appSvc", appSvc);
	String member_id =(String)session.getAttribute("member_id");
	List <ApplicationVO> list = appSvc.getOneApp_List("A001");
	pageContext.setAttribute("list",list);
	
	MemberService mbrSvc = new MemberService();
	pageContext.setAttribute("mbrSvc", mbrSvc);
	
	InfoService infoSvc = new InfoService();
	pageContext.setAttribute("infoSvc", infoSvc);
	
%>



<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
  <title>Island Peak Member Article</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>




<!-- member -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/memberContent.css">

  <style type="text/css">
      /*本地字體引入*/
  @font-face{
    font-family: Russo;
    src:url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
     unicode-range: U+00-024F;
  }
  @font-face{
    font-family: Noto;
    src:url('/DA102G1/assets/fonts/NotoSansTC-Medium.otf');
    unicode-range: U+4E00-9FFF;
  }


.info_top_add {
    border-collapse:collapse;
    width:70%;
    text-align: center;
    margin: 93px auto 0px auto;
}

.add_input {
 	width: 50px; 
}

.info_top_add th,.info_top_add td {
	padding: 10px 0px;
}

.info_top_add th {
	color: brown;
	background: lightgray;
}

.info_top_add tr {
	border-top: 4px solid #fff;
	background: dimgray;
}
.app_time{
	width: 230px;
}


  </style>

</head>
<body>

	<jsp:include page="/common/signup.jsp" flush="true"/>


<div class="container-fluid">
  <div class="row">
	
	<jsp:include page="/common/navbar.jsp" flush="true"/>

<div class="col-lg-12 contentbox" >
<img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
  
<jsp:include page="/common/memberSideBar.jsp" flush="true"/>

  <div id="cright" class="col-lg-10 col-md-10 cb">
    
    <table class="info_top_add">

							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

									
							<tr> 
								<th class="app_other">會員名稱</th>
								<th class="app_other">路線名稱</th>
								<th class="app_other">緊急聯絡人</th>
								<th class="app_other">緊急聯絡人電話</th>
								<th class="app_other">衛星電話</th>
								<th class="app_other">無線電頻率</th>
								<th class="app_other">申請進度</th>
							</tr>

						
						<c:forEach var="appVO" items="${list}">
							<form action="<%=request.getContextPath()%>/ati_report/ati_report.do" method="post">
								<tr id="talbe_info">
									<td class="app_other">${mbrSvc.getOneMember(appVO.member_id).m_name}</td>
									<td class="app_other">${infoSvc.getOneInfo(appVO.route_id).route_Name}</td>
									<td class="app_other">${appVO.egc_contact}</td>
									<td class="app_other">${appVO.egc_phone}</td>
									<td class="app_other">${appVO.satellite}</td>
									<td class="app_other">${appVO.radio}</td>
								    <td class="app_other" style="color:darkorange"><c:if test="${appVO.app_status == 0}">未審核</c:if></td>
								    <td class="app_other"><c:if test="${appVO.app_status == 1}">審核通過</c:if></td>
									<td class="app_other" style="color:red"><c:if test="${appVO.app_status == 2}">審核未過</c:if></td>
								</tr>
							</form>
						</c:forEach>
						</table>
    

  </div>

</div>



		<jsp:include page="/common/footer.jsp" flush="true"/>	
  </div>
</div>

<!-- navJS -->
<!-- bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.spring.staff.model.*"%>
<%@page import="com.spring.member.model.*"%>
<%@page import="com.spring.group.model.*"%>
<%@page import="com.spring.ati_report.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="mSvc" class="com.spring.member.model.MemberService"/>
<!-- staffSvc -->
<jsp:useBean id="staffSvc" class="com.spring.staff.model.StaffService"/>
<jsp:useBean id="aSvc" class="com.spring.article.model.ArticleService"/>

<% 
		
	Ati_reportService ati_reportSvc = new Ati_reportService();
	List <Ati_reportVO> list = ati_reportSvc.getAll();
	pageContext.setAttribute("ati_reportSvc", ati_reportSvc);
	pageContext.setAttribute("list",list);
	

	String account = (String)session.getAttribute("admin");
	StaffVO staffVO = staffSvc.findByAccount(account);
	pageContext.setAttribute("staffVO", staffVO);
	
	
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
  <title>Island Peak Article Report</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<!-- body&footer -->

<!-- member -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/backgroundContext.css">

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
.btn_pass{
	border: none;
    background: floralwhite;
    padding: 6px 12px;
    border-radius: 10px;
    box-shadow: 2px 4px 4px 2px;
}

  </style>

</head>
<body>

	<jsp:include page="/WEB-INF/common/signup.jsp" flush="true"/>


<div class="container-fluid">
  <div class="row">
	
	<jsp:include page="/WEB-INF/common/adminNavbar.jsp" flush="true"/>

<div class="col-lg-12 contentbox" >
<img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">
  
  <jsp:include page="/WEB-INF/common/adminSideBar.jsp" flush="true"/>
  
  <div id="cright" class="col-lg-10 col-md-10 cb rightContent">
    
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
								<th class="app_other">檢舉編號</th>
								<th class="app_other">文章標題</th>
								<th class="app_other">管理員名稱</th>
								<th class="app_other">會員名稱</th>
								<th class="app_other">檢舉狀態</th>
								<th class="app_time">檢舉時間</th>
								<th class="app_other">封鎖</th>
								<th class="app_other">開放</th>
								
<!-- 							先拿會員id去揪團成員查，取得揪團id，用揪團id去查申請狀態 -->
							</tr>

					
						<c:forEach var="ati_reportVO" items="${list}">
							<form action="<%=application.getContextPath()%>/ati_report/ati_report.do" method="get">
								<tr id="talbe_info">
									<td class="app_other">${ati_reportVO.report_id}</td>
									<td class="app_other">${aSvc.getOneArticle(ati_reportVO.article_id).article_title}</td>
									
									<c:if test="${ati_reportVO.sf_id != null}"><td class="app_other">${staffSvc.getOneStaff(ati_reportVO.sf_id).sf_name}</td></c:if>
									<c:if test="${ati_reportVO.sf_id == null}"><td class="app_other" style="color:red">未有管理者處理</td></c:if>
									
									<td class="app_other">${mSvc.getOneMember(ati_reportVO.member_id).m_name}</td>
									
									<c:if test="${ati_reportVO.report_status == 1}"><td class="app_other">未處理</td></c:if>
									<c:if test="${ati_reportVO.report_status == 2}"><td class="app_other">已封鎖</td></c:if>
									
									<td class="app_time"><fmt:formatDate value="${ati_reportVO.report_time}" pattern="yyyy-mm-dd hh:dd:ss"/></td>
									<td class="app_other"><button type="submit" class="btn_pass" name="action" value="update_status">封鎖</button></td>
									<input type="hidden" name="article_id" value="${ati_reportVO.article_id}" >
									<input type="hidden" name="sf_id" value="${staffVO.sf_id}" >
									<td class="app_other"><button type="submit" class="btn_pass" name="action" value="update_open">開放</button></td>
								</tr>
							</form>
						</c:forEach>
						</table>
    

  </div>

</div>



		<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>	
  </div>
</div>


<!-- bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
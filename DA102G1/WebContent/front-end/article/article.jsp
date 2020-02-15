<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.staff.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="com.group.model.*"%>
<%@page import="com.article.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="mSvc" class="com.member.model.MemberService"/>
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
memberVO = mSvc.getOneMember(memberVO.getMember_id());
pageContext.setAttribute("memberVO", memberVO);
%>

<% 
	ArticleService articleSvc = new ArticleService();
	List <ArticleVO> list = articleSvc.getAll();
	pageContext.setAttribute("articleSvc", articleSvc);
	pageContext.setAttribute("list",list);
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
.app_other{
	
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
								<th class="app_other">文章編號</th>
								<th class="app_time">文章標題</th>
								<th class="app_time">發送時間</th>
								<th class="app_other">愛心數</th>
								<th class="app_other">修改</th>
							
								
<!-- 							先拿會員id去揪團成員查，取得揪團id，用揪團id去查申請狀態 -->
							</tr>

						
						<c:forEach var="articleVO" items="${list}">
							<form action="<%=application.getContextPath()%>/article/article.do" method="post">
								<tr id="talbe_info">
									<td class="app_other">${articleVO.article_id}</td>
									<td class="app_time">${articleVO.article_title}</td>
									<td class="app_time"><fmt:formatDate value="${articleVO.article_time}" pattern="yyyy-mm-dd hh:dd:ss"/></td>
									<td class="app_other">${articleVO.like_c}</td>
									<td class="app_other">
										<button type="submit" name="action" value="update">修改</button>
										<input type="hidden" name="article_id" value="${articleVO.article_id}"/>

									</td>
								
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
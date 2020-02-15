<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.memberinfo.model.*"%>
<%@ page import="java.util.*" %>

<%
	MemberService mSvc = new MemberService();
	pageContext.setAttribute("mSvc", mSvc);
%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
%>

<% 
	MemberinfoService memberinfoSve = new MemberinfoService();
	pageContext.setAttribute("memberinfoSve", memberinfoSve);
%>

<% 
	GroupService groupSvc = new GroupService();
	GroupVO groupVO = new GroupVO();
	List<GroupVO> list = groupSvc.getGroupLeader(memberVO.getMember_id(),1,3,4); 
	pageContext.setAttribute("list", list);
%>

<%
	List<GroupVO>list2 = groupSvc.GroupHistory(memberVO.getMember_id(), 0);
	pageContext.setAttribute("list2", list2);
%>

<%
	HashMap st = new HashMap();
	st.put(0, "成團");
	st.put(1, "未成團");
	st.put(2, "取消揪團");
	st.put(3, "人數已滿");
	st.put(4, "審核中");
	pageContext.setAttribute("status", st);
%>

<jsp:useBean id="memberinfoSvc" class="com.memberinfo.model.MemberinfoService"/>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
  <title>Island Peak My Group</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- form style -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/assets/stylesheets/joinGroup.css">
<!-- form style -->

<!-- body&footer -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/assets/stylesheets/body.css">

<!-- member -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/assets/stylesheets/memberContent.css">

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

 	
  </style>

</head>
<body>
		
	<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
		
		
	<jsp:include page="/common/signup.jsp" flush="true"/>


<div class="container-fluid">
  <div class="row">
	
	<jsp:include page="/common/navbar.jsp" flush="true"/>

<div class="col-lg-12 contentbox" >
<img src="<%= application.getContextPath()%>/assets/images/snowpeak/forest-1743206.jpg">
  
 <jsp:include page="/common/memberSideBar.jsp"></jsp:include>
 
  <div id="cright" class="col-lg-10 col-md-10 cb">

       <div class="tab-center">
	
		<div class="topbox">
		 	<h1>個人活動列表</h1>
		</div>
		
		
		<div class="midbox">
			<table class="grouplist">
				<tr>
					<th>團主</th>
					<th>揪團名稱</th>
					<th>團員人數</th>
					<th>主要路線</th>
					<th>申請入園日期</th>
					<th>成團狀態</th>
					<th>修改</th>
					<th>詳細資訊</th>
					<th>取消揪團</th>
				</tr>
					
				<c:forEach var="groupVO" items="${list}" >		
					
				<tr>
				    <td>${mSvc.getOneMember(groupVO.gp_leader).m_name}</td>
					<td>${groupVO.gp_name}</td>
					<td>${memberinfoSvc.countByGroupId(groupVO.group_id)}/${groupVO.gp_nbp}</td>
					<td>${groupVO.target_loca}</td>
					<td>${groupVO.first_day}</td>
<%-- 					<td>${groupVO.end_day}</td>  --%>
					<td>${status[groupVO.gp_status]}</td>
				 
				  
					
					<td>
						<img src="<%= application.getContextPath()%>/assets/images/Icon/change.png" class="pic">
							<form ACTION="<%= request.getContextPath()%>/group/group.do" id="form1" METHOD="POST">
								<input type="hidden" name="group_id" value="${groupVO.group_id}">
								<input type="hidden" name="action" value="getOne_For_Update">	
							</form>
					</td>
					
					<td>
						<img src="<%= application.getContextPath()%>/assets/images/Icon/database.png" class="pic">
							<form ACTION="<%= request.getContextPath()%>/group/group.do" id="form1" METHOD="POST">
								<input type="hidden" name="group_id" value="${groupVO.group_id}">
								<input type="hidden" name="action" value="detailed">	
							</form>
					</td>
						 
					<td>
						<img src="<%= application.getContextPath()%>/assets/images/Icon/bin.png" class="pic">
							<form ACTION="<%= request.getContextPath()%>/group/group.do" id="form1" METHOD="POST">
								<input type="hidden" name="group_id" value="${groupVO.group_id}">
								<input type="hidden" name="action" value="updateStatus">	
							</form>
					</td>
				
				</tr>

				</c:forEach>
			</table>			
		</div>
				
				
		<div class="bottombox">
		<div class="topbox">
		 	<h1>揪團紀錄</h1>
		</div>
		
		<div class="midbox">
			<table class="grouplist">
				<tr>
					<th>團主</th>
					<th>揪團名稱</th>
					<th>團員人數</th>
					<th>主要路線</th>
					<th>申請入園日期</th>
					<th>離開園區日期</th>
					<th>成團狀態</th>
					<th>詳細資訊</th>
				</tr>
					
				<c:forEach var="groupVO" items="${list2}" >		
				
				<tr>
				    <td>${mSvc.getOneMember(groupVO.gp_leader).m_name}</td>
					<td>${groupVO.gp_name}</td>
					<td>${memberinfoSvc.countByGroupId(groupVO.group_id)}/${groupVO.gp_nbp}</td>
					<td>${groupVO.target_loca}</td>
					<td>${groupVO.first_day}</td>
<%-- 					<td>${groupVO.end_day}</td>  --%>
					<td>${status[groupVO.gp_status]}</td>

					<td>
						<img src="<%= application.getContextPath()%>/assets/images/Icon/database.png" class="pic">
							<form ACTION="<%= request.getContextPath()%>/group/group.do" id="form1" METHOD="POST">
								<input type="hidden" name="group_id" value="${groupVO.group_id}">
								<input type="hidden" name="action" value="groupUphistory">	
							</form>
					</td>
				</tr>
			
				</c:forEach>
			</table>			
		</div>
		
		</div>
		
		
         <script>
			$(".pic").click(function(){
				$(this).next().submit();
				})
		 </script>
       </div>
		
    </div>
  </div>

</div>
		<jsp:include page="/common/footer.jsp" flush="true"/>	
  </div>
</div>


<!-- sideBar JS -->
<!-- navJS -->
<!-- bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
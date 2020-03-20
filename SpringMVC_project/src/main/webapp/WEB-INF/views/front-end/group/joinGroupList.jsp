<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.spring.group.model.*"%>
<%@ page import="com.spring.member.model.*"%>
<%@ page import="com.spring.memberinfo.model.*"%>

<jsp:useBean id="minfoSvc" class="com.spring.memberinfo.model.MemberinfoService"/>
<jsp:useBean id="mSvc" class="com.spring.member.model.MemberService"/>

<%
	GroupService groupSvc = new GroupService();
	List<GroupVO> list = groupSvc.joinGroupGroup(1,3);
	pageContext.setAttribute("list", list);
%>

<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
%>

   
<%
	HashMap<Integer,String> st = new HashMap<Integer,String>();
	st.put(0, "成團");
	st.put(1, "未成團");
	st.put(2, "取消揪團");
	pageContext.setAttribute("status", st); 
%>

<jsp:useBean id="meminfoSvc" class="com.spring.memberinfo.model.MemberinfoService"/>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
  <title>Island Peak Group Up</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- form style -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/assets/stylesheets/joinGroup.css">
<!-- form style -->



<!-- body&footer -->
<link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/assets/stylesheets/body.css">
<!-- member -->
<%-- <link rel="stylesheet" type="text/css" href="<%= application.getContextPath()%>/assets/stylesheets/memberContent.css"> --%>

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
  
  .contentbox{
    margin: 80px auto 20px auto;
  box-sizing: border-box;
  height: 1000px;
  padding: 0px;
    
}
.contentbox>img{
  position: fixed;
  top: 0;
  left: 0; 
  z-index: -1;
  object-fit: cover;
  height: 100%;
  width: 100%;
  
  
  
  
  
  div.tab-center{
    background-color: rgba(150,150,150,0.7);
	border-radius: 8px;
	padding: 20px;
	font-size: 20px;
	font-weight: 0;
	height: 1000px;
	text-align:left;
	width:auto;

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
		
		
	<jsp:include page="/WEB-INF/common/signup.jsp" flush="true"/>


<div class="container-fluid">
  <div class="row">
	
	<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true"/>

<div class="col-lg-11 contentbox" >
<img src="<%= application.getContextPath()%>/assets/images/snowpeak/forest-1743206.jpg">
  

       <div class="tab-center">
	
		<div class="topbox">
		 <h3>揪團列表</h3>	
		</div>
		
		
		<!--Navbar-->
<nav class="navbar navbar-expand-lg navbar-dark indigo mb-4">

  <!-- Navbar brand -->
  <a class="navbar-brand" href="<%= request.getContextPath()%>/front-end/group/groupMember/groupUp.jsp">
  <button type="button" class="btn btn-info btn-lg">
  創 立 揪 團
</button></a>

  <!-- Collapsible content -->
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  
	<!-- 查詢  -->
    <form class="form-inline ml-auto">
      <div class="md-form my-0">
        <input class="form-control" type="text" placeholder="Search" aria-label="Search">
      </div>
      <button href="#!" class="btn btn-success my-0 ml-sm-2 btn-lg" type="submit">Search</button>
    </form>
	<!-- 查詢  -->
	
  </div>
  <!-- Collapsible content -->

</nav>
<!--/.Navbar-->
		<!-- Button trigger modal -->

		
		
		<div class="midbox">
			<table class="grouplist">
				<tr>
					<th>團主</th>
					<th>揪團名稱</th>
					<th>團員人數</th>
					<th>主要路線</th>
					<th>申請入園日期</th>
					<th>成團狀態</th>
					<th>詳細資訊</th>
					<th>加入揪團</th>
				</tr>
				
				<%@ include file="page/page1.file"%>
				<c:forEach var="groupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						
				<tr>
					<td>${mSvc.getOneMember(groupVO.gp_leader).m_name}</td>
					<td>${groupVO.gp_name}</td>
					<td>${meminfoSvc.countByGroupId(groupVO.group_id)}/${groupVO.gp_nbp}</td> <!-- Magic Don't toutch -->
					<td>${groupVO.target_loca}</td> 
					<td>${groupVO.first_day}</td>
					<td>${status[groupVO.gp_status]}</td>
						
					<td>
						<img src="<%= application.getContextPath()%>/assets/images/Icon/change.png" class="pic">
							<form ACTION="<%= request.getContextPath()%>/group/group.do" id="form1" METHOD="POST">
								<input type="hidden" name="group_id" value="${groupVO.group_id}">
								<input type="hidden" name="action" value="memberdetailed">	
							</form>
					</td>
					
					<td>
						<c:if test="${minfoSvc.getOneMemberinfo(groupVO.group_id,memberVO.member_id) == null}">
						<img src="<%= application.getContextPath()%>/assets/images/Icon/plus.png" class="pic">
							<form ACTION="<%= request.getContextPath()%>/member/memberinfo.do" id="form1" METHOD="POST">				
								<input type="hidden" name="group_id" value="${groupVO.group_id}">
								<input type="hidden" name="member_id" value="${memberVO.member_id}">
								<input type="hidden" name="action" value="findByMemberId" class="member">	
							</form>
						</c:if>
					</td>
                                  
				</tr>
					
				</c:forEach>
			</table>
			 <%@ include file="page/page2.file" %>								
		</div>
         <script>
         $(".pic").click(function(){
				$(this).next().submit();
				})			
		 </script>
		 <script>
		 $(".pic").click(function(){
				$(this).hide();
				})
		</script>
       </div>
		

  </div>

</div>
		<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>	
  </div>
</div>


<!-- sideBar JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/memberSideBar.js"></script>
<!-- navJS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>
<!-- bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@page import="com.spring.application.model.*"%>
<%@page import="com.spring.member.model.*"%>
<%@page import="com.spring.group.model.*"%>
<%@page import="com.spring.info.model.*"%>
<%@page import="com.spring.group.model.*"%>
<%@page import="com.spring.memberinfo.model.*"%>
<%@page import="com.spring.meal.model.*"%>
<%@page import="com.spring.location.model.*"%>
<%@page import="java.util.*"%>
<%
	//申請單
	ApplicationService appSvc = new ApplicationService();
	ApplicationVO appVO = (ApplicationVO) request.getAttribute("appVO");	
	List<ApplicationVO> list =appSvc.getStatus_List(0);
	pageContext.setAttribute("list", list);
	
	//路線
	InfoService infoSvc = new InfoService();
	pageContext.setAttribute("infoSvc", infoSvc);
	
	
	//會員
	MemberService mbrSvc = new MemberService();
	MemberVO mbrVO = (MemberVO)mbrSvc.getOneMember("A001");
	session.setAttribute("mbrVO", mbrVO );
	pageContext.setAttribute("mbrSvc", mbrSvc);
	
	//揪團單組成員
	MemberinfoService mebinfoSvc = new MemberinfoService();
	session.setAttribute("mebinfoSvc",mebinfoSvc);
	

	//揪團
	GroupService groupSvc = new GroupService();
	GroupVO groupVO = (GroupVO) request.getAttribute("groupVO");
	pageContext.setAttribute("groupSvc",groupSvc);
	//套餐
	MealService mealSvc = new MealService();
	session.setAttribute("mealSvc",mealSvc);
	//住宿
	LocationService locationSvc = new LocationService();
	session.setAttribute("locationSvc",locationSvc);
	
%>




<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>Background</title>
<meta charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css' rel='stylesheet'></link>
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/APP_BACK.css">
	<link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/backgroundContext.css">
<style type="text/css">
/*本地字體引入*/
@font-face {
	font-family: Russo;
	src: url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
	unicode-range: U+00-024F;
}

@font-face {
	font-family: Noto;
	src: url('/DA102G1/assets/fonts/NotoSansTC-Medium.otf');
	unicode-range: U+4E00-9FFF;
}

.app_back_form div {
	display: inline-flex;
	width: 900px;
	margin: 0px;
	padding: 0px;
	font-size: 18px;
	align-items: center;
}

.app_back_form ul {
	list-style: none;
	padding: 0px;
	font-size: 0px;
	width: 900px;
	margin-left: 180px;
	filter:opacity(80%);
}

.app_back_form li:nth-child(1) {
	background: gray;
}

.app_back_form li:nth-child(2) {
	background: dimgray;
	height:60px;
}

.app_back_form li:nth-child(3) {
	background: lightgray;
}

.app_back_form li {
	margin: 0px;
	padding: 0px;
	font-size: 0px;
	width: 100%;
}

.app_back_form button {
	width: 80px;
	height: 40px;

}

.p_name {
	position: relative;
	transform: translateY(20%);
	height: 80px;
}

.p_name p {
	height: 50px;
	font-size: 30px;
	padding: 0px;
	position: relative;
	transform: translateY(-30%);
	margin: 0px;
}

.p_name p:first-child {
	margin: 0px 23px 0px 102px;

}

.app_status span {
	position: relative;
	transform: translateX(300px);
	margin: 0px;
	padding: 0px;
	color: brown;
}

.app_num p:nth-child(3) {
	margin-left: 40px;
}

.app_date p:nth-child(1) {
	margin-left: 30px;
}

.app_num p, .app_date p {
	position: relative;
	transform: translateY(30%);
	margin-left: 10px;
}

fieldset {
	padding: 0;
	border-top: 6px double #f3c682;

	/*  background:rgba(230,240,230,0.5);*/
}

legend {
	color: #d6cc7a;
	font-weight: bold;
	width: 250px;
}

.app_news {
	position: relative;
	transform: translateX(5%);
	margin: 0px;
	padding: 0px;
}

hr {
    border: 0px;
    height: 2px;
    background: #333;
    background-image: linear-gradient(to right, #ccc, #333, #ccc);
   	margin-left: 180px;
}



ul:hover{
	box-shadow: 1px 3px 3px 1px rgba(234,198,130,.5);
    transform : translateX(8px);
    filter:opacity(100%);
    transition:all .4s;
}


.app_date{
    width: 290px;

}


th, td {
 	width: 10%; 
	padding: 10px 0px;
}

th {
	color: brown;
	background: lightgray;
}

tr {
	border-top: 4px solid #fff;
	background: dimgray;
}

.test_table{
	display:none;
    font-size: 18px;
    position: fixed;
    margin: auto;
    top: 103px;
    left: 228px;
    z-index: 100;
    transition: margin 2s;
    width: 76%;
}


.out_lightbox{
	position: fixed;
	width:100%;
	height:100%;
	background-color: black;
	z-index:10;
	opacity:0.4;
	display:none;	
}

.btn_pass{
    border: none;
    margin-left: 15px;
    background:floralwhite;
    padding: 6px;
    border-radius: 10px;
    box-shadow: 2px 4px 4px 2px;
}

.fa-warning_name{
	color: yellow;
    padding-right: 16px;
    margin-left: -46px;
}

.fa-warning{
	color: yellow;
}

.td_1{
	width:67px;
}

.td_2{
	width:114px;
}

</style>



</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="out_lightbox"></div>
			<jsp:include page="/WEB-INF/common/adminNavbar.jsp" flush="true" />

			<div class="col-lg-12 contentbox">
				<img src="<%=application.getContextPath()%>/assets/images/snowpeak/forest-1743206.jpg">

				<jsp:include page="/WEB-INF/common/adminSideBar.jsp" flush="true" />


				<div class="col-lg-10 col-md-10 cb rightContent">

					<div class="col-lg-12 cb">

				<fieldset>
					<legend>APPLICATION</legend>
					<%@ include file="change_Page1.file" %>
					<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
							
							<div>
					<a href="<%=application.getContextPath()%>/back-end/application/Application_back_checkpass.jsp"><button class="btn_pass">已審核</button></a>
					<a href="<%=application.getContextPath()%>/back-end/application/Application_back_waitPass.jsp"><button class="btn_pass">已審未過</button></a>
				</div>
						<div class="row">
						<c:forEach  var="appVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<form  class="app_back_form" action="<%=application.getContextPath() %>/app/app.do" method="POST" >
									<ul>
										
										<li>
											<div class="col-8 app_num" >
							
												<p>種類:</p>
												<p>
												<c:if test="${appVO.group_id==null}">個人申請</c:if>
												<c:if test="${appVO.group_id!=null}">揪團申請</c:if>
												</p>
												
												<p>
												<c:if test="${appVO.group_id==null}">會員編號:</c:if>
												<c:if test="${appVO.group_id!=null}">揪團編號:</c:if>
												
												</p>
												
												
												<c:if test="${appVO.group_id==null}">
												<p>${appVO.member_id}</p>
												</c:if>
												<c:if test="${appVO.group_id!=null}">
												<p>${appVO.group_id}</p>
												</c:if>
												<c:if test="${appVO.group_id!=null}">
												<p>團名: ${groupSvc.getOneGroup(appVO.group_id).gp_name}</p>
												</c:if>
											</div>
											<div class="col-4 app_date">
												<p>日期:</p>
												<p><fmt:formatDate value="${appVO.app_time}"  pattern="yyyy-MM-dd HH:mm:ss"/></p>
											</div>
										</li>
										<li>
										<c:if test="${infoSvc.getOneInfo(appVO.route_id).open_Status == 1}">
											<div class="col-8 p_name">
												<p>Member name:</p>
												<p>${mbrSvc.getOneMember(appVO.member_id).m_name}</p>	
											</div>
										</c:if>
										<c:if test="${infoSvc.getOneInfo(appVO.route_id).open_Status == 0}">
											<div class="col-8 p_name" >
												<p><i class="fa fa-warning fa-warning_name"></i>Member name:</p>
												<p style="color:red">${mbrSvc.getOneMember(appVO.member_id).m_name}</p>	
											</div>
										</c:if>
											<div class="col-4">
												<div class="col-6">
												<c:if test="${appVO.group_id==null}">
												<button type="submit" class="btn btn-outline-secondary" name="action" value="pass_alone">通過</button>
												<input type="hidden" name="app_num" value="${appVO.app_num}" />
												</c:if>
												<c:if test="${appVO.group_id!=null}">
														<button type="submit" class="btn btn-outline-secondary" name="action" value="pass">通過</button>
														<input type="hidden" name="group_id" value="${appVO.group_id}" />
														<input type="hidden" name="member_id" value="${appVO.member_id}" />
														<input type="hidden" name="first_day" value="${appVO.first_day}" />
														<input type="hidden"  name="dailybed_provided" value="${appVO.dailybed_provided}" />
														<input type="hidden"  name="meal_provided" value="${appVO.meal_provided}" />
														<input type="hidden" name="app_days" value="${appVO.app_days}" />
														<input type="hidden" name="meal_price" value="${mealSvc.getOneMeal('M001').meal_price}" />
														<input type="hidden" name="bed_price" value="${locationSvc.getOneLocation('A0001').bed_price}" />
														<input type="hidden" name="locations" value="${appVO.locations}" />
														<input type="hidden" name="app_num" value="${appVO.app_num}" />
												</c:if>
												
												</div>
												<div class="col-6">
												
													<button type="submit" class="btn btn-outline-secondary" name="action" value="fail">不通過</button>
													<input type="hidden" name="m_email" value="${mbrVO.m_email}" />
													
												</div>
											</div>
										</li>
										<li>
											<div class="col-6 app_news">
												<a href="#" class="news">詳細資訊</a>
												<!--分辨個人申請還是揪團申請，在取得id，來標記，因為後面打開燈箱用id去辨識 -->
												<c:if test="${appVO.group_id==null}">
												<input type="hidden"  value="${appVO.app_num}" />
												</c:if>
												<c:if test="${appVO.group_id!=null}">
												<input type="hidden"  value="${appVO.app_num}" />
												</c:if>
												
												
												
											</div>
											<div class="col-6 app_status">
												<span>目前狀態:</span>
												<span>
												<c:if test="${appVO.app_status==0}">未審核</c:if>
												<c:if test="${appVO.app_status==1}">已審核</c:if>
												<c:if test="${appVO.app_status==2}">已審未過</c:if>
												</span>
												<hr class="style-one" />
											</div>
									
										</li>
									</ul>
							<c:if test="${appVO.group_id==null}">
							<table class="test_table"  id='${appVO.app_num}'>	
							    <tr>
							      <th class="td_1">姓名</th><th class="td_2">e-mail</th><th class="td_1">緊急連絡人</th><th class="td_2">緊急連絡人電話</th><th class="td_2">衛星電話</th><th class="td_1">無線電話</th><th class="td_1">申請天數</th><th class="td_1">起始日期</th><th class="td_2">申請路線</th>
							    </tr>
						<!--路線不通過會在詳細資料顯示紅色 -->
						<c:if test="${infoSvc.getOneInfo(appVO.route_id).open_Status == 1}">	    
							    <tr>
							      <td>${mbrSvc.getOneMember(appVO.member_id).m_name}</td><td>${mbrSvc.getOneMember(appVO.member_id).m_email}</td><td>${appVO.egc_contact}</td><td>${appVO.egc_phone}</td><td>${appVO.satellite}</td><td>${appVO.radio}</td><td>${appVO.app_days}</td><td>${appVO.first_day}</td><td class="td_2">${infoSvc.getOneInfo(appVO.route_id).route_Name}</td>
							    </tr>
						</c:if>	
						<c:if test="${infoSvc.getOneInfo(appVO.route_id).open_Status == 0}">	   					    
							     <tr>
							      <td>${mbrSvc.getOneMember(appVO.member_id).m_name}</td><td>${mbrSvc.getOneMember(appVO.member_id).m_email}</td><td>${appVO.egc_contact}</td><td>${appVO.egc_phone}</td><td>${appVO.satellite}</td><td>${appVO.radio}</td><td>${appVO.app_days}</td><td>${appVO.first_day}</td><td style="color:red"><i class="fa fa-warning" class="td_2"></i>${infoSvc.getOneInfo(appVO.route_id).route_Name}</td>
							    </tr>
						</c:if>	   
				</c:if>		   
		
						<!--揪團的下方表格 -->
						<c:if test="${appVO.group_id!=null}">
					<table class="test_table"  id='${appVO.app_num}'>	
						<tr>
							<th class="td_1">姓名</th><th class="td_2">e-mail</th><th class="td_1">緊急連絡人</th><th class="td_1">緊急連絡人電話</th><th class="td_2">住址</th><th class="td_1">申請天數</th><th class="td_2">日期</th><th class="td_1">團員手機</th><th class="td_1">申請路線</th>
					    </tr>
							    <c:forEach  var="mebinfoVO" items="${mebinfoSvc.find_GroupName(appVO.group_id)}">
								    <c:if test="${infoSvc.getOneInfo(appVO.route_id).open_Status == 0}">
									     <tr>
									     <td class="td_1">${mebinfoVO.m_name}</td><td class="td_2">${mebinfoVO.m_email}</td><td class="td_1">${mebinfoVO.egc_contact}</td><td class="td_1">${mebinfoVO.egc_phone}</td><td class="td_2">${mebinfoVO.address}</td><td class="td_1">${appVO.app_days}</td><td class="td_2">${appVO.first_day}</td><td class="td_1">${mebinfoVO.cellphone}</td><td class="td_2" style="color:red"><i class="fa fa-warning"></i>${infoSvc.getOneInfo(appVO.route_id).route_Name}</td>
								    	</tr>
								    </c:if>
								     <c:if test="${infoSvc.getOneInfo(appVO.route_id).open_Status == 1}">
									     <tr>
									     <td>${mebinfoVO.m_name}</td><td>${mebinfoVO.m_email}</td><td>${mebinfoVO.egc_contact}</td><td>${mebinfoVO.egc_phone}</td><td>${mebinfoVO.address}</td><td>${appVO.app_days}</td><td>${appVO.first_day}</td><td>${mebinfoVO.cellphone}</td><td>${infoSvc.getOneInfo(appVO.route_id).route_Name}</td>
								    	</tr>
								     </c:if>
							    </c:forEach>
						</c:if>			 
 
						 
							</table>
			
									<hr class="style-one" />
							</form>
							

					
							</c:forEach>
						</div>
					
					<%@ include file="change_Page2.file" %>
			</fieldset>


					</div>

				</div>

			</div>

			<jsp:include page="/WEB-INF/common/footer.jsp" flush="true" />
		</div>
	</div>

	<script type="text/javascript">
	var news_value;
	  $(".news").click(function(){
		  var news_value = $(this).next().val();
		  var news_value1="#"+news_value;
		  $(news_value1).show(1000);
		  $(".out_lightbox").show(1000);
	  })
	  
		$(".out_lightbox").click(function(){
			  var news_value = $(this).next().val();
			  $(".test_table").hide(10);
			  $(".out_lightbox").hide(10);
		  })

	//只通過一次
// 	$('.btn').on('click',function(){
// 		var which = $('.btn').index(this);
// 		alert(which);
// 		$('.btn:eq(' + which + ')').prop("disabled", true);
// 	});
	</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
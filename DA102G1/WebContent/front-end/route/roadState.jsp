<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.info.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="java.util.*"%>

<%
	InfoVO infoVO = (InfoVO) request.getAttribute("infoVO");
	InfoService infoSvc = new InfoService();
	List<InfoVO> list = infoSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>園區資訊</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- body -->
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/assets/stylesheets/ROUTE.css">


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

  /* content start*/

.contentbox{
  margin-top: 80px;
  box-sizing: border-box;
  background-image: url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg);
  background-size: cover;

  height: auto;
  padding: 0px;
  

}
.contentbox .cb{/*contentbox 底下的div*/
  display: inline-block;
  position: relative;
}

/*centerbox start*/
#centerbox{
width: 100%;
background-color: rgba(52,53,55,0.7);
border: 1px solid rgba(150,150,150,0.5);
height: auto;
  min-height:850px;
}
#centerbox div{
/*   margin-top: 15px; */
/*   border: 2px solid red; */
}
/*centerbox end*/


  /* content end*/
  
  


#info_top_add {
	text-align: center;
 	min-width: 800px; 
	margin: 80px auto;
}

.add_input {
 	width: 50px; 
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

.time {
	width: 10px;
	padding: 0px
}

.info_td {
	width: 60px;
	
}

.info_td input {
	width: 100px;
}

.time input {
	width: 180px;
	margin: 0px;
	padding: 0px;
}

.app_table {
	display: none;
	padding: 0px;
	margin: 200px;
}

.info_td2{
	width:150px;
}
	</style>

</head>
<body>
	
	<jsp:include page="/common/signup.jsp" flush="true"/>

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/common/navbar.jsp" flush="true"/>
			
			<div class="col-lg-12 contentbox" >		  
				<div id="centerbox" class="container-fluid m-auto">
					<div class="col-lg-12 cb">
						<table id="info_top_add">

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
								<th class="info_td">路線編號</th>
								<th class="info_td">路線名稱</th>
								<th class="time">開放時間</th>
								<th class="info_td">開放狀態</th>
							</tr>

							<c:forEach var="infoVO" items="${list}">

								<tr id="talbe_info">
									<td class="info_td">${infoVO.route_ID}</td>
									<c:set scope="request" var="route_ID">
									</c:set>
									<td class="info_td">${infoVO.route_Name}</td>
									<td class="time">${infoVO.open_Time}</td>
									<td class="status"><c:if test="${infoVO.open_Status==0}">不開放</c:if>
										<c:if test="${infoVO.open_Status==1}">開放</c:if></td>

								</tr>
							</c:forEach>
						</table>



						<form METHOD="get" ACTION="<%=request.getContextPath()%>/info/info.do" name="form">
							<table class="app_table">
								<tr>
									<th class="info_td">路線編號</th>
									<th class="info_td">路線名稱</th>
									<th class="time">開放時間</th>
									<th class="info_td">開放狀態</th>
								</tr>
								<tr>
									<td class="info_td"><input type="text" name="route_ID" id="route_id" ></td>
									<td class="info_td"><input type="text" name="route_Name"></td>
									<td class="time"><input name="open_Time" id="f_date1" type="text" ></td>
									<td class="info_td2"><input type="radio" name="open_Status" value="0">不開放<input type="radio" name="open_Status" value="1">開放
									</td>
								</tr>
							</table>
							<input type="hidden" name="action" value="insert">
							<a href="<%= application.getContextPath() %>/front-end/welcome/index.jsp">
								<button type="button" class="btn btn-info">回 首 頁</button>
							</a>
							
						</form>


					</div>
				</div>
			</div>
<%-- 		  	<jsp:include page="/common/footer.jsp" flush="true"/> --%>
		</div>
	</div>

<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
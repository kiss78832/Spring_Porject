<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.equipment.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.rentodlist.model.*"%>
<%
	RentOdListService rolSvc = new RentOdListService();
	List<RentOdListVO> list = rolSvc.getAll();
	pageContext.setAttribute("list", list);

	HashMap<String, String> hm = new HashMap<String, String>();
	hm.put("0","訂單成立");
	hm.put("1","取消訂單");
	hm.put("2","出租中");
	hm.put("3","未歸還");
	hm.put("4","已歸還 訂單結束");

	application.setAttribute("hm", hm);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>共用頁面</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- body&footer -->
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

/* content start*/
.contentbox {
	margin-top: 80px;
	box-sizing: border-box;
/* 	background-image: */
/* 		url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg); */
	background-size: cover;
/* 	height: 1250px; */
	padding: 0px;
}

.contentbox .cb { /*contentbox 底下的div*/
	display: inline-block;
	position: relative;
}
/*左側區塊*/
#cleft {
	background-color: rgba(52, 53, 55, 0.2);
	border: 1px solid rgba(150, 150, 150, 0.5);
/* 	height: 1250px; */
	float: left;
}

#cleft div {
/* 	border: 2px solid red; */
	margin: 15px auto;
}

/*右側區塊*/
#cright {
	background-color: rgba(72, 73, 75, 0.9);
	border: 1px solid rgba(150, 150, 150, 1);
	height: 100%;
	float: left;
}

#cright div {
	border: 2px;
	margin: 15px auto;
}
/* content end*/
@media screen and (max-width : 480px) {
	body {
		font-size: 1.5rem;
	}
}

table {
	display: flex;
	flex-flow: column nowrap;
	line-height: 1.25;
	border: 0;
}

caption {
	padding: 1rem;
	font-size: 1.5rem;
	font-weight: 900;
	color: #999;
	caption-side: top;
}

caption span {
	font-size: 1.25rem;
	font-weight: 300;
	color: #A52A2A;
}

tr {
	display: flex;
}

tr:nth-of-type(odd) {
	background-color: #E5E5E5;
}

th {
	font-weight: 700;
	background-color: #f2f2f2;
	color: #666;
}

th, td {
	display: flex;
	flex: 1 0;
	align-items: center;
	justify-content: center;
	padding: 0.5rem 0.1rem;
	border: 1px solid #BFBFBF;
	border-collapse: collapse;
	border-spacing: 0;
}

/* This will affect only Chrome browsers 29+
	* See: http://browserhacks.com/
	* Hack because 'hyphens: auto;' is supported in Chrome only on Android & Mac 
	* and word-break 'overrides' hyphens in Firefox
	*/
.selector:not (*:root ), th, td {
	word-wrap: break-word;
	overflow-wrap: break-word;
	word-break: break-all;
}

/* Breaking the numbers! */
.numbers {
	word-break: break-word;
}
</style>

</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/common/navbar.jsp" flush="true" />

			<div class="col-lg-12 contentbox">
<img src="<%= application.getContextPath() %>/assets/images/snowpeak/forest-1743206.jpg">

<jsp:include page="/common/adminSideBar.jsp" flush="true"/>
				<div id="cright" class="col-lg-10 col-md-10 cb">

					<div class="cb col-lg-12">你要管理的內容（已置中,寬度內距已調）</div>




					<table>
						<tr>
							<th>租借訂單編號</th>
							<th>會員編號</th>
							<th>姓名</th>
							<th>電話</th>
							<th>訂單產生時間</th>
							<th>預約出租日期</th>
							<th>實際出租日期</th>
							<th>預計歸還日期</th>
							<th>實際歸還日期</th>
							<th>訂單狀態</th>
							<th>付款模式</th>
							<th>總金額</th>
							<th>操作</th>
							<%@ include file="/back-end/product/pages/page1.file"%>
							<c:forEach var="rolVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
						</tr>
						<tr>
							<td>${rolVO.rent_odnum}</td>
							<td>${rolVO.member_id}</td>
							<td>${rolVO.rent_name}</td>
							<td>${rolVO.rent_phone}</td>
							<td>${rolVO.odlist_createdate}</td>
							<td>${rolVO.rsved_rent_date}</td>
							<td>${rolVO.real_rent_date}</td>
							<td>${rolVO.ex_return_date}</td>
							<td>${rolVO.real_return_date}</td>
							<td><c:if test="${rolVO.od_status == 0}">
								${hm['0']}					
								</c:if> <c:if test="${rolVO.od_status == 1}">
								${hm['1']}
								</c:if> <c:if test="${rolVO.od_status == 2}">
								${hm['2']}
								</c:if> <c:if test="${rolVO.od_status == 3}">
								${hm['3']}
								</c:if></td>
							<td>${rolVO.rent_payment}</td>
							<td>${rolVO.od_total_price}</td>
							<td><FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/rentodlistbackend/rentodlistbackend.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="rent_odnum" value="${rolVO.rent_odnum}"> <input
										type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>"> <input
										type="hidden" name="whichPage" value="<%=whichPage%>">
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/rentodlistbackend/rentodlistbackend.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="查詢明細" class="btn1"> <input
										type="hidden" name="rent_odnum" value="${rolVO.rent_odnum}">
									<input type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>"> <input
										type="hidden" name="whichPage" value="<%=whichPage%>">
									<input type="hidden" name="action" value="getOne_For_Display">
								</FORM></td>
						</tr>
						</c:forEach>
					</table>
					<%@ include file="/back-end/product/pages/page2.file"%>














				</div>

			</div>


			<jsp:include page="/common/footer.jsp" flush="true" />


		</div>
	</div>


	<!-- JS -->
	<script type="text/javascript"
		src="<%=application.getContextPath()%>/assets/javascripts/index.js"></script>
	<script type="text/javascript"
		src="<%=application.getContextPath()%>/assets/javascripts/sideBar.js"></script>

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
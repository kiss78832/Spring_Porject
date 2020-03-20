<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=" java.util.* "%>
<%@ page import="com.spring.dailyBed.model.*"%>
<%@ page import="com.spring.location.model.*"%>
<!DOCTYPE html>
<%
	DailyBedService dailyBedSvc = new DailyBedService();

	LocationService locationSvc = new LocationService();
	List<LocationVO> locList = locationSvc.getAll();
	pageContext.setAttribute("locList", locList);
%>


<html>

<head>
<link rel="icon" type="image/x-icon"
	href="https://i.imgur.com/J0aPP1N.png" />
<title>Island Peak</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>




<link rel="stylesheet"
	href="<%=application.getContextPath()%>/assets/stylesheets/Reserved_Jquery.css">

<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>

<style type="text/css">
/*本地字體引入*/
@font-face {
	font-family: Russo;
	src: url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
	unicode-range: U+00-024F;
}

@font-face {
	font-family: Noto;
	src: url('/DA102G1/assets/fonts/NotoSansTC-Regular.otf');
	unicode-range: U+4E00-9FFF;
}

/* content start*/
.contentbox {
	margin-top: 80px;
	box-sizing: border-box;
	background-image:
		url(${pageContext.request.contextPath}/assets/images/snowpeak/forest-1743206.jpg);
	background-size: cover;
	height: auto;
	padding: 0px;
}

.contentbox .cb {
	/*contentbox 底下的div*/
	display: inline-block;
	position: relative;
}

/*左側區塊*/
#cleft {
	background-color: rgba(52, 53, 55, 0.2);
	border: 1px solid rgba(150, 150, 150, 0.5);
	height: auto;
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
	/* 	border: 2px solid red; */
	margin: 15px auto;
}

.btn {
	background-color: rgba(0, 0, 0, 0.5);
	border: 1px solid white;
	padding: 10px;
	min-width: 145px;
	margin: 15px auto;
	color: white;
	font-size: 20px;
	font-weight: 300;
	transition: 0.2s;
}

.btn:hover {
	background-color: rgba(230, 230, 230, 0.8);
	border: 1px solid black;
}

.content {
	padding: 0 18px;
	display: none;
	overflow: hidden;
	background-color: rgba(0, 0, 0, .5);
}

.collapsible {
	background-color: rgba(0, 0, 0, 0.4);
	border: 1px solid white;
	color: white;
	font-size: 20px;
	font-weight: 300;
	padding: 18px;
	width: 100%;
	text-align: center;
	outline: none;
	margin-top: 15px;
}

.collapsible:hover {
	color: red;
	outline: none;
}

/* content end*/
</style>




</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true" />

			<div class="col-lg-12 contentbox">


				<div id="cleft" class="col-2 cb">
					<!-- 左側區塊 可放按鈕等等 -->


					<button class="collapsible">瀏覽食宿資訊</button>
					<div class="content">
						<button class="btn"
						onclick="location.href='<%=request.getContextPath()%>/front-end/dailyBed/Reserved_Query.jsp'">床位查詢</button>
						<br>
						<button class="btn"
							onclick="location.href='<%=request.getContextPath()%>/front-end/meal/Meal_Query.jsp'">套餐</button>
						<br>
					</div>
					<button class="collapsible"
						onclick="location.href='<%=request.getContextPath()%>/front-end/room_order/Reserved_Bed.jsp'">預訂床位</button>

				</div>
				<div id="cright" class="col-10 cb">
					<div class="col-lg-12">
						<div class="years_and_months col-lg-12">
							<table>
								<tr>
									<th class="head" id="down"><span> &lt;&lt; </span></th>
									<th class="head"></th>
									<th class="head" id="year"></th>
									<th class="head">年</th>
									<th class="head">/</th>
									<th class="head" id="month"></th>
									<th class="head">月</th>
									<th class="head" id="up"><span>>></span></th>
								</tr>
							</table>

							<%--hover and show pic --%>
							<%
								LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
							%>
							<div class="location_name">
								<c:if test="${empty locationVO }">
								請選擇據點
							</c:if>
								${locationVO.location_name} <input type="hidden"
									id="location_idSelect" value="${locationVO.location_id}">
								<div class="showLocPic">
									<img
										src="<%=request.getContextPath()%>/getLocationPic?location_id=${locationVO.location_id}">
								</div>
							</div>
							<div class="col-3" style="border: 0px">
								<div class="formBox">

									
									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/dailyBed.html"
										style="color: black;" id="locStatusSelect">
										<input type="hidden" name="action" value="locStatusSelect">
										<select size="1" name="location_status" class="form-control1"
											id="location_status">
											<option disabled="disabled" selected>請選擇據點類型
											<option value="1">須審核
											<option value="2">不須審核
										</select>
									</form>
									
									

									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/dailyBed.html"
										style="color: black;" id="locSelect">
										<input type="hidden" name="action" value="locSelect">
										<select size="1" name="location_id" class="form-control1"
											id="location_id">
											<option disabled="disabled" selected>請選擇據點
												<c:forEach var="locationVO" items="${locList}">
													<option value="${locationVO.location_id}">${locationVO.location_name}
												</c:forEach>
										</select>
									</form>


									<form class="monthChange">
										<fieldset>
											<select id="monthChange">
												<option value="" disabled selected>請選擇月份</option>
												<option value="0">1</option>
												<option value="1">2</option>
												<option value="2">3</option>
												<option value="3">4</option>
												<option value="4">5</option>
												<option value="5">6</option>
												<option value="6">7</option>
												<option value="7">8</option>
												<option value="8">9</option>
												<option value="9">10</option>
												<option value="10">11</option>
												<option value="11">12</option>
											</select>
										</fieldset>
									</form>
								</div>
							</div>
						</div>

						<div class="col-lg-12 row">

							<div class="col-13" style="border: 0px">
								<table class="days">
									<tr>
										<th>日</th>
										<th>一</th>
										<th>二</th>
										<th>三</th>
										<th>四</th>
										<th>五</th>
										<th>六</th>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</div>

						</div>

					</div>
				</div>

			</div>


			<jsp:include page="/WEB-INF/common/footer.jsp" flush="true" />
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {

			$(".collapsible").click(function() {
				$(this).next().animate({
					height : 'toggle'
				});
			});
		});
	</script>

	<script
		src="<%=application.getContextPath()%>/assets/javascripts/Reserved_Jquery.js"></script>

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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=" java.util.* "%>
<%@ page import="com.spring.order_detail.model.*"%>
<%@ page import="com.spring.location.model.*"%>
<%@ page import="com.spring.meal.model.*"%>
<!DOCTYPE html>

<%
	List<Order_DetailVO> orderDetailCheck = (List<Order_DetailVO>) request.getAttribute("orderDetailCheck");
	session.setAttribute("orderDetailCheck", orderDetailCheck);
	
	Integer total_price = 0;
	for (Order_DetailVO order_DetailVO : orderDetailCheck) {
		total_price += (order_DetailVO.getBedTotal_price() + order_DetailVO.getMealTotal_price());
	}
	pageContext.setAttribute("total_price",total_price);
%>

<jsp:useBean id="locSvc" class="com.spring.location.model.LocationService" />
<jsp:useBean id="mealSvc" class="com.spring.meal.model.MealService" />

<html>
<head>
<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
  <title>Island Peak</title>
  <meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- ========== -->

<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.css">

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>




<!-- right side css -->
<link rel="stylesheet" type="text/css"
	href="<%= application.getContextPath() %>/assets/stylesheets/Order_Detail.css">

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
	height: 1250px;
	float: left;
}

#cleft div {
	border: 2px solid red;
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


/*右側區塊*/
#cright {
	background-color: rgba(72, 73, 75, 0.9);
	border: 1px solid rgba(150, 150, 150, 1);
	height: 100%;
	float: left;
}

#cright div {
	/*border: 2px solid red;*/
	margin: 5px auto 5px auto;
}

/* content end*/
</style>

</head>

<body>

	<div class="container-fluid">
		<div class="row">
<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true"/>

			<div class="col-lg-12 contentbox">


				<div id="cleft" class="col-lg-2 col-md-2 cb">
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
				<div id="cright" class="col-lg-10 col-md-10 cb">
					
					<!-- ==================== -->
					<!-- 右側區塊Table -->
					<!-- ==================== -->
					<div class="cb col-lg-12">
						<!-- 資料顯示 -->
						<div class="row">
							<div class="col-md-12 col-lg-12 col-sm-12">
								<div class="white-box col-md-12 col-lg-12 col-sm-12">

									<div class="col-md-12">
										<table class="table  table-hover table-striped">
											<thead class="thead-light">
												<tr>
													<th>預訂日期</th>
													<th>預訂據點</th>
													<th>預訂床位數</th>
													<th>預訂床位總價錢</th>
													<th>預訂套餐</th>
													<th>預訂套餐份數</th>
													<th>預訂套餐總價錢</th>
												</tr>
											</thead>
											<tbody>

												<c:if test="${not empty errorMsgs}">
													<font style="color: red">請修正以下錯誤:</font>
													<ul>
														<c:forEach var="message" items="${errorMsgs}">
															<li style="color: red; font-size: 20px;">${message}</li>
														</c:forEach>
													</ul>
												</c:if>

												<c:forEach var="orderDetailVO" items="${orderDetailCheck}">
													<tr class="table-secondary">
														<td>${orderDetailVO.checkin_date}</td>
														<td>${locSvc.getOneLocation(orderDetailVO.location_id).location_name}</td>
														<td>${orderDetailVO.bed_num}</td>
														<td class="text-success">${orderDetailVO.bedTotal_price}</td>
														<td><c:choose>
																<c:when test="${orderDetailVO.meal_id eq '0'}">
																	無訂套餐
																</c:when>

																<c:otherwise>
																	${mealSvc.getOneMeal(orderDetailVO.meal_id).meal_name}
																</c:otherwise>

															</c:choose></td>
														<td>${orderDetailVO.meal_quantity}</td>
														<td class="text-success">${orderDetailVO.mealTotal_price}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										
										
										<div class="filterList row">
										<div class="orderBar">
											
										</div>
										<div class="orderBar">
										
											<div>
												<h2 class="line"></h2>
												<br>
												<span class="totalPrice">總金額:   </span> 
												<span class="text-success">${total_price}</span>
												
											</div>
										
											<div class="btnList">
												<form METHOD="post"
													action="<%=request.getContextPath()%>/orderDetail.html"
													id="back">
													<input type="hidden" name="action" value="back">
												</form>
												<div class="btnSet">
													<button id="addBack">重新填寫</button>
												</div>

												<form METHOD="post"
													action="<%=request.getContextPath()%>/roomOrder.html"
													id="insertOrder">
													<input type="hidden" name="action" value="insertOrder">
												</form>
												<div class="btnSet">
												<button id="addNew">送出訂單</button>
												</div>
												
												<div class="loader loader--style3" title="2">
  												<svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
    													 width="40px" height="40px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">
  												<path fill="#000" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
   													 <animateTransform attributeType="xml"
     													 attributeName="transform"
      													 type="rotate"
     													 from="0 25 25"
     												     to="360 25 25"
    													 dur="0.6s"
    													 repeatCount="indefinite"/>
    											</path>
 											    </svg>
												</div>
												
											</div>
										</div>

									</div>			
										
									</div>
								</div>

							</div>
						</div>
						<!-- 資料顯示 -->

					</div>

				</div>

			</div>




<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"/>
		</div>
	</div>

	<script>
		var coll = $(".collapsible");
		var i;

		for (i = 0; i < coll.length; i++) {
			coll[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var content = this.nextElementSibling;
				if (content.style.display === "block") {
					content.style.display = "none";
				} else {
					content.style.display = "block";
				}
			});
		}
	</script>


	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
	

	<script
		src="<%= application.getContextPath() %>/assets/javascripts/orderDetail.js"></script>

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
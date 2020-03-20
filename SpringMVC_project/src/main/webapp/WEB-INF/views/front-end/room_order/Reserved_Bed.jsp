<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=" java.util.* "%>
<%@ page import="com.spring.order_detail.model.*"%>
<%@ page import="com.spring.meal.model.*"%>
<%@ page import="com.spring.location.model.*"%>
<%@ page import="com.spring.dailyBed.model.*"%>
<!DOCTYPE html>

<%
	List<Order_DetailVO> orderDetailCheck = (List<Order_DetailVO>) request.getAttribute("orderDetailCheck");
	pageContext.setAttribute("orderDetailCheck", orderDetailCheck);
%>

<jsp:useBean id="locSvc" class="com.spring.location.model.LocationService" />
<jsp:useBean id="mealSvc" class="com.spring.meal.model.MealService" />
<jsp:useBean id="dailyBedSvc" class="com.spring.dailyBed.model.DailyBedService" />

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
	height: 1250px;
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
	margin: 0.5%;
}

/* content end*/
</style>

<link rel="stylesheet"
	href="<%=application.getContextPath()%>/assets/stylesheets/Reserved_Bed.css">
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true" />

			<div class="col-lg-12 contentbox">


				<div id="cleft" class="col-lg-2 col-md-2 cb">
					<!-- 左側區塊 可放按鈕等等 -->
					<div class="cb col-lg-12">

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

				</div>
				<div id="cright" class="col-lg-10 col-md-10 cb">
					<div class="cb col-lg-12">
						<h2 class="pageTitle">
							<span>預訂床位</span>
						</h2>

						<div id="submit_detail"><img title="確認訂單"  src="<%=request.getContextPath()%>/assets/images/Icon/list2.png"></div>

						<form METHOD="post"
							ACTION="<%=request.getContextPath()%>/orderDetail.html"
							id="submitOrder">
							<input type="hidden" name="action" value="submitOrder">
							<c:choose>

								<c:when test="${not empty orderDetailCheck}">

									<%
										System.out.println("runnnn");
									%>
									<c:forEach var="orderDetailVO" items="${orderDetailCheck}">
										<div class="dateDiv">
											<div>
												<div class="col align-self-start">
													
														<input type="date" class="date col" min="" name="date"
															value="${orderDetailVO.checkin_date}">
												
														<div class="cancel">X</div>	
														
											
												</div>

												<div class="col align-self-center">
											
														<select class="chosen location" name="location">
															<option value="" disabled selected>請選擇據點</option>
															<c:forEach var="locationVO"
																items="${dailyBedSvc.getLocListStillCanProvide(orderDetailVO.checkin_date)}">
																<option value="${locationVO.location_id}">${locationVO.location_name}
															</c:forEach>
														</select>
												
												
														<input type="number" class="chosen bed_num" name="bed_num">
												
														<select class="chosen meal" name="meal_id">
															<option value="0"
																${'0' eq orderDetailVO.meal_id?'selected="selected"' : ''}>套餐</option>
															<c:forEach var="mealVO"
																items="${mealSvc.getMealsByMeal_status(1)}">
																<option value="${mealVO.meal_id}"
																	${mealVO.meal_id eq orderDetailVO.meal_id?'selected="selected"' : ''}>${mealVO.meal_name}
															</c:forEach>
														</select>
													
												
														<select class="chosen meal_num" name="meal_num">
															<option value="0"
																${0 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>份數</option>
															<option value="1"
																${1 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>1</option>
															<option value="2"
																${2 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>2</option>
															<option value="3"
																${3 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>3</option>
															<option value="4"
																${4 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>4</option>
															<option value="5"
																${5 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>5</option>
															<option value="6"
																${6 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>6</option>
															<option value="7"
																${7 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>7</option>
															<option value="8"
																${8 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>8</option>
															<option value="9"
																${9 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>9</option>
															<option value="10"
																${10 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>10</option>
															<option value="11"
																${11 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>11</option>
															<option value="12"
																${12 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>12</option>
															<option value="13"
																${13 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>13</option>
															<option value="14"
																${14 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>14</option>
															<option value="15"
																${15 == orderDetailVO.meal_quantity?'selected="selected"' : ''}>15</option>
														</select>
													
												</div>
												
											</div>
										</div>
									</c:forEach>
								</c:when>



								<c:otherwise>
									<%
										System.out.println("runnn2n22222222");
									%>
									<div class="dateDiv">
										<div class="">
											<div class=" col align-self-center">
											
												<input type="date" class="date col" min="" name="date">
											
											<div class="cancel">
												X
											</div>
										</div>

											<div class=" col align-self-center">
												<select class="chosen location" name="location">
													<option value="" disabled selected>請選擇據點</option>
												</select>
												
												<input type="number" class="chosen bed_num" name="bed_num">
												
												<select class="chosen meal" name="meal_id">
													<option value="0" selected>套餐</option>
													<c:forEach var="mealVO"
														items="${mealSvc.getMealsByMeal_status(1)}">
														<option value="${mealVO.meal_id}">${mealVO.meal_name}
													</c:forEach>
												</select>
												
												<select class="chosen meal_num" name="meal_num">
													<option value="0" selected>份數</option>
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
												</select>
											</div>
																						
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</form>
					</div>
					<footer id="addDateDiv">
						<p>( + )</p>
					</footer>
				</div>

			</div>



		</div>

	</div>


	<%
		String state = (String) request.getAttribute("state");
		pageContext.setAttribute("state", state);
	%>
	<c:if test="${state eq 'success' }">
		<script>
			$(function(){
				Swal.fire(
					      '送出成功',
					      '您的訂單已送出!請至您的信箱查看明細',
					      'success'
					    )
			});
			
			window.setTimeout(function() {
			    window.location.href = '<%=request.getContextPath()%>/front-end/resIndex/ResIndex.jsp';
			}, 3500);
		</script>
	</c:if>

	<c:if test="${state eq 'fail' }">
		<%
			List<String> list = (List<String>) request.getAttribute("errorMsgs");
				StringBuilder sb = new StringBuilder();

				for (String string : list) {
					sb.append(string).append("  ");
				}

				request.setAttribute("error", sb.toString());
		%>

		<%
			System.out.println("fail come");
		%>
		<script>
			$(function(){
				Swal.fire({
					  type: 'error',
					  title: '很抱歉!',
					  text:'<%=(String) request.getAttribute("error")%>'
					  })
			});
			

		</script>
	</c:if>

	<%
		String errorCheckState = (String) request.getAttribute("errorCheckState");
		pageContext.setAttribute("errorCheckState", errorCheckState);
	%>

	<c:if test="${errorCheckState eq 'fail' }">
		<%
			Set<String> list = (Set<String>) request.getAttribute("errorMsgs");
				StringBuilder sb = new StringBuilder();

				for (String string : list) {
					sb.append(string + "  ");
				}

				pageContext.setAttribute("error", sb.toString());
				System.out.println("幹:" + sb.toString());
				request.removeAttribute("errorCheckState");
		%>

		<%
			System.out.println("errorCheckState fail come");
		%>
		<script>
			$(function(){
				Swal.fire({
					  type: 'error',
					  title: '很抱歉!',
					  text:'<%=(String) pageContext.getAttribute("error")%>'
				})
			});
		</script>
	</c:if>






	<jsp:include page="/WEB-INF/common/footer.jsp" flush="true" />

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
		src="<%=application.getContextPath()%>/assets/javascripts/Reserved_Bed.js"></script>
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
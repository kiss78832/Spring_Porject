<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=" java.util.* "%>
<%@ page import="com.spring.order_detail.model.*"%>
<%@ page import="com.spring.room_order.model.*"%>

<!DOCTYPE html>
<html>

<%
	List<Order_DetailVO> order_DetailList = (List<Order_DetailVO>) request.getAttribute("Order_DetailList");
	pageContext.setAttribute("order_DetailList", order_DetailList);
%>

<jsp:useBean id="order_detailSvc"
	class="com.spring.order_detail.model.Order_DetailService" />
<jsp:useBean id="locSvc"
	class="com.spring.location.model.LocationService" />
<jsp:useBean id="mealSvc"
	class="com.spring.meal.model.MealService" />

<head>
<title></title>

<!-- canvasJS jquery line chart -->
<script type="text/javascript"
	src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
<!-- ========== -->



<!-- right side css -->
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/OrderMge.css">



</head>

<body>

	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>訂單明細</th>
					<th>訂單編號</th>
					<th>據點名稱</th>
					<th>訂床數</th>
					<th>訂床價錢</th>
					<th>入住日期</th>
					<th>套餐名稱</th>
					<th>套餐份數</th>
					<th>套餐價錢</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order_detailVO" items="${order_DetailList}"
					varStatus="s">
					<tr>
						<td class="txt-oflo">${order_detailVO.detail_id}</td>
						<td class="txt-oflo">${order_detailVO.order_id}</td>
						<td><span class="txt-oflo">${locSvc.getOneLocation(order_detailVO.location_id).location_name}</span></td>
						<td class="txt-oflo">${order_detailVO.bed_num}</td>
						<td class="text-success">${order_detailVO.bedTotal_price}</td>
						<td class="txt-oflo">${order_detailVO.checkin_date}</td>
						<td class="txt-oflo">${mealSvc.getOneMeal(order_detailVO.meal_id).meal_name}</td>
						<td class="txt-oflo">${order_detailVO.meal_quantity}</td>
						<td class="text-success">${order_detailVO.mealTotal_price}</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>

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


	<script
		src="<%=application.getContextPath()%>/assets/javascripts/OrderMge.js"></script>
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
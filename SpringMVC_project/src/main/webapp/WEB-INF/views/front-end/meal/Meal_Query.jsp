<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import=" java.util.* "%>
<%@ page import="com.spring.meal.model.*"%>
<!DOCTYPE html>

<%
	MealService mealSvc = new MealService();
	List<MealVO> list = mealSvc.getMealsByMeal_status(1);
	pageContext.setAttribute("list", list);
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


<!--  own design -->
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/assets/stylesheets/Meal_Query.css">
<!-- own js -->
<script type="text/javascript"
	src="<%=application.getContextPath()%>/assets/javascripts/Meal_Query.js"></script>


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
	/* border: 2px solid black; */
	margin: 15px auto;
}

/* content end*/
</style>


</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/WEB-INF/common/navbar.jsp" flush="true" />

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

					<div class="cb col-lg-12">
						<%-- <div class="row">--%>
						<!-- 700*400 -->
						<%@ include file="page1.file"%>

						<div class="page1 card-columns">
							<c:forEach var="mealVO" items="${list}" begin="<%=pageIndex %>"
								end="<%= pageIndex + rowsPerPage-1 %>">
								<%--<div class="col-lg-3  mb-3">--%>
								<div class="card">
									<%--<div class="card h-80	">--%>
									<div class="inner">
										<img class="card-img-top"
											src="<%=request.getContextPath()%>/getMealPic?meal_id=${mealVO.meal_id}"
											alt="${mealVO.meal_name}">
									</div>
									<div class="card-body">
										<h4 class="card-title">${mealVO.meal_name}</h4>
										<h5>$${mealVO.meal_price}</h5>
										<p class="card-text">${mealVO.meal_content}</p>
									</div>
								</div>
								<%--</div>--%>
							</c:forEach>
						</div>

						<div class="showLine justify-content-around">
							<%
								if (pageNumber > 0) {
							%>
							<b><font color=red>第<%=whichPage%>/<%=pageNumber%>頁
							</font></b>
							<%
								}
							%>
							<b style="color: white">●符 合 查 詢 條 件 如 下 所 示: 共<font
								color=red><%=rowNumber%></font>筆
							</b>
						</div>
						<%@ include file="page3.file"%>
						<%-- </div>--%>
					</div>

				</div>
			</div>






			<jsp:include page="/WEB-INF/common/footer.jsp" flush="true" />
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